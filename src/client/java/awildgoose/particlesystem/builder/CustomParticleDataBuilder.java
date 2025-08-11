package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.provider.CustomParticleData;
import awildgoose.particlesystem.provider.CustomParticleTexture;

@SuppressWarnings("unused")
public class CustomParticleDataBuilder {
    private AnimatedFloat size = AnimatedFloat.ONE;
    private CustomParticleTexture texture = CustomParticleTexture.WISP;
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

    public CustomParticleDataBuilder texture(CustomParticleTexture texture) {
        this.texture = texture;
        return this;
    }

    public CustomParticleData build() {
        return new CustomParticleData(size, lifetime, texture);
    }
}
