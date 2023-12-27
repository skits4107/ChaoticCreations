package net.skits4107.chaoticcreations.event;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyMappingLookup;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skits4107.chaoticcreations.ChaoticCreations;



@Mod.EventBusSubscriber(modid = ChaoticCreations.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void KeyPress(InputEvent.Key event){
        if(event.getKey() == 90){

        }

    }
}
