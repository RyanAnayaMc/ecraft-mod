package dev.night.ecraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ManaStarComponent(
            int mana
        )
{
    public static final Codec<ManaStarComponent> CODEC = RecordCodecBuilder.create(
            builder -> {
                return builder.group(
                        Codec.INT.fieldOf("mana").forGetter(ManaStarComponent::mana)
                ).apply(builder, ManaStarComponent::new);
            }
    );
}
