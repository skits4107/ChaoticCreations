package net.skits4107.chaoticcreations.event;

import net.minecraft.client.KeyMapping;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyMappingLookup;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.entity.custom.CustomtizableGolem;
import net.skits4107.chaoticcreations.item.ModItems;

import java.util.Random;


@Mod.EventBusSubscriber(modid = ChaoticCreations.MOD_ID)
public class ModEvents {

    private static Random random = new Random();

    @SubscribeEvent
    public static void onLivingEntityUpdate(LivingEvent.LivingTickEvent event){
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide){
            if (entity instanceof Parrot){
                int val = random.nextInt(12000); //12000 every 10 or so minutes
                if (val == 0){
                    ItemStack itemStack = new ItemStack(ModItems.FEATHER_WAND.get());
                    ItemEntity drop = new ItemEntity(entity.level(), entity.getX(), entity.getY(),entity.getZ(), itemStack);
                    entity.level().addFreshEntity(drop);
                }
            }
        }
    }

    //it is vclient side event. come back to it later.
    /*
    @SubscribeEvent
    public static void onBlockPlaced(BlockEvent.EntityPlaceEvent event){
        Level level = event.getEntity().level();

        ChaoticCreations.LOGGER.info("Event fired");
        if(!level.isClientSide){
            return;
        }
        ChaoticCreations.LOGGER.info("server side");

        BlockState state = event.getPlacedBlock();
        if(state.getBlock().equals(Blocks.CARVED_PUMPKIN)){
            ChaoticCreations.LOGGER.info("pumpkin");
            BlockPos pos = event.getBlockSnapshot().getPos();
            BlockState upper = level.getBlockState(pos.below());
            BlockState lower = level.getBlockState(pos.below().below());
            CustomtizableGolem golem = CustomtizableGolem.build(upper, lower, level);
            golem.setPos(pos.getX(), pos.getY(), pos.getZ());
            level.addFreshEntity(golem);
        }


    } */


}
