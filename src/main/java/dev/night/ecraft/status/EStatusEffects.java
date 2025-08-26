package dev.night.ecraft.status;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.attributes.EEntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class EStatusEffects {
        public static final RegistryEntry<StatusEffect> CHAOS_STATE = register("chaos_state",
                new EStatusEffect(StatusEffectCategory.BENEFICIAL, 0xDB198E));

        public static final RegistryEntry<StatusEffect> FOCUSED_DRILL_BEAM = register("focused_drill_beam",
                new EStatusEffect(StatusEffectCategory.BENEFICIAL, 0xFF8001)
                        .addAttributeModifier(
                                EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                                Identifier.of(Ecraft.MOD_ID, "effect.focused_drill_beam"),
                                10f,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
        );

        public static final RegistryEntry<StatusEffect> DIMENSIONAL_POCKETS = register("dimensional_pockets",
                new EStatusEffect(StatusEffectCategory.BENEFICIAL, 0xFF8001)
                        .addAttributeModifier(
                                EEntityAttributes.GENERIC_BACKPACK_SLOT_AMOUNT,
                                Identifier.of(Ecraft.MOD_ID, "effect.dimensional_pockets"),
                                3f,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
        );

        public static final RegistryEntry<StatusEffect> LIONS_BLESSING = register("lions_blessing",
                new DamageOnTouchStatusEffect(StatusEffectCategory.BENEFICIAL, 25, 0xB1E78E)
                        .addAttributeModifier(
                                EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE,
                                Identifier.ofVanilla("knockback_resistance"),
                                Integer.MAX_VALUE,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
        );

        public static final RegistryEntry<StatusEffect> INSTANT_MANA = register("instant_mana",
                new InstantManaStatusEffect(StatusEffectCategory.BENEFICIAL, 0x2990F0, 20));

        public static final RegistryEntry<StatusEffect> MANA_BLOCKADE = register("mana_blockade",
                new EStatusEffect(StatusEffectCategory.HARMFUL, 0x273576)
                        .addAttributeModifier(
                                EEntityAttributes.GENERIC_RESERVED_MANA,
                                Identifier.of(Ecraft.MOD_ID, "reserved_mana"),
                                25,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
        );

        public static final RegistryEntry<StatusEffect> MANA_SICKNESS = register("mana_sickness",
                new EStatusEffect(StatusEffectCategory.HARMFUL, 0x275784));

        public static final RegistryEntry<StatusEffect> MANA_REGENERATION = register("mana_regeneration",
                new ManaRegenerationStatusEffect(StatusEffectCategory.BENEFICIAL, 0x3C1AD6));

        public static final RegistryEntry<StatusEffect> MAGIC_POWER = register("magic_power",
                new EStatusEffect(StatusEffectCategory.BENEFICIAL, 0x802CDF)
                        .addAttributeModifier(
                                EEntityAttributes.GENERIC_MAGIC_ATTACK_DAMAGE,
                                Identifier.of(Ecraft.MOD_ID, "magic_damage"),
                                3,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
        );

        public static final RegistryEntry<StatusEffect> MAGIC_INEPTITUDE = register("magic_ineptitude",
                new EStatusEffect(StatusEffectCategory.HARMFUL, 0x731263)
                        .addAttributeModifier(
                                EEntityAttributes.GENERIC_MAGIC_ATTACK_DAMAGE,
                                Identifier.of(Ecraft.MOD_ID, "magic_damage"),
                                -4,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
        );

        public static final RegistryEntry<StatusEffect> MAGIC_FATIGUE = register("magic_fatigue",
                new EStatusEffect(StatusEffectCategory.HARMFUL, 0xA76E9D)
                        .addAttributeModifier(
                                EEntityAttributes.GENERIC_MANA_REGENERATION_DELAY,
                                Identifier.of(Ecraft.MOD_ID, "mana_regen_delay"),
                                40,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
                        .addAttributeModifier(
                                EEntityAttributes.GENERIC_DEPLETED_MANA_REGENERATION_DELAY,
                                Identifier.of(Ecraft.MOD_ID, "depleted_mana_regen_delay"),
                                20,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
        );

    public static final RegistryEntry<StatusEffect> RECALL = register("recall",
            new RecallEffect(StatusEffectCategory.BENEFICIAL, 0x22E8F0));

    public static final RegistryEntry<StatusEffect> RETURN = register("return",
            new ResurrectionEffect(StatusEffectCategory.BENEFICIAL, 0x382FD6));

        private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
            return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Ecraft.MOD_ID, id), statusEffect);
        }
}
