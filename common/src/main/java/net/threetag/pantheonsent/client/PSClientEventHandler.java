package net.threetag.pantheonsent.client;

import com.mojang.blaze3d.shaders.FogShape;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.level.material.FogType;
import net.threetag.palladiumcore.event.ClientTickEvents;
import net.threetag.palladiumcore.event.EventResult;
import net.threetag.palladiumcore.event.ViewportEvents;

import java.util.concurrent.atomic.AtomicReference;

@Environment(EnvType.CLIENT)
public class PSClientEventHandler implements ClientTickEvents.ClientLevelTick, ViewportEvents.RenderFog, ViewportEvents.ComputeFogColor {

    public static int THIRD_PERSON_TICKS = 0;
    public static int DARKNESS_TICKS = 0;
    private static int DARKNESS = 0;
    private static int PREV_DARKNESS = 0;

    public static void init() {
        var instance = new PSClientEventHandler();
        ClientTickEvents.CLIENT_LEVEL_POST.register(instance);
        ViewportEvents.RENDER_FOG.register(instance);
        ViewportEvents.COMPUTE_FOG_COLOR.register(instance);
    }

    @Override
    public void clientLevelTick(Minecraft minecraft, ClientLevel clientLevel) {
        if (minecraft.player != null) {

            if (THIRD_PERSON_TICKS > 0) {
                THIRD_PERSON_TICKS--;
                minecraft.options.setCameraType(THIRD_PERSON_TICKS == 0 ? CameraType.FIRST_PERSON : CameraType.THIRD_PERSON_FRONT);
            }

            PREV_DARKNESS = DARKNESS;
            if (DARKNESS_TICKS > 0 && DARKNESS < 20) {
                DARKNESS++;
            } else if (DARKNESS_TICKS <= 0 && DARKNESS > 0) {
                DARKNESS--;
            }
            DARKNESS_TICKS--;
        }
    }

    public static float getDarkness(double partialTick) {
        return (float) Mth.lerp(partialTick, PREV_DARKNESS, DARKNESS) / 20F;
    }

    @Override
    public EventResult renderFog(GameRenderer gameRenderer, Camera camera, double partialTick, FogRenderer.FogMode fogMode, FogType fogType, AtomicReference<Float> farPlaneDistance, AtomicReference<Float> nearPlaneDistance, AtomicReference<FogShape> fogShape) {
        float darkness = PSClientEventHandler.getDarkness(partialTick);

        if (darkness > 0F) {
            var far = 3F - farPlaneDistance.get();
            var near = 1F - nearPlaneDistance.get();
            fogShape.set(FogShape.SPHERE);
            farPlaneDistance.set(farPlaneDistance.get() + far * darkness);
            nearPlaneDistance.set(nearPlaneDistance.get() + near * darkness);
            return EventResult.cancel();
        }

        return EventResult.pass();
    }

    @Override
    public void computeFogColor(GameRenderer gameRenderer, Camera camera, double partialTick, AtomicReference<Float> red, AtomicReference<Float> green, AtomicReference<Float> blue) {
        float darkness = PSClientEventHandler.getDarkness(partialTick);

        if (darkness > 0F) {
            darkness = 1F - darkness;
            blue.set(blue.get() * darkness);
            green.set(green.get() * darkness);
            red.set(red.get() * darkness);
        }
    }
}
