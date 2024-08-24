package dev.night.ecraft.item.spear;

import dev.night.ecraft.Ecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.EntityType.Builder;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import solipingen.sassot.entity.ModEntityTypes;
import dev.night.ecraft.item.spear.CincinnasiteDiamondSpearEntity;
import solipingen.sassot.entity.projectile.spear.DiamondSpearEntity;

public class SpearEntityTypes extends ModEntityTypes {
    public static final EntityType<CincinnasiteDiamondSpearEntity> CINCINNASITE_DIAMOND_SPEAR_ENTITY =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of(Ecraft.MOD_ID, "diamond_spear"),
                    EntityType.Builder.<CincinnasiteDiamondSpearEntity>create(
                            CincinnasiteDiamondSpearEntity::new, SpawnGroup.MISC
                    ).dimensions(0.5f, 0.5f)
            .build());

    public static final EntityType<MetallurgiumSpearEntity> METALLURGIUM_SPEAR_ENTITY =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of(Ecraft.SASSOT_ID, "metallurgium_spear"),
                    EntityType.Builder.<MetallurgiumSpearEntity>create(
                            MetallurgiumSpearEntity::new, SpawnGroup.MISC
                    ).dimensions(0.5f, 0.5f)
                            .build());

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initialzing entities");
    }
}
