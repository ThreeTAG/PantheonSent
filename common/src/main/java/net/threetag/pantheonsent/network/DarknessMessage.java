package net.threetag.pantheonsent.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.FriendlyByteBuf;
import net.threetag.palladiumcore.network.MessageContext;
import net.threetag.palladiumcore.network.MessageS2C;
import net.threetag.palladiumcore.network.MessageType;
import net.threetag.pantheonsent.client.PSClientEventHandler;
import org.jetbrains.annotations.NotNull;

public class DarknessMessage extends MessageS2C {

    private final int ticks;

    public DarknessMessage(int ticks) {
        this.ticks = ticks;
    }

    public DarknessMessage(FriendlyByteBuf buf) {
        this.ticks = buf.readInt();
    }

    @NotNull
    @Override
    public MessageType getType() {
        return PSNetwork.DARKNESS;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.ticks);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void handle(MessageContext context) {
        PSClientEventHandler.DARKNESS_TICKS = this.ticks;
    }
}
