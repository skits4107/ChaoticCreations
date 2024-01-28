package net.skits4107.chaoticcreations.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.skits4107.chaoticcreations.entity.custom.CustomFallingBlockEntity;
import net.skits4107.chaoticcreations.entity.custom.CustomtizableGolem;

public class CustomizableGolemRenderer  extends EntityRenderer<CustomtizableGolem> {
    private final BlockRenderDispatcher dispatcher;
    public CustomizableGolemRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.dispatcher = pContext.getBlockRenderDispatcher();
    }

    @Override
    public ResourceLocation getTextureLocation(CustomtizableGolem pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    public void render(CustomtizableGolem pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        BlockState upper = pEntity.getUpperBlock();
        BlockState lower = pEntity.getLowerBlock();
        BlockState pumpkin = Blocks.CARVED_PUMPKIN.defaultBlockState();

        if (upper.getRenderShape() == RenderShape.MODEL) {
            Level level = pEntity.level();
            if (upper != level.getBlockState(pEntity.blockPosition()) && upper.getRenderShape() != RenderShape.INVISIBLE) {
                pMatrixStack.pushPose();
                BlockPos blockpos = BlockPos.containing(pEntity.getX(), pEntity.getBoundingBox().maxY-1, pEntity.getZ());
                pMatrixStack.translate(-0.5D, -1D, -0.5D);
                var model = this.dispatcher.getBlockModel(upper);
                for (var renderType : model.getRenderTypes(upper, RandomSource.create(upper.getSeed(pEntity.blockPosition())), net.minecraftforge.client.model.data.ModelData.EMPTY))
                    this.dispatcher.getModelRenderer().tesselateBlock(level, model, upper, blockpos, pMatrixStack, pBuffer.getBuffer(renderType), false, RandomSource.create(), upper.getSeed(pEntity.blockPosition()), OverlayTexture.NO_OVERLAY, net.minecraftforge.client.model.data.ModelData.EMPTY, renderType);
                pMatrixStack.popPose();
            }
        }

        if (pumpkin.getRenderShape() == RenderShape.MODEL) {
            Level level = pEntity.level();
            if (pumpkin != level.getBlockState(pEntity.blockPosition()) && pumpkin.getRenderShape() != RenderShape.INVISIBLE) {
                pMatrixStack.pushPose();
                BlockPos blockpos = BlockPos.containing(pEntity.getX(), pEntity.getBoundingBox().maxY, pEntity.getZ());
                pMatrixStack.translate(-0.5D, 0.0D, -0.5D);
                var model = this.dispatcher.getBlockModel(pumpkin);
                for (var renderType : model.getRenderTypes(pumpkin, RandomSource.create(pumpkin.getSeed(pEntity.blockPosition())), net.minecraftforge.client.model.data.ModelData.EMPTY))
                    this.dispatcher.getModelRenderer().tesselateBlock(level, model, pumpkin, blockpos, pMatrixStack, pBuffer.getBuffer(renderType), false, RandomSource.create(), pumpkin.getSeed(pEntity.blockPosition()), OverlayTexture.NO_OVERLAY, net.minecraftforge.client.model.data.ModelData.EMPTY, renderType);
                pMatrixStack.popPose();
            }
        }

        if (lower.getRenderShape() == RenderShape.MODEL) {
            Level level = pEntity.level();
            if (lower != level.getBlockState(pEntity.blockPosition()) && lower.getRenderShape() != RenderShape.INVISIBLE) {
                pMatrixStack.pushPose();
                BlockPos blockpos = BlockPos.containing(pEntity.getX(), pEntity.getBoundingBox().maxY-2, pEntity.getZ());
                pMatrixStack.translate(-0.5D, -2D, -0.5D);
                var model = this.dispatcher.getBlockModel(lower);
                for (var renderType : model.getRenderTypes(lower, RandomSource.create(lower.getSeed(pEntity.blockPosition())), net.minecraftforge.client.model.data.ModelData.EMPTY))
                    this.dispatcher.getModelRenderer().tesselateBlock(level, model, lower, blockpos, pMatrixStack, pBuffer.getBuffer(renderType), false, RandomSource.create(), lower.getSeed(pEntity.blockPosition()), OverlayTexture.NO_OVERLAY, net.minecraftforge.client.model.data.ModelData.EMPTY, renderType);
                pMatrixStack.popPose();
            }
        }


        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }


}
