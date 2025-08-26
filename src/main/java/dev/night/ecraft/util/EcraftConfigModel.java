package dev.night.ecraft.util;

import dev.night.ecraft.Ecraft;
import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.SectionHeader;

@Modmenu(modId = Ecraft.MOD_ID)
@Config(name = "ecraft-config", wrapperName = "EcraftConfig")
public class EcraftConfigModel {
    @SectionHeader("gunlance")
    public boolean displayGunlanceHud = true;
    public int gunlanceHudOffsetX = 0;
    public int gunlanceHudOffsetY = 0;
    public boolean displayMegaBusterHud = true;
    public int megaBusterHudOffsetX = 0;
    public int megaBusterHudOffsetY = 0;
    public boolean displayManaHud = true;
    public int manaHudOffsetX = 0;
    public int manaHudOffsetY = 0;
}
