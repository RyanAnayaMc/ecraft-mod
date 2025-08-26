package dev.night.ecraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class MagicLampBlock extends Block {
    public static final MapCodec<MagicLampBlock> CODEC = createCodec(MagicLampBlock::new);
    public static final BooleanProperty LIT = Properties.LIT;
    public static final double PLAYER_RANGE = 30;

    public MagicLampBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LIT, true));
    }

    @Override
    protected MapCodec<MagicLampBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.isPlayerInRange(pos.getX(), pos.getY(), pos.getZ(), PLAYER_RANGE) != state.get(LIT))
            world.setBlockState(pos, state.cycle(LIT), Block.NOTIFY_ALL);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(LIT, true);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }
}
