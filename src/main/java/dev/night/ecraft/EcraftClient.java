package dev.night.ecraft;

import dev.night.ecraft.item.ModArmor;
import dev.night.ecraft.item.ModArmorMaterials;
import dev.night.ecraft.item.ModItemGroups;
import dev.night.ecraft.item.ModItems;
import net.fabricmc.api.ClientModInitializer;

public class EcraftClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModItems.initialize();
        ModArmorMaterials.initialize();
        ModArmor.initialize();
        ModItemGroups.initialize();
    }
}
