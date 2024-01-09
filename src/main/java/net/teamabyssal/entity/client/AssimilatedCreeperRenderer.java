package net.teamabyssal.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.teamabyssal.entity.custom.AssimilatedCreeperEntity;
import net.teamabyssal.entity.custom.MalruptorEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AssimilatedCreeperRenderer extends GeoEntityRenderer<AssimilatedCreeperEntity> {

    public AssimilatedCreeperRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new AssimilatedCreeperModel());
        shadowRadius = 0.0f;
    }
    @Override
    public void render(AssimilatedCreeperEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        stack.pushPose();
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
        stack.popPose();
    }
}
