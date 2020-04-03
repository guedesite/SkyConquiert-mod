package fr.neocraft.main.Blocks.Special;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SpawnerZombie extends SpawnerBlock{

	
	 public SpawnerZombie(Material p_i45394_1_, int level, int stat) {
		super(p_i45394_1_, level, stat);
		// TODO Auto-generated constructor stub
	}
	@Override
	    public boolean hasTileEntity(int metadata)
	    {
	        return true; // signale que le bloc a une entité
	    }
	 @Override
	 public int quantityDropped(Random p_149745_1_)
	    {
	        return 1;
	    }
	 public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	    {
	        return new TileEntityMobSpawner();
	    }
	 @Override
	    public int getExpDrop(IBlockAccess world, int metadata, int fortune)
	    {
	        return 0;
	    }


}
