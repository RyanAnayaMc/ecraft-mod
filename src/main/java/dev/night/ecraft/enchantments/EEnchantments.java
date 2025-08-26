package dev.night.ecraft.enchantments;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.enchantments.enchantmenteffects.TipperEnchantmentEffect;
import dev.night.ecraft.tags.ETags;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class EEnchantments {
    public static final RegistryKey<Enchantment> SOLAR_OVERDRIVE = keyOf("solar_overdrive");
    public static final RegistryKey<Enchantment> STEADY_RECHARGE = keyOf("steady_recharge");
    public static final RegistryKey<Enchantment> OVERCLOCK = keyOf("overclock");
    public static final RegistryKey<Enchantment> REPEATER = keyOf("repeater");
    public static final RegistryKey<Enchantment> EXTENDER = keyOf("extender");
    public static final RegistryKey<Enchantment> TIPPER = keyOf("tipper");

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchants = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(
                registerable,
                EXTENDER,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(ETags.Items.LANCE_ENCHANTABLE),
                                items.getOrThrow(ETags.Items.LANCE_ENCHANTABLE),
                                5,
                                4,
                                Enchantment.leveledCost(7, 10),
                                Enchantment.leveledCost(30, 11),
                                3,
                                AttributeModifierSlot.MAINHAND
                        )
                ).addEffect(
                        EnchantmentEffectComponentTypes.ATTRIBUTES,
                        new AttributeEnchantmentEffect(
                                Identifier.of(Ecraft.MOD_ID, "enchantment.extender"),
                                EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                                new EnchantmentLevelBasedValue.Linear(0.25f, 0.25f),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
                ).exclusiveSet(
                        enchants.getOrThrow(ETags.Enchantments.RANGE_EXCLUSIVE_SET)
                )
        );

        register(
                registerable,
                TIPPER,
                Enchantment.builder(
                        Enchantment.definition(
                                items.getOrThrow(ETags.Items.LANCE_ENCHANTABLE),
                                items.getOrThrow(ETags.Items.LANCE_ENCHANTABLE),
                                5,
                                2,
                                Enchantment.leveledCost(7, 10),
                                Enchantment.leveledCost(30, 11),
                                3,
                                AttributeModifierSlot.MAINHAND
                        )
                ).addEffect(
                        EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new TipperEnchantmentEffect(EnchantmentLevelBasedValue.linear(1.5f, 0.5f))
                ).addEffect(
                        EnchantmentEffectComponentTypes.ATTRIBUTES,
                        new AttributeEnchantmentEffect(
                                Identifier.of(Ecraft.MOD_ID, "enchantment.tipperdmg"),
                                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                                new EnchantmentLevelBasedValue.Constant(-0.33f),
                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                ).addEffect(
                        EnchantmentEffectComponentTypes.ATTRIBUTES,
                        new AttributeEnchantmentEffect(
                                Identifier.of(Ecraft.MOD_ID, "enchantment.tipperkb"),
                                EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                                new EnchantmentLevelBasedValue.Constant(-0.33f),
                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                ).exclusiveSet(
                        enchants.getOrThrow(ETags.Enchantments.RANGE_EXCLUSIVE_SET)
                )
        );
    }

    private static RegistryKey<Enchantment> keyOf(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Ecraft.MOD_ID, name));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing enchantments");
    }
}
