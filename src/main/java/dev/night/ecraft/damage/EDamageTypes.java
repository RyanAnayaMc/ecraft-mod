package dev.night.ecraft.damage;

import dev.night.ecraft.Ecraft;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class EDamageTypes {
    public static final RegistryKey<DamageType> CALIBURN_BLAST = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Ecraft.MOD_ID, "caliburn_blast"));
    public static final RegistryKey<DamageType> MEGA_BUSTER = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Ecraft.MOD_ID, "mega_buster"));
    public static final RegistryKey<DamageType> WIND_STAFF = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Ecraft.MOD_ID, "wind_staff"));
    public static final RegistryKey<DamageType> FIRE_STAFF = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Ecraft.MOD_ID, "fire_staff"));
    public static final RegistryKey<DamageType> ICE_STAFF = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Ecraft.MOD_ID, "ice_staff"));
    public static final RegistryKey<DamageType> LIGHTNING_STAFF = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Ecraft.MOD_ID, "lightning_staff"));
    public static final RegistryKey<DamageType> CHAOS_STATE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(Ecraft.MOD_ID, "chaos_state"));

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }

    public static void initializeDamageTypes() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing damage types");
    }
}
