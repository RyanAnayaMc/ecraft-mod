package dev.night.ecraft.block;

import dev.night.ecraft.util.BigExplosion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public class ExplosiveChargeBlock extends Block {
    private final int blastRadius;
    private final Predicate<BlockState> blocksToIgnore;
    private final Text extraTooltip;

    public ExplosiveChargeBlock(Settings settings, int blastRadius, @Nullable Text extraTooltip, @Nullable Predicate<BlockState> blocksToIgnore) {
        super(settings);
        this.blastRadius = blastRadius;
        this.extraTooltip = extraTooltip;
        this.blocksToIgnore = blocksToIgnore;
    }

    public ExplosiveChargeBlock(Settings settings, int blastRadius) {
        this(settings, blastRadius, null, null);
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("block.ecraft.explosive_charge_block.requires_detonator").withColor(0xE4080A));
        tooltip.add(Text.translatable("block.ecraft.explosive_charge_block.radius", blastRadius));
        if (extraTooltip != null)
            tooltip.add(extraTooltip);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world instanceof ServerWorld serverWorld && serverWorld.isReceivingRedstonePower(pos))
            getExplosion(serverWorld, pos).explode();
    }

    public BigExplosion getExplosion(ServerWorld world, Vec3i location) {
        if (blocksToIgnore != null)
            return new BigExplosion(world, null, location, blastRadius, blocksToIgnore);
        else
            return new BigExplosion(world, null, location, blastRadius);
    }

    public int getBlastRadius() {
        return blastRadius;
    }
}
