package fr.takkers.crst.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import fr.takkers.crst.CRST;
import fr.takkers.crst.entity.custom.ShadowWalkerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.annotation.Nullable;

public class ShadowWalkerRenderer extends GeoEntityRenderer<ShadowWalkerEntity> {
    public ShadowWalkerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ShadowWalkerModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public ResourceLocation getTextureLocation(ShadowWalkerEntity instance) {
        return new ResourceLocation(CRST.MODID, "textures/entity/shadow_walker.png");
    }

    @Override
    public RenderType getRenderType(ShadowWalkerEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

}