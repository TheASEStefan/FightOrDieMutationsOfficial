package net.teamabyssal.extra;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.constants.PossibleAssimilated;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class AttackClass {
    @SubscribeEvent
    public static void EntityAttack(LivingHurtEvent event) {
        if (event != null && event.getEntity() != null) {
            Entity entity = event.getEntity();
            PossibleAssimilated assimilated = (PossibleAssimilated) entity;
            if (entity instanceof Cow || entity instanceof Sheep || entity instanceof Pig || entity instanceof Fox) {
                assimilated.IsetAssimilationProgress(1);
            }
        }
    }
}
