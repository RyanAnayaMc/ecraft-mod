package dev.night.ecraft.item.tools.weapons;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.attributes.EEntityAttributes;
import dev.night.ecraft.item.EToolMaterials;
import dev.night.ecraft.item.tools.HellstoneTools;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.UnbreakableComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.puffish.attributesmod.AttributesMod;

import java.util.List;

public class SpecialtyWeapon {
    public static final Identifier ENTITY_RANGE_MODIFIER_ID = Identifier.of(Ecraft.MOD_ID, "entity_range");
    public static final Identifier KNOCKBACK_MODIFIER_ID = Identifier.of(Ecraft.MOD_ID, "knockback");
    public static final Identifier MOVE_SPEED_MODIFIER_ID = Identifier.of(Ecraft.MOD_ID, "move_speed");

    public static final float MURAMASA_RANGE_BONUS = .25f;
    public static final float VOLCANO_RANGE_BONUS = 1f;
    public static final float VOLCANO_KNOCKBACK_BONUS = 1f;
    public static final float BLADE_OF_GRASS_RANGE_BONUS = 1f;
    public static final float BLADE_OF_GRASS_KNOCKBACK_BONUS = 1f;
    public static final float NIGHTS_EDGE_RANGE_BONUS = 0.25f;
    public static final float NIGHTS_EDGE_MOVE_SPEED_BONUS = .15f;
    public static final float TRUE_NIGHTS_EDGE_RANGE_BONUS = 0.5f;
    public static final float TRUE_NIGHTS_EDGE_MOVE_SPEED_BONUS = .3f;

    public static class NightsEdge extends SwordItem {

        public NightsEdge() {
            super(EToolMaterials.DEMONITE, new Item.Settings().rarity(Rarity.EPIC).attributeModifiers(NightsEdge.createAttributeModifiers()));
        }

        public static AttributeModifiersComponent createAttributeModifiers() {
            return AttributeModifiersComponent.builder()
                    .add(
                            EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(
                                    BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)4.5 + ToolMaterials.DIAMOND.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE
                            ),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_ATTACK_SPEED,
                            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.4d, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                            new EntityAttributeModifier(ENTITY_RANGE_MODIFIER_ID, NIGHTS_EDGE_RANGE_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_MOVEMENT_EFFICIENCY,
                            new EntityAttributeModifier(MOVE_SPEED_MODIFIER_ID, NIGHTS_EDGE_MOVE_SPEED_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .build();
        }
    }

    public static class TrueNightsEdge extends SwordItem {

        public TrueNightsEdge() {
            super(EToolMaterials.FLAWLESS_AETHERIUM, new Item.Settings().component(DataComponentTypes.UNBREAKABLE, new UnbreakableComponent(true)).rarity(Rarity.EPIC).attributeModifiers(TrueNightsEdge.createAttributeModifiers()));
        }

        public static AttributeModifiersComponent createAttributeModifiers() {
            return AttributeModifiersComponent.builder()
                    .add(
                            EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(
                                    BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)4.5 + EToolMaterials.FLAWLESS_AETHERIUM.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE
                            ),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_ATTACK_SPEED,
                            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.4d, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                            new EntityAttributeModifier(ENTITY_RANGE_MODIFIER_ID, TRUE_NIGHTS_EDGE_RANGE_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_MOVEMENT_EFFICIENCY,
                            new EntityAttributeModifier(MOVE_SPEED_MODIFIER_ID, TRUE_NIGHTS_EDGE_MOVE_SPEED_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EEntityAttributes.GENERIC_ARMOR_SHRED,
                            new EntityAttributeModifier(AttributesMod.ARMOR_SHRED_ID, 1, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .build();
        }
    }

    public static class BladeOfGrass extends SwordItem {

        public BladeOfGrass() {
            super(ToolMaterials.DIAMOND, new Item.Settings().rarity(Rarity.RARE).attributeModifiers(BladeOfGrass.createAttributeModifiers()));
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.blade_of_grass_poison_tooltip").withColor(0x9BD61D));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            if (!target.getType().isIn(EntityTypeTags.UNDEAD))
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 1), attacker);
            return super.postHit(stack, target, attacker);
        }

        public static AttributeModifiersComponent createAttributeModifiers() {
            return AttributeModifiersComponent.builder()
                    .add(
                            EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(
                                    BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)4 + ToolMaterials.DIAMOND.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE
                            ),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_ATTACK_SPEED,
                            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3.1d, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                            new EntityAttributeModifier(ENTITY_RANGE_MODIFIER_ID, BLADE_OF_GRASS_RANGE_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                            new EntityAttributeModifier(KNOCKBACK_MODIFIER_ID, BLADE_OF_GRASS_KNOCKBACK_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .build();
        }
    }

    public static class Volcano extends HellstoneTools.Sword {

        public Volcano() {
            super(EToolMaterials.HELLSTONE, new Item.Settings().rarity(Rarity.RARE).fireproof().attributeModifiers(Volcano.createAttributeModifiers()));
        }

        @Override
        public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
            tooltip.add(Text.translatable("item.ecraft.volcano_fire_aspect_tooltip").withColor(0xFE9900));
        }

        @Override
        public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
            target.setOnFireForTicks(240);
            return super.postHit(stack, target, attacker);
        }

        public static AttributeModifiersComponent createAttributeModifiers() {
            return AttributeModifiersComponent.builder()
                    .add(
                            EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(
                                    BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)((float)4.5 + EToolMaterials.HELLSTONE.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE
                            ),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_ATTACK_SPEED,
                            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3.1d, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                            new EntityAttributeModifier(ENTITY_RANGE_MODIFIER_ID, VOLCANO_RANGE_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_ATTACK_KNOCKBACK,
                            new EntityAttributeModifier(KNOCKBACK_MODIFIER_ID, VOLCANO_KNOCKBACK_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .build();
        }
    }

    public static class Muramasa extends SwordItem {

        public Muramasa() {
            super(EToolMaterials.COBALT, new Item.Settings().rarity(Rarity.RARE).attributeModifiers(Muramasa.createAttributeModifiers()));
        }

        public static AttributeModifiersComponent createAttributeModifiers() {

            return AttributeModifiersComponent.builder()
                    .add(
                            EntityAttributes.GENERIC_ATTACK_DAMAGE,
                            new EntityAttributeModifier(
                                    BASE_ATTACK_DAMAGE_MODIFIER_ID, 6.5, EntityAttributeModifier.Operation.ADD_VALUE
                            ),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.GENERIC_ATTACK_SPEED,
                            new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.1f, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .add(
                            EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                            new EntityAttributeModifier(ENTITY_RANGE_MODIFIER_ID, MURAMASA_RANGE_BONUS, EntityAttributeModifier.Operation.ADD_VALUE),
                            AttributeModifierSlot.MAINHAND
                    )
                    .build();
        }
    }
}
