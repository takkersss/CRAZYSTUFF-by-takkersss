package fr.takkers.crst.enchantment.custom;

import com.mojang.blaze3d.shaders.Effect;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantments;

import javax.annotation.Nullable;

public class WeaknessEnchantment extends Enchantment {
    public WeaknessEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if(!pAttacker.level.isClientSide()) {
            ServerLevel world = ((ServerLevel) pAttacker.level);
            BlockPos position = pTarget.blockPosition();
            LivingEntity pEnemy = (LivingEntity) pTarget;

            if(pLevel == 1) {
                pEnemy.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 1));
                System.out.println("\u001B[36m"+ pEnemy + "\u001B[0m");
                System.out.println("\u001B[36m"+ pAttacker + "\u001B[0m");
            }

        }

        super.doPostAttack(pAttacker, pTarget, pLevel);
    }


    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTradeable() {
        return true;
    }

    @Override
    public boolean isCurse() {
        return false;
    }
}