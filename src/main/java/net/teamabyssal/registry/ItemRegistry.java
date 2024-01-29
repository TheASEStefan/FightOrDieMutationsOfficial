package net.teamabyssal.registry;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.item.AdditionDevice;
import net.teamabyssal.item.Parascore;
import net.teamabyssal.item.SubtractionDevice;
import net.teamabyssal.item.categories.BaseItem;

import java.util.ArrayList;
import java.util.List;


public class ItemRegistry {
    public  static  final List<Item> DROP_LOOT_ITEMS = new ArrayList<>();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FightOrDieMutations.MODID);
    public static final RegistryObject<Item> SHILLER_SPAWN_EGG = ITEMS.register("shiller_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.SHILLER, 0x000000, 0x110202, new Item.Properties()));
    public static final RegistryObject<Item> MALRUPTOR_SPAWN_EGG = ITEMS.register("springer_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.MALRUPTOR, 0x000000, 0x110202, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_HUMAN_SPAWN_EGG = ITEMS.register("assimilated_human_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_HUMAN, 0x28283e, 0x345350, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_VILLAGER_SPAWN_EGG = ITEMS.register("assimilated_villager_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_VILLAGER, 0x281604, 0x6e3d0a, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_HUMAN_HEAD_SPAWN_EGG = ITEMS.register("assimilated_human_head_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_HUMAN_HEAD, 0x28283e, 0x345350, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_VILLAGER_HEAD_SPAWN_EGG = ITEMS.register("assimilated_villager_head_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_VILLAGER_HEAD, 0x281603, 0x6e5d0a, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_COW_SPAWN_EGG = ITEMS.register("assimilated_cow_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_COW, 0x281604, 0x3f2306, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_SHEEP_SPAWN_EGG = ITEMS.register("assimilated_sheep_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_SHEEP, 0xfacbcb, 0x560808, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_PIG_SPAWN_EGG = ITEMS.register("assimilated_pig_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_PIG, 0x280416, 0x9b1057, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_FOX_SPAWN_EGG = ITEMS.register("assimilated_fox_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_FOX, 0x3f2306, 0x9d570e, new Item.Properties()));
    public static final RegistryObject<Item> ASSIMILATED_CREEPER_SPAWN_EGG = ITEMS.register("assimilated_creeper_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityRegistry.ASSIMILATED_CREEPER, 0x042804, 0x10a510, new Item.Properties()));

    public static final RegistryObject<Item> ADDITION_DEVICE = ITEMS.register("addition_device",
            () -> new AdditionDevice(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> SUBTRACTION_DEVICE = ITEMS.register("subtraction_device",
            () -> new SubtractionDevice(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> PARASCORE = ITEMS.register("parascore",
            () -> new Parascore(new Item.Properties()));
    public static final RegistryObject<Item> INFECTOR_THORAX = ITEMS.register("infector_thorax",
            () -> new BaseItem(new Item.Properties()));
    public static final RegistryObject<Item> RIPPING_FLESH = ITEMS.register("ripping_flesh",
            () -> new BaseItem(new Item.Properties()));
    public static final RegistryObject<Item> ROTTEN_BRAIN = ITEMS.register("rotten_brain",
            () -> new BaseItem(new Item.Properties()));
    public static final RegistryObject<Item> SICKENED_HEART = ITEMS.register("sickened_heart",
            () -> new BaseItem(new Item.Properties()));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
