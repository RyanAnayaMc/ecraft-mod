package dev.night.ecraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public record DetonatorComponent(
        List<BlockPos> chargeLocations
)
{
    public static final Codec<DetonatorComponent> CODEC = RecordCodecBuilder.create(
            builder -> {
                return builder.group(
                        Codec.list(BlockPos.CODEC).optionalFieldOf("chargeLocations", new ArrayList<>()).forGetter(DetonatorComponent::chargeLocations)
                ).apply(builder, DetonatorComponent::new);
            }
    );
}
