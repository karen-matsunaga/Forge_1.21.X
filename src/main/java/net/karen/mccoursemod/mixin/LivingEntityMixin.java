package net.karen.mccoursemod.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "canAttack*", at = @At("HEAD"), cancellable = true)
    private void livingPlayerAttack(LivingEntity living, CallbackInfoReturnable<Boolean> cir) {
        if (living instanceof Player player) { // Monsters etc. not attack Player
            if (EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION.getOrThrow(player), player) > 0) {
                cir.setReturnValue(false);
            }
        }
    }
}