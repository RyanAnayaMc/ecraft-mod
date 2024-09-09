package dev.night.ecraft.item;

import com.provismet.CombatPlusCore.interfaces.MeleeWeapon;
import dev.night.ecraft.Ecraft;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import nourl.mythicmetals.item.tools.MythicToolMaterials;

import javax.management.Attribute;

public class LanceItem extends ToolItem implements MeleeWeapon {
    public static final Identifier BLOCK_RANGE_MODIFIER_ID = Identifier.of(Ecraft.MOD_ID, "block_range");
    public static final float LANCE_RANGE_BONUS = 1f;

    public static final Identifier ENTITY_RANGE_MODIFIER_ID = Identifier.of(Ecraft.MOD_ID, "entity_range");

    public LanceItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    // super epic lazy person constructors lol
    public LanceItem(ToolMaterial material, float baseAttack, float attackSpeed) {
        super(
                material,
                new Item.Settings()
                        .attributeModifiers(
                                LanceItem.getLanceAttributes(
                                        material,
                                        baseAttack + 4f,
                                        attackSpeed - 2.8f
                                )
                        )
        );
    }

    public LanceItem(ToolMaterial material) {
        this(material, 0, 0);
    }

    public static AttributeModifiersComponent getLanceAttributes(ToolMaterial material, float baseAttack, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                baseAttack + material.getAttackDamage(),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                BLOCK_RANGE_MODIFIER_ID,
                                LANCE_RANGE_BONUS,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(EntityAttributes.PLAYER_ENTITY_INTERACTION_RANGE,
                        new EntityAttributeModifier(
                                ENTITY_RANGE_MODIFIER_ID,
                                LANCE_RANGE_BONUS,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if ((double)state.getHardness(world, pos) != 0.0) {
            stack.damage(0, miner, EquipmentSlot.MAINHAND);
        }
        return true;
    }
}
