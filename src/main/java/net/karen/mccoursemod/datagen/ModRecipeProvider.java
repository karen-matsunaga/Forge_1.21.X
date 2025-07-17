package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
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

        // Custom block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.ALEXANDRITE.get())
                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get())).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 9)
                              .requires(ModBlocks.ALEXANDRITE_BLOCK.get())
                              .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK.get()),
                                          has(ModBlocks.ALEXANDRITE_BLOCK.get())).save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 32)
                              .requires(ModBlocks.MAGIC_BLOCK.get())
                              .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK.get()), has(ModBlocks.ALEXANDRITE_BLOCK.get()))
                              .save(output, MccourseMod.MOD_ID + ":alexandrite_from_magic_block");

        // Custom ore
        oreSmelting(output, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                0.25f, 200, "alexandrite");

        oreBlasting(output, ALEXANDRITE_SMELTABLES, RecipeCategory.MISC, ModItems.ALEXANDRITE.get(),
                0.25f, 100, "alexandrite");

        // Custom stair
        stairBuilder(ModBlocks.ALEXANDRITE_STAIRS.get(),
                     Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                               .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get()))
                               .save(output);

        // Custom slab
        slab(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_SLAB.get(), ModItems.ALEXANDRITE.get());

        // Custom button
        buttonBuilder(ModBlocks.ALEXANDRITE_BUTTON.get(),
                      Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                                .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get()))
                                .save(output);

        // Custom pressure plate
        pressurePlate(output, ModBlocks.ALEXANDRITE_PRESSURE_PLATE.get(), ModItems.ALEXANDRITE.get());

        // Custom fence
        fenceBuilder(ModBlocks.ALEXANDRITE_FENCE.get(),
                     Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                               .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get()))
                               .save(output);

        // Custom fence gate
        fenceGateBuilder(ModBlocks.ALEXANDRITE_FENCE_GATE.get(),
                         Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                                   .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get()))
                                   .save(output);

        // Custom wall
        wall(output, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ALEXANDRITE_WALL.get(), ModItems.ALEXANDRITE.get());

        // Custom door
        doorBuilder(ModBlocks.ALEXANDRITE_DOOR.get(),
                    Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                              .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get()))
                              .save(output);

        // Custom trapdoor
        trapdoorBuilder(ModBlocks.ALEXANDRITE_TRAPDOOR.get(),
                        Ingredient.of(ModItems.ALEXANDRITE.get())).group("alexandrite")
                                  .unlockedBy(getHasName(ModItems.ALEXANDRITE.get()), has(ModItems.ALEXANDRITE.get()))
                                  .save(output);

        // Custom smithing template
        trimSmithing(output, ModItems.KAUPEN_SMITHING_TEMPLATE.get(),
                     ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "kaupen"));
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