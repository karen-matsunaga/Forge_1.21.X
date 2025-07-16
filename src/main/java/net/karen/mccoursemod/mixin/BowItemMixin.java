package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.util.Util;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//@Mixin(BowItem.class)
//public abstract class BowItemMixin {
//    @Inject(method = "getPowerForTime", at = @At("HEAD"), cancellable = true)
//    private static void injectPower(int charge, CallbackInfoReturnable<Float> cir) {
//        ItemStack stack = Util.getLastBowUsed();
//        if (stack != null) {
//            int level = Util.enchant(stack, ModEnchantments.LIGHTSTRING.get());
//            if (level > 0) {
//                float speedMultiplier = 1.0f + (0.25f * level); // 25% per level
//                float power = charge / (20.0f / speedMultiplier); // Simulates faster loading
//                cir.setReturnValue(Math.min(power, 1.0f));
//            }
//        }
//    }
//}