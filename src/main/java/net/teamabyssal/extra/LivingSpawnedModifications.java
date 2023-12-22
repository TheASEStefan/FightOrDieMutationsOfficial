package net.teamabyssal.extra;

import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.categories.Infector;
import net.teamabyssal.entity.categories.Parasite;
import net.teamabyssal.fight_or_die.FightOrDieMutations;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class LivingSpawnedModifications {
    @SubscribeEvent()
    public static void addSpawn(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof Villager) {
            Villager abstractVillager = (Villager) event.getEntity();
            abstractVillager.goalSelector.addGoal(1, new AvoidEntityGoal(abstractVillager, Infector.class, 16.0F, 0.7F, 0.75F));
        }
        if (event.getEntity() instanceof WanderingTrader) {
            WanderingTrader wanderingTraderEntity = (WanderingTrader) event.getEntity();
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Infector.class, 16.0F, 0.7F, 0.75F));
        }
        if (event.getEntity() instanceof WanderingTrader) {
            WanderingTrader wanderingTraderEntity = (WanderingTrader) event.getEntity();
            wanderingTraderEntity.goalSelector.addGoal(1, new AvoidEntityGoal(wanderingTraderEntity, Infector.class, 16.0F, 0.7F, 0.75F));
        }
        if (event.getEntity() instanceof Zombie) {
            Zombie zombie = (Zombie) event.getEntity();
            zombie.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(zombie, Infector.class, true));
            zombie.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(zombie, Parasite.class, true));
        }
        if (event.getEntity() instanceof IronGolem) {
            IronGolem ironGolem = (IronGolem) event.getEntity();
            ironGolem.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(ironGolem, Infector.class, true));
            ironGolem.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(ironGolem, Parasite.class, true));
        }


    }
}
