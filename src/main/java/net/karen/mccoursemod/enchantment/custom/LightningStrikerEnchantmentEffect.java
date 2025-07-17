package net.karen.mccoursemod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public record LightningStrikerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC =
            MapCodec.unit(LightningStrikerEnchantmentEffect::new);

    // DEFAULT METHOD - Registry Lightning Striker function
    @Override
    public void apply(@NotNull ServerLevel level, int enchantmentLevel, @NotNull EnchantedItemInUse use,
                      @NotNull Entity entity, @NotNull Vec3 vec3) {
        if (enchantmentLevel == 1) { // Lightning Striker 1
            EntityType.LIGHTNING_BOLT.spawn(level, entity.getOnPos(), MobSpawnType.TRIGGERED);
        }
        if (enchantmentLevel == 2) { // Lightning Striker 2
            EntityType.LIGHTNING_BOLT.spawn(level, entity.getOnPos(), MobSpawnType.TRIGGERED);
            EntityType.LIGHTNING_BOLT.spawn(level, entity.getOnPos(), MobSpawnType.TRIGGERED);
        }
    }

    // DEFAULT METHOD - Registry custom CODEC
    @Override
    public @NotNull MapCodec<? extends EnchantmentEntityEffect> codec() { return CODEC; }
}