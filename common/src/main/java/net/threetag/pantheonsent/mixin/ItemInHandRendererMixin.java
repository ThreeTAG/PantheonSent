package net.threetag.pantheonsent.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.threetag.pantheonsent.item.BrushItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Inject(at = @At("HEAD"), method = "renderArmWithItem", cancellable = true)
    private void renderArmWithItem(AbstractClientPlayer player, float partialTicks, float pitch, InteractionHand hand, float swingProgress, ItemStack stack, float equippedProgress, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, CallbackInfo ci) {
        if (player.isUsingItem() && player.getUseItem().getItem() instanceof BrushItem && player.getUseItemRemainingTicks() > 0 && player.getUsedItemHand() == hand) {
            matrixStack.pushPose();
            HumanoidArm arm = (hand == InteractionHand.MAIN_HAND) ? player.getMainArm() : player.getMainArm().getOpposite();
            boolean bl = arm == HumanoidArm.RIGHT;

            int side = bl ? 1 : -1;
            int max = 8 * 16;
            float progress = (player.getUseItemRemainingTicks() - partialTicks) / max;
            float angle = Mth.sin(16 * progress * Mth.PI);

            matrixStack.mulPose(Vector3f.XP.rotationDegrees(-60.0F));
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(-45.0F));

            matrixStack.translate(-0.3D, 0.3D, -1.0D);
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(side * 40.0F * angle));
            matrixStack.translate(-0.2D, 0.2D, 0.0D);

            ((ItemInHandRenderer)(Object) this).renderItem(player, stack, ItemTransforms.TransformType.FIXED, !bl, matrixStack, buffer, combinedLight);
            matrixStack.popPose();
            ci.cancel();
        }
    }

}
