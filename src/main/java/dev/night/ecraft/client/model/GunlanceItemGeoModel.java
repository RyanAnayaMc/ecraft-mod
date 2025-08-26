package dev.night.ecraft.client.model;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.tools.weapons.GunlanceItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;


import static dev.night.ecraft.client.item.GunlanceItemRenderer.materialToId;


public class GunlanceItemGeoModel extends GeoModel<GunlanceItem> {

    private final ToolMaterial material;

    private final String itemId;

    public GunlanceItemGeoModel(ToolMaterial material) {
        super();
        this.material = material;
        itemId = materialToId.get(material);
    }

    @Override
    public Identifier getModelResource(GunlanceItem animatable) {
        return Identifier.of(Ecraft.MOD_ID, "geo/" + itemId + "_gunlance.geo.json");
    }

    @Override
    public Identifier getTextureResource(GunlanceItem animatable) {
        return Identifier.of(Ecraft.MOD_ID, "textures/item/gunlances/" + itemId + "_gunlance.png");
    }

    @Override
    public Identifier getAnimationResource(GunlanceItem animatable) {
        return Identifier.of(Ecraft.MOD_ID, "animations/" + itemId + "_gunlance.animation.json");
    }
}
