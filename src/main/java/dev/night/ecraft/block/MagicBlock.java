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
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

/**
 * A block somewhat like the Redstone Block, but it randomly toggles between emitting power and not emitting power.
 */
public class MagicBlock extends Block {
    public static final MapCodec<MagicBlock> CODEC = createCodec(MagicBlock::new);
    public static final BooleanProperty POWERED = Properties.POWERED;

    public MagicBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(POWERED, Boolean.FALSE));
    }

    @Override
    protected MapCodec<MagicBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected boolean emitsRedstonePower(BlockState state) {
        return state.get(POWERED);
    }

    @Override
    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWERED) ? 15 : 0;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(POWERED, ctx.getWorld().random.nextBoolean());
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(5) <= 1)
            world.setBlockState(pos, state.cycle(POWERED), Block.NOTIFY_ALL);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }
}
