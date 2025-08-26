package dev.night.ecraft.item.tools.weapons;

import com.github.theredbrain.manaattributes.entity.ManaUsingEntity;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.attributes.EEntityAttributes;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.component.StaffComponent;
import dev.night.ecraft.entity.StaffProjectileEntity;
import dev.night.ecraft.sound.ESounds;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class StaffItem extends ToolItem {
    public static final Identifier BASE_MAGIC_ATTACK_DAMAGE_MODIFIER_ID = Identifier.of(Ecraft.MOD_ID, "base_magic_attack_damage");
    private final int manaCost;
    protected final Item projectileItem;
    private final int cooldownTicks;
    protected final int existenceTicks;
    protected final byte pierceLevel;
    private final boolean subjectToGravity;
    private final float projectileSpeed;
    public final TagKey<Item> gemItem;

    public StaffItem(ToolMaterial material, TagKey<Item> gemItem, Item.Settings settings, Item projectileItem, int manaCost) {
        this(material, gemItem, settings, projectileItem, manaCost, 40, 180, (byte) 0, false, 1.5f);
    }

    public StaffItem(
            ToolMaterial material,
            TagKey<Item> gemItem,
            Item.Settings settings,
            Item projectileItem,
            int manaCost,
            int cooldownTicks,
            int existenceTicks,
            byte pierceLevel,
            boolean subjectToGravity,
            float projectileSpeed)
    {
        super(material, settings.component(EComponents.STAFF_COMPONENT, getDefaultComponent()));
        this.projectileItem = projectileItem;
        this.gemItem = gemItem;
        this.manaCost = manaCost;
        this.cooldownTicks = cooldownTicks;
        this.existenceTicks = existenceTicks;
        this.pierceLevel = pierceLevel;
        this.subjectToGravity = subjectToGravity;
        this.projectileSpeed = projectileSpeed;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
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
        ItemStack stack = user.getStackInHand(hand);
        ManaUsingEntity userMana = (ManaUsingEntity) user;

        // Check if user has enough mana to use item and if on server world
        if (world instanceof ServerWorld serverWorld && userMana.manaattributes$getMana() >= getManaCostForUser(user)) {
            // Check to see if dual wielding logic should apply
            boolean staffInBothHands = user.getStackInHand(Hand.MAIN_HAND).getItem() instanceof StaffItem && user.getStackInHand(Hand.OFF_HAND).getItem() instanceof StaffItem;
            boolean isMainHand = hand.equals(Hand.MAIN_HAND);

            if (staffInBothHands) {
                // Dual wielding logic
                Hand otherHand = isMainHand ? Hand.OFF_HAND : Hand.MAIN_HAND;

                // Get times each staff was previously used
                long thisLastUseTime = user.getStackInHand(hand).getOrDefault(EComponents.STAFF_COMPONENT, getDefaultComponent()).timeLastUsed();
                long otherLastUseTime = user.getStackInHand(otherHand).getOrDefault(EComponents.STAFF_COMPONENT, getDefaultComponent()).timeLastUsed();

                if (otherLastUseTime < thisLastUseTime) {
                    // If main staff was used more recently, pass on action to other hand
                    return TypedActionResult.pass(stack);
                } else {
                    // Otherwise, use staff in this hand
                    fireStaff(serverWorld, user, stack, true, isMainHand ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                }
            } else {
                // If not dual wielding, just use the staff
                fireStaff(serverWorld, user, stack, false, isMainHand ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            }

            return TypedActionResult.success(stack);
        }

        return TypedActionResult.fail(stack);
    }

    protected void fireStaff(ServerWorld serverWorld, PlayerEntity user, ItemStack stack) {
        fireStaff(serverWorld, user, stack, false, EquipmentSlot.MAINHAND);
    }

    protected void fireStaff(ServerWorld serverWorld, PlayerEntity user, ItemStack stack, boolean isDualWield, EquipmentSlot slot) {
        ManaUsingEntity userMana = (ManaUsingEntity) user;

        // Create projectile entity and set attributes
        StaffProjectileEntity projectile = createEntity(serverWorld, user);
        projectile.setOwner(user);
        projectile.setVelocity(user, user.getPitch(), user.getHeadYaw(), 0, projectileSpeed, 1);
        projectile.setNoGravity(!subjectToGravity);

        // Deplete user mana and spawn entity and inflict cooldown
        if (isDualWield) {
            StaffItem mainHand = (StaffItem) user.getStackInHand(Hand.MAIN_HAND).getItem();
            StaffItem offHand = (StaffItem) user.getStackInHand(Hand.OFF_HAND).getItem();
            int cooldown = (int) ((mainHand.cooldownTicks + offHand.cooldownTicks) * .55);
            user.getItemCooldownManager().set(mainHand, cooldown);
            user.getItemCooldownManager().set(offHand, cooldown);
        } else {
            user.getItemCooldownManager().set(stack.getItem(), cooldownTicks);
        }

        userMana.manaattributes$addMana(-getManaCostForUser(user));
        serverWorld.spawnEntity(projectile);
        serverWorld.playSound(null, user.getBlockPos(), ESounds.STAFF_CAST, SoundCategory.PLAYERS);
        stack.set(EComponents.STAFF_COMPONENT, new StaffComponent(serverWorld.getTime()));
        stack.damage(1, user, slot);
    }

    protected StaffProjectileEntity createEntity(ServerWorld serverWorld, PlayerEntity user) {
        return new StaffProjectileEntity(serverWorld, user.getPos().add(0, 1.5, 0), existenceTicks, pierceLevel, user.getAttributeValue(EEntityAttributes.GENERIC_MAGIC_ATTACK_DAMAGE), projectileItem);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, float baseAttackDamage, float attackSpeed, float magicAttackDamage) {
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
                .add(
                        EEntityAttributes.GENERIC_MAGIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_MAGIC_ATTACK_DAMAGE_MODIFIER_ID, magicAttackDamage + material.getAttackDamage(), EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EEntityAttributes.GENERIC_MAGIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_MAGIC_ATTACK_DAMAGE_MODIFIER_ID, (magicAttackDamage + material.getAttackDamage()) / 2, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.OFFHAND
                )
                .build();
    }

    public static StaffComponent getDefaultComponent() {
        return new StaffComponent(0);
    }
}
