package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.ParticleSystem;
import awildgoose.particlesystem.particle.CustomParticle;
import awildgoose.particlesystem.provider.CustomParticleData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

@SuppressWarnings("unused")
public class CustomParticleBuilder {
    private CustomParticleData data;
    private double x, y, z;

    public CustomParticleBuilder with(CustomParticleData data) {
        this.data = data;
        return this;
    }

    public CustomParticleBuilder at(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public CustomParticleBuilder at(Vec3d pos) {
        double x = pos.x;
        double y = pos.y;
        double z = pos.z;
        return this.at(x, y, z);
    }

    public CustomParticleBuilder at(BlockPos pos) {
        return this.at(Vec3d.ofCenter(pos));
    }

    @Environment(EnvType.CLIENT)
    @SuppressWarnings("UnusedReturnValue")
    public CustomParticle spawn() {
        CustomParticle particle = (CustomParticle) MinecraftClient.getInstance().particleManager.addParticle(
                ParticleSystem.CUSTOM_PARTICLE,
                this.x, this.y, this.z,
                this.data.velocityX, this.data.velocityY, this.data.velocityZ
        );

        if (particle != null) {
            particle.setData(this.data);
        } else {
            ParticleSystem.LOGGER.error("Failed to spawn custom particle!");
        }

        return particle;
    }
}
