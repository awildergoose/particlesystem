package awildgoose.particlesystem.animated.spring;

import awildgoose.particlesystem.animated.AnimatedValue;

@SuppressWarnings("unused")
public class AnimatedSpringDouble extends AnimatedSpringNumber<Double> {
    public AnimatedSpringDouble(double damping, double frequency, double... values) {
        super(damping, frequency, values);
    }

    @Override
    public Double getValue(float partialTicks) {
        return interpolateValue(partialTicks);
    }

    @Override
    public AnimatedValue<Double> copy() {
        AnimatedSpringDouble copy = new AnimatedSpringDouble(damping, frequency, values.clone());
        copy.setLifetime(getLifetime());
        return copy;
    }
}