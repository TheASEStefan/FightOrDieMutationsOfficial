package net.teamabyssal.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.custom.*;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeEvent {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(EntityRegistry.SHILLER.get(), ShillerEntity.createAttributes().build());
        event.put(EntityRegistry.MALRUPTOR.get(), MalruptorEntity.createAttributes().build());
        event.put(EntityRegistry.ASSIMILATED_HUMAN.get(), AssimilatedHumanEntity.createAttributes().build());
        event.put(EntityRegistry.ASSIMILATED_VILLAGER.get(), AssimilatedVillagerEntity.createAttributes().build());
        event.put(EntityRegistry.ASSIMILATED_COW.get(), AssimilatedCowEntity.createAttributes().build());
        event.put(EntityRegistry.ASSIMILATED_SHEEP.get(), AssimilatedSheepEntity.createAttributes().build());
        event.put(EntityRegistry.ASSIMILATED_PIG.get(), AssimilatedPigEntity.createAttributes().build());
        event.put(EntityRegistry.ASSIMILATED_HUMAN_HEAD.get(), AssimilatedHumanHeadEntity.createAttributes().build());
        event.put(EntityRegistry.ASSIMILATED_VILLAGER_HEAD.get(), AssimilatedVillagerHeadEntity.createAttributes().build());
        event.put(EntityRegistry.ASSIMILATED_CREEPER.get(), AssimilatedCreeperEntity.createAttributes().build());

    }

}
