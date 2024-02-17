package net.teamabyssal.item;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.teamabyssal.item.categories.BaseItem;
import net.teamabyssal.item.categories.Device;
import net.teamabyssal.item.categories.Utility;
import net.teamabyssal.registry.WorldDataRegistry;

public class Parascore extends BaseItem implements Device, Utility {

    public Parascore(Properties pProperties) {
        super(new Properties().stacksTo(1).durability(10));
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide && pUsedHand == InteractionHand.MAIN_HAND && pLevel instanceof ServerLevel world) {
            WorldDataRegistry worldDataRegistry = WorldDataRegistry.getWorldDataRegistry(world);
            pPlayer.displayClientMessage(Component.literal(" " + worldDataRegistry.getScore()), true);
            pPlayer.getCooldowns().addCooldown(this, 60);
        }
        ItemStack itemstack = pPlayer.getItemInHand(pUsedHand);

        return InteractionResultHolder.consume(itemstack);
    }
}
