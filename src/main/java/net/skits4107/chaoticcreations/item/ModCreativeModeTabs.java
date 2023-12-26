package net.skits4107.chaoticcreations.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.skits4107.chaoticcreations.ChaoticCreations;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChaoticCreations.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CHAOTIC_TAB = CREATIVE_MODE_TABS.register("chaotic_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(Items.BREAD)) //to set modded item as display: ModItems.ITEM_NAME.get()
                    .title(Component.translatable("creativetab.chaotic_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        /* put items here to add to modded creative mode tab
                            pOutput.accept(ModItems.ITEM.get()); //example
                        */
                        pOutput.accept(ModItems.CHAOTIC_WAND.get());
                        pOutput.accept(ModItems.FALLING_STICK.get());

                    })
                    .build());

    public static void register(IEventBus EventBus){
        CREATIVE_MODE_TABS.register(EventBus);

    }
}
