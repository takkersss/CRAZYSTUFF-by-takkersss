package fr.takkers.crst.block.custom.client;

import fr.takkers.crst.CRST;
import fr.takkers.crst.block.custom.PwdKLNGGnomon;
import fr.takkers.crst.block.entity.custom.PwdKLNGGnomonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PwdKLNGGnomonModel extends GeoModel<PwdKLNGGnomonEntity> {
    @Override
    public ResourceLocation getModelResource(PwdKLNGGnomonEntity object) {
        return new ResourceLocation(CRST.MODID, "geo/pwd_klng_gnomon.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PwdKLNGGnomonEntity object) {
        return new ResourceLocation(CRST.MODID, "textures/block/klng_gnomon.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PwdKLNGGnomonEntity animatable) {
        return new ResourceLocation(CRST.MODID, "animations/pwd_klng_gnomon.animation.json");
    }
}
