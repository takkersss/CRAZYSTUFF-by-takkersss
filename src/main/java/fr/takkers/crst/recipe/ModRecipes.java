package fr.takkers.crst.recipe;

import fr.takkers.crst.CRST;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, CRST.MODID);

    public static final RegistryObject<RecipeSerializer<ArtefactExtractorRecipe>> ARTEFACT_EXTRACTING_SERIALIZER =
            SERIALIZERS.register("artefact_extracting", () -> ArtefactExtractorRecipe.Serializer.INSTANCE);




    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
