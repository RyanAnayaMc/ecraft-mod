package dev.night.ecraft.status;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class DamageOnTouchStatusEffect extends StatusEffect {
    public static final int RANGE = 1;
    public static final int KNOCKBACK_MULTIPLIER = 5;
    private final int damage;
    
    protected DamageOnTouchStatusEffect(StatusEffectCategory category, int damage, int color) {
        super(category, color);
        this.damage = damage;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity user, int amplifier) {
        if (user.getWorld() instanceof ServerWorld serverWorld) {
            // Get all entities who are hit
            BlockPos userPos = user.getBlockPos();
            int x0 = userPos.getX() - RANGE;
            int x1 = userPos.getX() + RANGE;
            int y0 = userPos.getY() - RANGE;
            int y1 = userPos.getY() + RANGE;
            int z0 = userPos.getZ() - RANGE;
            int z1 = userPos.getZ() + RANGE;
            List<Entity> entities = serverWorld.getOtherEntities(user, new Box(x0, y0, z0, x1, y1, z1));

            // Iterate over entities hit
            for (Entity entity : entities) {
                // Ignore players in creative/spectator
                if (entity instanceof PlayerEntity playerEntity && (playerEntity.isCreative() || playerEntity.isSpectator())) {
                    // Deal damage
                    entity.damage(serverWorld.getDamageSources().mobAttack(user), damage);

                    // Get knockback
                    double knockbackModifier = KNOCKBACK_MULTIPLIER * user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);

                    knockbackModifier *= (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);

                    double knockbackX = entity.getX() - user.getX();
                    double knockbackY = entity.getY() - user.getY();
                    double knockbackZ = entity.getZ() - user.getZ();

                    double squaredMagnitude = knockbackX * knockbackX + knockbackY * knockbackY + knockbackZ * knockbackZ;
                    knockbackModifier /= squaredMagnitude;

                    Vec3d knockbackVector = new Vec3d(knockbackX, knockbackY, knockbackZ).multiply(knockbackModifier);

                    // Apply knockback
                    entity.setVelocity(knockbackVector);
                }
            }
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration > 0;
    }
}
