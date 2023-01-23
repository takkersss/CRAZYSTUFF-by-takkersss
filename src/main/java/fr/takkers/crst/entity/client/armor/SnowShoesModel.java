package fr.takkers.crst.entity.client.armor;

import fr.takkers.crst.CRST;
import fr.takkers.crst.item.custom.SnowShoes;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class SnowShoesModel extends GeoModel<SnowShoes> {
    @Override
    public ResourceLocation getModelResource(SnowShoes object) {
        return new ResourceLocation(CRST.MODID, "geo/snow_shoes.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SnowShoes object) {
        return new ResourceLocation(CRST.MODID, "textures/item/snow_shoes.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SnowShoes animatable) {
        return new ResourceLocation(CRST.MODID, "animations/snow_shoes.animation.json");
    }
}