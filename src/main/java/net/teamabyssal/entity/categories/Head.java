package net.teamabyssal.entity.categories;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.teamabyssal.entity.ai.FloatDiveGoal;
import net.teamabyssal.handlers.PhaseHandler;
import net.teamabyssal.handlers.ScoreHandler;
import net.teamabyssal.registry.EffectRegistry;
import net.teamabyssal.registry.EntityRegistry;


public class Head extends Monster {


    public Head(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 10.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
        this.xpReward = 2;
        EntityRegistry.PARASITES.add(this);
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
        return 1600;
    }
    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity instanceof LivingEntity && Math.random() <= 0.85F) {
            ((LivingEntity) entity).addEffect(new MobEffectInstance(EffectRegistry.HIVE_SICKNESS.get(), 1200, 0), entity);
        }
        return super.doHurtTarget(entity);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new FloatDiveGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Zombie.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Skeleton.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Spider.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Silverfish.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Endermite.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EnderMan.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Creeper.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Witch.class, true));
    }


    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        Entity entity = pSource.getEntity();

    }

    @Override
    public void die(DamageSource source) {
        this.level().addParticle(DustParticleOptions.REDSTONE, this.getX(), this.getY() + 0.3F, this.getZ(), 0.0D, 0.0D, 0.0D);
        this.level().addParticle(DustParticleOptions.REDSTONE, this.getX(), this.getY() + 0.4F, this.getZ() + 0.1F, 0.0D, 0.0D, 0.0D);
        if (Math.random() <= 0.85F && PhaseHandler.getPhase() > 2) {
            ScoreHandler.setScore(ScoreHandler.getScore() - 1);
        }

        super.die(source);
    }

}
