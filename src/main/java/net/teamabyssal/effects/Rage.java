package net.teamabyssal.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.teamabyssal.entity.categories.*;
import net.teamabyssal.registry.EffectRegistry;

public class Rage extends MobEffect {

    public Rage() {
        super(MobEffectCategory.BENEFICIAL, 0x000000);
    }


    public void applyEffectTick(LivingEntity entity, int intense) {
        if (entity instanceof Parasite || entity instanceof Head || entity instanceof Assimilated || entity instanceof Infector || entity instanceof AdvancedAssimilated || entity instanceof Primitive || entity instanceof Adapted) {
            if (this == EffectRegistry.RAGE.get()) {
                    if (entity.level() instanceof ServerLevel world) {
                        world.sendParticles(ParticleTypes.FLAME, entity.getX(), entity.getEyeY() - 0.2, entity.getZ(), 23, 0.2, 0.4, 0.1, 0);
                        for (int index = 0; index <= 1 + entity.getRandom().nextInt(6); index++) {
                            world.sendParticles(ParticleTypes.FLAME, entity.getRandomX(0.6), entity.getEyeY() - 0.2, entity.getRandomZ(0.8), 8, 0.2, 0.4, 0.1, 0);
                        }
                    }

            }
        }

    }


    public boolean isDurationEffectTick(int duration, int intensity) {
        if (this == EffectRegistry.RAGE.get()) {
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
