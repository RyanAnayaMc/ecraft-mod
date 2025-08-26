package dev.night.ecraft.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;

public class ChickenBlock extends Block {
    public static final MapCodec<ChickenBlock> CODEC = createCodec(ChickenBlock::new);
    public static final int MAX_BITES = 3;
    public static final IntProperty CHICKEN_BLOCK_BITES = EBlockProperties.CHICKEN_BLOCK_BITES;
    public static final int DEFAULT_COMPARATOR_OUTPUT = getComparatorOutput(0);
    public final boolean IS_RAW;
    protected static final VoxelShape[] BITES_TO_SHAPE =
            new VoxelShape[] {
                    Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
                    Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
                    Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
                    Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0)};

    public ChickenBlock(Settings settings) {
        this(settings, false);
    }

    public ChickenBlock(Settings settings, boolean isRaw) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(CHICKEN_BLOCK_BITES, 0));
        this.IS_RAW = isRaw;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BITES_TO_SHAPE[(Integer)state.get(CHICKEN_BLOCK_BITES)];
    }

    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            if (tryEat(world, pos, state, player, IS_RAW).isAccepted()) {
                return ActionResult.SUCCESS;
            }

            if (player.getStackInHand(Hand.MAIN_HAND).isEmpty()) {
                return ActionResult.CONSUME;
            }
        }

        return tryEat(world, pos, state, player, IS_RAW);
    }

    protected static ActionResult tryEat(WorldAccess world, BlockPos pos, BlockState state, PlayerEntity player, boolean isRaw) {
        if (!player.canConsume(false)) {
            return ActionResult.PASS;
        } else {
            if (isRaw) {
                player.getHungerManager().add(5, 3f);
                if (world.getRandom().nextInt(10) < 3)
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 600, 1));
            } else {
                player.getHungerManager().add(15, 18);
            }

            int i = (Integer)state.get(CHICKEN_BLOCK_BITES);
            world.emitGameEvent(player, GameEvent.EAT, pos);
            if (i < MAX_BITES) {
                world.setBlockState(pos, (BlockState)state.with(CHICKEN_BLOCK_BITES, i + 1), 3);
            } else {
                world.removeBlock(pos, false);
                world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return ActionResult.SUCCESS;
        }
    }

    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == Direction.DOWN && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isSolid();
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHICKEN_BLOCK_BITES);
    }

    protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return getComparatorOutput(state.get(CHICKEN_BLOCK_BITES));
    }

    public static int getComparatorOutput(int bites) {
        return (MAX_BITES + 1 - bites) * 3;
    }

    protected boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }
}
