package dev.night.ecraft.item.tools.weapons;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class MultiMiningTool extends MiningToolItem {
    private final int MINING_RADIUS;
    private final int MINING_DEPTH;

    public MultiMiningTool(ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings, int radius, int depth) {
        super(material, effectiveBlocks, settings);
        MINING_RADIUS = radius;
        MINING_DEPTH = depth;
    }

    public static List<BlockPos> getBlocksToBeDestroyed(int radius, int depth, BlockPos initialBlock, ServerPlayerEntity player) {
        List<BlockPos> positions = new ArrayList<>();
        HitResult hit = player.raycast(20, 0, false);

        if (hit.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) hit;

            switch (blockHit.getSide()) {
                case DOWN -> {
                    for (int y = 0; y < depth; y++)
                        for (int x = -radius; x <= radius; x++)
                            for (int z = -radius; z <= radius; z++)
                                positions.add(new BlockPos(initialBlock.getX() + x, initialBlock.getY() + y, initialBlock.getZ() + z));
                }
                case UP -> {
                    for (int y = 0; y > -depth; y--)
                        for (int x = -radius; x <= radius; x++)
                            for (int z = -radius; z <= radius; z++)
                                positions.add(new BlockPos(initialBlock.getX() + x, initialBlock.getY() + y, initialBlock.getZ() + z));
                }
                case EAST -> {
                    for (int x = 0; x > -depth; x--)
                        for (int y = -radius; y <= radius; y++)
                            for (int z = -radius; z <= radius; z++)
                                positions.add(new BlockPos(initialBlock.getX() + x, initialBlock.getY() + y, initialBlock.getZ() + z));
                }
                case WEST -> {
                    for (int x = 0; x < depth; x++)
                        for (int y = -radius; y <= radius; y++)
                            for (int z = -radius; z <= radius; z++)
                                positions.add(new BlockPos(initialBlock.getX() + x, initialBlock.getY() + y, initialBlock.getZ() + z));
                }
                case SOUTH -> {
                    for (int z = 0; z > -depth; z--)
                        for (int y = -radius; y <= radius; y++)
                            for (int x = -radius; x <= radius; x++)
                                positions.add(new BlockPos(initialBlock.getX() + x, initialBlock.getY() + y, initialBlock.getZ() + z));
                }
                case NORTH -> {
                    for (int z = 0; z < depth; z++)
                        for (int y = -radius; y <= radius; y++)
                            for (int x = -radius; x <= radius; x++)
                                positions.add(new BlockPos(initialBlock.getX() + x, initialBlock.getY() + y, initialBlock.getZ() + z));
                }
            }
        }

        return positions;
    }

    public boolean canMultiMine(ItemStack stack) {
        return true;
    }

    public int getMiningRadius(ItemStack stack) {
        return MINING_RADIUS;
    }

    public int getMiningDepth(ItemStack stack) {
        return MINING_DEPTH;
    }


}
