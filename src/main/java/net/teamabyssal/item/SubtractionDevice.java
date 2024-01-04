package net.teamabyssal.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.teamabyssal.item.categories.Device;
import net.teamabyssal.item.categories.Utility;
import net.teamabyssal.registry.WorldDataRegistry;

public class SubtractionDevice extends Item implements Device, Utility {

    public SubtractionDevice(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry((ServerLevel) pLevel);
        int currentScore = worldDataRegistry.getScore();
        if (!pLevel.isClientSide && pPlayer != null && pUsedHand == InteractionHand.MAIN_HAND) {
            worldDataRegistry.setScore(currentScore - 1000);
            pPlayer.sendSystemMessage(Component.literal("-1000"));
            pPlayer.getCooldowns().addCooldown(this, 20);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
