package net.skits4107.chaoticcreations.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.skits4107.chaoticcreations.block.entity.LunarBlockCrackedEntity;
import net.skits4107.chaoticcreations.block.entity.LunarBlockEntity;
import net.skits4107.chaoticcreations.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class LunarBlockCracked extends LunarBlock {


    public LunarBlockCracked(Properties pProperties) {
        super(pProperties);
    }
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new LunarBlockCrackedEntity(pPos, pState);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.LUNAR_BLOCK_CRACKED_ENTITY.get(), (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1, pBlockEntity));

    }
}
