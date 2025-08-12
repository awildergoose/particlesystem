package awildgoose.particlesystem.animated;

/**
 * An animated double
 * @see AnimatedNumber
 */
@SuppressWarnings("unused")
public class AnimatedDouble extends AnimatedNumber<Double> {
    public static final AnimatedDouble ONE = new AnimatedDouble(Easing.LINEAR, 1.0);

    public AnimatedDouble(Easing easing, double... values) {
        super(easing, values);
    }

    @Override
    public Double getValue(float partialTicks) {
        return interpolateValue(partialTicks);
    }

    @Override
    public AnimatedValue<Double> copy() {
        double[] valuesCopy = values.clone();
        AnimatedValue<Double> copyInstance = new AnimatedDouble(easing, valuesCopy);
        copyInstance.setLifetime(getLifetime());
        return copyInstance;
    }
}
