package dev.night.ecraft.enchantments.enchantmenteffects;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.damage.EDamageTypes;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record TipperEnchantmentEffect(EnchantmentLevelBasedValue tipperAttackDamageMultiplier) implements EnchantmentEntityEffect {
    public static final MapCodec<TipperEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    EnchantmentLevelBasedValue.CODEC.fieldOf("tipperAttackDamageMultiplier").forGetter(TipperEnchantmentEffect::tipperAttackDamageMultiplier)
            ).apply(instance, TipperEnchantmentEffect::new)
    );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        // Determine if user and target are both living entities
        if (target instanceof LivingEntity victim && context.owner() != null && context.owner() instanceof LivingEntity user) {
            // Determine if tipper damage should be dealt
            double userRange = user.getAttributeValue(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE);
            double tipperMinRange = userRange - 1;
            if (user.distanceTo(victim) > tipperMinRange) {
                double baseDamage = user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
                float tipperBonus = (float) (baseDamage * tipperAttackDamageMultiplier.getValue(level));
                victim.damage(world.getDamageSources().mobAttack(user), tipperBonus
                );
                victim.setVelocity(victim.getVelocity().multiply(level + 0.5));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
