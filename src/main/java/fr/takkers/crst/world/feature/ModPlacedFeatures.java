package fr.takkers.crst.world.feature;

import fr.takkers.crst.CRST;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {

    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, CRST.MODID);

    public static final RegistryObject<PlacedFeature> CENTER_STONE_PLACED = PLACED_FEATURES.register("center_stone_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) (Holder<? extends ConfiguredFeature<?, ?>>)
                    ModConfiguredFeatures.CENTER_STONE, ModOrePlacement.commonOrePlacement(4, // VeinsPerChunk
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-61), VerticalAnchor.absolute(-51)))));

    public static final RegistryObject<PlacedFeature> DENSE_VEGETATION_PLACED = PLACED_FEATURES.register("dense_vegetation_placed",
            () -> new PlacedFeature((Holder<ConfiguredFeature<?, ?>>) (Holder<? extends ConfiguredFeature<?, ?>>)
                    ModConfiguredFeatures.DENSE_VEGETATION_PATCH, List.of(RarityFilter.onAverageOnceEvery(2),
                    InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())));

    public static void register(IEventBus eventBus) {
        PLACED_FEATURES.register(eventBus);
    }
}
