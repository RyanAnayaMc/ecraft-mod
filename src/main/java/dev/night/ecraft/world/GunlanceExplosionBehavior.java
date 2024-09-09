package dev.night.ecraft.world;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.explosion.AdvancedExplosionBehavior;
import net.minecraft.world.explosion.Explosion;

import java.util.Optional;

public class GunlanceExplosionBehavior extends AdvancedExplosionBehavior {
    private final LivingEntity USER;
    public static final float KNOCKBACK = 0.75F;
    public static final float KNOCKBACK_BURST = 0.1F;
    private final float DAMAGE;

    public GunlanceExplosionBehavior(boolean destroyBlocks, boolean damageEntities, Optional<Float> knockbackModifier, Optional<RegistryEntryList<Block>> immuneBlocks) {
        super(destroyBlocks, damageEntities, knockbackModifier, immuneBlocks);
        USER = null;
        DAMAGE = 5;
    }

    public GunlanceExplosionBehavior(LivingEntity user, float damage, boolean isFullBurst) {
        super(false, true, Optional.of(isFullBurst ? KNOCKBACK_BURST : KNOCKBACK), Optional.empty());
        USER = user;
        DAMAGE = damage;
    }

    @Override
    public boolean shouldDamage(Explosion explosion, Entity entity) {
        return (USER == null || !entity.equals(USER));
    }

    @Override
    public float calculateDamage(Explosion explosion, Entity entity) {
        return DAMAGE;
    }
}
