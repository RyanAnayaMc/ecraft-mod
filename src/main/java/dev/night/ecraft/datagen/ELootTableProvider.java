package dev.night.ecraft.datagen;

import dev.night.ecraft.block.EBlocks;
import dev.night.ecraft.item.EItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ELootTableProvider extends FabricBlockLootTableProvider {
    public ELootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(EBlocks.CHARGE);
        addDrop(EBlocks.DETONIUM_CHARGE);
        addDrop(EBlocks.VERDANT_CHARGE);
        addDrop(EBlocks.DETONIUM_NUKE);

        addDrop(EBlocks.TERRASTEEL_BLOCK);
        addDrop(EBlocks.CINCINNASITE_DIAMOND_BLOCK);
        addDrop(EBlocks.RAW_DIAMOND_BLOCK);

        addDrop(EBlocks.DEEPSLATE_AETHERIUM_ORE, oreDrops(EBlocks.BASALT_AETHERIUM_ORE, EItems.RAW_AETHERIUM_CRYSTAL));
        addDrop(EBlocks.BASALT_AETHERIUM_ORE, oreDrops(EBlocks.BASALT_AETHERIUM_ORE, EItems.RAW_AETHERIUM_CRYSTAL));
        addDrop(EBlocks.BLACKSTONE_AETHERIUM_ORE, oreDrops(EBlocks.BLACKSTONE_AETHERIUM_ORE, EItems.RAW_AETHERIUM_CRYSTAL));
        addDrop(EBlocks.END_AETHERIUM_ORE, oreDrops(EBlocks.END_AETHERIUM_ORE, EItems.RAW_AETHERIUM_CRYSTAL));
        addDrop(EBlocks.REFINED_AETHERIUM_BLOCK);
        addDrop(EBlocks.AETHERIUM_BRICKS);

        addDrop(EBlocks.RAW_DETONIUM_BLOCK);
        addDrop(EBlocks.DETONIUM_BLOCK);
        addDrop(EBlocks.DETONIUM_ORE, oreDrops(EBlocks.DETONIUM_ORE, EItems.RAW_DETONIUM));
        addDrop(EBlocks.DEEPSLATE_DETONIUM_ORE, oreDrops(EBlocks.DEEPSLATE_DETONIUM_ORE, EItems.RAW_DETONIUM));
        addDrop(EBlocks.GRIMSTONE_DETONIUM_ORE, oreDrops(EBlocks.GRIMSTONE_DETONIUM_ORE, EItems.RAW_DETONIUM));
        addDrop(EBlocks.VOIDSTONE_DETONIUM_ORE, oreDrops(EBlocks.VOIDSTONE_DETONIUM_ORE, EItems.RAW_DETONIUM));
        addDrop(EBlocks.ETERNAL_ICE_DETONIUM_ORE, oreDrops(EBlocks.ETERNAL_ICE_DETONIUM_ORE, EItems.RAW_DETONIUM));
        addDrop(EBlocks.HAZE_ICE_DETONIUM_ORE, oreDrops(EBlocks.HAZE_ICE_DETONIUM_ORE, EItems.RAW_DETONIUM));

        addDrop(EBlocks.RAW_VERDANT_BLOCK);
        addDrop(EBlocks.VERDANT_BLOCK);
        addDrop(EBlocks.VERDANT_ORE, oreDrops(EBlocks.VERDANT_ORE, EItems.RAW_VERDANT));
        addDrop(EBlocks.DEEPSLATE_VERDANT_ORE, oreDrops(EBlocks.DEEPSLATE_VERDANT_ORE, EItems.RAW_VERDANT));
        addDrop(EBlocks.GRIMSTONE_VERDANT_ORE, oreDrops(EBlocks.GRIMSTONE_VERDANT_ORE, EItems.RAW_VERDANT));
        addDrop(EBlocks.VOIDSTONE_VERDANT_ORE, oreDrops(EBlocks.VOIDSTONE_VERDANT_ORE, EItems.RAW_VERDANT));
        addDrop(EBlocks.ETERNAL_ICE_VERDANT_ORE, oreDrops(EBlocks.ETERNAL_ICE_VERDANT_ORE, EItems.RAW_VERDANT));
        addDrop(EBlocks.HAZE_ICE_VERDANT_ORE, oreDrops(EBlocks.HAZE_ICE_VERDANT_ORE, EItems.RAW_VERDANT));

        addDrop(EBlocks.HALLOWED_BLOCK);

        addDrop(EBlocks.RAW_TITANIUM_BLOCK);
        addDrop(EBlocks.TITANIUM_BLOCK);
        addDrop(EBlocks.TITANIUM_ORE, oreDrops(EBlocks.TITANIUM_ORE, EItems.RAW_TITANIUM));
        addDrop(EBlocks.DEEPSLATE_TITANIUM_ORE, oreDrops(EBlocks.DEEPSLATE_TITANIUM_ORE, EItems.RAW_TITANIUM));

        addDrop(EBlocks.RAW_ORICHALCUM_BLOCK);
        addDrop(EBlocks.ORICHALCUM_BLOCK);
        addDrop(EBlocks.ORICHALCUM_ORE, oreDrops(EBlocks.ORICHALCUM_ORE, EItems.RAW_ORICHALCUM));
        addDrop(EBlocks.DEEPSLATE_ORICHALCUM_ORE, oreDrops(EBlocks.DEEPSLATE_ORICHALCUM_ORE, EItems.RAW_ORICHALCUM));

        addDrop(EBlocks.RAW_COBALT_BLOCK);
        addDrop(EBlocks.COBALT_BLOCK);
        addDrop(EBlocks.COBALT_ORE, oreDrops(EBlocks.COBALT_ORE, EItems.RAW_COBALT));
        addDrop(EBlocks.DEEPSLATE_COBALT_ORE, oreDrops(EBlocks.DEEPSLATE_COBALT_ORE, EItems.RAW_COBALT));

        addDrop(EBlocks.RAW_HELLSTONE_BLOCK);
        addDrop(EBlocks.HELLSTONE_BLOCK);
        addDrop(EBlocks.HELLSTONE_BRICKS);
        addDrop(EBlocks.HELLSTONE_ORE, multipleOreDrops(EBlocks.HELLSTONE_ORE, EItems.RAW_HELLSTONE, 1, 2));

        addDrop(EBlocks.RAW_DEMONITE_BLOCK);
        addDrop(EBlocks.DEMONITE_BLOCK);
        addDrop(EBlocks.DEMONITE_ORE, oreDrops(EBlocks.DEMONITE_ORE, EItems.RAW_DEMONITE));
        addDrop(EBlocks.DEEPSLATE_DEMONITE_ORE, oreDrops(EBlocks.DEEPSLATE_DEMONITE_ORE, EItems.RAW_DEMONITE));

        addDrop(EBlocks.RAW_TUNGSTEN_BLOCK);
        addDrop(EBlocks.TUNGSTEN_BLOCK);
        addDrop(EBlocks.TUNGSTEN_ORE, oreDrops(EBlocks.TUNGSTEN_ORE, EItems.RAW_TUNGSTEN));
        addDrop(EBlocks.DEEPSLATE_TUNGSTEN_ORE, oreDrops(EBlocks.DEEPSLATE_TUNGSTEN_ORE, EItems.RAW_TUNGSTEN));

        addDrop(EBlocks.MAGIC_LAMP);
        addDrop(EBlocks.RAW_MAGIC_BLOCK);
        addDrop(EBlocks.MAGIC_BLOCK);
        addDrop(EBlocks.MAGIC_RAIL);
        addDrop(EBlocks.MAGIC_ORE, multipleOreDrops(EBlocks.MAGIC_ORE, EItems.MAGIC_DUST, 2, 5));
        addDrop(EBlocks.DIRT_MAGIC_ORE, multipleOreDrops(EBlocks.DIRT_MAGIC_ORE, EItems.MAGIC_DUST, 2, 5));
        addDrop(EBlocks.ANDESITE_MAGIC_ORE, multipleOreDrops(EBlocks.ANDESITE_MAGIC_ORE, EItems.MAGIC_DUST, 2, 5));
        addDrop(EBlocks.DIORITE_MAGIC_ORE, multipleOreDrops(EBlocks.DIORITE_MAGIC_ORE, EItems.MAGIC_DUST, 2, 5));
        addDrop(EBlocks.GRANITE_MAGIC_ORE, multipleOreDrops(EBlocks.GRANITE_MAGIC_ORE, EItems.MAGIC_DUST, 2, 5));
        addDrop(EBlocks.DEEPSLATE_MAGIC_ORE, multipleOreDrops(EBlocks.DEEPSLATE_MAGIC_ORE, EItems.MAGIC_DUST, 2, 5));
        addDrop(EBlocks.TUFF_MAGIC_ORE, multipleOreDrops(EBlocks.TUFF_MAGIC_ORE, EItems.MAGIC_DUST, 2, 5));
    }

    public LootTable.Builder multipleOreDrops(Block withSilkTouch, Item withoutSilkTouch, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(withSilkTouch, this.applyExplosionDecay(withSilkTouch, ((LeafEntry.Builder<?>)
                ItemEntry.builder(withoutSilkTouch).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE)))));
    }
}
