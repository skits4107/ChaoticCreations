package net.skits4107.chaoticcreations.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ChaoticCreations.MOD_ID);

    public static final RegistryObject<BlockEntityType<LunarBlockEntity>> LUNAR_BLOCK_ENTITY = BLOCK_ENTITIES.register("lunar_block", ()->
            BlockEntityType.Builder.of(LunarBlockEntity::new, ModBlocks.LUNAR_BLOCK.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<LunarBlockBrickEntity>> LUNAR_BLOCK_BRICK_ENTITY = BLOCK_ENTITIES.register("lunar_block_brick_entity", ()->
            BlockEntityType.Builder.of(LunarBlockBrickEntity::new, ModBlocks.LUNAR_BLOCK_BRICK.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<LunarBlockCarvedEntity>> LUNAR_BLOCK_CARVED_ENTITY = BLOCK_ENTITIES.register("lunar_block_carved_entity", ()->
            BlockEntityType.Builder.of(LunarBlockCarvedEntity::new, ModBlocks.LUNAR_BLOCK_CARVED.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<LunarBlockCobbleEntity>> LUNAR_BLOCK_COBBLE_ENTITY = BLOCK_ENTITIES.register("lunar_block_cobble_entity", ()->
            BlockEntityType.Builder.of(LunarBlockCobbleEntity::new, ModBlocks.LUNAR_BLOCK_COBBLE.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<LunarBlockCrackedEntity>> LUNAR_BLOCK_CRACKED_ENTITY = BLOCK_ENTITIES.register("lunar_block_cracked_entity", ()->
            BlockEntityType.Builder.of(LunarBlockCrackedEntity::new, ModBlocks.LUNAR_BLOCK_CRACKED.get()).build(null)
    );

    public static final RegistryObject<BlockEntityType<LunarBlockStairEntity>> LUNAR_STAIR_BLOCK_ENTITY = BLOCK_ENTITIES.register("lunar_stair_block_entity", ()->
            BlockEntityType.Builder.of(LunarBlockStairEntity::new, ModBlocks.LUNAR_STAIR_BLOCK.get()).build(null)
    );

    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
