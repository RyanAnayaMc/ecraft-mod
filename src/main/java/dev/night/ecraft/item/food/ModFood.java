package dev.night.ecraft.item.food;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.util.Rarity;

public class ModFood {
    public static final Item DR_PEPPER = ModItems.registerItem("dr_pepper", new SodaItem(
            new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .food(ModFoodComponents.DR_PEPPER)
    ));

    public static final Item PIBB_XTRA = ModItems.registerItem("pibb_xtra", new SodaItem(
            new Item.Settings()
                    .food(ModFoodComponents.PIBB_XTRA)
    ));

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing food");
    }
}
