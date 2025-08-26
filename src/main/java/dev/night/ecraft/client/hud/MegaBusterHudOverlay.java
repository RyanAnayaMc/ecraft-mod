package dev.night.ecraft.client.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.component.MegaBusterComponent;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.tools.weapons.MegaBusterItem;
import dev.night.ecraft.item.tools.weapons.XBusterItem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MegaBusterHudOverlay implements HudRenderCallback {
    public static final Identifier X_HUD_BASE = Identifier.of(Ecraft.MOD_ID, "hud/mega_buster/x_hud_base");
    public static final Identifier BUSTER_HUD_BASE = Identifier.of(Ecraft.MOD_ID, "hud/mega_buster/buster_hud_base");
    public static final Identifier BUSTER_HUD_FILLED = Identifier.of(Ecraft.MOD_ID, "hud/mega_buster/buster_hud_filled");
    public static final Identifier BUSTER_HUD_EMPTY = Identifier.of(Ecraft.MOD_ID, "hud/mega_buster/buster_hud_empty");
    public static final Identifier BUSTER_HUD_TOP = Identifier.of(Ecraft.MOD_ID, "hud/mega_buster/buster_hud_top");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        if (!Ecraft.CONFIG.displayMegaBusterHud())
            return;

        MinecraftClient client = MinecraftClient.getInstance();
        int x = 0;
        int y = 0;

        ClientPlayerEntity player = client.player;
        ItemStack stack = player.getMainHandStack();

        MegaBusterComponent component = stack.get(EComponents.MEGA_BUSTER_COMPONENT);

        if (component == null)
            return;

        boolean isMegaBuster = stack.getItem() instanceof MegaBusterItem;
        int currentEnergy = component.battery();
        int maxEnergy = isMegaBuster ? MegaBusterItem.MEGA_BUSTER_BATTERY_CAPACITY : XBusterItem.X_BUSTER_BATTERY_CAPACITY;

        x = 10 + Ecraft.CONFIG.megaBusterHudOffsetX();
        y = 10 + Ecraft.CONFIG.megaBusterHudOffsetY();

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderColor(1, 1, 1, 1);

        // Draw top
        drawContext.drawGuiTexture(BUSTER_HUD_TOP, x, y, 14, 4);
        y += 2;

        // Draw ammo indicators
        int numIndicators = maxEnergy / 10;
        int filledIndicators = (currentEnergy + 9) / 10; // currentEnergy divided by 10, forcibly rounded up
        int notFilledIndicators = numIndicators - filledIndicators;

        for (int i = 0; i < numIndicators; i++)
            if (i < notFilledIndicators)
                drawContext.drawGuiTexture(BUSTER_HUD_EMPTY, x, y += 2, 14, 2);
            else
                drawContext.drawGuiTexture(BUSTER_HUD_FILLED, x, y += 2, 14, 2);

        // Draw bottom
        if (isMegaBuster)
            drawContext.drawGuiTexture(BUSTER_HUD_BASE, x, y, 14, 3);
        else
            drawContext.drawGuiTexture(X_HUD_BASE, x, y, 14, 16);
    }
}

