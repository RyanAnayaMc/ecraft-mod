package dev.night.ecraft.client.model;

import dev.night.ecraft.item.GunlanceItem;
import net.minecraft.item.ToolMaterial;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GunlanceItemRenderer extends GeoItemRenderer<GunlanceItem> {
    public GunlanceItemRenderer(ToolMaterial toolMaterial) {
        super(new GunlanceItemGeoModel(toolMaterial));

    }
}
