package dev.night.ecraft;

import dev.night.ecraft.attributes.EEntityAttributes;
import dev.night.ecraft.block.EBlocks;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.enchantments.EEnchantmentEffects;
import dev.night.ecraft.enchantments.EEnchantments;
import dev.night.ecraft.entity.EEntities;
import dev.night.ecraft.entity.villager.EVillagers;
import dev.night.ecraft.event.MultiMiningToolUsageEvent;
import dev.night.ecraft.item.*;
import dev.night.ecraft.item.food.EFood;
import dev.night.ecraft.item.food.EFoodComponents;
import dev.night.ecraft.loot.ELootTableInjection;
import dev.night.ecraft.potion.BrewingRecipeRegistry;
import dev.night.ecraft.potion.EPotions;
import dev.night.ecraft.recipe.ERecipeSerializers;
import dev.night.ecraft.sound.ESounds;
import dev.night.ecraft.util.EcraftConfig;
import dev.night.ecraft.world.gen.EWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Ecraft implements ModInitializer {
	public static final String MOD_ID = "ecraft";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final EcraftConfig CONFIG = EcraftConfig.createAndLoad();

	@Override
	public void onInitialize() {
		EComponents.initialize();
		EItems.initialize();
		EFoodComponents.initialize();
		EFood.initialize();
		EBlocks.initialize();
		EArmorMaterials.initialize();
		EWeapons.initialize();
		EArmor.initialize();
		EVillagers.initialize();
		EItemGroups.initialize();
		ELootTableInjection.modifyLootTables();
		ESounds.initialize();
		EEnchantmentEffects.initialize();
		EEnchantments.initialize();
		EWorldGeneration.initialize();
		EEntityAttributes.initialize();
		EEntities.initialize();
		ERecipeSerializers.initialize();
		EPotions.initialize();
		BrewingRecipeRegistry.registerBrewingRecipes();

		EVillagers.addTrades();

		PlayerBlockBreakEvents.BEFORE.register(new MultiMiningToolUsageEvent());

		FuelRegistry.INSTANCE.add(EItems.ENDLESS_LAVA_BUCKET, 20000);
	}
}