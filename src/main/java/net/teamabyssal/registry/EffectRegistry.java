package net.teamabyssal.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.teamabyssal.effects.HiveSickness;
import net.teamabyssal.fight_or_die.FightOrDieMutations;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, FightOrDieMutations.MODID);
    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }

    public static final RegistryObject<MobEffect> HIVE_SICKNESS = MOB_EFFECTS.register("hive_sickness",
            HiveSickness::new);
}
