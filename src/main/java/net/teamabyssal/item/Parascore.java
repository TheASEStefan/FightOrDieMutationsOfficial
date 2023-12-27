package net.teamabyssal.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ToolAction;
import net.teamabyssal.handlers.PhaseHandler;
import net.teamabyssal.handlers.ScoreHandler;
import net.teamabyssal.item.categories.Device;
import net.teamabyssal.item.categories.Utility;

public class Parascore extends Item implements Device, Utility {
    public Parascore(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean canPerformAction(ItemStack stack, ToolAction toolAction) {
        return ScoreHandler.getScore() >= 0;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return Math.max(2, 5) + (int) Math.floor(5.25);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && pPlayer != null && pUsedHand == InteractionHand.MAIN_HAND) {
            pPlayer.sendSystemMessage(Component.literal("Score: " + ScoreHandler.getScore()));
            pPlayer.getCooldowns().addCooldown(this, 60);
            this.setDamage(new ItemStack(this), this.getDamage(new ItemStack(this)) - 1);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
