package awildgoose.particlesystem.animated;

/**
 * An animated number using Easing
 * @param <T> Type of object to animate
 * @see AnimatedValue
 */
public abstract class AnimatedNumber<T extends Number> implements AnimatedValue<T> {
    protected final double[] values;
    protected final Easing easing;
    protected int lifetime;
    protected int age;

    public AnimatedNumber(Easing easing, double... values) {
        if (values.length == 1) {
            this.values = new double[] { values[0], values[0] }; // constant
        } else if (values.length < 1) {
            throw new IllegalArgumentException("Need at least 1 value");
        } else {
            this.values = values;
        }
        this.easing = easing;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public int getLifetime() {
        return lifetime;
    }

    @Override
    public void tick() {
        if (age < lifetime) {
            age++;
        }
    }

    protected double interpolateValue(float partialTicks) {
        float progress = ((float) age + partialTicks) / lifetime;
        progress = Math.min(progress, 1.0f); // clamp
        float segmentProgress = progress * (values.length - 1);
        int segIndex = (int) segmentProgress;
        float localT = segmentProgress - segIndex;

        if (segIndex >= values.length - 1) {
            return values[values.length - 1];
        }
        float eased = (float) easing.apply(localT);
        return values[segIndex] + (values[segIndex + 1] - values[segIndex]) * eased;
    }

    @Override
    public abstract T getValue(float partialTicks);
}
