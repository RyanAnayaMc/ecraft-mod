package dev.night.ecraft.world;

import com.mojang.datafixers.util.Pair;
import dev.night.ecraft.Ecraft;
import dev.night.ecraft.sound.ModSounds;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.util.ClientUtil;

import java.util.ArrayList;
import java.util.List;

public class GunlanceExplosion{

    public static Explosion createExplosion(
            World world,
            @Nullable Entity entity,
            @Nullable DamageSource damageSource,
            @Nullable ExplosionBehavior behavior,
            double x,
            double y,
            double z,
            float power,
            boolean createFire,
            boolean particles,
            ParticleEffect particle,
            ParticleEffect emitterParticle,
            RegistryEntry<SoundEvent> soundEvent
    ) {
        Explosion.DestructionType destructionType = Explosion.DestructionType.KEEP;

        Explosion explosion = new Explosion(world, entity, damageSource, behavior, x, y, z, power, createFire, destructionType, particle, emitterParticle, soundEvent);
        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(particles);
        return explosion;
    }

    /*
    public static Explosion createGunlanceExplosion(World world, PlayerEntity user, Vec3d pos, float power) {
        Explosion explosion = new Explosion(
                world,
                user,
                Explosion.createDamageSource(world, user),
                new GunlanceExplosionBehavior(user),
                pos.x, pos.y, pos.z,
                5f,
                false,
                Explosion.DestructionType.KEEP,
                ParticleTypes.EXPLOSION,
                ParticleTypes.EXPLOSION_EMITTER,
                ModSounds.GUNLANCE_FIRE
        );

        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(true);
        return explosion;
    }*/
}
