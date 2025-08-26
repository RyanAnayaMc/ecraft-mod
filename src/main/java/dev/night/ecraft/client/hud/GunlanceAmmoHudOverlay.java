package dev.night.ecraft.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.component.GunlanceComponent;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.tools.weapons.GunlanceItem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class GunlanceAmmoHudOverlay implements HudRenderCallback {
    public static final Identifier SHELL_HUD = Identifier.of(Ecraft.MOD_ID, "hud/gunlance/shell");
    public static final Identifier SHELL_EMPTY_HUD = Identifier.of(Ecraft.MOD_ID, "hud/gunlance/shell_empty");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        if (!Ecraft.CONFIG.displayGunlanceHud())
            return;

        MinecraftClient client = MinecraftClient.getInstance();
        int x = 0;
        int y = 0;

        ClientPlayerEntity player = client.player;
        ItemStack stack = player.getMainHandStack();

        if (!(stack.getItem() instanceof GunlanceItem gunlanceItem))
            return;

        GunlanceComponent component = stack.getOrDefault(EComponents.GUNLANCE_COMPONENT, GunlanceItem.getDefaultComponent(gunlanceItem.AMMO_CAPACITY));

        int currentAmmo = component.currentAmmo();
        int maxAmmo = gunlanceItem.AMMO_CAPACITY;

        int ammoCenterPoint = maxAmmo * 4;

        x = drawContext.getScaledWindowWidth() / 2 - ammoCenterPoint + Ecraft.CONFIG.gunlanceHudOffsetX();
        y = drawContext.getScaledWindowHeight() - 64 + Ecraft.CONFIG.gunlanceHudOffsetY();

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1, 1, 1, 1);

        for (int i = 0; i < maxAmmo; i++)
            if (i < currentAmmo)
                drawContext.drawGuiTexture(SHELL_HUD, x + i * 8, y, 8, 14);
            else
                drawContext.drawGuiTexture(SHELL_EMPTY_HUD, x + i * 8, y, 8, 14);
    }
}

