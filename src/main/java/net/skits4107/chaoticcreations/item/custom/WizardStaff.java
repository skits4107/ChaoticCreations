package net.skits4107.chaoticcreations.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.entity.custom.CustomFallingBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WizardStaff extends Item {


    public WizardStaff(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if (pStack.hasTag()){
            CompoundTag tag = pStack.getTag();
            if (tag.contains("spell")){
                String spell = tag.getString("spell");
                pTooltipComponents.add(Component.literal("current spell selected: "+spell));
                return;
            }
        }
        //if there is no spell tag then:
        pTooltipComponents.add(Component.literal("current spell selected: None"));

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //exit early on client side
        //if (pLevel.isClientSide) {
          //  return super.use(pLevel, pPlayer, pUsedHand);
        //}
        ItemStack itemStack = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
        //if there is nbt data
        if (itemStack.hasTag()){
            CompoundTag tag = itemStack.getTag();
            if (tag.contains("spell")){
                String spell = tag.getString("spell");
                castSpell(pLevel, pPlayer, itemStack, spell);
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    protected void castSpell(Level pLevel, Player pPlayer, ItemStack stack, String spell){
        if (spell.equals("fire_blast")){
            Vec3 looking = pPlayer.getViewVector(1.0F);
            Entity block = CustomFallingBlockEntity.fall(pLevel, pPlayer.blockPosition().above(), Blocks.FIRE.defaultBlockState(), looking);
            ChaoticCreations.LOGGER.info(looking.toString());

            block.setDeltaMovement(looking);
            pLevel.addFreshEntity(block);

            stack.hurtAndBreak(1, pPlayer, (p) -> {
                p.broadcastBreakEvent(InteractionHand.MAIN_HAND);
            });
        }
    }

}
