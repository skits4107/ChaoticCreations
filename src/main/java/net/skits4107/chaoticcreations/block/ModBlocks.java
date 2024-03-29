package net.skits4107.chaoticcreations.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.block.custom.*;
import net.skits4107.chaoticcreations.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ChaoticCreations.MOD_ID);

    /* new blocks here*/
    public static RegistryObject<Block> LUNAR_BLOCK = registerBlock("lunar_block", ()-> new LunarBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> LUNAR_BLOCK_BRICK = registerBlock("lunar_block_brick", ()-> new LunarBlockBrick(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> LUNAR_BLOCK_CARVED = registerBlock("lunar_block_carved", ()-> new LunarBlockCarved(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> LUNAR_BLOCK_COBBLE = registerBlock("lunar_block_cobble", ()-> new LunarBlockCobble(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> LUNAR_BLOCK_CRACKED = registerBlock("lunar_block_cracked", ()-> new LunarBlockCracked(BlockBehaviour.Properties.copy(Blocks.STONE)));

    public static RegistryObject<Block> LUNAR_STAIR_BLOCK = registerBlock("lunar_stair_block", ()-> new LunarBlockStair(()->LUNAR_BLOCK.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.STONE)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
