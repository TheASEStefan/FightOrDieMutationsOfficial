package net.teamabyssal.extra;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.constants.PossibleAssimilated;
import net.teamabyssal.entity.custom.*;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.*;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class TurnClass {

    AbstractVillager villager;

    @SubscribeEvent
    public static void TurnEvent(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() != null) {
            LivingEntity livingEntity = (LivingEntity) event.getEntity();
            Level world = livingEntity.level();
            double x = livingEntity.getX();
            double y = livingEntity.getY();
            double z = livingEntity.getZ();
            PossibleAssimilated assimilated = ((PossibleAssimilated) livingEntity);

            if (assimilated.IgetAssimilationProgress() > 0 && livingEntity.hasEffect(EffectRegistry.HIVE_SICKNESS.get())) {
                assimilated.IsetAssimilationProgress(assimilated.IgetAssimilationProgress() + 1);
                if (assimilated.IgetAssimilationProgress() > 0 && assimilated.IgetAssimilationProgress() < 100) {
                    for(int i = 0; i < 1; ++i) {
                        double offset = 0.1;
                        double speed = 0.3;
                        if (livingEntity.level() instanceof ServerLevel server) {
                            server.sendParticles(ParticleRegistry.BLOOD_PUFF.get(), livingEntity.getRandomX(0.8), livingEntity.getRandomY() + 0.3, livingEntity.getRandomZ(0.8), 2, offset, offset, offset, speed);
                        }
                    }
                }
                if (assimilated.IgetAssimilationProgress() == 60) {
                    if (livingEntity.hasEffect(EffectRegistry.HIVE_SICKNESS.get())) {
                        if (livingEntity instanceof Sheep sheep && FightOrDieMutationsConfig.SERVER.assimilated_sheep_assimilation.get()) {
                            AssimilatedSheepEntity assimilatedSheepEntity = EntityRegistry.ASSIMILATED_SHEEP.get().create(world);
                            assert assimilatedSheepEntity != null;
                            assimilatedSheepEntity.moveTo(x, y, z);
                            world.addFreshEntity(assimilatedSheepEntity);
                            sheep.discard();
                            sheep.level().playSound((Player) null, sheep.blockPosition(), SoundRegistry.ENTITY_TURN.get(), SoundSource.HOSTILE, 1.6F, 1.0F);
                            sheep.level().playSound((Player) null, sheep.blockPosition(), SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.3F, 1.0F);
                            if (sheep.getLastHurtByMob() != null) {
                                assimilatedSheepEntity.setTarget(sheep.getLastHurtByMob());
                                assimilatedSheepEntity.getLookControl().setLookAt(sheep.getLastHurtByMob());
                            }
                            if (sheep.level() instanceof ServerLevel server) {
                                server.sendParticles(ParticleTypes.EXPLOSION, sheep.getX(), sheep.getY() + 1, sheep.getZ(), 3, 0.4, 1.0, 0.4, 0);
                                WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(server);
                                int currentScore = worldDataRegistry.getScore();
                                worldDataRegistry.setScore(currentScore + 2);
                            }

                        }
                        else if (livingEntity instanceof Cow cow && FightOrDieMutationsConfig.SERVER.assimilated_cow_assimilation.get()) {
                            AssimilatedCowEntity assimilatedCowEntity = EntityRegistry.ASSIMILATED_COW.get().create(world);
                            assert assimilatedCowEntity != null;
                            assimilatedCowEntity.moveTo(x, y, z);
                            world.addFreshEntity(assimilatedCowEntity);
                            cow.discard();
                            cow.level().playSound((Player) null, cow.blockPosition(), SoundRegistry.ENTITY_TURN.get(), SoundSource.HOSTILE, 1.6F, 1.0F);
                            cow.level().playSound((Player) null, cow.blockPosition(), SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.3F, 1.0F);
                            if (cow.getLastHurtByMob() != null) {
                                assimilatedCowEntity.setTarget(cow.getLastHurtByMob());
                                assimilatedCowEntity.getLookControl().setLookAt(cow.getLastHurtByMob());
                            }
                            if (cow.level() instanceof ServerLevel server) {
                                server.sendParticles(ParticleTypes.EXPLOSION, cow.getX(), cow.getY() + 1, cow.getZ(), 3, 0.4, 1.0, 0.4, 0);
                                WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(server);
                                int currentScore = worldDataRegistry.getScore();
                                worldDataRegistry.setScore(currentScore + 2);
                            }
                        }
                        else if (livingEntity instanceof Pig pig && FightOrDieMutationsConfig.SERVER.assimilated_pig_assimilation.get()) {
                            AssimilatedPigEntity assimilatedPigEntity = EntityRegistry.ASSIMILATED_PIG.get().create(world);
                            assert assimilatedPigEntity != null;
                            assimilatedPigEntity.moveTo(x, y, z);
                            world.addFreshEntity(assimilatedPigEntity);
                            pig.discard();
                            pig.level().playSound((Player) null, pig.blockPosition(), SoundRegistry.ENTITY_TURN.get(), SoundSource.HOSTILE, 1.6F, 1.0F);
                            pig.level().playSound((Player) null, pig.blockPosition(), SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.3F, 1.0F);
                            if (pig.getLastHurtByMob() != null) {
                                assimilatedPigEntity.setTarget(pig.getLastHurtByMob());
                                assimilatedPigEntity.getLookControl().setLookAt(pig.getLastHurtByMob());
                            }
                            if (pig.level() instanceof ServerLevel server) {
                                server.sendParticles(ParticleTypes.EXPLOSION, pig.getX(), pig.getY() + 1, pig.getZ(), 3, 0.4, 1.0, 0.4, 0);
                                WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(server);
                                int currentScore = worldDataRegistry.getScore();
                                worldDataRegistry.setScore(currentScore + 2);
                            }
                        }
                        else if (livingEntity instanceof Fox fox && FightOrDieMutationsConfig.SERVER.assimilated_fox_assimilation.get()) {
                            AssimilatedFoxEntity assimilatedFoxEntity = EntityRegistry.ASSIMILATED_FOX.get().create(world);
                            assert assimilatedFoxEntity != null;
                            assimilatedFoxEntity.moveTo(x, y, z);
                            world.addFreshEntity(assimilatedFoxEntity);
                            fox.discard();
                            fox.level().playSound((Player) null, fox.blockPosition(), SoundRegistry.ENTITY_TURN.get(), SoundSource.HOSTILE, 1.6F, 1.0F);
                            fox.level().playSound((Player) null, fox.blockPosition(), SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.3F, 1.0F);
                            if (fox.getLastHurtByMob() != null) {
                                assimilatedFoxEntity.setTarget(fox.getLastHurtByMob());
                                assimilatedFoxEntity.getLookControl().setLookAt(fox.getLastHurtByMob());
                            }
                            if (fox.level() instanceof ServerLevel server) {
                                server.sendParticles(ParticleTypes.EXPLOSION, fox.getX(), fox.getY() + 1, fox.getZ(), 3, 0.4, 1.0, 0.4, 0);
                                WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(server);
                                int currentScore = worldDataRegistry.getScore();
                                worldDataRegistry.setScore(currentScore + 2);
                            }
                        }
                        else if (livingEntity instanceof Creeper creeper && FightOrDieMutationsConfig.SERVER.assimilated_creeper_assimilation.get()) {
                            AssimilatedCreeperEntity assimilatedCreeperEntity = EntityRegistry.ASSIMILATED_CREEPER.get().create(world);
                            assert assimilatedCreeperEntity != null;
                            assimilatedCreeperEntity.moveTo(x, y, z);
                            world.addFreshEntity(assimilatedCreeperEntity);
                            creeper.discard();
                            creeper.level().playSound((Player) null, creeper.blockPosition(), SoundRegistry.ENTITY_TURN.get(), SoundSource.HOSTILE, 1.6F, 1.0F);
                            creeper.level().playSound((Player) null, creeper.blockPosition(), SoundEvents.ZOMBIE_INFECT, SoundSource.HOSTILE, 1.3F, 1.0F);
                            if (creeper.getLastHurtByMob() != null) {
                                assimilatedCreeperEntity.setTarget(creeper.getLastHurtByMob());
                                assimilatedCreeperEntity.getLookControl().setLookAt(creeper.getLastHurtByMob());
                            }
                            if (creeper.level() instanceof ServerLevel server) {
                                server.sendParticles(ParticleTypes.EXPLOSION, creeper.getX(), creeper.getY() + 1, creeper.getZ(), 3, 0.4, 1.0, 0.4, 0);
                                WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(server);
                                int currentScore = worldDataRegistry.getScore();
                                worldDataRegistry.setScore(currentScore + 5);
                            }
                        }
                    }
                }
            }
        }
    }
}
