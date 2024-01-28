package net.skits4107.chaoticcreations.entity.custom;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.skits4107.chaoticcreations.Util;
import net.skits4107.chaoticcreations.entity.ModEntities;

import java.util.Optional;

public class CustomtizableGolem extends Entity {

    public static final EntityDataAccessor<BlockState> UPPER_BLOCK = SynchedEntityData.defineId(CustomtizableGolem.class, EntityDataSerializers.BLOCK_STATE);

    public static final EntityDataAccessor<BlockState> LOWER_BLOCK = SynchedEntityData.defineId(CustomtizableGolem.class, EntityDataSerializers.BLOCK_STATE);


    public CustomtizableGolem(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(UPPER_BLOCK, Util.getRnandomBlock().defaultBlockState());
        this.entityData.define(LOWER_BLOCK, Util.getRnandomBlock().defaultBlockState());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {

        BlockState upperState = NbtUtils.readBlockState(null, tag.getCompound("uppper"));
        BlockState lowerState = NbtUtils.readBlockState(null, tag.getCompound("lower"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {

        tag.put("upper", NbtUtils.writeBlockState( this.getUpperBlock()));

        tag.put("lower", NbtUtils.writeBlockState( this.getLowerBlock()));

    }



    public BlockState getUpperBlock(){
        return this.entityData.get(UPPER_BLOCK);
    }

    public void setUpperBlock(BlockState state){
        this.entityData.set(UPPER_BLOCK, state);
    }

    public BlockState getLowerBlock(){
        return this.entityData.get(LOWER_BLOCK);
    }

    public void setLowerBlock(BlockState state){
        this.entityData.set(LOWER_BLOCK, state);
    }

    public static CustomtizableGolem build(BlockState upper, BlockState lower, Level level){
        CustomtizableGolem golem = ModEntities.CUSTOM_GOLEM.get().create(level);
        golem.setLowerBlock(lower);
        golem.setUpperBlock(upper);
        return golem;
    }






}
