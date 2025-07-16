package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput output) {
        List<ItemLike> ALEXANDRITE_SMELTABLES = List.of(ModItems.RAW_ALEXANDRITE.get(),
                ModBlocks.ALEXANDRITE_ORE.get(), ModBlocks.ALEXANDRITE_DEEPSLATE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 9)
                .requires(ModBlocks.ALEXANDRITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK.get()), has(ModBlocks.ALEXANDRITE_BLOCK.get())).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 32)
                .requires(ModBlocks.MAGIC_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK.get()), has(ModBlocks.ALEXANDRITE_BLOCK.get()))
                .save(output, MccourseMod.MOD_ID + ":alexandrite_from_magic_block");

        oreSmelting(output, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                0.25f, 200, "alexandrite");
        oreBlasting(output, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                0.25f, 100, "alexandrite");

    }

    protected static void oreSmelting(@NotNull RecipeOutput recipeOutput, List<ItemLike> itemLike,
                                      @NotNull RecipeCategory category, @NotNull ItemLike result,
                                      float experience, int cookingTime, @NotNull String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, itemLike, category, result,
                   experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(@NotNull RecipeOutput recipeOutput, List<ItemLike> itemLike,
                                      @NotNull RecipeCategory category, @NotNull ItemLike result,
                                      float experience, int cookingTime, @NotNull String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, itemLike, category, result,
                   experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput,
                                                                       RecipeSerializer<T> cookingSerializer,
                                                                       AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> itemLike, RecipeCategory category,
                                                                       ItemLike result, float experience, int cookingTime,
                                                                       String group, String recipeName) {
        for (ItemLike itemlike : itemLike) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience,
                                               cookingTime, cookingSerializer, factory)
                                      .group(group)
                                      .unlockedBy(getHasName(itemlike), has(itemlike))
                                      .save(recipeOutput, MccourseMod.MOD_ID + ":" + getItemName(result) +
                                            recipeName + "_" + getItemName(itemlike));
        }
    }
}