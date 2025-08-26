package dev.night.ecraft;

import dev.night.ecraft.datagen.*;
import dev.night.ecraft.enchantments.EEnchantments;
import dev.night.ecraft.world.EConfiguredFeatures;
import dev.night.ecraft.world.EPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class EcraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(EBlockTagProvider::new);
		pack.addProvider(EItemTagProvider::new);
		pack.addProvider(ELootTableProvider::new);
		pack.addProvider(EModelProvider::new);
		pack.addProvider(ERecipeProvider::new);
		pack.addProvider(ERegistryDataGenerator::new);
		pack.addProvider(EEnchantmentTagProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, EConfiguredFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, EPlacedFeatures::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, EEnchantments::bootstrap);
	}
}
