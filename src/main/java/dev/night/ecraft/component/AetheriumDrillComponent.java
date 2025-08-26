package dev.night.ecraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public record AetheriumDrillComponent(
        int currentEnergy,
        byte drillBeamMode, // 0 = focused beam (more range, only one block), 1 = standard beam (3x3x3),  2 = tunneling beam (3x3x9), 3 = wide beam (5x5x5)
        byte efficiencyUpgradeLevel
)
{

    public static final Codec<AetheriumDrillComponent> CODEC = RecordCodecBuilder.create(
            builder -> {
                return builder.group(
                        Codec.INT.fieldOf("currentEnergy").forGetter(AetheriumDrillComponent::currentEnergy),
                        Codec.BYTE.optionalFieldOf("drillBeamMode", (byte) 0).forGetter(AetheriumDrillComponent::drillBeamMode),
                        Codec.BYTE.optionalFieldOf("hasFuelEfficiencyUpgrade", (byte) 0).forGetter(AetheriumDrillComponent::efficiencyUpgradeLevel)
                ).apply(builder, AetheriumDrillComponent::new);
            }
    );
}
