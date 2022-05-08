package net.threetag.pantheonsent.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.threetag.pantheonsent.block.entity.BrushableBlockEntity;

import java.util.Random;

public class BrushableBlockEntityRenderer implements BlockEntityRenderer<BrushableBlockEntity> {

    public BrushableBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(BrushableBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        ItemStack stack = blockEntity.getItem();

        if (!stack.isEmpty()) {
            Random random = new Random(blockEntity.getBlockPos().asLong());
            BakedModel bakedModel = Minecraft.getInstance().getItemRenderer().getModel(stack, blockEntity.getLevel(), null, 0);
            poseStack.pushPose();
            poseStack.translate(0.5F, 4.5F / 16F, 8F / 16F);
            poseStack.mulPose(Vector3f.YP.rotationDegrees(random.nextInt(4) * 90));
            poseStack.mulPose(Vector3f.XN.rotationDegrees(90F));
            if(!bakedModel.isGui3d()) {
                poseStack.scale(0.5F, 0.5F, 0.5F);
            }
            Minecraft.getInstance().getItemRenderer().render(stack, ItemTransforms.TransformType.FIXED, false, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY, bakedModel);
            poseStack.popPose();
        }
    }

}
