package net.teamabyssal.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.custom.*;
import net.teamabyssal.fight_or_die.FightOrDieMutations;

import java.util.ArrayList;
import java.util.List;

public class EntityRegistry {


    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, FightOrDieMutations.MODID);


    public static final MobCategory PARASITE = MobCategory.create("parasite","parasite", FightOrDieMutationsConfig.SERVER.mob_cap.get(),false,false,128);

    public  static  final List<Entity> PARASITES = new ArrayList<>();

    public static final RegistryObject<EntityType<ShillerEntity>> SHILLER =
            ENTITY_TYPES.register("shiller",
                    () -> EntityType.Builder.of(ShillerEntity::new, PARASITE)
                            .sized(0.6f, 0.6f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "shiller").toString()));
    public static final RegistryObject<EntityType<MalruptorEntity>> MALRUPTOR =
            ENTITY_TYPES.register("springer",
                    () -> EntityType.Builder.of(MalruptorEntity::new, PARASITE)
                            .sized(0.8f, 1.1f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "springer").toString()));
    public static final RegistryObject<EntityType<AssimilatedHumanEntity>> ASSIMILATED_HUMAN =
            ENTITY_TYPES.register("assimilated_human",
                    () -> EntityType.Builder.of(AssimilatedHumanEntity::new, PARASITE)
                            .sized(0.8f, 1.9f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_human").toString()));
    public static final RegistryObject<EntityType<AssimilatedVillagerEntity>> ASSIMILATED_VILLAGER =
            ENTITY_TYPES.register("assimilated_villager",
                    () -> EntityType.Builder.of(AssimilatedVillagerEntity::new, PARASITE)
                            .sized(0.8f, 1.9f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_villager").toString()));
    public static final RegistryObject<EntityType<AssimilatedHumanHeadEntity>> ASSIMILATED_HUMAN_HEAD =
            ENTITY_TYPES.register("assimilated_human_head",
                    () -> EntityType.Builder.of(AssimilatedHumanHeadEntity::new, PARASITE)
                            .sized(0.7f, 0.8f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_human_head").toString()));
    public static final RegistryObject<EntityType<AssimilatedVillagerHeadEntity>> ASSIMILATED_VILLAGER_HEAD =
            ENTITY_TYPES.register("assimilated_villager_head",
                    () -> EntityType.Builder.of(AssimilatedVillagerHeadEntity::new, PARASITE)
                            .sized(0.7f, 0.8f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_villager_head").toString()));
    public static final RegistryObject<EntityType<AssimilatedCowEntity>> ASSIMILATED_COW =
            ENTITY_TYPES.register("assimilated_cow",
                    () -> EntityType.Builder.of(AssimilatedCowEntity::new, PARASITE)
                            .sized(1.0f, 1.5f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_cow").toString()));
    public static final RegistryObject<EntityType<AssimilatedSheepEntity>> ASSIMILATED_SHEEP =
            ENTITY_TYPES.register("assimilated_sheep",
                    () -> EntityType.Builder.of(AssimilatedSheepEntity::new, PARASITE)
                            .sized(0.9f, 1.4f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_sheep").toString()));
    public static final RegistryObject<EntityType<AssimilatedCreeperEntity>> ASSIMILATED_CREEPER =
            ENTITY_TYPES.register("assimilated_creeper",
                    () -> EntityType.Builder.of(AssimilatedCreeperEntity::new, PARASITE)
                            .sized(0.8f, 2.0f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_creeper").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
