package net.teamabyssal.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.teamabyssal.fight_or_die.FightOrDieMutations;


public class CreativeTabRegistry {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FightOrDieMutations.MODID);



    public static final RegistryObject<CreativeModeTab> ITEM = TABS.register("items", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + FightOrDieMutations.MODID + ".item")).icon(() -> new ItemStack(ItemRegistry.SHILLER_SPAWN_EGG.get())).displayItems((enabledFeatures, entries) -> {
        entries.accept(ItemRegistry.SHILLER_SPAWN_EGG.get());
        entries.accept(ItemRegistry.MALRUPTOR_SPAWN_EGG.get());
        entries.accept(ItemRegistry.MARGROUPER_SPAWN_EGG.get());

    }).build());

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }

}
