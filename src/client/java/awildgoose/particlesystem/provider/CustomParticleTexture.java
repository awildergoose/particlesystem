package awildgoose.particlesystem.provider;

@SuppressWarnings("unused")
public enum CustomParticleTexture {
    // the order is IMPORTANT here
    EXTRUDING_SPARK,
    SMOKE,
    SPARK,
    SPARKLE,
    STAR,
    THIN_EXTRUDING_SPARK,
    TWINKLE,
    WISP;

    public int asNum() {
        return this.ordinal();
    }
}
