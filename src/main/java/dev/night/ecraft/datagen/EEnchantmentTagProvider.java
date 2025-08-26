package dev.night.ecraft.datagen;

import dev.night.ecraft.enchantments.EEnchantments;
import dev.night.ecraft.tags.ETags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class EEnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {
    public EEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ETags.Enchantments.RANGE_EXCLUSIVE_SET)
                .add(EEnchantments.EXTENDER)
                .add(EEnchantments.TIPPER);

        getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
                .add(EEnchantments.EXTENDER)
                .add(EEnchantments.TIPPER);
    }
}
