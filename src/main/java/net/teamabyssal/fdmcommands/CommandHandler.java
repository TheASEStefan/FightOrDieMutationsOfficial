package net.teamabyssal.fdmcommands;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.fight_or_die.FightOrDieMutations;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class CommandHandler {
    @SubscribeEvent
    public static void Command(RegisterCommandsEvent event) {
        PointCommand.register(event.getDispatcher());
        PhaseCommand.register(event.getDispatcher());
    }
}
