package fr.takkers.crst.block.custom;

import fr.takkers.crst.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChristmasBall extends AbstractGlassBlock {

    protected static final VoxelShape SHAPE_1 = Block.box(6.0D, 12.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    protected static final VoxelShape SHAPE_2 = Block.box(5.0D, 12.0D, 5.0D, 11.0D, 16.0D, 11.0D);
    // Pour agrandir, diminuer p1 et augmenter p2
    protected static final VoxelShape SHAPE_3 = Block.box(4.0D, 11.0D, 4.0D, 12.0D, 16.0D, 12.0D);


    public static final BooleanProperty LIT = BooleanProperty.create("clicked");
    public static final IntegerProperty COLOR = IntegerProperty.create("color",0, 5); // 5 non-inclus
    public static final IntegerProperty BALL = IntegerProperty.create("number",1, 4);

    public ChristmasBall(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(COLOR, 0).setValue(LIT, true).setValue(BALL, 1));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch(pState.getValue(BALL)) {
            case 1:
            default:
                return SHAPE_1;
            case 2:
                return SHAPE_2;
            case 3:
                return SHAPE_3;
        }
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        Block b = pLevel.getBlockState(pPos.above()).getBlock();
        // Faire tenir sur les 8 différents blocs de feuille
        if (b == Blocks.ACACIA_LEAVES || b == Blocks.SPRUCE_LEAVES || b == Blocks.BIRCH_LEAVES || b == Blocks.FLOWERING_AZALEA_LEAVES || b == Blocks.DARK_OAK_LEAVES || b == Blocks.OAK_LEAVES || b == Blocks.JUNGLE_LEAVES || b == Blocks.AZALEA_LEAVES){
            return true;
        }else return Block.canSupportCenter(pLevel, pPos.above(), Direction.DOWN);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        // Changer le nombre de boule en cas de clic avec une boule
        if(!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND && pPlayer.getMainHandItem().getItem() == ModBlocks.CHRISTMAS_BALL.get().asItem() && !pPlayer.isCrouching()){
            int currentNumber = pState.getValue(BALL);
            // SI LE JOUEUR EST EN SURVIE
            if (!pPlayer.isCreative()){
                currentNumber++;
                if(currentNumber != 4){
                    pPlayer.getMainHandItem().shrink(1);
                }else{
                    currentNumber = 3;
                }
            }
            // SI LE JOUEUR EST EN CREATIF
            else
            {
                currentNumber++;
                if(currentNumber == 4){
                    currentNumber = 1;
                }
            }
            pLevel.setBlock(pPos, pState.setValue(BALL, currentNumber), 3);
            System.out.println("\u001B[36m"+ "currentNumber : " + currentNumber + "\u001B[0m");
        }
        // Allumer et éteindre en clickant
        else if(!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND && !pPlayer.isCrouching()){
                //Change the blockstate (allumé ou non)
                boolean currentStateCLICKED = pState.getValue(LIT);
                pLevel.setBlock(pPos, pState.setValue(LIT, !currentStateCLICKED), 3);
        }
        // Changer la couleur en cas de click accroupi
        if(!pLevel.isClientSide() && pHand == InteractionHand.MAIN_HAND && pPlayer.isCrouching()){
            int currentColor = pState.getValue(COLOR);
            currentColor++;
            if(currentColor == 5){
                currentColor = 0;
            }
            pLevel.setBlock(pPos, pState.setValue(COLOR, currentColor), 3);
            System.out.println("\u001B[36m"+ "currentColor : " + currentColor + "\u001B[0m");
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT, COLOR, BALL);
    }

}
