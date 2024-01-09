package net.teamabyssal.extra;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.custom.AssimilatedCowEntity;
import net.teamabyssal.entity.custom.AssimilatedHumanEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.EffectRegistry;
import net.teamabyssal.registry.EntityRegistry;
import net.teamabyssal.registry.WorldDataRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class AssimilationEvent {
    @SubscribeEvent
    public static void AssimilationEventFDM(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null && !event.getEntity().level().isClientSide) {
            Level world = event.getEntity().level();
            double x = event.getEntity().getX();
            double y = event.getEntity().getY();
            double z = event.getEntity().getZ();
            Entity entity = event.getEntity();
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry((ServerLevel) world);
            int currentPhase = worldDataRegistry.getPhase();
            int currentScore = worldDataRegistry.getScore();

            if (entity instanceof Zombie zombie && zombie.hasEffect(EffectRegistry.HIVE_SICKNESS.get()) && !world.isClientSide && FightOrDieMutationsConfig.SERVER.assimilated_human_assimilation.get()) {
                AssimilatedHumanEntity assimilatedHumanEntity = EntityRegistry.ASSIMILATED_HUMAN.get().create(world);
                assert assimilatedHumanEntity != null;
                assimilatedHumanEntity.moveTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                world.addFreshEntity(assimilatedHumanEntity);
                assimilatedHumanEntity.playSound(SoundEvents.ZOMBIE_INFECT);
                if (zombie.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.EXPLOSION, zombie.getX(), zombie.getY() + 1, zombie.getZ(), 5, 0.4, 1.0, 0.4, 0);
                    if (currentPhase < 3) {
                        worldDataRegistry.setScore(currentScore + 5);
                    }
                    else if (currentPhase >= 3) {
                        worldDataRegistry.setScore(currentScore + 10);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void AnimalAssimilation(LivingHurtEvent event) {
        if (event != null && event.getEntity() != null && !event.getEntity().level().isClientSide) {
            Level world = event.getEntity().level();
            double x = event.getEntity().getX();
            double y = event.getEntity().getY();
            double z = event.getEntity().getZ();
            Entity entity = event.getEntity();
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry((ServerLevel) world);
            int currentScore = worldDataRegistry.getScore();

            if (entity instanceof Cow cow && cow.hasEffect(EffectRegistry.HIVE_SICKNESS.get()) && !world.isClientSide && FightOrDieMutationsConfig.SERVER.assimilated_cow_assimilation.get()) {
                AssimilatedCowEntity assimilatedCowEntity = EntityRegistry.ASSIMILATED_COW.get().create(world);
                assert assimilatedCowEntity != null;
                assimilatedCowEntity.moveTo(x, y, z);
                world.addFreshEntity(assimilatedCowEntity);
                cow.discard();
                assimilatedCowEntity.playSound(SoundEvents.ZOMBIE_INFECT);
                if (cow.getLastHurtByMob() != null) {
                    assimilatedCowEntity.setTarget(cow.getLastHurtByMob());
                    assimilatedCowEntity.getLookControl().setLookAt(cow.getLastHurtByMob());
                }
                if (cow.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.EXPLOSION, cow.getX(), cow.getY() + 1, cow.getZ(), 5, 0.4, 1.0, 0.4, 0);
                    worldDataRegistry.setScore(currentScore + 2);
                }
            }
        }
    }
}

