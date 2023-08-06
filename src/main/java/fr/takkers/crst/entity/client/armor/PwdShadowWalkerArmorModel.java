package fr.takkers.crst.entity.client.armor;

import fr.takkers.crst.CRST;
import fr.takkers.crst.item.custom.PwdShadowWalkerArmorItem;
import fr.takkers.crst.item.custom.ShadowWalkerArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class PwdShadowWalkerArmorModel extends GeoModel<PwdShadowWalkerArmorItem> {
    @Override
    public ResourceLocation getModelResource(PwdShadowWalkerArmorItem object) {
        return new ResourceLocation(CRST.MODID, "geo/pwd_shadowwalker_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PwdShadowWalkerArmorItem object) {
        return new ResourceLocation(CRST.MODID, "textures/item/pwd_shadowwalker_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PwdShadowWalkerArmorItem animatable) {
        return new ResourceLocation(CRST.MODID, "animations/pwd_shadowwalker_armor.animation.json");
    }
}
