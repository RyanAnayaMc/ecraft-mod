package dev.night.ecraft.datagen;

import dev.night.ecraft.block.EBlocks;
import dev.night.ecraft.tags.ETags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class EBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public EBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ETags.Blocks.NEEDS_AETHERIUM_TOOL)
                ;

        getOrCreateTagBuilder(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.AETHERIUM_ORES)
                .add(EBlocks.REFINED_AETHERIUM_BLOCK)
                .add(EBlocks.AETHERIUM_BRICKS);

        getOrCreateTagBuilder(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.DETONIUM_ORES)
                .add(EBlocks.RAW_DETONIUM_BLOCK)
                .add(EBlocks.DETONIUM_BLOCK);

        getOrCreateTagBuilder(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.VERDANT_ORES)
                .add(EBlocks.RAW_VERDANT_BLOCK)
                .add(EBlocks.VERDANT_BLOCK);

        getOrCreateTagBuilder(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .add(EBlocks.HALLOWED_BLOCK);

        getOrCreateTagBuilder(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.TITANIUM_ORES)
                .add(EBlocks.RAW_TITANIUM_BLOCK)
                .add(EBlocks.TITANIUM_BLOCK);

        getOrCreateTagBuilder(ETags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.ORICHALCUM_ORES)
                .add(EBlocks.RAW_ORICHALCUM_BLOCK)
                .add(EBlocks.ORICHALCUM_BLOCK);

        getOrCreateTagBuilder(ETags.Blocks.NEEDS_NETHERITE_TOOL)
                .forceAddTag(ETags.Blocks.COBALT_ORES)
                .add(EBlocks.RAW_COBALT_BLOCK)
                .add(EBlocks.COBALT_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .forceAddTag(ETags.Blocks.HELLSTONE_ORES)
                .add(EBlocks.RAW_HELLSTONE_BLOCK)
                .add(EBlocks.HELLSTONE_BRICKS);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .forceAddTag(ETags.Blocks.DEMONITE_ORES)
                .add(EBlocks.DEMONITE_BLOCK)
                .add(EBlocks.RAW_DEMONITE_BLOCK);

        getOrCreateTagBuilder(ETags.Blocks.NEEDS_COPPER_TOOL)
                .forceAddTag(BlockTags.IRON_ORES)
                .add(Blocks.IRON_ORE)
                .add(Blocks.DEEPSLATE_IRON_ORE)
                .add(Blocks.IRON_BLOCK)
                .add(Blocks.RAW_IRON_BLOCK)
                .add(EBlocks.TUNGSTEN_ORE)
                .add(EBlocks.DEEPSLATE_TUNGSTEN_ORE)
                .add(EBlocks.TUNGSTEN_BLOCK)
                .add(EBlocks.RAW_TUNGSTEN_BLOCK)
                .forceAddTag(ETags.Blocks.MAGIC_ORES)
                .add(EBlocks.RAW_MAGIC_BLOCK)
                .add(EBlocks.MAGIC_BLOCK);

        getOrCreateTagBuilder(ETags.Blocks.INCORRECT_FOR_AETHERIUM_TOOL)
                .add(Blocks.BEDROCK);

        getOrCreateTagBuilder(ETags.Blocks.INCORRECT_FOR_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(ETags.Blocks.INCORRECT_FOR_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(ETags.Blocks.INCORRECT_FOR_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(ETags.Blocks.INCORRECT_FOR_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(ETags.Blocks.INCORRECT_FOR_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(ETags.Blocks.INCORRECT_FOR_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_NETHERITE_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_GOLD_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COPPER_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_NETHERITE_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_NETHERITE_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(ETags.Blocks.INCORRECT_FOR_COPPER_TOOL)
                .forceAddTag(BlockTags.NEEDS_IRON_TOOL)
                .forceAddTag(BlockTags.NEEDS_DIAMOND_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_NETHERITE_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_STONE_TOOL)
                .forceAddTag(BlockTags.IRON_ORES)
                .forceAddTag(ETags.Blocks.NEEDS_COPPER_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_NETHERITE_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(BlockTags.INCORRECT_FOR_WOODEN_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COPPER_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_NETHERITE_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_COBALT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_ORICHALCUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_TITANIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_HALLOWED_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_VERDANT_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_DETONIUM_TOOL)
                .forceAddTag(ETags.Blocks.NEEDS_AETHERIUM_TOOL);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(EBlocks.DIRT_MAGIC_ORE);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(EBlocks.MAGIC_ORE)
                .add(EBlocks.ANDESITE_MAGIC_ORE)
                .add(EBlocks.DIORITE_MAGIC_ORE)
                .add(EBlocks.GRANITE_MAGIC_ORE)
                .add(EBlocks.TUFF_MAGIC_ORE)
                .add(EBlocks.DEEPSLATE_MAGIC_ORE)
                .add(EBlocks.RAW_MAGIC_BLOCK)
                .add(EBlocks.MAGIC_BLOCK)

                .forceAddTag(ETags.Blocks.TUNGSTEN_ORES)
                .add(EBlocks.RAW_TUNGSTEN_BLOCK)
                .add(EBlocks.TUNGSTEN_BLOCK)

                .add(EBlocks.RAW_DIAMOND_BLOCK)

                .forceAddTag(ETags.Blocks.DEMONITE_ORES)
                .add(EBlocks.RAW_DEMONITE_BLOCK)
                .add(EBlocks.DEMONITE_BLOCK)

                .forceAddTag(ETags.Blocks.HELLSTONE_ORES)
                .add(EBlocks.RAW_HELLSTONE_BLOCK)
                .add(EBlocks.HELLSTONE_BLOCK)
                .add(EBlocks.HELLSTONE_BRICKS)

                .forceAddTag(ETags.Blocks.COBALT_ORES)
                .add(EBlocks.RAW_COBALT_BLOCK)
                .add(EBlocks.COBALT_BLOCK)

                .forceAddTag(ETags.Blocks.ORICHALCUM_ORES)
                .add(EBlocks.RAW_ORICHALCUM_BLOCK)
                .add(EBlocks.ORICHALCUM_BLOCK)

                .forceAddTag(ETags.Blocks.TITANIUM_ORES)
                .add(EBlocks.RAW_TITANIUM_BLOCK)
                .add(EBlocks.TITANIUM_BLOCK)

                .add(EBlocks.HALLOWED_BLOCK)

                .forceAddTag(ETags.Blocks.VERDANT_ORES)
                .add(EBlocks.RAW_VERDANT_BLOCK)
                .add(EBlocks.VERDANT_BLOCK)

                .forceAddTag(ETags.Blocks.DETONIUM_ORES)
                .add(EBlocks.RAW_DETONIUM_BLOCK)
                .add(EBlocks.DETONIUM_BLOCK)

                .forceAddTag(ETags.Blocks.AETHERIUM_ORES)
                .add(EBlocks.REFINED_AETHERIUM_BLOCK)
                .add(EBlocks.AETHERIUM_BRICKS)

                .add(EBlocks.CINCINNASITE_DIAMOND_BLOCK)
                .add(EBlocks.TERRASTEEL_BLOCK);

        getOrCreateTagBuilder(ETags.Blocks.PICKSAW_MINEABLE)
                .forceAddTag(BlockTags.AXE_MINEABLE)
                .forceAddTag(BlockTags.SHOVEL_MINEABLE)
                .forceAddTag(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(BlockTags.SWORD_EFFICIENT)
                .forceAddTag(BlockTags.HOE_MINEABLE);

        getOrCreateTagBuilder(ETags.Blocks.AETHERIUM_ORES)
                .add(EBlocks.DEEPSLATE_AETHERIUM_ORE)
                .add(EBlocks.BLACKSTONE_AETHERIUM_ORE)
                .add(EBlocks.BASALT_AETHERIUM_ORE)
                .add(EBlocks.END_AETHERIUM_ORE);

        getOrCreateTagBuilder(ETags.Blocks.DETONIUM_ORES)
                .add(EBlocks.DETONIUM_ORE)
                .add(EBlocks.DEEPSLATE_COBALT_ORE)
                .add(EBlocks.GRIMSTONE_DETONIUM_ORE)
                .add(EBlocks.VOIDSTONE_DETONIUM_ORE)
                .add(EBlocks.ETERNAL_ICE_DETONIUM_ORE)
                .add(EBlocks.HAZE_ICE_DETONIUM_ORE);

        getOrCreateTagBuilder(ETags.Blocks.VERDANT_ORES)
                .add(EBlocks.VERDANT_ORE)
                .add(EBlocks.DEEPSLATE_COBALT_ORE)
                .add(EBlocks.GRIMSTONE_VERDANT_ORE)
                .add(EBlocks.VOIDSTONE_VERDANT_ORE)
                .add(EBlocks.ETERNAL_ICE_VERDANT_ORE)
                .add(EBlocks.HAZE_ICE_VERDANT_ORE);

        getOrCreateTagBuilder(ETags.Blocks.TITANIUM_ORES)
                .add(EBlocks.TITANIUM_ORE)
                .add(EBlocks.DEEPSLATE_TITANIUM_ORE);

        getOrCreateTagBuilder(ETags.Blocks.ORICHALCUM_ORES)
                .add(EBlocks.ORICHALCUM_ORE)
                .add(EBlocks.DEEPSLATE_ORICHALCUM_ORE);

        getOrCreateTagBuilder(ETags.Blocks.COBALT_ORES)
                .add(EBlocks.COBALT_ORE)
                .add(EBlocks.DEEPSLATE_COBALT_ORE);

        getOrCreateTagBuilder(ETags.Blocks.HELLSTONE_ORES)
                .add(EBlocks.HELLSTONE_ORE);

        getOrCreateTagBuilder(ETags.Blocks.DEMONITE_ORES)
                .add(EBlocks.DEMONITE_ORE)
                .add(EBlocks.DEEPSLATE_DEMONITE_ORE);

        getOrCreateTagBuilder(ETags.Blocks.TUNGSTEN_ORES)
                .add(EBlocks.TUNGSTEN_ORE)
                .add(EBlocks.DEEPSLATE_TUNGSTEN_ORE);

        getOrCreateTagBuilder(ETags.Blocks.MAGIC_ORES)
                .add(EBlocks.MAGIC_ORE)
                .add(EBlocks.DIRT_MAGIC_ORE)
                .add(EBlocks.ANDESITE_MAGIC_ORE)
                .add(EBlocks.DIORITE_MAGIC_ORE)
                .add(EBlocks.GRANITE_MAGIC_ORE)
                .add(EBlocks.TUFF_MAGIC_ORE)
                .add(EBlocks.DEEPSLATE_MAGIC_ORE);

        getOrCreateTagBuilder(ConventionalBlockTags.ORES)
                .forceAddTag(ETags.Blocks.MAGIC_ORES)
                .forceAddTag(ETags.Blocks.TUNGSTEN_ORES)
                .forceAddTag(ETags.Blocks.DEMONITE_ORES)
                .forceAddTag(ETags.Blocks.HELLSTONE_ORES)
                .forceAddTag(ETags.Blocks.COBALT_ORES)
                .forceAddTag(ETags.Blocks.ORICHALCUM_ORES)
                .forceAddTag(ETags.Blocks.TITANIUM_ORES)
                .forceAddTag(ETags.Blocks.VERDANT_ORES)
                .forceAddTag(ETags.Blocks.DETONIUM_ORES)
                .forceAddTag(ETags.Blocks.AETHERIUM_ORES);

        getOrCreateTagBuilder(ConventionalBlockTags.STORAGE_BLOCKS)
                .add(EBlocks.RAW_MAGIC_BLOCK)
                .add(EBlocks.MAGIC_BLOCK)

                .add(EBlocks.RAW_TUNGSTEN_BLOCK)
                .add(EBlocks.TUNGSTEN_BLOCK)

                .add(EBlocks.RAW_DEMONITE_BLOCK)
                .add(EBlocks.DEMONITE_BLOCK)

                .add(EBlocks.RAW_DIAMOND_BLOCK)

                .add(EBlocks.RAW_HELLSTONE_BLOCK)
                .add(EBlocks.HELLSTONE_BLOCK)

                .add(EBlocks.RAW_COBALT_BLOCK)
                .add(EBlocks.COBALT_BLOCK)

                .add(EBlocks.RAW_ORICHALCUM_BLOCK)
                .add(EBlocks.ORICHALCUM_BLOCK)

                .add(EBlocks.RAW_TITANIUM_BLOCK)
                .add(EBlocks.TITANIUM_BLOCK)

                .add(EBlocks.HALLOWED_BLOCK)

                .add(EBlocks.RAW_VERDANT_BLOCK)
                .add(EBlocks.VERDANT_BLOCK)

                .add(EBlocks.RAW_DETONIUM_BLOCK)
                .add(EBlocks.DETONIUM_BLOCK)

                .add(EBlocks.REFINED_AETHERIUM_BLOCK)

                .add(EBlocks.CINCINNASITE_DIAMOND_BLOCK)
                .add(EBlocks.TERRASTEEL_BLOCK);
    }
}
