package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
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
import static net.karen.mccoursemod.item.ModItems.*;
import static net.karen.mccoursemod.util.ModTags.Items.*;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> lookupCompletableFuture,
                              @Nullable ExistingFileHelper existingFileHelper) {
        super(output, completableFuture, lookupCompletableFuture, MccourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        // Custom advanced item tag (Magic block)
        tag(TRANSFORMABLE_ITEMS).add(ALEXANDRITE.get()).add(RAW_ALEXANDRITE.get())
                                              .add(Items.COAL).add(Items.STICK).add(Items.COMPASS);
        // Custom trimmable armor item tag
        tag(ItemTags.TRIMMABLE_ARMOR).add(ALEXANDRITE_HELMET.get())
                                     .add(ALEXANDRITE_CHESTPLATE.get())
                                     .add(ALEXANDRITE_LEGGINGS.get())
                                     .add(ALEXANDRITE_BOOTS.get());

        // Custom trim materials item tag
        tag(ItemTags.TRIM_MATERIALS).add(ALEXANDRITE.get());

        // Custom trim templates item tag
        tag(ItemTags.TRIM_TEMPLATES).add(KAUPEN_SMITHING_TEMPLATE.get());

        // Custom active Fly effect item tag
        tag(HELMET_FLY).add(Items.NETHERITE_HELMET);
        tag(CHESTPLATE_FLY).add(Items.NETHERITE_CHESTPLATE);
        tag(LEGGINGS_FLY).add(Items.NETHERITE_LEGGINGS);
        tag(BOOTS_FLY).add(Items.NETHERITE_BOOTS);

        // Custom log and wood item tag
        tag(ItemTags.LOGS_THAT_BURN).add(ModBlocks.WALNUT_LOG.get().asItem())
                                    .add(ModBlocks.WALNUT_WOOD.get().asItem())
                                    .add(ModBlocks.STRIPPED_WALNUT_LOG.get().asItem())
                                    .add(ModBlocks.STRIPPED_WALNUT_WOOD.get().asItem());

        // Level Charger items
        this.tag(LEVEL_CHARGER_GENERAL).add(LEVEL_CHARGER_PLUS.get(), LEVEL_CHARGER_MINUS.get()); // GENERAL

        // Custom planks
        tag(ItemTags.PLANKS).add(ModBlocks.WALNUT_PLANKS.get().asItem());
    }
}