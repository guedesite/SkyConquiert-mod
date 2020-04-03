package fr.neocraft.main.Blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.Special.BlockFuturist;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockElevateur extends BlockFuturist{

	public int radius = 5;
	
	public BlockElevateur(Material p_i45394_1_, int level, int r) {
		super(p_i45394_1_, level);
		this.radius = r;
	}
	
	
	@SideOnly(Side.CLIENT)
    private IIcon[] lvl;

	 public void registerBlockIcons(IIconRegister p_149651_1_)
	 {
	     this.blockIcon = p_149651_1_.registerIcon(neoreference.MOD_ID + ":Elevateur_base");
	     lvl =new IIcon[3];
	     for(int i = 0; i < 3; i++)
	     { 
	    	 lvl[i] = p_149651_1_.registerIcon(neoreference.MOD_ID + ":Elevateur_"+i);
	     }
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public IIcon getIcon(int side, int metadata)
	    {
	    	
	        return lvl[metadata];
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
	    {
	    	double d0;
	        double d1;
	        double d2;
	        double d3;
	        double d4;
	        double d5;
	        for(int i = 1; i <= radius; i++)
	        {
	        	if(p_149734_5_.nextInt(5) == 0)
	        	{
		        	 d0 = (double)((float)p_149734_2_ + p_149734_5_.nextFloat());
		             d1 = (double)((float)p_149734_3_ + i + p_149734_5_.nextFloat());
		             d2 = (double)((float)p_149734_4_ + p_149734_5_.nextFloat());
		             d3 = 0.0D;
		             d4 = 0.0D;
		             d5 = 0.0D;
		        	p_149734_1_.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
	        	}
	        }
	    
	    }

	 public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	    {
	        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
	        p_149726_1_.setBlockMetadataWithNotify(p_149726_2_, p_149726_3_, p_149726_4_, 0,2);
	    }
	 
	 public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	 {
	       
	       p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 0, 2);

	    }
	

	

}
