package dev.night.ecraft.world.gen;

import dev.night.ecraft.Ecraft;

public class EWorldGeneration {
    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing worldgen");
        EOreGeneration.initialize();
    }
}
