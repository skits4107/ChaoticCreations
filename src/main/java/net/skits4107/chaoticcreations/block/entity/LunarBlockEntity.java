package net.skits4107.chaoticcreations.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.block.custom.LunarBlock;

public class LunarBlockEntity extends BlockEntity {

    public LunarBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.LUNAR_BLOCK_ENTITY.get(), pPos, pBlockState);
    }


    public void tick(Level pLevel, BlockPos pPos, BlockState pState, LunarBlockEntity pBlockEntity) {
        if(pLevel.isDay() != pState.getValue(LunarBlock.DAYTIME) && !pLevel.isClientSide){
            pLevel.setBlock(pPos,  pState.setValue(LunarBlock.DAYTIME, pLevel.isDay()), 3);
        }

    }

}
