package fr.takkers.crst.event;

import fr.takkers.crst.CRST;
import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.block.custom.client.PwdKLNGGnomonRenderer;
import fr.takkers.crst.block.entity.ModBlockEntities;
import fr.takkers.crst.block.entity.renderer.ArtefactExtractorRenderer;
import fr.takkers.crst.block.entity.renderer.EconomyControlOfficeRenderer;
import fr.takkers.crst.entity.client.armor.SnowShoesRenderer;
import fr.takkers.crst.item.custom.SnowShoes;
import fr.takkers.crst.particle.ModParticles;
import fr.takkers.crst.particle.custom.GlowstoneParticles;
import fr.takkers.crst.particle.custom.UnusualTotemParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = CRST.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

    }

    @Mod.EventBusSubscriber(modid = CRST.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void registerRenderers(final EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntities.ECONOMY_CONTROL_OFFICE_BLOCK_ENTITY.get(),
                    EconomyControlOfficeRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.ARTEFACT_EXTRACTOR_BLOCK_ENTITY.get(),
                    ArtefactExtractorRenderer::new);
            event.registerBlockEntityRenderer(ModBlockEntities.PWD_KLNG_GNOMON_BLOCK_ENTITY.get(),
                    PwdKLNGGnomonRenderer::new);
        }

        @SubscribeEvent
        public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
            Minecraft.getInstance().particleEngine.register(ModParticles.GLOWSTONE_PARTICLES.get(),
                    GlowstoneParticles.Provider::new);
            Minecraft.getInstance().particleEngine.register(ModParticles.UNUSUAL_TOTEM_PARTICLES.get(),
                    UnusualTotemParticles.Provider::new);
        }

        /*
        @SubscribeEvent
        public static void registerArmorRenderer(final EntityRenderersEvent.AddLayers event) {
            GeoArmorRenderer.registerArmorRenderer(SnowShoes.class,()-> new SnowShoesRenderer());
        }

         */
    }
}
