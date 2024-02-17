package net.teamabyssal.extra;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.custom.*;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.*;

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

            if (entity instanceof Zombie zombie && zombie.hasEffect(EffectRegistry.HIVE_SICKNESS.get()) && !world.isClientSide && FightOrDieMutationsConfig.SERVER.assimilated_human_assimilation.get() && Math.random() <= 0.95F && event.getSource().getEntity() != null && EntityRegistry.PARASITES.contains(event.getSource().getEntity())) {
                AssimilatedHumanEntity assimilatedHumanEntity = EntityRegistry.ASSIMILATED_HUMAN.get().create(world);
                assert assimilatedHumanEntity != null;
                assimilatedHumanEntity.moveTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                world.addFreshEntity(assimilatedHumanEntity);
                zombie.level().playSound((Player) null, zombie.blockPosition(), SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.2F, 1.0F);
                if (zombie.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.EXPLOSION, zombie.getX(), zombie.getY() + 1, zombie.getZ(), 3, 0.4, 1.0, 0.4, 0);
                    if (currentPhase < 3) {
                        worldDataRegistry.setScore(currentScore + 5);
                    }
                    else if (currentPhase >= 3) {
                        worldDataRegistry.setScore(currentScore + 10);
                    }
                }
            }
            else if (entity instanceof Creeper creeper && creeper.hasEffect(EffectRegistry.HIVE_SICKNESS.get()) && !world.isClientSide && FightOrDieMutationsConfig.SERVER.assimilated_creeper_assimilation.get() && Math.random() <= 0.90F) {
                AssimilatedCreeperEntity assimilatedCreeperEntity = EntityRegistry.ASSIMILATED_CREEPER.get().create(world);
                assert assimilatedCreeperEntity != null;
                assimilatedCreeperEntity.moveTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                world.addFreshEntity(assimilatedCreeperEntity);
                creeper.level().playSound((Player) null, creeper.blockPosition(), SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.2F, 1.0F);
                if (creeper.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.EXPLOSION, creeper.getX(), creeper.getY() + 1, creeper.getZ(), 3, 0.4, 1.0, 0.4, 0);
                    if (currentPhase < 3) {
                        worldDataRegistry.setScore(currentScore + 10);
                    }
                    else if (currentPhase >= 3) {
                        worldDataRegistry.setScore(currentScore + 20);
                    }
                }
            }
            else if (entity instanceof Villager villager && villager.hasEffect(EffectRegistry.HIVE_SICKNESS.get()) && !world.isClientSide && FightOrDieMutationsConfig.SERVER.assimilated_villager_assimilation.get() && Math.random() <= 0.85F && event.getSource().getEntity() != null && EntityRegistry.PARASITES.contains(event.getSource().getEntity())) {
                AssimilatedVillagerEntity assimilatedVillagerEntity = EntityRegistry.ASSIMILATED_VILLAGER.get().create(world);
                assert assimilatedVillagerEntity != null;
                assimilatedVillagerEntity.moveTo(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
                world.addFreshEntity(assimilatedVillagerEntity);
                villager.level().playSound((Player) null, villager.blockPosition(), SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.2F, 1.0F);
                if (villager.level() instanceof ServerLevel server) {
                    server.sendParticles(ParticleTypes.EXPLOSION, villager.getX(), villager.getY() + 1, villager.getZ(), 3, 0.4, 1.0, 0.4, 0);
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

}

