package dev.night.ecraft.entity;

import com.google.common.collect.Lists;
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
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class StaffProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    private static final TrackedData<ItemStack> ITEM = DataTracker.registerData(StaffProjectileEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    private int lifetimeTicks;
    private byte pierceLevel;
    private IntOpenHashSet piercedEntities;
    private List<Entity> piercingKilledEntities;

    public StaffProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public StaffProjectileEntity(World world, Vec3d position, int lifetimeTicks, byte pierceLevel, double damage, Item item) {
        super(EEntities.STAFF_PROJECTILE, world);
        this.setPosition(position);
        this.lifetimeTicks = lifetimeTicks;
        this.pickupType = PickupPermission.DISALLOWED;
        this.pierceLevel = pierceLevel;
        this.setSound(ESounds.STAFF_IMPACT);
        this.setDamage(damage);
        this.setStack(item.getDefaultStack());
        this.setItem(item.getDefaultStack());
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
    public ItemStack getStack() {
        return this.getDataTracker().get(ITEM);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        float f = (float)this.getVelocity().length();
        double d = this.getDamage();
        Entity entity2 = this.getOwner();
        DamageSource damageSource = this.getDamageSources().indirectMagic(this, (Entity)(entity2 != null ? entity2 : this));
        if (this.getWeaponStack() != null && this.getWorld() instanceof ServerWorld serverWorld) {
            d = (double) EnchantmentHelper.getDamage(serverWorld, this.getWeaponStack(), entity, damageSource, (float)d);
        }

        int i = MathHelper.ceil(MathHelper.clamp((double)f * d, 0.0, 2.147483647E9));
        if (this.getPierceLevel() > 0) {
            if (this.piercedEntities == null) {
                this.piercedEntities = new IntOpenHashSet(5);
            }

            if (this.piercingKilledEntities == null) {
                this.piercingKilledEntities = Lists.<Entity>newArrayListWithCapacity(5);
            }

            if (this.piercedEntities.size() >= this.getPierceLevel() + 1) {
                this.discard();
                return;
            }

            this.piercedEntities.add(entity.getId());
        }

        if (this.isCritical()) {
            long l = (long)this.random.nextInt(i / 2 + 2);
            i = (int)Math.min(l + (long)i, 2147483647L);
        }

        if (entity2 instanceof LivingEntity livingEntity) {
            livingEntity.onAttacking(entity);
        }

        boolean bl = entity.getType() == EntityType.ENDERMAN;
        int j = entity.getFireTicks();
        if (this.isOnFire() && !bl) {
            entity.setOnFireFor(5.0F);
        }

        if (entity.damage(damageSource, (float)i)) {
            if (bl) {
                return;
            }

            if (entity instanceof LivingEntity livingEntity2) {
                this.knockback(livingEntity2, damageSource);
                if (this.getWorld() instanceof ServerWorld serverWorld2) {
                    EnchantmentHelper.onTargetDamaged(serverWorld2, livingEntity2, damageSource, this.getWeaponStack());
                }

                this.onHit(livingEntity2);
                if (livingEntity2 != entity2 && livingEntity2 instanceof PlayerEntity && entity2 instanceof ServerPlayerEntity && !this.isSilent()) {
                    ((ServerPlayerEntity)entity2)
                            .networkHandler
                            .sendPacket(new GameStateChangeS2CPacket(GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER, GameStateChangeS2CPacket.DEMO_OPEN_SCREEN));
                }

                if (!entity.isAlive() && this.piercingKilledEntities != null) {
                    this.piercingKilledEntities.add(livingEntity2);
                }
            }

            this.playSound(this.getSound(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            if (this.getPierceLevel() <= 0) {
                this.discard();
            }
        } else {
            entity.setFireTicks(j);
            this.deflect(ProjectileDeflection.SIMPLE, entity, this.getOwner(), false);
            this.setVelocity(this.getVelocity().multiply(0.2));
            if (!this.getWorld().isClient && this.getVelocity().lengthSquared() < 1.0E-7) {
                if (this.pickupType == PersistentProjectileEntity.PickupPermission.ALLOWED) {
                    this.dropStack(this.asItemStack(), 0.1F);
                }

                this.discard();
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().getRandom().nextBoolean())
            this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, getStack()), this.getX(), this.getY(), this.getZ(), 0, 0, 0);

        if (age >= 180)
            discard();
    }

    @Override
    public byte getPierceLevel() {
        return pierceLevel;
    }

    @Override
    protected float getDragInWater() {
        return 1f;
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return EItems.MEGA_BUSTER_SHOT_2.getDefaultStack();
    }

    private void clearPiercingStatus() {
        if (this.piercingKilledEntities != null) {
            this.piercingKilledEntities.clear();
        }

        if (this.piercedEntities != null) {
            this.piercedEntities.clear();
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        clearPiercingStatus();
        discard();
    }

    @Override
    protected boolean canHit(Entity entity) {
        return super.canHit(entity) && (this.piercedEntities == null || !this.piercedEntities.contains(entity.getId()));
    }
}
