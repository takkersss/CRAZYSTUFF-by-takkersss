package fr.takkers.crst.item.custom;

import fr.takkers.crst.entity.client.armor.SnowShoesRenderer;
import fr.takkers.crst.item.client.LevitationWandRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.network.GeckoLibNetwork;
import software.bernie.geckolib3.network.ISyncable;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.function.Consumer;

public class LevitationWand extends Item implements IAnimatable {

    public AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public LevitationWand(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer renderer = new LevitationWandRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimationData data) {
        AnimationController controller = new AnimationController(this, "controller", 0, this::predicate);

        // Registering a sound listener just makes it so when any sound keyframe is hit
        // the method will be called.
        // To register a particle listener or custom event listener you do the exact
        // same thing, just with registerParticleListener and
        // registerCustomInstructionListener, respectively.
        data.addAnimationController(controller);
    }

    private <P extends Item & IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        // Not setting an animation here as that's handled below
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.levitation_wand.idle", () -> true));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}