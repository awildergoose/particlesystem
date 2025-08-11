package awildgoose.particlesystem.animated;

import java.util.function.DoubleUnaryOperator;

@SuppressWarnings("unused")
public enum Easing {
    LINEAR(t -> t),

    QUAD_IN(t -> t * t),
    QUAD_OUT(t -> t * (2 - t)),
    QUAD_IN_OUT(t -> (t < 0.5) ? 2 * t * t : -1 + (4 - 2 * t) * t),

    CUBIC_IN(t -> t * t * t),
    CUBIC_OUT(t -> {
        double f = t - 1;
        return f * f * f + 1;
    }),
    CUBIC_IN_OUT(t -> (t < 0.5) ? 4 * t * t * t : (t - 1) * (2 * t - 2) * (2 * t - 2) + 1),

    QUART_IN(t -> t * t * t * t),
    QUART_OUT(t -> 1 - Math.pow(t - 1, 4)),
    QUART_IN_OUT(t -> (t < 0.5) ? 8 * Math.pow(t, 4) : 1 - 8 * Math.pow(t - 1, 4)),

    QUINT_IN(t -> Math.pow(t, 5)),
    QUINT_OUT(t -> Math.pow(t - 1, 5) + 1),
    QUINT_IN_OUT(t -> (t < 0.5) ? 16 * Math.pow(t, 5) : 1 + 16 * Math.pow(t - 1, 5)),

    SINE_IN(t -> 1 - Math.cos((t * Math.PI) / 2)),
    SINE_OUT(t -> Math.sin((t * Math.PI) / 2)),
    SINE_IN_OUT(t -> -0.5 * (Math.cos(Math.PI * t) - 1)),

    EXPO_IN(t -> (t == 0) ? 0 : Math.pow(1024, t - 1)),
    EXPO_OUT(t -> (t == 1) ? 1 : 1 - Math.pow(2, -10 * t)),
    EXPO_IN_OUT(t -> {
        if (t == 0) return 0;
        if (t == 1) return 1;
        if (t < 0.5) return Math.pow(1024, t * 2 - 1) / 2;
        return (2 - Math.pow(2, -20 * t + 10)) / 2;
    }),

    CIRC_IN(t -> 1 - Math.sqrt(1 - t * t)),
    CIRC_OUT(t -> Math.sqrt(1 - (t - 1) * (t - 1))),
    CIRC_IN_OUT(t -> {
        if (t < 0.5) return (1 - Math.sqrt(1 - 4 * t * t)) / 2;
        return (Math.sqrt(1 - Math.pow(-2 * t + 2, 2)) + 1) / 2;
    }),

    BACK_IN(t -> {
        double c1 = 1.70158;
        double c3 = c1 + 1;
        return c3 * t * t * t - c1 * t * t;
    }),
    BACK_OUT(t -> {
        double c1 = 1.70158;
        double c3 = c1 + 1;
        double f = t - 1;
        return 1 + c3 * f * f * f + c1 * f * f;
    }),
    BACK_IN_OUT(t -> {
        double c1 = 1.70158 * 1.525;
        if (t < 0.5) return (Math.pow(2 * t, 2) * ((c1 + 1) * 2 * t - c1)) / 2;
        return (Math.pow(2 * t - 2, 2) * ((c1 + 1) * (t * 2 - 2) + c1) + 2) / 2;
    }),

    ELASTIC_IN(t -> {
        if (t == 0 || t == 1) return t;
        return -Math.pow(2, 10 * t - 10) * Math.sin((t * 10 - 10.75) * ((2 * Math.PI) / 3));
    }),
    ELASTIC_OUT(t -> {
        if (t == 0 || t == 1) return t;
        return Math.pow(2, -10 * t) * Math.sin((t * 10 - 0.75) * ((2 * Math.PI) / 3)) + 1;
    }),
    ELASTIC_IN_OUT(t -> {
        if (t == 0 || t == 1) return t;
        double v = Math.sin((20 * t - 11.125) * ((2 * Math.PI) / 4.5));
        if (t < 0.5)
            return -(Math.pow(2, 20 * t - 10) * v) / 2;
        return (Math.pow(2, -20 * t + 10) * v) / 2 + 1;
    }),

    BOUNCE_OUT(t -> {
        double n1 = 7.5625;
        double d1 = 2.75;
        if (t < 1 / d1) {
            return n1 * t * t;
        } else if (t < 2 / d1) {
            t -= 1.5 / d1;
            return n1 * t * t + 0.75;
        } else if (t < 2.5 / d1) {
            t -= 2.25 / d1;
            return n1 * t * t + 0.9375;
        } else {
            t -= 2.625 / d1;
            return n1 * t * t + 0.984375;
        }
    }),
    BOUNCE_IN(t -> 1 - Easing.BOUNCE_OUT.function.applyAsDouble(1 - t)),
    BOUNCE_IN_OUT(t -> (t < 0.5)
            ? (1 - Easing.BOUNCE_OUT.function.applyAsDouble(1 - 2 * t)) / 2
            : (1 + Easing.BOUNCE_OUT.function.applyAsDouble(2 * t - 1)) / 2);

    public final DoubleUnaryOperator function;

    Easing(DoubleUnaryOperator function) {
        this.function = function;
    }

    public double apply(double t) {
        return function.applyAsDouble(t);
    }
}
