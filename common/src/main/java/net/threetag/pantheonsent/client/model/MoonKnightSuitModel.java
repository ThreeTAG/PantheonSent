package net.threetag.pantheonsent.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.function.Function;

public class MoonKnightSuitModel<T extends LivingEntity> extends HumanoidModel<T> {

    public final ModelPart loinCloth;
    public final ModelPart cape;

    public MoonKnightSuitModel(ModelPart modelPart) {
        super(modelPart);
        this.loinCloth = this.body.getChild("loincloth");
        this.cape = this.body.getChild("cape");
    }

    public MoonKnightSuitModel(ModelPart modelPart, Function<ResourceLocation, RenderType> function) {
        super(modelPart, function);
        this.loinCloth = this.body.getChild("loincloth");
        this.cape = this.body.getChild("cape");
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.loinCloth.xRot = -Math.max(0.1F, Math.max(this.leftLeg.xRot, this.rightLeg.xRot) * 1.5F);
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);

        if (entity instanceof Player player) {
            double d0 = Mth.lerp(partialTicks, player.xCloakO, player.xCloak) - Mth.lerp(partialTicks, entity.xo, entity.getX());
            double d1 = Mth.lerp(partialTicks, player.yCloakO, player.yCloak) - Mth.lerp(partialTicks, entity.yo, entity.getY());
            double d2 = Mth.lerp(partialTicks, player.zCloakO, player.zCloak) - Mth.lerp(partialTicks, entity.zo, entity.getZ());
            float f = entity.yBodyRotO + (entity.yBodyRot - entity.yBodyRotO);
            double d3 = Mth.sin(f * ((float) Math.PI / 180F));
            double d4 = -Mth.cos(f * ((float) Math.PI / 180F));
            float f1 = (float) d1 * 10.0F;
            f1 = Mth.clamp(f1, -6.0F, 32.0F);
            float f2 = (float) (d0 * d3 + d2 * d4) * 100.0F;
            f2 = Mth.clamp(f2, 0.0F, 150.0F);
            if (f2 < 0.0F) {
                f2 = 0.0F;
            }

            float f4 = Mth.lerp(partialTicks, player.oBob, player.bob);
            f1 = f1 + Mth.sin(Mth.lerp(partialTicks, entity.walkDistO, entity.walkDist) * 6.0F) * 32.0F * f4;

            float rotation = 6.0F + f2 / 2.0F + f1;
            this.cape.xRot = (float) Math.toRadians(rotation);
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        this.cape.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
