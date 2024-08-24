package dev.night.ecraft.item.spear;

import dev.night.ecraft.item.ModToolMaterials;
import dev.night.ecraft.item.ModWeapons;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import nourl.mythicmetals.item.tools.MythicToolMaterials;
import solipingen.sassot.entity.projectile.spear.SpearEntity;

public class MetallurgiumSpearEntity extends SpearEntity {
    public static final float SPEED = 2.5f;


    public MetallurgiumSpearEntity(EntityType<? extends MetallurgiumSpearEntity> entityType, World world) {
        super(entityType, world, MythicToolMaterials.METALLURGIUM, SPEED);
    }

    public MetallurgiumSpearEntity(LivingEntity owner, World world, ItemStack stack) {
        super(SpearEntityTypes.METALLURGIUM_SPEAR_ENTITY, owner, world, stack, MythicToolMaterials.METALLURGIUM, SPEED);
    }

    public MetallurgiumSpearEntity(double x, double y, double z, World world, ItemStack stack) {
        super(SpearEntityTypes.METALLURGIUM_SPEAR_ENTITY, x, y, z, world, stack, MythicToolMaterials.METALLURGIUM, SPEED);
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(ModWeapons.METALLURGIUM_SPEAR);
    }
}
