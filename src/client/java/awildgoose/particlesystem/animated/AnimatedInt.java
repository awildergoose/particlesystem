package awildgoose.particlesystem.animated;

/**
 * An animated int
 * @see AnimatedNumber
 */
@SuppressWarnings("unused")
public class AnimatedInt extends AnimatedNumber<Integer> {
    public static final AnimatedInt ONE = new AnimatedInt(Easing.LINEAR, 1.0);

    public AnimatedInt(Easing easing, double... values) {
        super(easing, values);
    }

    @Override
    public Integer getValue(float partialTicks) {
        return (int) Math.round(interpolateValue(partialTicks));
    }

    @Override
    public AnimatedValue<Integer> copy() {
        double[] valuesCopy = values.clone();
        AnimatedValue<Integer> copyInstance = new AnimatedInt(easing, valuesCopy);
        copyInstance.setLifetime(getLifetime());
        return copyInstance;
    }
}
