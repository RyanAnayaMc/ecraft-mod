package dev.night.ecraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public record GunlanceComponent(
            int currentAmmo,
            int shellChargingTime,
            int fullBurstExplosionCooldown, List<Vec3d> explosionQueue,
            long lastHitTime, int currentCombo,
            int wyvernFireWindupTime, long lastWyvernFire, boolean wyvernFireIsWinding
        )
{
    public static final Codec<GunlanceComponent> CODEC = RecordCodecBuilder.create(
            builder -> {
                return builder.group(
                        Codec.INT.fieldOf("currentAmmo").forGetter(GunlanceComponent::currentAmmo),
                        Codec.INT.optionalFieldOf("shellChargingTime", 0).forGetter(GunlanceComponent::shellChargingTime),
                        Codec.INT.optionalFieldOf("fullBurstExplosionCooldown", 0).forGetter(GunlanceComponent::fullBurstExplosionCooldown),
                        Codec.list(Vec3d.CODEC).optionalFieldOf("explosionQueue", new ArrayList<>()).forGetter(GunlanceComponent::explosionQueue),
                        Codec.LONG.optionalFieldOf("comboTimer", 0L).forGetter(GunlanceComponent::lastHitTime),
                        Codec.INT.optionalFieldOf("currentCombo", 0).forGetter(GunlanceComponent::currentCombo),
                        Codec.INT.optionalFieldOf("wyvernFireWindupTime", 0).forGetter(GunlanceComponent::wyvernFireWindupTime),
                        Codec.LONG.optionalFieldOf("lastWyvernFire", 0L).forGetter(GunlanceComponent::lastWyvernFire),
                        Codec.BOOL.optionalFieldOf("wyvernFireIsWinding", false).forGetter(GunlanceComponent::wyvernFireIsWinding)
                ).apply(builder, GunlanceComponent::new);
            }
    );
}
