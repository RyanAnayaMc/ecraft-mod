package dev.night.ecraft.status;

import dev.night.ecraft.potion.EPotions;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;
import org.jetbrains.annotations.Nullable;

public class RecallEffect extends StatusEffect {
    protected RecallEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (source instanceof ServerPlayerEntity player)
            tryTeleportHome(player, amplifier);
        else if (target instanceof ServerPlayerEntity player)
            tryTeleportHome(player, amplifier);
    }

    private void tryTeleportHome(ServerPlayerEntity player, int amplifier) {
        TeleportTarget teleportTarget = player.getRespawnTarget(player.isAlive(), TeleportTarget.NO_OP);
        ServerWorld serverWorld = player.getServerWorld();

        boolean isTargetOverworld = teleportTarget.world().getDimensionEntry().matchesKey(DimensionTypes.OVERWORLD);
        boolean isPlayerOverworld = player.getServerWorld().getDimensionEntry().matchesKey(DimensionTypes.OVERWORLD);

        if (amplifier <= 0 && (!isTargetOverworld || !isPlayerOverworld)) {
            player.sendMessage(Text.translatable("effect.ecraft.recall.fail"));
            player.giveItemStack(PotionContentsComponent.createStack(Items.POTION, EPotions.RECALL));
        } else {
            serverWorld.playSound(player, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS);
            player.fallDistance = 0;
            player.teleportTo(teleportTarget);
            serverWorld.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS);
        }
    }
}
