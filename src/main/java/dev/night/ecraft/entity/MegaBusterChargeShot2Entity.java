package dev.night.ecraft.entity;

import dev.night.ecraft.item.tools.weapons.MegaBusterItem;
import dev.night.ecraft.item.EItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MegaBusterChargeShot2Entity extends MegaBusterChargeShotEntity {

    public MegaBusterChargeShot2Entity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public byte getPierceLevel() {
        // subtract by 1 since pierce level is not number of hits but rather number of passthroughs
        return MegaBusterItem.MEGA_BUSTER_CHARGE_SHOT_MAX_HITS - 1;
    }

    @Override protected ItemStack getDefaultItemStack() {
        return EItems.MEGA_BUSTER_SHOT_3.getDefaultStack();
    }
}
