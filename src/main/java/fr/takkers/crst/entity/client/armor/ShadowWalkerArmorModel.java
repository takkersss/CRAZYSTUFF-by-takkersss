package fr.takkers.crst.entity.client.armor;

import fr.takkers.crst.CRST;
import fr.takkers.crst.item.custom.ShadowWalkerArmorItem;
import fr.takkers.crst.item.custom.SnowShoes;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ShadowWalkerArmorModel extends GeoModel<ShadowWalkerArmorItem> {
    @Override
    public ResourceLocation getModelResource(ShadowWalkerArmorItem object) {
        return new ResourceLocation(CRST.MODID, "geo/shadowwalker_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShadowWalkerArmorItem object) {
        return new ResourceLocation(CRST.MODID, "textures/item/shadowwalker_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShadowWalkerArmorItem animatable) {
        return new ResourceLocation(CRST.MODID, "animations/shadowwalker_armor.animation.json");
    }
}
