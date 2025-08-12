package awildgoose.particlesystem.animated.spring;

import awildgoose.particlesystem.animated.AnimatedNumber;
import awildgoose.particlesystem.animated.Easing;

@SuppressWarnings("unused")
public abstract class AnimatedSpringNumber<T extends Number> extends AnimatedNumber<T> {
    protected final double damping;
    protected final double frequency;

    public AnimatedSpringNumber(double damping, double frequency, double... values) {
        super(Easing.LINEAR, values); // easing unused
        this.damping = damping;
        this.frequency = frequency;
    }

    @Override
    protected double interpolateValue(float partialTicks) {
        float progress = ((float) age + partialTicks) / lifetime;
        progress = Math.min(progress, 1.0f);

        float segmentProgress = progress * (values.length - 1);
        int segIndex = (int) segmentProgress;
        float localT = segmentProgress - segIndex;

        if (segIndex >= values.length - 1) {
            return values[values.length - 1];
        }

        double start = values[segIndex];
        double end = values[segIndex + 1];

        double spring = 1 - Math.exp(-damping * localT * 10) * Math.cos(frequency * localT * Math.PI);
        return start + (end - start) * spring;
    }
}