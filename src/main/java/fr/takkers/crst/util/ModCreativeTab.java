package fr.takkers.crst.util;

import fr.takkers.crst.CRST;
import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CRST.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {
    public static CreativeModeTab CRST_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        CRST_TAB = event.registerCreativeModeTab(new ResourceLocation(CRST.MODID, "crst_creative_tab"),
                builder -> builder.icon(() -> new ItemStack(ModBlocks.SHADOWWALKER_HEAD.get())).title(Component.translatable("itemGroup.crst_creative_tab")).build());
    }
}