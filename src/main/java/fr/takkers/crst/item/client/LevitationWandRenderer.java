package fr.takkers.crst.item.client;

import fr.takkers.crst.item.custom.LevitationWand;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class LevitationWandRenderer extends GeoItemRenderer<LevitationWand> {
    public LevitationWandRenderer() {
        super(new LevitationWandModel());
    }
}
