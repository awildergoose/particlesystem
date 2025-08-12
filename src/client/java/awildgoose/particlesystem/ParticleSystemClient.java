package awildgoose.particlesystem;

import awildgoose.particlesystem.action.ActionCallPosition;
import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.animated.Easing;
import awildgoose.particlesystem.animated.spring.AnimatedSpringAngle;
import awildgoose.particlesystem.animated.spring.AnimatedSpringColor;
import awildgoose.particlesystem.animated.spring.AnimatedSpringFloat;
import awildgoose.particlesystem.builder.CustomParticleBuilder;
import awildgoose.particlesystem.builder.CustomParticleDataBuilder;
import awildgoose.particlesystem.particle.CustomParticle;
import awildgoose.particlesystem.provider.CustomParticleTexture;
import awildgoose.particlesystem.provider.Scheduler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;

import java.awt.*;

@SuppressWarnings("unused")
public class ParticleSystemClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(
				ParticleSystem.CUSTOM_PARTICLE, CustomParticle.Factory::new);
		ClientTickEvents.END_WORLD_TICK.register(Scheduler::tick);

		//noinspection CommentedOutCode
		ClientPlayerBlockBreakEvents.AFTER.register((world, player, pos, state) -> new CustomParticleBuilder()
				.at(pos)
				.with(
						new CustomParticleDataBuilder()
						.lifetime(200)
						.angle(new AnimatedSpringAngle(
								1.0f,
								1.0f,
								0.0f,
								360.0f,
								0.0f,
								360.0f,
								0.0f,
								360.0f
						))
						.color(new AnimatedSpringColor(
								1.0f,
								1.0f,
								Color.BLUE,
								Color.RED,
								Color.GREEN,
								new Color(255, 255, 255, 0)
						))
						.size(new AnimatedSpringFloat(1.0f,
								1.0f,
								0.0f,
								0.5f,
								0.5f,
								0.0f))
						.render(ActionCallPosition.PRE, (particle, tickProgress) -> {
//							double delta = ((double) particle.getAge() + tickProgress) / particle.getMaxAge();
//							Vec3d lerpedPos = particle.getStartPos().lerp(player.getPos(), delta);
//							particle.setPos(lerpedPos);
							return false;
						})
						.disableNoClip()
						.texture(CustomParticleTexture.TWINKLE)
						.gravity(new AnimatedFloat(
								Easing.EXPO_IN_OUT,
								0.2f
						))
						.build()
				).after(40).createCircle(32, 2));
	}
}