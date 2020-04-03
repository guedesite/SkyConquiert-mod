package fr.neocraft.main.Blocks.Special;

import java.util.Random;

import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class SpawnerBlock extends BlockMobSpawner{

	public int id;
	
	public SpawnerBlock(Material p_i45394_1_, int level, int stat)
    {
		
		id=stat;
        this.setHarvestLevel("pickaxe", level);
    }
	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true; // signale que le bloc a une entité
    }
 @Override
 public int quantityDropped(Random p_149745_1_)
    {
        return 0;
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
