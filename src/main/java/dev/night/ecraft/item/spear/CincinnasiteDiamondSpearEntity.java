package dev.night.ecraft.item.spear;

import dev.night.ecraft.item.ModToolMaterials;
import dev.night.ecraft.item.ModWeapons;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.world.World;
import solipingen.sassot.entity.projectile.spear.SpearEntity;

public class CincinnasiteDiamondSpearEntity extends SpearEntity {
    public static final float SPEED = 2.5f;


    public CincinnasiteDiamondSpearEntity(EntityType<? extends CincinnasiteDiamondSpearEntity> entityType, World world) {
        super(entityType, world, ModToolMaterials.CINCINNASITE_DIAMOND, SPEED);
    }

    public CincinnasiteDiamondSpearEntity(LivingEntity owner, World world, ItemStack stack) {
        super(SpearEntityTypes.CINCINNASITE_DIAMOND_SPEAR_ENTITY, owner, world, stack, ModToolMaterials.CINCINNASITE_DIAMOND, SPEED);
    }

    public CincinnasiteDiamondSpearEntity(double x, double y, double z, World world, ItemStack stack) {
        super(SpearEntityTypes.CINCINNASITE_DIAMOND_SPEAR_ENTITY, x, y, z, world, stack, ModToolMaterials.CINCINNASITE_DIAMOND, SPEED);
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModWeapons.CINCINNASITE_DIAMOND_SPEAR);
    }
}
