package net.teamabyssal.extra;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.custom.AssimilatedHumanEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.handlers.PhaseHandler;
import net.teamabyssal.handlers.ScoreHandler;
import net.teamabyssal.registry.EffectRegistry;
import net.teamabyssal.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class AssimilationEvent {
    @SubscribeEvent
    public static void MalruptorKillEvent(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null && !event.getEntity().level().isClientSide) {
            Level world = event.getEntity().level();
            double x = event.getEntity().getX();
            double y = event.getEntity().getY();
            double z = event.getEntity().getZ();
            Entity entity = event.getEntity();

            if (entity instanceof Zombie zombie && zombie.hasEffect(EffectRegistry.HIVE_SICKNESS.get()) && !world.isClientSide && FightOrDieMutationsConfig.SERVER.assimilated_human_assimilation.get()) {
                AssimilatedHumanEntity assimilatedHumanEntity = EntityRegistry.ASSIMILATED_HUMAN.get().create(world);
                assert assimilatedHumanEntity != null;
                assimilatedHumanEntity.moveTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                world.addFreshEntity(assimilatedHumanEntity);
                assimilatedHumanEntity.playSound(SoundEvents.ZOMBIE_INFECT);
                if (zombie.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.EXPLOSION, zombie.getX(), zombie.getY() + 1, zombie.getZ(), 5, 0.4, 1.0, 0.4, 0);
                    if (PhaseHandler.getPhase() < 3) {
                        ScoreHandler.setScore(ScoreHandler.getScore() + 5);
                    }
                    else if (PhaseHandler.getPhase() >= 3) {
                        ScoreHandler.setScore(ScoreHandler.getScore() + 10);
                    }
                }
            }
        }
    }
}

