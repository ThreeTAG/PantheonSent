package net.threetag.pantheonsent.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.threetag.palladiumcore.event.ClientTickEvents;
import net.threetag.palladiumcore.event.EventResult;
import net.threetag.palladiumcore.event.InputEvents;

@Environment(EnvType.CLIENT)
public class PSClientEventHandler implements InputEvents.MouseClickedPre, ClientTickEvents.ClientLevelTick {

    public static int THIRD_PERSON_TICKS = 0;
    public static int DARKNESS_TICKS = 0;
    private static int DARKNESS = 0;
    private static int PREV_DARKNESS = 0;

    public static void init() {
        var instance = new PSClientEventHandler();
        InputEvents.MOUSE_CLICKED_PRE.register(instance);
        ClientTickEvents.CLIENT_LEVEL_POST.register(instance);
    }

    @Override
    public EventResult mouseClickedPre(Minecraft client, int button, int action, int mods) {
        //        if (!Ability.getEnabledEntries(client.player, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
//            return EventResult.interruptFalse();
//        }
        return EventResult.pass();
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
}
