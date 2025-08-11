package awildgoose.particlesystem.particle;

import awildgoose.particlesystem.provider.CustomParticleData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class CustomParticle extends AnimatedParticle {
    private CustomParticleData data;

    CustomParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 0.05F);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.maxAge = 20;
        this.scale = 1.0f;
        this.setData(CustomParticleData.DEFAULT);
    }

    private void updateSprite() {
        int totalFrames = 8;
        int currentFrame = 1 + this.data.texture.asNum();
        this.setSprite(spriteProvider.getSprite(currentFrame, totalFrames));
    }

    private void applyData(float tickProgress) {
        this.scale = this.data.size.getValue(tickProgress);
        this.maxAge = this.data.lifetime;
        this.updateSprite();
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float tickProgress) {
        this.applyData(tickProgress);
        super.render(vertexConsumer, camera, tickProgress);
    }

    @Override
    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Override
    public void tick() {
        super.tick();
        this.data.tick();
    }

    public void setData(CustomParticleData data) {
        this.data = data;
        this.applyData(0);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new CustomParticle(clientWorld, d, e, f, g, h, i, this.spriteProvider);
        }
    }
}
