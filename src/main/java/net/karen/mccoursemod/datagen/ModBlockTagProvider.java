package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MccourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ALEXANDRITE_BLOCK.get())
                                            .add(ModBlocks.RAW_ALEXANDRITE_BLOCK.get())
                                            .add(ModBlocks.ALEXANDRITE_ORE.get())
                                            .add(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get())
                                            .add(ModBlocks.ALEXANDRITE_END_ORE.get())
                                            .add(ModBlocks.ALEXANDRITE_NETHER_ORE.get())
                                            .add(ModBlocks.MAGIC_BLOCK.get())
                                            .add(ModBlocks.ALEXANDRITE_LAMP.get(),
                                                 ModBlocks.ALEXANDRITE_BUTTON.get(), ModBlocks.ALEXANDRITE_DOOR.get(),
                                                 ModBlocks.ALEXANDRITE_FENCE.get(), ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
                                                 ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(), ModBlocks.ALEXANDRITE_SLAB.get(),
                                                 ModBlocks.ALEXANDRITE_STAIRS.get(), ModBlocks.ALEXANDRITE_TRAPDOOR.get(),
                                                 ModBlocks.ALEXANDRITE_WALL.get());

        // Break with Iron
        tag(BlockTags.NEEDS_IRON_TOOL).add(ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get());

        // Break with Diamond
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());

        // Fences
        tag(BlockTags.FENCES).add(ModBlocks.ALEXANDRITE_FENCE.get());

        // Fence gates
        tag(BlockTags.FENCE_GATES).add(ModBlocks.ALEXANDRITE_FENCE_GATE.get());

        // Walls
        tag(BlockTags.WALLS).add(ModBlocks.ALEXANDRITE_WALL.get());

        // Alexandrite tool tier
        tag(ModTags.Blocks.NEEDS_ALEXANDRITE_TOOL).add(ModBlocks.RAW_ALEXANDRITE_BLOCK.get())
                                                  .add(Blocks.OBSIDIAN)
                                                  .addTag(BlockTags.NEEDS_IRON_TOOL);

        // Alexandrite incorrect tool tier
        tag(ModTags.Blocks.INCORRECT_FOR_ALEXANDRITE_TOOL).addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                                                          .remove(ModTags.Blocks.NEEDS_ALEXANDRITE_TOOL);

        // Walnut tree block tag
        this.tag(BlockTags.LOGS_THAT_BURN).add(ModBlocks.WALNUT_LOG.get())
                                          .add(ModBlocks.WALNUT_WOOD.get())
                                          .add(ModBlocks.STRIPPED_WALNUT_LOG.get())
                                          .add(ModBlocks.STRIPPED_WALNUT_WOOD.get());
    }
}