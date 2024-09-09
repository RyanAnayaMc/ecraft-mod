package dev.night.ecraft;

import dev.night.ecraft.block.ModBlocks;
import dev.night.ecraft.item.*;
import dev.night.ecraft.item.food.ModFood;
import dev.night.ecraft.item.food.ModFoodComponents;
import dev.night.ecraft.loot.LootTableInjection;
import dev.night.ecraft.sound.ModSounds;
import dev.night.ecraft.util.EcraftConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Ecraft implements ModInitializer {
	public static final String MOD_ID = "ecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final EcraftConfig CONFIG = EcraftConfig.createAndLoad();

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModFoodComponents.initialize();
		ModFood.initialize();
		ModBlocks.initialize();
		ModArmorMaterials.initialize();
		ModWeapons.initialize();
		ModArmor.initialize();
		ModItemGroups.initialize();
		LootTableInjection.modifyLootTables();
		ModSounds.initialize();
	}
}