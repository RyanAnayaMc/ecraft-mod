package dev.night.ecraft.client.entity;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.entity.MegaBusterLemonEntity;
import dev.night.ecraft.item.EItems;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class MegaBusterLemonRenderer extends EntityRenderer<MegaBusterLemonEntity> {
    private static final float MIN_DISTANCE = 12.25F;
    private final ItemRenderer itemRenderer;
    private final float scale;
    private final boolean lit;

    public MegaBusterLemonRenderer(EntityRendererFactory.Context ctx, float scale, boolean lit) {
        super(ctx);
        this.itemRenderer = ctx.getItemRenderer();
        this.scale = scale;
        this.lit = lit;
    }

    public MegaBusterLemonRenderer(EntityRendererFactory.Context context) {
        this(context, .5F, true);
    }

    protected int getBlockLight(MegaBusterLemonEntity entity, BlockPos pos) {
        return this.lit ? 15 : super.getBlockLight(entity, pos);
    }

    public void render(MegaBusterLemonEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        Ecraft.LOGGER.info("age " + entity.age + " square dist " + this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(entity));
        if (entity.age >= 2 || !(this.dispatcher.camera.getFocusedEntity().squaredDistanceTo(entity) < 12.25)) {
            matrices.push();
            matrices.scale(this.scale, this.scale, this.scale);
            matrices.multiply(this.dispatcher.getRotation());
            this.itemRenderer.renderItem(EItems.MEGA_BUSTER_SHOT_1.getDefaultStack(), ModelTransformationMode.GROUND, 255, OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, entity.getWorld(), entity.getId());
            matrices.pop();
            super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        }
    }

    public Identifier getTexture(MegaBusterLemonEntity entity) {
        return SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE;
    }
}