package dev.night.ecraft.client.render.entity.projectile.spear;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.client.render.entity.model.ModEntityModelLayers;
import dev.night.ecraft.client.render.entity.model.projectile.spear.CincinnasiteDiamondSpearEntityModel;
import dev.night.ecraft.item.spear.CincinnasiteDiamondSpearEntity;
import dev.night.ecraft.item.spear.MetallurgiumSpearEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

@Environment(value = EnvType.CLIENT)
public class MetallurgiumSpearEntityRenderer extends EntityRenderer<MetallurgiumSpearEntity> {
    public static final Identifier TEXTURE = Identifier.of(Ecraft.SASSOT_ID, "textures/entity/spear/metallurgium_spear.png");
    private final CincinnasiteDiamondSpearEntityModel model;


    public MetallurgiumSpearEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new CincinnasiteDiamondSpearEntityModel(context.getPart(ModEntityModelLayers.METALLURGIUM_SPEAR_ENTITY_MODEL_LAYER));
    }

    @Override
    public void render(MetallurgiumSpearEntity spearEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, spearEntity.prevYaw, spearEntity.getYaw()) - 90.0f));
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, spearEntity.prevPitch, spearEntity.getPitch()) - 90.0f));
        matrixStack.translate(0.0f, -1.7f, 0.0f);
        VertexConsumer vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumerProvider, this.model.getLayer(this.getTexture(spearEntity)), false, spearEntity.isEnchanted());
        this.model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV);
        matrixStack.pop();
        super.render(spearEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(MetallurgiumSpearEntity spearEntity) {
        return TEXTURE;
    }
}