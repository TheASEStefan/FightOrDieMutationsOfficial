package net.teamabyssal.extra;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.categories.Assimilated;
import net.teamabyssal.entity.categories.Head;
import net.teamabyssal.entity.categories.Infector;
import net.teamabyssal.entity.categories.Parasite;
import net.teamabyssal.entity.custom.MalruptorEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.handlers.PhaseHandler;
import net.teamabyssal.registry.EffectRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class LivingSpawnedModifications {
    @SubscribeEvent()
    public static void addSpawn(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            Level world = entity.level();
            if (!world.isClientSide) {
                if (PhaseHandler.getPhase() > 3 && PhaseHandler.getPhase() < 5) {
                    entity.addEffect(new MobEffectInstance(EffectRegistry.HIVE_SICKNESS.get(), 3600, 0), entity);
                } else if (PhaseHandler.getPhase() > 4 && PhaseHandler.getPhase() < 6) {
                    entity.addEffect(new MobEffectInstance(EffectRegistry.HIVE_SICKNESS.get(), 4800, 0), entity);
                } else if (PhaseHandler.getPhase() > 5) {
                    entity.addEffect(new MobEffectInstance(EffectRegistry.HIVE_SICKNESS.get(), 6000, 0), entity);
                }
            }
        }
        if (event.getEntity() instanceof Villager) {
            Villager abstractVillager = (Villager) event.getEntity();
            abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, Infector.class, 16.0F, 0.7F, 0.75F));
            abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, Parasite.class, 16.0F, 0.7F, 0.75F));
            abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, Assimilated.class, 16.0F, 0.7F, 0.75F));
            abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, Head.class, 16.0F, 0.7F, 0.75F));
        }
        else if (event.getEntity() instanceof WanderingTrader) {
            WanderingTrader wanderingTraderEntity = (WanderingTrader) event.getEntity();
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Infector.class, 16.0F, 0.7F, 0.75F));
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Parasite.class, 16.0F, 0.7F, 0.75F));
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Assimilated.class, 16.0F, 0.7F, 0.75F));
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Head.class, 16.0F, 0.7F, 0.75F));
        }
        else if (event.getEntity() instanceof Zombie) {
            Zombie zombie = (Zombie) event.getEntity();
            zombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, Infector.class, true));
            zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, Parasite.class, true));
            zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, Assimilated.class, true));
            zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, Head.class, true));
        }
        else if (event.getEntity() instanceof IronGolem) {
            IronGolem ironGolem = (IronGolem) event.getEntity();
            ironGolem.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(ironGolem, Infector.class, true));
            ironGolem.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ironGolem, Parasite.class, true));
            ironGolem.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ironGolem, Assimilated.class, true));
            ironGolem.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ironGolem, Head.class, true));
        }
        else if (event.getEntity() instanceof MalruptorEntity) {
            MalruptorEntity malruptorEntity = (MalruptorEntity) event.getEntity();
            Level world = malruptorEntity.level();
            if (!world.isClientSide) {
                if (Math.random() <= 0.85F) {
                    malruptorEntity.setKills(malruptorEntity.getKills() + malruptorEntity.getRandom().nextInt(15));
                }
            }
        }





    }
}
