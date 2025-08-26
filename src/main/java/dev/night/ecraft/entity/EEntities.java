package dev.night.ecraft.entity;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.client.entity.*;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EEntities {
    public static final EntityType<LightningStaffProjectileEntity> LIGHTNING_STAFF_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Ecraft.MOD_ID, "lightning_staff_projectile"),
            EntityType.Builder.<LightningStaffProjectileEntity>create(
                    LightningStaffProjectileEntity::new, SpawnGroup.MISC
            ).dimensions(.5f, .5f).maxTrackingRange(4).trackingTickInterval(10).build("lightning_staff_projectile")
    );

    public static final EntityType<FireStaffProjectileEntity> FIRE_STAFF_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Ecraft.MOD_ID, "fire_staff_projectile"),
            EntityType.Builder.<FireStaffProjectileEntity>create(
                    FireStaffProjectileEntity::new, SpawnGroup.MISC
            ).dimensions(.5f, .5f).maxTrackingRange(4).trackingTickInterval(10).build("fire_staff_projectile")
    );

    public static final EntityType<StaffProjectileEntity> STAFF_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Ecraft.MOD_ID, "staff_projectile"),
            EntityType.Builder.<StaffProjectileEntity>create(
                    StaffProjectileEntity::new, SpawnGroup.MISC
            ).dimensions(.5f, .5f).maxTrackingRange(4).trackingTickInterval(10).build("staff_projectile")
    );

    public static final EntityType<MegaBusterLemonEntity> MEGA_BUSTER_LEMON = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Ecraft.MOD_ID, "mega_buster_lemon"),
            EntityType.Builder.<MegaBusterLemonEntity>create(
                    MegaBusterLemonEntity::new, SpawnGroup.MISC
            ).dimensions(.5f, .5f).build("mega_buster_lemon")
    );

    public static final EntityType<MegaBusterChargeShotEntity> MEGA_BUSTER_CHARGE_SHOT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Ecraft.MOD_ID, "mega_buster_charge_shot"),
            EntityType.Builder.<MegaBusterChargeShotEntity>create(
                    MegaBusterChargeShotEntity::new, SpawnGroup.MISC
            ).dimensions(.5f, .5f).build("mega_buster_charge_shot")
    );

    public static final EntityType<MegaBusterChargeShot2Entity> MEGA_BUSTER_CHARGE_SHOT_2 = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Ecraft.MOD_ID, "mega_buster_charge_shot_2"),
            EntityType.Builder.<MegaBusterChargeShot2Entity>create(
                    MegaBusterChargeShot2Entity::new, SpawnGroup.MISC
            ).dimensions(1.5f, 1.5f).build("mega_buster_charge_shot_2")
    );

    public static final EntityType<MegaBusterChargeShot3Entity> MEGA_BUSTER_CHARGE_SHOT_3 = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Ecraft.MOD_ID, "mega_buster_charge_shot_3"),
            EntityType.Builder.<MegaBusterChargeShot3Entity>create(
                    MegaBusterChargeShot3Entity::new, SpawnGroup.MISC
            ).dimensions(1.5f, 1.5f).build("mega_buster_charge_shot_3")
    );

    public static final EntityType<MagicArrowEntity> MAGIC_ARROW = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Ecraft.MOD_ID, "magic_arrow"),
            EntityType.Builder.<MagicArrowEntity>create(
                    MagicArrowEntity::new, SpawnGroup.MISC
            ).dimensions(0.5f, 0.5f).eyeHeight(0.13F).maxTrackingRange(4).trackingTickInterval(20)
                    .build("magic_arrow")
    );

    public static void initialize() {
        Ecraft.LOGGER.info("Mod " + Ecraft.MOD_ID + " initializing entities");
    }

    public static void initializeClient() {
        EntityRendererRegistry.register(MEGA_BUSTER_LEMON, MegaBusterLemonRenderer::new);
        EntityRendererRegistry.register(MEGA_BUSTER_CHARGE_SHOT, MegaBusterChargeShotRenderer::new);
        EntityRendererRegistry.register(MEGA_BUSTER_CHARGE_SHOT_2, MegaBusterChargeShot2Renderer::new);
        EntityRendererRegistry.register(MEGA_BUSTER_CHARGE_SHOT_3, MegaBusterChargeShot3Renderer::new);
        EntityRendererRegistry.register(STAFF_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(FIRE_STAFF_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(LIGHTNING_STAFF_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(MAGIC_ARROW, MagicArrowEntityRenderer::new);
    }
}
