package fr.takkers.crst.entity.client.armor;

import fr.takkers.crst.item.custom.PwdShadowWalkerArmorItem;
import fr.takkers.crst.item.custom.ShadowWalkerArmorItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class PwdShadowWalkerArmorRenderer extends GeoArmorRenderer<PwdShadowWalkerArmorItem> {
    public PwdShadowWalkerArmorRenderer() {
        super(new PwdShadowWalkerArmorModel());
    }
}
