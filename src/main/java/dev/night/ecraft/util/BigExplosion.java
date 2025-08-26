package dev.night.ecraft.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.Predicate;

public class BigExplosion {
    private final ServerWorld serverWorld;
    private final Entity origin;
    private final Vec3i location;
    private final int radius;
    private final int radiusSquared;
    Predicate<BlockState> blocksToIgnore;

    public BigExplosion(ServerWorld world, Entity origin, Vec3i location, int radius) {
        this(world, origin, location, radius, (blockState -> false));
    }

    public BigExplosion(ServerWorld world, Entity origin, Vec3i location, int radius, Predicate<BlockState> blocksToIgnore) {
        this.serverWorld = world;
        this.origin = origin;
        this.location = location;
        this.radius = radius;
        this.radiusSquared = radius * radius;
        this.blocksToIgnore = blocksToIgnore;
    }

    public void explode() {
        // Used for fast distance formula
        int radiusSquared = radius * radius;

        // Create regular explosion
        serverWorld.createExplosion(origin, location.getX(), location.getY(), location.getZ(), (float) radius / 2, World.ExplosionSourceType.TRIGGER);

        // Destroy extra blocks
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                for (int z = -radius; z < radius; z++) {

                    BlockPos pos = new BlockPos(location.getX() + x, location.getY() + y, location.getZ() + z);
                    BlockState blockState = serverWorld.getBlockState(pos);

                    // Ensure block is close enough
                    if (radiusSquared < x * x + y * y + z * z)
                        continue;

                    // skip if block is air or too blast resistant
                    if (blockState.isAir() || blockState.getBlock().getBlastResistance() > 10000)
                        continue;

                    if (blocksToIgnore.test(blockState))
                        continue;

                    serverWorld.setBlockState(pos, Blocks.AIR.getDefaultState());
                    // serverWorld.breakBlock(pos, false);
                }
            }
        }
    }
}
