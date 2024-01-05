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

    public static final RegistryObject<BlockEntityType<LunarBlockEntity>> LUNAR_BLOCK_ENTITY = BLOCK_ENTITIES.register("testing_e", ()->
            BlockEntityType.Builder.of(LunarBlockEntity::new, ModBlocks.LUNAR_BLOCK.get()).build(null)
    );

    public static void register(IEventBus bus){
        BLOCK_ENTITIES.register(bus);
    }
}
