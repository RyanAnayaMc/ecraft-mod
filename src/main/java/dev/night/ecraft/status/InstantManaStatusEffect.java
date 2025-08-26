package dev.night.ecraft.status;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.InstantStatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.jetbrains.annotations.Nullable;

public class InstantManaStatusEffect extends InstantStatusEffect {
    private final int baseMana;

    public InstantManaStatusEffect(StatusEffectCategory category, int color, int baseMana) {
        super(category, color);
        this.baseMana = baseMana;
    }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (target instanceof ManaUsingEntity manaUsingEntity)
            doManaRecovery(manaUsingEntity, amplifier);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof ManaUsingEntity manaUsingEntity)
            doManaRecovery(manaUsingEntity, amplifier);

        return true;
    }

    private void doManaRecovery(ManaUsingEntity entity, float amplifier) {
        entity.manaattributes$addMana(baseMana * (amplifier + 2) / 2);
    }
}
