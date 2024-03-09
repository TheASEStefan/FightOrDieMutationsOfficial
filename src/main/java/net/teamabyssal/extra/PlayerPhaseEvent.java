package net.teamabyssal.extra;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.WorldDataRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class PlayerPhaseEvent {

    @SubscribeEvent
    public static void PhaseEvent(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.player instanceof ServerPlayer player && event.player.level() instanceof ServerLevel world) {
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(world);
            if (!worldDataRegistry.isCnt0() || !worldDataRegistry.isCnt1() || !worldDataRegistry.isCnt2() || !worldDataRegistry.isCnt3() || !worldDataRegistry.isCnt4() || !worldDataRegistry.isCnt5()) {
                WorldEventPhase.serverWorldTick(world, player);
            }
            if (Math.random() < 0.02F) {
                player.sendSystemMessage(Component.literal("Score: " + worldDataRegistry.getScore()));
            }
        }
    }



    @SubscribeEvent
    public static void PointIncrementEvent(TickEvent.LevelTickEvent event) {
        if(event.phase == TickEvent.Phase.END && event.level instanceof ServerLevel world) {
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(world);
            int currentPhase = worldDataRegistry.getPhase();
            int currentScore = worldDataRegistry.getScore();
            if (event.level.getDifficulty() == Difficulty.NORMAL) {
                if (Math.random() < 0.02F) {
                    if (currentPhase < 2) {
                        worldDataRegistry.setScore(currentScore + 2);
                    }
                    else if (currentPhase > 2) {
                        worldDataRegistry.setScore(currentScore - 1);
                    }
                }
                if (Math.random() < 0.015F) {
                    if (currentScore > 0) {
                        worldDataRegistry.setScore(currentScore + 5);
                    }
                }
            }
            else if (event.level.getDifficulty() == Difficulty.HARD) {
                if (Math.random() < 0.02F) {
                    if (currentPhase < 2) {
                        worldDataRegistry.setScore(currentScore + 3);
                    }
                    else if (currentPhase > 2) {
                        worldDataRegistry.setScore(currentScore - 1);
                    }
                }
                if (Math.random() < 0.025F) {
                    if (currentScore > 0) {
                        worldDataRegistry.setScore(currentScore + 10);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void SleepEvent(PlayerSleepInBedEvent event) {
        if (event.getEntity() != null && event.getEntity() instanceof ServerPlayer player) {
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry((ServerLevel) player.level());
            int currentPhase = worldDataRegistry.getPhase();
            int currentScore = worldDataRegistry.getScore();
            if (currentPhase == 0) {
                worldDataRegistry.setScore(currentScore + 5);
                player.sendSystemMessage(Component.literal("5 points have been added."));
                worldDataRegistry.setDirty();
            }
            else if (currentPhase == 1) {
                worldDataRegistry.setScore(currentScore + 10);
                player.sendSystemMessage(Component.literal("10 points have been added."));
                worldDataRegistry.setDirty();
            }
            else if (currentPhase == 2) {
                worldDataRegistry.setScore(currentScore + 20);
                player.sendSystemMessage(Component.literal("20 points have been added."));
                worldDataRegistry.setDirty();
            }
            else if (currentPhase == 3) {
                worldDataRegistry.setScore(currentScore + 40);
                player.sendSystemMessage(Component.literal("40 points have been added."));
                worldDataRegistry.setDirty();
            }
            else if (currentPhase == 4) {
                worldDataRegistry.setScore(currentScore + 80);
                player.sendSystemMessage(Component.literal("80 points have been added."));
                worldDataRegistry.setDirty();
            }
            else if (currentPhase == 5) {
                worldDataRegistry.setScore(currentScore + 160);
                player.sendSystemMessage(Component.literal("160 points have been added."));
                worldDataRegistry.setDirty();

            }
        }
    }
}
