package fr.takkers.crst.block.entity.custom;

import fr.takkers.crst.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

public class PwdKLNGGnomonEntity extends BlockEntity implements GeoBlockEntity {
    private boolean isWheelFinished;
    RawAnimation headWheel = RawAnimation.begin().then("wheeling", Animation.LoopType.PLAY_ONCE);

    public PwdKLNGGnomonEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(ModBlockEntities.PWD_KLNG_GNOMON_BLOCK_ENTITY.get(), p_155229_, p_155230_);
    }

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (tAnimationState.isCurrentAnimation(headWheel)) {
            if (tAnimationState.getController().hasAnimationFinished()) {
                isWheelFinished = true;
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            }
        } else if(!isWheelFinished){
            tAnimationState.getController().setAnimation(headWheel);
        }
        return PlayState.CONTINUE;
    }

    /*public void playWheelAnim(){
        triggerAnim("controller", "wheeling");
    }*/

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }
}
