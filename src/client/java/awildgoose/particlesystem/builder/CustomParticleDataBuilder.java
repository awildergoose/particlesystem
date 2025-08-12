package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.action.Action;
import awildgoose.particlesystem.action.ActionCallPosition;
import awildgoose.particlesystem.animated.*;
import awildgoose.particlesystem.particle.CustomParticle;
import awildgoose.particlesystem.provider.CustomParticleData;
import awildgoose.particlesystem.provider.CustomParticleTexture;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * Used to create data for custom particles, like {@link #color}, {@link #angle}, and {@link #lifetime} and so on
 */
@SuppressWarnings("unused")
public class CustomParticleDataBuilder {
    private AnimatedNumber<Float> size = AnimatedFloat.ONE;
    private AnimatedNumber<Float> angle = AnimatedFloat.ZERO;
    private AnimatedValue<Color> color = AnimatedColor.WHITE;
    private AnimatedNumber<Float> gravity = AnimatedFloat.ZERO;
    private int texture = CustomParticleTexture.WISP.asNum();
    private int lifetime = 20;
    private double velocityX = 0;
    private double velocityY = 0;
    private double velocityZ = 0;
    private boolean noClip = true;
    private final ArrayList<Action> renderActions = new ArrayList<>();
    private final ArrayList<Action> tickActions = new ArrayList<>();

    public CustomParticleDataBuilder size(AnimatedNumber<Float> size) {
        this.size = resolveAnimatedValue(size);
        return this;
    }

    public CustomParticleDataBuilder size(AnimatedNumber<Float> size, int duration) {
        this.size = resolveAnimatedValue(size, duration);
        return this;
    }

    public CustomParticleDataBuilder lifetime(int lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    public CustomParticleDataBuilder texture(CustomParticleTexture texture) {
        this.texture = texture.asNum();
        return this;
    }

    public CustomParticleDataBuilder texture(int texture) {
        this.texture = texture;
        return this;
    }

    public CustomParticleDataBuilder angle(AnimatedNumber<Float> angle) {
        this.angle = resolveAnimatedValue(angle);
        return this;
    }

    public CustomParticleDataBuilder angle(AnimatedNumber<Float> angle, int duration) {
        this.angle = resolveAnimatedValue(angle, duration);
        return this;
    }

    public CustomParticleDataBuilder color(AnimatedValue<Color> color) {
        this.color = resolveAnimatedValue(color);
        return this;
    }

    public CustomParticleDataBuilder color(AnimatedValue<Color> color, int duration) {
        this.color = resolveAnimatedValue(color, duration);
        return this;
    }

    public CustomParticleDataBuilder velocity(double x, double y, double z) {
        this.velocityX = x;
        this.velocityY = y;
        this.velocityZ = z;
        return this;
    }

    public CustomParticleDataBuilder gravity(AnimatedNumber<Float> gravity) {
        this.gravity = gravity;
        return this;
    }

    public CustomParticleDataBuilder enableNoClip() {
        this.noClip = true;
        return this;
    }

    public CustomParticleDataBuilder disableNoClip() {
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
        return new CustomParticleData(size,
                lifetime,
                texture,
                angle,
                color,
                velocityX,
                velocityY,
                velocityZ,
                gravity,
                noClip,
                tickActions,
                renderActions);
    }
}
