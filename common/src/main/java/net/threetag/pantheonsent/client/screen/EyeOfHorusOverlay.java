package net.threetag.pantheonsent.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.threetag.palladium.client.model.animation.AnimationUtil;
import net.threetag.palladium.util.Easing;
import net.threetag.palladiumcore.event.ClientTickEvents;
import net.threetag.palladiumcore.registry.client.OverlayRegistry;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.sound.PSSoundEvents;

import java.util.Objects;

public class EyeOfHorusOverlay implements OverlayRegistry.IngameOverlay, ClientTickEvents.ClientTick {

    public static final ResourceLocation TEXTURE = PantheonSent.id("textures/gui/eye_of_horus_effect.png");
    private static int TIMER = 0;

    public EyeOfHorusOverlay() {
        ClientTickEvents.CLIENT_PRE.register(this);
    }

    @Override
    public void render(Minecraft minecraft, Gui gui, GuiGraphics guiGraphics, float partialTicks, int width, int height) {
        if (TIMER < 0) {
            return;
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        float timer = TIMER + partialTicks;
        float f = (timer / 2F) % 32;
        float transparency = AnimationUtil.ease(Easing.INOUTSINE, timer <= 32 ? timer / 32F : 1F - ((timer - 32F) / 32F));
        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(width / 2F - 32, height / 2F - 32, 0);
        guiGraphics.pose().scale(4F, 4F, 1F);
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1F, 1F, 1F, transparency);
        guiGraphics.blit(TEXTURE, 0, 0, 0, (int) f * 16, 16, 16, 16, 512);
        guiGraphics.pose().popPose();
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
    }

    @Override
    public void clientTick(Minecraft minecraft) {
        if (TIMER >= 0) {
            if (TIMER == 64) {
                TIMER = -1;
            } else {
                TIMER++;
            }
        }
    }

    public static void start() {
        var pos = Objects.requireNonNull(Minecraft.getInstance().player).getEyePosition();
        Minecraft.getInstance().getSoundManager().play(new SimpleSoundInstance(PSSoundEvents.EYE_OF_HORUS.get(), SoundSource.PLAYERS, 1F, 1F, RandomSource.create(), pos.x, pos.y, pos.z));
        TIMER = 0;
    }
}
