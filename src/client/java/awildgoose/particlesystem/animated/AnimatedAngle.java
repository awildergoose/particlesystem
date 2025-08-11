package awildgoose.particlesystem.animated;

@SuppressWarnings("unused")
public class AnimatedAngle extends AnimatedFloat {
    public AnimatedAngle(Easing easing, double... values) {
        super(easing, convertToRadians(values));
    }

    private static double[] convertToRadians(double[] degrees) {
        double[] radians = new double[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            radians[i] = Math.toRadians(degrees[i]);
        }
        return radians;
    }
}
