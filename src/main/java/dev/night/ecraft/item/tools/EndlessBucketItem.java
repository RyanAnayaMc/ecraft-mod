package dev.night.ecraft.item.tools;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class EndlessBucketItem extends BucketItem {
    private Fluid fluidButAgain;

    public EndlessBucketItem(Fluid fluid, Settings settings) {
        super(fluid, settings);
        fluidButAgain = fluid;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return this.getDefaultStack();
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult blockHitResult = raycast(
                world, user, this.fluidButAgain == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE
        );
        if (blockHitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else if (blockHitResult.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(itemStack);
        } else {
            BlockPos blockPos = blockHitResult.getBlockPos();
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos2 = blockPos.offset(direction);
            if (!world.canPlayerModifyAt(user, blockPos) || !user.canPlaceOn(blockPos2, direction, itemStack)) {
                return TypedActionResult.fail(itemStack);
            } else if (this.fluidButAgain == Fluids.EMPTY) {
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.getBlock() instanceof FluidDrainable fluidDrainable) {
                    ItemStack itemStack2 = fluidDrainable.tryDrainFluid(user, world, blockPos, blockState);
                    if (!itemStack2.isEmpty()) {
                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        fluidDrainable.getBucketFillSound().ifPresent(sound -> user.playSound(sound, 1.0F, 1.0F));
                        world.emitGameEvent(user, GameEvent.FLUID_PICKUP, blockPos);
                        ItemStack itemStack3 = ItemUsage.exchangeStack(itemStack, user, itemStack2);
                        if (!world.isClient) {
                            Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity)user, itemStack2);
                        }

                        return TypedActionResult.success(itemStack3, world.isClient());
                    }
                }

                return TypedActionResult.fail(itemStack);
            } else {
                BlockState blockState = world.getBlockState(blockPos);
                BlockPos blockPos3 = blockState.getBlock() instanceof FluidFillable && this.fluidButAgain == Fluids.WATER ? blockPos : blockPos2;
                if (this.placeFluid(user, world, blockPos3, blockHitResult)) {
                    this.onEmptied(user, world, itemStack, blockPos3);
                    if (user instanceof ServerPlayerEntity) {
                        Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)user, blockPos3, itemStack);
                    }

                    user.incrementStat(Stats.USED.getOrCreateStat(this));
                    return TypedActionResult.success(itemStack, world.isClient());
                } else {
                    return TypedActionResult.fail(itemStack);
                }
            }
        }
    }
}
