package net.skits4107.chaoticcreations.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class LunarBlockCrackedEntity extends VanishingBlockEntity{
    public LunarBlockCrackedEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LUNAR_BLOCK_CRACKED_ENTITY.get(), pPos, pBlockState);
    }
}
