package dev.night.ecraft.status;

import dev.night.ecraft.potion.EPotions;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.dimension.DimensionTypes;
import org.jetbrains.annotations.Nullable;

public class ResurrectionEffect extends StatusEffect {
    protected ResurrectionEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (source instanceof ServerPlayerEntity player)
            teleportToLastDeathLocation(player, amplifier);
        else if (target instanceof ServerPlayerEntity player)
            teleportToLastDeathLocation(player, amplifier);
    }

    private void teleportToLastDeathLocation(ServerPlayerEntity player, int amplifier) {
        ServerWorld serverWorld = player.getServerWorld();

        if (player.getLastDeathPos().isPresent()) {
            GlobalPos pos = player.getLastDeathPos().get();
            TeleportTarget teleportTarget = new TeleportTarget(
                    player.server.getWorld(pos.dimension()),
                    pos.pos().toCenterPos(),
                    Vec3d.ZERO,
                    0,
                    0,
                    TeleportTarget.NO_OP
            );

            boolean isTargetOverworld = teleportTarget.world().getDimensionEntry().matchesKey(DimensionTypes.OVERWORLD);
            boolean isPlayerOverworld = player.getServerWorld().getDimensionEntry().matchesKey(DimensionTypes.OVERWORLD);

            if (amplifier <= 0 && (!isTargetOverworld || !isPlayerOverworld)) {
                player.sendMessage(Text.translatable("effect.ecraft.return.fail"));
                player.giveItemStack(PotionContentsComponent.createStack(Items.POTION, EPotions.RESURRECTION));
            } else {
                serverWorld.playSound(player, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS);
                player.fallDistance = 0;
                player.teleportTo(teleportTarget);
                serverWorld.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS);
            }
        } else {
            player.sendMessage(Text.translatable("effect.ecraft.return.fail_2"));
            player.giveItemStack(PotionContentsComponent.createStack(Items.POTION, amplifier > 0 ? EPotions.STRONG_RESURRECTION : EPotions.RESURRECTION));
        }
    }
}
