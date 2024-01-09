package net.skits4107.chaoticcreations.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class LunarBlockBrickEntity extends VanishingBlockEntity{
    public LunarBlockBrickEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LUNAR_BLOCK_BRICK_ENTITY.get(), pPos, pBlockState);
    }
}
