package dev.night.ecraft.client.render.entity.model;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.client.render.entity.model.projectile.spear.CincinnasiteDiamondSpearEntityModel;
import dev.night.ecraft.client.render.entity.model.projectile.spear.MetallurgiumSpearEntityModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import solipingen.sassot.SpearsAxesSwordsShieldsAndOtherTools;

@Environment(value = EnvType.CLIENT)
public class ModEntityModelLayers {
    public static final EntityModelLayer CINCINNASITE_DIAMOND_SPEAR_ENTITY_MODEL_LAYER = new EntityModelLayer(Identifier.of(Ecraft.SASSOT_ID, "cincinnasite_diamond_spear"), "main");
    public static final EntityModelLayer METALLURGIUM_SPEAR_ENTITY_MODEL_LAYER = new EntityModelLayer(Identifier.of(Ecraft.SASSOT_ID, "metallurgium_spear"), "main");

    public static void registerModEntityModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(CINCINNASITE_DIAMOND_SPEAR_ENTITY_MODEL_LAYER, CincinnasiteDiamondSpearEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(METALLURGIUM_SPEAR_ENTITY_MODEL_LAYER, MetallurgiumSpearEntityModel::getTexturedModelData);
    }
}
