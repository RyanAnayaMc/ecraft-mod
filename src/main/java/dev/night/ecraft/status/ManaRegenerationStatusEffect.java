package dev.night.ecraft.status;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ManaRegenerationStatusEffect extends EStatusEffect {
    public ManaRegenerationStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof ManaUsingEntity manaUsingEntity && manaUsingEntity.manaattributes$getMana() < manaUsingEntity.manaattributes$getMaxMana()) {
            manaUsingEntity.manaattributes$addMana(5);
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 50 >> amplifier;
        return i == 0 || duration % i == 0;
    }
}
