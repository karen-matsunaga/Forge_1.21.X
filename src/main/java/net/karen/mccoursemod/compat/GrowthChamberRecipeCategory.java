package net.karen.mccoursemod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.recipe.GrowthChamberRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class GrowthChamberRecipeCategory implements IRecipeCategory<GrowthChamberRecipe> {
    public static final ResourceLocation UID =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "growth_chamber");
    public static final ResourceLocation TEXTURE =
           ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "textures/gui/growth_chamber/growth_chamber_gui.png");

    public static final RecipeType<GrowthChamberRecipe> GROWTH_CHAMBER_RECIPE_RECIPE_TYPE =
           new RecipeType<>(UID, GrowthChamberRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public GrowthChamberRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 83);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.GROWTH_CHAMBER.get()));
    }

    @Override
    public @NotNull RecipeType<GrowthChamberRecipe> getRecipeType() {
        return new RecipeType<>(UID, GrowthChamberRecipe.class);
    }

    @Override
    public @NotNull Component getTitle() { return Component.translatable("block.mccoursemod.growth_chamber"); }

    @Override
    public @Nullable IDrawable getIcon() { return icon; }

    @Override
    public int getHeight() { return background.getHeight(); }

    @Override
    public int getWidth() { return background.getWidth(); }

    @Override
    public void draw(GrowthChamberRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics, 0, 0);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GrowthChamberRecipe recipe, @NotNull IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getIngredients().getFirst());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getResultItem(null));
    }
}