package fr.takkers.crst.item.client;

import fr.takkers.crst.item.custom.LevitationWand;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class LevitationWandRenderer extends GeoItemRenderer<LevitationWand> {
    public LevitationWandRenderer() {
        super(new LevitationWandModel());
    }
}
