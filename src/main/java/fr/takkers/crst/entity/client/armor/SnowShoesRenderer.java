package fr.takkers.crst.entity.client.armor;

import fr.takkers.crst.CRST;
import fr.takkers.crst.entity.client.ShadowWalkerModel;
import fr.takkers.crst.item.custom.SnowShoes;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer;

public final class SnowShoesRenderer extends GeoArmorRenderer<SnowShoes> {
    public SnowShoesRenderer() {
        super(new SnowShoesModel());

        //addRenderLayer(new AutoGlowingGeoLayer<>(this));
    }
}