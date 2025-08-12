package awildgoose.particlesystem.animated;

/**
 * An animated value of any type, Usually animated using {@link AnimatedNumber} or {@link awildgoose.particlesystem.animated.spring.AnimatedSpringNumber}s
 * @param <T> Type of object to animate
 */
public interface AnimatedValue<T> {
    T getValue(float partialTicks);
    void tick();

    int getLifetime();
    void setLifetime(int lifetime);

    AnimatedValue<T> copy();
}
