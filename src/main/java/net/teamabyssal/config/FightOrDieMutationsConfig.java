package net.teamabyssal.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.List;

public class FightOrDieMutationsConfig {
    public static final Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final DataGen DATAGEN;
    public static final ForgeConfigSpec DATAGEN_SPEC;


    static {

        Pair<Server, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER = commonSpecPair.getLeft();
        SERVER_SPEC = commonSpecPair.getRight();

        Pair<DataGen , ForgeConfigSpec> commonPair = new ForgeConfigSpec.Builder().configure(DataGen::new);
        DATAGEN = commonPair.getLeft();
        DATAGEN_SPEC = commonPair.getRight();






    }
    public static class Server {


        public final ForgeConfigSpec.ConfigValue<Integer> mob_cap;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> dimension_parameters;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawns;
        public final ForgeConfigSpec.ConfigValue<Boolean> infector_spawn;
        public final ForgeConfigSpec.ConfigValue<Integer> infector_days;
        public final ForgeConfigSpec.ConfigValue<Boolean> shiller_spawn;
        public final ForgeConfigSpec.ConfigValue<Integer> shiller_days;
        public final ForgeConfigSpec.ConfigValue<Double> shiller_health;
        public final ForgeConfigSpec.ConfigValue<Double> shiller_damage;
        public final ForgeConfigSpec.ConfigValue<Double> malruptor_health;
        public final ForgeConfigSpec.ConfigValue<Double> malruptor_damage;
        public final ForgeConfigSpec.ConfigValue<Integer> malruptor_attack_days;
        public final ForgeConfigSpec.ConfigValue<Double> margrouper_health;
        public final ForgeConfigSpec.ConfigValue<Double> margrouper_damage;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("Shiller");
            this.shiller_health = builder.comment("Default 6").defineInRange("Sets Shiller's Max health", 6, 2, Double.MAX_VALUE);
            this.shiller_damage = builder.comment("Default 0").defineInRange("Sets Shiller's Damage", 0, 0, 0.0);
            builder.pop();
            builder.push("Malruptor");
            this.malruptor_health = builder.comment("Default 12").defineInRange("Sets Malruptor's Max health", 12, 5, Double.MAX_VALUE);
            this.malruptor_damage = builder.comment("Default 5").defineInRange("Sets Malruptor's Damage", 5, 2, Double.MAX_VALUE);
            this.malruptor_attack_days = builder.comment("Default 3").defineInRange("Days before malruptors start being aggressive", 3, 1, 10);
            builder.pop();
            builder.push("Margrouper");
            this.margrouper_health = builder.comment("Default 18").defineInRange("Sets Margrouper's Max health", 18, 8, Double.MAX_VALUE);
            this.margrouper_damage = builder.comment("Default 7").defineInRange("Sets Margrouper's Damage", 7, 3, Double.MAX_VALUE);
            builder.pop();

            builder.push("Spawns");
            // Shiller
            this.shiller_spawn = builder.comment("Default false").define("Should shillers spawn after a few days?",false);
            this.shiller_days = builder.comment("Default 2").define("Days before shillers start spawning",2);
            // Infector ( Malruptor, Margrouper )
            this.infector_spawn = builder.comment("Default true").define("Should infectors spawn after a few days?",true);
            this.infector_days = builder.comment("Default 5").define("Days before infectors start spawning",5);

            this.mob_cap = builder.comment("Default 60").define("MobCap",60);
            this.dimension_parameters = builder.comment("Default minecraft:is_overworld").defineList("Dictates in what biome the parasites spawn",
                    Lists.newArrayList("minecraft:is_overworld") , o -> o instanceof String);
            this.spawns = builder.defineList("mob|weight|minimum|maximum",
                    Lists.newArrayList("fight_or_die:shiller|25|1|2", "fight_or_die:malruptor|20|1|2", "fight_or_die:margrouper|12|1|1") , o -> o instanceof String);
            builder.pop();


        }
    }
    public static class DataGen {

        public DataGen(ForgeConfigSpec.Builder builder) {

        }

    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
                .writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
}