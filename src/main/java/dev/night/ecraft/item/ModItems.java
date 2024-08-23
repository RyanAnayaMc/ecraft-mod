package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import javax.swing.text.html.parser.Entity;

public class ModItems {

    public static final Item CINCINNASITE_DIAMOND_INGOT =
            registerItem("cincinnasite_diamond_ingot", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static final Item CINCINNASITE_DIAMOND_NUGGET =
            registerItem("cincinnasite_diamond_nugget", new Item(new Item.Settings().rarity(Rarity.UNCOMMON)));

    public static final Item TERRASTEEL_INGOT =
            registerItem("terrasteel_ingot", new Item(new Item.Settings().rarity(Rarity.RARE).fireproof()));

    public static final Item TERRASTEEL_NUGGET =
            registerItem("terrasteel_nugget", new Item(new Item.Settings().rarity(Rarity.UNCOMMON).fireproof()));

    public static final Item RAW_DIAMOND =
            registerItem("raw_diamond", new Item(new Item.Settings()));

    public static final Item SHARD_OF_TRAVEL =
            registerItem("shard_of_travel", new Item(new Item.Settings()
                    .rarity(Rarity.RARE)
                    .component(DataComponentTypes.ENCHANTMENT_GLINT_OVERRIDE, true)
            ));
    
    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering items");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(ModItems.CINCINNASITE_DIAMOND_INGOT);
        });
    }

    /**
     * Registers an item to the registry
     * @param name The name for the item
     * @param item The item to add
     * @return The item added
     */
    public static Item registerItem(String name, Item item) {
        return registerItem(name, item, Ecraft.MOD_ID);
    }

    /**
     * Registers an item to the registry with a spoofed mod ID
     * @param name The name for the item
     * @param item The item to add
     * @param spoofID The mod ID to use for this item
     * @return The item added
     */
    public static Item registerItem(String name, Item item, String spoofID) {
        return Registry.register(Registries.ITEM, Identifier.of(spoofID, name), item);
    }
}
