package net.skits4107.chaoticcreations.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.internal.TextComponentMessageFormatHandler;
import net.skits4107.chaoticcreations.ChaoticCreations;
import net.skits4107.chaoticcreations.Util;
import net.skits4107.chaoticcreations.entity.custom.CustomFallingBlockEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WizardStaff extends Item {

    private int tick = 0;
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
                pTooltipComponents.add(Component.literal("current spell: "+color+spell));
                return;
            }
        }
        //if there is no spell tag then:
        pTooltipComponents.add(Component.literal("current spell: None"));

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //exit early on client side
        if (pLevel.isClientSide) {
            return super.use(pLevel, pPlayer, pUsedHand);
        }
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
            //create 10 fire blocks that shoot in the direction the player is looking with slightly different directions
            for(int i=0; i<10; i++){
                //add a bit of randomness to the direction of the fire
                Vec3 motion = looking.add(Util.random.nextDouble(1)-0.5, Util.random.nextDouble(1)-0.5, Util.random.nextDouble(1)-0.5).normalize();
                Entity block = CustomFallingBlockEntity.fall(pLevel, pPlayer.blockPosition().above(), Blocks.FIRE.defaultBlockState(), motion);
            }

            stack.hurtAndBreak(1, pPlayer, (p) -> {
                p.broadcastBreakEvent(InteractionHand.MAIN_HAND);
            });
        }
        else if (spell.equals("defense")){
            Direction dir = pPlayer.getDirection();
            BlockPos pos = pPlayer.blockPosition();
            stack.getTag().putBoolean("building_wall",true);
            stack.getTag().putIntArray("building_block", new int[]{0,0});
            stack.getTag().putIntArray("wall_player_pos", new int[]{pos.getX(), pos.getY(), pos.getZ()});
            if (dir.equals(Direction.EAST)){ //positive X
                stack.getTag().putString("direction", "east");
            }
            else if (dir.equals(Direction.WEST)){
                stack.getTag().putString("direction", "west");
            }
            else if (dir.equals(Direction.NORTH)){ //positive Z
                stack.getTag().putString("direction", "north");
            }
            else if (dir.equals(Direction.SOUTH)){
                stack.getTag().putString("direction", "south");
            }
            //inventory tick handles the wall building
        }
        else if(spell.equals("lightning")){
            Vec3 looking = pPlayer.getViewVector(1.0F).multiply(7,7,7);

            BlockHitResult result = pLevel.clip(new ClipContext(pPlayer.getEyePosition(), pPlayer.getEyePosition().add(looking), ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, pPlayer));
            BlockPos pos = result.getBlockPos();
            LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT, pLevel);

            bolt.setPos(new Vec3(pos.getX(), pos.getY(), pos.getZ()));
            pLevel.addFreshEntity(bolt);

        }
        else if (spell.equals("levitate")){

        }
    }

    @Override
    public Component getHighlightTip(ItemStack item, Component displayName) {
        if (item.hasTag()){
            CompoundTag tag = item.getTag();
            //check if tag has the spell
            if (tag.contains("spell")){
                //if it has the spell the ndisplay the spell
                String spell = tag.getString("spell");
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
                return Component.literal("Wizard staff, Spell: "+color+spell);
                //player.displayClientMessage(Component.literal("staff has: "+spell), true);
            }
        }
        return super.getHighlightTip(item, displayName);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pLevel.isClientSide){return;}
        tick++;
        if(tick % 1000 == 0){
            tick=0; //prevents tick from becoming super large.
        }

        if(tick % 3 != 0){
            return; //only every 3 ticks will it try and place a block
        }

        //if the item is the one building the wall
        if (pStack.hasTag()) {
            CompoundTag tag = pStack.getTag();
            if (tag.contains("building_wall") && tag.contains("building_block") && tag.contains("wall_player_pos") && tag.contains("direction")) {
                //if you are building the wall
                if (tag.getBoolean("building_wall")) {
                    //get relavent info from nbt
                    Direction dir = Direction.byName(tag.getString("direction"));
                    int[] pp = tag.getIntArray("wall_player_pos");
                    BlockPos playerPos = new BlockPos(pp[0], pp[1], pp[2]);
                    int[] current_block = tag.getIntArray("building_block");

                    //calculate where to drop based on direction and current block
                    if (dir.equals(Direction.NORTH)) { //negative Z
                        BlockPos current_pos = playerPos.offset(-3 + current_block[0], 5, -3);
                        Entity block = FallingBlockEntity.fall(pLevel, current_pos, Blocks.STONE.defaultBlockState());
                    }
                    else if (dir.equals(Direction.SOUTH)) { //positive Z
                        BlockPos current_pos = playerPos.offset(3 - current_block[0], 5, 3);
                        Entity block = FallingBlockEntity.fall(pLevel, current_pos, Blocks.STONE.defaultBlockState());
                    }
                    else if (dir.equals(Direction.WEST)) { // negative X
                        BlockPos current_pos = playerPos.offset(-3, 5, 3 - current_block[0]);
                        Entity block = FallingBlockEntity.fall(pLevel, current_pos, Blocks.STONE.defaultBlockState());
                    }
                    else if (dir.equals(Direction.EAST)) { //positive X
                        BlockPos current_pos = playerPos.offset(3, 5, -3 + current_block[0]);
                        Entity block = FallingBlockEntity.fall(pLevel, current_pos, Blocks.STONE.defaultBlockState());
                    }


                    //if it is the last block stop building
                    if (current_block[0] == 6 && current_block[1] == 3) {
                        tag.putBoolean("building_wall", false);
                    }
                    else if (current_block[0] == 6){ //if at end of row go to next
                        current_block[1] +=1;
                    }
                    //increase the current block column
                    current_block[0] = (current_block[0]+1) % 7;
                    //update the current row block.
                    tag.putIntArray("building_block", current_block);
                }
            }
        }

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }
}
