package awildgoose.particlesystem.action;

import awildgoose.particlesystem.particle.CustomParticle;

/**
 * Used for pre/post render/tick callbacks
 */
public interface Action {
    /**
     * Determines where to place the callback
     * @return The position of when the callback should be called
     */
    ActionCallPosition getPosition();

    /**
     * Runs when the action triggers (based on the position provided in getPosition)
     * @param particle The particle calling the callback
     * @param tickProgress Delta time
     * @return `true` if the event should be suppressed, Only supported in pre-render
     */
    boolean run(CustomParticle particle, float tickProgress);
}
