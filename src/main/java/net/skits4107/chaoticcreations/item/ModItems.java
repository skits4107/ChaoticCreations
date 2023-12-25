package net.skits4107.chaoticcreations.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.skits4107.chaoticcreations.ChaoticCreations;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ChaoticCreations.MOD_ID);

    /* new items here*/

    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
