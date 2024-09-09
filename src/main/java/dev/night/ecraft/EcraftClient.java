package dev.night.ecraft;

import dev.night.ecraft.client.GunlanceAmmoHudOverlay;
import dev.night.ecraft.client.Keybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class EcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // KeyBindingHelper.registerKeyBinding(Keybinds.RELOAD_KEYBIND);

        HudRenderCallback.EVENT.register(new GunlanceAmmoHudOverlay());
    }
}
