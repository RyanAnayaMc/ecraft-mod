package dev.night.ecraft.item.tools.weapons;

import dev.night.ecraft.damage.EDamageTypes;
import dev.night.ecraft.sound.ESounds;
import dev.night.ecraft.status.EStatusEffects;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.EToolMaterials;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class BastardSword {
    public static final int BASTARD_SWORD_BASE_DAMAGE = 5;
    public static final float BASTARD_SWORD_ATTACK_SPEED = -3.1f;

    public static class BaseSword extends SwordItem {
        public BaseSword() {
            super(
                    EToolMaterials.TITANIUM,
                    new Settings()
                            .attributeModifiers(
                                    createAttributeModifiers(EToolMaterials.TITANIUM, BASTARD_SWORD_BASE_DAMAGE, BASTARD_SWORD_ATTACK_SPEED)
                            )
                            .rarity(Rarity.EPIC)
                            .fireproof()
            );
        }
    }

    public static class Durendal extends ChargeSwordItem {
        private static final int DURENDAL_SUPER_RADIUS = 24;
        private static final int LIGHTNING_STRIKES_IN_SUPER = 16;
        private static final float LIGHTNING_STRIKE_CHANCE = 0.1f;

        public Durendal() {
            super(
                    EToolMaterials.FLAWLESS_AETHERIUM,
                    new Settings()
                            .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                            .attributeModifiers(
                                    createAttributeModifiers(EToolMaterials.FLAWLESS_AETHERIUM, BASTARD_SWORD_BASE_DAMAGE, BASTARD_SWORD_ATTACK_SPEED)
                            )
                            .rarity(Rarity.EPIC)
                            .fireproof()
            );
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.durendal_tooltip1"));
            tooltip.add(Text.translatable("item.ecraft.durendal_tooltip2"));
            tooltip.add(Text.translatable("item.ecraft.durendal_tooltip3"));
        }

        @Override
        byte getChargePerHit() {
            return 3;
        }

        @Override
        byte getChargePerKill() {
            return 6;
        }

        @Override
        SoundEvent getFullChargedSoundEvent() {
            return SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER;
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack stack = user.getStackInHand(hand);

            if (world instanceof ServerWorld serverWorld && isFullyCharged(stack)) {
                serverWorld.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_BEACON_ACTIVATE, SoundCategory.PLAYERS);

                BlockPos userPos = user.getBlockPos();
                List<Entity> entities = serverWorld.getOtherEntities(
                        user,
                        new Box(
                            userPos.getX() - DURENDAL_SUPER_RADIUS, userPos.getY() - DURENDAL_SUPER_RADIUS, userPos.getZ() - DURENDAL_SUPER_RADIUS,
                            userPos.getX() + DURENDAL_SUPER_RADIUS, userPos.getY() + DURENDAL_SUPER_RADIUS, userPos.getZ() + DURENDAL_SUPER_RADIUS
                        ),
                        EntityPredicates.VALID_LIVING_ENTITY
                );

                for (int i = 0; i < LIGHTNING_STRIKES_IN_SUPER; i++) {
                    int size = entities.size();
                    Entity randomEntity = Util.getRandom(entities, serverWorld.random);

                    EntityType.LIGHTNING_BOLT.spawn(serverWorld, randomEntity.getBlockPos(), SpawnReason.TRIGGERED);
                }

                serverWorld.setWeather(0, 300, true, true);

                stack.set(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent());
                return TypedActionResult.success(stack);
            }

            return TypedActionResult.fail(stack);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            if (isFullyCharged(stack) && attacker.getRandom().nextFloat() < LIGHTNING_STRIKE_CHANCE) {
                EntityType.LIGHTNING_BOLT.spawn((ServerWorld) attacker.getWorld(), target.getBlockPos(), SpawnReason.TRIGGERED);
            }

            return super.postHit(stack, target, attacker);
        }
    }

    public static class Solais extends ChargeSwordItem {
        public static final int BUFF_DURATION = 160;

        public Solais() {
            super(
                    EToolMaterials.FLAWLESS_AETHERIUM,
                    new Settings()
                            .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                            .attributeModifiers(
                                    createAttributeModifiers(EToolMaterials.FLAWLESS_AETHERIUM, BASTARD_SWORD_BASE_DAMAGE, BASTARD_SWORD_ATTACK_SPEED)
                            )
                            .rarity(Rarity.EPIC)
                            .fireproof()
            );
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.solais_tooltip1"));
            tooltip.add(Text.translatable("item.ecraft.solais_tooltip2"));
            tooltip.add(Text.translatable("item.ecraft.solais_tooltip3"));
            tooltip.add(Text.translatable("item.ecraft.solais_tooltip4"));
        }

        @Override
        byte getChargePerHit() {
            return 2;
        }

        @Override
        byte getChargePerKill() {
            return 3;
        }

        @Override
        SoundEvent getFullChargedSoundEvent() {
            return ESounds.SOLAIS_FULLY_CHARGED;
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack stack = user.getStackInHand(hand);

            if (world instanceof ServerWorld serverWorld && isFullyCharged(stack)) {
                serverWorld.playSound(null, user.getBlockPos(), ESounds.SOLAIS_SUPER, SoundCategory.PLAYERS);
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, BUFF_DURATION, 999));
                user.addStatusEffect(new StatusEffectInstance(EStatusEffects.LIONS_BLESSING, BUFF_DURATION));

                stack.set(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent());
            }

            return TypedActionResult.fail(stack);
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            if (isFullyCharged(stack)) {
                attacker.heal(1f);
                attacker.getWorld().playSound(null, attacker.getBlockPos(), ESounds.SOLAIS_HEAL, SoundCategory.PLAYERS);
            }

            return super.postHit(stack, target, attacker);
        }
    }

    public static class Caliburn extends ChargeSwordItem {
        public static final int CALIBURN_EXPLOSION_RADIUS = 8;

        public Caliburn() {
            super(
                    EToolMaterials.FLAWLESS_AETHERIUM,
                    new Settings()
                            .component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true))
                            .rarity(Rarity.EPIC)
                            .component(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent())
                            .attributeModifiers(createAttributeModifiers(EToolMaterials.FLAWLESS_AETHERIUM, BASTARD_SWORD_BASE_DAMAGE, BASTARD_SWORD_ATTACK_SPEED))
                            .fireproof()
            );
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.caliburn_tooltip1"));
            tooltip.add(Text.translatable("item.ecraft.caliburn_tooltip2"));
            tooltip.add(Text.translatable("item.ecraft.caliburn_tooltip3"));
        }

        @Override
        byte getChargePerHit() {
            return 4;
        }

        @Override
        byte getChargePerKill() {
            return 8; // Fast charge for zombie hordes
        }

        @Override
        SoundEvent getFullChargedSoundEvent() {
            return ESounds.CALIBURN_FULLY_CHARGED;
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            if (isFullyCharged(stack)) {
                target.setOnFireForTicks(320); // "Fire Aspect IV" - 16s burn
            }
            // No burn on normal swing
            return super.postHit(stack, target, attacker);
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack stack = user.getStackInHand(hand);
            if (world instanceof ServerWorld serverWorld && isFullyCharged(stack)) {
                BlockPos userPos = user.getBlockPos();
                List<Entity> entities = serverWorld.getOtherEntities(user, new Box(
                        userPos.getX() - CALIBURN_EXPLOSION_RADIUS, userPos.getY() - CALIBURN_EXPLOSION_RADIUS, userPos.getZ() - CALIBURN_EXPLOSION_RADIUS,
                        userPos.getX() + CALIBURN_EXPLOSION_RADIUS, userPos.getY() + CALIBURN_EXPLOSION_RADIUS, userPos.getZ() + CALIBURN_EXPLOSION_RADIUS
                ));

                for (Entity entity : entities) {
                    if (entity instanceof LivingEntity livingEntity && !(entity instanceof PlayerEntity player && (player.isCreative() || player.isSpectator()))) {
                        livingEntity.damage(serverWorld.getDamageSources().create(EDamageTypes.CALIBURN_BLAST, user), 25f); // Flat 25 damage
                        livingEntity.setOnFireForTicks(240); // 12s fire for ability

                        // Enhanced particle effects per entity
                        serverWorld.spawnParticles(ParticleTypes.FLAME, entity.getX(), entity.getY(), entity.getZ(), 30, 0.5, 0.5, 0.5, 0.1);
                        serverWorld.spawnParticles(ParticleTypes.SMOKE, entity.getX(), entity.getY(), entity.getZ(), 20, 0.5, 0.5, 0.5, 0.05);
                        serverWorld.spawnParticles(ParticleTypes.LAVA, entity.getX(), entity.getY(), entity.getZ(), 10, 0.3, 0.3, 0.3, 0.05);
                    }
                }

                // Additional radial blast particles at user position
                serverWorld.spawnParticles(ParticleTypes.FLAME, user.getX(), user.getY(), user.getZ(), 50, 2.0, 2.0, 2.0, 0.2);
                serverWorld.spawnParticles(ParticleTypes.SMOKE, user.getX(), user.getY(), user.getZ(), 30, 2.0, 2.0, 2.0, 0.1);
                serverWorld.spawnParticles(ParticleTypes.EXPLOSION, user.getX(), user.getY(), user.getZ(), 5, 1.0, 1.0, 1.0, 0.0);
                serverWorld.spawnParticles(ParticleTypes.LAVA, user.getX(), user.getY(), user.getZ(), 15, 1.5, 1.5, 1.5, 0.1);

                serverWorld.playSound(null, userPos, ESounds.CALIBURN_SUPER, SoundCategory.PLAYERS);
                serverWorld.playSound(null, userPos, SoundEvents.ENTITY_DRAGON_FIREBALL_EXPLODE, SoundCategory.PLAYERS);
                stack.set(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent());

                return TypedActionResult.success(stack);
            }
            return TypedActionResult.fail(stack);
        }
    }

    /*
    public static class Caliburn extends ChargeSwordItem {
        public static final int CALIBURN_EXPLOSION_RADIUS = 5;
        public static final int CALIBURN_KNOCKBACK_MODIFIER = 1;
        public static final int CALIBURN_EXPLOSION_MAX_DAMAGE = 20;

        @Override
        byte getChargePerHit() {
            return 4;
        }

        @Override
        byte getChargePerKill() {
            return 6;
        }

        @Override
        SoundEvent getFullChargedSoundEvent() {
            return ESounds.CALIBURN_FULLY_CHARGED;
        }

        public Caliburn() {
            super(
                    EToolMaterials.FLAWLESS_AETHERIUM,
                    new Settings()
                            .rarity(Rarity.EPIC)
                            .component(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent())
                            .attributeModifiers(createAttributeModifiers(EToolMaterials.FLAWLESS_AETHERIUM, BASTARD_SWORD_BASE_DAMAGE, BASTARD_SWORD_ATTACK_SPEED))
                            .fireproof()
            );
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            if (isFullyCharged(stack))
                target.setOnFireForTicks(80);
            return super.postHit(stack, target, attacker);
        }

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            ItemStack stack = user.getStackInHand(hand);
            if (world instanceof ServerWorld serverWorld && isFullyCharged(stack)) {
                BlockPos userPos = user.getBlockPos();
                int radius = 7;
                List<Entity> entities = serverWorld.getOtherEntities(user, new Box(
                        userPos.getX() - radius, userPos.getY() - radius, userPos.getZ() - radius,
                        userPos.getX() + radius, userPos.getY() + radius, userPos.getZ() + radius
                ));

                for (Entity entity : entities) {
                    if (!entity.isTouchingWater() && !(entity instanceof PlayerEntity player && (player.isCreative() || player.isSpectator()))) {
                        float damage = 15f; // Flat damage
                        serverWorld.spawnParticles(ParticleTypes.FLAME, entity.getX(), entity.getY(), entity.getZ(), 20, 0.5, 0.5, 0.5, 0.1);
                        entity.damage(serverWorld.getDamageSources().create(EDamageTypes.CALIBURN_BLAST, user), damage);
                        entity.setFireTicks(240);

                        // Pull enemies inward
                        Vec3d knockbackVector = user.getPos().subtract(entity.getPos()).normalize().multiply(0.5);
                        entity.addVelocity(knockbackVector.x, knockbackVector.y, knockbackVector.z);
                    }
                }

                user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 100, 0));
                serverWorld.playSound(null, userPos, ESounds.CALIBURN_SUPER, SoundCategory.PLAYERS);
                stack.set(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent());

                return TypedActionResult.success(stack);
            }
            return TypedActionResult.fail(stack);
        }

        // original use method

        @Override
        public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
            // Only execute on server
            ItemStack stack = user.getStackInHand(hand);
            if (world instanceof ServerWorld serverWorld && isFullyCharged(stack)) {
                // Get all entities who are hit
                BlockPos userPos = user.getBlockPos();
                int x0 = userPos.getX() - CALIBURN_EXPLOSION_RADIUS;
                int x1 = userPos.getX() + CALIBURN_EXPLOSION_RADIUS;
                int y0 = userPos.getY() - CALIBURN_EXPLOSION_RADIUS;
                int y1 = userPos.getY() + CALIBURN_EXPLOSION_RADIUS;
                int z0 = userPos.getZ() - CALIBURN_EXPLOSION_RADIUS;
                int z1 = userPos.getZ() + CALIBURN_EXPLOSION_RADIUS;
                List<Entity> entities = serverWorld.getOtherEntities(user, new Box(x0, y0, z0, x1, y1, z1));

                // Iterate over entities hit
                for (Entity entity : entities) {
                    // Ignore fire immune entities or entities in water or players in creative/spectator
                    if (!entity.isFireImmune() || entity.isTouchingWater() || (entity instanceof PlayerEntity playerEntity && (playerEntity.isCreative() || playerEntity.isSpectator()))) {
                        // Damage multiplier is inversely proportional to the square of the distance
                        float distanceMultiplier = (float) (1 - Math.sqrt(entity.squaredDistanceTo(user) / (CALIBURN_EXPLOSION_RADIUS * CALIBURN_EXPLOSION_RADIUS)));

                        // Spawn particles
                        serverWorld.spawnParticles(ParticleTypes.SMOKE, entity.getX(), entity.getY(), entity.getZ(), 10, serverWorld.random.nextDouble(), serverWorld.random.nextDouble(), serverWorld.random.nextDouble(), 4);
                        serverWorld.spawnParticles(ParticleTypes.FLAME, entity.getX(), entity.getY(), entity.getZ(), 10, serverWorld.random.nextDouble(), serverWorld.random.nextDouble(), serverWorld.random.nextDouble(), 4);

                        // Deal damage
                        entity.damage(serverWorld.getDamageSources().playerAttack(user), CALIBURN_EXPLOSION_MAX_DAMAGE * distanceMultiplier);
                        entity.setFireTicks(240);

                        // Get knockback
                        double knockbackModifier = CALIBURN_KNOCKBACK_MODIFIER * distanceMultiplier * Explosion.getExposure(user.getPos(), entity) * user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);

                        if (entity instanceof LivingEntity livingEntity)
                            knockbackModifier *= (float) livingEntity.getAttributeValue(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);

                        double knockbackX = entity.getX() - user.getX();
                        double knockbackY = entity.getY() - user.getY();
                        double knockbackZ = entity.getZ() - user.getZ();

                        double squaredMagnitude = knockbackX * knockbackX + knockbackY * knockbackY + knockbackZ * knockbackZ;
                        knockbackModifier /= squaredMagnitude;

                        Vec3d knockbackVector = new Vec3d(knockbackX, knockbackY, knockbackZ).multiply(knockbackModifier);

                        // Apply knockback
                        entity.setVelocity(knockbackVector);
                    }
                }

                // Play sound effect
                serverWorld.playSound(null, user.getBlockPos(), ESounds.CALIBURN_SUPER, SoundCategory.PLAYERS);

                // Reset charge
                stack.set(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent());

                return TypedActionResult.success(stack);
            }

            return TypedActionResult.fail(stack);
        }
         */
}
