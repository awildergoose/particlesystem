package awildgoose.particlesystem.provider;

import awildgoose.particlesystem.animated.AnimatedFloat;

public class CustomParticleData {
    public static final CustomParticleData DEFAULT = new CustomParticleData(AnimatedFloat.ONE, 20);
    public AnimatedFloat size;
    public int lifetime;

    public CustomParticleData(AnimatedFloat size, int lifetime) {
        this.size = size;
        this.lifetime = lifetime;
    }

    public void tick() {
        this.size.tick();
    }
}
