package net.teamabyssal.entity.categories;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.network.NetworkHooks;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.ai.*;
import net.teamabyssal.registry.*;


public class Assimilated extends Monster {

    public static final EntityDataAccessor<Integer> RAGE_TICKS = SynchedEntityData.defineId(Assimilated.class, EntityDataSerializers.INT);

    public Assimilated(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 10.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
        this.xpReward = 8;
        EntityRegistry.PARASITES.add(this);
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
    public void setRageTicks(int ticks) {
        entityData.set(RAGE_TICKS, ticks);
    }
    public int getRageTicks() {
        return this.entityData.get(RAGE_TICKS);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("rage_ticks",entityData.get(RAGE_TICKS));
    }


    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        entityData.set(RAGE_TICKS, tag.getInt("rage_ticks"));
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(RAGE_TICKS, 0);
    }

    @Override
    public void tick() {
        if (this.getTarget() != null) {
            this.setRageTicks(this.getRageTicks() + 1);
        }
        if (this.getRageTicks() > 600 && Math.random() <= 0.1 && !this.hasEffect(EffectRegistry.RAGE.get())) {
            this.addEffect(new MobEffectInstance(EffectRegistry.RAGE.get(), 300, 0), this);
            this.setRageTicks(0);
        }
        super.tick();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(3, new FloatDiveGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, this::targetPredicate));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    private boolean targetPredicate(LivingEntity liv) {
        return !(liv instanceof Assimilated || liv instanceof AdvancedAssimilated || liv instanceof Parasite || liv instanceof Infector || liv instanceof Head || liv instanceof Animal || liv instanceof Squid || liv instanceof ArmorStand || liv instanceof AbstractFish || liv instanceof Bat || FightOrDieMutationsConfig.SERVER.blacklist.get().contains(liv.getEncodeId()));
    }

    public static boolean checkMonsterAssimilatedRules(EntityType<? extends Assimilated> entityType, ServerLevelAccessor levelAccessor, MobSpawnType mobSpawnType, BlockPos pos, RandomSource source) {

        WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry((ServerLevel) levelAccessor.getLevel());
        int currentPhase = worldDataRegistry.getPhase();

        return levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, source) && checkMobSpawnRules(entityType, levelAccessor, mobSpawnType, pos, source) && currentPhase > 1;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        Entity entity = pSource.getEntity();
        if (Math.random() <= 0.3F) {
            this.spawnAtLocation(ItemRegistry.RIPPING_FLESH.get());
        }
        if (Math.random() <= 0.05) {
            this.spawnAtLocation(ItemRegistry.SICKENED_HEART.get());
        }
        else if (Math.random() <= 0.05) {
            this.spawnAtLocation(ItemRegistry.ROTTEN_BRAIN.get());
        }

    }


    @Override
    public void die(DamageSource source) {
        if (this.level() instanceof ServerLevel world) {
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(world);
            int currentScore = worldDataRegistry.getScore();
            int currentPhase = worldDataRegistry.getPhase();
            if (Math.random() <= 0.85F && currentPhase > 2) {
                worldDataRegistry.setScore(currentScore - 5);
            }
        }
        super.die(source);
    }
}
