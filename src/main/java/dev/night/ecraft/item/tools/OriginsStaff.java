package dev.night.ecraft.item.tools;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import dev.night.ecraft.damage.EDamageTypes;
import dev.night.ecraft.entity.FireStaffProjectileEntity;
import dev.night.ecraft.entity.LightningStaffProjectileEntity;
import dev.night.ecraft.item.tools.weapons.StaffItem;
import dev.night.ecraft.sound.ESounds;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.joml.Vector3f;

import java.util.List;

import static net.minecraft.item.Item.BASE_ATTACK_DAMAGE_MODIFIER_ID;
import static net.minecraft.item.Item.BASE_ATTACK_SPEED_MODIFIER_ID;

public class OriginsStaff {
    public static final Identifier BASE_MAGIC_ATTACK_DAMAGE_MODIFIER_ID = StaffItem.BASE_MAGIC_ATTACK_DAMAGE_MODIFIER_ID;

    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, float baseAttackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)(baseAttackDamage + material.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static class Wind extends ToolItem {
        private static final double WIND_STAFF_ARC_RADIANS = Math.PI / 3;
        private final int manaCost;
        private final int cooldownTicks;
        private final float knockback;
        private final float radius;
        private final float damage;

        public Wind(ToolMaterial material, Settings settings, int manaCost, int cooldownTicks, float knockback, float radius, float damage) {
            super(material, settings);
            this.manaCost = manaCost;
            this.cooldownTicks = cooldownTicks;
            this.knockback = knockback;
            this.radius = radius;
            this.damage = damage;
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            tooltip.add(
                    Text.translatable("item.ecraft.staff.magic_damage_tooltip", damage).withColor(0x5454FC)
            );
            tooltip.add(
                    Text.translatable("item.ecraft.staff.mana_tooltip", manaCost).withColor(0x5454FC)
            );
        }

        public int getManaCostForUser(PlayerEntity user) {
            if (user.isCreative())
                return 0;
            return manaCost;
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            if (world instanceof ServerWorld serverWorld
                    && user instanceof ManaUsingEntity manaUsingEntity
                    && manaUsingEntity.manaattributes$getMana() >= getManaCostForUser(user))
            {
                // Deplete mana
                manaUsingEntity.manaattributes$addMana(-getManaCostForUser(user));

                // Get entities that will be hit
                Vector3f userDirection = user.getRotationVector().toVector3f();
                List<Entity> entities = serverWorld.getOtherEntities(
                        user,
                        new Box(
                                user.getX() - radius, user.getY() - radius, user.getZ() - radius,
                                user.getX() + radius, user.getY() + radius, user.getZ() + radius
                        ),
                        entity -> {
                            // Ensure the entity is in the correct angle relative to the player
                            Vector3f vectorFromPlayerToEntity = new Vector3f((float) (entity.getX() - user.getX()), (float) (entity.getY() - user.getY()), (float) (entity.getZ() - user.getZ()));
                            return userDirection.angle(vectorFromPlayerToEntity) <= WIND_STAFF_ARC_RADIANS && entity instanceof LivingEntity;
                        }
                );

                // Affect entities
                for (Entity entity : entities) {
                    entity.damage(serverWorld.getDamageSources().create(EDamageTypes.WIND_STAFF, user), (float) damage);
                    if (entity instanceof LivingEntity livingEntity)
                        livingEntity.takeKnockback(knockback, user.getX() - entity.getX(), user.getZ() - entity.getZ());
                }

                // Spawn lots of particles
                serverWorld.spawnParticles(ParticleTypes.GUST, user.getX(), user.getY(), user.getZ(), 24, 3, 1, 3, 0.1);

                // Play sound effect
                serverWorld.playSound(null, user.getBlockPos(), ESounds.STAFF_WIND_FIRE, SoundCategory.PLAYERS);

                // Inflict cooldown
                user.getItemCooldownManager().set(this, cooldownTicks);

                return TypedActionResult.success(user.getStackInHand(hand));
            }

            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }

    public static class Ice extends ToolItem {
        private static final double ICE_STAFF_ARC_RADIANS = Math.PI / 8;
        private final int manaCost;
        private final int cooldownTicks;
        private final int slowdownTicks;
        private final int slowdownLevels;
        private final float radius;
        private final float damage;

        public Ice(ToolMaterial material, Settings settings, int manaCost, int cooldownTicks, int slowdownTicks, int slowdownLevels, float radius, float damage) {
            super(material, settings);
            this.manaCost = manaCost;
            this.cooldownTicks = cooldownTicks;
            this.slowdownTicks = slowdownTicks;
            this.slowdownLevels = slowdownLevels;
            this.radius = radius;
            this.damage = damage;
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            tooltip.add(
                    Text.translatable("item.ecraft.staff.magic_damage_tooltip", damage).withColor(0x5454FC)
            );
            tooltip.add(
                    Text.translatable("item.ecraft.staff.mana_tooltip", manaCost).withColor(0x5454FC)
            );
        }

        public int getManaCostForUser(PlayerEntity user) {
            if (user.isCreative())
                return 0;
            return manaCost;
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            if (world instanceof ServerWorld serverWorld
                    && user instanceof ManaUsingEntity manaUsingEntity
                    && manaUsingEntity.manaattributes$getMana() >= getManaCostForUser(user))
            {
                // Deplete mana
                manaUsingEntity.manaattributes$addMana(-getManaCostForUser(user));

                // Get entities that will be hit
                Vector3f userDirection = user.getRotationVector().toVector3f();
                List<Entity> entities = serverWorld.getOtherEntities(
                        user,
                        new Box(
                                user.getX() - radius, user.getY() - radius, user.getZ() - radius,
                                user.getX() + radius, user.getY() + radius, user.getZ() + radius
                        ),
                        entity -> {
                            // Ensure the entity is in the correct angle relative to the player
                            Vector3f vectorFromPlayerToEntity = new Vector3f((float) (entity.getX() - user.getX()), (float) (entity.getY() - user.getY()), (float) (entity.getZ() - user.getZ()));
                            return userDirection.angle(vectorFromPlayerToEntity) <= ICE_STAFF_ARC_RADIANS && entity instanceof LivingEntity;
                        }
                );

                // Affect entities
                for (Entity entity : entities) {
                    entity.damage(serverWorld.getDamageSources().create(EDamageTypes.ICE_STAFF, user), (float) damage);
                    if (entity instanceof LivingEntity livingEntity) {
                        int slownessAmp = slowdownLevels;
                        if (livingEntity.hasStatusEffect(StatusEffects.SLOWNESS))
                            slownessAmp += livingEntity.getStatusEffect(StatusEffects.SLOWNESS).getAmplifier();
                        livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, slowdownTicks, slownessAmp));
                    }
                }

                // Spawn lots of particles
                serverWorld.spawnParticles(ParticleTypes.SNOWFLAKE, user.getX(), user.getY(), user.getZ(), 24, 3, 1, 3, 0.1);

                // Play sound effect
                serverWorld.playSound(null, user.getBlockPos(), ESounds.STAFF_ICE_FIRE, SoundCategory.PLAYERS);

                // Inflict cooldown
                user.getItemCooldownManager().set(this, cooldownTicks);

                return TypedActionResult.success(user.getStackInHand(hand));
            }

            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }

    public static class Lightning extends ToolItem {
        private final int manaCost;
        private final int cooldownTicks;
        private final float knockback;
        private final float maxRange;
        private final float aoeRange;
        private final float damage;

        public Lightning(ToolMaterial material, Settings settings, int manaCost, int cooldownTicks, float knockback, float maxRange, float aoeRange, float damage) {
            super(material, settings);
            this.manaCost = manaCost;
            this.cooldownTicks = cooldownTicks;
            this.knockback = knockback;
            this.maxRange = maxRange;
            this.aoeRange = aoeRange;
            this.damage = damage;
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            tooltip.add(
                    Text.translatable("item.ecraft.staff.magic_damage_tooltip", damage).withColor(0x5454FC)
            );
            tooltip.add(
                    Text.translatable("item.ecraft.staff.mana_tooltip", manaCost).withColor(0x5454FC)
            );
        }

        public int getManaCostForUser(PlayerEntity user) {
            if (user.isCreative())
                return 0;
            return manaCost;
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            if (world instanceof ServerWorld serverWorld
                    && user instanceof ManaUsingEntity manaUsingEntity
                    && manaUsingEntity.manaattributes$getMana() >= getManaCostForUser(user))
            {
                // Deplete mana
                manaUsingEntity.manaattributes$addMana(-getManaCostForUser(user));

                // Create projectile entity and set attributes
                LightningStaffProjectileEntity projectile = new LightningStaffProjectileEntity(serverWorld, user.getPos().add(0, 1.5, 0), damage, aoeRange, knockback);
                projectile.setOwner(user);
                projectile.setVelocity(user, user.getPitch(), user.getHeadYaw(), 0, 3f, 0.25f);
                projectile.setNoGravity(true);
                serverWorld.spawnEntity(projectile);

                // Play sound effect
                serverWorld.playSound(null, user.getBlockPos(), ESounds.STAFF_LIGHTNING_FIRE, SoundCategory.PLAYERS);

                serverWorld.spawnParticles(ParticleTypes.ELECTRIC_SPARK, user.getX(), user.getY(), user.getZ(), 16, 0.5, 0.5, 0.5, 0.1);

                // Inflict cooldown
                user.getItemCooldownManager().set(this, cooldownTicks);

                return TypedActionResult.success(user.getStackInHand(hand));
            }

            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }

    public static class Fire extends ToolItem {
        private final int manaCost;
        private final int cooldownTicks;
        private final float aoeRange;
        private final float damage;

        public Fire(ToolMaterial material, Settings settings, int manaCost, int cooldownTicks, float aoeRange, float damage) {
            super(material, settings);
            this.manaCost = manaCost;
            this.cooldownTicks = cooldownTicks;
            this.aoeRange = aoeRange;
            this.damage = damage;

        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            super.appendTooltip(stack, context, tooltip, type);
            tooltip.add(
                    Text.translatable("item.ecraft.staff.magic_damage_tooltip", damage).withColor(0x5454FC)
            );
            tooltip.add(
                    Text.translatable("item.ecraft.staff.mana_tooltip", manaCost).withColor(0x5454FC)
            );
        }

        public int getManaCostForUser(PlayerEntity user) {
            if (user.isCreative())
                return 0;
            return manaCost;
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            if (world instanceof ServerWorld serverWorld
                    && user instanceof ManaUsingEntity manaUsingEntity
                    && manaUsingEntity.manaattributes$getMana() >= getManaCostForUser(user))
            {
                // Deplete mana
                manaUsingEntity.manaattributes$addMana(-getManaCostForUser(user));

                // Create projectile entities and set attributes
                FireStaffProjectileEntity projectile = new FireStaffProjectileEntity(serverWorld, user.getPos().add(0, 1.5, 0), damage, aoeRange);
                projectile.setOwner(user);
                projectile.setVelocity(user, user.getPitch(), user.getHeadYaw(), 0, 2f, 0.1f);
                projectile.setNoGravity(false);
                serverWorld.spawnEntity(projectile);

                FireStaffProjectileEntity projectile2 = new FireStaffProjectileEntity(serverWorld, user.getPos().add(0, 1.5, 0), damage, aoeRange);
                projectile2.setOwner(user);
                projectile2.setVelocity(user, user.getPitch(), user.getHeadYaw() + 30, 0, 2f, 0.1f);
                projectile2.setNoGravity(false);
                serverWorld.spawnEntity(projectile2);

                FireStaffProjectileEntity projectile3 = new FireStaffProjectileEntity(serverWorld, user.getPos().add(0, 1.5, 0), damage, aoeRange);
                projectile3.setOwner(user);
                projectile3.setVelocity(user, user.getPitch(), user.getHeadYaw() - 30, 0, 2f, 0.1f);
                projectile3.setNoGravity(false);
                serverWorld.spawnEntity(projectile3);

                // Play sound effect
                serverWorld.playSound(null, user.getBlockPos(), ESounds.STAFF_FIRE_FIRE, SoundCategory.PLAYERS);

                serverWorld.spawnParticles(ParticleTypes.SMOKE, user.getX(), user.getY(), user.getZ(), 16, 0.5, 0.5, 0.5, 0.1);

                // Inflict cooldown
                user.getItemCooldownManager().set(this, cooldownTicks);

                return TypedActionResult.success(user.getStackInHand(hand));
            }

            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }
}
