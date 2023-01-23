package fr.takkers.crst.item.custom;

import fr.takkers.crst.CRST;
import fr.takkers.crst.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CRST.MODID)
public class RedWand extends Item {
    public RedWand(Properties pProperties) {
        super(pProperties);
    }


    @SubscribeEvent
    public static void onInteractEvent(PlayerInteractEvent event) {
        Level level = event.getLevel();

        if (!level.isClientSide()) {
            Player player = event.getEntity();
            BlockPos playerPos = player.getOnPos();
            int playerposX = playerPos.getX();
            int playerposY = playerPos.getY();
            int playerposZ = playerPos.getZ();
            BlockPos playerPosDown = new BlockPos(playerposX, playerposY - 0.2f, playerposZ);

            Block block = level.getBlockState(playerPosDown).getBlock();


            if (block == Blocks.AIR || block == Blocks.GRASS) {
                if (player.getMainHandItem().getItem() == ModItems.RED_WAND.get() && event.getHand() == InteractionHand.MAIN_HAND) {
                    level.setBlockAndUpdate(playerPosDown, Blocks.RED_STAINED_GLASS.defaultBlockState());

                    System.out.println("\u001B[34m" + "playerposDown = " + playerPosDown + "\u001B[0m");
                    System.out.println("\u001B[34m" + "playerpos = " + playerPos + "\u001B[0m");
                    if (!player.isCreative()) {
                    player.getMainHandItem().hurtAndBreak(1, player, (p_150845_) -> {
                    p_150845_.broadcastBreakEvent(event.getHand());
                });}
                }
            }
        }
    }

}


