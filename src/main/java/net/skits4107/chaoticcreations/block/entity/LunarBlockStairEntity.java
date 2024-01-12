package net.skits4107.chaoticcreations.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LunarBlockStairEntity extends VanishingBlockEntity{
    public LunarBlockStairEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LUNAR_STAIR_BLOCK_ENTITY.get(), pPos, pBlockState);
    }
}
