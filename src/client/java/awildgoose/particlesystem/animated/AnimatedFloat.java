package awildgoose.particlesystem.animated;

@SuppressWarnings("unused")
public class AnimatedFloat extends AnimatedNumber<Float> {
    public static final AnimatedFloat ONE = new AnimatedFloat(Easing.LINEAR, 1.0);

    public AnimatedFloat(Easing easing, double... values) {
        super(easing, values);
    }

    @Override
    public Float getValue(float partialTicks) {
        return (float) interpolateValue(partialTicks);
    }
}
