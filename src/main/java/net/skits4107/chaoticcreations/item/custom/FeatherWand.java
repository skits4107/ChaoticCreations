package net.skits4107.chaoticcreations.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.skits4107.chaoticcreations.entity.custom.HeavyItemEntity;

import java.util.List;
import java.util.Optional;

public class FeatherWand extends Item {
    public FeatherWand(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();

        //check if item entity was clicked on
        //there is probably a better way but i couldnt find any methods or objects that allowed me
        //to do it in a simplier way. this is what chatgpt came up with and seemed good enough to me
        if (!level.isClientSide) {
            double reachDistance = 5.0; // Example reach distance
            AABB area = player.getBoundingBox().inflate(reachDistance);
            //get all the dropped items near player
            List<Entity> entities = level.getEntities(player, area, e -> (e instanceof ItemEntity || e instanceof HeavyItemEntity));

            //calculate the direction the player is looking and how are far it should look.
            Vec3 playerEyePosition = player.getEyePosition(1.0F);
            Vec3 viewVector = player.getViewVector(1.0F);
            Vec3 reachVector = playerEyePosition.add(viewVector.x * reachDistance, viewVector.y * reachDistance, viewVector.z * reachDistance);

            //go through each dropped item entity and check if it is being looked at by the player
            for (Entity entity : entities) {
                AABB entityBoundingBox = entity.getBoundingBox();
                //check if you found an entity within line of sight of the player
                Optional<Vec3> hitVec = entityBoundingBox.clip(playerEyePosition, reachVector);
                if (hitVec.isPresent()) {
                    // Entity is within the line of sight, perform your logic
                    if (entity instanceof ItemEntity){
                        HeavyItemEntity heavyItem = HeavyItemEntity.makeHeavy((ItemEntity) entity);
                        heavyItem.absMoveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                        entity.remove(Entity.RemovalReason.DISCARDED);
                        level.addFreshEntity(heavyItem);
                        return InteractionResult.SUCCESS;
                    }
                    //else:
                    HeavyItemEntity heavyItem = (HeavyItemEntity) entity;
                    ItemStack itemStack = heavyItem.getItem();
                    ItemEntity drop = new ItemEntity(level, entity.getX(), entity.getY(),entity.getZ(), itemStack);
                    entity.remove(Entity.RemovalReason.KILLED);
                    level.addFreshEntity(drop);
                    return InteractionResult.SUCCESS;

                }
            }

        }

        //if there was no item entity clicked on then make blocks fall
        BlockPos pos = pContext.getClickedPos();
        BlockState state = level.getBlockState(pos);
        if (!level.isClientSide){
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            FallingBlockEntity.fall(level, pos, state);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        //check if item entity was clicked on
        //there is probably a better way but i couldnt find any methods or objects that allowed me
        //to do it in a simplier way. this is what chatgpt came up with and seemed good enough to me
        if (!level.isClientSide) {
            // Calculate the ray trace result

            double reachDistance = 5.0; // Example reach distance
            AABB area = player.getBoundingBox().inflate(reachDistance);
            List<Entity> entities = level.getEntities(player, area, e -> (e instanceof ItemEntity || e instanceof HeavyItemEntity));

            //calculate the direction the player is looking and how are far it should look.
            Vec3 playerEyePosition = player.getEyePosition(1.0F);
            Vec3 viewVector = player.getViewVector(1.0F);
            Vec3 reachVector = playerEyePosition.add(viewVector.x * reachDistance, viewVector.y * reachDistance, viewVector.z * reachDistance);

            //go through each dropped item entity and check if it is being looked at by the player
            for (Entity entity : entities) {
                AABB entityBoundingBox = entity.getBoundingBox();
                //check if you found an entity within line of sight of the player
                Optional<Vec3> hitVec = entityBoundingBox.clip(playerEyePosition, reachVector);
                if (hitVec.isPresent()) {
                    // Entity is within the line of sight, perform your logic
                    if (entity instanceof ItemEntity) {
                        HeavyItemEntity heavyItem = HeavyItemEntity.makeHeavy((ItemEntity) entity);
                        heavyItem.absMoveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                        entity.remove(Entity.RemovalReason.DISCARDED);
                        level.addFreshEntity(heavyItem);
                        break;
                    }
                    //else:
                    HeavyItemEntity heavyItem = (HeavyItemEntity) entity;
                    ItemStack itemStack = heavyItem.getItem();
                    ItemEntity drop = new ItemEntity(level, entity.getX(), entity.getY(), entity.getZ(), itemStack);
                    entity.remove(Entity.RemovalReason.KILLED);
                    level.addFreshEntity(drop);
                    break;
                }
            }
        }
        return super.use(level, player, usedHand);
    }



    /*
    keeping just in case i need to use this code for simular functionality for other features

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (!level.isClientSide){
            findBlocksAroundPlayer(player, level,10);
            player.getItemInHand(usedHand).hurtAndBreak(1, player, (p) -> {
                p.broadcastBreakEvent(usedHand);
            });
        }
        return super.use(level, player, usedHand);
    }


    public void findBlocksAroundPlayer(Player player, Level level, int radius) {
        BlockPos playerPos = player.blockPosition();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos blockPos = playerPos.offset(x, y, z);
                    BlockState blockState = level.getBlockState(blockPos);
                    // Process the block as needed

                    if (blockPos.distToCenterSqr(playerPos.getX(), playerPos.getY(), playerPos.getZ()) <= radius*radius){
                        level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
                        FallingBlockEntity.fall(level, blockPos, blockState);
                    }
                }
            }
        }
    } */

    }
