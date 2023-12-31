package net.teamabyssal.extra;

import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.custom.AssimilatedHumanHeadEntity;
import net.teamabyssal.entity.custom.MalruptorEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.handlers.ScoreHandler;
import net.teamabyssal.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class MalruptorKillsEvent {

    @SubscribeEvent
    public static void MalruptorKillEvent(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null && !event.getEntity().level().isClientSide && event.getSource().getEntity() != null) {

            if (EntityRegistry.PARASITES.contains(event.getSource().getEntity()) && event.getEntity() != null) {
                ScoreHandler.setScore(ScoreHandler.getScore() + 3);
                if (event.getEntity() instanceof Player || event.getEntity() instanceof IronGolem) {
                    ScoreHandler.setScore(ScoreHandler.getScore() + 10);
                }
            }

            if (event.getSource().getEntity() instanceof MalruptorEntity) {
            MalruptorEntity malruptorEntity = (MalruptorEntity) event.getSource().getEntity();
            Level world = malruptorEntity.level();
            if (!world.isClientSide && malruptorEntity.isAlive()) {
                malruptorEntity.setKills(malruptorEntity.getKills() + 1);
               }
            }
            else if (event.getSource().getEntity() instanceof AssimilatedHumanHeadEntity) {
                AssimilatedHumanHeadEntity assimilatedHumanHeadEntity = (AssimilatedHumanHeadEntity) event.getSource().getEntity();
                Level world = assimilatedHumanHeadEntity.level();
                if (!world.isClientSide && assimilatedHumanHeadEntity.isAlive()) {
                    assimilatedHumanHeadEntity.setKills(assimilatedHumanHeadEntity.getKills() + 1);
                }
            }
        }
    }
}
