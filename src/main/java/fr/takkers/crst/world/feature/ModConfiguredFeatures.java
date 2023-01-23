package fr.takkers.crst.world.feature;

import fr.takkers.crst.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public class ModConfiguredFeatures {

    public static final List<OreConfiguration.TargetBlockState> OVERWORLD_CENTER_STONE = List.of(
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.CENTER_STONE.get().defaultBlockState()));

    public static final Holder<ConfiguredFeature<OreConfiguration, ?>> CENTER_STONE = FeatureUtils.register("center_stone",
            Feature.ORE, new OreConfiguration(OVERWORLD_CENTER_STONE, 12));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> DENSE_VEGETATION_PATCH =
            FeatureUtils.register("dense_vegetation_patch", Feature.RANDOM_PATCH,
                    new RandomPatchConfiguration(50, 7, 1, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                            new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.DENSE_VEGETATION.get())))));


}
