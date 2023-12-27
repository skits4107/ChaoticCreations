package net.skits4107.chaoticcreations.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.skits4107.chaoticcreations.entity.custom.HeavyItemEntity;
import org.joml.Quaternionf;

public class HeavyItemEntityRenderer  extends EntityRenderer<HeavyItemEntity> {

    private final ItemRenderer itemRenderer;
    public HeavyItemEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.itemRenderer = pContext.getItemRenderer();
    }

    @Override
    public ResourceLocation getTextureLocation(HeavyItemEntity pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }

    @Override
    public void render(HeavyItemEntity pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose();
        ItemStack itemstack = pEntity.getItem();
        Item item = itemstack.getItem();
        BakedModel bakedmodel = this.itemRenderer.getModel(itemstack, pEntity.level(), (LivingEntity)null, pEntity.getId());

        if(item instanceof BlockItem ){
            BlockItem blockItem = (BlockItem) item;
            if (blockItem.getBlock() instanceof BushBlock){
                pPoseStack.mulPose(new Quaternionf().fromAxisAngleDeg(1.0F, 0.0F, 0.0F, 90.0F));
            }
            else{
                pPoseStack.translate(0.0,-0.1,0.0);
            }
        }
        else{
            pPoseStack.mulPose(new Quaternionf().fromAxisAngleDeg(1.0F, 0.0F, 0.0F, 90.0F));
        }

        this.itemRenderer.render(itemstack, ItemDisplayContext.GROUND, false, pPoseStack, pBuffer, pPackedLight, OverlayTexture.NO_OVERLAY, bakedmodel);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
    }
}
