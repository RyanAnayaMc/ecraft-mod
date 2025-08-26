package dev.night.ecraft.client.hud;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.night.ecraft.Ecraft;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class ManaHudOverlay implements HudRenderCallback {
    public static final Identifier MANA_STAR_EMPTY = Identifier.of(Ecraft.MOD_ID, "hud/mana/mana_star_empty");
    public static final Identifier MANA_STAR_HALF = Identifier.of(Ecraft.MOD_ID, "hud/mana/mana_star_half");
    public static final Identifier MANA_STAR_FULL = Identifier.of(Ecraft.MOD_ID, "hud/mana/mana_star_full");
    public static final Identifier GREEN_MANA_STAR_HALF = Identifier.of(Ecraft.MOD_ID, "hud/mana/green_mana_star_half");
    public static final Identifier GREEN_MANA_STAR_FULL = Identifier.of(Ecraft.MOD_ID, "hud/mana/green_mana_star_full");
    public static final Identifier PURPLE_MANA_STAR_HALF = Identifier.of(Ecraft.MOD_ID, "hud/mana/purple_mana_star_half");
    public static final Identifier PURPLE_MANA_STAR_FULL = Identifier.of(Ecraft.MOD_ID, "hud/mana/purple_mana_star_full");

    @Override
    public void onHudRender(DrawContext drawContext, RenderTickCounter tickCounter) {
        if (!Ecraft.CONFIG.displayManaHud())
            return;

        MinecraftClient client = MinecraftClient.getInstance();
        int x = 0;
        int y = 0;

        ClientPlayerEntity player = client.player;

        if (player instanceof ManaUsingEntity manaUsingEntity && !player.isCreative()) {
            x = drawContext.getScaledWindowWidth() / 2 - 91 + Ecraft.CONFIG.manaHudOffsetX();
            y = drawContext.getScaledWindowHeight() - 49 + Ecraft.CONFIG.manaHudOffsetY();

            if (player.getArmor() > 0)
                y -= 10;

            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.enableDepthTest();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShaderColor(1, 1, 1, 1);

            float entityMana = manaUsingEntity.manaattributes$getMana();

            // An odd number means a half star will be drawn
            int manaStarsIndicator = (int) (entityMana * 2 / 10);
            int greenStarsIndicator = manaStarsIndicator - 20;
            int purpleStarsIndicator = greenStarsIndicator - 20;

            // forcibly rounded up
            int manaStarsToDraw = Math.min((int) ((manaUsingEntity.manaattributes$getMaxMana() + 9) / 10), 10);

            for (int i = 0; i < manaStarsToDraw; i ++) {
                if ((i + 1) * 2 <= purpleStarsIndicator)
                    drawContext.drawGuiTexture(PURPLE_MANA_STAR_FULL, x + i * 8, y, 9, 9);
                else if (i * 2 + 1 <= purpleStarsIndicator)
                    drawContext.drawGuiTexture(PURPLE_MANA_STAR_HALF, x + i * 8, y, 9, 9);
                else if ((i + 1) * 2 <= greenStarsIndicator)
                    drawContext.drawGuiTexture(GREEN_MANA_STAR_FULL, x + i * 8, y, 9, 9);
                else if (i * 2 + 1 <= greenStarsIndicator)
                    drawContext.drawGuiTexture(GREEN_MANA_STAR_HALF, x + i * 8, y, 9, 9);
                else if ((i + 1) * 2 <= manaStarsIndicator)
                    drawContext.drawGuiTexture(MANA_STAR_FULL, x + i * 8, y, 9, 9);
                else if (i * 2 + 1 <= manaStarsIndicator)
                    drawContext.drawGuiTexture(MANA_STAR_HALF, x + i * 8, y, 9, 9);
                else
                    drawContext.drawGuiTexture(MANA_STAR_EMPTY, x + i * 8, y, 9, 9);
            }
        }
    }
}

