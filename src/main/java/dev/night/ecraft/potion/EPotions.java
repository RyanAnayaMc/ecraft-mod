package dev.night.ecraft.potion;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.status.EStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class EPotions {
    public static final RegistryEntry<Potion> A_LITTLE_BIT_OF_EVERYTHING = register(
            "a_little_bit_of_everything",
            new Potion(
                    new StatusEffectInstance(StatusEffects.ABSORPTION, 200),
                    new StatusEffectInstance(StatusEffects.BAD_OMEN, 200),
                    new StatusEffectInstance(StatusEffects.BLINDNESS, 200),
                    new StatusEffectInstance(EStatusEffects.CHAOS_STATE, 200),
                    new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 200),
                    new StatusEffectInstance(StatusEffects.DARKNESS, 200),
                    new StatusEffectInstance(EStatusEffects.DIMENSIONAL_POCKETS, 200),
                    new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200),
                    new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200),
                    new StatusEffectInstance(StatusEffects.GLOWING, 200),
                    new StatusEffectInstance(StatusEffects.HASTE, 200),
                    new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200),
                    new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 200),
                    new StatusEffectInstance(StatusEffects.HUNGER, 200),
                    new StatusEffectInstance(StatusEffects.INFESTED, 200),
                    new StatusEffectInstance(StatusEffects.INVISIBILITY, 200),
                    new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200),
                    new StatusEffectInstance(StatusEffects.LEVITATION, 200),
                    new StatusEffectInstance(StatusEffects.LUCK, 200),
                    new StatusEffectInstance(EStatusEffects.LIONS_BLESSING, 200),
                    new StatusEffectInstance(EStatusEffects.MAGIC_FATIGUE, 200),
                    new StatusEffectInstance(EStatusEffects.MAGIC_INEPTITUDE, 200),
                    new StatusEffectInstance(EStatusEffects.MAGIC_POWER, 200),
                    new StatusEffectInstance(EStatusEffects.MANA_BLOCKADE, 200),
                    new StatusEffectInstance(EStatusEffects.MANA_REGENERATION, 200),
                    new StatusEffectInstance(EStatusEffects.MANA_SICKNESS, 200),
                    new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 200),
                    new StatusEffectInstance(StatusEffects.NAUSEA, 200),
                    new StatusEffectInstance(StatusEffects.NIGHT_VISION, 200),
                    new StatusEffectInstance(StatusEffects.OOZING, 200),
                    new StatusEffectInstance(StatusEffects.POISON, 200),
                    new StatusEffectInstance(StatusEffects.RESISTANCE, 200),
                    new StatusEffectInstance(StatusEffects.SATURATION, 200),
                    new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200),
                    new StatusEffectInstance(StatusEffects.SLOWNESS, 200),
                    new StatusEffectInstance(StatusEffects.SPEED, 200),
                    new StatusEffectInstance(StatusEffects.STRENGTH, 200),
                    new StatusEffectInstance(StatusEffects.TRIAL_OMEN, 200),
                    new StatusEffectInstance(StatusEffects.WATER_BREATHING, 200),
                    new StatusEffectInstance(StatusEffects.WEAKNESS, 200),
                    new StatusEffectInstance(StatusEffects.WEAVING, 200),
                    new StatusEffectInstance(StatusEffects.WIND_CHARGED, 200),
                    new StatusEffectInstance(StatusEffects.WITHER, 200)
            )
    );

    public static final RegistryEntry<Potion> AQUATIC_SPELUNKER = register(
            "aquatic_spelunker",
            new Potion(
                    new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 2400),
                    new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 2400)
            )
    );

    public static final RegistryEntry<Potion> LONG_AQUATIC_SPELUNKER = register(
            "long_aquatic_spelunker",
            new Potion(
                    "aquatic_spelunker",
                    new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 4800),
                    new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 4800)
            )
    );

    public static final RegistryEntry<Potion> LESSER_MANA_HEAL = register(
            "lesser_mana_heal",
            new Potion(
                    new StatusEffectInstance(EStatusEffects.INSTANT_MANA, 1, 0),
                    new StatusEffectInstance(EStatusEffects.MANA_SICKNESS, 1200)
            )
    );

    public static final RegistryEntry<Potion> MANA_HEAL = register(
            "mana_heal",
            new Potion(
                    new StatusEffectInstance(EStatusEffects.INSTANT_MANA, 1, 1),
                    new StatusEffectInstance(EStatusEffects.MANA_SICKNESS, 1200)
            )
    );

    public static final RegistryEntry<Potion> GREATER_MANA_HEAL = register(
            "greater_mana_heal",
            new Potion(
                    new StatusEffectInstance(EStatusEffects.INSTANT_MANA, 1, 2),
                    new StatusEffectInstance(EStatusEffects.MANA_SICKNESS, 1200)
            )
    );

    public static final RegistryEntry<Potion> SUPER_MANA_HEAL = register(
            "super_mana_heal",
            new Potion(
                    new StatusEffectInstance(EStatusEffects.INSTANT_MANA, 1, 3),
                    new StatusEffectInstance(EStatusEffects.MANA_SICKNESS, 1200)
            )
    );

    public static final RegistryEntry<Potion> MANA_BLOCKADE = register(
            "mana_blockade",
            new Potion(new StatusEffectInstance(EStatusEffects.MANA_BLOCKADE, 1200))
    );

    public static final RegistryEntry<Potion> LONG_MANA_BLOCKADE = register(
            "long_mana_blockade",
            new Potion("mana_blockade", new StatusEffectInstance(EStatusEffects.MANA_BLOCKADE, 2400))
    );

    public static final RegistryEntry<Potion> STRONG_MANA_BLOCKADE = register(
            "strong_mana_blockade",
            new Potion("mana_blockade", new StatusEffectInstance(EStatusEffects.MANA_BLOCKADE, 600, 1))
    );

    public static final RegistryEntry<Potion> MANA_REGENERATION = register(
            "mana_regeneration",
            new Potion(new StatusEffectInstance(EStatusEffects.MANA_REGENERATION, 900))
    );

    public static final RegistryEntry<Potion> LONG_MANA_REGENERATION = register(
            "long_mana_regeneration",
            new Potion("mana_regeneration", new StatusEffectInstance(EStatusEffects.MANA_REGENERATION, 1800))
    );

    public static final RegistryEntry<Potion> STRONG_MANA_REGENERATION = register(
            "strong_mana_regeneration",
            new Potion("mana_regeneration", new StatusEffectInstance(EStatusEffects.MANA_REGENERATION, 450, 1))
    );

    public static final RegistryEntry<Potion> MAGIC_POWER = register(
            "magic_power",
            new Potion(new StatusEffectInstance(EStatusEffects.MAGIC_POWER, 3600))
    );

    public static final RegistryEntry<Potion> LONG_MAGIC_POWER = register(
            "long_magic_power",
            new Potion("magic_power", new StatusEffectInstance(EStatusEffects.MAGIC_POWER, 9600))
    );

    public static final RegistryEntry<Potion> STRONG_MAGIC_POWER = register(
            "strong_magic_power",
            new Potion("magic_power", new StatusEffectInstance(EStatusEffects.MAGIC_POWER, 1800, 1))
    );

    public static final RegistryEntry<Potion> MAGIC_INEPTITUDE = register(
            "magic_ineptitude",
            new Potion(new StatusEffectInstance(EStatusEffects.MAGIC_INEPTITUDE, 1800))
    );

    public static final RegistryEntry<Potion> LONG_MAGIC_INEPTITUDE = register(
            "long_magic_ineptitude",
            new Potion("magic_ineptitude", new StatusEffectInstance(EStatusEffects.MAGIC_INEPTITUDE, 3600))
    );

    public static final RegistryEntry<Potion> STRONG_MAGIC_INEPTITUDE = register(
            "strong_magic_ineptitude",
            new Potion("magic_ineptitude", new StatusEffectInstance(EStatusEffects.MAGIC_INEPTITUDE, 900, 1))
    );

    public static final RegistryEntry<Potion> MAGIC_FATIGUE = register(
            "magic_fatigue",
            new Potion(new StatusEffectInstance(EStatusEffects.MAGIC_FATIGUE, 1800))
    );

    public static final RegistryEntry<Potion> LONG_MAGIC_FATIGUE = register(
            "long_magic_fatigue",
            new Potion("magic_fatigue", new StatusEffectInstance(EStatusEffects.MAGIC_FATIGUE, 3600))
    );

    public static final RegistryEntry<Potion> STRONG_MAGIC_FATIGUE = register(
            "strong_magic_fatigue",
            new Potion("magic_fatigue", new StatusEffectInstance(EStatusEffects.MAGIC_FATIGUE, 900, 1))
    );

    public static final RegistryEntry<Potion> RECALL = register(
            "recall",
            new Potion(new StatusEffectInstance(EStatusEffects.RECALL))
    );

    public static final RegistryEntry<Potion> STRONG_RECALL = register(
            "strong_recall",
            new Potion("recall", new StatusEffectInstance(EStatusEffects.RECALL, 0, 1))
    );

    public static final RegistryEntry<Potion> RESURRECTION = register(
            "resurrection",
            new Potion(new StatusEffectInstance(EStatusEffects.RETURN))
    );

    public static final RegistryEntry<Potion> STRONG_RESURRECTION = register(
            "strong_resurrection",
            new Potion("resurrection", new StatusEffectInstance(EStatusEffects.RETURN, 0, 1))
    );


    public static RegistryEntry<Potion> register(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(Ecraft.MOD_ID, name), potion);
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering potions");
    }
}
