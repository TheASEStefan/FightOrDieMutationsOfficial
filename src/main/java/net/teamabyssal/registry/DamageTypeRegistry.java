package net.teamabyssal.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import org.jetbrains.annotations.Nullable;

public class DamageTypeRegistry {
    public static ResourceKey<DamageType> create(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(FightOrDieMutations.MODID,id));
    }
    public static final ResourceKey<DamageType> HIVE_SICKNESS_DAMAGE = create("hive_sickness_damage");
    public static DamageSource damageSource(Entity entity, ResourceKey<DamageType> registryKey){
        return new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(registryKey));
    }
    public static DamageSource damageSource(Entity entity, ResourceKey<DamageType> registryKey ,@Nullable Entity entity2){
        return new DamageSource(entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(registryKey),entity2);
    }
    public static DamageSource hive_sickness_damage(LivingEntity entity){
        return damageSource(entity, HIVE_SICKNESS_DAMAGE);
    }
}
