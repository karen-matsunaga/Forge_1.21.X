//package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.CompletableFuture;

//public class ModEnchantmentTagProvider extends EnchantmentTagsProvider {
//    public ModEnchantmentTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
//        super(output, lookupProvider);
//    }
//
//    @Override
//    protected void addTags(HolderLookup.@NotNull Provider provider) {
//        this.tag(EnchantmentTags.DAMAGE_EXCLUSIVE).add(ModEnchantments.LIGHTNING_STRIKER);
//        this.tag(ModTags.Enchantments.LIGHTNING_STRIKER).add(ModEnchantments.LIGHTNING_STRIKER);
//    }
//}