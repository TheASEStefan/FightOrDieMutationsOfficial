package net.teamabyssal.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.teamabyssal.entity.custom.AssimilatedEndermanHeadEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import software.bernie.geckolib.model.GeoModel;

public class AssimilatedEndermanHeadModel extends GeoModel<AssimilatedEndermanHeadEntity> {


    @Override
    public ResourceLocation getModelResource(AssimilatedEndermanHeadEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "geo/assimilated_enderman_head.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AssimilatedEndermanHeadEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "textures/entity/assimilated_enderman_head.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AssimilatedEndermanHeadEntity animatable) {
        return new ResourceLocation(FightOrDieMutations.MODID, "animations/assimilated_enderman_head.animation.json");
    }
}
