package net.threetag.pantheonsent.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.client.model.KhonshuModel;
import net.threetag.pantheonsent.entity.Khonshu;

public class KhonshuRenderer extends MobRenderer<Khonshu, KhonshuModel<Khonshu>> {

    public static final ResourceLocation TEXTURE = PantheonSent.id("textures/entity/khonshu.png");

    public KhonshuRenderer(EntityRendererProvider.Context context) {
        super(context, new KhonshuModel(context.bakeLayer(KhonshuModel.MODEL_LAYER)), 0.7F);
    }

    @Override
    protected void scale(Khonshu livingEntity, PoseStack matrixStack, float partialTickTime) {

    }

    @Override
    public boolean shouldRender(Khonshu livingEntity, Frustum camera, double camX, double camY, double camZ) {
        return true;
//        return livingEntity.avatarId != null && livingEntity.avatarId.equals(Minecraft.getInstance().player.getUUID()) && super.shouldRender(livingEntity, camera, camX, camY, camZ);
    }

    @Override
    public ResourceLocation getTextureLocation(Khonshu entity) {
        return TEXTURE;
    }
}
