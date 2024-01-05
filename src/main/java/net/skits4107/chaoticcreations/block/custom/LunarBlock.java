package net.skits4107.chaoticcreations.block.custom;


import net.minecraft.core.BlockPos;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import net.skits4107.chaoticcreations.block.entity.LunarBlockEntity;
import net.skits4107.chaoticcreations.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;




public class LunarBlock extends BaseEntityBlock {



    public static final Property<Boolean> DAYTIME = BooleanProperty.create("daytime");

    public LunarBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(DAYTIME, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(DAYTIME); //registers properties to block states

    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        if(pState.getValue(DAYTIME)){
            return RenderShape.INVISIBLE;
        }
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(pState.getValue(DAYTIME)){
            return Shapes.empty();
        }
        return super.getShape(pState, pLevel, pPos, pContext);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        if(pState.getValue(DAYTIME)){
            return Shapes.empty();
        }
        return super.getCollisionShape(pState, pLevel, pPos, pContext);
    }


    @Override
    public VoxelShape getInteractionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        if(pState.getValue(DAYTIME)){
            return Shapes.empty();
        }
        return super.getInteractionShape(pState, pLevel, pPos);
    }



    @Override
    public boolean hasDynamicShape() {
        return true;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new LunarBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()){
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.LUNAR_BLOCK_ENTITY.get(), (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1, pBlockEntity));

    }

    @Override
    public boolean canBeReplaced(BlockState pState, BlockPlaceContext pUseContext) {
        return pState.getValue(DAYTIME);
    }


}
