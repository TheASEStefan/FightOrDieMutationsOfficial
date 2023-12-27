package net.teamabyssal.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.categories.*;
import net.teamabyssal.registry.EffectRegistry;

public class HiveSickness extends MobEffect {

    public HiveSickness() {
        super(MobEffectCategory.HARMFUL, 0x000000);
    }

    public void applyEffectTick(LivingEntity entity, int intense) {
        if (!( entity instanceof Infector || entity instanceof Assimilated || entity instanceof AdvancedAssimilated || entity instanceof Abandoned || entity instanceof Ancient || entity instanceof Finale || entity instanceof Guard || entity instanceof Primitive || entity instanceof Parasite || FightOrDieMutationsConfig.SERVER.hive_sickness.get().contains(entity.getEncodeId()))){
            if (this == EffectRegistry.HIVE_SICKNESS.get()) {
                if (!entity.getCommandSenderWorld().isClientSide && entity instanceof Player player && player.getFoodData().getFoodLevel() > 0 && intense < 1){
                    player.causeFoodExhaustion(1.0F);
                }
            }
        }

    }

    public boolean isDurationEffectTick(int duration, int intensity) {
        if (this == EffectRegistry.HIVE_SICKNESS.get()) {
            int i = 80 >> intensity;
            if (i > 0) {
                return duration % i == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}
