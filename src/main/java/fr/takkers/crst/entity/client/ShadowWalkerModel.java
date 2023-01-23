package fr.takkers.crst.entity.client;

import fr.takkers.crst.CRST;
import fr.takkers.crst.entity.custom.ShadowWalkerEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ShadowWalkerModel extends AnimatedGeoModel<ShadowWalkerEntity> {
    @Override
    public ResourceLocation getModelResource(ShadowWalkerEntity object) {
        return new ResourceLocation(CRST.MODID, "geo/shadowwalker.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShadowWalkerEntity object) {
        return new ResourceLocation(CRST.MODID, "textures/entity/shadow_walker.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShadowWalkerEntity animatable) {
        return new ResourceLocation(CRST.MODID, "animations/shadowwalker.animation.json");
    }
}
