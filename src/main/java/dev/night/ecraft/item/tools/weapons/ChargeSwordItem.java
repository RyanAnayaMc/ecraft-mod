package dev.night.ecraft.item.tools.weapons;

import dev.night.ecraft.component.ChargeSwordComponent;
import dev.night.ecraft.component.EComponents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public abstract class ChargeSwordItem extends SwordItem {
    public static final byte MAX_CHARGE = 100;

    abstract byte getChargePerHit();
    abstract byte getChargePerKill();
    abstract SoundEvent getFullChargedSoundEvent();

    public ChargeSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public static boolean isFullyCharged(ItemStack stack) {
        return getCharge(stack) >= MAX_CHARGE;
    }

    public static byte getCharge(ItemStack stack) {
        return stack.getOrDefault(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent()).charge();
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        byte charge = stack.getOrDefault(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent()).charge();
        boolean isFullAtStart = charge >= MAX_CHARGE;

        // Increment charge and ensure it's not over the max
        charge = (byte) Math.min(charge + getChargePerHit(), MAX_CHARGE);
        stack.set(EComponents.CHARGE_SWORD_COMPONENT, new ChargeSwordComponent(charge));

        // If sword is now fully charged as a result of the increment, play sound effect
        if (!isFullAtStart && charge >= MAX_CHARGE)
            attacker.getWorld().playSound(null, attacker.getBlockPos(), getFullChargedSoundEvent(), SoundCategory.PLAYERS);

        return super.postHit(stack, target, attacker);
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postDamageEntity(stack, target, attacker);

        if (target.isDead()) {
            // Increment charge and ensure it's not over the max
            byte charge = stack.getOrDefault(EComponents.CHARGE_SWORD_COMPONENT, getDefaultComponent()).charge();
            boolean isFullAtStart = charge >= MAX_CHARGE;

            charge = (byte) Math.min(charge + getChargePerKill(), MAX_CHARGE);
            stack.set(EComponents.CHARGE_SWORD_COMPONENT, new ChargeSwordComponent(charge));

            // If sword is now fully charged as a result of the increment, play sound effect
            if (!isFullAtStart && charge >= MAX_CHARGE)
                attacker.getWorld().playSound(null, attacker.getBlockPos(), getFullChargedSoundEvent(), SoundCategory.PLAYERS);
        }
    }

    public static ChargeSwordComponent getDefaultComponent() {
        return new ChargeSwordComponent((byte) 0);
    }
}
