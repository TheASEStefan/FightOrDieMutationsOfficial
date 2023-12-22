package net.teamabyssal.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.custom.MalruptorEntity;
import net.teamabyssal.entity.custom.MargrouperEntity;
import net.teamabyssal.entity.custom.ShillerEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class AttributeEvent {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(EntityRegistry.SHILLER.get(), ShillerEntity.createAttributes().build());
        event.put(EntityRegistry.MALRUPTOR.get(), MalruptorEntity.createAttributes().build());
        event.put(EntityRegistry.MARGROUPER.get(), MargrouperEntity.createAttributes().build());

    }

}
