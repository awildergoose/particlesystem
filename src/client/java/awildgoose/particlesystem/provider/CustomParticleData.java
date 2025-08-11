package awildgoose.particlesystem.provider;

import awildgoose.particlesystem.animated.AnimatedColor;
import awildgoose.particlesystem.animated.AnimatedFloat;

public class CustomParticleData {
    public static final CustomParticleData DEFAULT = new CustomParticleData(AnimatedFloat.ONE, 20, CustomParticleTexture.WISP, AnimatedFloat.ZERO, AnimatedColor.WHITE);
    public AnimatedFloat size;
    public AnimatedFloat angle;
    public int lifetime;
    public CustomParticleTexture texture;
    public AnimatedColor color;

    public CustomParticleData(AnimatedFloat size, int lifetime, CustomParticleTexture texture, AnimatedFloat angle, AnimatedColor color) {
        this.size = size;
        this.lifetime = lifetime;
        this.texture = texture;
        this.angle = angle;
        this.color = color;
    }

    public void tick() {
        this.size.tick();
        this.angle.tick();
        this.color.tick();
    }
}
