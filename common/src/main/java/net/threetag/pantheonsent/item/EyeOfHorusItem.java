package net.threetag.pantheonsent.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.threetag.palladium.item.CurioTrinketItem;
import net.threetag.palladium.util.PlayerUtil;
import net.threetag.palladiumcore.item.IPalladiumItem;

public class EyeOfHorusItem extends CurioTrinketItem implements IPalladiumItem {

    public EyeOfHorusItem(Properties properties) {
        super(properties);
    }

    @Override
    public EquipmentSlot getEquipmentSlot(ItemStack stack) {
        return EquipmentSlot.CHEST;
    }

    @Override
    public void armorTick(ItemStack stack, Level level, Player player) {
        this.tick(player, stack);
    }

    @Override
    public void tick(LivingEntity entity, ItemStack stack) {
        if (entity instanceof Player player && !player.getCooldowns().isOnCooldown(this) && entity.getHealth() <= 6F) {
            entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2));
            player.getCooldowns().addCooldown(this, 20 * 30);
            // TODO sound
            stack.hurtAndBreak(1, player, player1 -> {
                if (!player1.isSilent()) {
                    PlayerUtil.playSoundToAll(player1.level, player1.getX(), player1.getEyeY(), player1.getZ(), 50, SoundEvents.ITEM_BREAK, player1.getSoundSource(), 0.8F, 0.8F + player1.level.random.nextFloat() * 0.4F);
                }
            });
        }
    }

    @Override
    public boolean canRightClickEquip() {
        return true;
    }
}
