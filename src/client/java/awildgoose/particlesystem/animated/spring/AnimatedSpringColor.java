package awildgoose.particlesystem.animated.spring;

import awildgoose.particlesystem.animated.AnimatedValue;

import java.awt.Color;

/**
 * An animated color using springs
 * @see awildgoose.particlesystem.animated.AnimatedColor
 * @see awildgoose.particlesystem.animated.spring.AnimatedSpringNumber
 */
@SuppressWarnings("unused")
public class AnimatedSpringColor implements AnimatedValue<Color> {
    private final Color[] colors;
    private final double damping;
    private final double frequency;
    private int lifetime;
    private int age;

    public AnimatedSpringColor(double damping, double frequency, Color... colors) {
        if (colors == null || colors.length == 0)
            throw new IllegalArgumentException("Need at least one color");

        if (colors.length == 1) {
            this.colors = new Color[]{colors[0], colors[0]};
        } else {
            this.colors = colors;
        }

        this.damping = damping;
        this.frequency = frequency;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getLifetime() {
        return lifetime;
    }

    @Override
    public void tick() {
        if (age < lifetime) age++;
    }

    @Override
    public Color getValue(float partialTicks) {
        float progress = ((float) age + partialTicks) / lifetime;
        progress = Math.min(progress, 1.0f);

        float segmentProgress = progress * (colors.length - 1);
        int segIndex = (int) segmentProgress;
        float localT = segmentProgress - segIndex;

        if (segIndex >= colors.length - 1) {
            return colors[colors.length - 1];
        }

        double spring = 1 - Math.exp(-damping * localT * 10) * Math.cos(frequency * localT * Math.PI);

        Color from = colors[segIndex];
        Color to = colors[segIndex + 1];

        int r = (int) (from.getRed() + (to.getRed() - from.getRed()) * spring);
        int g = (int) (from.getGreen() + (to.getGreen() - from.getGreen()) * spring);
        int b = (int) (from.getBlue() + (to.getBlue() - from.getBlue()) * spring);
        int a = (int) (from.getAlpha() + (to.getAlpha() - from.getAlpha()) * spring);

        return new Color(r, g, b, a);
    }

    @Override
    public AnimatedValue<Color> copy() {
        AnimatedSpringColor copy = new AnimatedSpringColor(damping, frequency, colors.clone());
        copy.setLifetime(getLifetime());
        return copy;
    }

    public static AnimatedValue<Color> of(Color val) {
        return new AnimatedSpringColor(0.8, 4.0, val);
    }
}