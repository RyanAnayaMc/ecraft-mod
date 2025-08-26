package dev.night.ecraft.item.trinkets;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.night.ecraft.attributes.EEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class MedallionOfSpaceTrinketItem extends TrinketItem {
    private final int inventorySlots;

    public MedallionOfSpaceTrinketItem(Settings settings, int inventorySlots) {
        super(settings);
        this.inventorySlots = inventorySlots;
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        modifiers.put(
                EEntityAttributes.GENERIC_BACKPACK_SLOT_AMOUNT,
                new EntityAttributeModifier(slotIdentifier, inventorySlots, EntityAttributeModifier.Operation.ADD_VALUE)
        );

        return modifiers;
    }

}
