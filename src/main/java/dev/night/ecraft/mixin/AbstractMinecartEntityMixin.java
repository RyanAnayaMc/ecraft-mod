package dev.night.ecraft.mixin;

import com.mojang.datafixers.util.Pair;
import dev.night.ecraft.block.EBlocks;
import net.minecraft.block.*;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;

import java.util.Map;

@Mixin(AbstractMinecartEntity.class)
public abstract class AbstractMinecartEntityMixin {
    @Shadow public abstract Direction getMovementDirection();

    @Shadow
    protected abstract void applySlowdown();

    @Shadow
    abstract boolean willHitBlockAt(BlockPos pos);

    @Shadow @Final private static Map<RailShape, Pair<Vec3i, Vec3i>> ADJACENT_RAIL_POSITIONS_BY_SHAPE;

    @Unique
    Map<RailShape, Direction> railShapeToDirection = Map.of(
            RailShape.ASCENDING_EAST, Direction.EAST,
            RailShape.ASCENDING_SOUTH, Direction.SOUTH,
            RailShape.ASCENDING_NORTH, Direction.NORTH,
            RailShape.ASCENDING_WEST, Direction.WEST
    );

    /**
     * @author RyanAnayaMc
     * @reason To adjust logic of cart slowdowns when off the rails
     */
    @Overwrite
    public void moveOffRail() {
        AbstractMinecartEntity cart = (AbstractMinecartEntity)(Object)this;

        double maxSpeed = this.getMaxSpeed();
        Vec3d velocity = cart.getVelocity();

        cart.setVelocity(MathHelper.clamp(velocity.x, -maxSpeed, maxSpeed), velocity.y, MathHelper.clamp(velocity.z, -maxSpeed, maxSpeed));
        // If cart is grounded, rapidly decelerate (vanilla behavior)
        if (cart.isOnGround()) {
            cart.setVelocity(cart.getVelocity().multiply(0.5));
        }

        cart.move(MovementType.SELF, cart.getVelocity());
        if (!cart.isOnGround() && !cart.isInFluid())
            // If cart is airborne, crank speed up even more
            cart.setVelocity(cart.getVelocity().multiply(1.1));
        else if (!cart.isOnGround()) {
            // If cart is not grounded but in a fluid, do vanilla stuff
            cart.setVelocity(cart.getVelocity().multiply(0.95));
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void moveOnRail(BlockPos startPos, BlockState state) {
        AbstractMinecartEntity cart = (AbstractMinecartEntity)(Object)this;
        cart.onLanding();
        double cartX = cart.getX();
        double cartY = cart.getY();
        double cartZ = cart.getZ();
        Vec3d cartPosition = cart.snapPositionToRail(cartX, cartY, cartZ);
        cartY = (double)startPos.getY();
        boolean isOnMagicRail = state.isOf(EBlocks.MAGIC_RAIL);
        boolean isOnPoweredRail = isOnMagicRail;
        boolean isOnBrakeRail = false;
        if (state.isOf(Blocks.POWERED_RAIL)) {
            isOnPoweredRail = (Boolean)state.get(PoweredRailBlock.POWERED);
            isOnBrakeRail = !isOnPoweredRail;
        }

        double g = 0.0078125;
        if (cart.isTouchingWater()) {
            g *= 0.2;
        }

        Vec3d cartVelocity = cart.getVelocity();
        RailShape railShape = state.get(((AbstractRailBlock)state.getBlock()).getShapeProperty());
        switch (railShape) {
            case ASCENDING_EAST:
                cart.setVelocity(cartVelocity.add(-g, 0.0, 0.0));
                cartY++;
                break;
            case ASCENDING_WEST:
                cart.setVelocity(cartVelocity.add(g, 0.0, 0.0));
                cartY++;
                break;
            case ASCENDING_NORTH:
                cart.setVelocity(cartVelocity.add(0.0, 0.0, g));
                cartY++;
                break;
            case ASCENDING_SOUTH:
                cart.setVelocity(cartVelocity.add(0.0, 0.0, -g));
                cartY++;
        }

        cartVelocity = cart.getVelocity();
        Pair<Vec3i, Vec3i> pair = ADJACENT_RAIL_POSITIONS_BY_SHAPE.get(railShape);
        Vec3i exitRelativePosition1 = pair.getFirst();
        Vec3i exitRelativePosition2 = pair.getSecond();
        double exitDiffX = (double)(exitRelativePosition2.getX() - exitRelativePosition1.getX());
        double exitDiffZ = (double)(exitRelativePosition2.getZ() - exitRelativePosition1.getZ());
        double exitDiffHypotenuse = Math.sqrt(exitDiffX * exitDiffX + exitDiffZ * exitDiffZ);
        double k = cartVelocity.x * exitDiffX + cartVelocity.z * exitDiffZ;
        if (k < 0.0) {
            exitDiffX = -exitDiffX;
            exitDiffZ = -exitDiffZ;
        }

        double horizontalVelocity = Math.min(this.getMaxSpeed(), cartVelocity.horizontalLength());
        cartVelocity = new Vec3d(horizontalVelocity * exitDiffX / exitDiffHypotenuse, cartVelocity.y, horizontalVelocity * exitDiffZ / exitDiffHypotenuse);
        cart.setVelocity(cartVelocity);
        Entity entity = cart.getFirstPassenger();
        if (entity instanceof PlayerEntity) {
            Vec3d playerVelocity = entity.getVelocity();
            double m = playerVelocity.horizontalLengthSquared();
            double n = cart.getVelocity().horizontalLengthSquared();
            if (m > 1.0E-4 && n < 0.01) {
                cart.setVelocity(cart.getVelocity().add(playerVelocity.x * 0.1, 0.0, playerVelocity.z * 0.1));
                isOnBrakeRail = false;
            }
        }

        if (isOnBrakeRail) {
            horizontalVelocity = cart.getVelocity().horizontalLength();
            if (horizontalVelocity < 0.03) {
                cart.setVelocity(Vec3d.ZERO);
            } else {
                cart.setVelocity(cart.getVelocity().multiply(0.5, 0.0, 0.5));
            }
        }

        double o = (double)startPos.getX() + 0.5 + (double)exitRelativePosition1.getX() * 0.5;
        double p = (double)startPos.getZ() + 0.5 + (double)exitRelativePosition1.getZ() * 0.5;
        double q = (double)startPos.getX() + 0.5 + (double)exitRelativePosition2.getX() * 0.5;
        double r = (double)startPos.getZ() + 0.5 + (double)exitRelativePosition2.getZ() * 0.5;
        exitDiffX = q - o;
        exitDiffZ = r - p;
        double s;
        if (exitDiffX == 0.0) {
            s = cartZ - (double)startPos.getZ();
        } else if (exitDiffZ == 0.0) {
            s = cartX - (double)startPos.getX();
        } else {
            double t = cartX - o;
            double u = cartZ - p;
            s = (t * exitDiffX + u * exitDiffZ) * 2.0;
        }

        cartX = o + exitDiffX * s;
        cartZ = p + exitDiffZ * s;
        cart.setPosition(cartX, cartY, cartZ);
        double cartPassengerSpeedMultiplier = cart.hasPassengers() ? 0.75 : 1.0;
        double cartMaxSpeed = this.getMaxSpeed();
        cartVelocity = cart.getVelocity();
        cart.move(MovementType.SELF, new Vec3d(MathHelper.clamp(cartPassengerSpeedMultiplier * cartVelocity.x, -cartMaxSpeed, cartMaxSpeed), 0.0, MathHelper.clamp(cartPassengerSpeedMultiplier * cartVelocity.z, -cartMaxSpeed, cartMaxSpeed)));
        if (exitRelativePosition1.getY() != 0 && MathHelper.floor(cart.getX()) - startPos.getX() == exitRelativePosition1.getX() && MathHelper.floor(cart.getZ()) - startPos.getZ() == exitRelativePosition1.getZ()) {
            cart.setPosition(cart.getX(), cart.getY() + (double)exitRelativePosition1.getY(), cart.getZ());
        } else if (exitRelativePosition2.getY() != 0 && MathHelper.floor(cart.getX()) - startPos.getX() == exitRelativePosition2.getX() && MathHelper.floor(cart.getZ()) - startPos.getZ() == exitRelativePosition2.getZ()) {
            cart.setPosition(cart.getX(), cart.getY() + (double)exitRelativePosition2.getY(), cart.getZ());
        }

        if (!isOnMagicRail)
            this.applySlowdown();

        Vec3d newCartPosition = cart.snapPositionToRail(cart.getX(), cart.getY(), cart.getZ());
        if (newCartPosition != null && cartPosition != null) {
            double v = (cartPosition.y - newCartPosition.y) * 0.05;
            cartVelocity = cart.getVelocity();
            horizontalVelocity = cartVelocity.horizontalLength();
            if (horizontalVelocity > 0.0) {
                cart.setVelocity(cartVelocity.multiply((horizontalVelocity + v) / horizontalVelocity, 1.0, (horizontalVelocity + v) / horizontalVelocity));
            }

            cart.setPosition(cart.getX(), newCartPosition.y, cart.getZ());
        }

        int x = MathHelper.floor(cart.getX());
        int y = MathHelper.floor(cart.getZ());
        if (x != startPos.getX() || y != startPos.getZ()) {
            Vec3d vec3d5 = cart.getVelocity();
            double w = vec3d5.horizontalLength();
            cart.setVelocity(w * (double)(x - startPos.getX()), vec3d5.y, w * (double)(y - startPos.getZ()));
        }

        if (isOnPoweredRail) {
            cartVelocity = cart.getVelocity();
            horizontalVelocity = cartVelocity.horizontalLength();
            if (horizontalVelocity > 0.01) {
                double poweredRailBoost = isOnMagicRail ? 0.12 : 0.06;

                cart.setVelocity(cartVelocity.add(cartVelocity.x / horizontalVelocity * poweredRailBoost, 0.0, cartVelocity.z / horizontalVelocity * poweredRailBoost));
            } else {
                cartVelocity = cart.getVelocity();
                double newVelocityX = cartVelocity.x;
                double newVelocityZ = cartVelocity.z;
                if (railShape == RailShape.EAST_WEST) {
                    if (this.willHitBlockAt(startPos.west())) {
                        newVelocityX = 0.02;
                    } else if (this.willHitBlockAt(startPos.east())) {
                        newVelocityX = -0.02;
                    }
                } else {
                    if (railShape != RailShape.NORTH_SOUTH) {
                        return;
                    }

                    if (this.willHitBlockAt(startPos.north())) {
                        newVelocityZ = 0.02;
                    } else if (this.willHitBlockAt(startPos.south())) {
                        newVelocityZ = -0.02;
                    }
                }

                cart.setVelocity(newVelocityX, cartVelocity.y, newVelocityZ);
            }
        }
    }

    /**
     * @author RyanAnayaMc
     * @reason To make minecarts faster, but to slow them down for certain sensitive areas
     */
    @Overwrite
    public double getMaxSpeed() {
        AbstractMinecartEntity cart = (AbstractMinecartEntity)(Object)this;
        World world = cart.getWorld();

        int i = MathHelper.floor(cart.getX());
        int j = MathHelper.floor(cart.getY());
        int k = MathHelper.floor(cart.getZ());
        if (cart.getWorld().getBlockState(new BlockPos(i, j - 1, k)).isIn(BlockTags.RAILS)) {
            j--;
        }

        BlockPos pos = new BlockPos(i, j, k);

        BlockState state = world.getBlockState(pos);

        if (state.isOf(EBlocks.MAGIC_RAIL)) {
            // Get the blocks that are one and two ahead
            Direction cartDirection = cart.getMovementDirection();
            BlockPos posOneAhead = pos.offset(cartDirection);
            BlockPos posTwoAhead = posOneAhead.offset(cartDirection);
            BlockState stateOneAhead = world.getBlockState(posOneAhead);
            BlockState stateTwoAhead = world.getBlockState(posTwoAhead);

            // Determine if the cart should slow down based on the current rail or the next two ahead
            boolean shouldSlowDown = shouldSlowDownForBlock(state) || shouldSlowDownForBlock(stateOneAhead) || shouldSlowDownForBlock(stateTwoAhead);
            return shouldSlowDown ? cartAmpedMaxSpeed() : cartCrankedMaxSpeed();
        } else {
            // If not on a magic rail, perform default behavior if cart is not airborne
            return state.isAir() ? cartAmpedMaxSpeed() : cartDefaultMaxSpeed();
        }
    }

    @Unique
    private boolean shouldSlowDownForBlock(BlockState blockState) {
        // Don't slow down if it's not a rail
        if (!AbstractRailBlock.isRail(blockState))
            return false;

        // The block should be a rail - we need its shape
        assert AbstractRailBlock.isRail(blockState);
        RailShape railShape = blockState.get(((AbstractRailBlock)blockState.getBlock()).getShapeProperty());

        if (railShape.isAscending()) {
            // Check if the rail is ascending or descending
            Direction movementDirection = ((AbstractMinecartEntity) (Object) this).getMovementDirection();
            Direction railDirection = railShapeToDirection.get(railShape);

            // If directions match, it means the cart is actually going uphill and thus should slow down
            return movementDirection.equals(railDirection);
        }

        // Slow down if the rail is an ascending rail or if it is a turning rail
        return isTurningRail(railShape);
    }

    @Unique
    private static boolean isTurningRail(RailShape railShape) {
        return (railShape == RailShape.NORTH_EAST || railShape == RailShape.NORTH_WEST || railShape == RailShape.SOUTH_EAST || railShape == RailShape.SOUTH_WEST);
    }

    @Unique
    private double cartCrankedMaxSpeed() {
        return cartDefaultMaxSpeed() * 6;
    }

    @Unique
    private double cartAmpedMaxSpeed() {
        return cartDefaultMaxSpeed() * 1.25;
    }

    @Unique
    private double cartDefaultMaxSpeed() {
        return (((AbstractMinecartEntity) (Object) this).isTouchingWater() ? 4.0 : 8.0) / 20.0;
    }
}
