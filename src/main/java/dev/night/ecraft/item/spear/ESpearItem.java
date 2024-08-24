package dev.night.ecraft.item.spear;

import dev.night.ecraft.item.ModWeapons;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import solipingen.sassot.entity.projectile.spear.*;
import solipingen.sassot.item.SpearItem;

public class ESpearItem extends SpearItem {

    public ESpearItem(ToolMaterial material, Item.Settings settings) {
        super(material, settings);
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        SpearEntity spearEntity = new WoodenSpearEntity(pos.getX(), pos.getY(), pos.getZ(), world, stack.copyWithCount(1));
        if (stack.isOf(ModWeapons.CINCINNASITE_DIAMOND_SPEAR)) {
            spearEntity = new CincinnasiteDiamondSpearEntity(pos.getX(), pos.getY(), pos.getZ(), world, stack.copyWithCount(1));
        } else if (stack.isOf(ModWeapons.METALLURGIUM_SPEAR)) {
            spearEntity = new MetallurgiumSpearEntity(pos.getX(), pos.getY(), pos.getZ(), world, stack.copyWithCount(1));
        }
        spearEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return spearEntity;
    }

}
