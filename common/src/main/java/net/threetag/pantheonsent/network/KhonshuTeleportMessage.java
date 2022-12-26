package net.threetag.pantheonsent.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.phys.Vec3;
import net.threetag.palladiumcore.network.MessageContext;
import net.threetag.palladiumcore.network.MessageS2C;
import net.threetag.palladiumcore.network.MessageType;
import net.threetag.pantheonsent.entity.Khonshu;
import org.jetbrains.annotations.NotNull;

public class KhonshuTeleportMessage extends MessageS2C {

    private final int entityId;
    private final Vec3 position;

    public KhonshuTeleportMessage(Khonshu khonshu, Vec3 position) {
        this.entityId = khonshu.getId();
        this.position = position;
    }

    public KhonshuTeleportMessage(FriendlyByteBuf buf) {
        this.entityId = buf.readInt();
        this.position = new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    @NotNull
    @Override
    public MessageType getType() {
        return PSNetwork.KHONSHU_TELEPORT;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.entityId);
        buf.writeDouble(this.position.x);
        buf.writeDouble(this.position.y);
        buf.writeDouble(this.position.z);
    }

    @Override
    public void handle(MessageContext context) {
        this.handleClient();
    }

    @Environment(EnvType.CLIENT)
    public void handleClient() {
        var entity = Minecraft.getInstance().level.getEntity(this.entityId);

        if (entity instanceof Khonshu khonshu) {
            khonshu.moveTo(this.position);
        }
    }
}
