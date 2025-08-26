package dev.night.ecraft.client.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.tools.weapons.MegaBusterItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MegaBusterItemRenderer extends GeoItemRenderer<MegaBusterItem> {

    public MegaBusterItemRenderer() {
        super(
                new DefaultedItemGeoModel<>(Identifier.of(Ecraft.MOD_ID, "mega_buster"))
        );
    }
}
