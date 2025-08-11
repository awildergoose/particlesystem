package awildgoose.particlesystem.particle;

import awildgoose.particlesystem.action.Action;
import awildgoose.particlesystem.action.ActionCallPosition;
import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.animated.Easing;
import awildgoose.particlesystem.provider.CustomParticleData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class CustomParticle extends AnimatedParticle {
    private CustomParticleData data;
    private final Vec3d startPos;

    CustomParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 0F);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.startPos = this.getPos();

        this.setData(CustomParticleData.DEFAULT);
        this.data.gravity = new AnimatedFloat(Easing.LINEAR, 1.0f);
    }

    public int getAge() {
        return this.age;
    }
    public Vec3d getPos() {
        return new Vec3d(this.x, this.y, this.z);
    }
    public Vec3d getPrevPos() {
        return new Vec3d(this.lastX, this.lastY, this.lastZ);
    }
    public Vec3d getStartPos() {
        return this.startPos;
    }

    public void setPos(Vec3d pos) {
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
    }

    public boolean callActions(ArrayList<Action> actions, ActionCallPosition position, float tickProgress) {
        AtomicBoolean suppress = new AtomicBoolean(false);

        actions.forEach((v) -> {
            if (v.getPosition() == position)  {
                if (v.run(this, tickProgress))
                    suppress.set(true);
            }
        });

        return suppress.get();
    }

    private void updateSprite() {
        int totalFrames = 8;
        int currentFrame = 1 + this.data.texture.asNum();
        this.setSprite(spriteProvider.getSprite(currentFrame, totalFrames));
    }

    private void applyData(float tickProgress) {
        this.scale = this.data.size.getValue(tickProgress);
        this.gravityStrength = this.data.gravity.getValue(tickProgress);
        this.maxAge = this.data.lifetime;
        this.setColor(this.data.color.getValue(tickProgress).getRGB());
        this.updateSprite();
    }

    @Override
    public BillboardParticle.Rotator getRotator() {
        return (quaternion, camera, tickProgress) -> {
            quaternion.set(camera.getRotation());
            quaternion.rotateZ(this.data.angle.getValue(tickProgress));
        };
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float tickProgress) {
        this.applyData(tickProgress);
        if (!this.callActions(this.data.renderActions, ActionCallPosition.PRE, tickProgress)) {
            super.render(vertexConsumer, camera, tickProgress);
            this.callActions(this.data.renderActions, ActionCallPosition.POST, tickProgress);
        }
    }

    @Override
    public void move(double dx, double dy, double dz) {
        if (this.data.noClip) {
            this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
            this.repositionFromBoundingBox();
        } else {
            super.move(dx, dy, dz);
        }
    }

    @Override
    public void tick() {
        super.tick();
        this.callActions(this.data.tickActions, ActionCallPosition.PRE, 0);
        this.data.tick();
        this.callActions(this.data.tickActions, ActionCallPosition.POST, 0);
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
