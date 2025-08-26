package dev.night.ecraft.item;

import dev.night.ecraft.component.GunlanceComponent;
import dev.night.ecraft.component.EComponents;
import dev.night.ecraft.item.tools.weapons.GunlanceItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class DebugStick extends Item {
    public DebugStick(Settings settings) {
        super(settings);

    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        GunlanceComponent component = stack.getOrDefault(EComponents.GUNLANCE_COMPONENT, GunlanceItem.getDefaultComponent(8));


        stack.set(EComponents.GUNLANCE_COMPONENT, new GunlanceComponent(
                component.currentAmmo() + 1,
                component.shellChargingTime(),
                component.fullBurstExplosionCooldown(), component.explosionQueue(),
                component.lastHitTime(), component.currentCombo(),
                component.wyvernFireWindupTime(), component.lastWyvernFire(), component.wyvernFireIsWinding()
        ));

        return TypedActionResult.pass(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.of("count " + stack.get(EComponents.GUNLANCE_COMPONENT).currentAmmo()));
    }
}
