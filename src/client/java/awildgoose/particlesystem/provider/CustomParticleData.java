package awildgoose.particlesystem.provider;

import awildgoose.particlesystem.action.Action;
import awildgoose.particlesystem.animated.AnimatedColor;
import awildgoose.particlesystem.animated.AnimatedFloat;

import java.util.ArrayList;

public class CustomParticleData {
    public static final CustomParticleData DEFAULT = new CustomParticleData(AnimatedFloat.ONE, 20, CustomParticleTexture.WISP, AnimatedFloat.ZERO, AnimatedColor.WHITE, 0, 0, 0, AnimatedFloat.ZERO, true, new ArrayList<>(), new ArrayList<>());

    public AnimatedFloat size;
    public AnimatedFloat angle;
    public AnimatedColor color;
    public CustomParticleTexture texture;
    public int lifetime;

    public double velocityX;
    public double velocityY;
    public double velocityZ;
    public AnimatedFloat gravity;
    public boolean noClip;

    public ArrayList<Action> tickActions;
    public ArrayList<Action> renderActions;

    public CustomParticleData(AnimatedFloat size, int lifetime, CustomParticleTexture texture, AnimatedFloat angle, AnimatedColor color, double velocityX, double velocityY, double velocityZ, AnimatedFloat gravity, boolean noClip, ArrayList<Action> tickActions, ArrayList<Action> renderActions) {
        this.size = size;
        this.lifetime = lifetime;
        this.texture = texture;
        this.angle = angle;
        this.color = color;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.gravity = gravity;
        this.noClip = noClip;
        this.tickActions = tickActions;
        this.renderActions = renderActions;
    }

    public void tick() {
        this.size.tick();
        this.angle.tick();
        this.color.tick();
    }
}
