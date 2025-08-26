package dev.night.ecraft.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record StaffComponent(
            long timeLastUsed
        )
{
    public static final Codec<StaffComponent> CODEC = RecordCodecBuilder.create(
            builder -> {
                return builder.group(
                        Codec.LONG.fieldOf("timeLastUsed").forGetter(StaffComponent::timeLastUsed)
                ).apply(builder, StaffComponent::new);
            }
    );
}
