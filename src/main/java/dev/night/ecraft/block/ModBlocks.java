package dev.night.ecraft.block;

import dev.night.ecraft.Ecraft;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block RAW_DIAMOND_BLOCK = registerBlock("raw_diamond_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .requiresTool()
                            .strength(4f)
                            .sounds(BlockSoundGroup.METAL)
            ),
            true
    );

    public static final Block CINCINNASITE_DIAMOND_BLOCK = registerBlock("cincinnasite_diamond_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .requiresTool()
                            .strength(4.5f)
                            .sounds(BlockSoundGroup.METAL)
            ),
            true
    );

    public static final Block TERRASTEEL_BLOCK = registerBlock("terrasteel_block",
            new Block(
                    AbstractBlock.Settings.create()
                            .requiresTool()
                            .strength(5f)
                            .sounds(BlockSoundGroup.NETHERITE)
            ),
            true
    );

    public static Block registerBlock(String name, Block block, boolean shouldRegister, String spoofID) {
        Identifier id = Identifier.of(spoofID, name);

        if (shouldRegister)
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static Block registerBlock(String name, Block block, boolean shouldRegister) {
        return registerBlock(name, block, shouldRegister, Ecraft.MOD_ID);
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering blocks");
    }
}
