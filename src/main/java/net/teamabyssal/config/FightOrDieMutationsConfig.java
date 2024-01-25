package net.teamabyssal.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.List;

public class FightOrDieMutationsConfig {
    public static int globalVariable = 0;
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

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklist;
        public final ForgeConfigSpec.ConfigValue<Integer> phase1_points;
        public final ForgeConfigSpec.ConfigValue<Integer> phase2_points;
        public final ForgeConfigSpec.ConfigValue<Integer> phase3_points;
        public final ForgeConfigSpec.ConfigValue<Integer> phase4_points;
        public final ForgeConfigSpec.ConfigValue<Integer> phase5_points;
        public final ForgeConfigSpec.ConfigValue<Integer> mob_cap;

        public final ForgeConfigSpec.ConfigValue<List<? extends String>> hive_sickness;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> dimension_parameters;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> spawns;
        public final ForgeConfigSpec.ConfigValue<Double> shiller_health;
        public final ForgeConfigSpec.ConfigValue<Double> shiller_damage;
        public final ForgeConfigSpec.ConfigValue<Double> malruptor_health;
        public final ForgeConfigSpec.ConfigValue<Double> malruptor_damage;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_human_health;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_human_damage;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_villager_health;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_villager_damage;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_human_head_health;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_human_head_damage;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_villager_head_health;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_villager_head_damage;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_cow_health;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_cow_damage;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_sheep_health;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_sheep_damage;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_pig_health;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_pig_damage;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_creeper_health;
        public final ForgeConfigSpec.ConfigValue<Double> assimilated_creeper_damage;
        public final ForgeConfigSpec.ConfigValue<Boolean> creeper_near_mutation_explosion;
        public final ForgeConfigSpec.ConfigValue<Boolean> assimilated_human_assimilation;
        public final ForgeConfigSpec.ConfigValue<Boolean> assimilated_villager_assimilation;
        public final ForgeConfigSpec.ConfigValue<Boolean> assimilated_adventurer_assimilation;
        public final ForgeConfigSpec.ConfigValue<Boolean> assimilated_cow_assimilation;
        public final ForgeConfigSpec.ConfigValue<Boolean> assimilated_sheep_assimilation;
        public final ForgeConfigSpec.ConfigValue<Boolean> assimilated_pig_assimilation;
        public final ForgeConfigSpec.ConfigValue<Boolean> assimilated_creeper_assimilation;
        public final ForgeConfigSpec.ConfigValue<Boolean> springer_attacks_enderman;
        public final ForgeConfigSpec.ConfigValue<Boolean> springer_attacks_witch;

        public Server(ForgeConfigSpec.Builder builder) {
            builder.push("Phases");
            this.phase1_points = builder.comment("Default 1000").define("Sets points required to enter phase 1",1000);
            this.phase2_points = builder.comment("Default 5000").define("Sets points required to enter phase 2",5000);
            this.phase3_points = builder.comment("Default 25000").define("Sets points required to enter phase 3",25000);
            this.phase4_points = builder.comment("Default 100000").define("Sets points required to enter phase 4",100000);
            this.phase5_points = builder.comment("Default 250000").define("Sets points required to enter phase 5",250000);
            builder.pop();
            builder.push("Shiller");
            this.shiller_health = builder.comment("Default 6").defineInRange("Sets Shiller's Max health", 6, 2, Double.MAX_VALUE);
            this.shiller_damage = builder.comment("Default 0").defineInRange("Sets Shiller's Damage", 0, 0, 0.0);
            builder.pop();
            builder.push("Malruptor");
            this.malruptor_health = builder.comment("Default 12").defineInRange("Sets Malruptor's Max health", 12, 5, Double.MAX_VALUE);
            this.malruptor_damage = builder.comment("Default 5").defineInRange("Sets Malruptor's Damage", 5, 2, Double.MAX_VALUE);
            builder.pop();
            builder.push("Assimilated Human");
            this.assimilated_human_health = builder.comment("Default 20").defineInRange("Sets Assimilated Human's Max health", 20, 10, Double.MAX_VALUE);
            this.assimilated_human_damage = builder.comment("Default 8").defineInRange("Sets Assimilated Human's Damage", 8, 4, Double.MAX_VALUE);
            builder.pop();
            builder.push("Assimilated Villager");
            this.assimilated_villager_health = builder.comment("Default 22").defineInRange("Sets Assimilated Villager's Max health", 22, 12, Double.MAX_VALUE);
            this.assimilated_villager_damage = builder.comment("Default 9").defineInRange("Sets Assimilated Villager's Damage", 9, 4, Double.MAX_VALUE);
            builder.pop();
            builder.push("Assimilated Human Head");
            this.assimilated_human_head_health = builder.comment("Default 7").defineInRange("Sets Assimilated Human Head's Max health", 7, 4, Double.MAX_VALUE);
            this.assimilated_human_head_damage = builder.comment("Default 3").defineInRange("Sets Assimilated Human Head's Damage", 3, 1, Double.MAX_VALUE);
            builder.pop();
            builder.push("Assimilated Villager Head");
            this.assimilated_villager_head_health = builder.comment("Default 8").defineInRange("Sets Assimilated Villager Head's Max health", 8, 4, Double.MAX_VALUE);
            this.assimilated_villager_head_damage = builder.comment("Default 3").defineInRange("Sets Assimilated Villager Head's Damage", 3, 1, Double.MAX_VALUE);
            builder.pop();
            builder.push("Assimilated Cow");
            this.assimilated_cow_health = builder.comment("Default 16").defineInRange("Sets Assimilated Cow's Max health", 16, 7, Double.MAX_VALUE);
            this.assimilated_cow_damage = builder.comment("Default 8").defineInRange("Sets Assimilated Cow's Damage", 8, 3, Double.MAX_VALUE);
            builder.pop();
            builder.push("Assimilated Sheep");
            this.assimilated_sheep_health = builder.comment("Default 14").defineInRange("Sets Assimilated Sheep's Max health", 14, 4, Double.MAX_VALUE);
            this.assimilated_sheep_damage = builder.comment("Default 7").defineInRange("Sets Assimilated Sheep's Damage", 7, 3, Double.MAX_VALUE);
            builder.pop();
            builder.push("Assimilated Pig");
            this.assimilated_pig_health = builder.comment("Default 12").defineInRange("Sets Assimilated Pig's Max health", 12, 3, Double.MAX_VALUE);
            this.assimilated_pig_damage = builder.comment("Default 7").defineInRange("Sets Assimilated Pig's Damage", 7, 2, Double.MAX_VALUE);
            builder.pop();
            builder.push("Assimilated Creeper");
            this.assimilated_creeper_health = builder.comment("Default 12").defineInRange("Sets Assimilated Creeper's Max health", 12, 5, Double.MAX_VALUE);
            this.assimilated_creeper_damage = builder.comment("Default 3").defineInRange("Sets Assimilated Creeper's Damage", 3, 1, Double.MAX_VALUE);
            this.creeper_near_mutation_explosion = builder.comment("Default true").define("Should assimilated creepers not explode if close to a sim creature or higher?",true);
            builder.pop();

            builder.push("Spawns");
            this.mob_cap = builder.comment("Default 60").define("MobCap",60);
            this.dimension_parameters = builder.comment("Default minecraft:is_overworld").defineList("Dictates in what biome the parasites spawn",
                    Lists.newArrayList("minecraft:is_overworld") , o -> o instanceof String);
            this.spawns = builder.defineList("mob|weight|minimum|maximum",
                    Lists.newArrayList("fight_or_die:shiller|45|1|3", "fight_or_die:springer|30|1|2", "fight_or_die:assimilated_human|28|1|2", "fight_or_die:assimilated_villager|25|1|2", "fight_or_die:assimilated_cow|25|1|2", "fight_or_die:assimilated_sheep|25|1|2", "fight_or_die:assimilated_pig|25|1|2", "fight_or_die:assimilated_creeper|15|1|1") , o -> o instanceof String);
            builder.pop();

            builder.push("Targeting Tasks");

            this.blacklist = builder.defineList("Mobs Not Targeted",
                    Lists.newArrayList(
                            "minecraft:squid","minecraft:bat","minecraft:armor_stand", "minecraft:creeper", "minecraft:ghast") , o -> o instanceof String);

            builder.pop();


            builder.push("Effects");
            this.hive_sickness = builder.defineList("Mobs that are immune to the hive sickness infection",
                    Lists.newArrayList(
                            "minecraft:ghast"
                            , "minecraft:magma_cube"
                            , "minecraft:phantom"
                            , "minecraft:snow_golem"
                            , "minecraft:stray"
                            , "minecraft:skeleton" ) , o -> o instanceof String);
            builder.pop();
            builder.push("Assimilations");
            this.assimilated_human_assimilation = builder.comment("Default true").define("Should zombies convert into their assimilated counterpart?",true);
            this.assimilated_cow_assimilation = builder.comment("Default true").define("Should cows convert into their assimilated counterpart?",true);
            this.assimilated_sheep_assimilation = builder.comment("Default true").define("Should sheeps convert into their assimilated counterpart?",true);
            this.assimilated_pig_assimilation = builder.comment("Default true").define("Should pigs convert into their assimilated counterpart?",true);
            this.assimilated_villager_assimilation = builder.comment("Default true").define("Should villagers convert into their assimilated counterpart?",true);
            this.assimilated_adventurer_assimilation = builder.comment("Default true").define("Should players convert into their assimilated counterpart?",true);
            this.assimilated_creeper_assimilation = builder.comment("Default true").define("Should creepers convert into their assimilated counterpart?",true);
            builder.pop();
            builder.push("Springer Targeting Goals");
            this.springer_attacks_enderman = builder.comment("Default true").define("Should springers target endermans?",true);
            this.springer_attacks_witch = builder.comment("Default true").define("Should springers target witches?",true);
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