package net.skits4107.chaoticcreations;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.skits4107.chaoticcreations.block.ModBlocks;
import net.skits4107.chaoticcreations.block.entity.ModBlockEntities;
import net.skits4107.chaoticcreations.entity.ModEntities;
import net.skits4107.chaoticcreations.entity.client.CustomFallingBlockEntityRenderer;
import net.skits4107.chaoticcreations.entity.client.HeavyItemEntityRenderer;
import net.skits4107.chaoticcreations.item.ModCreativeModeTabs;
import net.skits4107.chaoticcreations.item.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChaoticCreations.MOD_ID)
public class ChaoticCreations {
    public static final String MOD_ID = "chaoticcreations";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ChaoticCreations() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        // example of how to add item to vanilla tab
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES){
            event.accept(ModItems.CHAOTIC_WAND); // same for mod blocks
            event.accept(ModItems.FEATHER_WAND);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.HEAVY_ITEM_ENTITY.get(), HeavyItemEntityRenderer::new);
            EntityRenderers.register(ModEntities.FALLING.get(), CustomFallingBlockEntityRenderer::new);
        }
    }
}
