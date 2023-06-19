package net.threetag.pantheonsent.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.threetag.palladium.client.model.CapedHumanoidModel;
import net.threetag.palladium.client.model.ExtraAnimatedModel;
import net.threetag.pantheonsent.client.model.animation.BlockingAnimation;
import net.threetag.pantheonsent.client.model.animation.GlidingAnimation;

import java.util.function.Function;

public class MoonKnightCapeModel<T extends LivingEntity> extends CapedHumanoidModel<T> implements ExtraAnimatedModel<T> {

    public final ModelPart left1, left2, left3;
    public final ModelPart right1, right2, right3;

    public MoonKnightCapeModel(ModelPart modelPart) {
        super(modelPart);
        this.left1 = this.cape.getChild("left1");
        this.left2 = this.left1.getChild("left2");
        this.left3 = this.left2.getChild("left3");
        this.right1 = this.cape.getChild("right1");
        this.right2 = this.right1.getChild("right2");
        this.right3 = this.right2.getChild("right3");
    }

    public MoonKnightCapeModel(ModelPart modelPart, Function<ResourceLocation, RenderType> function) {
        super(modelPart, function);
        this.left1 = this.cape.getChild("left1");
        this.left2 = this.left1.getChild("left2");
        this.left3 = this.left2.getChild("left3");
        this.right1 = this.cape.getChild("right1");
        this.right2 = this.right1.getChild("right2");
        this.right3 = this.right2.getChild("right3");
    }

    @Override
    public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
        this.extraAnimations(entity, limbSwing, limbSwingAmount, 0, 0, 0, partialTicks);
    }

    @Override
    public void extraAnimations(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks) {
        super.extraAnimations(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks);
        this.left1.yRot = 0;
        this.left2.yRot = 0;
        this.left3.yRot = 0;
        this.right1.yRot = 0;
        this.right2.yRot = 0;
        this.right3.yRot = 0;
        float fold = 0F;

        if (GlidingAnimation.INSTANCE.getProgress(entity, partialTicks) > 0F) {
            float progress = GlidingAnimation.INSTANCE.getProgress(entity, partialTicks);
            this.cape.xRot += (Math.toRadians(GlidingAnimation.INSTANCE.getCapeRotation()) - this.cape.xRot) * progress;
            fold = (float) (Math.toRadians(5F) * progress);
        }

        this.left1.yRot = -fold;
        this.left2.yRot = -fold;
        this.left3.yRot = -fold;
        this.right1.yRot = fold;
        this.right2.yRot = fold;
        this.right3.yRot = fold;

        if (BlockingAnimation.INSTANCE.getProgress(entity, partialTicks) > 0F) {
            float progress = BlockingAnimation.INSTANCE.getProgress(entity, partialTicks);
            KhonshuModel.interpolateXRotTo(this.cape, 0, progress);
            KhonshuModel.interpolateYRotTo(this.left1, (float) Math.toRadians(-25F), progress);
            KhonshuModel.interpolateYRotTo(this.left2, (float) Math.toRadians(-90F), progress);
            KhonshuModel.interpolateYRotTo(this.left3, (float) Math.toRadians(-45F), progress);
            KhonshuModel.interpolateYRotTo(this.right1, (float) Math.toRadians(25F), progress);
            KhonshuModel.interpolateYRotTo(this.right2, (float) Math.toRadians(90F), progress);
            KhonshuModel.interpolateYRotTo(this.right3, (float) Math.toRadians(45F), progress);
        }
    }

    // Code to cut the texture down, I was too lazy to do it by hand

//    public static void main(String[] args) {
//        String path = "F:\\Development\\PantheonSent\\common\\src\\main\\resources\\assets\\pantheonsent\\textures\\models\\alpha_masks\\moon_knight_cape_transformation";
//        File directory = new File(path);
//
//        for (File file : Objects.requireNonNull(directory.listFiles((dir, name) -> name.endsWith(".png")))) {
//            if(!file.isDirectory()) {
//                System.out.println(file.getAbsolutePath());
//                try {
//                    BufferedImage origImg = ImageIO.read(file);
//                    BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
//                    Graphics2D newG = image.createGraphics();
//
//                    // From old to v1
////                    copyArea(origImg, newG, 18, 0, 6, 32, 0, 0);
////                    copyArea(origImg, newG, 12, 0, 6, 32, 12, 0);
////                    copyArea(origImg, newG, 7, 0, 5, 32, 24, 0);
////                    copyArea(origImg, newG, 0, 0, 7, 32, 34, 0);
////                    copyArea(origImg, newG, 24, 0, 6, 32, 0, 32);
////                    copyArea(origImg, newG, 30, 0, 5, 32, 12, 32);
////                    copyArea(origImg, newG, 35, 0, 7, 32, 22, 32);
//
//                    // From v1 to v2
//                    copyArea(origImg, newG, 0, 0, 6, 32, 6, 0);
//                    copyArea(origImg, newG, 12, 0, 6, 32, 6, 32);
//                    copyArea(origImg, newG, 24, 0, 5, 32, 17, 32);
//                    copyArea(origImg, newG, 34, 0, 7, 32, 29, 32);
//                    copyArea(origImg, newG, 0, 32, 6, 32, 18, 0);
//                    copyArea(origImg, newG, 12, 32, 5, 32, 29, 0);
//                    copyArea(origImg, newG, 22, 32, 7, 32, 41, 0);
//
//                    // 10
//                    // 70
//                    // 90
//
//                    if (ImageIO.write(image, "png", new File(path + "\\converted\\" + file.getName()))) {
//                        System.out.println("-- saved");
//                    }
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//    }
//
//    public static void copyArea(BufferedImage origImg, Graphics2D newG, int x, int y, int width, int height, int newX, int newY) {
//        var sub = origImg.getSubimage(x, y, width, height);
//        newG.drawImage(origImg, newX, newY, newX + width, newY + height, x, y, x + width, y + height, null);
//    }

}
