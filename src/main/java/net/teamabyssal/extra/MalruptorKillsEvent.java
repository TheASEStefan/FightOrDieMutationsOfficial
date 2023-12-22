package net.teamabyssal.extra;

import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.custom.MalruptorEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class MalruptorKillsEvent {

    @SubscribeEvent
    public static void MalruptorKillEvent(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null && !event.getEntity().level().isClientSide && event.getSource().getEntity() != null && event.getSource().getEntity() instanceof MalruptorEntity) {
            MalruptorEntity malruptorEntity = (MalruptorEntity) event.getSource().getEntity();
            Level world = malruptorEntity.level();
            if (!world.isClientSide && malruptorEntity.isAlive()) {
                malruptorEntity.setKills(malruptorEntity.getKills() + 1);
            }
        }
    }
}
