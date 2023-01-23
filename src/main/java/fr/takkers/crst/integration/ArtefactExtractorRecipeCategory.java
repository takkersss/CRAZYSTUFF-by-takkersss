package fr.takkers.crst.integration;


import fr.takkers.crst.CRST;
import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.item.ModItems;
import fr.takkers.crst.recipe.ArtefactExtractorRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public class ArtefactExtractorRecipeCategory implements IRecipeCategory<ArtefactExtractorRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(CRST.MODID, "artefact_extractor");
    public final static ResourceLocation TEXTURE = new ResourceLocation(CRST.MODID, "textures/gui/artefact_extractor_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public ArtefactExtractorRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ARTEFACT_EXTRACTOR.get()));
    }

    @Override
    public RecipeType<ArtefactExtractorRecipe> getRecipeType() {
        return JEICRSTPlugin.ARTEFACT_EXTRACTOR_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.crst.artefact_extractor");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull ArtefactExtractorRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 34, 18).addIngredients(Ingredient.of((ModItems.OIL_FLASK).get().getDefaultInstance()));
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 18).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 103, 18).addIngredients(Ingredient.of(ModItems.DIAMOND_TIP.get()));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getResultItem());
    }
}