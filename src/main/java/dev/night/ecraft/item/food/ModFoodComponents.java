package dev.night.ecraft.item.food;

import dev.night.ecraft.Ecraft;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;

public class ModFoodComponents {
    public static final FoodComponent DR_PEPPER =
            new FoodComponent.Builder()
                    .nutrition(3)
                    .saturationModifier(1F)
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

    public static final FoodComponent PIBB_XTRA =
            new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(0.5F)
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.BAD_OMEN, Integer.MAX_VALUE, 0), 1f
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 0), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 1200, 49), 1.0F
                    )
                    .statusEffect(
                            new StatusEffectInstance(StatusEffects.NAUSEA, 1200, 9), 1.0F
                    )
                    .alwaysEdible().build();

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing food components");
    }

}
