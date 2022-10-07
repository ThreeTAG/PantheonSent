package net.threetag.pantheonsent.network;

import net.threetag.palladiumcore.network.MessageType;
import net.threetag.palladiumcore.network.NetworkManager;
import net.threetag.pantheonsent.PantheonSent;

public class PSNetwork {

    public static final NetworkManager NETWORK = NetworkManager.create(PantheonSent.id("main_channel"));

    public static final MessageType EYE_OF_HORUS_EFFECT = NETWORK.registerS2C("eye_of_horus_effect", EyeOfHorusEffectMessage::new);

    public static void init() {
        // nothing, just load class
    }

}
