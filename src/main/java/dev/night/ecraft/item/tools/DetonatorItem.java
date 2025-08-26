package dev.night.ecraft.item.tools;

import dev.night.ecraft.block.ExplosiveChargeBlock;
import dev.night.ecraft.component.DetonatorComponent;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.util.BigExplosion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DetonatorItem extends Item {
    public DetonatorItem(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // Only run if this is player on server
        if (world.isClient() || !entity.isPlayer())
            return;

        // If block location no longer has a charge, remove it
        List<BlockPos> posList = new ArrayList<>(stack.getOrDefault(EComponents.DETONATOR_COMPONENT, getDefaultComponent()).chargeLocations());
        boolean removed = posList.removeIf(pos -> !(world.getBlockState(pos).getBlock() instanceof ExplosiveChargeBlock));

        if (removed)
            stack.set(EComponents.DETONATOR_COMPONENT, new DetonatorComponent(posList));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // Only execute on server side
        if (world.isClient())
            return TypedActionResult.fail(stack);

        // Get component data
        DetonatorComponent component = stack.getOrDefault(EComponents.DETONATOR_COMPONENT, getDefaultComponent());
        List<BlockPos> chargeLocations = component.chargeLocations();

        // Fail if there are no charges
        if (chargeLocations.isEmpty())
            return TypedActionResult.fail(stack);

        // Get locations to explode
        List<BigExplosion> explosions = new ArrayList<>(chargeLocations.size());
        boolean isSafe = true;

        for (BlockPos pos : chargeLocations) {
            BlockState state = world.getBlockState(pos);

            if (state.getBlock() instanceof ExplosiveChargeBlock charge) {
                explosions.add(charge.getExplosion((ServerWorld) world, pos));

                if (pos.isWithinDistance(user.getBlockPos(), charge.getBlastRadius() + 2))
                    isSafe = false;
            }
        }

        if (!isSafe && user.getPose() != EntityPose.CROUCHING) {
            user.sendMessage(Text.translatable("item.ecraft.detonator_danger"), true);
            return TypedActionResult.fail(stack);
        }

        // Do explosions
        explosions.forEach(BigExplosion::explode);

        // Update component
        stack.set(EComponents.DETONATOR_COMPONENT, new DetonatorComponent(new ArrayList<>()));

        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // Only execute on server side
        if (context.getWorld() instanceof ServerWorld serverWorld) {
            // Get information about block
            BlockPos blockPos = context.getBlockPos();
            Block block = serverWorld.getBlockState(blockPos).getBlock();

            // Determine if block is an explosive charge
            if (block instanceof ExplosiveChargeBlock) {
                ItemStack stack = context.getStack();

                // Get list of charges marked by detonator
                DetonatorComponent component = stack.getOrDefault(EComponents.DETONATOR_COMPONENT, getDefaultComponent());
                List<BlockPos> chargeLocations = new ArrayList<>(stack.getOrDefault(EComponents.DETONATOR_COMPONENT, getDefaultComponent()).chargeLocations());

                // Remove the charge from the list if it already exists, otherwise add it
                if (!chargeLocations.remove(blockPos) && chargeLocations.size() < 3)
                    chargeLocations.add(blockPos);

                stack.set(EComponents.DETONATOR_COMPONENT, new DetonatorComponent(chargeLocations));

                if (context.getPlayer() != null)
                    context.getPlayer().getItemCooldownManager().set(this, 10);

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.PASS;
    }


    public static DetonatorComponent getDefaultComponent() {
        return new DetonatorComponent(new ArrayList<>());
    }
}
