package awildgoose.particlesystem.animated;

public interface AnimatedValue<T> {
    T getValue(float partialTicks);
    void tick();

    int getLifetime();
    void setLifetime(int lifetime);
}
