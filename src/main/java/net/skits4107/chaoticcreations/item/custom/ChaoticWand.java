package net.skits4107.chaoticcreations.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

import static net.skits4107.chaoticcreations.Util.randomizeBlock;

public class ChaoticWand extends Item {
    public ChaoticWand(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        BlockPos pos = pContext.getClickedPos();
        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();

        if(!level.isClientSide){

            if(player != null){
                randomizeBlock(pos, level);
                pContext.getItemInHand().hurtAndBreak(1, player, (p) -> {
                    p.broadcastBreakEvent(pContext.getHand());
                });
                return InteractionResult.SUCCESS;
            }

        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return true;
    }




}
