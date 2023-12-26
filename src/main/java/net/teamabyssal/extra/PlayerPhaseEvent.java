package net.teamabyssal.extra;

import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.handlers.PhaseHandler;
import net.teamabyssal.handlers.ScoreHandler;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class PlayerPhaseEvent {
    @SubscribeEvent
    public static void PhaseEvent(TickEvent.PlayerTickEvent event) {
        if (event.player instanceof ServerPlayer player) {
            Level world = player.level();
            PhaseHandler.checkAndNotifyPhaseChange(player);
            if (Math.random() <= 0.03F) {
                player.sendSystemMessage(Component.literal("Score: " + ScoreHandler.getScore() ));
            }
        }
    }

    @SubscribeEvent
    public static void PhaseEvent(TickEvent.ServerTickEvent event) {
        if(event.phase == TickEvent.Phase.END) {
            if (event.getServer().getWorldData().getDifficulty() == Difficulty.NORMAL) {
                if (Math.random() < 0.02F) {
                    if (PhaseHandler.getPhase() < 2) {
                        ScoreHandler.setScore(ScoreHandler.getScore() + 1);
                    }
                    else if (PhaseHandler.getPhase() > 2) {
                        ScoreHandler.setScore(ScoreHandler.getScore() - 1);
                    }
                }
                if (Math.random() < 0.01F) {
                    if (ScoreHandler.getScore() > 0) {
                        ScoreHandler.setScore(ScoreHandler.getScore() - 1);
                    }
                }
            }
            else if (event.getServer().getWorldData().getDifficulty() == Difficulty.HARD) {
                if (Math.random() < 0.02F) {
                    if (PhaseHandler.getPhase() < 2) {
                        ScoreHandler.setScore(ScoreHandler.getScore() + 1);
                    }
                    else if (PhaseHandler.getPhase() > 2) {
                        ScoreHandler.setScore(ScoreHandler.getScore() - 1);
                    }
                }
                if (Math.random() < 0.015F) {
                    if (PhaseHandler.getPhase() > 1) {
                        ScoreHandler.setScore(ScoreHandler.getScore() + 1);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void SleepEvent(PlayerSleepInBedEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && event.getEntity() != null) {
            if (PhaseHandler.getPhase() == 0) {
                ScoreHandler.setScore(ScoreHandler.getScore() + 5);
                player.sendSystemMessage(Component.literal("5 points have been added."));
            }
            else if (PhaseHandler.getPhase() == 1) {
                ScoreHandler.setScore(ScoreHandler.getScore() + 10);
                player.sendSystemMessage(Component.literal("10 points have been added."));
            }
            else if (PhaseHandler.getPhase() == 2) {
                ScoreHandler.setScore(ScoreHandler.getScore() + 20);
                player.sendSystemMessage(Component.literal("20 points have been added."));
            }
            else if (PhaseHandler.getPhase() == 3) {
                ScoreHandler.setScore(ScoreHandler.getScore() + 40);
                player.sendSystemMessage(Component.literal("40 points have been added."));
            }
            else if (PhaseHandler.getPhase() == 4) {
                ScoreHandler.setScore(ScoreHandler.getScore() + 80);
                player.sendSystemMessage(Component.literal("80 points have been added."));
            }
            else if (PhaseHandler.getPhase() == 5) {
                ScoreHandler.setScore(ScoreHandler.getScore() + 160);
                player.sendSystemMessage(Component.literal("160 points have been added."));
            }
        }
    }
}
