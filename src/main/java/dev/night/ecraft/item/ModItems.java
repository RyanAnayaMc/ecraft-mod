package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CINCINNASITE_DIAMOND_INGOT =
            registerItem("cincinnasite_diamond_ingot", new Item(new Item.Settings()));

    public static final Item CINCINNASITE_DIAMOND_NUGGET =
            registerItem("cincinnasite_diamond_nugget", new Item(new Item.Settings()));

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering items");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ModItems.CINCINNASITE_DIAMOND_INGOT);
        });
    }

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Ecraft.MOD_ID, name), item);
    }
}
