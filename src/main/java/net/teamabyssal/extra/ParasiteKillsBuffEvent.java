package net.teamabyssal.extra;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.categories.*;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.EffectRegistry;
import net.teamabyssal.registry.EntityRegistry;
import net.teamabyssal.registry.WorldDataRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class ParasiteKillsBuffEvent {
    @SubscribeEvent
    public static void ParasiteBuffEvent(LivingDeathEvent event) {
        if (event != null && event.getEntity() != null && !event.getEntity().level().isClientSide && event.getSource().getEntity() != null) {
            LivingEntity entity = (LivingEntity) event.getSource().getEntity();
            Level world = entity.level();
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry((ServerLevel) world);
            int currentPhase = worldDataRegistry.getPhase();
                if (!world.isClientSide && (entity instanceof Parasite || entity instanceof Head || entity instanceof Assimilated || entity instanceof Infector || entity instanceof AdvancedAssimilated || entity instanceof Primitive || entity instanceof Adapted)) {
                    if (currentPhase >= 3 && Math.random() <= 0.7) {
                        entity.addEffect(new MobEffectInstance(EffectRegistry.RAGE.get(), 400, 0), entity);
                    }
                }
        }
    }
}
