package fr.takkers.crst.item.client;

import fr.takkers.crst.CRST;
import fr.takkers.crst.item.custom.LevitationWand;
import fr.takkers.crst.item.custom.OrbitalSander;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OrbitalSanderModel extends GeoModel<OrbitalSander> {
    @Override
    public ResourceLocation getModelResource(OrbitalSander object) {
        return new ResourceLocation(CRST.MODID, "geo/orbital_sander.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OrbitalSander object) {
        return new ResourceLocation(CRST.MODID, "textures/item/levitation_wand.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OrbitalSander animatable) {
        return new ResourceLocation(CRST.MODID, "animations/orbital_sander.animation.json");
    }
}
