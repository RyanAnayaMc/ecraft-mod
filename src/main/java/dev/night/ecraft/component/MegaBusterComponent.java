package dev.night.ecraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;

public record MegaBusterComponent(
            int battery
        )
{
    public static final Codec<MegaBusterComponent> CODEC = RecordCodecBuilder.create(
            builder -> {
                return builder.group(
                        Codec.INT.fieldOf("battery").forGetter(MegaBusterComponent::battery)
                ).apply(builder, MegaBusterComponent::new);
            }
    );
}
