package dev.night.ecraft;

import dev.night.ecraft.client.render.entity.model.ModEntityModelLayers;
import dev.night.ecraft.item.ModArmor;
import dev.night.ecraft.item.ModArmorMaterials;
import dev.night.ecraft.item.ModItemGroups;
import dev.night.ecraft.item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import dev.night.ecraft.client.item.ModModelPredicateProvider;
import dev.night.ecraft.client.render.entity.ModEntityRendererRegistry;

public class EcraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEntityModelLayers.registerModEntityModelLayers();
        ModEntityRendererRegistry.registerModEntityRenderers();
        ModModelPredicateProvider.registerModItemModelPredicates();
    }
}
