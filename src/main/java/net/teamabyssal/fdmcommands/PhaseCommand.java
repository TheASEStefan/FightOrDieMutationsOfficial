package net.teamabyssal.fdmcommands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.teamabyssal.handlers.PhaseHandler;

public class PhaseCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        LiteralArgumentBuilder<CommandSourceStack> phaseCommand = Commands.literal("set_phase")
                .requires(player -> player.hasPermission(2));
        phaseCommand.then((Commands.argument("set_phase", IntegerArgumentType.integer()).executes((ctx) -> {
            return setPhase(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "set_phase"));
        })));
        dispatcher.register(phaseCommand);
    }

    private static int setPhase(CommandSourceStack commandStack, int phase) {
            if (phase > 5 || phase < 0) {
                PhaseHandler.setPhase(0);

            }
            else if (phase >= 0 && phase <= 5) {
                PhaseHandler.setPhase(phase);

            }

        return 0;
    }
}
