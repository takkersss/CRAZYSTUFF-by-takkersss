package fr.takkers.crst.item.client;

import fr.takkers.crst.CRST;
import fr.takkers.crst.item.custom.LevitationWand;
import fr.takkers.crst.item.custom.PwdKLNGGnomonItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PwdKLNGGnomonItemModel extends GeoModel<PwdKLNGGnomonItem> {
    @Override
    public ResourceLocation getModelResource(PwdKLNGGnomonItem object) {
        return new ResourceLocation(CRST.MODID, "geo/pwd_klng_gnomon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PwdKLNGGnomonItem object) {
        return new ResourceLocation(CRST.MODID, "textures/block/klng_gnomon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PwdKLNGGnomonItem animatable) {
        return new ResourceLocation(CRST.MODID, "animations/pwd_klng_gnomon.animation.json");
    }
}
