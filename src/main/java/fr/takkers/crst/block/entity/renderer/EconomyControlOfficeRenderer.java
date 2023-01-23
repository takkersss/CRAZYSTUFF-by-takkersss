package fr.takkers.crst.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import fr.takkers.crst.block.custom.EconomyControlOffice;
import fr.takkers.crst.block.entity.custom.EconomyControlOfficeBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class EconomyControlOfficeRenderer implements BlockEntityRenderer<EconomyControlOfficeBlockEntity> {
    public EconomyControlOfficeRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(EconomyControlOfficeBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getRenderStack();
        pPoseStack.pushPose();
        pPoseStack.translate(0.25f, 0.64f, 0.5f);
        pPoseStack.scale(0.25f, 0.25f, 0.25f);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(270));

        switch (pBlockEntity.getBlockState().getValue(EconomyControlOffice.FACING)) {
            case NORTH -> pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
            case EAST -> pPoseStack.mulPose(Axis.ZP.rotationDegrees(90));
            case SOUTH -> pPoseStack.mulPose(Axis.ZP.rotationDegrees(0));
            case WEST -> pPoseStack.mulPose(Axis.ZP.rotationDegrees(270));
        }

        switch (pBlockEntity.getBlockState().getValue(EconomyControlOffice.FACING)) {
            case NORTH -> pPoseStack.translate(0f, 1f, 0f); //south
            case SOUTH -> pPoseStack.translate(1f, 1f, 0f); //north
            case EAST -> pPoseStack.translate(0f, 0f, 0f); // east
            case WEST -> pPoseStack.translate(-1f, 2f, 0f); //west
        }

        itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.GUI, getLightLevel(pBlockEntity.getLevel(),
                        pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY, pPoseStack, pBufferSource, 1);
        pPoseStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}