package net.teamabyssal.fdmcommands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.teamabyssal.registry.WorldDataRegistry;

public class PointCommand {


    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        LiteralArgumentBuilder<CommandSourceStack> pointCommand = Commands.literal("set_points")
                .requires(player -> player.hasPermission(3));
        pointCommand.then((Commands.argument("set_points", IntegerArgumentType.integer()).executes((ctx) -> {
            return setPoints(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "set_points"));
        })));
        dispatcher.register(pointCommand);
    }

    private static int setPoints(CommandSourceStack commandStack, int points) {
        if (points < 0) {
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(commandStack.getLevel());
            worldDataRegistry.setScore(0);
        }
        else {
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(commandStack.getLevel());
            worldDataRegistry.setScore(points);
        }

        return 0;
    }
}
