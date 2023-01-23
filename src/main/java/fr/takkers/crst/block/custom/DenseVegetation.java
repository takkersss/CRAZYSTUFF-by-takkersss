package fr.takkers.crst.block.custom;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class DenseVegetation extends DoublePlantBlock implements BlockColor {
    public DenseVegetation(Properties p_57318_) {
        super(p_57318_);
    }

    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public float getMaxVerticalOffset() {
        return 0f;
    }

    @Override
    public float getMaxHorizontalOffset() {
        return 0f;
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        pEntity.makeStuckInBlock(pState, new Vec3(0.4D, (double) 0.05F, 0.4D));
    }

    @Override
    public int getColor(BlockState pState, @Nullable BlockAndTintGetter pLevel, @Nullable BlockPos pPos, int pTintIndex) {
        return 0;
    }
}