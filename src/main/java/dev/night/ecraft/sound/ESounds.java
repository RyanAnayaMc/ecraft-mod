package dev.night.ecraft.sound;

import dev.night.ecraft.Ecraft;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ESounds {
    // Staff sound effects
    public static final SoundEvent STAFF_IMPACT = register("staff_impact");
    public static final SoundEvent STAFF_CAST = register("staff_cast");
    public static final SoundEvent STAFF_FIRE_FIRE = register("staff_fire_fire");
    public static final SoundEvent STAFF_FIRE_IMPACT = register("staff_fire_impact");
    public static final SoundEvent STAFF_WIND_FIRE = register("staff_wind_fire");
    public static final SoundEvent STAFF_ICE_FIRE = register("staff_ice_fire");
    public static final SoundEvent STAFF_LIGHTNING_FIRE = register("staff_lightning_fire");
    public static final SoundEvent STAFF_SWING = register("staff_swing");

    // Gunlance sound effects
    public static final SoundEvent GUNLANCE_EMPTY = register("gunlance_empty");
    public static final RegistryEntry.Reference<SoundEvent> GUNLANCE_FIRE = registerReference("gunlance_fire");
    public static final SoundEvent GUNLANCE_RELOAD = register("gunlance_reload");
    public static final SoundEvent GUNLANCE_CHARGE = register("gunlance_charge");
    public static final RegistryEntry.Reference<SoundEvent> GUNLANCE_CHARGE_FIRE = registerReference("gunlance_charge_fire");

    // Mega Buster sound effects
    public static final SoundEvent MEGA_BUSTER_SHOT = register("mega_buster_shot");
    public static final SoundEvent MEGA_BUSTER_HIT = register("mega_buster_hit");
    public static final SoundEvent MEGA_BUSTER_RECHARGE = register("mega_buster_recharge");
    public static final SoundEvent MEGA_BUSTER_CHARGING_START = register("mega_buster_charging_start");
    public static final SoundEvent MEGA_BUSTER_CHARGING_LOOP = register("mega_buster_charging_loop");
    public static final SoundEvent MEGA_BUSTER_CHARGE_SHOT = register("mega_buster_charge_shot");

    // X Buster Sound Effects
    public static final SoundEvent X_BUSTER_SHOT = register("x_buster_shot");
    public static final SoundEvent X_BUSTER_CHARGE_SHOT = register("x_buster_charge_shot");
    public static final SoundEvent X_BUSTER_SUPER_CHARGE_SHOT = register("x_buster_super_charge_shot");
    public static final SoundEvent X_BUSTER_HIT_BLOCK = register("x_buster_hit_block");
    public static final SoundEvent X_BUSTER_HIT_ENTITY = register("x_buster_hit_entity");
    public static final SoundEvent X_BUSTER_RECHARGE = register("x_buster_recharge");
    public static final SoundEvent X_BUSTER_RECHARGE_FULL = register("x_buster_recharge_full");
    public static final SoundEvent X_BUSTER_CHARGING_START = register("x_buster_charge_start");
    public static final SoundEvent X_BUSTER_CHARGING_LOOP = register("x_buster_charge_loop");

    // Bastard Sword Sound Effects
    public static final SoundEvent CALIBURN_FULLY_CHARGED = register("caliburn_charged");
    public static final SoundEvent CALIBURN_SUPER = register("caliburn_super");
    public static final SoundEvent SOLAIS_FULLY_CHARGED = register("solais_charged");
    public static final SoundEvent SOLAIS_HEAL = register("solais_heal");
    public static final SoundEvent SOLAIS_SUPER = register("solais_super");

    public static SoundEvent register(String name) {
        Identifier id = Identifier.of(Ecraft.MOD_ID, name);
        SoundEvent ev = SoundEvent.of(id);
        return Registry.register(Registries.SOUND_EVENT, id, ev);
    }

    public static RegistryEntry.Reference<SoundEvent> registerReference(String name) {
        Identifier id = Identifier.of(Ecraft.MOD_ID, name);
        return Registry.registerReference(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " adding sounds");
    }
}
