package dev.night.ecraft.client.item;

import dev.night.ecraft.Ecraft;
import dev.night.ecraft.item.ModWeapons;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.util.Identifier;
import solipingen.sassot.SpearsAxesSwordsShieldsAndOtherTools;
import solipingen.sassot.enchantment.ModEnchantments;

@Environment(value = EnvType.CLIENT)
public class ModModelPredicateProvider {
    public static void registerModItemModelPredicates() {
        registerSpear(ModWeapons.CINCINNASITE_DIAMOND_SPEAR);
        registerSpear(ModWeapons.METALLURGIUM_SPEAR);
        registerShield(ModWeapons.CINCINNASITE_DIAMOND_SHIELD);
        registerFishingRod(ModWeapons.CINCINNASITE_DIAMOND_FUSED_FISHING_ROD);
    }

    // stolen from sassot
    private static void registerSpear(Item spear) {
        ModelPredicateProviderRegistry.register(spear, Identifier.of("throwing"),
                (stack, world, entity, seed) -> (entity != null && entity.isUsingItem() && !(entity instanceof MerchantEntity) && entity.getActiveItem() == stack) || (entity instanceof PiglinEntity && ((MobEntity)entity).isAttacking()) ? 1.0f : 0.0f);
    }

    private static void registerShield(Item shield) {
        ModelPredicateProviderRegistry.register(shield, Identifier.of("blocking"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0f : 0.0f);
        ModelPredicateProviderRegistry.register(shield, Identifier.of(Ecraft.SASSOT_ID, "cloaking"),
                (stack, world, entity, seed) -> {
                    if (entity != null) {
                        RegistryEntryLookup<Enchantment> enchantmentLookup = entity.getRegistryManager().createRegistryLookup().getOrThrow(RegistryKeys.ENCHANTMENT);
                        return entity.isUsingItem() && entity.getActiveItem() == stack && EnchantmentHelper.getLevel(enchantmentLookup.getOrThrow(ModEnchantments.CLOAKING), stack) > 0 ? 1.0f : 0.0f;
                    }
                    return 0.0f;
                });
    }

    private static void registerFishingRod(Item fishingRod) {
        ModelPredicateProviderRegistry.register(fishingRod, Identifier.of("cast"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0f;
                    }
                    boolean bl = entity.getMainHandStack() == stack;
                    boolean bl2 = entity.getOffHandStack() == stack;
                    if (entity.getMainHandStack().getItem() instanceof FishingRodItem) {
                        bl2 = false;
                    }
                    return (bl || bl2) && entity instanceof PlayerEntity && ((PlayerEntity)entity).fishHook != null ? 1.0f : 0.0f;
                });
    }
}
