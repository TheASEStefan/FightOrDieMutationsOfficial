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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.network.NetworkHooks;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.ai.FloatDiveGoal;
import net.teamabyssal.entity.ai.InfectorSearchAreaGoal;
import net.teamabyssal.entity.ai.InfectorTargettingGoal;
import net.teamabyssal.handlers.PhaseHandler;
import net.teamabyssal.registry.EntityRegistry;
import org.jetbrains.annotations.Nullable;

public class Infector extends Monster {

    @Nullable
    BlockPos searchPos;

    public Infector(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 12.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.2F);
        this.xpReward = 5;
        EntityRegistry.PARASITES.add(this);
    }

    @Nullable
    public BlockPos getSearchPos() {
        return searchPos;
    }

    public void setSearchPos(@Nullable BlockPos searchPos) {
        this.searchPos = searchPos;
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

    public int getMaxAirSupply() {
        return 1200;
    }
    protected int increaseAirSupply(int p_28389_) {
        return this.getMaxAirSupply();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof LivingEntity && Math.random() < 0.3F) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0), entity);
        }
        return super.doHurtTarget(entity);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new InfectorSearchAreaGoal(this, 1.2));
        this.goalSelector.addGoal(1,new InfectorTargettingGoal(this));
        this.goalSelector.addGoal(3,new FloatDiveGoal(this));
    }


    public static boolean checkMonsterInfectorRules(EntityType<? extends Infector> entityType, ServerLevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos pos, RandomSource source) {

        return levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, source) && checkMobSpawnRules(entityType, levelAccessor, mobSpawnType, pos, source) && PhaseHandler.getPhase() > 0;
    }


    @Override
    public void die(DamageSource source) {
        if (source == this.damageSources().generic()) {
            this.level().addParticle(DustParticleOptions.REDSTONE, this.getX(), this.getY() + 0.8, this.getZ(), 0.0D, 0.0D, 0.0D);
        }
        super.die(source);
    }
}
