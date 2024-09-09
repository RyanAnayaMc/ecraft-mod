package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.client.Keybinds;
import dev.night.ecraft.client.model.GunlanceItemRenderer;
import dev.night.ecraft.sound.ModSounds;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class GunlanceItem extends LanceItem implements GeoItem {
    private final RawAnimation ATTACK_ANIM = RawAnimation.begin().thenPlay("attack");
    private final RawAnimation RELOAD_ANIM = RawAnimation.begin().thenPlay("reload");
    private final RawAnimation SHELL_ANIM = RawAnimation.begin().thenPlay("shell");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    // Ammo variables
    private int currentAmmo;
    private final int AMMO_CAPACITY;
    private static final float EXPLOSION_POWER = 2F;

    // Cooldown variables
    public static final int SHELL_COOLDOWN = 30;
    public static final int RELOAD_COOLDOWN = 30;
    public static final int FULL_BURST_COOLDOWN = 50;
    private int cooldownCurrent;

    // Damage variables
    private static final int SHELL_DAMAGE_MULTIPLIER = 2;
    private static final int SHELL_CHARGE_DAMAGE_MULTIPLIER = 3;
    private static final int WYVERN_FIRE_DAMAGE_MULTIPLIER = 5;

    // Shelling variables
    private static final int SHELL_CHARGE_PRE_COOLDOWN = 6;
    private static final int SHELL_CHARGE_DURATION = 14;
    private static final int SHELL_CHARGE_MAX_TIME = 40;
    private int shellChargingTime;
    private boolean chargeSfxPlayed;

    // Full burst variables
    private int fullBurstExplosionCooldown;
    public static final int FULL_BURST_SHOT_DELAY = 2;
    public static final double FULL_BURST_START_DISTANCE = 0.5;
    public static final double FULL_BURST_START_HEIGHT = 0;
    public static final float FULL_BURST_APEX = 4f;
    public static final float FULL_BURST_DISTANCE = 1f;
    private Queue<Vec3d> explosionQueue;

    // Combo variables
    private static final int COMBO_THRESHOLD = 16;
    private int comboTimer;
    private int combo;

    // Wyvern fire variables
    private static final int WYVERN_FIRE_WINDUP = 70;
    private static final int WYVERN_FIRE_COOLDOWN = 2 * 60 * 20; // 2 minutes times 60 seconds per min times 20 ticks per second
    private int wyvernFireWindupTime;
    private int wyvernFireCooldownTime;
    private boolean wyvernFireIsWinding;

    //region Constructors
    public GunlanceItem(ToolMaterial material, Settings settings, int capacity) {
        super(material, settings);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);

        AMMO_CAPACITY = capacity;
        currentAmmo = capacity;
        cooldownCurrent = 0;
        fullBurstExplosionCooldown = 0;
        explosionQueue = new LinkedList<Vec3d>();
        comboTimer = 0;
        combo = 0;
        shellChargingTime = 0;
        chargeSfxPlayed = false;
        wyvernFireCooldownTime = 0;
        wyvernFireWindupTime = 0;
        wyvernFireIsWinding = false;
    }

    public GunlanceItem(ToolMaterial material, Settings settings) {
        this(material, settings, 8);
    }
    //endregion

    public int getCurrentAmmo() {
        return currentAmmo;
    }

    public int getAmmoCapacity() {
        return AMMO_CAPACITY;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.isPlayer() || world.isClient())
            return;

        // Decrease cooldowns
        if (cooldownCurrent > 0)
            cooldownCurrent--;

        if (comboTimer > 0)
            comboTimer--;
        else
            combo = 0;

        if (wyvernFireCooldownTime > 0)
            wyvernFireCooldownTime--;

        // Check for wyvern fire or full bursts
        doWyvernFire((ServerWorld) world, (PlayerEntity) entity);

        doFullBurst(world, (PlayerEntity) entity);

        if (((PlayerEntity) entity).getMainHandStack().getItem().equals(this) && Keybinds.RELOAD_KEYBIND.isPressed())
            reload((ServerWorld) world, (PlayerEntity) entity, stack);
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

        return shellHandler(stack, (ServerWorld) world, (PlayerEntity) user);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity) || world.isClient())
            return;

        shellHandler(stack, (ServerWorld) world, (PlayerEntity) user);
    }

    private ItemStack shellHandler(ItemStack stack, ServerWorld world, PlayerEntity user) {
        shell(world, user, Hand.MAIN_HAND, shellChargingTime >= SHELL_CHARGE_DURATION + SHELL_CHARGE_PRE_COOLDOWN);

        return stack;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!chargeSfxPlayed && shellChargingTime >= SHELL_CHARGE_PRE_COOLDOWN) {
            world.playSound(null, user.getBlockPos(), ModSounds.GUNLANCE_CHARGE, SoundCategory.PLAYERS);
            chargeSfxPlayed = true;
        }

        shellChargingTime++;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        // Server code
        if (world.isClient)
            return TypedActionResult.fail(user.getStackInHand(hand));

        shellChargingTime = 0;
        chargeSfxPlayed = false;

        // Gunlance only works from main hand
        if (hand.equals(Hand.OFF_HAND))
            return TypedActionResult.fail(user.getStackInHand(hand));

        Ecraft.LOGGER.info("id of item used is " + GeoItem.getId(user.getStackInHand(hand)));

        // Wyvern fire if crouching
        if (user.getPose() == EntityPose.CROUCHING)
            return wyvernFire(world, user, hand);
            // Full burst if conditions are right
        else if (combo > 0 && combo % 3 == 0)
            return fullBurst((ServerWorld) world, user, hand);

        user.setCurrentHand(hand);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
    //endregion

    //region Hit methods
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (comboTimer > 0 || combo == 0) {
            comboTimer = COMBO_THRESHOLD;
            combo++;
        }

        return super.postHit(stack, target, attacker);
    }
    //endregion

    //region Gunlance Attacks
    private TypedActionResult<ItemStack> reload(ServerWorld world, PlayerEntity user, ItemStack itemStack) {
        int userGunlanceShells = user.getInventory().count(ModItems.GUNLANCE_SHELL);

        if (cooldownCurrent > 0 || userGunlanceShells <= 0)
            return TypedActionResult.fail(itemStack);

        if (currentAmmo < AMMO_CAPACITY) {
            if (!(user).isCreative()) {
                int neededAmmo = AMMO_CAPACITY - currentAmmo;
                int reloadedAmmo = Math.min(userGunlanceShells, neededAmmo);

                Inventories.remove(user.getInventory(), (stack) -> stack.getItem().equals(ModItems.GUNLANCE_SHELL), reloadedAmmo, false);

                currentAmmo += reloadedAmmo;
            } else
                currentAmmo = AMMO_CAPACITY;
            triggerAnim(user,
                    GeoItem.getOrAssignId(itemStack, world),
                    "controller",
                    "reload"
            );
            world.playSound(null, user.getBlockPos(), ModSounds.GUNLANCE_RELOAD, SoundCategory.PLAYERS);
            cooldownCurrent = RELOAD_COOLDOWN;
        }

        return TypedActionResult.consume(itemStack);
    }

    private TypedActionResult<ItemStack> wyvernFire(World world, PlayerEntity user, Hand hand) {
        // Check cooldown
        if (wyvernFireCooldownTime > 0 || cooldownCurrent > 0 || wyvernFireIsWinding)
            return TypedActionResult.fail(user.getStackInHand(hand));

        // Add slowness 5 and resistance 4
        user.addStatusEffect(
                new StatusEffectInstance(StatusEffects.SLOWNESS, WYVERN_FIRE_WINDUP, 5, false, false)
        );
        user.addStatusEffect(
                new StatusEffectInstance(StatusEffects.RESISTANCE, WYVERN_FIRE_WINDUP, 4, false, false)
        );

        world.playSound(null, user.getBlockPos(), ModSounds.GUNLANCE_CHARGE, SoundCategory.PLAYERS);

        // Start timer for explosion
        wyvernFireWindupTime = WYVERN_FIRE_WINDUP;
        wyvernFireIsWinding = true;

        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    private void doWyvernFire(ServerWorld world, PlayerEntity user) {
        if (wyvernFireWindupTime > 0)
            wyvernFireWindupTime--;
        else if (wyvernFireIsWinding) {
            wyvernFireIsWinding = false;

            doGunlanceExplosion(
                    world,
                    user,
                    WYVERN_FIRE_DAMAGE_MULTIPLIER,
                    user.getPos().offset(user.getFacing(), 0.5),
                    true,
                    false
            );

            ItemStack stack = user.getStackInHand(Hand.MAIN_HAND);
            stack.damage(Math.clamp(10, 1, stack.getMaxDamage() - stack.getDamage() - 1), user, EquipmentSlot.MAINHAND);
            wyvernFireCooldownTime = WYVERN_FIRE_COOLDOWN;
            cooldownCurrent = SHELL_COOLDOWN;
        }
    }

    private TypedActionResult<ItemStack> fullBurst(ServerWorld world, PlayerEntity user, Hand hand) {
        // If user has ammo and is grounded, full burst
        if (cooldownCurrent <= 0 && currentAmmo > 1 && user.isOnGround()) {

            // Populate queue of explosion events
            Vec3d usrPos = user.getPos();
            Direction usrFace = user.getHorizontalFacing();

            Vec3d first = usrPos
                    .offset(usrFace, FULL_BURST_START_DISTANCE)
                    .add(0, FULL_BURST_START_HEIGHT, 0);

            explosionQueue.add(first);

            double deltaVertical = (FULL_BURST_APEX - FULL_BURST_START_HEIGHT) / currentAmmo;
            double deltaHorizontal = (FULL_BURST_DISTANCE - FULL_BURST_START_DISTANCE) / currentAmmo;

            for (int i = 1; i < currentAmmo; i++)
                explosionQueue.add(usrPos.offset(usrFace, FULL_BURST_START_DISTANCE + deltaHorizontal * i)
                        .add(0, FULL_BURST_START_HEIGHT + deltaVertical * i, 0));

            // Play shelling animation
            triggerAnim(user,
                    GeoItem.getOrAssignId(user.getStackInHand(hand), (ServerWorld) world),
                    "controller",
                    "shell"
            );

            // Decrease ammo and inflict shell cooldown
            cooldownCurrent = FULL_BURST_COOLDOWN;
            currentAmmo = 0;

            ItemStack stack = user.getStackInHand(hand);
            stack.damage(Math.clamp(15, 1, stack.getMaxDamage() - stack.getDamage() - 1), user, EquipmentSlot.MAINHAND);
        } else if (currentAmmo > 1) {
            // if only one shot or user is airborne, normal shell instead
            shell(world, user, hand);
        } else if (currentAmmo <= 0) {
            // Play click noise if gunlance is empty
            world.playSound(null, user.getBlockPos(), ModSounds.GUNLANCE_EMPTY, SoundCategory.PLAYERS);
            return TypedActionResult.fail(user.getStackInHand(hand));
        }

        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    private void doFullBurst(World world, PlayerEntity entity) {
        if (fullBurstExplosionCooldown > 0)
            fullBurstExplosionCooldown--;
        else if (!explosionQueue.isEmpty()) {
            doGunlanceExplosion((ServerWorld) world, (PlayerEntity) entity, SHELL_DAMAGE_MULTIPLIER, explosionQueue.poll(), false, true);
            ((PlayerEntity) entity).changeLookDirection(0, 0.1);
            fullBurstExplosionCooldown = FULL_BURST_SHOT_DELAY;
            cooldownCurrent = SHELL_COOLDOWN;
        }
    }

    private TypedActionResult<ItemStack> shell(ServerWorld world, PlayerEntity user, Hand hand) {
        return shell(world, user, hand, false);
    }

    private TypedActionResult<ItemStack> shell(ServerWorld world, PlayerEntity user, Hand hand, boolean isCharged) {
        // If user has ammo, shell
        if (cooldownCurrent <= 0 && currentAmmo > 0) {

            // Shell blast is in front of user
            Vec3d blastPos = user.getPos()
                    .offset(user.isOnGround() ? user.getHorizontalFacing() : user.getFacing(), 1f)
                    // add vertical offset if user is grounded to blast isn't in ground
                    .add(0, user.isOnGround() ? 1.5f : 0, 0);


            // Play shelling animation
            triggerAnim(user,
                    GeoItem.getOrAssignId(user.getStackInHand(hand), (ServerWorld) world),
                    "controller",
                    "shell"
            );

            // Create shell explosion
            doGunlanceExplosion(world, user, isCharged ? SHELL_CHARGE_DAMAGE_MULTIPLIER : SHELL_DAMAGE_MULTIPLIER, blastPos, isCharged, false);

            // Decrease ammo and inflict shell cooldown and damage gunlance
            cooldownCurrent = SHELL_COOLDOWN;
            currentAmmo--;

            ItemStack stack = user.getStackInHand(hand);
            stack.damage(Math.clamp(5, 1, stack.getMaxDamage() - stack.getDamage() - 1), user, EquipmentSlot.MAINHAND);
        } else if (currentAmmo <= 0) {
            // Play click noise if gunlance is empty
            world.playSound(null, user.getBlockPos(), ModSounds.GUNLANCE_EMPTY, SoundCategory.PLAYERS);
            return TypedActionResult.fail(user.getStackInHand(hand));
        }

        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    private static Explosion doGunlanceExplosion(ServerWorld world, PlayerEntity user, float damageMultiplier, Vec3d blastPos, boolean useChargeSound, boolean isFullBurst) {
        return world.createExplosion(
                null,
                Explosion.createDamageSource(world, user),
                new GunlanceExplosionBehavior(user, getDamage(user) * damageMultiplier, isFullBurst),
                blastPos.getX(),
                blastPos.getY(),
                blastPos.getZ(),
                EXPLOSION_POWER,
                false,
                World.ExplosionSourceType.TRIGGER,
                ParticleTypes.EXPLOSION,
                ParticleTypes.EXPLOSION_EMITTER,
                useChargeSound ? ModSounds.GUNLANCE_CHARGE_FIRE : ModSounds.GUNLANCE_FIRE
        );
    }

    private static float getDamage(PlayerEntity user) {
        return ((GunlanceItem)user.getStackInHand(Hand.MAIN_HAND).getItem()).getWeaponDamage(user.getStackInHand(Hand.MAIN_HAND));
    }
    //endregion

    //region GeckoLib implementation
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        "controller",
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
                    public @NotNull BuiltinModelItemRenderer getGeoItemRenderer() {
                        if (this.renderer == null)
                            renderer = new GunlanceItemRenderer(getMaterial());

                        return renderer;
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