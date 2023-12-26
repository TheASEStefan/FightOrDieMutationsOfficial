package net.teamabyssal.extra;

import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.Score;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.custom.MalruptorEntity;
import net.teamabyssal.entity.custom.MargrouperEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.handlers.ScoreHandler;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class MalruptorKillsEvent {

    @SubscribeEvent
    public static void MalruptorKillEvent(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null && !event.getEntity().level().isClientSide && event.getSource().getEntity() != null) {
            if (event.getSource().getEntity() instanceof MalruptorEntity) {
            MalruptorEntity malruptorEntity = (MalruptorEntity) event.getSource().getEntity();
            Level world = malruptorEntity.level();
            if (!world.isClientSide && malruptorEntity.isAlive()) {
                malruptorEntity.setKills(malruptorEntity.getKills() + 1);
                ScoreHandler.setScore(ScoreHandler.getScore() + 3);
                if (event.getEntity() != null && (event.getEntity() instanceof Player || event.getEntity() instanceof IronGolem)) {
                    ScoreHandler.setScore(ScoreHandler.getScore() + 10);
                }
               }
            }
            else if (event.getSource().getEntity() instanceof MargrouperEntity) {
                MargrouperEntity margrouperEntity = (MargrouperEntity) event.getSource().getEntity();
                Level world = margrouperEntity.level();
                if (!world.isClientSide && margrouperEntity.isAlive()) {
                    ScoreHandler.setScore(ScoreHandler.getScore() + 3);
                    if (event.getEntity() != null && (event.getEntity() instanceof Player || event.getEntity() instanceof IronGolem)) {
                        ScoreHandler.setScore(ScoreHandler.getScore() + 10);
                    }
                }
            }
        }
    }
}
