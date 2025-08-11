package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.action.Action;
import awildgoose.particlesystem.action.ActionCallPosition;
import awildgoose.particlesystem.animated.AnimatedAngle;
import awildgoose.particlesystem.animated.AnimatedColor;
import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.animated.AnimatedValue;
import awildgoose.particlesystem.particle.CustomParticle;
import awildgoose.particlesystem.provider.CustomParticleData;
import awildgoose.particlesystem.provider.CustomParticleTexture;

import java.util.ArrayList;
import java.util.function.BiFunction;

@SuppressWarnings("unused")
public class CustomParticleDataBuilder {
    private AnimatedFloat size = AnimatedFloat.ONE;
    private AnimatedFloat angle = AnimatedFloat.ZERO;
    private AnimatedColor color = AnimatedColor.WHITE;
    private AnimatedFloat gravity = AnimatedFloat.ZERO;
    private CustomParticleTexture texture = CustomParticleTexture.WISP;
    private int lifetime = 20;
    private double velocityX = 0;
    private double velocityY = 0;
    private double velocityZ = 0;
    private boolean noClip = true;
    private final ArrayList<Action> renderActions = new ArrayList<>();
    private final ArrayList<Action> tickActions = new ArrayList<>();

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

    public CustomParticleDataBuilder velocity(double x, double y, double z) {
        this.velocityX = x;
        this.velocityY = y;
        this.velocityZ = z;
        return this;
    }

    public CustomParticleDataBuilder gravity(AnimatedFloat gravity) {
        this.gravity = gravity;
        return this;
    }

    public CustomParticleDataBuilder noClip() {
        this.noClip = true;
        return this;
    }

    public CustomParticleDataBuilder clip() {
        this.noClip = false;
        return this;
    }

    public CustomParticleDataBuilder render(ActionCallPosition position, BiFunction<CustomParticle, Float, Boolean> function) {
        this.renderActions.add(new Action() {
            @Override
            public ActionCallPosition getPosition() {
                return position;
            }

            @Override
            public boolean run(CustomParticle particle, float tickProgress) {
                return function.apply(particle, tickProgress);
            }
        });

        return this;
    }

    public CustomParticleDataBuilder tick(ActionCallPosition position, BiFunction<CustomParticle, Float, Boolean> function) {
        this.renderActions.add(new Action() {
            @Override
            public ActionCallPosition getPosition() {
                return position;
            }

            @Override
            public boolean run(CustomParticle particle, float tickProgress) {
                return function.apply(particle, tickProgress);
            }
        });

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
        return new CustomParticleData(size, lifetime, texture, angle, color, velocityX, velocityY, velocityZ, gravity, noClip, tickActions, renderActions);
    }
}
