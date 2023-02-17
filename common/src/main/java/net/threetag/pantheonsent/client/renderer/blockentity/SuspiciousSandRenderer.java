package net.threetag.pantheonsent.client.renderer.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.threetag.pantheonsent.block.SuspiciousSandBlock;
import net.threetag.pantheonsent.block.entity.SuspiciousSandBlockEntity;

public class SuspiciousSandRenderer implements BlockEntityRenderer<SuspiciousSandBlockEntity> {

    private final ItemRenderer itemRenderer;

    public SuspiciousSandRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(SuspiciousSandBlockEntity suspiciousSandBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {
        if (suspiciousSandBlockEntity.getLevel() != null) {
            int k = suspiciousSandBlockEntity.getBlockState().getValue(SuspiciousSandBlock.DUSTED);
            if (k > 0) {
                Direction direction = suspiciousSandBlockEntity.getHitDirection();
                if (direction != null) {
                    ItemStack itemStack = suspiciousSandBlockEntity.getItem();
                    if (!itemStack.isEmpty()) {
                        poseStack.pushPose();
                        poseStack.translate(0.0F, 0.5F, 0.0F);
                        float[] fs = this.translations(direction, k);
                        poseStack.translate(fs[0], fs[1], fs[2]);
                        poseStack.mulPose(Vector3f.YP.rotationDegrees(75.0F));
                        boolean bl = direction == Direction.EAST || direction == Direction.WEST;
                        poseStack.mulPose(Vector3f.YP.rotationDegrees((float)((bl ? 90 : 0) + 11)));
                        poseStack.scale(0.5F, 0.5F, 0.5F);
                        int l = LevelRenderer.getLightColor(suspiciousSandBlockEntity.getLevel(), suspiciousSandBlockEntity.getBlockState(), suspiciousSandBlockEntity.getBlockPos().relative(direction));
                        this.itemRenderer.renderStatic(itemStack, ItemTransforms.TransformType.FIXED, l, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, 0);
                        poseStack.popPose();
                    }
                }
            }
        }
    }

    private float[] translations(Direction direction, int i) {
        float[] fs = new float[]{0.5F, 0.0F, 0.5F};
        float f = (float)i / 10.0F * 0.75F;
        switch (direction) {
            case EAST:
                fs[0] = 0.73F + f;
                break;
            case WEST:
                fs[0] = 0.25F - f;
                break;
            case UP:
                fs[1] = 0.25F + f;
                break;
            case DOWN:
                fs[1] = -0.23F - f;
                break;
            case NORTH:
                fs[2] = 0.25F - f;
                break;
            case SOUTH:
                fs[2] = 0.73F + f;
        }

        return fs;
    }

}
