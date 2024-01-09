package net.skits4107.chaoticcreations.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.block.custom.LunarBlock;

public class VanishingBlockEntity extends BlockEntity {



    public VanishingBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState, VanishingBlockEntity pBlockEntity) {
        if(pLevel.isClientSide){
            return;
        }
        ChaoticCreations.LOGGER.info("tick block");
        if(pLevel.isDay() != pState.getValue(LunarBlock.DAYTIME)){
            ChaoticCreations.LOGGER.info("set block");
            level.setBlock(pPos,  pState.setValue(LunarBlock.DAYTIME, level.isDay()), 3);
        }
    }

}
