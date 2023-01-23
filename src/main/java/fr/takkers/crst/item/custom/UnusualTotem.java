package fr.takkers.crst.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UnusualTotem extends Item {
    public UnusualTotem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        tooltip.add(Component.literal("We can see an inscription :").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("We*v*ng sp*der* *om* no* he*e.").withStyle(ChatFormatting.GOLD));

        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
    }
}
