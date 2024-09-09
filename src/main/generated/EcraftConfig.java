package dev.night.ecraft.util;

import blue.endless.jankson.Jankson;
import io.wispforest.owo.config.ConfigWrapper;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EcraftConfig extends ConfigWrapper<dev.night.ecraft.util.EcraftConfigModel> {

    public final Keys keys = new Keys();

    private final Option<java.lang.Boolean> displayGunlanceHud = this.optionForKey(this.keys.displayGunlanceHud);
    private final Option<java.lang.Integer> gunlanceHudOffsetX = this.optionForKey(this.keys.gunlanceHudOffsetX);
    private final Option<java.lang.Integer> gunlanceHudOffsetY = this.optionForKey(this.keys.gunlanceHudOffsetY);

    private EcraftConfig() {
        super(dev.night.ecraft.util.EcraftConfigModel.class);
    }

    private EcraftConfig(Consumer<Jankson.Builder> janksonBuilder) {
        super(dev.night.ecraft.util.EcraftConfigModel.class, janksonBuilder);
    }

    public static EcraftConfig createAndLoad() {
        var wrapper = new EcraftConfig();
        wrapper.load();
        return wrapper;
    }

    public static EcraftConfig createAndLoad(Consumer<Jankson.Builder> janksonBuilder) {
        var wrapper = new EcraftConfig(janksonBuilder);
        wrapper.load();
        return wrapper;
    }

    public boolean displayGunlanceHud() {
        return displayGunlanceHud.value();
    }

    public void displayGunlanceHud(boolean value) {
        displayGunlanceHud.set(value);
    }

    public int gunlanceHudOffsetX() {
        return gunlanceHudOffsetX.value();
    }

    public void gunlanceHudOffsetX(int value) {
        gunlanceHudOffsetX.set(value);
    }

    public int gunlanceHudOffsetY() {
        return gunlanceHudOffsetY.value();
    }

    public void gunlanceHudOffsetY(int value) {
        gunlanceHudOffsetY.set(value);
    }


    public static class Keys {
        public final Option.Key displayGunlanceHud = new Option.Key("displayGunlanceHud");
        public final Option.Key gunlanceHudOffsetX = new Option.Key("gunlanceHudOffsetX");
        public final Option.Key gunlanceHudOffsetY = new Option.Key("gunlanceHudOffsetY");
    }
}

