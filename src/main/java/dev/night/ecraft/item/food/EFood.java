package dev.night.ecraft.item.food;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.EItems;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

public class EFood {
    public static final Item RAW_CHICKEN_NUGGET = EItems.registerItem("raw_chicken_nugget", new Item(
            new Item.Settings().food(EFoodComponents.RAW_CHICKEN_NUGGET)));

    public static final Item COOKED_CHICKEN_NUGGET = EItems.registerItem("cooked_chicken_nugget", new Item(
            new Item.Settings().food(EFoodComponents.CHICKEN_NUGGET)));

    public static final Item DR_PEPPER = EItems.registerItem("dr_pepper", new SodaItem(
            new Item.Settings()
                    .rarity(Rarity.EPIC)
                    .food(EFoodComponents.DR_PEPPER)
    ));

    public static final Item JARRITOS = EItems.registerItem("jarritos", new SodaItem(
            new Item.Settings()
                    .rarity(Rarity.RARE)
                    .food(EFoodComponents.JARRITOS)
    ));

    public static final Item SNAPPLE = EItems.registerItem("snapple", new SodaItem(
            new Item.Settings()
                    .rarity(Rarity.UNCOMMON)
                    .food(EFoodComponents.SNAPPLE)
    ));

    public static final Item PIBB_XTRA = EItems.registerItem("pibb_xtra", new SodaItem(
            new Item.Settings()
                    .food(EFoodComponents.PIBB_XTRA)
    ));

    public static final Item RED_BULL = EItems.registerItem("red_bull", new SodaItem(
            new Item.Settings()
                    .food(EFoodComponents.RED_BULL)
    ));

    public static final Item MAGIC_APPLE = EItems.registerItem("magic_apple", new Item(
            new Item.Settings().food(EFoodComponents.MAGIC_APPLE).rarity(Rarity.UNCOMMON)));

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing food");
    }
}
