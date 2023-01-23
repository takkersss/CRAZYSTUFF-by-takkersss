package fr.takkers.crst.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemFrameItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SheetMetal extends FaceAttachedHorizontalDirectionalBlock {
    public SheetMetal(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL));
    }

    protected static final VoxelShape EAST_SHAPE = Block.box(15.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape WEST_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 1.0D, 16.0D, 16.0D);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0D, 0.0D, 15.0D, 16.0D, 16.0D, 16.0D);
    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 1.0D);
    protected static final VoxelShape FLOOR_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    protected static final VoxelShape CEIL_SHAPE = Block.box(0.0D, 15.0D, 0.0D, 16.0D, 16.0D, 16.0D);


    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING).getOpposite();
        switch(pState.getValue(FACE)) {

            case FLOOR:
                return FLOOR_SHAPE;

            case WALL:
                switch(direction) {
                    case EAST:
                        return EAST_SHAPE;
                    case WEST:
                        return WEST_SHAPE;
                    case SOUTH:
                        return SOUTH_SHAPE;
                    default:
                    case NORTH:
                        return NORTH_SHAPE;
                }
            case CEILING:
            default:
                return CEIL_SHAPE;
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, FACE);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return true;
    }


}