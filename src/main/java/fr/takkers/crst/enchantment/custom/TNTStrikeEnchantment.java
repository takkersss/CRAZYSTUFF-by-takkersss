package fr.takkers.crst.enchantment.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class TNTStrikeEnchantment extends Enchantment {
    public TNTStrikeEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if(!pAttacker.level.isClientSide()) {
            ServerLevel world = ((ServerLevel) pAttacker.level);
            BlockPos position = pTarget.blockPosition();
            Level level = world.getLevel();
            BlockPos upPosition = new BlockPos(position.getX(), position.getY() + 5, position.getZ());

            if(pLevel == 1) {
                BlockPos checkUpBlockPos;
                Block checkUpBlock;
                int checkUpValue = 0;
                for (int i = 1; i <= 5; i++) {
                    checkUpBlockPos = new BlockPos(position.getX(), position.getY() + i, position.getZ());
                    checkUpBlock = level.getBlockState(checkUpBlockPos).getBlock();
                    if (checkUpBlock == Blocks.AIR) {
                        checkUpValue = checkUpValue + 1;
                    }
                }
                if(checkUpValue == 5) {
                    EntityType.TNT.spawn(world, (ItemStack) null, null, upPosition,
                            MobSpawnType.TRIGGERED, true, true);
                    System.out.println("\u001B[36m"+ checkUpValue + " Spawn Reussi" + "\u001B[0m");
                }
                else{
                    System.out.println("\u001B[36m"+ checkUpValue + " Spawn Failed" + "\u001B[0m");
                }

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
        return false;
    }

    @Override
    public boolean isCurse() {
        return true;
    }
}