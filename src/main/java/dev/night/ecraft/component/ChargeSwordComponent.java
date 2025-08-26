package dev.night.ecraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ChargeSwordComponent(
            byte charge
        )
{
    public static final Codec<ChargeSwordComponent> CODEC = RecordCodecBuilder.create(
            builder -> {
                return builder.group(
                        Codec.BYTE.fieldOf("battery").forGetter(ChargeSwordComponent::charge)
                ).apply(builder, ChargeSwordComponent::new);
            }
    );
}
