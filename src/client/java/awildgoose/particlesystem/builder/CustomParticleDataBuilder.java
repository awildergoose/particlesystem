package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.animated.AnimatedAngle;
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

    public CustomParticleDataBuilder size(AnimatedFloat size, int duration) {
        this.size = resolveAnimatedNumber(size, duration);
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

    public CustomParticleDataBuilder angle(AnimatedAngle angle) {
        this.angle = resolveAnimatedNumber(angle);
        return this;
    }

    public CustomParticleDataBuilder angle(AnimatedAngle angle, int duration) {
        this.angle = resolveAnimatedNumber(angle, duration);
        return this;
    }

    private <T extends AnimatedNumber<?>> T resolveAnimatedNumber(T number, int duration) {
        if (number.getLifetime() <= 0) {
            number.setLifetime(duration);
        }

        return number;
    }

    private <T extends AnimatedNumber<?>> T resolveAnimatedNumber(T number) {
        return resolveAnimatedNumber(number, this.lifetime);
    }

    public CustomParticleData build() {
        return new CustomParticleData(size, lifetime, texture, angle);
    }
}
