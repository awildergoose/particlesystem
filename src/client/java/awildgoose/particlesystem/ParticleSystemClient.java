package awildgoose.particlesystem;

import awildgoose.particlesystem.action.ActionCallPosition;
import awildgoose.particlesystem.animated.AnimatedAngle;
import awildgoose.particlesystem.animated.AnimatedColor;
import awildgoose.particlesystem.animated.AnimatedFloat;
import awildgoose.particlesystem.animated.Easing;
import awildgoose.particlesystem.builder.CustomParticleBuilder;
import awildgoose.particlesystem.particle.CustomParticle;
import awildgoose.particlesystem.builder.CustomParticleDataBuilder;
import awildgoose.particlesystem.provider.CustomParticleTexture;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;

import java.awt.*;

@SuppressWarnings("unused")
public class ParticleSystemClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(
				ParticleSystem.CUSTOM_PARTICLE, CustomParticle.Factory::new);

		//noinspection CommentedOutCode
		ClientPlayerBlockBreakEvents.AFTER.register((world, player, pos, state) -> new CustomParticleBuilder()
				.at(pos)
				.with(
						new CustomParticleDataBuilder()
						.lifetime(40)
						.angle(new AnimatedAngle(
								Easing.EXPO_IN_OUT,
								0.0f,
								0.0f,
								360.0f
						), 20)
						.color(new AnimatedColor(
								Easing.EXPO_IN_OUT,
								new Color(255, 255, 255, 0),
								Color.BLUE,
								Color.WHITE,
								Color.BLUE,
								new Color(255, 255, 255, 0)
						), 20)
						.size(new AnimatedFloat(Easing.EXPO_IN_OUT, 0.0f, 0.5f, 0.5f, 0.0f))
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
				).spawn());
	}
}