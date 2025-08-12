package awildgoose.particlesystem.animated.spring;

import awildgoose.particlesystem.animated.AnimatedValue;

@SuppressWarnings("unused")
public class AnimatedSpringInt extends AnimatedSpringNumber<Integer> {
    public AnimatedSpringInt(double damping, double frequency, double... values) {
        super(damping, frequency, values);
    }

    @Override
    public Integer getValue(float partialTicks) {
        return (int) Math.round(interpolateValue(partialTicks));
    }

    @Override
    public AnimatedValue<Integer> copy() {
        AnimatedSpringInt copy = new AnimatedSpringInt(damping, frequency, values.clone());
        copy.setLifetime(getLifetime());
        return copy;
    }
}