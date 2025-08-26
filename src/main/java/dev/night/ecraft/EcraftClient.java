package dev.night.ecraft;

import dev.night.ecraft.client.hud.GunlanceAmmoHudOverlay;
import dev.night.ecraft.client.hud.ManaHudOverlay;
import dev.night.ecraft.client.hud.MegaBusterHudOverlay;
import dev.night.ecraft.entity.EEntities;
import dev.night.ecraft.util.EModelPredicates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class EcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // KeyBindingHelper.registerKeyBinding(Keybinds.RELOAD_KEYBIND);
        HudRenderCallback.EVENT.register(new GunlanceAmmoHudOverlay());
        HudRenderCallback.EVENT.register(new MegaBusterHudOverlay());
        HudRenderCallback.EVENT.register(new ManaHudOverlay());
        // HudRenderCallback.EVENT.register(new PointBlankHudOverlay());
        EModelPredicates.initialize();
        EEntities.initializeClient();
    }
}
