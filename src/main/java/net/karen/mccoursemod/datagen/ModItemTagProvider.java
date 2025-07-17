package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
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
        // Custom advanced item tag (Magic block)
        tag(ModTags.Items.TRANSFORMABLE_ITEMS).add(ModItems.ALEXANDRITE.get()).add(ModItems.RAW_ALEXANDRITE.get())
                                              .add(Items.COAL).add(Items.STICK).add(Items.COMPASS);
        // Custom trimmable armor item tag
        tag(ItemTags.TRIMMABLE_ARMOR).add(ModItems.ALEXANDRITE_HELMET.get())
                                     .add(ModItems.ALEXANDRITE_CHESTPLATE.get())
                                     .add(ModItems.ALEXANDRITE_LEGGINGS.get())
                                     .add(ModItems.ALEXANDRITE_BOOTS.get());

        // Custom trim materials item tag
        tag(ItemTags.TRIM_MATERIALS).add(ModItems.ALEXANDRITE.get());

        // Custom trim templates item tag
        tag(ItemTags.TRIM_TEMPLATES).add(ModItems.KAUPEN_SMITHING_TEMPLATE.get());

        // Custom active Fly effect item tag
        tag(ModTags.Items.HELMET_FLY).add(Items.NETHERITE_HELMET);
        tag(ModTags.Items.CHESTPLATE_FLY).add(Items.NETHERITE_CHESTPLATE);
        tag(ModTags.Items.LEGGINGS_FLY).add(Items.NETHERITE_LEGGINGS);
        tag(ModTags.Items.BOOTS_FLY).add(Items.NETHERITE_BOOTS);
    }
}