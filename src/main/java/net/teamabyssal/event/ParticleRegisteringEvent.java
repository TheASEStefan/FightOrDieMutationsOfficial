package net.teamabyssal.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.particles.BloodPuffParticle;
import net.teamabyssal.particles.KillCountParticle;
import net.teamabyssal.particles.PoisonPuffParticle;
import net.teamabyssal.registry.ParticleRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleRegisteringEvent {
    @SubscribeEvent
    public static void registerParticle(RegisterParticleProvidersEvent event) {

        Minecraft.getInstance().particleEngine.register(ParticleRegistry.BLOOD_PUFF.get(),
                BloodPuffParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegistry.POISON_PUFF.get(),
                PoisonPuffParticle.Provider::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegistry.KILL_COUNT.get(),
                KillCountParticle.Provider::new);
    }
}
