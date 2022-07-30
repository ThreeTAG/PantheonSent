package net.threetag.pantheonsent;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.world.item.Items;
import net.threetag.pantheonsent.entity.Khonshu;

public class PantheonSentDebug {

    public static void init() {
        InteractionEvent.RIGHT_CLICK_ITEM.register((player, hand) -> {
            if (!player.level.isClientSide && player.getItemInHand(hand).getItem() == Items.BONE) {
                Khonshu khonshu = new Khonshu(player.level, player, true);
                player.level.addFreshEntity(khonshu);
            }
            return CompoundEventResult.pass();
        });
    }

}
