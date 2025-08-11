package awildgoose.particlesystem;

import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.animated.Easing;
import awildgoose.particlesystem.builder.CustomParticleBuilder;
import awildgoose.particlesystem.particle.CustomParticle;
import awildgoose.particlesystem.builder.CustomParticleDataBuilder;
import awildgoose.particlesystem.provider.CustomParticleTexture;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;

@SuppressWarnings("unused")
public class ParticleSystemClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(
				ParticleSystem.CUSTOM_PARTICLE, CustomParticle.Factory::new);

		ClientPlayerBlockBreakEvents.AFTER.register((world, player, pos, state) -> new CustomParticleBuilder()
				.at(pos)
				.with(
						new CustomParticleDataBuilder()
						.lifetime(40)
						.size(new AnimatedFloat(Easing.QUINT_IN_OUT, 0.0f, 0.5f, 0.5f, 0.0f))
						.texture(CustomParticleTexture.TWINKLE)
						.build()
				).spawn());
	}
}