package fr.takkers.crst.util;

import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab CRST_CREATIVE_TAB = new CreativeModeTab("crst_creative_tab") {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.SHADOWWALKER_HEAD.get());
        }
    };
}