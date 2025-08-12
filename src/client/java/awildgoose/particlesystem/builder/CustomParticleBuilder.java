package awildgoose.particlesystem.builder;

import awildgoose.particlesystem.ParticleSystem;
import awildgoose.particlesystem.particle.CustomParticle;
import awildgoose.particlesystem.provider.CustomParticleData;
import awildgoose.particlesystem.provider.Scheduler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * Used to create particles and spawn them into the world
 * <pre>{@code
 *     new CustomParticleBuilder().at(0, 0, 0).with(new CustomParticleDataBuilder().build()).spawn();
 * }</pre>
 * @see CustomParticleDataBuilder
 */
@Environment(EnvType.CLIENT)
@SuppressWarnings("unused")
public class CustomParticleBuilder {
    private CustomParticleData data = CustomParticleData.DEFAULT;
    private double x, y, z = 0;
    private int spawnDelay = 0;

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

    public CustomParticleBuilder after(int durationTicks) {
        this.spawnDelay = durationTicks;
        return this;
    }

    private CustomParticle spawnInternal() {
        CustomParticle particle = (CustomParticle) MinecraftClient.getInstance().particleManager.addParticle(
                ParticleSystem.CUSTOM_PARTICLE,
                this.x, this.y, this.z,
                this.data.velocityX, this.data.velocityY, this.data.velocityZ
        );

        if (particle != null) {
            particle.setData(this.data.copy());
        } else {
            ParticleSystem.LOGGER.error("Failed to spawn custom particle!");
        }

        return particle;
    }

    public void spawn() {
        this.spawn(null);
    }

    @SuppressWarnings("UnusedReturnValue")
    public void spawn(@Nullable Consumer<CustomParticle> callback) {
        if (this.spawnDelay != 0) {
            Scheduler.schedule(this.spawnDelay, (world) -> {
                CustomParticle p = this.spawnInternal();
                if (callback != null) callback.accept(p);
            });
        } else {
            CustomParticle p = this.spawnInternal();
            if (callback != null) callback.accept(p);
        }
    }

    public void repeat(int times, @Nullable Consumer<CustomParticle> action) {
        for (int i = 0; i < times; i++)
            this.spawn(action);
    }

    public void repeat(int times) {
        this.repeat(times, null);
    }

    public void createCircle(int points, double radius, @Nullable Consumer<CustomParticle> action) {
        double centerX = this.x;
        double centerY = this.y;
        double centerZ = this.z;

        for (int i = 0; i < points; i++) {
            double angle = 2 * Math.PI * i / points;
            double x = centerX + radius * Math.cos(angle);
            double z = centerZ + radius * Math.sin(angle);

            CustomParticleBuilder copyBuilder = this.copy().at(x, centerY, z);
            copyBuilder.spawn(action);
        }
    }

    public void createCircle(int points, double radius) {
        this.createCircle(points, radius, null);
    }

    public CustomParticleBuilder copy() {
        CustomParticleBuilder copy = new CustomParticleBuilder();
        copy.data = this.data;
        copy.x = this.x;
        copy.y = this.y;
        copy.z = this.z;
        copy.spawnDelay = this.spawnDelay;
        return copy;
    }
}
