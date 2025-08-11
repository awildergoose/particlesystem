package awildgoose.particlesystem.animated;

import java.awt.Color;

@SuppressWarnings("unused")
public class AnimatedColor implements AnimatedValue<Color> {
    public static final AnimatedColor WHITE = new AnimatedColor(Easing.LINEAR, Color.WHITE);
    private final Color[] colors;
    private final Easing easing;
    private int lifetime;
    private int age;

    public AnimatedColor(Easing easing, Color... inputColors) {
        if (inputColors == null || inputColors.length == 0) {
            throw new IllegalArgumentException("Need at least one color");
        }

        if (inputColors.length == 1) {
            this.colors = new Color[] { inputColors[0], inputColors[0] };
        } else {
            this.colors = inputColors;
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

        float eased = (float) easing.apply(localT);

        Color from = colors[segIndex];
        Color to = colors[segIndex + 1];

        int r = (int) (from.getRed() + (to.getRed() - from.getRed()) * eased);
        int g = (int) (from.getGreen() + (to.getGreen() - from.getGreen()) * eased);
        int b = (int) (from.getBlue() + (to.getBlue() - from.getBlue()) * eased);

        return new Color(r, g, b);
    }
}
