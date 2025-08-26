package dev.night.ecraft.item.food;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.status.EStatusEffects;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class EFoodComponents {
    public static final FoodComponent RAW_CHICKEN_NUGGET =
            new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(.03f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 60, 0), 0.4F)
                    .snack()
                    .build();

    public static final FoodComponent CHICKEN_NUGGET =
            new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(.06f)
                    .snack()
                    .build();

    public static final FoodComponent DR_PEPPER =
            new FoodComponent.Builder()
                    .nutrition(5)
                    .saturationModifier(3F)
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.REGENERATION, 2400, 0), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.REGENERATION, 200, 22), 1f // 23 flavors babyyyyyy
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.RESISTANCE, 1200, 0), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.HASTE, 2400, 4), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 4), 1.0F
                    )
                    .alwaysEdible().build();

    public static final FoodComponent JARRITOS =
            new FoodComponent.Builder()
                    .nutrition(6)
                    .saturationModifier(4f)
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.HASTE, 2400, 2), 1f
                    )
                    .statusEffect(
                            new StatusEffectInstance(EStatusEffects.DIMENSIONAL_POCKETS, 24000, 2), 1f
                    )
                    .alwaysEdible().build();

    public static final FoodComponent SNAPPLE =
            new FoodComponent.Builder()
                    .nutrition(8)
                    .saturationModifier(5f)
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 2), 1f
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.LUCK, 7200, 2), .5f
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 1200, 1), .5f
                    )
                    .alwaysEdible().build();

    public static final FoodComponent PIBB_XTRA =
            new FoodComponent.Builder()
                    .nutrition(2)
                    .saturationModifier(0.75F)
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.SLOWNESS, 50, 0), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.BAD_OMEN, 3000, 0), .5f
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.BLINDNESS, 60, 0), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 200, 49), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.NAUSEA, 150, 4), 1.0F
                    )
                    .alwaysEdible().build();

    public static final FoodComponent RED_BULL =
            new FoodComponent.Builder()
                    .nutrition(3)
                    .saturationModifier(.75f)
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.LEVITATION, 80, 7), 1f
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.SLOW_FALLING, 240, 1), 1f
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.SPEED, 240, 1), 1f
                    )
                    .alwaysEdible().build();

    public static final FoodComponent MAGIC_APPLE =
            new FoodComponent.Builder()
                .nutrition(5)
                .saturationModifier(1.5F)
                .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 0), 1.0F)
                .statusEffect(new StatusEffectInstance(EStatusEffects.MANA_REGENERATION, 200, 1), 1.0F)
                .alwaysEdible().build();

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing food components");
    }

}
