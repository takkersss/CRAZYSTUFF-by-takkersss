package fr.takkers.crst.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModMaterialTiers {
    public static final ForgeTier SHADOW_WALKER_TIER = new ForgeTier(3, 2048, 8f,
            3f, 18, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Items.NETHERITE_SCRAP));

}