package dev.night.ecraft.entity;

import dev.night.ecraft.item.EItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MegaBusterChargeShot3Entity extends MegaBusterChargeShotEntity {

    public MegaBusterChargeShot3Entity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override protected ItemStack getDefaultItemStack() {
        return EItems.MEGA_BUSTER_SHOT_4.getDefaultStack();
    }
}
