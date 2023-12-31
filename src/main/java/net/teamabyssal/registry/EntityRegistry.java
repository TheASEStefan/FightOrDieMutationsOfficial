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
                            .sized(0.8f, 0.6f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "shiller").toString()));
    public static final RegistryObject<EntityType<MalruptorEntity>> MALRUPTOR =
            ENTITY_TYPES.register("malruptor",
                    () -> EntityType.Builder.of(MalruptorEntity::new, PARASITE)
                            .sized(1.1f, 1.1f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "malruptor").toString()));
    public static final RegistryObject<EntityType<MargrouperEntity>> MARGROUPER =
            ENTITY_TYPES.register("margrouper",
                    () -> EntityType.Builder.of(MargrouperEntity::new, PARASITE)
                            .sized(1.4f, 1.4f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "margrouper").toString()));
    public static final RegistryObject<EntityType<AssimilatedHumanEntity>> ASSIMILATED_HUMAN =
            ENTITY_TYPES.register("assimilated_human",
                    () -> EntityType.Builder.of(AssimilatedHumanEntity::new, PARASITE)
                            .sized(0.8f, 2.2f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_human").toString()));
    public static final RegistryObject<EntityType<AssimilatedHumanHeadEntity>> ASSIMILATED_HUMAN_HEAD =
            ENTITY_TYPES.register("assimilated_human_head",
                    () -> EntityType.Builder.of(AssimilatedHumanHeadEntity::new, PARASITE)
                            .sized(0.7f, 1.2f)
                            .build(new ResourceLocation(FightOrDieMutations.MODID, "assimilated_human_head").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

}
