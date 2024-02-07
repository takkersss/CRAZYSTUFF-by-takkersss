package fr.takkers.crst.item.client;

import fr.takkers.crst.item.custom.LevitationWand;
import fr.takkers.crst.item.custom.OrbitalSander;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class OrbitalSanderRenderer extends GeoItemRenderer<OrbitalSander> {
    public OrbitalSanderRenderer() {
        super(new OrbitalSanderModel());
    }
}
