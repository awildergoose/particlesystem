package awildgoose.particlesystem.animated.spring;

import awildgoose.particlesystem.animated.AnimatedValue;

/**
 * An animated float using springs
 * @see awildgoose.particlesystem.animated.spring.AnimatedSpringNumber
 */
@SuppressWarnings("unused")
public class AnimatedSpringFloat extends AnimatedSpringNumber<Float> {
    public AnimatedSpringFloat(double damping, double frequency, double... values) {
        super(damping, frequency, values);
    }

    @Override
    public Float getValue(float partialTicks) {
        return (float) interpolateValue(partialTicks);
    }

    @Override
    public AnimatedValue<Float> copy() {
        AnimatedSpringFloat copy = new AnimatedSpringFloat(damping, frequency, values.clone());
        copy.setLifetime(getLifetime());
        return copy;
    }

    public static AnimatedValue<Float> of(Float val) {
        return new AnimatedSpringFloat(0.8, 4.0, val);
    }
}