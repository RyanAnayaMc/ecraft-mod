package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.recipe.Ingredient;

import java.util.Map;

import java.util.List;
import java.util.function.Supplier;

public class EArmorMaterials {
    // vanilla - leather 5, chain/iron 15, diamond 33, gold 7, netherite 37
    public static final int COPPER_DURABILITY_MULTIPLIER = 10;
    public static final int TUNGSTEN_DURABILITY_MULTIPLIER = 25;
    public static final int DEMONITE_DURABILITY_MULTIPLIER = 30;
    public static final int HELLSTONE_DURABILITY_MULTIPLIER = 40;
    public static final int COBALT_DURABILITY_MULTIPLIER = 40;
    public static final int ORICHALCUM_DURABILITY_MULTIPLIER = 42;
    public static final int TITANIUM_DURABILITY_MULTIPLIER = 45;
    public static final int HALLOWED_DURABILITY_MULTIPLIER = 50;
    public static final int VERDANT_DURABILITY_MULTIPLIER = 57;
    public static final int DETONIUM_DURABILITY_MULTIPLIER = 60;
    public static final int REFINED_AETHERIUM_DURABILITY_MULTIPLIER = 65;

    public static final int CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER = 35;
    public static final int TERRASTEEL_DURABILITY_MULTIPLIER = 50;

    public static final RegistryEntry<ArmorMaterial> COPPER = registerMaterial(
            "copper",
            Map.of(
                    ArmorItem.Type.HELMET, 2,
                    ArmorItem.Type.CHESTPLATE, 4,
                    ArmorItem.Type.LEGGINGS, 4,
                    ArmorItem.Type.BOOTS, 1
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_GENERIC,
            () -> Ingredient.ofItems(Items.COPPER_INGOT),
            0,
            .25f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> TUNGSTEN = registerMaterial(
            "tungsten",
            Map.of(
                    ArmorItem.Type.HELMET, 2,
                    ArmorItem.Type.CHESTPLATE,5,
                    ArmorItem.Type.LEGGINGS, 4,
                    ArmorItem.Type.BOOTS, 2
            ),
            10,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
            () -> Ingredient.ofItems(EItems.TUNGSTEN_INGOT),
            .5f,
            0,
            false
    );

    public static final RegistryEntry<ArmorMaterial> DEMONITE = registerMaterial(
            "demonite",
            Map.of(
                    ArmorItem.Type.HELMET, 3,
                    ArmorItem.Type.CHESTPLATE, 6,
                    ArmorItem.Type.LEGGINGS, 5,
                    ArmorItem.Type.BOOTS, 2
            ),
            18,
            SoundEvents.ITEM_ARMOR_EQUIP_GOLD,
            () -> Ingredient.ofItems(EItems.DEMONITE_INGOT),
            2.5f,
            .05f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> HELLSTONE = registerMaterial(
            "hellstone",
            Map.of(
                    ArmorItem.Type.HELMET, 3,
                    ArmorItem.Type.CHESTPLATE, 9,
                    ArmorItem.Type.LEGGINGS, 6,
                    ArmorItem.Type.BOOTS, 2
            ),
            12,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.HELLSTONE_INGOT),
            2.75f,
            .1f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> COBALT = registerMaterial(
            "cobalt",
            Map.of(
                    ArmorItem.Type.HELMET, 4,
                    ArmorItem.Type.CHESTPLATE, 9,
                    ArmorItem.Type.LEGGINGS, 7,
                    ArmorItem.Type.BOOTS, 4
            ),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.COBALT_INGOT),
            3f,
            .1f,
            false

    );

    public static final RegistryEntry<ArmorMaterial> ORICHALCUM = registerMaterial(
            "orichalcum",
            Map.of(
                    ArmorItem.Type.HELMET, 5,
                    ArmorItem.Type.CHESTPLATE, 9,
                    ArmorItem.Type.LEGGINGS, 7,
                    ArmorItem.Type.BOOTS, 5
            ),
            18,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.ORICHALCUM_INGOT),
            3f,
            .15f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> TITANIUM = registerMaterial(
            "titanium",
            Map.of(
                    ArmorItem.Type.HELMET, 5,
                    ArmorItem.Type.CHESTPLATE, 10,
                    ArmorItem.Type.LEGGINGS, 8,
                    ArmorItem.Type.BOOTS, 5
            ),
            21,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.TITANIUM_INGOT),
            3.5f,
            .2f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> HALLOWED = registerMaterial(
            "hallowed",
            Map.of(
                    ArmorItem.Type.HELMET, 6,
                    ArmorItem.Type.CHESTPLATE, 11,
                    ArmorItem.Type.LEGGINGS, 9,
                    ArmorItem.Type.BOOTS, 5
            ),
            25,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.HALLOWED_INGOT),
            3.5f,
            .2f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> VERDANT = registerMaterial(
            "verdant",
            Map.of(
                    ArmorItem.Type.HELMET, 6,
                    ArmorItem.Type.CHESTPLATE, 11,
                    ArmorItem.Type.LEGGINGS, 10,
                    ArmorItem.Type.BOOTS, 6
            ),
            30,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.VERDANT_INGOT),
            3.5f,
            .2f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> DETONIUM = registerMaterial(
            "detonium",
            Map.of(
                    ArmorItem.Type.HELMET, 7,
                    ArmorItem.Type.CHESTPLATE, 12,
                    ArmorItem.Type.LEGGINGS, 10,
                    ArmorItem.Type.BOOTS, 6
            ),
            28,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.DETONIUM_INGOT),
            3.5f,
            .25f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> REFINED_AETHERIUM = registerMaterial(
            "refined_aetherium",
            Map.of(
                    ArmorItem.Type.HELMET, 7,
                    ArmorItem.Type.CHESTPLATE, 12,
                    ArmorItem.Type.LEGGINGS, 10,
                    ArmorItem.Type.BOOTS, 7
            ),
            28,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.REFINED_AETHERIUM_CRYSTAL),
            3.5f,
            .25f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> FLAWLESS_AETHERIUM = registerMaterial(
            "flawless_aetherium",
            Map.of(
                    ArmorItem.Type.HELMET, 8,
                    ArmorItem.Type.CHESTPLATE, 13,
                    ArmorItem.Type.LEGGINGS, 10,
                    ArmorItem.Type.BOOTS, 8
            ),
            40,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.FLAWLESS_AETHERIUM_CRYSTAL),
            4f,
            .325f,
            false
    );

    public static final RegistryEntry<ArmorMaterial> CINCINNASITE_DIAMOND = registerMaterial(
        "cincinnasite_diamond",
            Map.of(
                    ArmorItem.Type.HELMET, 3,
                    ArmorItem.Type.CHESTPLATE, 8,
                    ArmorItem.Type.LEGGINGS, 6,
                    ArmorItem.Type.BOOTS, 3
            ),
            5,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(EItems.CINCINNASITE_DIAMOND_INGOT),
            1.5F,
            0.05F,
            false
    );

    public static final RegistryEntry<ArmorMaterial> TERRASTEEL = registerMaterial(
            "terrasteel",
            Map.of(
                    ArmorItem.Type.HELMET, 4,
                    ArmorItem.Type.CHESTPLATE, 11,
                    ArmorItem.Type.LEGGINGS, 7,
                    ArmorItem.Type.BOOTS, 4
            ),
            8,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
            () -> Ingredient.ofItems(EItems.TERRASTEEL_INGOT),
            4,
            0,
            false
    );

    public static RegistryEntry<ArmorMaterial> registerMaterial(String id, Map<ArmorItem.Type, Integer> defensePoints, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
        // Get the supported layers for the armor material
        List<ArmorMaterial.Layer> layers = List.of(
                // The ID of the texture layer, the suffix, and whether the layer is dyeable.
                // We can just pass the armor material ID as the texture layer ID.
                // We have no need for a suffix, so we'll pass an empty string.
                // We'll pass the dyeable boolean we received as the dyeable parameter.
                new ArmorMaterial.Layer(Identifier.of(Ecraft.MOD_ID, id), "", dyeable)
        );

        ArmorMaterial material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance);
        // Register the material within the ArmorMaterials registry.
        material = Registry.register(Registries.ARMOR_MATERIAL, Identifier.of(Ecraft.MOD_ID, id), material);

        // The majority of the time, you'll want the RegistryEntry of the material - especially for the ArmorItem constructor.
        return RegistryEntry.of(material);
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering armor materials");
    }
}
