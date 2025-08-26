package dev.night.ecraft.datagen;

import dev.night.ecraft.Ecraft;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class DatagenModels {
    public static final Model LANCE = new Model(Optional.of(Identifier.of(Ecraft.MOD_ID, "item/lance")), Optional.empty(), TextureKey.LAYER0);
    public static final Model STAFF = new Model(Optional.of(Identifier.of(Ecraft.MOD_ID, "item/staff")), Optional.empty(), TextureKey.LAYER0);
}
