package net.teamabyssal.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.teamabyssal.entity.custom.MalruptorEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MalruptorRenderer extends GeoEntityRenderer<MalruptorEntity> {


    public MalruptorRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new MalruptorModel());
        shadowRadius = 0.0f;
    }

    @Override
    public void render(MalruptorEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        stack.pushPose();
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
        stack.popPose();
    }
}
