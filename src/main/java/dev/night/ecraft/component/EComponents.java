package dev.night.ecraft.component;

import dev.night.ecraft.Ecraft;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EComponents {
    public static final ComponentType<GunlanceComponent> GUNLANCE_COMPONENT = register("gunlance_component",
            ComponentType.<GunlanceComponent>builder().codec(GunlanceComponent.CODEC).build());

    public static final ComponentType<MegaBusterComponent> MEGA_BUSTER_COMPONENT = register("mega_buster_component",
            ComponentType.<MegaBusterComponent>builder().codec(MegaBusterComponent.CODEC).build());

    public static final ComponentType<DetonatorComponent> DETONATOR_COMPONENT = register("detonator_component",
            ComponentType.<DetonatorComponent>builder().codec(DetonatorComponent.CODEC).build());

    public static final ComponentType<ChargeSwordComponent> CHARGE_SWORD_COMPONENT = register("charge_sword_component",
            ComponentType.<ChargeSwordComponent>builder().codec(ChargeSwordComponent.CODEC).build());

    public static final ComponentType<ManaStarComponent> MANA_STAR_COMPONENT = register("mana_star_component",
            ComponentType.<ManaStarComponent>builder().codec(ManaStarComponent.CODEC).build());

    public static final ComponentType<StaffComponent> STAFF_COMPONENT = register("staff_component",
            ComponentType.<StaffComponent>builder().codec(StaffComponent.CODEC).build());

    public static final ComponentType<AetheriumDrillComponent> AETHERIUM_DRILL_COMPONENT = register("aetherium_drill_component",
            ComponentType.<AetheriumDrillComponent>builder().codec(AetheriumDrillComponent.CODEC).build());

    public static <T> ComponentType<T> register(String name, ComponentType<T> componentType) {
        Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Ecraft.MOD_ID, name), componentType);

        return componentType;
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing components");
    }
}
