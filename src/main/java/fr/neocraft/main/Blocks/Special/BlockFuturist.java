package fr.neocraft.main.Blocks.Special;

import java.util.Random;

import fr.neocraft.main.Blocks.BlockBasic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public  class BlockFuturist extends BlockBasic {

	
	public BlockFuturist(Material p_i45394_1_, int level) {
		super(p_i45394_1_, level);
	}
	
	public boolean SendData(World w, int x, int y, int z, int lastx, int lasty, int lastz, Data type, int nbreq) {
		boolean flag = false;
		if(nbreq < 15)
		{
			if((x +1 != lastx | y != lasty | z != lastz) & w.getBlock(x+1, y, z) instanceof BlockFuturist)
			{
				((BlockFuturist) w.getBlock(x+1, y, z)).RecieveData(w, x + 1, y, z, x, y, z, type, nbreq++);
				flag = true;
			}if((x -1 != lastx | y != lasty | z != lastz )& w.getBlock(x-1, y, z) instanceof BlockFuturist)
			{
				((BlockFuturist) w.getBlock(x-1, y, z)).RecieveData(w, x - 1, y, z, x, y, z, type, nbreq++);
				flag = true;
			} if((x != lastx | y+1 != lasty | z != lastz) & w.getBlock(x, y+1, z) instanceof BlockFuturist)
			{
				((BlockFuturist) w.getBlock(x, y+1, z)).RecieveData(w, x, y+1, z, x, y, z, type, nbreq++);
				flag = true;
			} if((x != lastx | y-1 != lasty | z != lastz )& w.getBlock(x, y-1, z) instanceof BlockFuturist)
				
			{
				((BlockFuturist) w.getBlock(x, y-1, z)).RecieveData(w, x, y-1, z, x, y, z, type, nbreq++);
				flag = true;
			}if((x != lastx | y != lasty | z+1 != lastz )& w.getBlock(x, y, z+1) instanceof BlockFuturist)
			{
				((BlockFuturist) w.getBlock(x, y, z+1)).RecieveData(w, x , y, z+1, x, y, z, type, nbreq++);
				flag = true;
			} if((x != lastx | y != lasty | z-1 != lastz )& w.getBlock(x, y, z-1) instanceof BlockFuturist)
			{
				((BlockFuturist) w.getBlock(x, y, z-1)).RecieveData(w, x, y, z-1, x, y, z, type, nbreq++);
				flag = true;
			}
		}
		return flag;
	}
	
	public void RecieveData(World w, int x, int y, int z, int lastx, int lasty, int lastz, Data type, int nbreq) {
		
	}

	
	
	public static enum Data {
		Reset,
		TrueOutPut,
		FalseOutPut;
	}



}
