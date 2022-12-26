package net.threetag.pantheonsent.network;

import net.threetag.palladiumcore.network.MessageType;
import net.threetag.palladiumcore.network.NetworkManager;
import net.threetag.pantheonsent.PantheonSent;

public class PSNetwork {

    public static final NetworkManager NETWORK = NetworkManager.create(PantheonSent.id("main_channel"));

    public static final MessageType EYE_OF_HORUS_EFFECT = NETWORK.registerS2C("eye_of_horus_effect", EyeOfHorusEffectMessage::new);
    public static final MessageType FORCE_THIRD_PERSON = NETWORK.registerS2C("force_third_person", ForceThirdPersonMessage::new);
    public static final MessageType DARKNESS = NETWORK.registerS2C("darkness", DarknessMessage::new);
    public static final MessageType KHONSHU_TELEPORT = NETWORK.registerS2C("khonshu_teleport", KhonshuTeleportMessage::new);

    public static void init() {
        // nothing, just load class
    }

}
