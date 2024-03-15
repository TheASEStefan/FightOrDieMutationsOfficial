package net.teamabyssal.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.teamabyssal.entity.custom.AssimilatedCreeperHeadEntity;
import net.teamabyssal.entity.custom.AssimilatedHumanHeadEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AssimilatedCreeperHeadRenderer extends GeoEntityRenderer<AssimilatedCreeperHeadEntity> {

    public AssimilatedCreeperHeadRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new AssimilatedCreeperHeadModel());
        shadowRadius = 0.0f;
    }

    @Override
    public void render(AssimilatedCreeperHeadEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        stack.pushPose();
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
        stack.popPose();
    }
}
