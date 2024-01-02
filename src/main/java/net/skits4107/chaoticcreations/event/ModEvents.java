package net.skits4107.chaoticcreations.event;

import net.minecraft.client.KeyMapping;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyMappingLookup;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skits4107.chaoticcreations.ChaoticCreations;
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

}
