package awildgoose.particlesystem.provider;

import awildgoose.particlesystem.animated.AnimatedFloat;

public class CustomParticleData {
    public static final CustomParticleData DEFAULT = new CustomParticleData(AnimatedFloat.ONE, 20, CustomParticleTexture.WISP, AnimatedFloat.ZERO);
    public AnimatedFloat size;
    public AnimatedFloat angle;
    public int lifetime;
    public CustomParticleTexture texture;

    public CustomParticleData(AnimatedFloat size, int lifetime, CustomParticleTexture texture, AnimatedFloat angle) {
        this.size = size;
        this.lifetime = lifetime;
        this.texture = texture;
        this.angle = angle;
    }

    public void tick() {
        this.size.tick();
        this.angle.tick();
    }
}
