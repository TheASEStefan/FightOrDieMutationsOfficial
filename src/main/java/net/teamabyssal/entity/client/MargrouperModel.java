package net.teamabyssal.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.teamabyssal.entity.custom.MargrouperEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import software.bernie.geckolib.model.GeoModel;

public class MargrouperModel extends GeoModel<MargrouperEntity> {
    @Override
    public ResourceLocation getModelResource(MargrouperEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "geo/margrouper.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MargrouperEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "textures/entity/margrouper.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MargrouperEntity animatable) {
        return new ResourceLocation(FightOrDieMutations.MODID, "animations/margrouper.animation.json");
    }
}
