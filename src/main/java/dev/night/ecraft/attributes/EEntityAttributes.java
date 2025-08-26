package dev.night.ecraft.attributes;

import com.github.theredbrain.backpackattribute.BackpackAttribute;
import com.github.theredbrain.manaattributes.ManaAttributes;
import dev.night.ecraft.Ecraft;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.puffish.attributesmod.AttributesMod;

public class EEntityAttributes {
    /**
     * The number of extra backpack slots to grant to an entity.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_BACKPACK_SLOT_AMOUNT = BackpackAttribute.BACKPACK_CAPACITY;

    /**
     * The amount of mana to regenerate every {@link #GENERIC_MANA_TICK_THRESHOLD} ticks
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_MANA_REGENERATION = ManaAttributes.MANA_REGENERATION;

    /**
     * The maxumum amount of mana an entity can have.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_MAX_MANA = ManaAttributes.MAX_MANA;

    /**
     * If mana is not fully depleted, the delay before mana regeneration starts.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_MANA_REGENERATION_DELAY = ManaAttributes.MANA_REGENERATION_DELAY_THRESHOLD;

    /**
     * If mana is depleted, the delay before mana regeneration starts.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_DEPLETED_MANA_REGENERATION_DELAY = ManaAttributes.DEPLETED_MANA_REGENERATION_DELAY_THRESHOLD;

    /**
     * The number of ticks between each mana regeneration instance.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_MANA_TICK_THRESHOLD = ManaAttributes.MANA_TICK_THRESHOLD;

    /**
     * The percentage of maximum mana that cannot be used.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_RESERVED_MANA = ManaAttributes.RESERVED_MANA;

    /**
     * Magic damage from Pufferfish's attributes.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_MAGIC_ATTACK_DAMAGE = AttributesMod.MAGIC_DAMAGE;

    /**
     * Armor shred from Pufferfish's attributes.
     */
    public static final RegistryEntry<EntityAttribute> GENERIC_ARMOR_SHRED = AttributesMod.ARMOR_SHRED;

    private static RegistryEntry<EntityAttribute> register(String id, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(Ecraft.MOD_ID, id), attribute);
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing entity attributes");
    }
}
