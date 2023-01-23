package fr.takkers.crst.item.custom;

import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Random;

public class Mortar extends Item {
    public Mortar(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isEnchantable(@Nonnull ItemStack stack)
    {
        return false;
    }


    //Item stays in the crafting table

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if(container.hurt(1, RandomSource.create(), null))
            return ItemStack.EMPTY;
        else
            return container;
    }

}
