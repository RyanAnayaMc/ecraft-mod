package dev.night.ecraft;

import dev.night.ecraft.item.*;
import dev.night.ecraft.item.spear.SpearEntityTypes;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Ecraft implements ModInitializer {
	public static final String MOD_ID = "ecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.initialize();
		ModBlocks.initialize();
		ModArmorMaterials.initialize();
		SpearEntityTypes.initialize();
		ModWeapons.initialize();
		ModArmor.initialize();
		ModItemGroups.initialize();
	}
}