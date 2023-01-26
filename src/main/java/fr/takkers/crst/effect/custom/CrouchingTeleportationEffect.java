package fr.takkers.crst.effect.custom;

import fr.takkers.crst.sound.ModSounds;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class CrouchingTeleportationEffect extends MobEffect {
    public CrouchingTeleportationEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }


    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        Level level = pLivingEntity.level;

        if(!level.isClientSide()) {
            if(pLivingEntity.isCrouching()){

                BlockPos playerPos = pLivingEntity.blockPosition();//Position Joueur
                BlockPos pos1 = new BlockPos(playerPos.getX(), playerPos.getY() -1, playerPos.getZ());
                Block block;

                int nbBlockUnderPlayer = abs(pos1.getY()) + 63;   // Nombre de block entre le joueur et la profondeur max
                if(nbBlockUnderPlayer >= 35){
                    nbBlockUnderPlayer = 35;
                }
                Block[] blocksUnderPlayer = new Block[nbBlockUnderPlayer]; // Tableau avec comme taille nbBlockUnderPlayer

                for(int i = 0; i < nbBlockUnderPlayer; i++){

                    BlockPos blockPos = new BlockPos(pos1.getX(), pos1.getY() - i, pos1.getZ());
                    block = level.getBlockState(blockPos).getBlock(); // Block actuel
                    blocksUnderPlayer[i] = block; // Met le block actuel dans le tableau

                    // Si le block c'est de l'air
                    if(block == Blocks.AIR || block == Blocks.VOID_AIR || block == Blocks.CAVE_AIR){
                        block = level.getBlockState(blockPos).getBlock();
                    }
                    else if(i != 0 && i !=1 && i != 2 ) {
                        if(blocksUnderPlayer[i-1] == Blocks.AIR && blocksUnderPlayer[i-2] == Blocks.AIR) {
                            pLivingEntity.teleportTo(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
                            //player.getLevel().playLocalSound(pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundEvents.ENDERMAN_TELEPORT, pLivingEntity.getSoundSource(), 0.5F, 1.0F, false);
                            break;
                        }
                    }

                }
                AfficheTableau(blocksUnderPlayer);
            }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }


    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int k = 40 >> pAmplifier;
        if(k == 0) {
            return true;
        }else return pDuration % k == 0;
    }

    private void AfficheTableau(Block[] blockTab){
        for (int i = 0; i < blockTab.length; i++) {
            System.out.println("\u001B[36m"+ "block " + i + " : " + blockTab[i] + "\u001B[0m");
        }
    }
}