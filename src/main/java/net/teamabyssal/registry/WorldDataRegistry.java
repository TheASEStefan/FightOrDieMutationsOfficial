package net.teamabyssal.registry;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.SavedData;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import org.jetbrains.annotations.NotNull;

public class WorldDataRegistry extends SavedData {

    private int score;
    private int phase;
    private int join;
    private boolean cnt0 = false;
    private boolean cnt1 = false;
    private boolean cnt2 = false;
    private boolean cnt3 = false;
    private boolean cnt4 = false;
    private boolean cnt5 = false;

    public boolean isCnt0() {
        setDirty();
        return cnt0;
    }

    public void setCnt0(boolean cnt0) {
        this.cnt0 = cnt0;
        setDirty();
    }
    public boolean isCnt1() {
        setDirty();
        return cnt1;
    }

    public void setCnt1(boolean cnt1) {
        this.cnt1 = cnt1;
        setDirty();
    }
    public boolean isCnt2() {
        setDirty();
        return cnt2;
    }

    public void setCnt2(boolean cnt2) {
        this.cnt2 = cnt2;
        setDirty();
    }
    public boolean isCnt3() {
        setDirty();
        return cnt3;
    }

    public void setCnt3(boolean cnt3) {
        this.cnt3 = cnt3;
        setDirty();
    }
    public boolean isCnt4() {
        setDirty();
        return cnt4;
    }

    public void setCnt4(boolean cnt4) {
        this.cnt4 = cnt4;
        setDirty();
    }
    public boolean isCnt5() {
        setDirty();
        return cnt5;
    }

    public void setCnt5(boolean cnt5) {
        this.cnt5 = cnt5;
        setDirty();
    }


    public int getJoin() {
        setDirty();
        return join;
    }

    public void setJoin(int join) {
        this.join = join;
        setDirty();
    }

    public int getScore() {
        setDirty();
        return score;
    }

    public void setScore(int score) {
        if (score < 0) {
            this.score = 0;
        }
        else {
            this.score = score;
        }
        setDirty();
    }

    public int getPhase() {
        setDirty();
        if (getScore() >= 0 && getScore() < 1000) {
            phase = 0;
        }
        else if (getScore() >= 1000 && getScore() < 5000) {
            phase = 1;
        }
        else if (getScore() >= 5000 && getScore() < 25000) {
            phase = 2;
        }
        else if (getScore() >= 25000 && getScore() < 100000) {
            phase = 3;
        }
        else if (getScore() >= 100000 && getScore() < 250000) {
            phase = 4;
        }
        else if (getScore() >= 250000) {
            phase = 5;
        }
        return phase;
    }

    public void setPhase(int phase) {
        if (phase == 0) {
            setScore(0);
        }
        else if (phase == 1) {
            setScore(1000);
        }
        else if (phase == 2) {
            setScore(5000);
        }
        else if (phase == 3) {
            setScore(25000);
        }
        else if (phase == 4) {
            setScore(100000);
        }
        else if (phase == 5) {
            setScore(250000);
        }
        else {
            setScore(0);
        }
        this.phase = phase;
        setDirty();
    }


    public void phaseHandlerEvent(ServerLevel world, ServerPlayer player) {
        if (getPhase() == 0 && !cnt0) {
            cnt0 = true;
            player.sendSystemMessage(Component.literal("Zero"));
            player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE0.get(), SoundSource.HOSTILE, 1.2F, 1.0F);

        }
        else if (getPhase() == 1 && !cnt1) {
            cnt1 = true;
            player.sendSystemMessage(Component.literal("First"));
            player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE1.get(), SoundSource.HOSTILE, 1.6F, 1.0F);

        }
        else if (getPhase() == 2 && !cnt2) {
            cnt2 = true;
            player.sendSystemMessage(Component.literal("Second"));
            player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE2.get(), SoundSource.HOSTILE, 1.6F, 1.0F);

        }
        else if (getPhase() == 3 && !cnt3) {
            cnt3 = true;
            player.sendSystemMessage(Component.literal("Third"));
            player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE3.get(), SoundSource.HOSTILE, 1.2F, 1.0F);

        }
        else if (getPhase() == 4 && !cnt4) {
            cnt4 = true;
            player.sendSystemMessage(Component.literal("Fourth"));
            player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE4.get(), SoundSource.HOSTILE, 1.4F, 1.0F);

        }
        else if (getPhase() == 5 && !cnt5) {
            cnt5 = true;
            player.sendSystemMessage(Component.literal("Fifth"));
            player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE5.get(), SoundSource.HOSTILE, 1.4F, 1.0F);

        }
        this.setDirty();
    }


    @Override
    public @NotNull CompoundTag save(CompoundTag nbt) {
        nbt.putInt("score", this.score);
        nbt.putInt("phase", this.phase);
        nbt.putInt("join", this.join);
        nbt.putBoolean("cnt0", this.cnt0);
        nbt.putBoolean("cnt1", this.cnt1);
        nbt.putBoolean("cnt2", this.cnt2);
        nbt.putBoolean("cnt3", this.cnt3);
        nbt.putBoolean("cnt4", this.cnt4);
        nbt.putBoolean("cnt5", this.cnt5);
        return nbt;
    }

    public static WorldDataRegistry fromNBT(CompoundTag nbt){
        WorldDataRegistry worldDataRegistry = new WorldDataRegistry();
        worldDataRegistry.score = nbt.getInt("score");
        worldDataRegistry.phase = nbt.getInt("phase");
        worldDataRegistry.join = nbt.getInt("join");
        worldDataRegistry.cnt0 = nbt.getBoolean("cnt0");
        worldDataRegistry.cnt1 = nbt.getBoolean("cnt1");
        worldDataRegistry.cnt2 = nbt.getBoolean("cnt2");
        worldDataRegistry.cnt3 = nbt.getBoolean("cnt3");
        worldDataRegistry.cnt4 = nbt.getBoolean("cnt4");
        worldDataRegistry.cnt5 = nbt.getBoolean("cnt5");
        return worldDataRegistry;
    }

    public static WorldDataRegistry getWorldDataRegistry(ServerLevel world) {
        WorldDataRegistry WorldDataRegistry = world.getDataStorage().computeIfAbsent(net.teamabyssal.registry.WorldDataRegistry::fromNBT, WorldDataRegistry::new, FightOrDieMutations.MODID);
        WorldDataRegistry.setDirty();
        return WorldDataRegistry;
    }




}
