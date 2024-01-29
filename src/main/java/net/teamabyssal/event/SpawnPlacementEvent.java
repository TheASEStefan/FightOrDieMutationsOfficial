package net.teamabyssal.event;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.teamabyssal.entity.categories.AdvancedAssimilated;
import net.teamabyssal.entity.categories.Assimilated;
import net.teamabyssal.entity.categories.Infector;
import net.teamabyssal.entity.categories.Parasite;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import net.teamabyssal.registry.EntityRegistry;

@Mod.EventBusSubscriber(modid = FightOrDieMutations.MODID)
public class SpawnPlacementEvent {

    @SubscribeEvent
    public static void SpawnPlacement(SpawnPlacementRegisterEvent event){
        event.register(EntityRegistry.SHILLER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Parasite::checkMonsterParasiteRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityRegistry.MALRUPTOR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Infector::checkMonsterInfectorRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityRegistry.ASSIMILATED_HUMAN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Assimilated::checkMonsterAssimilatedRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityRegistry.ASSIMILATED_VILLAGER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Assimilated::checkMonsterAssimilatedRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityRegistry.ASSIMILATED_COW.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Assimilated::checkMonsterAssimilatedRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityRegistry.ASSIMILATED_SHEEP.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Assimilated::checkMonsterAssimilatedRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityRegistry.ASSIMILATED_PIG.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Assimilated::checkMonsterAssimilatedRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityRegistry.ASSIMILATED_FOX.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Assimilated::checkMonsterAssimilatedRules, SpawnPlacementRegisterEvent.Operation.AND);
        event.register(EntityRegistry.ASSIMILATED_CREEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, AdvancedAssimilated::checkMonsterAdvancedAssimilatedRules, SpawnPlacementRegisterEvent.Operation.AND);


    }
}
