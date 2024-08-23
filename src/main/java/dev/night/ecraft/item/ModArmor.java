package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModArmor {
    //region Cincinnasite Diamond Armor
    public static final Item CINCINNASITE_DIAMOND_HELMET =
            ModItems.registerItem("cincinnasite_diamond_helmet", new ArmorItem(
                    ModArmorMaterials.CINCINNASITE_DIAMOND,
                    ArmorItem.Type.HELMET,
                    new Item.Settings()
                            .rarity(Rarity.UNCOMMON)
                            .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(
                            ModArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER
                    ))
            ));

    public static final Item CINCINNASITE_DIAMOND_CHESTPLATE =
            ModItems.registerItem("cincinnasite_diamond_chestplate", new ArmorItem(
                    ModArmorMaterials.CINCINNASITE_DIAMOND,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings()
                            .rarity(Rarity.UNCOMMON)
                            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(
                            ModArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER
                    ))
            ));

    public static final Item CINCINNASITE_DIAMOND_LEGGINGS =
            ModItems.registerItem("cincinnasite_diamond_leggings", new ArmorItem(
                    ModArmorMaterials.CINCINNASITE_DIAMOND,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings()
                            .rarity(Rarity.UNCOMMON)
                            .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(
                            ModArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER
                    ))
            ));

    public static final Item CINCINNASITE_DIAMOND_BOOTS =
            ModItems.registerItem("cincinnasite_diamond_boots", new ArmorItem(
                    ModArmorMaterials.CINCINNASITE_DIAMOND,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings()
                            .rarity(Rarity.UNCOMMON)
                            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(
                            ModArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER
                    ))
            ));
    //endregion

    //region Terrasteel armor
    public static final Item TERRASTEEL_HELMET =
            ModItems.registerItem("terrasteel_helmet", new ArmorItem(
                    ModArmorMaterials.TERRASTEEL,
                    ArmorItem.Type.HELMET,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(
                                    ModArmorMaterials.TERRASTEEL_DURABILITY_MULTIPLIER
                            ))
                            .attributeModifiers(
                                    AttributeModifiersComponent.builder()
                                            .add(
                                                    EntityAttributes.GENERIC_MAX_HEALTH,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.HELMET.getName()),
                                                            3,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.HELMET.getEquipmentSlot())
                                            )
                                            .add(
                                                    EntityAttributes.GENERIC_ARMOR,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.HELMET.getName()),
                                                            4,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.HELMET.getEquipmentSlot())
                                            )
                                            .add(
                                                    EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.HELMET.getName()),
                                                            4,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.HELMET.getEquipmentSlot())
                                            )
                                            .build()
                            )
            ));

    public static final Item TERRASTEEL_CHESTPLATE =
            ModItems.registerItem("terrasteel_chestplate", new ArmorItem(
                    ModArmorMaterials.TERRASTEEL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(
                                    ModArmorMaterials.TERRASTEEL_DURABILITY_MULTIPLIER
                            ))
                            .attributeModifiers(
                                    AttributeModifiersComponent.builder()
                                            .add(
                                                    EntityAttributes.GENERIC_MAX_HEALTH,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.CHESTPLATE.getName()),
                                                            5,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.CHESTPLATE.getEquipmentSlot())
                                            )
                                            .add(
                                                    EntityAttributes.GENERIC_ARMOR,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.CHESTPLATE.getName()),
                                                            11,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.CHESTPLATE.getEquipmentSlot())
                                            )
                                            .add(
                                                    EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.CHESTPLATE.getName()),
                                                            4,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.CHESTPLATE.getEquipmentSlot())
                                            )
                                            .build()
                            )
            ));

    public static final Item TERRASTEEL_LEGGINGS =
            ModItems.registerItem("terrasteel_leggings", new ArmorItem(
                    ModArmorMaterials.TERRASTEEL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(
                                    ModArmorMaterials.TERRASTEEL_DURABILITY_MULTIPLIER
                            ))
                            .attributeModifiers(
                                    AttributeModifiersComponent.builder()
                                            .add(
                                                    EntityAttributes.GENERIC_MAX_HEALTH,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.LEGGINGS.getName()),
                                                            4,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.LEGGINGS.getEquipmentSlot())
                                            )
                                            .add(
                                                    EntityAttributes.GENERIC_ARMOR,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.LEGGINGS.getName()),
                                                            7,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.LEGGINGS.getEquipmentSlot())
                                            )
                                            .add(
                                                    EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.LEGGINGS.getName()),
                                                            4,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.LEGGINGS.getEquipmentSlot())
                                            )
                                            .build()
                            )
            ));

    public static final Item TERRASTEEL_BOOTS =
            ModItems.registerItem("terrasteel_boots", new ArmorItem(
                    ModArmorMaterials.TERRASTEEL,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings()
                            .fireproof()
                            .rarity(Rarity.RARE)
                            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(
                                    ModArmorMaterials.TERRASTEEL_DURABILITY_MULTIPLIER
                            ))
                            .attributeModifiers(
                                    AttributeModifiersComponent.builder()
                                            .add(
                                                    EntityAttributes.GENERIC_MAX_HEALTH,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.BOOTS.getName()),
                                                            2,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.BOOTS.getEquipmentSlot())
                                            )
                                            .add(
                                                    EntityAttributes.GENERIC_ARMOR,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.BOOTS.getName()),
                                                            4,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.BOOTS.getEquipmentSlot())
                                            )
                                            .add(
                                                    EntityAttributes.GENERIC_ARMOR_TOUGHNESS,
                                                    new EntityAttributeModifier(
                                                            Identifier.ofVanilla("armor." + ArmorItem.Type.BOOTS.getName()),
                                                            4,
                                                            EntityAttributeModifier.Operation.ADD_VALUE
                                                    ),
                                                    AttributeModifierSlot.forEquipmentSlot(ArmorItem.Type.BOOTS.getEquipmentSlot())
                                            )
                                            .build()
                            )
            ));
    //endregion

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering armors");
    }
}
