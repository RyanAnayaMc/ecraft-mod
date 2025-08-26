package dev.night.ecraft.item.tools;

import dev.night.ecraft.item.tools.weapons.BattleaxeItem;
import dev.night.ecraft.item.tools.weapons.LanceItem;
import dev.night.ecraft.item.tools.weapons.MultiMiningTool;
import dev.night.ecraft.item.tools.weapons.ScytheItem;
import dev.night.ecraft.world.GunlanceExplosionBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

public class DetoniumTools {
    public static final float EXPLOSION_POWER = 2F;
    public static final float EXPLOSION_DAMAGE = 10f;

    public static class Blastaxe extends MultiMiningTool {

        public Blastaxe(ToolMaterial material, Settings settings) {
            super(material, BlockTags.PICKAXE_MINEABLE, settings, 1, 5);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.detonium_strong_explosion_tooltip").withColor(0xE4080A));
            tooltip.add(Text.translatable("item.ecraft.detonium_mining_tooltip").withColor(0xE4080A));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            detoniumPostHit(stack, target, attacker, 0.4f, 4);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Sword extends SwordItem {
        public Sword(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.detonium_explosion_tooltip").withColor(0xE4080A));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            detoniumPostHit(stack, target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Lance extends LanceItem {
        public Lance(ToolMaterial material, Settings settings) {
            super(material, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.detonium_explosion_tooltip").withColor(0xE4080A));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            detoniumPostHit(stack, target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Pickaxe extends PickaxeItem {
        public Pickaxe(ToolMaterial material, Settings settings) {
            super(material, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.detonium_explosion_tooltip").withColor(0xE4080A));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            detoniumPostHit(stack, target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Battleaxe extends BattleaxeItem {
        public Battleaxe(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.detonium_explosion_tooltip").withColor(0xE4080A));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            detoniumPostHit(stack, target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Shovel extends ShovelItem {
        public Shovel(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.detonium_explosion_tooltip").withColor(0xE4080A));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            detoniumPostHit(stack, target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Scythe extends ScytheItem {
        public Scythe(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.detonium_explosion_tooltip").withColor(0xE4080A));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            detoniumPostHit(stack, target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Picksaw extends PicksawItem {
        public Picksaw(ToolMaterial material, Settings settings) {
            super(material, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.detonium_explosion_tooltip").withColor(0xE4080A));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            detoniumPostHit(stack, target, attacker);
            return super.postHit(stack, target, attacker);
        }
    }

    private static void detoniumPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        detoniumPostHit(stack, target, attacker, 0.1f, 1);
    }

    private static void detoniumPostHit(ItemStack stack, LivingEntity target, LivingEntity attacker, float chance, float damageMultiplier) {
        if (!target.getWorld().isClient() && attacker.isPlayer()) {
            ServerWorld world = (ServerWorld) target.getWorld();
            PlayerEntity user = (PlayerEntity) attacker;

            boolean willExplode = world.random.nextFloat() < chance;

            if (willExplode)
                doExplosion(world, user, damageMultiplier, target.getPos()).affectWorld(true);
        }
    }

    private static Explosion doExplosion(ServerWorld world, PlayerEntity user, float damageMultiplier, Vec3d blastPos) {
        return world.createExplosion(
                null,
                Explosion.createDamageSource(world, user),
                new GunlanceExplosionBehavior(user, EXPLOSION_DAMAGE * damageMultiplier, false),
                blastPos.getX(),
                blastPos.getY(),
                blastPos.getZ(),
                EXPLOSION_POWER,
                false,
                World.ExplosionSourceType.TRIGGER,
                ParticleTypes.EXPLOSION,
                ParticleTypes.EXPLOSION_EMITTER,
                SoundEvents.ENTITY_GENERIC_EXPLODE
        );
    }
}
