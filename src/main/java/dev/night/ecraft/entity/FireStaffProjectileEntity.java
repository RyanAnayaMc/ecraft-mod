package dev.night.ecraft.entity;

import com.google.common.collect.Sets;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.damage.EDamageTypes;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.sound.ESounds;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

public class FireStaffProjectileEntity extends PersistentProjectileEntity implements FlyingItemEntity {
    private static final TrackedData<ItemStack> ITEM = DataTracker.registerData(FireStaffProjectileEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    private int lifetimeTicks;
    private float aoeRange;

    public FireStaffProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public FireStaffProjectileEntity(World world, Vec3d position, double damage, float aoeRange) {
        super(EEntities.FIRE_STAFF_PROJECTILE, world);
        this.setPosition(position);
        this.lifetimeTicks = 3;
        this.pickupType = PickupPermission.DISALLOWED;
        this.setDamage(damage);
        Ecraft.LOGGER.info("entity assigned damage "+ damage);
        this.setStack(EItems.FIRE_STAFF_PROJECTILE.getDefaultStack());
        this.setItem(EItems.FIRE_STAFF_PROJECTILE.getDefaultStack());
        this.aoeRange = aoeRange;
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
            serverWorld.playSound(null, getBlockPos(), ESounds.STAFF_FIRE_IMPACT, SoundCategory.PLAYERS);
            serverWorld.spawnParticles(ParticleTypes.FLAME, this.getX(), this.getY(), this.getZ(), 16, 0.5, 0.5, 0.5, 0.1);

            // spawning fire, shoutout to mojang because i stole the code lmao
            Set<BlockPos> set = Sets.<BlockPos>newHashSet();
            int i = 16;

            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    for (int l = 0; l < 16; l++) {
                        if (j == 0 || j == 15 || k == 0 || k == 15 || l == 0 || l == 15) {
                            double d = (double)((float)j / 15.0F * 2.0F - 1.0F);
                            double e = (double)((float)k / 15.0F * 2.0F - 1.0F);
                            double f = (double)((float)l / 15.0F * 2.0F - 1.0F);
                            double g = Math.sqrt(d * d + e * e + f * f);
                            d /= g;
                            e /= g;
                            f /= g;
                            float h = this.aoeRange * (0.7F + this.getWorld().random.nextFloat() * 0.6F) * 0.5f;
                            double m = this.getX();
                            double n = this.getY();
                            double o = this.getZ();

                            for (float p = 0.3F; h > 0.0F; h -= 0.22500001F) {
                                BlockPos blockPos = BlockPos.ofFloored(m, n, o);
                                if (!this.getWorld().isInBuildLimit(blockPos)) {
                                    break;
                                }
                                set.add(blockPos);

                                m += d * 0.3F;
                                n += e * 0.3F;
                                o += f * 0.3F;
                            }
                        }
                    }
                }
            }

            for (BlockPos blockPos2 : set) {
                if (this.random.nextInt(20) == 0
                        && this.getWorld().getBlockState(blockPos2).isAir()
                        && this.getWorld().getBlockState(blockPos2.down()).isOpaqueFullCube(this.getWorld(), blockPos2.down())) {
                    this.getWorld().setBlockState(blockPos2, AbstractFireBlock.getState(this.getWorld(), blockPos2));
                }
            }

            // Damage entities
            for (Entity entity : entities) {
                entity.damage(serverWorld.getDamageSources().create(EDamageTypes.FIRE_STAFF, this.getOwner()), (float) this.getDamage());
                if (entity instanceof LivingEntity livingEntity)
                    livingEntity.takeKnockback(0.3f, this.getX() - entity.getX(), this.getZ() - entity.getZ());
            }
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().getRandom().nextBoolean())
            this.getWorld().addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, getStack()), this.getX(), this.getY(), this.getZ(), 0, 0, 0);

        if (age >= lifetimeTicks) {
            hitsLocation(this.getPos());
            discard();
        }
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
        return EItems.FIRE_STAFF_PROJECTILE.getDefaultStack();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        this.hitsLocation(blockHitResult.getPos());
        discard();
    }
}
