package net.skits4107.chaoticcreations.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.entity.custom.CustomFallingBlockEntity;
import net.skits4107.chaoticcreations.entity.custom.CustomtizableGolem;
import net.skits4107.chaoticcreations.entity.custom.HeavyItemEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ChaoticCreations.MOD_ID);


    public static final RegistryObject<EntityType<HeavyItemEntity>> HEAVY_ITEM_ENTITY = ENTITIES.register("heavy_item_entity", () -> EntityType.Builder.
                    <HeavyItemEntity>of(HeavyItemEntity::new, MobCategory.MISC).
            sized(0.5F,0.5F).build("heavy_item_entity"));

    public static final RegistryObject<EntityType<CustomFallingBlockEntity>> FALLING = ENTITIES.register("falling", () -> EntityType.Builder.
                    <CustomFallingBlockEntity>of(CustomFallingBlockEntity::new, MobCategory.MISC).
            sized(0.5F,0.5F).build("falling"));

    public static final RegistryObject<EntityType<CustomtizableGolem>> CUSTOM_GOLEM = ENTITIES.register("custom_golem", () -> EntityType.Builder.
                    <CustomtizableGolem>of(CustomtizableGolem::new, MobCategory.MISC).
            sized(1F,3F).build("custom_golem"));


    public static void register(IEventBus bus){
        ENTITIES.register(bus);
    }
}
