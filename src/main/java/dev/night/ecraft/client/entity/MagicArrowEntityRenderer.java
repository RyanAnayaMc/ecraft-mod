package dev.night.ecraft.client.entity;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.entity.MagicArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class MagicArrowEntityRenderer extends ProjectileEntityRenderer<MagicArrowEntity> {
    public static final Identifier TEXTURE = Identifier.of(Ecraft.MOD_ID, "textures/entity/magic_arrow.png");

    public MagicArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(MagicArrowEntity spectralArrowEntity) {
        return TEXTURE;
    }
}
