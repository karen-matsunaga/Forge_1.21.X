package net.karen.mccoursemod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    // Registry all custom foods
    // Kohlrabi
    public static final FoodProperties KOHLRABI = new FoodProperties.Builder().nutrition(3)
                                                                              .saturationModifier(0.25f)
                                                                              .effect(new MobEffectInstance(MobEffects.INVISIBILITY,
                                                                                      400), 0.20f).build();

    // Honey Berry
    public static final FoodProperties HONEY_BERRY = new FoodProperties.Builder().nutrition(2)
                                                                                 .saturationModifier(0.15f)
                                                                                 .fast().build();
}