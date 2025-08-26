package dev.night.ecraft.client.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.tools.weapons.XBusterItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class XBusterItemRenderer extends GeoItemRenderer<XBusterItem> {

    public XBusterItemRenderer() {
        super(
                new DefaultedItemGeoModel<>(Identifier.of(Ecraft.MOD_ID, "x_buster"))
        );
    }
}
