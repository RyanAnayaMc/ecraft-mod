package dev.night.ecraft.item.tools;

import dev.night.ecraft.component.AetheriumDrillComponent;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.EItems;
import dev.night.ecraft.item.EToolMaterials;
import dev.night.ecraft.item.EWeapons;
import dev.night.ecraft.item.tools.weapons.LanceItem;
import dev.night.ecraft.item.tools.weapons.MultiMiningTool;
import dev.night.ecraft.tags.ETags;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;

public class MagicDrillTool extends MultiMiningTool {
    private static final int maxEnergy = 450;
    private static final int fuelPerMagicCrystal = 150;
    private static final int rangeBonus = 3;

    public MagicDrillTool() {
        super(
                EToolMaterials.CINCINNASITE_DIAMOND,
                ETags.Blocks.PICKSAW_MINEABLE,
                new Settings()
                        .rarity(Rarity.UNCOMMON)
                        .component(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent())
                        .attributeModifiers(createAttributeModifiers())
                        .fireproof(),
                3, 5
        );
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID, (double)(EWeapons.HOE_BASE_ATTACK + EToolMaterials.CINCINNASITE_DIAMOND.getAttackDamage()), EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, (double)EWeapons.HOE_BASE_SPEED, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE,
                        new EntityAttributeModifier(LanceItem.ENTITY_RANGE_MODIFIER_ID, rangeBonus, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        AetheriumDrillComponent component = stack.getOrDefault(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent());
        int energy = component.currentEnergy();
        byte mode = component.drillBeamMode();
        byte efficiencyLevel = component.efficiencyUpgradeLevel();

        tooltip.add(Text.translatable("item.ecraft.magic_laser_drill.tooltip.energy", energy, maxEnergy));
        tooltip.add(Text.translatable("item.ecraft.magic_laser_drill.tooltip.energy_refill", energy, maxEnergy));
        switch (mode) {
            case 0 -> tooltip.add(Text.translatable("item.ecraft.magic_laser_drill.tooltip.focus_beam"));
            case 1 -> tooltip.add(Text.translatable("item.ecraft.magic_laser_drill.tooltip.standard_beam"));
            case 2 -> tooltip.add(Text.translatable("item.ecraft.magic_laser_drill.tooltip.tunneling_beam"));
            case 3 -> tooltip.add(Text.translatable("item.ecraft.magic_laser_drill.tooltip.wide_beam"));
        }
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        if (clickType == ClickType.RIGHT) {
            if (slot.getStack().getItem().equals(EItems.MAGIC_CRYSTAL)) {
                // If being right clicked with magic crystals, refill it if there is space
                AetheriumDrillComponent component = stack.getOrDefault(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent());
                int energy = component.currentEnergy();

                // Determine how many crystals to take
                int crystalsNeededForMax = MathHelper.ceil((float) (maxEnergy - energy) / fuelPerMagicCrystal);
                int crystalsProvided = slot.getStack().getCount();
                int crystalsToTake = Math.min(crystalsProvided, crystalsNeededForMax);

                // Stop if we're taking no crystals
                if (crystalsToTake <= 0)
                    return false;

                // Consume crystals and add energy
                energy += crystalsToTake * fuelPerMagicCrystal;
                slot.getStack().decrement(crystalsToTake);

                // Ensure energy is not overcapped
                if (energy > maxEnergy)
                    energy = maxEnergy;

                // Update component
                stack.set(EComponents.AETHERIUM_DRILL_COMPONENT, new AetheriumDrillComponent(energy, component.drillBeamMode(), component.efficiencyUpgradeLevel()));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.RIGHT) {
            if (otherStack.getItem().equals(EItems.MAGIC_CRYSTAL)) {
                // If being right clicked with magic crystals, refill it if there is space
                AetheriumDrillComponent component = stack.getOrDefault(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent());
                int energy = component.currentEnergy();

                // Determine how many crystals to take
                int crystalsNeededForMax = MathHelper.ceil((float) (maxEnergy - energy) / fuelPerMagicCrystal);
                int crystalsProvided = otherStack.getCount();
                int crystalsToTake = Math.min(crystalsProvided, crystalsNeededForMax);

                // Stop if we're taking no crystals
                if (crystalsToTake <= 0)
                    return false;

                // Consume crystals and add energy
                energy += crystalsToTake * fuelPerMagicCrystal;
                otherStack.decrement(crystalsToTake);

                // Ensure energy is not overcapped
                if (energy > maxEnergy)
                    energy = maxEnergy;

                // Update component
                stack.set(EComponents.AETHERIUM_DRILL_COMPONENT, new AetheriumDrillComponent(energy, component.drillBeamMode(), component.efficiencyUpgradeLevel()));
                return true;
            }
        }
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld && hand.equals(Hand.MAIN_HAND)) {
            ItemStack stack = user.getStackInHand(hand);
            AetheriumDrillComponent component = stack.getOrDefault(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent());

            // Cycle beam modes
            byte drillBeamMode = (byte) (component.drillBeamMode() + 1);

            // If number is negative or is too large, loop it back to 0
            if (drillBeamMode < 0 || drillBeamMode > 3)
                drillBeamMode = 0;
            // Play sound and send message
            world.playSound(user, user.getBlockPos(), SoundEvents.BLOCK_BAMBOO_WOOD_BUTTON_CLICK_ON, SoundCategory.PLAYERS);
            switch (drillBeamMode) {
                case 0 -> user.sendMessage(Text.translatable("item.ecraft.magic_laser_drill.focus_beam"), true);
                case 1 -> user.sendMessage(Text.translatable("item.ecraft.magic_laser_drill.standard_beam"), true);
                case 2 -> user.sendMessage(Text.translatable("item.ecraft.magic_laser_drill.tunneling_beam"), true);
                case 3 -> user.sendMessage(Text.translatable("item.ecraft.magic_laser_drill.wide_beam"), true);
            }

            stack.set(EComponents.AETHERIUM_DRILL_COMPONENT, new AetheriumDrillComponent(component.currentEnergy(), drillBeamMode, component.efficiencyUpgradeLevel()));

            return TypedActionResult.success(user.getStackInHand(hand));
        } else {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (miner instanceof ServerPlayerEntity serverPlayer) {
            AetheriumDrillComponent component = stack.getOrDefault(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent());
            double chance = 1 - component.efficiencyUpgradeLevel() * 0.1;
            boolean shouldConsumeEnergy = world.getRandom().nextDouble() < chance;

            // Decrement energy (maybe if it has the efficiency upgrade)
            int currentEnergy = component.currentEnergy() - (shouldConsumeEnergy ? 1 : 0);
            if (currentEnergy <= 0) {
                currentEnergy = 0;
                serverPlayer.sendMessage(Text.translatable("item.ecraft.magic_laser_drill.no_fuel"), true);
            }

            stack.set(EComponents.AETHERIUM_DRILL_COMPONENT, new AetheriumDrillComponent(currentEnergy, component.drillBeamMode(), component.efficiencyUpgradeLevel()));
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public boolean canMultiMine(ItemStack stack) {
        AetheriumDrillComponent component = stack.getOrDefault(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent());
        return component.currentEnergy() > 0 && component.drillBeamMode() != 0;
    }

    @Override
    public int getMiningRadius(ItemStack stack) {
        byte drillBeamMode = stack.getOrDefault(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent()).drillBeamMode();

        return switch (drillBeamMode) {
            case 0 -> 0; // focused beam
            case 1 -> 1; // standard beam
            case 2 -> 1; // tunneling beam
            case 3 -> 2; // wide beam
            default -> throw new IllegalStateException("Unexpected value for drillBeamMode: " + drillBeamMode);
        };
    }

    @Override
    public int getMiningDepth(ItemStack stack) {
        byte drillBeamMode = stack.getOrDefault(EComponents.AETHERIUM_DRILL_COMPONENT, getDefaultComponent()).drillBeamMode();

        return switch (drillBeamMode) {
            case 0 -> 1; // focused beam
            case 1 -> 3; // standard beam
            case 2 -> 5; // tunneling beam
            case 3 -> 2; // wide beam
            default -> throw new IllegalStateException("Unexpected value for drillBeamMode: " + drillBeamMode);
        };
    }

    public static AetheriumDrillComponent getDefaultComponent() {
        return new AetheriumDrillComponent(maxEnergy, (byte) 0, (byte) 0);
    }
}
