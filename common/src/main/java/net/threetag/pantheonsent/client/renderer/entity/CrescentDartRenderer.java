package net.threetag.pantheonsent.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.client.model.CrescentDartModel;
import net.threetag.pantheonsent.entity.CrescentDart;

public class CrescentDartRenderer extends EntityRenderer<CrescentDart> {

    public static final ResourceLocation TEXTURE = PantheonSent.id("textures/entity/projectiles/crescent_dart.png");
    private final CrescentDartModel model;

    public CrescentDartRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new CrescentDartModel(context.bakeLayer(CrescentDartModel.MODEL_LAYER));
    }

    @Override
    public void render(CrescentDart entity, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
        matrixStack.pushPose();

        matrixStack.translate(0, 0.1F, 0);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(entity.getViewYRot(partialTicks) - 90.0F));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(entity.getViewXRot(partialTicks) + 90.0F));

        float shake = (float) entity.shakeTime - partialTicks;
        if (shake > 0.0F) {
            float f10 = -Mth.sin(shake * 3.0F) * shake;
            matrixStack.mulPose(Vector3f.ZP.rotationDegrees(f10));
        }

        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.prevRotation, entity.rotation) * 100F));
        matrixStack.translate(0, -1.5F + 3.5F / 16F, 0);
        this.model.renderToBuffer(matrixStack, buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);

        matrixStack.popPose();
        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(CrescentDart entity) {
        return TEXTURE;
    }
}
