package dev.night.ecraft.util;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.EWeapons;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.item.tools.weapons.ChargeSwordItem;
import dev.night.ecraft.item.tools.DetonatorItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class EModelPredicates {
    public static void initialize() {
        ModelPredicateProviderRegistry.register(EItems.DETONIUM_DETONATOR, Identifier.of(Ecraft.MOD_ID, "charge"),
                (stack, world, entity, seed) -> (float) stack.getOrDefault(EComponents.DETONATOR_COMPONENT, DetonatorItem.getDefaultComponent()).chargeLocations().size() / 3f);

        ModelPredicateProviderRegistry.register(EWeapons.CALIBURN, Identifier.of(Ecraft.MOD_ID, "charge"),
                (stack, world, entity, seed) -> (float) stack.getOrDefault(EComponents.CHARGE_SWORD_COMPONENT, ChargeSwordItem.getDefaultComponent()).charge() / ChargeSwordItem.MAX_CHARGE);

        ModelPredicateProviderRegistry.register(EWeapons.SOLAIS, Identifier.of(Ecraft.MOD_ID, "charge"),
                (stack, world, entity, seed) -> (float) stack.getOrDefault(EComponents.CHARGE_SWORD_COMPONENT, ChargeSwordItem.getDefaultComponent()).charge() / ChargeSwordItem.MAX_CHARGE);

        ModelPredicateProviderRegistry.register(EWeapons.DURENDAL, Identifier.of(Ecraft.MOD_ID, "charge"),
                (stack, world, entity, seed) -> (float) stack.getOrDefault(EComponents.CHARGE_SWORD_COMPONENT, ChargeSwordItem.getDefaultComponent()).charge() / ChargeSwordItem.MAX_CHARGE);
    }
}
