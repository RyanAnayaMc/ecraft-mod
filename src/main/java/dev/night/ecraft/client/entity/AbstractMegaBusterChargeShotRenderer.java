package dev.night.ecraft.client.entity;

import dev.night.ecraft.entity.MegaBusterChargeShotEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public abstract class AbstractMegaBusterChargeShotRenderer extends EntityRenderer<MegaBusterChargeShotEntity> {
    private static final float MIN_DISTANCE = 12.25F;
    private final ItemRenderer itemRenderer;
    private final boolean lit;
    private final float scale;

    public AbstractMegaBusterChargeShotRenderer(EntityRendererFactory.Context ctx, float scale, boolean lit) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
        this.scale = scale;
        this.lit = lit;
    }

    public AbstractMegaBusterChargeShotRenderer(EntityRendererFactory.Context context) {
        this(context, 0.5f, false);
    }

    public abstract ItemStack getShotStack();

    protected int getBlockLight(MegaBusterChargeShotEntity entity, BlockPos pos) {
        return this.lit ? 15 : super.getBlockLight(entity, pos);
    }

    public void render(MegaBusterChargeShotEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.age >= 2 || !(this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(entity) < 12.25)) {
            matrices.push();
            matrices.scale(scale, scale, scale);
            matrices.multiply(this.dispatcher.getRotation());
            this.itemRenderer.renderItem(getShotStack(), ModelTransformationMode.GROUND, 255, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), entity.getId());
            matrices.pop();
            super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        }
    }

    public Identifier getTexture(MegaBusterChargeShotEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}