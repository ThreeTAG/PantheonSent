package net.threetag.pantheonsent;

import dev.architectury.event.CompoundEventResult;
import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.threetag.pantheonsent.network.EyeOfHorusEffectMessage;

public class PantheonSentDebug {

    public static void init() {
//        LivingEntityEvents.TICK.register(entity -> {
//            if (entity instanceof Player player) {
//                AbilityReference reference = new AbilityReference(PantheonSent.id("moon_knight"), "throw_dart");
//                var dependency = reference.getEntry(player);
//                var res = dependency != null && (dependency.cooldown > 0);
//                player.displayClientMessage(Component.literal(String.valueOf(res)), true);
//            }
//        });

        InteractionEvent.RIGHT_CLICK_ITEM.register((player, hand) -> {
            if (player.getItemInHand(hand).is(Items.STICK) && player instanceof ServerPlayer serverPlayer) {
                new EyeOfHorusEffectMessage().send(serverPlayer);
            }
            return CompoundEventResult.pass();
        });
    }

}
