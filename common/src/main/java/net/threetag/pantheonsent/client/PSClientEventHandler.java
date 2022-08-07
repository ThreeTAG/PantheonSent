package net.threetag.pantheonsent.client;

import dev.architectury.event.EventResult;
import dev.architectury.event.events.client.ClientRawInputEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.threetag.palladium.power.ability.Ability;
import net.threetag.pantheonsent.ability.PSAbilities;

@Environment(EnvType.CLIENT)
public class PSClientEventHandler implements ClientRawInputEvent.MouseClicked {

    public static void init() {
        ClientRawInputEvent.MOUSE_CLICKED_PRE.register(new PSClientEventHandler());
    }

    @Override
    public EventResult mouseClicked(Minecraft client, int button, int action, int mods) {
        if (!Ability.getEnabledEntries(client.player, PSAbilities.MOON_KNIGHT_BLOCKING.get()).isEmpty()) {
//            return EventResult.interruptFalse();
        }
        return EventResult.pass();
    }
}
