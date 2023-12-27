package net.teamabyssal.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.teamabyssal.handlers.ScoreHandler;
import net.teamabyssal.item.categories.Device;
import net.teamabyssal.item.categories.Utility;

public class SubtractionDevice extends Item implements Device, Utility {

    public SubtractionDevice(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && pPlayer != null && pUsedHand == InteractionHand.MAIN_HAND) {
            ScoreHandler.setScore(ScoreHandler.getScore() - 1000);
            pPlayer.sendSystemMessage(Component.literal("-1000"));
            pPlayer.getCooldowns().addCooldown(this, 20);
            // this.setDamage(new ItemStack(this), this.getDamage(new ItemStack(this)) - 1);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
