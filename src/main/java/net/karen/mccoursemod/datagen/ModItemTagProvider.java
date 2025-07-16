package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, completableFuture, lookupCompletableFuture, MccourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModTags.Items.TRANSFORMABLE_ITEMS).add(ModItems.ALEXANDRITE.get())
                                              .add(ModItems.RAW_ALEXANDRITE.get())
                                              .add(Items.COAL)
                                              .add(Items.STICK)
                                              .add(Items.COMPASS);
    }
}