package fr.takkers.crst.item.client;

import fr.takkers.crst.CRST;
import fr.takkers.crst.item.custom.LevitationWand;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class LevitationWandModel extends GeoModel<LevitationWand> {
    @Override
    public ResourceLocation getModelResource(LevitationWand object) {
        return new ResourceLocation(CRST.MODID, "geo/levitation_wand.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LevitationWand object) {
        return new ResourceLocation(CRST.MODID, "textures/item/levitation_wand.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LevitationWand animatable) {
        return new ResourceLocation(CRST.MODID, "animations/levitation_wand.animation.json");
    }
}
