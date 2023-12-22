package net.teamabyssal.entity.ai;


import net.minecraft.world.entity.ai.goal.Goal;
import net.teamabyssal.entity.categories.Infector;

import java.util.EnumSet;

public class InfectorSearchAreaGoal extends Goal {
    public final Infector infector;
    public final double speed;
    public  int tryTicks;

    public InfectorSearchAreaGoal(Infector infector , double speed){
        this.infector = infector;
        this.speed = speed;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    protected void moveMobToBlock() {
        this.infector.getNavigation().moveTo((double)((float)this.infector.getSearchPos().getX()) + 0.5D, (double)(this.infector.getSearchPos().getY() + 1), (double)((float)this.infector.getSearchPos().getZ()) + 0.5D, 1);
    }
    @Override
    public boolean canUse() {
        return this.infector.getSearchPos() != null && infector.getTarget() == null;
    }

    @Override
    public void start() {
        this.moveMobToBlock();
        this.tryTicks = 0;
        super.start();
    }

    @Override
    public boolean canContinueToUse() {
        return infector.getTarget() == null;
    }


    @Override
    public void tick() {
        super.tick();
        ++this.tryTicks;
        if (this.infector.getSearchPos() != null && shouldRecalculatePath()){
            this.infector.getNavigation().moveTo(this.infector.getSearchPos().getX(),this.infector.getSearchPos().getY(),this.infector.getSearchPos().getZ(),1);
        }
        if (this.infector.getSearchPos() != null && this.infector.getSearchPos().closerToCenterThan(this.infector.position(),9.0)){
            infector.setSearchPos(null);
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
 