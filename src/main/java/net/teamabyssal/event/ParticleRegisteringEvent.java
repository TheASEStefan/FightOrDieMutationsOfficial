package net.teamabyssal.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.particles.BloodPuff;
import net.teamabyssal.particles.PoisonPuff;
import net.teamabyssal.registry.ParticleRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleRegisteringEvent {
    @SubscribeEvent
    public static void registerParticle(RegisterParticleProvidersEvent event) {

        Minecraft.getInstance().particleEngine.register(ParticleRegistry.BLOOD_PUFF.get(),
                BloodPuff.Provider::new);

        Minecraft.getInstance().particleEngine.register(ParticleRegistry.POISON_PUFF.get(),
                PoisonPuff.Provider::new);
    }
}
