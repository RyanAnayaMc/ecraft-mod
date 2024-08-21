package dev.night.ecraft.client.render.entity;

import dev.night.ecraft.item.spear.SpearEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import dev.night.ecraft.client.render.entity.projectile.spear.CincinnasiteDiamondSpearEntityRenderer;


public class ModEntityRendererRegistry {
    public static void registerModEntityRenderers() {
        EntityRendererRegistry.register(SpearEntityTypes.CINCINNASITE_DIAMOND_SPEAR_ENTITY, CincinnasiteDiamondSpearEntityRenderer::new);
    }
}
