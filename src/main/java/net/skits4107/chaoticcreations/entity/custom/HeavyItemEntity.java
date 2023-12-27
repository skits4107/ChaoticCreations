package net.skits4107.chaoticcreations.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.skits4107.chaoticcreations.entity.ModEntities;

public class HeavyItemEntity extends Entity {

    private static final EntityDataAccessor<ItemStack> DATA_ITEM = SynchedEntityData.defineId(ItemEntity.class, EntityDataSerializers.ITEM_STACK);
    public HeavyItemEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_ITEM, new ItemStack(Items.STONE));
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        CompoundTag compoundtag = tag.getCompound("Item");
        this.setItem(ItemStack.of(compoundtag));
        if (this.getItem().isEmpty()) {
            this.discard();
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (!this.getItem().isEmpty()) {
            tag.put("Item", this.getItem().save(new CompoundTag()));
        }
    }


    public ItemStack getItem() {
        return this.entityData.get(DATA_ITEM);
    }

    public void setItem(ItemStack item) {
        this.entityData.set(DATA_ITEM, item);
    }

    public static HeavyItemEntity makeHeavy(ItemEntity parent){
        HeavyItemEntity heavyItem = ModEntities.HEAVY_ITEM_ENTITY.get().create(parent.level());
        heavyItem.setItem(parent.getItem());
        return heavyItem;
    }


}
