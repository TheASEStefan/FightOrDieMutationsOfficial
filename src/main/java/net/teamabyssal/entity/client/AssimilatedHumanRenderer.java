package net.teamabyssal.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.teamabyssal.entity.custom.AssimilatedHumanEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AssimilatedHumanRenderer extends GeoEntityRenderer<AssimilatedHumanEntity> {


    public AssimilatedHumanRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new AssimilatedHumanModel());
        shadowRadius = 0.0f;
    }

    @Override
    public void render(AssimilatedHumanEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        stack.pushPose();
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
        stack.popPose();
    }
}
