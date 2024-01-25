package fr.takkers.crst.event;

import fr.takkers.crst.CRST;
import fr.takkers.crst.entity.ModEntityTypes;
import fr.takkers.crst.entity.custom.ShadowWalkerEntity;
import fr.takkers.crst.particle.ModParticles;
import fr.takkers.crst.particle.custom.GlowstoneParticles;
import fr.takkers.crst.particle.custom.UnusualTotemParticles;
import fr.takkers.crst.recipe.ArtefactExtractorRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegisterEvent;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = CRST.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_TYPES, helper -> {
            helper.register(new ResourceLocation(CRST.MODID, ArtefactExtractorRecipe.Type.ID),
                    ArtefactExtractorRecipe.Type.INSTANCE);
        });
    }

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.SHADOW_WALKER.get(), ShadowWalkerEntity.setAttributes());
    }
}