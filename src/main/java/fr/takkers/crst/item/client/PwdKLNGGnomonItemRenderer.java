package fr.takkers.crst.item.client;

import fr.takkers.crst.item.custom.PwdKLNGGnomonItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PwdKLNGGnomonItemRenderer extends GeoItemRenderer<PwdKLNGGnomonItem> {
    public PwdKLNGGnomonItemRenderer() {
        super(new PwdKLNGGnomonItemModel());
    }
}
