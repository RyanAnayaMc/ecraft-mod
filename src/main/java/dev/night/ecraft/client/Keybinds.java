package dev.night.ecraft.client;

import com.vicmatskiv.pointblank.client.ClientEventHandler;
import com.vicmatskiv.pointblank.crafting.PointBlankIngredient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class Keybinds {
    // stealing keybind from vic's point blank
    public static final KeyBinding RELOAD_KEYBIND = ClientEventHandler.RELOAD_KEY.get();
            /*
            new KeyBinding(
            "keybinds.ecraft.reload",
            InputUtil.Type.KEYSYM,
            InputUtil.GLFW_KEY_R,
            "eeeeeecraft"
    );*/
}
