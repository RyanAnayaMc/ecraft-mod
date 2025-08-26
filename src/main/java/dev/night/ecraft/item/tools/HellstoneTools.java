package dev.night.ecraft.item.tools;

import dev.night.ecraft.attributes.EEntityAttributes;
import dev.night.ecraft.entity.StaffProjectileEntity;
import dev.night.ecraft.item.tools.weapons.LanceItem;
import dev.night.ecraft.item.tools.weapons.StaffItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.List;

public class HellstoneTools {
    public static class Sword extends SwordItem {

        public Sword(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.molten_fire_aspect_tooltip").withColor(0xFE9900));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireForTicks(160);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Axe extends AxeItem {

        public Axe(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.molten_fire_aspect_tooltip").withColor(0xFE9900));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireForTicks(160);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Pickaxe extends PickaxeItem {
        public Pickaxe(ToolMaterial material, Settings settings) {
            super(material, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.molten_fire_aspect_tooltip").withColor(0xFE9900));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireForTicks(160);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Shovel extends ShovelItem {
        public Shovel(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.molten_fire_aspect_tooltip").withColor(0xFE9900));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireForTicks(160);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Hoe extends HoeItem {

        public Hoe(ToolMaterial toolMaterial, Settings settings) {
            super(toolMaterial, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.molten_fire_aspect_tooltip").withColor(0xFE9900));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireForTicks(160);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Lance extends LanceItem {
        public Lance(ToolMaterial material, Settings settings) {
            super(material, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.molten_fire_aspect_tooltip").withColor(0xFE9900));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireForTicks(160);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Picksaw extends PicksawItem {
        public Picksaw(ToolMaterial material, Settings settings) {
            super(material, settings);
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.molten_fire_aspect_tooltip").withColor(0xFE9900));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireForTicks(160);
            return super.postHit(stack, target, attacker);
        }
    }

    public static class Staff extends StaffItem {

        public Staff(ToolMaterial material, TagKey<Item> gemItem, Settings settings, Item projectileItem, int manaCost) {
            super(material, gemItem, settings, projectileItem, manaCost);
        }

        @Override
        protected StaffProjectileEntity createEntity(ServerWorld serverWorld, PlayerEntity user) {
            StaffProjectileEntity entity = new StaffProjectileEntity(serverWorld, user.getPos().add(0, 1.5, 0), existenceTicks, pierceLevel, user.getAttributeValue(EEntityAttributes.GENERIC_MAGIC_ATTACK_DAMAGE), projectileItem);
            entity.setOnFireForTicks(existenceTicks);
            return entity;
        }
    }
}
