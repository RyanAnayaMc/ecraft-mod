package dev.night.ecraft.enchantments;

import com.mojang.serialization.MapCodec;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.enchantments.enchantmenteffects.TipperEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> TIPPER_EFFECT = register("tipper_effect", TipperEnchantmentEffect.CODEC);

    public static MapCodec<? extends EnchantmentEntityEffect> register(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(Ecraft.MOD_ID, name), codec);
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing enchantment effects");
    }
}