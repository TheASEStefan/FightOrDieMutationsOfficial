package net.teamabyssal.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.teamabyssal.entity.custom.ShillerEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import software.bernie.geckolib.model.GeoModel;

public class ShillerModel extends GeoModel<ShillerEntity> {

    @Override
    public ResourceLocation getModelResource(ShillerEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "geo/shiller.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShillerEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "textures/entity/shiller.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShillerEntity animatable) {
        return new ResourceLocation(FightOrDieMutations.MODID, "animations/shiller.animation.json");
    }
}
