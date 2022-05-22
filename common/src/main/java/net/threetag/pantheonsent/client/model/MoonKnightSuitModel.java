package net.threetag.pantheonsent.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;

public class MoonKnightSuitModel<T extends LivingEntity> extends HumanoidModel<T> {

    public final ModelPart loinCloth;

    public MoonKnightSuitModel(ModelPart modelPart) {
        super(modelPart);
        this.loinCloth = this.body.getChild("loincloth");
    }

    public MoonKnightSuitModel(ModelPart modelPart, Function<ResourceLocation, RenderType> function) {
        super(modelPart, function);
        this.loinCloth = this.body.getChild("loincloth");
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        this.loinCloth.xRot = -Math.max(0.1F, Math.max(this.leftLeg.xRot, this.rightLeg.xRot) * 1.5F);
    }
}
