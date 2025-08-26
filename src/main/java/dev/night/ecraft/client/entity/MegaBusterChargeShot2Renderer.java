package dev.night.ecraft.client.entity;

import dev.night.ecraft.item.EItems;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.item.ItemStack;

public class MegaBusterChargeShot2Renderer extends AbstractMegaBusterChargeShotRenderer {
    public MegaBusterChargeShot2Renderer(EntityRendererFactory.Context ctx, float scale, boolean lit) {
        super(ctx, scale, lit);
    }

    public MegaBusterChargeShot2Renderer(EntityRendererFactory.Context context) {
        this(context, 1.5f, false);
    }

    @Override
    public ItemStack getShotStack() {
        return EItems.MEGA_BUSTER_SHOT_3.getDefaultStack();
    }
}
