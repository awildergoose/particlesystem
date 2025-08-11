package awildgoose.particlesystem.provider;

import awildgoose.particlesystem.animated.AnimatedFloat;

public class CustomParticleData {
    public static final CustomParticleData DEFAULT = new CustomParticleData(AnimatedFloat.ONE, 20, CustomParticleTexture.WISP);
    public AnimatedFloat size;
    public int lifetime;
    public CustomParticleTexture texture;

    public CustomParticleData(AnimatedFloat size, int lifetime, CustomParticleTexture texture) {
        this.size = size;
        this.lifetime = lifetime;
        this.texture = texture;
    }

    public void tick() {
        this.size.tick();
    }
}
