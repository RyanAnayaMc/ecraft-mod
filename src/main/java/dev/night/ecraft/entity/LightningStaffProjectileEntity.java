package dev.night.ecraft.entity;

import com.google.common.collect.Lists;
import dev.night.ecraft.damage.EDamageTypes;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.sound.ESounds;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class LightningStaffProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    private static final TrackedData<ItemStack> ITEM = DataTracker.registerData(LightningStaffProjectileEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    private int lifetimeTicks;
    private float aoeRange;
    private float knockback;

    public LightningStaffProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public LightningStaffProjectileEntity(World world, Vec3d position, double damage, float aoeRange, float knockback) {
        super(EEntities.LIGHTNING_STAFF_PROJECTILE, world);
        this.setPosition(position);
        this.lifetimeTicks = 30;
        this.pickupType = PickupPermission.DISALLOWED;
        this.setDamage(damage);
        this.setStack(EItems.LIGHTNING_STAFF_PROJECTILE.getDefaultStack());
        this.setItem(EItems.LIGHTNING_STAFF_PROJECTILE.getDefaultStack());
        this.aoeRange = aoeRange;
        this.knockback = knockback;
    }

    public void setItem(ItemStack stack) {
        this.getDataTracker().set(ITEM, stack.copyWithCount(1));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.put("Item", this.getStack().encode(this.getRegistryManager()));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Item", NbtElement.COMPOUND_TYPE)) {
            this.setItem(ItemStack.fromNbt(this.getRegistryManager(), nbt.getCompound("Item")).orElseGet(this::getDefaultItemStack));
        } else {
            this.setItem(this.getDefaultItemStack());
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ITEM, this.getDefaultItemStack());
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
    }

    @Override
    public ItemStack getStack() {
        return this.getDataTracker().get(ITEM);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        hitsLocation(entityHitResult.getPos());
        this.discard();
    }

    private void hitsLocation(Vec3d position) {
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            // Get entities
            List<Entity> entities = serverWorld.getOtherEntities(
                    this.getOwner(),
                    new Box(
                            position.x - aoeRange, position.y - aoeRange, position.z - aoeRange,
                            position.x + aoeRange, position.y + aoeRange, position.z + aoeRange
                    ),
                    EntityPredicates.VALID_LIVING_ENTITY
            );

            for (Entity entity : entities) {
                entity.damage(serverWorld.getDamageSources().create(EDamageTypes.LIGHTNING_STAFF, this.getOwner()), (float) this.getDamage());
                if (entity instanceof LivingEntity livingEntity)
                    livingEntity.takeKnockback(knockback, this.getX() - entity.getX(), this.getZ() - entity.getZ());
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().getRandom().nextBoolean())
            this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, getStack()), this.getX(), this.getY(), this.getZ(), 0, 0, 0);

        if (age >= lifetimeTicks)
            discard();
    }

    @Override
    public byte getPierceLevel() {
        return 0;
    }

    @Override
    protected float getDragInWater() {
        return 1f;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return EItems.LIGHTNING_STAFF_PROJECTILE.getDefaultStack();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.hitsLocation(blockHitResult.getPos());
        discard();
    }
}
