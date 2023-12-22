package net.teamabyssal.registry;


import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.teamabyssal.fight_or_die.FightOrDieMutations;


public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FightOrDieMutations.MODID);

    public static final RegistryObject<Item> SHILLER_SPAWN_EGG = ITEMS.register("shiller_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.SHILLER, 0x000000, 0x110202, new Item.Properties()));
    public static final RegistryObject<Item> MALRUPTOR_SPAWN_EGG = ITEMS.register("malruptor_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.MALRUPTOR, 0x000000, 0x110202, new Item.Properties()));
    public static final RegistryObject<Item> MARGROUPER_SPAWN_EGG = ITEMS.register("margrouper_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.MARGROUPER, 0x000000, 0x110202, new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
