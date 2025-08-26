package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.attributes.EEntityAttributes;
import dev.night.ecraft.util.Tuples;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.puffish.attributesmod.AttributesMod;

import static dev.night.ecraft.item.EItems.registerItem;

public class EArmor {
    //region Copper Armor
    public static final Item COPPER_HELMET = createArmor(
            "copper_helmet",
            EArmorMaterials.COPPER,
            EArmorMaterials.COPPER_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.COMMON
    );

    public static final Item COPPER_CHESTPLATE = createArmor(
            "copper_chestplate",
            EArmorMaterials.COPPER,
            EArmorMaterials.COPPER_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.COMMON
    );

    public static final Item COPPER_LEGGINGS = createArmor(
            "copper_leggings",
            EArmorMaterials.COPPER,
            EArmorMaterials.COPPER_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.COMMON
    );

    public static final Item COPPER_BOOTS = createArmor(
            "copper_boots",
            EArmorMaterials.COPPER,
            EArmorMaterials.COPPER_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.COMMON
    );
    //endregion

    //region Tungsten Armor
    public static final Item TUNGSTEN_HELMET = createArmor(
            "tungsten_helmet",
            EArmorMaterials.TUNGSTEN,
            EArmorMaterials.TUNGSTEN_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.COMMON
    );

    public static final Item TUNGSTEN_CHESTPLATE = createArmor(
            "tungsten_chestplate",
            EArmorMaterials.TUNGSTEN,
            EArmorMaterials.TUNGSTEN_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.COMMON
    );

    public static final Item TUNGSTEN_LEGGINGS = createArmor(
            "tungsten_leggings",
            EArmorMaterials.TUNGSTEN,
            EArmorMaterials.TUNGSTEN_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.COMMON
    );

    public static final Item TUNGSTEN_BOOTS = createArmor(
            "tungsten_boots",
            EArmorMaterials.TUNGSTEN,
            EArmorMaterials.TUNGSTEN_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.COMMON
    );
    //endregion

    //region Demonite Armor
    public static final Item DEMONITE_HELMET = createArmor(
            "demonite_helmet",
            EArmorMaterials.DEMONITE,
            EArmorMaterials.DEMONITE_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.COMMON
    );

    public static final Item DEMONITE_CHESTPLATE = createArmor(
            "demonite_chestplate",
            EArmorMaterials.DEMONITE,
            EArmorMaterials.DEMONITE_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.COMMON
    );

    public static final Item DEMONITE_LEGGINGS = createArmor(
            "demonite_leggings",
            EArmorMaterials.DEMONITE,
            EArmorMaterials.DEMONITE_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.COMMON
    );

    public static final Item DEMONITE_BOOTS = createArmor(
            "demonite_boots",
            EArmorMaterials.DEMONITE,
            EArmorMaterials.DEMONITE_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.COMMON
    );
    //endregion

    //region Hellstone Armor
    public static final Item HELLSTONE_HELMET = createFireproofArmor(
            "hellstone_helmet",
            EArmorMaterials.HELLSTONE,
            EArmorMaterials.HELLSTONE_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.COMMON
    );

    public static final Item HELLSTONE_CHESTPLATE = createFireproofArmor(
            "hellstone_chestplate",
            EArmorMaterials.HELLSTONE,
            EArmorMaterials.HELLSTONE_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.COMMON
    );

    public static final Item HELLSTONE_LEGGINGS = createFireproofArmor(
            "hellstone_leggings",
            EArmorMaterials.HELLSTONE,
            EArmorMaterials.HELLSTONE_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.COMMON
    );

    public static final Item HELLSTONE_BOOTS = createFireproofArmor(
            "hellstone_boots",
            EArmorMaterials.HELLSTONE,
            EArmorMaterials.HELLSTONE_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.COMMON
    );
    //endregion

    //region Cobalt Armor
    public static final Item COBALT_HELMET = createFireproofArmor(
            "cobalt_helmet",
            EArmorMaterials.COBALT,
            EArmorMaterials.COBALT_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.UNCOMMON
    );

    public static final Item COBALT_CHESTPLATE = createFireproofArmor(
            "cobalt_chestplate",
            EArmorMaterials.COBALT,
            EArmorMaterials.COBALT_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.UNCOMMON
    );

    public static final Item COBALT_LEGGINGS = createFireproofArmor(
            "cobalt_leggings",
            EArmorMaterials.COBALT,
            EArmorMaterials.COBALT_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.UNCOMMON
    );

    public static final Item COBALT_BOOTS = createFireproofArmor(
            "cobalt_boots",
            EArmorMaterials.COBALT,
            EArmorMaterials.COBALT_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.UNCOMMON
    );
    //endregion

    //region Orichalcum Armor
    public static final Item ORICHALCUM_HELMET = createFireproofArmor(
            "orichalcum_helmet",
            EArmorMaterials.ORICHALCUM,
            EArmorMaterials.ORICHALCUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.UNCOMMON
    );

    public static final Item ORICHALCUM_CHESTPLATE = createFireproofArmor(
            "orichalcum_chestplate",
            EArmorMaterials.ORICHALCUM,
            EArmorMaterials.ORICHALCUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.UNCOMMON
    );

    public static final Item ORICHALCUM_LEGGINGS = createFireproofArmor(
            "orichalcum_leggings",
            EArmorMaterials.ORICHALCUM,
            EArmorMaterials.ORICHALCUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.UNCOMMON
    );

    public static final Item ORICHALCUM_BOOTS = createFireproofArmor(
            "orichalcum_boots",
            EArmorMaterials.ORICHALCUM,
            EArmorMaterials.ORICHALCUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.UNCOMMON
    );
    //endregion

    //region Titanium Armor
    public static final Item TITANIUM_HELMET = createFireproofArmor(
            "titanium_helmet",
            EArmorMaterials.TITANIUM,
            EArmorMaterials.TITANIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.UNCOMMON
    );

    public static final Item TITANIUM_CHESTPLATE = createFireproofArmor(
            "titanium_chestplate",
            EArmorMaterials.TITANIUM,
            EArmorMaterials.TITANIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.UNCOMMON
    );

    public static final Item TITANIUM_LEGGINGS = createFireproofArmor(
            "titanium_leggings",
            EArmorMaterials.TITANIUM,
            EArmorMaterials.TITANIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.UNCOMMON
    );

    public static final Item TITANIUM_BOOTS = createFireproofArmor(
            "titanium_boots",
            EArmorMaterials.TITANIUM,
            EArmorMaterials.TITANIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.UNCOMMON
    );
    //endregion

    //region Hallowed Armor
    public static final Item HALLOWED_HELMET = createFireproofArmor(
            "hallowed_helmet",
            EArmorMaterials.HALLOWED,
            EArmorMaterials.HALLOWED_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.UNCOMMON
    );

    public static final Item HALLOWED_CHESTPLATE = createFireproofArmor(
            "hallowed_chestplate",
            EArmorMaterials.HALLOWED,
            EArmorMaterials.HALLOWED_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.UNCOMMON
    );

    public static final Item HALLOWED_LEGGINGS = createFireproofArmor(
            "hallowed_leggings",
            EArmorMaterials.HALLOWED,
            EArmorMaterials.HALLOWED_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.UNCOMMON
    );

    public static final Item HALLOWED_BOOTS = createFireproofArmor(
            "hallowed_boots",
            EArmorMaterials.HALLOWED,
            EArmorMaterials.HALLOWED_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.UNCOMMON
    );
    //endregion

    //region Verdant Armor
    public static final Item VERDANT_HELMET = createFireproofArmor(
            "verdant_helmet",
            EArmorMaterials.VERDANT,
            EArmorMaterials.VERDANT_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.RARE
    );

    public static final Item VERDANT_CHESTPLATE = createFireproofArmor(
            "verdant_chestplate",
            EArmorMaterials.VERDANT,
            EArmorMaterials.VERDANT_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.RARE
    );

    public static final Item VERDANT_LEGGINGS = createFireproofArmor(
            "verdant_leggings",
            EArmorMaterials.VERDANT,
            EArmorMaterials.VERDANT_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.RARE
    );

    public static final Item VERDANT_BOOTS = createFireproofArmor(
            "verdant_boots",
            EArmorMaterials.VERDANT,
            EArmorMaterials.VERDANT_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.RARE
    );
    //endregion

    //region Detonium Armor
    public static final Item DETONIUM_HELMET = createArmorWithAttributes(
            "detonium_helmet",
            EArmorMaterials.DETONIUM,
            EArmorMaterials.DETONIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, .25d)
    );

    public static final Item DETONIUM_CHESTPLATE = createArmorWithAttributes(
            "detonium_chestplate",
            EArmorMaterials.DETONIUM,
            EArmorMaterials.DETONIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, .75d)
    );

    public static final Item DETONIUM_LEGGINGS = createArmorWithAttributes(
            "detonium_leggings",
            EArmorMaterials.DETONIUM,
            EArmorMaterials.DETONIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, .25d)
    );

    public static final Item DETONIUM_BOOTS = createArmorWithAttributes(
            "detonium_boots",
            EArmorMaterials.DETONIUM,
            EArmorMaterials.DETONIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, .25d)
    );
    //endregion

    //region Refined Aetherium Armor
    public static final Item REFINED_AETHERIUM_HELMET = createArmorWithAttributes(
            "refined_aetherium_helmet",
            EArmorMaterials.REFINED_AETHERIUM,
            EArmorMaterials.REFINED_AETHERIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(AttributesMod.NATURAL_REGENERATION, 0.5d)
    );

    public static final Item REFINED_AETHERIUM_CHESTPLATE = createArmorWithAttributes(
            "refined_aetherium_chestplate",
            EArmorMaterials.REFINED_AETHERIUM,
            EArmorMaterials.REFINED_AETHERIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(AttributesMod.LIFE_STEAL, 0.25d)
    );

    public static final Item REFINED_AETHERIUM_LEGGINGS = createArmorWithAttributes(
            "refined_aetherium_leggings",
            EArmorMaterials.REFINED_AETHERIUM,
            EArmorMaterials.REFINED_AETHERIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_MOVEMENT_EFFICIENCY, 1d)
    );

    public static final Item REFINED_AETHERIUM_BOOTS = createArmorWithAttributes(
            "refined_aetherium_boots",
            EArmorMaterials.REFINED_AETHERIUM,
            EArmorMaterials.REFINED_AETHERIUM_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(AttributesMod.SPRINTING_SPEED, 0.05d)
    );
    //endregion

    //region Flawless Aetherium Armor
    public static final Item FLAWLESS_AETHERIUM_HELMET = createIndestructibleArmorWithAttributes(
            "flawless_aetherium_helmet",
            EArmorMaterials.FLAWLESS_AETHERIUM,
            ArmorItem.Type.HELMET,
            Rarity.EPIC,
            new Tuples.TwoItem<>(AttributesMod.NATURAL_REGENERATION, 0.5d),
            new Tuples.TwoItem<>(EEntityAttributes.GENERIC_MAGIC_ATTACK_DAMAGE, 2d),
            new Tuples.TwoItem<>(EEntityAttributes.GENERIC_MANA_REGENERATION, 4d),
            new Tuples.TwoItem<>(EEntityAttributes.GENERIC_MAX_MANA, 90d)
    );

    public static final Item FLAWLESS_AETHERIUM_CHESTPLATE = createIndestructibleArmorWithAttributes(
            "flawless_aetherium_chestplate",
            EArmorMaterials.FLAWLESS_AETHERIUM,
            ArmorItem.Type.CHESTPLATE,
            Rarity.EPIC,
            new Tuples.TwoItem<>(AttributesMod.LIFE_STEAL, 0.25d),
            new Tuples.TwoItem<>(AttributesMod.FORTUNE, 2d),
            new Tuples.TwoItem<>(AttributesMod.MINING_SPEED, 2d)
    );

    public static final Item FLAWLESS_AETHERIUM_LEGGINGS = createIndestructibleArmorWithAttributes(
            "flawless_aetherium_leggings",
            EArmorMaterials.FLAWLESS_AETHERIUM,
            ArmorItem.Type.LEGGINGS,
            Rarity.EPIC,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_MOVEMENT_EFFICIENCY, 1d),
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_STEP_HEIGHT, 2d),
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_WATER_MOVEMENT_EFFICIENCY, 2d)
    );

    public static final Item FLAWLESS_AETHERIUM_BOOTS = createIndestructibleArmorWithAttributes(
            "flawless_aetherium_boots",
            EArmorMaterials.FLAWLESS_AETHERIUM,
            ArmorItem.Type.BOOTS,
            Rarity.EPIC,
            new Tuples.TwoItem<>(AttributesMod.SPRINTING_SPEED, 0.05d),
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_SAFE_FALL_DISTANCE, 128d)
    );
    //endregion

    //region Cinninnasite Diamond Armor
    public static final Item CINCINNASITE_DIAMOND_HELMET = createArmor(
            "cincinnasite_diamond_helmet",
            EArmorMaterials.CINCINNASITE_DIAMOND,
            EArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.UNCOMMON
    );

    public static final Item CINCINNASITE_DIAMOND_CHESTPLATE = createArmor(
            "cincinnasite_diamond_chestplate",
            EArmorMaterials.CINCINNASITE_DIAMOND,
            EArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.UNCOMMON
    );

    public static final Item CINCINNASITE_DIAMOND_LEGGINGS = createArmor(
            "cincinnasite_diamond_leggings",
            EArmorMaterials.CINCINNASITE_DIAMOND,
            EArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.UNCOMMON
    );

    public static final Item CINCINNASITE_DIAMOND_BOOTS = createArmor(
            "cincinnasite_diamond_boots",
            EArmorMaterials.CINCINNASITE_DIAMOND,
            EArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.UNCOMMON
    );
    //endregion

    //region Terrasteel Armor
    /* Removed Terrasteel armor
    public static final Item TERRASTEEL_HELMET = createArmorWithAttributes(
            "terrasteel_helmet",
            EArmorMaterials.TERRASTEEL,
            EArmorMaterials.TERRASTEEL_DURABILITY_MULTIPLIER,
            ArmorItem.Type.HELMET,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_MAX_HEALTH, 3d)
    );

    public static final Item TERRASTEEL_CHESTPLATE = createArmorWithAttributes(
            "terrasteel_chestplate",
            EArmorMaterials.TERRASTEEL,
            EArmorMaterials.TERRASTEEL_DURABILITY_MULTIPLIER,
            ArmorItem.Type.CHESTPLATE,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_MAX_HEALTH, 5d)
    );

    public static final Item TERRASTEEL_LEGGINGS = createArmorWithAttributes(
            "terrasteel_leggings",
            EArmorMaterials.TERRASTEEL,
            EArmorMaterials.TERRASTEEL_DURABILITY_MULTIPLIER,
            ArmorItem.Type.LEGGINGS,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_MAX_HEALTH, 4d)
    );

    public static final Item TERRASTEEL_BOOTS = createArmorWithAttributes(
            "terrasteel_boots",
            EArmorMaterials.TERRASTEEL,
            EArmorMaterials.TERRASTEEL_DURABILITY_MULTIPLIER,
            ArmorItem.Type.BOOTS,
            Rarity.RARE, true,
            new Tuples.TwoItem<>(EntityAttributes.GENERIC_MAX_HEALTH, 2d)
    );
     */
    //endregion

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering armors");
    }

    public static Item createArmor(String name, RegistryEntry<ArmorMaterial> material, int durabilityMultiplier, ArmorItem.Type type, Rarity rarity) {
        return registerItem(name, new ArmorItem(material, type, new Item.Settings().rarity(rarity).maxDamage(type.getMaxDamage(durabilityMultiplier))));
    }

    public static Item createFireproofArmor(String name, RegistryEntry<ArmorMaterial> material, int durabilityMultiplier, ArmorItem.Type type, Rarity rarity) {
        return registerItem(name, new ArmorItem(material, type, new Item.Settings().fireproof().rarity(rarity).maxDamage(type.getMaxDamage(durabilityMultiplier))));
    }

    @SafeVarargs
    private static Item createArmorWithAttributes(String name, RegistryEntry<ArmorMaterial> material, int durabilityMultiplier, ArmorItem.Type type, Rarity rarity, boolean isFireproof, Tuples.TwoItem<RegistryEntry<EntityAttribute>, Double>... attributes) {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        builder.add(
                EntityAttributes.GENERIC_ARMOR,
                new EntityAttributeModifier(
                        Identifier.ofVanilla("armor." + type.getName()),
                        material.value().defense().get(type),
                        EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot())
        );
        builder.add(
                EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                new EntityAttributeModifier(
                        Identifier.ofVanilla("armor." + type.getName()),
                        material.value().toughness(),
                        EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot())
        );

        for (Tuples.TwoItem<RegistryEntry<EntityAttribute>, Double> attribute : attributes) {
            builder.add(
                    attribute.item1(),
                    new EntityAttributeModifier(
                            Identifier.ofVanilla("armor." + type.getName()),
                            attribute.item2(),
                            EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot())
            );
        }

        return registerItem(
                name,
                new ArmorItem(
                        material,
                        type,
                        isFireproof ?
                                new Item.Settings()
                                        .fireproof()
                                        .rarity(rarity)
                                        .maxDamage(type.getMaxDamage(durabilityMultiplier))
                                        .attributeModifiers(builder.build())
                                :
                                new Item.Settings()
                                        .rarity(rarity)
                                        .maxDamage(type.getMaxDamage(durabilityMultiplier))
                                        .attributeModifiers(builder.build())
                )
        );
    }

    @SafeVarargs
    private static Item createIndestructibleArmorWithAttributes(String name, RegistryEntry<ArmorMaterial> material, ArmorItem.Type type, Rarity rarity, Tuples.TwoItem<RegistryEntry<EntityAttribute>, Double>... attributes) {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();
        builder.add(
                EntityAttributes.GENERIC_ARMOR,
                new EntityAttributeModifier(
                        Identifier.ofVanilla("armor." + type.getName()),
                        material.value().defense().get(type),
                        EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot())
        );
        builder.add(
                EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                new EntityAttributeModifier(
                        Identifier.ofVanilla("armor." + type.getName()),
                        material.value().toughness(),
                        EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot())
        );

        for (Tuples.TwoItem<RegistryEntry<EntityAttribute>, Double> attribute : attributes) {
            builder.add(
                    attribute.item1(),
                    new EntityAttributeModifier(
                            Identifier.ofVanilla("armor." + type.getName()),
                            attribute.item2(),
                            EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot())
            );
        }

        return registerItem(
                name,
                new ArmorItem(
                        material,
                        type,
                        new Item.Settings()
                                .fireproof()
                                .rarity(rarity)
                                .maxDamage(Integer.MAX_VALUE)
                                .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                                .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
                                .attributeModifiers(builder.build())
                )
        );
    }

    /**
     * @deprecated Only allows for max health attribute and requires manually specifying the armor value. For use of multiple attributes of any type, use {@link #createArmorWithAttributes(String, RegistryEntry, int, ArmorItem.Type, Rarity, boolean, Tuples.TwoItem[])}
     * @return
     */
    @Deprecated
    private static Item createArmorWithAttributes(String name, RegistryEntry<ArmorMaterial> material, int durabilityMultiplier, ArmorItem.Type type, Rarity rarity, int maxHealthBonus, int armorBonus, int armorToughnessBonus) {
        return registerItem(name, new ArmorItem(material, type, new Item.Settings()
                .fireproof()
                .rarity(rarity)
                .maxDamage(type.getMaxDamage(durabilityMultiplier))
                .attributeModifiers(AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, new EntityAttributeModifier(Identifier.ofVanilla("armor." + type.getName()), maxHealthBonus, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot()))
                        .add(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.ofVanilla("armor." + type.getName()), armorBonus, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot()))
                        .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(Identifier.ofVanilla("armor." + type.getName()), armorToughnessBonus, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.forEquipmentSlot(type.getEquipmentSlot()))
                        .build()
                )));
    }
}