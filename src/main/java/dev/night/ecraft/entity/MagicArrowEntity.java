package dev.night.ecraft.entity;

import com.google.common.collect.Lists;
import dev.night.ecraft.item.EItems;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class MagicArrowEntity extends PersistentProjectileEntity {
    @Nullable
    private IntOpenHashSet piercedEntities;
    @Nullable
    private List<Entity> piercingKilledEntities;

    private int existenceTicks = 140;

    public MagicArrowEntity(EntityType<? extends MagicArrowEntity> entityType, World world) {
        super(entityType, world);
        this.setNoGravity(true);
        this.pickupType = PickupPermission.DISALLOWED;
    }

    public MagicArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(EEntities.MAGIC_ARROW, owner, world, stack, shotFrom);
        this.setNoGravity(true);
        this.pickupType = PickupPermission.DISALLOWED;
    }

    public MagicArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(EEntities.MAGIC_ARROW, x, y, z, world, stack, shotFrom);
        this.setNoGravity(true);
        this.pickupType = PickupPermission.DISALLOWED;
    }

    @Override
    public void tick() {
        super.tick();

        existenceTicks--;
        if (existenceTicks < 0)
            this.discard();
        if (this.getWorld().isClient && !this.inGround) {
            this.getWorld().addParticle(ParticleTypes.ENCHANT, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    public byte getPierceLevel() {
        return Byte.MAX_VALUE;
    }

    // Overridden just to make the damage count as magic damage lol
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
                this.piercingKilledEntities = Lists.<Entity>newArrayListWithCapacity(Byte.MAX_VALUE + 1);
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
                if (!this.getWorld().isClient && this.getPierceLevel() <= 0) {
                    livingEntity2.setStuckArrowCount(livingEntity2.getStuckArrowCount() + 1);
                }

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

                if (!this.getWorld().isClient && entity2 instanceof ServerPlayerEntity serverPlayerEntity) {
                    if (this.piercingKilledEntities != null && this.isShotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, this.piercingKilledEntities);
                    } else if (!entity.isAlive() && this.isShotFromCrossbow()) {
                        Criteria.KILLED_BY_CROSSBOW.trigger(serverPlayerEntity, Arrays.asList(entity));
                    }
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

    // Shadowed from original class
    private void clearPiercingStatus() {
        if (this.piercingKilledEntities != null) {
            this.piercingKilledEntities.clear();
        }

        if (this.piercedEntities != null) {
            this.piercedEntities.clear();
        }
    }

    @Override
    protected boolean canHit(Entity entity) {
        return super.canHit(entity) && (this.piercedEntities == null || !this.piercedEntities.contains(entity.getId()));
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(EItems.MAGIC_ARROW);
    }
}
