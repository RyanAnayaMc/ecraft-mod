package dev.night.ecraft.sound;

import dev.night.ecraft.Ecraft;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent GUNLANCE_EMPTY = register("gunlance_empty");
    public static final RegistryEntry.Reference<SoundEvent> GUNLANCE_FIRE = registerReference("gunlance_fire");
    public static final SoundEvent GUNLANCE_RELOAD = register("gunlance_reload");
    public static final SoundEvent GUNLANCE_CHARGE = register("gunlance_charge");
    public static final RegistryEntry.Reference<SoundEvent> GUNLANCE_CHARGE_FIRE = registerReference("gunlance_charge_fire");

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
