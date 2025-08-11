package awildgoose.particlesystem.provider;

import awildgoose.particlesystem.action.Action;
import awildgoose.particlesystem.animated.AnimatedColor;
import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.animated.AnimatedValue;

import java.awt.*;
import java.util.ArrayList;

public class CustomParticleData {
    public static final CustomParticleData DEFAULT = new CustomParticleData(AnimatedFloat.ONE, 20, CustomParticleTexture.WISP, AnimatedFloat.ZERO, AnimatedColor.WHITE, 0, 0, 0, AnimatedFloat.ZERO, true, new ArrayList<>(), new ArrayList<>());

    public AnimatedValue<Float> size;
    public AnimatedValue<Float> angle;
    public AnimatedValue<Float> gravity;
    public AnimatedValue<Color> color;
    public CustomParticleTexture texture;
    public int lifetime;

    public double velocityX;
    public double velocityY;
    public double velocityZ;
    public boolean noClip;

    public ArrayList<Action> tickActions;
    public ArrayList<Action> renderActions;

    public CustomParticleData(AnimatedValue<Float> size, int lifetime, CustomParticleTexture texture, AnimatedValue<Float> angle, AnimatedValue<Color> color, double velocityX, double velocityY, double velocityZ, AnimatedValue<Float> gravity, boolean noClip, ArrayList<Action> tickActions, ArrayList<Action> renderActions) {
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
        this.gravity.tick();
    }

    public CustomParticleData copy() {
        ArrayList<Action> tickActionsCopy = new ArrayList<>(this.tickActions);
        ArrayList<Action> renderActionsCopy = new ArrayList<>(this.renderActions);

        return new CustomParticleData(
                this.size.copy(),
                this.lifetime,
                this.texture,
                this.angle.copy(),
                this.color.copy(),
                this.velocityX,
                this.velocityY,
                this.velocityZ,
                this.gravity.copy(),
                this.noClip,
                tickActionsCopy,
                renderActionsCopy
        );
    }

}
