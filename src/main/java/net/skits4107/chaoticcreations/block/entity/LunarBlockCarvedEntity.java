package net.skits4107.chaoticcreations.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class LunarBlockCarvedEntity extends VanishingBlockEntity {

    public LunarBlockCarvedEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LUNAR_BLOCK_CARVED_ENTITY.get(), pPos, pBlockState);
    }
}
