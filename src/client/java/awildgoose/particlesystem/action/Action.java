package awildgoose.particlesystem.action;

import awildgoose.particlesystem.particle.CustomParticle;

public interface Action {
    ActionCallPosition getPosition();
    boolean run(CustomParticle particle, float tickProgress);
}
