package fr.takkers.crst.entity.client.armor;

import fr.takkers.crst.item.custom.ShadowWalkerArmorItem;
import fr.takkers.crst.item.custom.SnowShoes;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ShadowWalkerArmorRenderer extends GeoArmorRenderer<ShadowWalkerArmorItem> {
    public ShadowWalkerArmorRenderer() {
        super(new ShadowWalkerArmorModel());
    }
}
