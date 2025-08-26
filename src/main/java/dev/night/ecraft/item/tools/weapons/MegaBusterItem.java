package dev.night.ecraft.item.tools.weapons;

import dev.night.ecraft.client.item.MegaBusterItemRenderer;
import dev.night.ecraft.component.MegaBusterComponent;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.enchantments.EEnchantments;
import dev.night.ecraft.entity.MegaBusterChargeShot2Entity;
import dev.night.ecraft.entity.MegaBusterLemonEntity;
import dev.night.ecraft.entity.EEntities;
import dev.night.ecraft.sound.ESounds;
import dev.night.ecraft.util.Tuples;
import dev.night.ecraft.util.Utilities;
import net.minecraft.client.render.item.BuiltinModelItemRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.function.Consumer;

public class MegaBusterItem extends ToolItem implements GeoItem {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final int MEGA_BUSTER_PROJECTILE_DAMAGE = 4;
    public static final int MEGA_BUSTER_CHARGED_PROJECTILE_BASE_DAMAGE = 8;
    public static final int MEGA_BUSTER_CHARGED_PROJECTILE_DAMAGE_PER_LEVEL = 4;
    public static final float MEGA_BUSTER_PROJECTILE_VELOCITY = 1f;
    public static final int MEGA_BUSTER_PROJECTILE_LIFETIME_TICKS = 100;
    public static final int MEGA_BUSTER_NORMAL_SHOT_COOLDOWN_TICKS = 5;
    public static final int MEGA_BUSTER_CHARGE_HOLD_TIME = 72000;
    public static final int MEGA_BUSTER_LEVEL_ONE_CHARGE_REMAINING_TICKS = MEGA_BUSTER_CHARGE_HOLD_TIME - 40;
    public static final byte MEGA_BUSTER_CHARGE_SHOT_MAX_HITS = 3;

    public static final int MEGA_BUSTER_BATTERY_CAPACITY = 300;
    public static final int MEGA_BUSTER_CHARGED_SHOT_BATTERY_COST = 5;

    private static final String CONTROLLER_NAME = "mega_buster_controller";
    private static final RawAnimation SHOT_ANIM = RawAnimation.begin().thenPlay("shot");
    private static final RawAnimation CHARGE_SHOT_ANIM = RawAnimation.begin().thenPlay("charge_shot");


    public MegaBusterItem(Settings settings) {
        // TODO different tool material maybe
        super(ToolMaterials.IRON, settings);

        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        MegaBusterComponent component = stack.getOrDefault(EComponents.MEGA_BUSTER_COMPONENT, getDefaultComponent());
        tooltip.add(Text.translatable("item.ecraft.mega_buster.tooltip", component.battery(), MEGA_BUSTER_BATTERY_CAPACITY));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!entity.isPlayer() || world.isClient())
            return;

        boolean componentUpdated = false;

        // Get component data
        MegaBusterComponent component = stack.getOrDefault(EComponents.MEGA_BUSTER_COMPONENT, getDefaultComponent());
        int battery = component.battery();

        // If battery isn't full, refill
        if (battery <= MEGA_BUSTER_BATTERY_CAPACITY) {
            // Get enchant levels
            int solarOverdriveLevel = 0;
            boolean hasSteadyRecahrge = false;

            for (RegistryEntry<Enchantment> e : stack.getEnchantments().getEnchantments()) {
                if (e.matchesKey(EEnchantments.SOLAR_OVERDRIVE))
                    solarOverdriveLevel = stack.getEnchantments().getLevel(e);
                else if (e.matchesKey(EEnchantments.STEADY_RECHARGE))
                    hasSteadyRecahrge = true;
            }

            // If not in daylight without steady recharge, don't recharge
            if (!Utilities.isAffectedByDaylight(world, entity) && !hasSteadyRecahrge)
                return;

            // If eligible, refill battery by a low chance scaled by solar overdrive level
            int randomInt = world.random.nextInt(hasSteadyRecahrge ? 1350 : 900);
            boolean willRefill = randomInt <= 1 + 2 * solarOverdriveLevel;

            if (willRefill) {
                componentUpdated = true;

                // Each random tick will refill battery by 1/7th
                battery += MEGA_BUSTER_BATTERY_CAPACITY / 7;

                // Ensure battery isn't greater than maximum and play corresponding sfx
                if (battery >= MEGA_BUSTER_BATTERY_CAPACITY)
                    battery = MEGA_BUSTER_BATTERY_CAPACITY;
                world.playSound(null, entity.getBlockPos(), ESounds.MEGA_BUSTER_RECHARGE, SoundCategory.PLAYERS);
            }
        }

        // If component data was updated, update the component
        if (componentUpdated)
            stack.set(EComponents.MEGA_BUSTER_COMPONENT, new MegaBusterComponent(battery));
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return MEGA_BUSTER_CHARGE_HOLD_TIME;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (!world.isClient() && user.isPlayer())
            return stopUseAction(stack, (ServerWorld) world, (PlayerEntity) user, 0);

        return stack;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!world.isClient() && user.isPlayer())
            stopUseAction(stack, (ServerWorld) world, (PlayerEntity) user, remainingUseTicks);
    }

    private ItemStack stopUseAction(ItemStack stack, ServerWorld world, PlayerEntity user, int remainingUseTicks) {
        // Get item component
        MegaBusterComponent component = stack.getOrDefault(EComponents.MEGA_BUSTER_COMPONENT, getDefaultComponent());
        int battery = component.battery();
        int newBattery = battery;

        // Perform charged shot if charged long enough and battery is used
        if (remainingUseTicks <= MEGA_BUSTER_LEVEL_ONE_CHARGE_REMAINING_TICKS && battery >= MEGA_BUSTER_CHARGED_SHOT_BATTERY_COST)
            newBattery = chargedShot(world, user, stack, battery, 2).item2();
            // Otherwise do a normal shot
        else
            newBattery = normalShot(world, user, stack, battery).item2();

        // If battery value changed, update the component
        if (newBattery != battery)
            stack.set(EComponents.MEGA_BUSTER_COMPONENT, new MegaBusterComponent(newBattery));

        return stack;
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (world.isClient() || !user.isPlayer())
            return;

        // Get item component
        MegaBusterComponent component = stack.getOrDefault(EComponents.MEGA_BUSTER_COMPONENT, getDefaultComponent());
        int battery = component.battery();

        // Play sound effects for charging if has enough battery
        if (battery >= MEGA_BUSTER_CHARGED_SHOT_BATTERY_COST) {
            if (remainingUseTicks == MEGA_BUSTER_LEVEL_ONE_CHARGE_REMAINING_TICKS + 24)
                world.playSound(null, user.getBlockPos(), ESounds.MEGA_BUSTER_CHARGING_START, SoundCategory.PLAYERS);
            else if (remainingUseTicks <= MEGA_BUSTER_LEVEL_ONE_CHARGE_REMAINING_TICKS && remainingUseTicks % 5 == 0)
                world.playSound(null, user.getBlockPos(), ESounds.MEGA_BUSTER_CHARGING_LOOP, SoundCategory.PLAYERS);
        }
    }

    public Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> chargedShot(ServerWorld world, PlayerEntity user, ItemStack stack, int battery, int chargeLevel) {
        // If not enough battery, do normal shot instead
        if (battery < MEGA_BUSTER_CHARGED_SHOT_BATTERY_COST)
            return normalShot(world, user, stack, battery);

        // Create projectile entity and set attributes
        MegaBusterChargeShot2Entity shot = new MegaBusterChargeShot2Entity(EEntities.MEGA_BUSTER_CHARGE_SHOT_2, world);
        shot.setOwner(user);
        shot.setPos(user.getX(), user.getY() + 1.5f, user.getZ());
        shot.setVelocity(user, user.getPitch(), user.getYaw(), 0, MEGA_BUSTER_PROJECTILE_VELOCITY, 0);
        shot.setDamage(MEGA_BUSTER_CHARGED_PROJECTILE_BASE_DAMAGE + MEGA_BUSTER_CHARGED_PROJECTILE_DAMAGE_PER_LEVEL * chargeLevel);
        shot.setNoGravity(true);

        // Spawn projectile entity, play sound, trigger animation, reduce battery, and inflict cooldown
        battery -= MEGA_BUSTER_CHARGED_SHOT_BATTERY_COST;
        user.getItemCooldownManager().set(this, MEGA_BUSTER_NORMAL_SHOT_COOLDOWN_TICKS);
        triggerAnim(
                user,
                GeoItem.getOrAssignId(stack, (ServerWorld) world),
                CONTROLLER_NAME,
                "charge_shot"
        );
        world.spawnEntity(shot);
        world.playSound(null, user.getBlockPos(), ESounds.MEGA_BUSTER_CHARGE_SHOT, SoundCategory.PLAYERS);

        return new Tuples.TwoItem<>(TypedActionResult.pass(stack), battery);
    }

    public Tuples.TwoItem<TypedActionResult<ItemStack>, Integer> normalShot(ServerWorld world, PlayerEntity user, ItemStack stack, int battery) {
        // If no battery, don't shoot
        if (battery <= 0)
            return new Tuples.TwoItem<>(TypedActionResult.fail(stack), battery);

        // Create projectile entity and set attributes
        MegaBusterLemonEntity lemon = new MegaBusterLemonEntity(EEntities.MEGA_BUSTER_LEMON, world);
        lemon.setOwner(user);
        lemon.setPos(user.getX(), user.getY() + 1.5f, user.getZ());
        lemon.setVelocity(user, user.getPitch(), user.getYaw(), 0, MEGA_BUSTER_PROJECTILE_VELOCITY, 0);
        lemon.setDamage(MEGA_BUSTER_PROJECTILE_DAMAGE);
        lemon.setNoGravity(true);

        // Spawn projectile entity, play sound, trigger animation, reduce battery, and inflict cooldown
        battery--;
        user.getItemCooldownManager().set(this, MEGA_BUSTER_NORMAL_SHOT_COOLDOWN_TICKS);
        triggerAnim(
                user,
                GeoItem.getOrAssignId(stack, (ServerWorld) world),
                CONTROLLER_NAME,
                "shot"
        );
        world.spawnEntity(lemon);
        world.playSound(null, user.getBlockPos(), ESounds.MEGA_BUSTER_SHOT, SoundCategory.PLAYERS);

        return new Tuples.TwoItem<>(TypedActionResult.pass(stack), battery);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    private static MegaBusterComponent getDefaultComponent() {
        return new MegaBusterComponent(0);
    }


    //region GeckoLib Implementation
    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private MegaBusterItemRenderer renderer;

            @Override
            public BuiltinModelItemRenderer getGeoItemRenderer() {
                if (renderer == null)
                    renderer = new MegaBusterItemRenderer();

                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(
                new AnimationController<>(
                        this,
                        CONTROLLER_NAME,
                        0,
                        state -> PlayState.STOP
                )
                        .triggerableAnim("shot", SHOT_ANIM)
                        .triggerableAnim("charge_shot", CHARGE_SHOT_ANIM)
        );
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    //endregion
}
