package net.threetag.pantheonsent.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.model.ExtraAnimatedModel;
import net.threetag.pantheonsent.util.PantheonSentProperties;

import java.util.function.Function;

public class MoonKnightSuitModel<T extends LivingEntity> extends HumanoidModel<T> implements ExtraAnimatedModel<T> {

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
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        this.extraAnimations(entity, limbSwing, limbSwingAmount, 0, 0, 0, partialTicks);
    }

    @Override
    public void extraAnimations(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks) {
        int timer = PantheonSentProperties.KHONSHU_RECRUITING_TIMER.get(entity);
        if(timer > 0) {
            this.loinCloth.xRot = this.leftLeg.xRot;
        } else {
            this.loinCloth.xRot = -Math.max(0.1F, Math.max(this.leftLeg.xRot, this.rightLeg.xRot) * 1.5F);
        }
    }
}
