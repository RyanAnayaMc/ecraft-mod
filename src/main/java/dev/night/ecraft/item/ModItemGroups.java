package dev.night.ecraft.item;

import dev.night.ecraft.Ecraft;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup ECRAFT_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Ecraft.MOD_ID, "ecraft"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.ecraft"))
                    .icon(() -> new ItemStack(ModItems.CINCINNASITE_DIAMOND_INGOT)).entries(((displayContext, entries) -> {
                        entries.add(ModItems.CINCINNASITE_DIAMOND_INGOT);
                        entries.add(ModItems.CINCINNASITE_DIAMOND_NUGGET);
                        entries.add(ModArmor.CINCINNASITE_DIAMOND_HELMET);
                        entries.add(ModArmor.CINCINNASITE_DIAMOND_CHESTPLATE);
                        entries.add(ModArmor.CINCINNASITE_DIAMOND_LEGGINGS);
                        entries.add(ModArmor.CINCINNASITE_DIAMOND_BOOTS);
                    })).build());

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " registering item groups");
    }
}
