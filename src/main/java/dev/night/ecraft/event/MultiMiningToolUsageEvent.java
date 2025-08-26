package dev.night.ecraft.event;

import dev.night.ecraft.item.tools.weapons.MultiMiningTool;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class MultiMiningToolUsageEvent implements PlayerBlockBreakEvents.Before {
    private static final Set<BlockPos> MINED_BLOCKS = new HashSet<>();

    @Override
    public boolean beforeBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        if (player instanceof ServerPlayerEntity serverPlayer && serverPlayer.getMainHandStack().getItem() instanceof MultiMiningTool miningTool && miningTool.canMultiMine(serverPlayer.getMainHandStack())) {
            ItemStack stack = serverPlayer.getMainHandStack();
            if (MINED_BLOCKS.contains(pos))
                return true;

            for (BlockPos blockPos : MultiMiningTool.getBlocksToBeDestroyed(miningTool.getMiningRadius(serverPlayer.getMainHandStack()), miningTool.getMiningDepth(serverPlayer.getMainHandStack()), pos, serverPlayer)) {
                if (pos.equals(blockPos) || !miningTool.canMultiMine(stack) || !miningTool.isCorrectForDrops(serverPlayer.getMainHandStack(), world.getBlockState(blockPos)))
                    continue;

                MINED_BLOCKS.add(blockPos);
                serverPlayer.interactionManager.tryBreakBlock(blockPos);
                MINED_BLOCKS.remove(blockPos);
            }
        }

        return true;
    }
}
