package dev.night.ecraft.tags;

import dev.night.ecraft.Ecraft;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {
    public static final TagKey<Block> NEEDS_COPPER_TOOL = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Ecraft.MOD_ID, "needs_copper_tool"));
    public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = TagKey.of(RegistryKeys.BLOCK, Identifier.of(Ecraft.MOD_ID, "incorrect_for_copper_tool"));
}
