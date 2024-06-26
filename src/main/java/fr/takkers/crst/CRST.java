package fr.takkers.crst;

import com.mojang.logging.LogUtils;
import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.block.entity.ModBlockEntities;
import fr.takkers.crst.effect.ModEffects;
import fr.takkers.crst.enchantment.ModEnchantments;
import fr.takkers.crst.entity.ModEntityTypes;
import fr.takkers.crst.entity.client.ShadowWalkerRenderer;
import fr.takkers.crst.event.loot.ModLootModifiers;
import fr.takkers.crst.item.ModItems;
import fr.takkers.crst.networking.ModMessages;
import fr.takkers.crst.painting.ModPaintings;
import fr.takkers.crst.particle.ModParticles;
import fr.takkers.crst.recipe.ModRecipes;
import fr.takkers.crst.screen.ArtefactExtractorScreen;
import fr.takkers.crst.screen.EconomyControlOfficeScreen;
import fr.takkers.crst.screen.ModMenuTypes;
import fr.takkers.crst.sound.ModSounds;
import fr.takkers.crst.util.ModCreativeTab;
import fr.takkers.crst.villager.ModVillagers;
import fr.takkers.crst.world.biomemods.ModBiomeModifiers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(CRST.MODID)
public class CRST {
    public static final String MODID = "crst";
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogUtils.getLogger();

    // Add a comment
    public CRST() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModParticles.register(eventBus);
        ModEnchantments.register(eventBus);
        ModEntityTypes.register(eventBus);
        ModVillagers.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);
        ModSounds.register(eventBus);
        ModEffects.register(eventBus);
        ModPaintings.register(eventBus);
        ModBiomeModifiers.register(eventBus);
        ModCreativeTab.register(eventBus);
        ModLootModifiers.register(eventBus);

        eventBus.addListener(this::setup);

        GeckoLib.initialize();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        eventBus.addListener(this::addCreative);
    }

    private void setup(final FMLCommonSetupEvent event) {
        //SpawnPlacements.register(EntityType.WITHER_SKELETON,
        /*SpawnPlacements.register(ModEntityTypes.SHADOW_WALKER.get(),
                    SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    Skeleton::checkMonsterSpawnRules);*/
        //ModVillagers.registerPOIs();
        event.enqueueWork(ModMessages::register);

        //event.enqueueWork((ModMessages::register));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // Add to ingredients tab
        if (event.getTab() == ModCreativeTab.CRST_TAB.get()) {

            // MISC
            event.accept(ModItems.CENTER_STONE_BAR);
            event.accept(ModItems.DIAMOND_TIP);
            event.accept(ModItems.MORTAR);
            event.accept(ModItems.OIL_FLASK);

            event.accept(ModItems.RED_WAND);
            event.accept(ModItems.LEVITATION_WAND);
            event.accept(ModItems.ORBITAL_SANDER);
            event.accept(ModItems.SNOW_SHOES);

            // SHADOW WALKER
            event.accept(ModItems.SHADOWWALKER_SPAWN_EGG);
            event.accept(ModItems.SHADOWWALKER_SCALES);
            event.accept(ModItems.SHADOWWALKER_SWORD);

            event.accept(ModItems.SHADOWWALKER_HELMET);
            event.accept(ModItems.SHADOWWALKER_CHESTPLATE);
            event.accept(ModItems.PWD_SHADOWWALKER_CHESTPLATE);
            event.accept(ModItems.SHADOWWALKER_LEGGINGS);
            event.accept(ModItems.SHADOWWALKER_BOOTS);

            // BLOCK
            event.accept(ModBlocks.CENTER_STONE);
            event.accept(ModBlocks.CENTER_STONE_SLAB);
            event.accept(ModBlocks.CENTER_STONE_STAIRS);
            event.accept(ModBlocks.CENTER_STONE_WALL);
            event.accept(ModBlocks.DENSE_VEGETATION);
            event.accept(ModBlocks.SHEET_METAL);

            //BLOCK UTIL
            event.accept(ModBlocks.ECONOMY_CONTROL_OFFICE);
            event.accept(ModBlocks.ARTEFACT_EXTRACTOR);
            event.accept(ModBlocks.CHRISTMAS_BALL);

            // ARTEFACT
            event.accept(ModItems.STRANGE_PART_ONE);
            event.accept(ModItems.STRANGE_PART_TWO);
            event.accept(ModBlocks.KLNG_GNOMON);
            event.accept(ModItems.PWD_KLNG_GNOMON_ITEM);
            event.accept(ModItems.ARTEFACT_BALL);
            event.accept(ModBlocks.SHADOWWALKER_HEAD);
            event.accept(ModItems.TRIANGULAR_ARTEFACT);
            event.accept(ModItems.UNUSUAL_TOTEM);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntityTypes.SHADOW_WALKER.get(), ShadowWalkerRenderer::new);

            MenuScreens.register(ModMenuTypes.ARTEFACT_EXTRACTOR_MENU.get(), ArtefactExtractorScreen::new);
            MenuScreens.register(ModMenuTypes.ECONOMY_CONTROL_OFFICE_MENU.get(), EconomyControlOfficeScreen::new);
        }
    }

}