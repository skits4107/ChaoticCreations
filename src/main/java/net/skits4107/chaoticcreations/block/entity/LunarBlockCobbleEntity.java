package net.skits4107.chaoticcreations.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class LunarBlockCobbleEntity extends VanishingBlockEntity{
    public LunarBlockCobbleEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LUNAR_BLOCK_COBBLE_ENTITY.get(), pPos, pBlockState);
    }
}
