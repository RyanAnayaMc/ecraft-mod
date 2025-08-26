package dev.night.ecraft.item.tools;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.damage.EDamageTypes;
import dev.night.ecraft.sound.ESounds;
import dev.night.ecraft.status.EStatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;

import java.util.List;

public class RodOfDiscordItem extends Item {
    public final boolean inflictsChaosState;
    public static final int MAX_TELEPORT_DISTANCE = 128;
    public static final int CHAOS_STATE_DURATION_TICKS = 180;

    public RodOfDiscordItem(Settings settings, boolean inflictsChaosState) {
        super(settings);
        this.inflictsChaosState = inflictsChaosState;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.ecraft.rod_of_discord.tooltip"));
        if (inflictsChaosState)
            tooltip.add(Text.translatable("item.ecraft.rod_of_discord.tooltip_chaos"));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world instanceof ServerWorld serverWorld) {
            HitResult hit = user.raycast(MAX_TELEPORT_DISTANCE, 0, false);
            if (hit instanceof BlockHitResult blockHit) {
                Vec3d hitPosition = blockHit.getPos();

                serverWorld.playSound(user, user.getBlockPos(), ESounds.STAFF_CAST, SoundCategory.PLAYERS);
                user.teleportTo(new TeleportTarget(serverWorld, hitPosition, user.getVelocity(), user.getYaw(), user.getPitch(), TeleportTarget.NO_OP));
                serverWorld.playSound(null, user.getBlockPos(), ESounds.STAFF_CAST, SoundCategory.PLAYERS);

                if (inflictsChaosState) {
                    // Damage user if they have chaos state and re-inflict chaos state
                    if (user.hasStatusEffect(EStatusEffects.CHAOS_STATE)) {
                        user.damage(
                                serverWorld.getDamageSources().create(EDamageTypes.CHAOS_STATE, user),
                                user.getMaxHealth() / 4f
                        );
                    }
                    user.addStatusEffect(new StatusEffectInstance(EStatusEffects.CHAOS_STATE, CHAOS_STATE_DURATION_TICKS));
                }

                return TypedActionResult.success(user.getStackInHand(hand));
            }
        }

        return TypedActionResult.fail(user.getStackInHand(hand));
    }
}
