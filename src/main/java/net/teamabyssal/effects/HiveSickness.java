package net.teamabyssal.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.categories.*;
import net.teamabyssal.registry.EffectRegistry;
import net.teamabyssal.registry.EntityRegistry;

public class HiveSickness extends MobEffect {

    public HiveSickness() {
        super(MobEffectCategory.HARMFUL, -16777216);
    }

    public void applyEffectTick(LivingEntity entity, int intense) {
        if (!( EntityRegistry.PARASITES.contains(entity) || FightOrDieMutationsConfig.SERVER.hive_sickness.get().contains(entity.getEncodeId()))) {
            if (this == EffectRegistry.HIVE_SICKNESS.get()) {
                if (!entity.getCommandSenderWorld().isClientSide && entity instanceof Player player && player.getFoodData().getFoodLevel() > 0 && intense < 1){
                    player.causeFoodExhaustion(1.0F);
                }
            }
        }

    }
    @Override
    public void initializeClient(java.util.function.Consumer<IClientMobEffectExtensions> consumer) {
        consumer.accept(new IClientMobEffectExtensions() {
            @Override
            public boolean isVisibleInGui(MobEffectInstance effect) {
                return false;
            }
        });
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
