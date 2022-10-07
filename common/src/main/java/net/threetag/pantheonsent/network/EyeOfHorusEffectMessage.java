package net.threetag.pantheonsent.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.threetag.palladiumcore.network.MessageContext;
import net.threetag.palladiumcore.network.MessageS2C;
import net.threetag.palladiumcore.network.MessageType;
import net.threetag.pantheonsent.client.screen.EyeOfHorusOverlay;
import org.jetbrains.annotations.NotNull;

public class EyeOfHorusEffectMessage extends MessageS2C {

    public EyeOfHorusEffectMessage() {
    }

    public EyeOfHorusEffectMessage(FriendlyByteBuf buf) {

    }

    @NotNull
    @Override
    public MessageType getType() {
        return PSNetwork.EYE_OF_HORUS_EFFECT;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {

    }

    @Override
    public void handle(MessageContext context) {
        handleClient();
    }

    @Environment(EnvType.CLIENT)
    public void handleClient() {
        EyeOfHorusOverlay.start();
    }

}
