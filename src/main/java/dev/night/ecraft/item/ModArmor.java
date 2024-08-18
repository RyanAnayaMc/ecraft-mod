package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import net.minecraft.item.*;

public class ModArmor {
    // Cincinnasite Diamond Armor
    public static final Item CINCINNASITE_DIAMOND_HELMET =
            ModItems.registerItem("cincinnasite_diamond_helmet", new ArmorItem(
                    ModArmorMaterials.CINCINNASITE_DIAMOND,
                    ArmorItem.Type.HELMET,
                    new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(
                            ModArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER
                    ))
            ));

    public static final Item CINCINNASITE_DIAMOND_CHESTPLATE =
            ModItems.registerItem("cincinnasite_diamond_chestplate", new ArmorItem(
                    ModArmorMaterials.CINCINNASITE_DIAMOND,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(
                            ModArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER
                    ))
            ));

    public static final Item CINCINNASITE_DIAMOND_LEGGINGS =
            ModItems.registerItem("cincinnasite_diamond_leggings", new ArmorItem(
                    ModArmorMaterials.CINCINNASITE_DIAMOND,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(
                            ModArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER
                    ))
            ));

    public static final Item CINCINNASITE_DIAMOND_BOOTS =
            ModItems.registerItem("cincinnasite_diamond_boots", new ArmorItem(
                    ModArmorMaterials.CINCINNASITE_DIAMOND,
                    ArmorItem.Type.BOOTS,
                    new Item.Settings().maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(
                            ModArmorMaterials.CINCINNASITE_DIAMOND_DURABILITY_MULTIPLIER
                    ))
            ));

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering armors");
    }
}
