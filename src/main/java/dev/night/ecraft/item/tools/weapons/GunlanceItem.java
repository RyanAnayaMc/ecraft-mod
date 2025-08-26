package dev.night.ecraft.item.tools.weapons;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.client.item.GunlanceItemRenderer;
import dev.night.ecraft.component.GunlanceComponent;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.sound.ESounds;
import dev.night.ecraft.util.Tuples;
import dev.night.ecraft.util.Utilities;
import dev.night.ecraft.world.GunlanceExplosionBehavior;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GunlanceItem extends LanceItem implements GeoItem {
    // GeckoLib variables
    private static final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("attack");
    private static final RawAnimation RELOAD_ANIM = RawAnimation.begin().thenPlay("reload");
    private static final RawAnimation SHELL_ANIM = RawAnimation.begin().thenPlay("shell");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Ammo variables
    public final int AMMO_CAPACITY;
    public static final float EXPLOSION_POWER = 2F;

    // Cooldown variables
    public static final int SHELL_COOLDOWN = 30;
    public static final int RELOAD_COOLDOWN = 30;
    public static final int FULL_BURST_COOLDOWN = 50;

    // Damage variables
    public static final int SHELL_DAMAGE_MULTIPLIER = 2;
    public static final int SHELL_CHARGE_DAMAGE_MULTIPLIER = 3;
    public static final int WYVERN_FIRE_DAMAGE_MULTIPLIER = 5;

    // Shelling variables
    public static final int SHELL_CHARGE_PRE_COOLDOWN = 6;
    public static final int SHELL_CHARGE_DURATION = 14;
    public static final int SHELL_CHARGE_MAX_TIME = 40;

    // Full burst variables
    public static final int FULL_BURST_SHOT_DELAY = 2;
    public static final double FULL_BURST_START_DISTANCE = 0.5;
    public static final double FULL_BURST_START_HEIGHT = 0;
    public static final float FULL_BURST_APEX = 4f;
    public static final float FULL_BURST_DISTANCE = 1f;

    // Combo variables
    public static final int COMBO_THRESHOLD = 22;

    // Wyvern fire variables
    public static final int WYVERN_FIRE_WINDUP = 70;
    public static final int WYVERN_FIRE_COOLDOWN = 2 * 60 * 20; // 2 minutes times 60 seconds per min times 20 ticks per second

    //region Constructors
    public GunlanceItem(ToolMaterial material, Settings settings, int capacity) {
        super(material, settings.component(EComponents.GUNLANCE_COMPONENT, getDefaultComponent(capacity)));
        SingletonGeoAnimatable.registerSyncedAnimatable(this);

        AMMO_CAPACITY = capacity;
    }

    public GunlanceItem(ToolMaterial material, Settings settings) {
        this(material, settings, 8);
    }
    //endregion

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        GunlanceComponent component = stack.getOrDefault(EComponents.GUNLANCE_COMPONENT, getDefaultComponent(AMMO_CAPACITY));
        tooltip.add(Text.translatable("item.ecraft.gunlance.tooltip1", component.currentAmmo(), AMMO_CAPACITY));
        tooltip.add(Text.translatable("item.ecraft.gunlance.tooltip2", getShellDamage(stack)));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.isPlayer() || world.isClient())
            return;

        // Get component data
        GunlanceComponent component = stack.getOrDefault(EComponents.GUNLANCE_COMPONENT, getDefaultComponent(this.AMMO_CAPACITY));
        int currentAmmo = component.currentAmmo();
        int fullBurstCooldown = component.fullBurstExplosionCooldown();
        List<Vec3d> explosionQueue = component.explosionQueue();

        // Check for wyvern fire or full bursts
        Tuples.ThreeItem<Integer, Long, Boolean> wyvernFireData =
                doWyvernFire(
                        (ServerWorld) world,
                        (PlayerEntity) entity,
                        component.wyvernFireWindupTime(),
                        component.lastWyvernFire(),
                        component.wyvernFireIsWinding()
                );

        fullBurstCooldown = doFullBurst(world, (PlayerEntity) entity, fullBurstCooldown, explosionQueue);

        world.getTime();

        // Update gunlance component
        stack.set(EComponents.GUNLANCE_COMPONENT, new GunlanceComponent(
                currentAmmo,
                component.shellChargingTime(),
                fullBurstCooldown, explosionQueue,
                component.lastHitTime(), component.currentCombo(),
                wyvernFireData.item1(), wyvernFireData.item2(), wyvernFireData.item3()
        ));
    }

    /**
     * @param ammoCapacity The starting ammo for the Gunlance component. Should ideally be the ammo capacity of the gunlance.
     * @return A new Gunlance component for a given ammo capacity with all cooldowns at zero
     */
    public static GunlanceComponent getDefaultComponent(int ammoCapacity) {
        return new GunlanceComponent(
                ammoCapacity,
                0,
                0, new ArrayList<>(),
                0, 0,
                0, 0, false
        );
    }

    //region Use methods
    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return SHELL_CHARGE_MAX_TIME;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!(user instanceof PlayerEntity) || world.isClient())
            return stack;

        // Let another method handle shelling
        return shellHandler(stack, (ServerWorld) world, (PlayerEntity) user);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity) || world.isClient())
            return;

        // Let another method handle shelling
        shellHandler(stack, (ServerWorld) world, (PlayerEntity) user);
    }

    private ItemStack shellHandler(ItemStack stack, ServerWorld world, PlayerEntity user) {
        // Stop charging sound
        // world.getServer().getCommandManager().executeWithPrefix(world.getServer().getCommandSource(), "stopsound " + user.getNameForScoreboard() + " player " + ModSounds.GUNLANCE_CHARGE.getId());

        // Get gunlance component
        GunlanceComponent component = stack.getOrDefault(EComponents.GUNLANCE_COMPONENT, getDefaultComponent(AMMO_CAPACITY));
        int currentAmmo = component.currentAmmo();
        int shellChargingTime = component.shellChargingTime();

        // Shell
        currentAmmo = shell(world, user, Hand.MAIN_HAND, shellChargingTime >= SHELL_CHARGE_DURATION + SHELL_CHARGE_PRE_COOLDOWN, currentAmmo).item2();

        // Save current ammo to weapon
        stack.set(EComponents.GUNLANCE_COMPONENT, new GunlanceComponent(
                currentAmmo,
                shellChargingTime,
                component.fullBurstExplosionCooldown(), component.explosionQueue(),
                component.lastHitTime(), component.currentCombo(),
                component.wyvernFireWindupTime(), component.lastWyvernFire(), component.wyvernFireIsWinding()
        ));

        return stack;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient())
            return;

        // Get gunlance component
        GunlanceComponent component = stack.getOrDefault(EComponents.GUNLANCE_COMPONENT, getDefaultComponent(AMMO_CAPACITY));
        int shellChargingTime = component.shellChargingTime();

        // Play shell charge sound effect if charging
        if (shellChargingTime == SHELL_CHARGE_PRE_COOLDOWN)
            world.playSound(null, user.getBlockPos(), ESounds.GUNLANCE_CHARGE, SoundCategory.PLAYERS);



        // Increment shell charging counter
        stack.set(EComponents.GUNLANCE_COMPONENT, new GunlanceComponent(
                component.currentAmmo(),
                shellChargingTime + 1,
                component.fullBurstExplosionCooldown(),
                component.explosionQueue(),
                component.lastHitTime(),
                component.currentCombo(),
                component.wyvernFireWindupTime(),
                component.lastWyvernFire(),
                component.wyvernFireIsWinding()
        ));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Server code
        if (world.isClient)
            return TypedActionResult.fail(user.getStackInHand(hand));

        // Gunlance only works from main hand
        if (hand.equals(Hand.OFF_HAND))
            return TypedActionResult.fail(user.getStackInHand(hand));

        // Get component and required data from it
        ItemStack stack = user.getStackInHand(Hand.MAIN_HAND);
        GunlanceComponent component = stack.getOrDefault(EComponents.GUNLANCE_COMPONENT, getDefaultComponent(AMMO_CAPACITY));
        TypedActionResult<ItemStack> returnValue = TypedActionResult.consume(user.getStackInHand(hand));
        int currentAmmo = component.currentAmmo();
        int wyvernFireWindupTime = component.wyvernFireWindupTime();
        long lastWyvernFire = component.lastWyvernFire();
        boolean wyvernFireIsWinding = component.wyvernFireIsWinding();
        List<Vec3d> explosionQueue = component.explosionQueue();
        int combo = component.currentCombo();

        // Check to see if the combo is still going
        long currentTime = world.getTime();
        if (!Utilities.checkTimeDelta(currentTime, component.lastHitTime(), COMBO_THRESHOLD))
            combo = 0;

        // Zero out charge time
        int shellChargingTime = 0;
        Ecraft.LOGGER.info("use method combo " + combo);

        // Be like a firework if using elytra
        if (user.isFallFlying()) {
            Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> shellData = shell((ServerWorld) world, user, hand, false, currentAmmo, true);
            returnValue = shellData.item1();
            boolean success = currentAmmo != shellData.item2();
            currentAmmo = shellData.item2();

            if (success) {
                Vec3d vec3d = user.getRotationVector();
                double d = 1.5;
                double e = 0.2;
                double f = 10;
                Vec3d vec3d2 = user.getVelocity();
                user
                        .setVelocity(
                                vec3d2.add(
                                        f * (vec3d.x * 0.1 + (vec3d.x * d - vec3d2.x) * e), f * (vec3d.y * e + (vec3d.y * d - vec3d2.y) * e), f * (vec3d.z * e + (vec3d.z * d - vec3d2.z) * e)
                                )
                        );
            }
        }
        // Reload if crouching and moving backwards
        else if (user.getPose() == EntityPose.CROUCHING && Utilities.isMovingBackwards(user)){
            Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> reloadData =
                    reload((ServerWorld) world, user, user.getStackInHand(hand), currentAmmo);
            returnValue = reloadData.item1();
            currentAmmo = reloadData.item2();
        }
        // Else if user is just crouching, wyvern fire
        else if (user.getPose() == EntityPose.CROUCHING) {
            Tuples.FourItem<TypedActionResult<ItemStack>, Integer, Long, Boolean> wyvernFireData =
                    wyvernFire(world, user, hand, wyvernFireWindupTime, lastWyvernFire, wyvernFireIsWinding);
            returnValue = wyvernFireData.item1();
            wyvernFireWindupTime = wyvernFireData.item2();
            lastWyvernFire = wyvernFireData.item3();
            wyvernFireIsWinding = wyvernFireData.item4();
        }
        // Full burst if conditions are right
        else if (combo > 0 && combo % 3 == 0) {
            Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> fullBurstData = fullBurst((ServerWorld) world, user, hand, currentAmmo, explosionQueue);
            returnValue = fullBurstData.item1();
            currentAmmo = fullBurstData.item2();
        }

        // Save component value
        stack.set(EComponents.GUNLANCE_COMPONENT, new GunlanceComponent(
                currentAmmo,
                shellChargingTime,
                component.fullBurstExplosionCooldown(), explosionQueue,
                component.lastHitTime(), combo,
                wyvernFireWindupTime, lastWyvernFire, wyvernFireIsWinding
        ));

        user.setCurrentHand(hand);
        return returnValue;
    }
    //endregion

    //region Hit methods
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        // Retrieve component
        GunlanceComponent component = stack.getOrDefault(EComponents.GUNLANCE_COMPONENT, getDefaultComponent(AMMO_CAPACITY));
        long lastHitTime = component.lastHitTime();
        int combo = component.currentCombo();

        long currentTime = attacker.getWorld().getTime();
        Ecraft.LOGGER.info("timeDelta " + (lastHitTime - currentTime));

        // Increment combo and reopen combo window
        if (Utilities.checkTimeDelta(currentTime, lastHitTime, COMBO_THRESHOLD))
            combo++;
        else
            combo = 1;
        Ecraft.LOGGER.info("posthit method combo " + combo);

        lastHitTime = attacker.getWorld().getTime();

        // Update component
        stack.set(EComponents.GUNLANCE_COMPONENT, new GunlanceComponent(
                component.currentAmmo(),
                component.shellChargingTime(),
                component.fullBurstExplosionCooldown(), component.explosionQueue(),
                lastHitTime, combo,
                component.wyvernFireWindupTime(), component.lastWyvernFire(), component.wyvernFireIsWinding()
        ));

        return super.postHit(stack, target, attacker);
    }
    //endregion

    //region Gunlance Attacks

    /**
     * @return Tuple of the TypedActionResult and an integer of the gunlance's current ammo
     */
    private Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> reload(ServerWorld world, PlayerEntity user, ItemStack itemStack, int currentAmmo) {
        // Get the count of gunlance shells in the user inventory
        int userGunlanceShells = user.getInventory().count(EItems.GUNLANCE_SHELL);
        boolean isCreative = user.isCreative();

        // Do nothing if no shells
        if (userGunlanceShells <= 0 && !isCreative)
            return new Tuples.TwoItem<>(TypedActionResult.pass(itemStack), currentAmmo);


        if (currentAmmo < AMMO_CAPACITY) {
            if (!isCreative) {
                // Find out how much ammo the gunlance needs
                int neededAmmo = AMMO_CAPACITY - currentAmmo;

                // If the user doesn't have enough shells in their inventory, the gunlance won't be fully loaded
                int reloadedAmmo = Math.min(userGunlanceShells, neededAmmo);

                // Remove the shells from the user's inventory
                Inventories.remove(user.getInventory(), (stack) -> stack.getItem().equals(EItems.GUNLANCE_SHELL), reloadedAmmo, false);

                // Add the ammo to the gunlance magazine
                currentAmmo += reloadedAmmo;
            } else
                // If creative mode, just fill the magazine
                currentAmmo = AMMO_CAPACITY;

            // Trigger the animation, play the sound effect, and inflict an item cooldown
            triggerAnim(user,
                    GeoItem.getOrAssignId(itemStack, world),
                    "gunlance_controller",
                    "reload"
            );
            world.playSound(null, user.getBlockPos(), ESounds.GUNLANCE_RELOAD, SoundCategory.PLAYERS);
            user.getItemCooldownManager().set(this, RELOAD_COOLDOWN);
        }

        return new Tuples.TwoItem<>(TypedActionResult.consume(itemStack), currentAmmo);
    }

    /**
     * @return Tuple where first element is TypedActionResult, second element  wyvernFireWindupTime, third element is lastWyvernFiretime fourth element is wyvernFireIsWinding (boolean, 0 is false 1 is true)
     */
    private Tuples.FourItem<TypedActionResult<ItemStack>, Integer, Long, Boolean> wyvernFire(World world, PlayerEntity user, Hand hand, int wyvernFireWindupTime, long lastWyvernFireTime, boolean wyvernFireIsWinding) {
        // Check cooldown
        if (Utilities.checkTimeDelta(world, lastWyvernFireTime, WYVERN_FIRE_COOLDOWN) || wyvernFireIsWinding)
            return new Tuples.FourItem<>(TypedActionResult.fail(user.getStackInHand(hand)), wyvernFireWindupTime, lastWyvernFireTime, wyvernFireIsWinding);

        // Add item cooldown
        user.getItemCooldownManager().set(this, WYVERN_FIRE_WINDUP);

        // Add slowness 5 and resistance 4
        user.addStatusEffect(
                new StatusEffectInstance(StatusEffects.SLOWNESS, WYVERN_FIRE_WINDUP, 5, false, false)
        );
        user.addStatusEffect(
                new StatusEffectInstance(StatusEffects.RESISTANCE, WYVERN_FIRE_WINDUP, 4, false, false)
        );

        // Play the sound effect
        world.playSound(null, user.getBlockPos(), ESounds.GUNLANCE_CHARGE, SoundCategory.PLAYERS);

        // Start timer for explosion
        wyvernFireWindupTime = WYVERN_FIRE_WINDUP;

        // wyvernFireIsWinding is set to true in this return statement since it is now queueing up the wyvern fire
        return new Tuples.FourItem<>(TypedActionResult.consume(user.getStackInHand(hand)), wyvernFireWindupTime, lastWyvernFireTime, true);
    }

    /**
     * @return Tuple where first item is wyvern fire windup time, second item is last wyvern fire time, third item is whether wyvern fire is winding up
     */
    private Tuples.ThreeItem<Integer, Long, Boolean> doWyvernFire(ServerWorld world, PlayerEntity user, int wyvernFireWindupTime, long lastWyvernFireTime, boolean wyvernFireIsWinding) {
        // Check cooldowns
        if (wyvernFireWindupTime > 0)
            // Decrement cooldown if nonzero
            wyvernFireWindupTime--;
        else if (wyvernFireIsWinding) {
            // If cooldown is zero AND the wyvern fire is winding up, unleash it
            wyvernFireIsWinding = false;

            doGunlanceExplosion(
                    world,
                    user,
                    getShellDamage(user.getStackInHand(Hand.MAIN_HAND)) * WYVERN_FIRE_DAMAGE_MULTIPLIER,
                    user.getPos().offset(user.getFacing(), 0.5),
                    true,
                    false
            );

            // Damage the stack and inflict cooldowns
            ItemStack stack = user.getStackInHand(Hand.MAIN_HAND);
            stack.damage(Math.clamp(10, 1, stack.getMaxDamage() - stack.getDamage() - 1), user, EquipmentSlot.MAINHAND);
            lastWyvernFireTime = world.getTime();
            user.getItemCooldownManager().set(this, SHELL_COOLDOWN);
        }

        return new Tuples.ThreeItem<>(wyvernFireWindupTime, lastWyvernFireTime, wyvernFireIsWinding);
    }

    /**
     * @return Tuple where the first element is the TypedActionResult, second element is the current ammo
     */
    private Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> fullBurst(ServerWorld world, PlayerEntity user, Hand hand, int currentAmmo, List<Vec3d> explosionQueue) {
        // If user has ammo and is grounded, full burst
        if (currentAmmo > 1 && user.isOnGround()) {

            // Populate queue of explosion events
            Vec3d usrPos = user.getPos();
            Direction usrFace = user.getHorizontalFacing();

            // Get a position in front of the user
            Vec3d first = usrPos
                    .offset(usrFace, FULL_BURST_START_DISTANCE)
                    .add(0, FULL_BURST_START_HEIGHT, 0);

            // Add the first explosion
            explosionQueue.add(first);

            // Calculate positions for remaining explosions and add them to the queue
            double deltaVertical = (FULL_BURST_APEX - FULL_BURST_START_HEIGHT) / currentAmmo;
            double deltaHorizontal = (FULL_BURST_DISTANCE - FULL_BURST_START_DISTANCE) / currentAmmo;

            for (int i = 1; i < currentAmmo; i++)
                explosionQueue.add(usrPos.offset(usrFace, FULL_BURST_START_DISTANCE + deltaHorizontal * i)
                        .add(0, FULL_BURST_START_HEIGHT + deltaVertical * i, 0));

            // Play shelling animation
            triggerAnim(user,
                    GeoItem.getOrAssignId(user.getStackInHand(hand), (ServerWorld) world),
                    "gunlance_controller",
                    "shell"
            );

            // Decrease ammo and inflict shell cooldown
            user.getItemCooldownManager().set(this, FULL_BURST_COOLDOWN);
            currentAmmo = 0;

            // Damage the stack
            ItemStack stack = user.getStackInHand(hand);
            stack.damage(Math.clamp(15, 1, stack.getMaxDamage() - stack.getDamage() - 1), user, EquipmentSlot.MAINHAND);
        } else if (currentAmmo > 1) {
            // if only one shot or user is airborne, normal shell instead
            currentAmmo = shell(world, user, hand, currentAmmo).item2();
        } else if (currentAmmo <= 0) {
            // Play click noise if gunlance is empty
            world.playSound(null, user.getBlockPos(), ESounds.GUNLANCE_EMPTY, SoundCategory.PLAYERS);
            return new Tuples.TwoItem<>(TypedActionResult.pass(user.getStackInHand(hand)), currentAmmo);
        }

        return new Tuples.TwoItem<>(TypedActionResult.pass(user.getStackInHand(hand)), currentAmmo);
    }

    /**
     * @return fullBurstExplosionCooldown
     */
    private int doFullBurst(World world, PlayerEntity entity, int fullBurstExplosionCooldown, List<Vec3d> explosionQueue) {
        if (fullBurstExplosionCooldown > 0)
            // Decrement full burst countdown timer
            fullBurstExplosionCooldown--;
        else if (!explosionQueue.isEmpty()) {
            // If timer is zero and there's an explosion in the queue, do it
            doGunlanceExplosion((ServerWorld) world, entity, getShellDamage(entity.getStackInHand(Hand.MAIN_HAND)) * SHELL_DAMAGE_MULTIPLIER, explosionQueue.removeFirst(), false, true);

            // Some recoil
            entity.changeLookDirection(0, 0.1);

            // Inflict cooldowns
            fullBurstExplosionCooldown = FULL_BURST_SHOT_DELAY;
            entity.getItemCooldownManager().set(this, FULL_BURST_COOLDOWN);
        }

        return fullBurstExplosionCooldown;
    }

    /**
     * @return Tuple with the TypedActionResult in the first item and the current ammo in the second
     */
    private Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> shell(ServerWorld world, PlayerEntity user, Hand hand, int currentAmmo) {
        // Call other shelling method
        return shell(world, user, hand, false, currentAmmo);
    }

    /**
     * @return Tuple with the TypedActionResult in the first item and the current ammo in the second
     */
    private Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> shell(ServerWorld world, PlayerEntity user, Hand hand, boolean isCharged, int currentAmmo) {
        return shell(world, user, hand, isCharged, currentAmmo, false);
    }

    /**
     * @return Tuple with the TypedActionResult in the first item and the current ammo in the second
     */
    private Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> shell(ServerWorld world, PlayerEntity user, Hand hand, boolean isCharged, int currentAmmo, boolean behind) {
        // If on cooldown, return
        if (user.getItemCooldownManager().isCoolingDown(this))
            return new Tuples.TwoItem<>(TypedActionResult.fail(user.getStackInHand(hand)), currentAmmo);

        // If user has ammo, shell
        if (currentAmmo > 0) {

            // Shell blast is in front of user
            Vec3d blastPos = user.getPos()
                    .offset(user.isOnGround() ? user.getHorizontalFacing() : user.getFacing(), behind ? -1f : 1f)
                    // add vertical offset if user is grounded so blast isn't in ground
                    .add(0, user.isOnGround() ? 1.5f : 0, 0);


            // Play shelling animation
            triggerAnim(user,
                    GeoItem.getOrAssignId(user.getStackInHand(hand), (ServerWorld) world),
                    "gunlance_controller",
                    "shell"
            );

            // Create shell explosion
            doGunlanceExplosion(world, user, getShellDamage(user.getStackInHand(Hand.MAIN_HAND)) * (isCharged ? SHELL_CHARGE_DAMAGE_MULTIPLIER : SHELL_DAMAGE_MULTIPLIER), blastPos, isCharged, false);

            // Decrease ammo and inflict shell cooldown and damage gunlance
            user.getItemCooldownManager().set(this, SHELL_COOLDOWN);
            currentAmmo--;
            ItemStack stack = user.getStackInHand(hand);
            stack.damage(Math.clamp(5, 1, stack.getMaxDamage() - stack.getDamage() - 1), user, EquipmentSlot.MAINHAND);
        } else {
            // Play click noise if gunlance is empty
            world.playSound(null, user.getBlockPos(), ESounds.GUNLANCE_EMPTY, SoundCategory.PLAYERS);
            return new Tuples.TwoItem<>(TypedActionResult.pass(user.getStackInHand(hand)), currentAmmo);
        }

        return new Tuples.TwoItem<>(TypedActionResult.pass(user.getStackInHand(hand)), currentAmmo);
    }

    private static Explosion doGunlanceExplosion(ServerWorld world, PlayerEntity user, float shellDamage, Vec3d blastPos, boolean useChargeSound, boolean isFullBurst) {
        // Create an explosion for the gunlance
        return world.createExplosion(
                null,
                Explosion.createDamageSource(world, user),
                new GunlanceExplosionBehavior(user, shellDamage, isFullBurst),
                blastPos.getX(),
                blastPos.getY(),
                blastPos.getZ(),
                EXPLOSION_POWER,
                false,
                World.ExplosionSourceType.TRIGGER,
                ParticleTypes.EXPLOSION,
                ParticleTypes.EXPLOSION_EMITTER,
                useChargeSound ? ESounds.GUNLANCE_CHARGE_FIRE : ESounds.GUNLANCE_FIRE
        );
    }

    public float getShellDamage(ItemStack stack) {
        return getWeaponDamage(stack);
    }
    //endregion

    //region GeckoLib implementation
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        "gunlance_controller",
                        0,
                        state -> PlayState.STOP
                )
                        .triggerableAnim("attack", ATTACK_ANIM)
                        .triggerableAnim("shell", SHELL_ANIM)
                        .triggerableAnim("reload", RELOAD_ANIM)
        );
    }


    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(
                new GeoRenderProvider() {
                    private GunlanceItemRenderer renderer;

                    @Override
                    public BuiltinModelItemRenderer getGeoItemRenderer() {
                        if (this.renderer == null)
                            this.renderer = new GunlanceItemRenderer(getMaterial());

                        return this.renderer;
                    }
                }
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    //endregion
}