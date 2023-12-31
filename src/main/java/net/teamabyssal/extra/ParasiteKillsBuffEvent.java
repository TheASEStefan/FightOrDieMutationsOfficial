package net.teamabyssal.extra;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.handlers.PhaseHandler;
import net.teamabyssal.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class ParasiteKillsBuffEvent {
    @SubscribeEvent
    public static void ParasiteBuffEvent(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null && !event.getEntity().level().isClientSide && event.getSource().getEntity() != null) {
            LivingEntity entity = (LivingEntity) event.getSource().getEntity();
            Level world = entity.level();
                if (!world.isClientSide && EntityRegistry.PARASITES.contains(entity)) {
                    if (PhaseHandler.getPhase() >= 2) {
                        entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 0), entity);
                    }
                    if (PhaseHandler.getPhase() >= 4) {
                        entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), entity);
                    }
                }
        }
    }
}
