package net.threetag.pantheonsent.mixin.fabric;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.material.FogType;
import net.threetag.pantheonsent.client.PSClientEventHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Shadow
    private static float fogRed;

    @Shadow
    private static float fogGreen;

    @Shadow
    private static float fogBlue;

    @Inject(method = "setupFog", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void setupFog(Camera camera, FogRenderer.FogMode fogMode, float farPlaneDistance, boolean bl, float partialTick, CallbackInfo ci, FogType fogType, Entity entity, FogRenderer.FogData fogData) {
        float darkness = PSClientEventHandler.getDarkness(partialTick);

        if (darkness > 0F) {
            var far = 3F - fogData.end;
            var near = 1F - fogData.start;
            RenderSystem.setShaderFogStart(fogData.start + near * darkness);
            RenderSystem.setShaderFogEnd(fogData.end + far * darkness);
            RenderSystem.setShaderFogShape(FogShape.SPHERE);
        }
    }

    @Inject(method = "setupColor", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;clearColor(FFFF)V", ordinal = 1))
    private static void setupColor(Camera activeRenderInfo, float partialTicks, ClientLevel level, int renderDistanceChunks, float bossColorModifier, CallbackInfo ci) {
        float darkness = PSClientEventHandler.getDarkness(partialTicks);

        if (darkness > 0F) {
            darkness = 1F - darkness;
            fogRed *= darkness;
            fogGreen *= darkness;
            fogBlue *= darkness;
        }
    }

}
