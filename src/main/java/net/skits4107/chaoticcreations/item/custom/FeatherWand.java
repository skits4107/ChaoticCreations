package net.skits4107.chaoticcreations.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class FeatherWand extends Item {
    public FeatherWand(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        //Player player = pContext.getPlayer();
        BlockState state = level.getBlockState(pos);
        if (!level.isClientSide){
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            FallingBlockEntity.fall(level, pos, state);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    /*
    keeping just in case i need to use this code for simular functionality for other features

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide){
            findBlocksAroundPlayer(player, level,10);
            player.getItemInHand(usedHand).hurtAndBreak(1, player, (p) -> {
                p.broadcastBreakEvent(usedHand);
            });
        }
        return super.use(level, player, usedHand);
    }


    public void findBlocksAroundPlayer(Player player, Level level, int radius) {
        BlockPos playerPos = player.blockPosition();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockPos = playerPos.offset(x, y, z);
                    BlockState blockState = level.getBlockState(blockPos);
                    // Process the block as needed

                    if (blockPos.distToCenterSqr(playerPos.getX(), playerPos.getY(), playerPos.getZ()) <= radius*radius){
                        level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
                        FallingBlockEntity.fall(level, blockPos, blockState);
                    }
                }
            }
        }
    } */


}
