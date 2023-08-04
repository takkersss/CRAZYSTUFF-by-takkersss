package fr.takkers.crst.block.custom.client;

import fr.takkers.crst.block.entity.custom.PwdKLNGGnomonEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class PwdKLNGGnomonRenderer extends GeoBlockRenderer<PwdKLNGGnomonEntity> {
    public PwdKLNGGnomonRenderer(BlockEntityRendererProvider.Context c) {
        super(new PwdKLNGGnomonModel());
    }
}
