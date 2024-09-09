package dev.night.ecraft.client.model;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.GunlanceItem;
import dev.night.ecraft.item.ModToolMaterials;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

import java.util.Map;

public class GunlanceItemGeoModel extends GeoModel<GunlanceItem> {
    private ToolMaterial material;
    public static final Map<ToolMaterial, String> materialToId = Map.of(
            ModToolMaterials.CINCINNASITE_DIAMOND, "cincinnasite_diamond",
            ToolMaterials.NETHERITE, "netherite"
    );
    private String itemId;

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
