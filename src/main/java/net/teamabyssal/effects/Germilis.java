package net.teamabyssal.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.teamabyssal.entity.categories.*;
import net.teamabyssal.registry.DamageTypeRegistry;
import net.teamabyssal.registry.EffectRegistry;

import java.util.ArrayList;
import java.util.List;

public class Germilis extends MobEffect {
    public Germilis() {
        super(MobEffectCategory.HARMFUL, -16777216);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        ArrayList<ItemStack> ret = new ArrayList<>();
        ret.add(ItemStack.EMPTY);
        return ret;
    }

    public void applyEffectTick(LivingEntity entity, int intense) {
        if (!(entity instanceof Parasite || entity instanceof Head || entity instanceof Assimilated || entity instanceof Infector || entity instanceof AdvancedAssimilated || entity instanceof Primitive || entity instanceof Adapted )) {
            if (this == EffectRegistry.GERMILIS.get()) {
                entity.hurt(DamageTypeRegistry.germilis_damage(entity), 1.0F);
            }
        }

    }
    public boolean isDurationEffectTick(int duration, int intensity) {
        if (this == EffectRegistry.GERMILIS.get()) {
            int i = 60 >> intensity;
            if (i > 0) {
                return duration % i == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}
