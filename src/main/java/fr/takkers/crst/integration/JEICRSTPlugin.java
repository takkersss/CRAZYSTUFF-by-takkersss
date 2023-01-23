package fr.takkers.crst.integration;

import fr.takkers.crst.CRST;
import fr.takkers.crst.recipe.ArtefactExtractorRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;


import java.util.List;
import java.util.Objects;

//@JeiPlugin
public class JEICRSTPlugin { /* implements IModPlugin {
    public static RecipeType<ArtefactExtractorRecipe> ARTEFACT_EXTRACTOR_TYPE =
            new RecipeType<>(ArtefactExtractorRecipeCategory.UID, ArtefactExtractorRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(CRST.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                ArtefactExtractorRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<ArtefactExtractorRecipe> recipesInfusing = rm.getAllRecipesFor(ArtefactExtractorRecipe.Type.INSTANCE);
        registration.addRecipes(ARTEFACT_EXTRACTOR_TYPE, recipesInfusing);
    }
    */
}