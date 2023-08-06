package net.threetag.pantheonsent.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.NotNull;

public class HieroglyphParticle extends TextureSheetParticle {

    private final double xStart;
    private final double yStart;
    private final double zStart;

    protected HieroglyphParticle(ClientLevel clientLevel, double d, double e, double f, double g, double h, double i) {
        super(clientLevel, d, e, f);

        this.xd = g;
        this.yd = h;
        this.zd = i;
        this.xStart = d;
        this.yStart = e;
        this.zStart = f;
        this.xo = d + g;
        this.yo = e + h;
        this.zo = f + i;
        this.x = this.xo;
        this.y = this.yo;
        this.z = this.zo;
        this.quadSize = 0.1F * (this.random.nextFloat() * 0.5F + 0.2F);
        float j = this.random.nextFloat() * 0.6F + 0.4F;
        this.rCol = 0.9F * j;
        this.gCol = 0.9F * j;
        this.bCol = j;
        this.hasPhysics = false;
        this.lifetime = (int) (Math.random() * 10.0) + 30;
    }

    @Override
    public @NotNull ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void move(double x, double y, double z) {
        this.setBoundingBox(this.getBoundingBox().move(x, y, z));
        this.setLocationFromBoundingbox();
    }

    @Override
    public int getLightColor(float partialTick) {
        int i = super.getLightColor(partialTick);
        float f = (float) this.age / (float) this.lifetime;
        f *= f;
        f *= f;
        int j = i & 0xFF;
        int k = i >> 16 & 0xFF;
        k += (int) (f * 15.0F * 16.0F);
        if (k > 240) {
            k = 240;
        }

        return j | k << 16;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            float f = (float) this.age / (float) this.lifetime;
            f = 1.0F - f;
            float g = 1.0F - f;
            g *= g;
            g *= g;
            this.x = this.xStart + this.xd * (double) f;
            this.y = this.yStart + this.yd * (double) f - (double) (g * 1.2F);
            this.z = this.zStart + this.zd * (double) f;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {

        private final SpriteSet sprite;

        public Provider(SpriteSet spriteSet) {
            this.sprite = spriteSet;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            HieroglyphParticle enchantmentTableParticle = new HieroglyphParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            enchantmentTableParticle.pickSprite(this.sprite);
            return enchantmentTableParticle;
        }
    }
}
