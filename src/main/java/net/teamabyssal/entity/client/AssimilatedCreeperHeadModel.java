package net.teamabyssal.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.teamabyssal.entity.custom.AssimilatedCreeperHeadEntity;
import net.teamabyssal.fight_or_die.FightOrDieMutations;
import software.bernie.geckolib.model.GeoModel;

public class AssimilatedCreeperHeadModel extends GeoModel<AssimilatedCreeperHeadEntity> {
    @Override
    public ResourceLocation getModelResource(AssimilatedCreeperHeadEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "geo/assimilated_creeper_head.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AssimilatedCreeperHeadEntity object) {
        return new ResourceLocation(FightOrDieMutations.MODID, "textures/entity/assimilated_creeper_head.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AssimilatedCreeperHeadEntity animatable) {
        return new ResourceLocation(FightOrDieMutations.MODID, "animations/assimilated_creeper_head.animation.json");
    }
}
