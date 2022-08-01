package net.threetag.pantheonsent.client.renderer.item;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.threetag.palladium.client.renderer.item.CurioTrinketRenderer;
import net.threetag.pantheonsent.PantheonSent;
import net.threetag.pantheonsent.item.PSItems;

@Environment(EnvType.CLIENT)
public class EyeOfHorusRenderer extends RenderLayer<LivingEntity, EntityModel<LivingEntity>> implements CurioTrinketRenderer {

    public static final ResourceLocation TEXTURE = PantheonSent.id("textures/models/eye_of_horus.png");
    public static final ResourceLocation TEXTURE_LIGHT = PantheonSent.id("textures/models/eye_of_horus_light.png");

    public EyeOfHorusRenderer(RenderLayerParent<LivingEntity, EntityModel<LivingEntity>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, PoseStack poseStack, EntityModel<? extends LivingEntity> entityModel, LivingEntity entity, MultiBufferSource bufferSource, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        this.render(poseStack, entityModel, entity, bufferSource, light);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (livingEntity.getItemBySlot(EquipmentSlot.CHEST).getItem() == PSItems.EYE_OF_HORUS.get()) {
            this.render(poseStack, this.getParentModel(), livingEntity, bufferSource, packedLight);
        }
    }

    public void render(PoseStack poseStack, EntityModel<? extends LivingEntity> entityModel, LivingEntity entity, MultiBufferSource bufferSource, int light) {
        var buffer = bufferSource.getBuffer(entityModel.renderType(TEXTURE));
        entityModel.renderToBuffer(poseStack, buffer, light, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);

        if (entity.getHealth() <= 6F && entity.hasEffect(MobEffects.REGENERATION)) {
            buffer = bufferSource.getBuffer(RenderType.eyes(TEXTURE_LIGHT));
            entityModel.renderToBuffer(poseStack, buffer, light, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
        }
    }
}
