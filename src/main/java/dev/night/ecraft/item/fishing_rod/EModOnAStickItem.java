package dev.night.ecraft.item.fishing_rod;

import dev.night.ecraft.item.ModToolMaterials;
import dev.night.ecraft.item.ModWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import solipingen.sassot.item.ModOnAStickItem;

public class EModOnAStickItem<T extends Entity> extends ModOnAStickItem<T> {

    private final ToolMaterial material;

    public EModOnAStickItem(Settings settings, EntityType<T> target, ToolMaterial material, int damagePerUse) {
        super(settings, target, material, damagePerUse);
        this.material = material;
    }

    private Item getFishingRodItem() {
        Item item = Items.FISHING_ROD;

        if (this.material == ModToolMaterials.CINCINNASITE_DIAMOND)
            item = ModWeapons.CINCINNASITE_DIAMOND_FUSED_FISHING_ROD;
        Items items;
        return item;

    }
}
