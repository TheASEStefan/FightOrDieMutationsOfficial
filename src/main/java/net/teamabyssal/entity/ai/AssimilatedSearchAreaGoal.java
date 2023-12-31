package net.teamabyssal.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.teamabyssal.entity.categories.Assimilated;

import java.util.EnumSet;

public class AssimilatedSearchAreaGoal extends Goal {

    public final Assimilated assimilated;
    public final double speed;
    public  int tryTicks;

    public AssimilatedSearchAreaGoal(Assimilated assimilated , double speed) {
        this.assimilated = assimilated;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    protected void moveMobToBlock() {
        this.assimilated.getNavigation().moveTo((double)((float)this.assimilated.getSearchPos().getX()) + 0.5D, (double)(this.assimilated.getSearchPos().getY() + 1), (double)((float)this.assimilated.getSearchPos().getZ()) + 0.5D, 1);
    }
    @Override
    public boolean canUse() {
        return this.assimilated.getSearchPos() != null && assimilated.getTarget() == null;
    }

    @Override
    public void start() {
        this.moveMobToBlock();
        this.tryTicks = 0;
        super.start();
    }

    @Override
    public boolean canContinueToUse() {
        return assimilated.getTarget() == null;
    }


    @Override
    public void tick() {
        super.tick();
        ++this.tryTicks;
        if (this.assimilated.getSearchPos() != null && shouldRecalculatePath()){
            this.assimilated.getNavigation().moveTo(this.assimilated.getSearchPos().getX(),this.assimilated.getSearchPos().getY(),this.assimilated.getSearchPos().getZ(),1);
        }
        if (this.assimilated.getSearchPos() != null && this.assimilated.getSearchPos().closerToCenterThan(this.assimilated.position(),9.0)){
            assimilated.setSearchPos(null);
        }
    }

    public boolean shouldRecalculatePath() {
        return this.tryTicks % 40 == 0;
    }


    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}
