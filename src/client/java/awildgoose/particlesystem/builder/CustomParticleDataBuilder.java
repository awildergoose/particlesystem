package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.provider.CustomParticleData;

@SuppressWarnings("unused")
public class CustomParticleDataBuilder {
    private AnimatedFloat size = AnimatedFloat.ONE;
    private int lifetime = 20;

    public CustomParticleDataBuilder size(AnimatedFloat size) {
        if (size.getLifetime() <= 0) {
            size.setLifetime(this.lifetime);
        }

        this.size = size;
        return this;
    }

    public CustomParticleDataBuilder lifetime(int lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public CustomParticleData build() {
        return new CustomParticleData(size, lifetime);
    }
}
