package fr.takkers.crst.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import fr.takkers.crst.CRST;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ArtefactExtractorScreen extends AbstractContainerScreen<ArtefactExtractorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CRST.MODID, "textures/gui/artefact_extractor_gui.png");

    public ArtefactExtractorScreen(ArtefactExtractorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        //this.blit(guiGraphics, x, y, 0, 0, imageWidth, imageHeight);
        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        if(menu.isCrafting()) {
            //blit(guiGraphics, x + 102, y + 41, 176, 0, 8, menu.getScaledProgress());
            guiGraphics.blit(TEXTURE, x + 102, y + 41, 176, 0, 8, menu.getScaledProgress());
        }
    }
}