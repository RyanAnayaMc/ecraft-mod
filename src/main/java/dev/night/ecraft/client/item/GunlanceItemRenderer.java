package dev.night.ecraft.client.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.EToolMaterials;
import dev.night.ecraft.item.tools.weapons.GunlanceItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

import java.util.Map;

public class GunlanceItemRenderer extends GeoItemRenderer<GunlanceItem> {
    public static final Map<ToolMaterial, String> materialToId = Map.of(
            EToolMaterials.CINCINNASITE_DIAMOND, "cincinnasite_diamond",
            EToolMaterials.HELLSTONE, "hellstone",
            ToolMaterials.NETHERITE, "netherite",
            EToolMaterials.DETONIUM, "detonium"
    );

    public GunlanceItemRenderer(ToolMaterial toolMaterial) {
        super(
                new DefaultedItemGeoModel<>(Identifier.of(Ecraft.MOD_ID, materialToId.get(toolMaterial) + "_gunlance"))
        );
    }
}
