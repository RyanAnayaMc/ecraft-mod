package dev.night.ecraft.item;

import com.provismet.AdditionalArmoury.items.DaggerItem;
import com.provismet.AdditionalArmoury.registries.AADataComponentTypes;
import dev.night.ecraft.Ecraft;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.*;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Arrays;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> ECRAFT_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Ecraft.MOD_ID, "ecraft"));

    public static final ItemGroup ECRAFT_GROUP = Registry.register(Registries.ITEM_GROUP,
            ECRAFT_GROUP_KEY,
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ecraft"))
                    .icon(() -> new ItemStack(ModItems.CINCINNASITE_DIAMOND_INGOT))
                    .entries(((displayContext, entries) -> {



                    })).build());

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering item groups");

        ItemGroupEvents.modifyEntriesEvent(ECRAFT_GROUP_KEY).register(
                entries -> {
                    // Add items
                    entries.add(ModItems.SHARD_OF_TRAVEL);
                    entries.add(ModItems.RAW_DIAMOND);
                    entries.add(Items.DIAMOND);
                    entries.add(ModBlocks.RAW_DIAMOND_BLOCK);
                    entries.add(ModItems.CINCINNASITE_DIAMOND_INGOT);
                    entries.add(ModItems.CINCINNASITE_DIAMOND_NUGGET);
                    entries.add(ModBlocks.CINCINNASITE_DIAMOND_BLOCK);
                    entries.add(ModItems.TERRASTEEL_INGOT);
                    entries.add(ModItems.TERRASTEEL_NUGGET);
                    entries.add(ModBlocks.TERRASTEEL_BLOCK);

                    // Add fishing rods
                    entries.add(ModWeapons.CINCINNASITE_DIAMOND_FUSED_FISHING_ROD);
                    entries.add(ModWeapons.CARROT_ON_A_CINCINNASITE_DIAMOND_FUSED_STICK);
                    entries.add(ModWeapons.WARPED_FUNGUS_ON_A_CINCINNASITE_DIAMOND_FUSED_STICK);

                    // Add armor
                    entries.add(ModArmor.CINCINNASITE_DIAMOND_HELMET);
                    entries.add(ModArmor.CINCINNASITE_DIAMOND_CHESTPLATE);
                    entries.add(ModArmor.CINCINNASITE_DIAMOND_LEGGINGS);
                    entries.add(ModArmor.CINCINNASITE_DIAMOND_BOOTS);
                    entries.add(ModArmor.TERRASTEEL_HELMET);
                    entries.add(ModArmor.TERRASTEEL_CHESTPLATE);
                    entries.add(ModArmor.TERRASTEEL_LEGGINGS);
                    entries.add(ModArmor.TERRASTEEL_BOOTS);

                    // Add swords
                    entries.add(ModWeapons.TERRASTEEL_SWORD);

                    // Add axes
                    entries.add(ModWeapons.TERRASTEEL_AXE);

                    // Add pickaxes
                    entries.add(ModWeapons.TERRASTEEL_PICKAXE);

                    // Add hoes
                    entries.add(ModWeapons.TERRASTEEL_HOE);

                    // Add shovels
                    entries.add(ModWeapons.TERRASTEEL_SHOVEL);

                    // Add spears
                    entries.add(ModWeapons.CINCINNASITE_DIAMOND_SPEAR);
                    entries.add(ModWeapons.METALLURGIUM_SPEAR);

                    // Add shields
                    entries.add(ModWeapons.CINCINNASITE_DIAMOND_SHIELD);
                    entries.add(ModWeapons.CINCINNASITE_DIAMOND_FRAMED_WOODEN_SHIELD);

                    // Add maces
                    entries.add(ModWeapons.CINCINNASITE_DIAMOND_MACE);

                    // Add daggers
                    daggerHandler(entries, ModWeapons.CINCINNASITE_DIAMOND_DAGGER);
                }
        );
    }

    private static void daggerHandler(FabricItemGroupEntries content, Item dagger) {
        content.add(dagger);
        content.getContext().lookup().getOptionalWrapper(RegistryKeys.POTION).ifPresent((wrapper) -> {
            addPotions(content, wrapper, dagger, ItemGroup.StackVisibility.PARENT_AND_SEARCH_TABS, content.getEnabledFeatures());
        });
    }

    private static void addPotions(ItemGroup.Entries entries, RegistryWrapper<Potion> registryWrapper, Item item, ItemGroup.StackVisibility visibility, FeatureSet enabledFeatures) {
        registryWrapper.streamEntries().filter((entry) -> {
            return ((Potion)entry.value()).isEnabled(enabledFeatures);
        }).map((entry) -> {
            return PotionContentsComponent.createStack(item, entry);
        }).peek((entry) -> {
            entry.set(AADataComponentTypes.USES, 64);
        }).forEach((stack) -> {
            entries.add(stack, visibility);
        });
    }
}
