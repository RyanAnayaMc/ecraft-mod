package dev.night.ecraft.loot;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.food.ModFood;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.Structures;

import java.util.List;

public class LootTableInjection {
    private static final List<RegistryKey<LootTable>> SODA_LOOT_TABLES = List.of(
            LootTables.ABANDONED_MINESHAFT_CHEST,
            LootTables.ANCIENT_CITY_CHEST,
            LootTables.ANCIENT_CITY_ICE_BOX_CHEST,
            LootTables.BASTION_BRIDGE_CHEST,
            LootTables.BASTION_OTHER_CHEST,
            LootTables.BASTION_TREASURE_CHEST,
            LootTables.DESERT_PYRAMID_CHEST,
            LootTables.BURIED_TREASURE_CHEST,
            LootTables.HERO_OF_THE_VILLAGE_FARMER_GIFT_GAMEPLAY,
            LootTables.IGLOO_CHEST_CHEST,
            LootTables.JUNGLE_TEMPLE_CHEST,
            LootTables.NETHER_BRIDGE_CHEST,
            LootTables.SIMPLE_DUNGEON_CHEST,
            LootTables.RUINED_PORTAL_CHEST,
            LootTables.SHIPWRECK_SUPPLY_CHEST,
            LootTables.STRONGHOLD_CORRIDOR_CHEST,
            LootTables.STRONGHOLD_CROSSING_CHEST,
            LootTables.UNDERWATER_RUIN_BIG_CHEST,
            LootTables.UNDERWATER_RUIN_SMALL_CHEST,
            LootTables.VILLAGE_BUTCHER_CHEST,
            LootTables.VILLAGE_PLAINS_CHEST,
            LootTables.VILLAGE_DESERT_HOUSE_CHEST,
            LootTables.VILLAGE_SAVANNA_HOUSE_CHEST,
            LootTables.VILLAGE_SNOWY_HOUSE_CHEST,
            LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
            LootTables.WOODLAND_MANSION_CHEST
    );

    public static void modifyLootTables() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " modifying loot tables");

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
                    if (SODA_LOOT_TABLES.contains(key)) {
                        LootPool.Builder poolBuilder = new LootPool.Builder()
                                .with(ItemEntry.builder(ModFood.DR_PEPPER).weight(3))
                                .with(ItemEntry.builder(ModFood.PIBB_XTRA).weight(15));

                        tableBuilder.pool(poolBuilder);
                    }
                }
        );
    }
}
