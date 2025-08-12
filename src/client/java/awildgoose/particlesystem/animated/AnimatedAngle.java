package awildgoose.particlesystem.animated;

/**
 * An animated float that converts all values to radians
 * @see AnimatedNumber
 */
@SuppressWarnings("unused")
public class AnimatedAngle extends AnimatedFloat {
    /**
     * @param easing Type of easing
     * @param values The float values
     */
    public AnimatedAngle(Easing easing, double... values) {
        super(easing, convertToRadians(values));
    }

    /**
     * @param degrees The degrees to convert to radians
     * @return The degrees converted to radians
     */
    private static double[] convertToRadians(double[] degrees) {
        double[] radians = new double[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            radians[i] = Math.toRadians(degrees[i]);
        }
        return radians;
    }
}
