package net.skits4107.chaoticcreations.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.skits4107.chaoticcreations.item.ModItems;

public class SpellCrystal extends Item {

    protected String spell;
    public SpellCrystal(Properties pProperties, String spell) {
        super(pProperties);
        this.spell = spell;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (pLevel.isClientSide()){
            return super.use(pLevel, pPlayer, pUsedHand);
        }

        Inventory inventory = pPlayer.getInventory();
        //if there is wizard staff
        for (ItemStack item : inventory.items) {
            if (item.getItem() == ModItems.WIZARD_STAFF.get()) {
                //change wizard staff spell
                if (item.hasTag()){
                    CompoundTag tag = item.getTag();
                    tag.putString("spell", spell);
                }
                else{
                    CompoundTag tag = new CompoundTag();
                    tag.putString("spell", spell);
                }
                ChatFormatting color;
                switch (spell){
                    case "fire_blast":
                        color = ChatFormatting.RED;
                        break;
                    case "defense":
                        color = ChatFormatting.DARK_GRAY;
                        break;
                    case "lightning":
                        color = ChatFormatting.BLUE;
                        break;
                    case "levitate":
                        color = ChatFormatting.YELLOW;
                        break;
                    default:
                        color =  ChatFormatting.WHITE;
                }
                pPlayer.displayClientMessage(Component.literal("staff has: "+color+this.spell), true);
                break;
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }
    public String getSpell(){
        return this.spell;
    }
}
