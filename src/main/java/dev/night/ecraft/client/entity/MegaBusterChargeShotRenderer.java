package dev.night.ecraft.client.entity;

import dev.night.ecraft.item.EItems;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.item.ItemStack;

public class MegaBusterChargeShotRenderer extends AbstractMegaBusterChargeShotRenderer {
    public MegaBusterChargeShotRenderer(EntityRendererFactory.Context ctx, float scale, boolean lit) {
        super(ctx, scale, lit);
    }

    public MegaBusterChargeShotRenderer(EntityRendererFactory.Context context) {
        this(context, 0.5f, false);
    }

    @Override
    public ItemStack getShotStack() {
        return EItems.MEGA_BUSTER_SHOT_2.getDefaultStack();
    }
}
