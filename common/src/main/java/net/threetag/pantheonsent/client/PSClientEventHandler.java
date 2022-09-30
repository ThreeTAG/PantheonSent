package net.threetag.pantheonsent.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.threetag.palladiumcore.event.EventResult;
import net.threetag.palladiumcore.event.InputEvents;

@Environment(EnvType.CLIENT)
public class PSClientEventHandler implements InputEvents.MouseClickedPre {

    public static void init() {
        InputEvents.MOUSE_CLICKED_PRE.register(new PSClientEventHandler());
    }

    @Override
    public EventResult mouseClickedPre(Minecraft client, int button, int action, int mods) {
        //        if (!Ability.getEnabledEntries(client.player, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
//            return EventResult.interruptFalse();
//        }
        return EventResult.pass();
    }
}
