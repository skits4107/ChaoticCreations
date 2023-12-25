package net.skits4107.chaoticcreations;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

    public static Random random = new Random();
    public static List<Block> blocks = new ArrayList<>();

    static {
        // Static initialization of the blocks list
        for (Block block : ForgeRegistries.BLOCKS) {
            blocks.add(block);
        }
    }
    public static void randomizeBlock(BlockPos pos, Level level){
        //List<Block> blocks = new ArrayList<>();
        if (blocks.isEmpty()){
            return;
        }

        //get random block
        int index = random.nextInt(blocks.size());
        Block block = blocks.get(index);
        //set block in world
        level.setBlock(pos, block.defaultBlockState(), 3);
    }
}
