package dev.night.ecraft.util;

import com.provismet.CombatPlusCore.utility.item.AttributeIdentifiers;
import dev.night.ecraft.item.EToolMaterials;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.joml.Vector3f;

public class Utilities {
    /**
     * Checks the time difference between a world's time and a given time
     * @param world The world to get the current time of
     * @param time The time to compare to
     * @param maxAllowedTimeDelta The maximum allowed difference in times
     * @return Whether world and time have a time difference no more than maxAllowedTimeDelta
     */
    public static boolean checkTimeDelta(World world, long time, int maxAllowedTimeDelta) {
        return checkTimeDelta(world.getTime(), time, maxAllowedTimeDelta);
    }

    /**
     * Checks the time difference between two times
     * @param time1 The first time to check
     * @param time2 The second time to check
     * @param maxAllowedTimeDelta The maximum allowed difference in times
     * @return Whether time1 and time2 have a difference no more than maxAllowedTimeDelta
     */
    public static boolean checkTimeDelta(long time1, long time2, int maxAllowedTimeDelta) {
        return Math.abs(time1 - time2) <= maxAllowedTimeDelta;
    }

    /**
     * Converts a given Vec3i to a normalized Vec3d
     * @param vector The vector to convert and normalize
     * @return A normalized Vec3d
     */
    public static Vec3d convertVector(Vec3i vector) {
        return convertVector(vector, true);
    }

    /**
     * Converts a Vec3i to a Vec3d and optionally normalizes it
     * @param vector The Vec3i to convert
     * @param normalize Whether to normalize the vector
     * @return The new vector
     */
    public static Vec3d convertVector(Vec3i vector, boolean normalize) {
        Vec3d newVector = new Vec3d(vector.getX(), vector.getY(), vector.getZ());

        if (normalize)
            return newVector.normalize();
        else
            return newVector;
    }

    /**
     * Checks to see if an entity is moving backwards. An entity is considered moving backwards if its movement vector and its look direction vector has an angle of between 90 and 270 degrees.
     * @param entity The entity to check
     * @return Whether the given entity is moving backwards
     */
    public static boolean isMovingBackwards(Entity entity) {
        Vector3f userMoveDir = entity.getMovement().toVector3f().normalize();
        Vector3f userLookDir = entity.getFacing().getUnitVector();
        double movementAngle = Math.acos(userMoveDir.dot(userLookDir));

        // User is considered as moving backwards if angle is between pi/2 and 3pi/2 radians aka 90 or 270 degrees
        return (movementAngle > Math.PI * 0.5) && (movementAngle < Math.PI * 1.5);
    }

    /**
     * Checks if an entity in a world is affected by daylight. Code stolen from logic to make zombies burn in daylight with RNG removed.
     * @param world The world the entity is in
     * @param entity The entity to check sunlight exposure for
     * @return Whether the given entity is in the sunlight
     */
    public static boolean isAffectedByDaylight(World world, Entity entity) {
        float f = world.isPosLoaded(entity.getBlockX(), entity.getBlockZ()) ? world.getBrightness(BlockPos.ofFloored(entity.getX(), entity.getEyeY(), entity.getZ())) : 0.0F;
        BlockPos blockPos = BlockPos.ofFloored(entity.getX(), entity.getEyeY(), entity.getZ());
        boolean bl = entity.isWet() || entity.inPowderSnow || entity.wasInPowderSnow;
        return f > 0.5F && !bl && world.isSkyVisible(blockPos);
    }

    public static AttributeModifiersComponent createToolAttributes (ToolMaterial material, float baseAttackDamage, float baseAttackSpeed) {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                baseAttackDamage + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                );

        float bonusAttackSpeed = 0f;

        if (material instanceof EToolMaterials extraMaterial && extraMaterial.getCustomAttribute() == EntityAttributes.GENERIC_ATTACK_SPEED) {
            bonusAttackSpeed = extraMaterial.getCustomAttributeValue();
        }

        builder.add(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(
                        Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                        baseAttackSpeed + bonusAttackSpeed,
                        EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.MAINHAND
        );

        if (material instanceof EToolMaterials extraMaterial && extraMaterial.getCustomAttribute() != null && extraMaterial.getCustomAttribute() != EntityAttributes.GENERIC_ATTACK_SPEED) {
            builder.add(
                    extraMaterial.getCustomAttribute(),
                    new EntityAttributeModifier(
                            AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE,
                            extraMaterial.getCustomAttributeValue(),
                            EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.MAINHAND
            );
        }

        return builder.build();
    }
}
