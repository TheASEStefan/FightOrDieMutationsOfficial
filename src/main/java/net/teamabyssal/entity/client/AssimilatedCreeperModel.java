package net.teamabyssal.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.teamabyssal.entity.custom.AssimilatedCreeperEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import software.bernie.geckolib.model.GeoModel;

public class AssimilatedCreeperModel extends GeoModel<AssimilatedCreeperEntity> {


    @Override
    public ResourceLocation getModelResource(AssimilatedCreeperEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "geo/assimilated_creeper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AssimilatedCreeperEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "textures/entity/assimilated_creeper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AssimilatedCreeperEntity animatable) {
        return new ResourceLocation(FightOrDieMutations.MODID, "animations/assimilated_creeper.animation.json");
    }
}
