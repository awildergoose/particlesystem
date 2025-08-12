package awildgoose.particlesystem.animated;

/**
 * An animated float
 * @see AnimatedNumber
 */
@SuppressWarnings("unused")
public class AnimatedFloat extends AnimatedNumber<Float> {
    public static final AnimatedFloat ZERO = new AnimatedFloat(Easing.LINEAR, 0.0);
    public static final AnimatedFloat ONE = new AnimatedFloat(Easing.LINEAR, 1.0);

    public AnimatedFloat(Easing easing, double... values) {
        super(easing, values);
    }

    @Override
    public Float getValue(float partialTicks) {
        return (float) interpolateValue(partialTicks);
    }

    @Override
    public AnimatedValue<Float> copy() {
        double[] valuesCopy = values.clone();
        AnimatedValue<Float> copyInstance = new AnimatedFloat(easing, valuesCopy);
        copyInstance.setLifetime(getLifetime());
        return copyInstance;
    }

    public static AnimatedValue<Float> of(Float val) {
        return new AnimatedFloat(Easing.LINEAR, val);
    }
}
