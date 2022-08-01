package net.threetag.pantheonsent.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.inventory.RestorationMenu;

@Environment(EnvType.CLIENT)
public class RestorationScreen extends ItemCombinerScreen<RestorationMenu> {

    private static final ResourceLocation TEXTURE = PantheonSent.id("textures/gui/restoration.png");

    public RestorationScreen(RestorationMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component, TEXTURE);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.renderLabels(poseStack, mouseX, mouseY);
    }
}
