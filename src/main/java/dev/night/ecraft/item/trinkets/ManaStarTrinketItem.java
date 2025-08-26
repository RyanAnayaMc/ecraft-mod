package dev.night.ecraft.item.trinkets;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.night.ecraft.attributes.EEntityAttributes;
import dev.night.ecraft.component.ManaStarComponent;
import dev.night.ecraft.component.EComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ManaStarTrinketItem extends TrinketItem {
    public ManaStarTrinketItem(Settings settings) {
        super(settings.component(EComponents.MANA_STAR_COMPONENT, getDefaultComponent()));
    }

    public ManaStarTrinketItem(Settings settings, int baseMana) {
        super(settings.component(EComponents.MANA_STAR_COMPONENT, new ManaStarComponent(baseMana)));
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        modifiers.put(
                EEntityAttributes.GENERIC_MAX_MANA,
                new EntityAttributeModifier(slotIdentifier, stack.getOrDefault(EComponents.MANA_STAR_COMPONENT, getDefaultComponent()).mana(), EntityAttributeModifier.Operation.ADD_VALUE)
        );

        return modifiers;
    }

    public static ManaStarComponent getDefaultComponent() {
        return new ManaStarComponent((byte) 10);
    }
}
