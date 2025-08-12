package awildgoose.particlesystem.animated.spring;

import awildgoose.particlesystem.animated.AnimatedValue;

/**
 * An animated angle using springs
 * @see awildgoose.particlesystem.animated.spring.AnimatedSpringFloat
 * @see awildgoose.particlesystem.animated.spring.AnimatedSpringNumber
 * @see awildgoose.particlesystem.animated.AnimatedAngle
 */
@SuppressWarnings("unused")
public class AnimatedSpringAngle extends AnimatedSpringFloat {
    public AnimatedSpringAngle(double damping, double frequency, double... degrees) {
        super(damping, frequency, convertToRadians(degrees));
    }

    private static double[] convertToRadians(double[] degrees) {
        double[] radians = new double[degrees.length];
        for (int i = 0; i < degrees.length; i++) {
            radians[i] = Math.toRadians(degrees[i]);
        }
        return radians;
    }

    @Override
    public AnimatedValue<Float> copy() {
        AnimatedSpringAngle copy = new AnimatedSpringAngle(damping, frequency, values.clone());
        copy.setLifetime(getLifetime());
        return copy;
    }
}
