package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
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

public class ModArmorMaterials {
    public static final int CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER = 50;
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
            () -> Ingredient.ofItems(ModItems.CINCINNASITE_DIAMOND_INGOT),
            1.5F,
            0.05F,
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
