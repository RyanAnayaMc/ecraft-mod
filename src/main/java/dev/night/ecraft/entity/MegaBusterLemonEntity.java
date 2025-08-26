package dev.night.ecraft.entity;

import dev.night.ecraft.damage.EDamageTypes;
import dev.night.ecraft.item.tools.weapons.MegaBusterItem;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.sound.ESounds;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class MegaBusterLemonEntity extends PersistentProjectileEntity {
    private int existedTicks;

    public MegaBusterLemonEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        existedTicks = 0;
        this.pickupType = PickupPermission.DISALLOWED;
        this.setSound(ESounds.MEGA_BUSTER_HIT);
    }

    public int getExistedTicks() {
        return existedTicks;
    }

    @Override
    public void tick() {
        super.tick();
        // Increment counter of ticks this projectile existed
        existedTicks++;

        // If it existed too long, discard it
        if (existedTicks > MegaBusterItem.MEGA_BUSTER_PROJECTILE_LIFETIME_TICKS) {
            discard();
            return;
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        this.discard();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity target = entityHitResult.getEntity();
        Entity owner = this.getOwner();

// Apply custom damage
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = (LivingEntity) target;

            // Create a custom damage source using the RegistryKey from EDamageTypes
            DamageSource damageSource = new DamageSource(
                    this.getWorld().getRegistryManager()
                            .get(RegistryKeys.DAMAGE_TYPE)
                            .getEntry(EDamageTypes.MEGA_BUSTER) // Use the RegistryKey
                            .get(),
                    this, // The projectile itself
                    owner // The entity that shot the projectile
            );

            // Apply damage to the target
            livingTarget.damage(damageSource, (float) getDamage());

            // Play hit sound (optional)
            this.playSound(this.getSound(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        }

        // Discard the projectile after hitting an entity
        this.discard();
    }

    @Override
    protected float getDragInWater() {
        return 1f;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(EItems.MEGA_BUSTER_SHOT_1);
    }
}
