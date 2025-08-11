package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.animated.AnimatedAngle;
import awildgoose.particlesystem.animated.AnimatedColor;
import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.animated.AnimatedValue;
import awildgoose.particlesystem.provider.CustomParticleData;
import awildgoose.particlesystem.provider.CustomParticleTexture;

@SuppressWarnings("unused")
public class CustomParticleDataBuilder {
    private AnimatedFloat size = AnimatedFloat.ONE;
    private AnimatedFloat angle = AnimatedFloat.ZERO;
    private AnimatedColor color = AnimatedColor.WHITE;
    private CustomParticleTexture texture = CustomParticleTexture.WISP;
    private int lifetime = 20;

    public CustomParticleDataBuilder size(AnimatedFloat size) {
        this.size = resolveAnimatedValue(size);
        return this;
    }

    public CustomParticleDataBuilder size(AnimatedFloat size, int duration) {
        this.size = resolveAnimatedValue(size, duration);
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
        this.angle = resolveAnimatedValue(angle);
        return this;
    }

    public CustomParticleDataBuilder angle(AnimatedAngle angle, int duration) {
        this.angle = resolveAnimatedValue(angle, duration);
        return this;
    }

    public CustomParticleDataBuilder color(AnimatedColor color) {
        this.color = resolveAnimatedValue(color);
        return this;
    }

    public CustomParticleDataBuilder color(AnimatedColor color, int duration) {
        this.color = resolveAnimatedValue(color, duration);
        return this;
    }

    private <T extends AnimatedValue<?>> T resolveAnimatedValue(T number, int duration) {
        if (number.getLifetime() <= 0) {
            number.setLifetime(duration);
        }

        return number;
    }

    private <T extends AnimatedValue<?>> T resolveAnimatedValue(T number) {
        return resolveAnimatedValue(number, this.lifetime);
    }

    public CustomParticleData build() {
        return new CustomParticleData(size, lifetime, texture, angle, color);
    }
}
