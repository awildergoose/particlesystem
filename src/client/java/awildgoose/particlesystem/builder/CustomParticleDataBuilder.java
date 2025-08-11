package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.animated.AnimatedNumber;
import awildgoose.particlesystem.provider.CustomParticleData;
import awildgoose.particlesystem.provider.CustomParticleTexture;

@SuppressWarnings("unused")
public class CustomParticleDataBuilder {
    private AnimatedFloat size = AnimatedFloat.ONE;
    private AnimatedFloat angle = AnimatedFloat.ZERO;
    private CustomParticleTexture texture = CustomParticleTexture.WISP;
    private int lifetime = 20;

    public CustomParticleDataBuilder size(AnimatedFloat size) {
        this.size = resolveAnimatedNumber(size);
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

    public CustomParticleDataBuilder angle(AnimatedFloat angle) {
        this.angle = resolveAnimatedNumber(angle);
        return this;
    }

    private <T extends AnimatedNumber<?>> T resolveAnimatedNumber(T number) {
        if (number.getLifetime() <= 0) {
            number.setLifetime(this.lifetime);
        }

        return number;
    }

    public CustomParticleData build() {
        return new CustomParticleData(size, lifetime, texture, angle);
    }
}
