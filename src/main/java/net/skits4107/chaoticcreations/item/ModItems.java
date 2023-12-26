package net.skits4107.chaoticcreations.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.item.custom.ChaoticWand;
import net.skits4107.chaoticcreations.item.custom.FallingStick;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChaoticCreations.MOD_ID);

    /* new items here*/
    public static final RegistryObject<Item> CHAOTIC_WAND = ITEMS.register("chaotic_wand", ()->new ChaoticWand(new Item.Properties().durability(20)));

    public static final RegistryObject<Item> FALLING_STICK = ITEMS.register("falling_stick", ()->new FallingStick(new Item.Properties().durability(100)));

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
