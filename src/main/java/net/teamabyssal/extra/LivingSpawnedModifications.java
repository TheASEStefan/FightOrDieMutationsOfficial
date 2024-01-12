package net.teamabyssal.extra;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.categories.*;
import net.teamabyssal.entity.custom.AssimilatedHumanHeadEntity;
import net.teamabyssal.entity.custom.MalruptorEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.EffectRegistry;
import net.teamabyssal.registry.EntityRegistry;
import net.teamabyssal.registry.WorldDataRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class LivingSpawnedModifications {
    @SubscribeEvent()
    public static void addSpawn(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            Level world = entity.level();
            if (!world.isClientSide && world instanceof ServerLevel serverLevel && !EntityRegistry.PARASITES.contains(entity)) {
                WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(serverLevel);
                int currentPhase = worldDataRegistry.getPhase();
                if (currentPhase == 4) {
                    entity.addEffect(new MobEffectInstance(EffectRegistry.HIVE_SICKNESS.get(), 3600, 0), entity);
                } else if (currentPhase == 5) {
                    entity.addEffect(new MobEffectInstance(EffectRegistry.HIVE_SICKNESS.get(), 4800, 0), entity);
                } else if (currentPhase > 5) {
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
            abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, AdvancedAssimilated.class, 16.0F, 0.7F, 0.75F));
        }
        else if (event.getEntity() instanceof WanderingTrader) {
            WanderingTrader wanderingTraderEntity = (WanderingTrader) event.getEntity();
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Infector.class, 16.0F, 0.7F, 0.75F));
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Parasite.class, 16.0F, 0.7F, 0.75F));
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Assimilated.class, 16.0F, 0.7F, 0.75F));
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Head.class, 16.0F, 0.7F, 0.75F));
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, AdvancedAssimilated.class, 16.0F, 0.7F, 0.75F));
        }
        else if (event.getEntity() instanceof Zombie) {
            Zombie zombie = (Zombie) event.getEntity();
            zombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, Infector.class, true));
            zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, Parasite.class, true));
            zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, Assimilated.class, true));
            zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, Head.class, true));
            zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, AdvancedAssimilated.class, true));
        }
        else if (event.getEntity() instanceof IronGolem) {
            IronGolem ironGolem = (IronGolem) event.getEntity();
            ironGolem.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(ironGolem, Infector.class, true));
            ironGolem.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ironGolem, Parasite.class, true));
            ironGolem.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ironGolem, Assimilated.class, true));
            ironGolem.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ironGolem, Head.class, true));
            ironGolem.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ironGolem, AdvancedAssimilated.class, true));
        }
        else if (event.getEntity() instanceof AssimilatedHumanHeadEntity) {
            AssimilatedHumanHeadEntity assimilatedHumanHeadEntity = (AssimilatedHumanHeadEntity) event.getEntity();
            Level world = assimilatedHumanHeadEntity.level();
            if (!world.isClientSide) {
                if (Math.random() <= 0.85F) {
                    assimilatedHumanHeadEntity.setKills(assimilatedHumanHeadEntity.getKills() + assimilatedHumanHeadEntity.getRandom().nextInt(2));
                }
            }
        }





    }
}
