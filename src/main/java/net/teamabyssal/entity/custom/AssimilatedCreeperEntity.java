package net.teamabyssal.entity.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.constants.MathHelper;
import net.teamabyssal.entity.ai.CustomMeleeAttackGoal;
import net.teamabyssal.entity.categories.AdvancedAssimilated;
import net.teamabyssal.entity.categories.Leaper;
import net.teamabyssal.registry.*;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class AssimilatedCreeperEntity extends AdvancedAssimilated implements GeoEntity, Leaper {
    private final int minDamage = 2;
    private final int maxDamage = 4;
    private static boolean leaping = false;
    private final float extraRadius = ((MathHelper.HEX + Mth.clamp(3, MathHelper.HEX, MathHelper.PI) + (MathHelper.DELTA / 3)) / 10);
    private final float explosionRadius = (MathHelper.HEX * 2);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public AssimilatedCreeperEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        boolean flag = super.causeFallDamage(pFallDistance, pMultiplier / ((float) (minDamage + maxDamage) / 2), pSource);
        return flag;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new CustomMeleeAttackGoal(this, 1.5, false) {
            @Override
            protected double getAttackReachSqr(LivingEntity entity) {
                return 2.0 + entity.getBbWidth() * entity.getBbWidth();
            }
        });
        this.goalSelector.addGoal(2, new CreeperLeapGoal(this, 0.45F));
    }

    @Nullable
    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.ATTACK_KNOCKBACK, 0.1D)
                .add(Attributes.FOLLOW_RANGE, 64D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35)
                .add(Attributes.MAX_HEALTH, FightOrDieMutationsConfig.SERVER.assimilated_creeper_health.get())
                .add(Attributes.ATTACK_DAMAGE, FightOrDieMutationsConfig.SERVER.assimilated_creeper_damage.get())
                .add(Attributes.ARMOR, 6D);

    }


    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(ItemTags.CREEPER_IGNITERS)) {
            SoundEvent soundevent = itemstack.is(Items.FIRE_CHARGE) ? SoundEvents.FIRECHARGE_USE : SoundEvents.FLINTANDSTEEL_USE;
            this.level().playSound(pPlayer, this.getX(), this.getY(), this.getZ(), soundevent, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            if (!this.level().isClientSide) {
                this.explodeThis();
                if (!itemstack.isDamageableItem()) {
                    itemstack.shrink(1);
                } else {
                    itemstack.hurtAndBreak(1, pPlayer, (p_32290_) -> {
                        p_32290_.broadcastBreakEvent(pHand);
                    });
                }
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    public boolean isLeaping() {
        return !this.onGround() && leaping && this.isAlive() && this.hurtTime < 1;
    }


    @Override
    public void tick() {
        if (this.getTarget() != null && this.isAlive()) {
            Entity attackTarget = this.getTarget();
            if (attackTarget instanceof Player || attackTarget instanceof IronGolem) {
                if (this.distanceTo(attackTarget) < 3) {
                    this.explodeThis();
                }
            }

        }
        super.tick();
    }


    private void explodeThis() {
        if (!this.level().isClientSide) {
            this.dead = true;
            this.level().explode(this, this.getX(), this.getY(), this.getZ(), (float) this.explosionRadius * this.extraRadius, Level.ExplosionInteraction.MOB);
            this.discard();
            this.spawnLingeringCloud();
        }
    }

    private void spawnLingeringCloud() {
        AreaEffectCloud cloud = new AreaEffectCloud(this.level(), this.getX(), this.getY(), this.getZ());
        cloud.setRadius(3.5F);
        cloud.setRadiusOnUse(-0.5F);
        cloud.setWaitTime(10);
        cloud.setDuration(cloud.getDuration() / 3);
        cloud.setRadiusPerTick(-cloud.getRadius() / (float)cloud.getDuration());
        cloud.addEffect(new MobEffectInstance(EffectRegistry.HIVE_SICKNESS.get(), 6000, 1));
        cloud.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600, 1));

        this.level().addFreshEntity(cloud);
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(
                new AnimationController<>(this, "controllerOP", 7, event -> {
                    if (event.isMoving() && !this.isAggressive() && !this.isLeaping()) {
                        return event.setAndContinue(RawAnimation.begin().thenLoop("assimilated_creeper_walk"));
                    }
                    else if (event.isMoving() && this.isAggressive() && !this.isLeaping()) {
                        return event.setAndContinue(RawAnimation.begin().thenLoop("assimilated_creeper_target"));
                    }
                    else if (this.isLeaping()) {
                        return event.setAndContinue(RawAnimation.begin().thenLoop("assimilated_creeper_leap"));
                    }
                    return event.setAndContinue(RawAnimation.begin().thenLoop("assimilated_creeper_idle"));
                }));

    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void die(DamageSource source) {
        /* if (Math.random() <= 0.25F) {
            //this.DropCowHead(this);
        }
         */

        super.die(source);
    }

    /*

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.ASSIMILATED_ANIMAL_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundRegistry.ENTITY_ASSIMILATED_HURT.get();
    }


    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.ASSIMILATED_ANIMAL_AMBIENT.get();
    }


   */


    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        Entity entity = pSource.getEntity();

    }


    public static class CreeperLeapGoal extends Goal {
        private final AssimilatedCreeperEntity mob;
        private LivingEntity target;
        private final float yd;


        public CreeperLeapGoal(AssimilatedCreeperEntity mob, float v) {
            this.mob = mob;
            this.yd = v;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse() {
            this.target = this.mob.getTarget();
            if (this.target == null) {
                return false;
            }
            else if (this.mob.isInWater()) {
                return false;
            }
            else {
                double d0 = this.mob.distanceTo(this.target);
                if (d0 > 6.0D && d0 < 14.0D) {
                    if (!this.mob.onGround()) {
                        return false;
                    } else {
                        return this.mob.getRandom().nextInt(reducedTickDelay(14)) == 0;
                    }
                } else {
                    return false;
                }
            }

        }

        @Override
        public void tick() {
            if (this.mob.getTarget() != null) {
                this.mob.getLookControl().setLookAt(this.target);
                leaping = true;
            }
        }

        public void start() {
            Vec3 vec3 = this.mob.getDeltaMovement();
            Vec3 vec31 = new Vec3((this.target.getX() - this.mob.getX()) / Mth.floor(MathHelper.HEX), 0.0D, this.target.getZ() - this.mob.getZ());
            if (vec31.lengthSqr() > 1.0E-7D) {
                vec31 = vec31.normalize().scale(2D).add(vec3.scale(1.5D));
            }

            this.mob.setDeltaMovement(vec31.x + yd, this.yd + (MathHelper.PI / 36), vec31.z + yd);
        }

        @Override
        public void stop() {
            super.stop();
        }
    }
}
