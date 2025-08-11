package awildgoose.particlesystem;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParticleSystem implements ModInitializer {
	public static final String MOD_ID = "particlesystem";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final SimpleParticleType CUSTOM_PARTICLE = FabricParticleTypes.simple();

	@Override
	public void onInitialize() {
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "custom"), CUSTOM_PARTICLE);
		LOGGER.info("Hello Fabric world!");
	}
}