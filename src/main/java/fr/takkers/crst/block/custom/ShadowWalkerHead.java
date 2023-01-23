package fr.takkers.crst.block.custom;

import fr.takkers.crst.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Wearable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;


public class ShadowWalkerHead extends HorizontalDirectionalBlock implements Wearable {

    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 10.0D, 13.0D);

    public ShadowWalkerHead(BlockBehaviour.Properties properties) {
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
    

    /*
    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
            double max = 0.09d;
            double min = 0.05d;
            double range = max - min + 0.05;
            double diameter = (Math.random() * range) + min;
            System.out.println("\u001B[34m" + diameter + "\u001B[0m" );

            for(int i = 0; i < 360; i++) {
                if(i % 20 == 0) {
                    pLevel.addParticle(ModParticles.GLOWSTONE_PARTICLES.get(),
                            pPos.getX() + 0.5d, pPos.getY(), pPos.getZ() + 0.5d,
                            Math.cos(i) * diameter, 0.10d, Math.sin(i) * diameter);
                }
            }
    }
    */
}
