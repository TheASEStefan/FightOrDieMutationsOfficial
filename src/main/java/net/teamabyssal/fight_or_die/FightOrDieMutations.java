package net.teamabyssal.fight_or_die;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.teamabyssal.biome.BiomeModification;
import net.teamabyssal.config.FightOrDieMutationsConfig;
import net.teamabyssal.entity.client.*;
import net.teamabyssal.event.SpawnPlacementEvent;
import net.teamabyssal.registry.*;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;


@Mod(FightOrDieMutations.MODID)
public class FightOrDieMutations {
    public static final String MODID = "fight_or_die";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FightOrDieMutations() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FightOrDieMutationsConfig.DATAGEN_SPEC ,"fight_or_die_data.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FightOrDieMutationsConfig.SERVER_SPEC ,"fight_or_die_config.toml");
        FightOrDieMutationsConfig.loadConfig(FightOrDieMutationsConfig.SERVER_SPEC,
                FMLPaths.CONFIGDIR.get().resolve("fight_or_die_config.toml").toString());

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext context = ModLoadingContext.get();
        MinecraftForge.EVENT_BUS.register(this);


        GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);

        modEventBus.addListener(SpawnPlacementEvent::SpawnPlacement);

        modEventBus.addListener(this::commonSetup);

        EntityRegistry.register(modEventBus);

        ItemRegistry.register(modEventBus);

        BlockRegistry.register(modEventBus);

        SoundRegistry.register(modEventBus);

        EffectRegistry.register(modEventBus);

        ParticleRegistry.register(modEventBus);

        CreativeTabRegistry.register(modEventBus);

        final DeferredRegister<Codec<? extends BiomeModifier>> biomeModifiers =
                DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, FightOrDieMutations.MODID);
        biomeModifiers.register(modEventBus);
        biomeModifiers.register("fight_or_die_spawns", BiomeModification::makeCodec);


    }


    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(EntityRegistry.SHILLER.get(), ShillerRenderer::new);
            EntityRenderers.register(EntityRegistry.MALRUPTOR.get(), MalruptorRenderer::new);
            EntityRenderers.register(EntityRegistry.ASSIMILATED_HUMAN.get(), AssimilatedHumanRenderer::new);
            EntityRenderers.register(EntityRegistry.ASSIMILATED_VILLAGER.get(), AssimilatedVillagerRenderer::new);
            EntityRenderers.register(EntityRegistry.ASSIMILATED_HUMAN_HEAD.get(), AssimilatedHumanHeadRenderer::new);
            EntityRenderers.register(EntityRegistry.ASSIMILATED_VILLAGER_HEAD.get(), AssimilatedVillagerHeadRenderer::new);
            EntityRenderers.register(EntityRegistry.ASSIMILATED_COW.get(), AssimilatedCowRenderer::new);
            EntityRenderers.register(EntityRegistry.ASSIMILATED_SHEEP.get(), AssimilatedSheepRenderer::new);
            EntityRenderers.register(EntityRegistry.ASSIMILATED_PIG.get(), AssimilatedPigRenderer::new);
            EntityRenderers.register(EntityRegistry.ASSIMILATED_CREEPER.get(), AssimilatedCreeperRenderer::new);


        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {


    }
}