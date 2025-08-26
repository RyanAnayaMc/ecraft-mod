package dev.night.ecraft.loot;

import cn.leolezury.eternalstarlight.common.data.ESLootTables;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.item.EWeapons;
import dev.night.ecraft.item.food.EFood;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;

import java.util.List;

public class ELootTableInjection {
    private static final List<RegistryKey<LootTable>> MURAMASA_LOOT_TABLES = List.of(
            LootTables.ABANDONED_MINESHAFT_CHEST,
            LootTables.ANCIENT_CITY_CHEST,
            LootTables.ANCIENT_CITY_ICE_BOX_CHEST,
            LootTables.DESERT_PYRAMID_CHEST,
            LootTables.BURIED_TREASURE_CHEST,
            LootTables.IGLOO_CHEST_CHEST,
            LootTables.JUNGLE_TEMPLE_CHEST,
            LootTables.SIMPLE_DUNGEON_CHEST,
            LootTables.STRONGHOLD_CORRIDOR_CHEST,
            LootTables.STRONGHOLD_CROSSING_CHEST,
            LootTables.WOODLAND_MANSION_CHEST
    );

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

    private static final List<RegistryKey<LootTable>> CALIBURN_TEMPLATE_LOOT_TABLES = List.of(
            LootTables.BASTION_BRIDGE_CHEST,
            LootTables.BASTION_TREASURE_CHEST,
            LootTables.BASTION_OTHER_CHEST
    );

    private static final List<RegistryKey<LootTable>> DURENDAL_TEMPLATE_LOOT_TABLES = List.of(
            LootTables.SHIPWRECK_SUPPLY_CHEST,
            LootTables.UNDERWATER_RUIN_BIG_CHEST,
            LootTables.UNDERWATER_RUIN_SMALL_CHEST,
            LootTables.BURIED_TREASURE_CHEST
    );

    private static final List<RegistryKey<LootTable>> SOLAIS_TEMPLATE_LOOT_TABLES = List.of(
            LootTables.ANCIENT_CITY_CHEST,
            LootTables.ANCIENT_CITY_ICE_BOX_CHEST,
            LootTables.END_CITY_TREASURE_CHEST
    );

    private static final List<RegistryKey<LootTable>> ROD_OF_DISCORD_CHEST_LOOT_TABLES = List.of(
            LootTables.STRONGHOLD_CORRIDOR_CHEST,
            LootTables.STRONGHOLD_CROSSING_CHEST,
            LootTables.END_CITY_TREASURE_CHEST
    );

    private static final List<RegistryKey<LootTable>> PACK_A_PUNCH_TEMPALTE_LOOT_TABLES = List.of(
            ESLootTables.BOSS_LUNAR_MONSTROSITY,
            ESLootTables.BOSS_TANGLED_HATRED,
            ESLootTables.BOSS_STARLIGHT_GOLEM
    );

    private static final List<RegistryKey<LootTable>> GUNLANCE_TEMPLATE_LOOT_TABLES = List.of(
            LootTables.BURIED_TREASURE_CHEST,
            LootTables.DESERT_PYRAMID_CHEST,
            LootTables.SHIPWRECK_TREASURE_CHEST,
            LootTables.STRONGHOLD_LIBRARY_CHEST
    );

    public static void modifyLootTables() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " modifying loot tables");

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (GUNLANCE_TEMPLATE_LOOT_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EItems.GUNLANCE_SMITHING_TEMPLATE).weight(2))
                        .with(ItemEntry.builder(EItems.GUNLANCE_SHELL).weight(5));
                tableBuilder.pool(poolBuilder);
            } else if (PACK_A_PUNCH_TEMPALTE_LOOT_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EItems.PACK_A_PUNCH_SMITHING_TEMPLATE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1, 3)))
                        );
                tableBuilder.pool(poolBuilder);
            } else if (ROD_OF_DISCORD_CHEST_LOOT_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EItems.ROD_OF_DISCORD)
                                .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.05f)))
                        );
                tableBuilder.pool(poolBuilder);
            } else if (CALIBURN_TEMPLATE_LOOT_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EItems.CALIBURN_SMITHING_TEMPLATE).weight(3));
                tableBuilder.pool(poolBuilder);
            } else if (DURENDAL_TEMPLATE_LOOT_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EItems.DURENDAL_SMITHING_TEMPLATE).weight(3));
                tableBuilder.pool(poolBuilder);
            } else if (SOLAIS_TEMPLATE_LOOT_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EItems.SOLAIS_SMITHING_TEMPLATE).weight(3));
                tableBuilder.pool(poolBuilder);
            } else if (MURAMASA_LOOT_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EWeapons.MURAMASA).weight(2));
                tableBuilder.pool(poolBuilder);
            } else if (SODA_LOOT_TABLES.contains(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EFood.DR_PEPPER).weight(1))
                        .with(ItemEntry.builder(EFood.JARRITOS).weight(2))
                        .with(ItemEntry.builder(EFood.PIBB_XTRA).weight(10))
                        .with(ItemEntry.builder(EFood.SNAPPLE).weight(3))
                        .with(ItemEntry.builder(EFood.RED_BULL).weight(4))
                        .with(ItemEntry.builder(EFood.MAGIC_APPLE).weight(5));
                tableBuilder.pool(poolBuilder);
            } else if (source.isBuiltin() && EntityType.ENDERMAN.getLootTableId().equals(key)) {
                LootPool.Builder poolBuilder = new LootPool.Builder()
                        .with(ItemEntry.builder(EItems.ROD_OF_DISCORD)
                                .apply(SetCountLootFunction.builder(BinomialLootNumberProvider.create(1, 0.01f)))
                        );
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}
