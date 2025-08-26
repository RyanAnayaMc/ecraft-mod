package dev.night.ecraft.status;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;

public class EStatusEffect extends StatusEffect {
    public EStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public EStatusEffect(StatusEffectCategory category, int color, ParticleEffect particleEffect) {
        super(category, color, particleEffect);
    }
}
