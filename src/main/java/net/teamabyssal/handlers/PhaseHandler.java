package net.teamabyssal.handlers;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.teamabyssal.registry.SoundRegistry;

public class PhaseHandler {

    public static int phase = 0;
    private static int cnt0 = 0;
    private static int cnt1 = 0;
    private static int cnt2 = 0;
    private static int cnt3 = 0;
    private static int cnt4 = 0;
    private static int cnt5 = 0;
    private static final int PHASE0 = 0;
    private static final int PHASE1 = 1000;
    private static final int PHASE2 = 5000;
    private static final int PHASE3 = 10000;
    private static final int PHASE4 = 25000;
    private static final int PHASE5 = 50000;

    public static int getPhase() {
        if (ScoreHandler.getScore() >= PHASE0 && ScoreHandler.getScore() < PHASE1) {
            PhaseHandler.phase = 0;
        }
        else if (ScoreHandler.getScore() >= PHASE1 && ScoreHandler.getScore() < PHASE2) {
            PhaseHandler.phase = 1;
        }
        else if (ScoreHandler.getScore() >= PHASE2 && ScoreHandler.getScore() < PHASE3) {
            PhaseHandler.phase = 2;
        }
        else if (ScoreHandler.getScore() >= PHASE3 && ScoreHandler.getScore() < PHASE4) {
            PhaseHandler.phase = 3;
        }
        else if (ScoreHandler.getScore() >= PHASE4 && ScoreHandler.getScore() < PHASE5) {
            PhaseHandler.phase = 4;
        }
        else if (ScoreHandler.getScore() >= PHASE5) {
            PhaseHandler.phase = 5;
        }
        return PhaseHandler.phase;
    }

    // 0 score = phase 0
    // 1000 score = phase 1
    // 5000 score = phase 2
    // 10000 score = phase 3
    // 25000 score = phase 4
    // 50000 score = phase 5

    public static void setPhase(int phase) {
        PhaseHandler.phase = phase;
    }
    public static void checkAndNotifyPhaseChange(Player player) {
        int currentPhase = PhaseHandler.getPhase();

            if (currentPhase == 0) {
                cnt0++;
                if (cnt0 == 1) {
                    player.sendSystemMessage(Component.literal("Zero"));
                    player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE0.get(), SoundSource.HOSTILE, 1.2F, 1.0F);
                }
            }
            else if (currentPhase == 1) {
                cnt1++;
                if (cnt1 == 1) {
                    player.sendSystemMessage(Component.literal("First"));
                    player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE1.get(), SoundSource.HOSTILE, 1.6F, 1.0F);
                }
            }
            else if (currentPhase == 2) {
                cnt2++;
                if (cnt2 == 1) {
                    player.sendSystemMessage(Component.literal("Second"));
                    player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE2.get(), SoundSource.HOSTILE, 1.6F, 1.0F);
                }
            }
            else if (currentPhase == 3) {
                cnt3++;
                if (cnt3 == 1) {
                    player.sendSystemMessage(Component.literal("Third"));
                    player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE3.get(), SoundSource.HOSTILE, 1.2F, 1.0F);
                }
            }
            else if (currentPhase == 4) {
                cnt4++;
                if (cnt4 == 1) {
                    player.sendSystemMessage(Component.literal("Fourth"));
                    player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE4.get(), SoundSource.HOSTILE, 1.4F, 1.0F);
                }
            }
            else if (currentPhase == 5) {
                cnt5++;
                if (cnt5 == 1) {
                    player.sendSystemMessage(Component.literal("Fifth"));
                    player.level().playSound((Player) null, player.blockPosition(), SoundRegistry.PHASE5.get(), SoundSource.HOSTILE, 1.4F, 1.0F);
                }
            }

    }

}
