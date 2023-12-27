package net.teamabyssal.entity.categories;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.ai.FloatDiveGoal;
import net.teamabyssal.handlers.PhaseHandler;
import net.teamabyssal.handlers.ScoreHandler;
import net.teamabyssal.registry.EffectRegistry;
import net.teamabyssal.registry.EntityRegistry;

import javax.crypto.spec.PSource;

public class Parasite extends Monster {

    public Parasite(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -0.8F);
        this.xpReward = 2;
        EntityRegistry.PARASITES.add(this);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }


    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInFluidType()) {
            this.moveRelative(0.1F, pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.6D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getEntity() != null && pSource.getEntity() instanceof LivingEntity entity && Math.random() <= 0.65F) {
            entity.addEffect(new MobEffectInstance(EffectRegistry.HIVE_SICKNESS.get(), 600, 0), entity);
        }
        return super.hurt(pSource, pAmount);
    }


    public int getMaxAirSupply() {
        return Math.max(600, 1200) / 2 ;
    }
    protected int increaseAirSupply(int p_28389_) {
        return this.getMaxAirSupply();
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(10, new FloatDiveGoal(this));
    }

    public static boolean checkMonsterParasiteRules(EntityType<? extends Parasite> entityType, ServerLevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos pos, RandomSource source) {

        return levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, source) && checkMobSpawnRules(entityType, levelAccessor, mobSpawnType, pos, source) && PhaseHandler.getPhase() >= 0;
    }

    @Override
    public void die(DamageSource source) {
        if (source == this.damageSources().generic()) {
            this.level().addParticle(DustParticleOptions.REDSTONE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        super.die(source);
    }

}
