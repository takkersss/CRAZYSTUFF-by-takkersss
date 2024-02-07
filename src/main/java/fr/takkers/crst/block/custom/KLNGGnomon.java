package fr.takkers.crst.block.custom;

import fr.takkers.crst.block.ModBlocks;
import fr.takkers.crst.item.client.LevitationWandRenderer;
import fr.takkers.crst.particle.ModParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.client.extensions.common.IClientBlockExtensions;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public class KLNGGnomon extends HorizontalDirectionalBlock {

    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 14.0D, 11.0D);

    public KLNGGnomon(BlockBehaviour.Properties properties) {
        super(properties);
    }


    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection());
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {
        if(level.isNight() && blockPos.getY() >= 100){
            if(blockState.getValue(HorizontalDirectionalBlock.FACING) == Direction.WEST || blockState.getValue(HorizontalDirectionalBlock.FACING) == Direction.EAST)
            {
                long t = level.getDayTime();
                if((t > 12000 && t < 14000) || (t > 22000 && t < 24000)){
                    level.setBlockAndUpdate(blockPos.below(), Blocks.BLUE_CONCRETE_POWDER.defaultBlockState());
                    Direction d = blockState.getValue(FACING);
                    level.setBlockAndUpdate(blockPos, ModBlocks.PWD_KLNG_GNOMON.get().defaultBlockState().setValue(FACING, d.getOpposite()));
                    level.playSound(null, blockPos, SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.BLOCKS, 1f, 1f);
                }
            }
        }
    }
}
