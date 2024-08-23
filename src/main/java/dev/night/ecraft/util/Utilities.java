package dev.night.ecraft.util;

import com.provismet.CombatPlusCore.utility.item.AttributeIdentifiers;
import dev.night.ecraft.item.ModToolMaterials;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class Utilities {
    public static AttributeModifiersComponent createToolAttributes (ToolMaterial material, float baseAttackDamage, float baseAttackSpeed) {
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                baseAttackDamage + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                );

        float bonusAttackSpeed = 0f;

        if (material instanceof ModToolMaterials extraMaterial && extraMaterial.getCustomAttribute() == EntityAttributes.GENERIC_ATTACK_SPEED) {
            bonusAttackSpeed = extraMaterial.getCustomAttributeValue();
        }

        builder.add(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(
                        Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                        baseAttackSpeed + bonusAttackSpeed,
                        EntityAttributeModifier.Operation.ADD_VALUE
                ),
                AttributeModifierSlot.MAINHAND
        );

        if (material instanceof ModToolMaterials extraMaterial && extraMaterial.getCustomAttribute() != null && extraMaterial.getCustomAttribute() != EntityAttributes.GENERIC_ATTACK_SPEED) {
            builder.add(
                    extraMaterial.getCustomAttribute(),
                    new EntityAttributeModifier(
                            AttributeIdentifiers.WEAPON_BONUS_ATTRIBUTE,
                            extraMaterial.getCustomAttributeValue(),
                            EntityAttributeModifier.Operation.ADD_VALUE
                    ),
                    AttributeModifierSlot.MAINHAND
            );
        }

        return builder.build();
    }
}
