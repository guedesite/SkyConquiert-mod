package fr.neocraft.main.Blocks.Special;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.BlockBasic;
import fr.neocraft.main.Blocks.Special.BlockFuturist.Data;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFuturistTerminalInput extends BlockFuturist {

	public BlockFuturistTerminalInput(Material p_i45394_1_, int level) {
		super(p_i45394_1_, level);
		
	}
	
	private IIcon reset, trueoutput, falseoutput;
	
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        boolean flag = (p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_) || p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_ + 1, p_149695_4_)) 
        		&&
        		
        		p_149695_1_.getBlock(p_149695_2_+1, p_149695_3_, p_149695_4_).isProvidingStrongPower(p_149695_1_, p_149695_2_+1, p_149695_3_, p_149695_4_, 5) != 0;
       
        if(flag)
        {
        	p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, this.tickRate(p_149695_1_));
        }

      
    }
	 public void updateTick(World w, int x, int y, int z, Random p_149674_5_)
	    {
	        if (!w.isRemote)
	        {
	            this.SendData(w, x, y, z, x, y, z, BlockFuturist.Data.Reset, 0);
	            AxisAlignedBB box = this.getCollisionBoundingBoxFromPool(w, x, y, z).expand(5D, 5D, 5D);
		        List list = w.getEntitiesWithinAABB(EntityPlayer.class, box);
		
		        if (list != null && !list.isEmpty())
		        {
		            for (int k = 0; k < list.size(); ++k)
		            {
		                Entity entity = (Entity)list.get(k);
		                if(entity instanceof EntityPlayer)
		                {
		                	 SoundManager.PlaySound(EnumSound.FuturAction.getSound(), (EntityPlayer)entity);
		                }
		            }
		        }
		
	        }
	    }
	
	 public int tickRate(World p_149738_1_)
	    {
	        return 4;
	    }
	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
		this.blockIcon = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistTerminalInput_default");  
		this.reset = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistTerminalInput_reset");  
		this.trueoutput = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistTerminalInput_true");  
		this.falseoutput = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistTerminalInput_false");  
    }
 
	 public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	    {
	        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
	        this.func_149938_m(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
	    }

	    private void func_149938_m(World p_149938_1_, int p_149938_2_, int p_149938_3_, int p_149938_4_)
	    {
	        if (!p_149938_1_.isRemote)
	        {
	         
	            p_149938_1_.setBlockMetadataWithNotify(p_149938_2_, p_149938_3_, p_149938_4_, 0, 2);
	       
	        
	        }
	    }
	    
	    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	    {
	     
	        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 0, 2);

	       
	    }
	
 @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		if(side == 5)
		{
			return this.reset;
		} else if(side == 2 ) {
			return this.falseoutput;
		}else if(side == 3)
		{
			return this.trueoutput;
		}else {
		 return this.blockIcon;
		}
	 
    }
 	@Override
 	public void RecieveData(World w,int x,  int y, int z, int lastx, int lasty, int lastz, Data type, int nbreq) {
		if(type.equals(BlockFuturist.Data.FalseOutPut))
		{
			
			w.setBlockMetadataWithNotify(x, y, z, 1, 2);
			w.notifyBlockOfNeighborChange(x, y, z-1, BlockMod.Barrier);
			
			
			 AxisAlignedBB box = this.getCollisionBoundingBoxFromPool(w, x, y, z).expand(10D, 10D, 10D);
			        List list = w.getEntitiesWithinAABB(EntityPlayer.class, box);
			
			        if (list != null && !list.isEmpty())
			        {
			            for (int k = 0; k < list.size(); ++k)
			            {
			                Entity entity = (Entity)list.get(k);
			                if(entity instanceof EntityPlayer)
			                {
			                	 SoundManager.PlaySound(EnumSound.FuturAction.getSound(), (EntityPlayer)entity);
			                }
			            }
			        }
			
		
			final World w2 = w;
			final int x2 = x, y2 = y, z2 =z;
			new Timer().schedule(new TimerTask() {

				@Override
				public void run() {
					
					w2.setBlockMetadataWithNotify(x2, y2, z2, 0, 2);
					w2.notifyBlockOfNeighborChange(x2, y2, z2-1, BlockMod.FuturTerminalInput);
				}
				
			}, 500);
		} else if(type.equals(BlockFuturist.Data.TrueOutPut))
		{
			
			AxisAlignedBB box = this.getCollisionBoundingBoxFromPool(w, x, y, z).expand(10D, 10D, 10D);
	        List list = w.getEntitiesWithinAABB(EntityPlayer.class, box);
	
	        if (list != null && !list.isEmpty())
	        {
	            for (int k = 0; k < list.size(); ++k)
	            {
	                Entity entity = (Entity)list.get(k);
	                if(entity instanceof EntityPlayer)
	                {
	                	 SoundManager.PlaySound(EnumSound.FuturAction.getSound(), (EntityPlayer)entity);
	                }
	            }
	        }
			
			w.setBlockMetadataWithNotify(x, y, z, 1, 2);
			w.notifyBlockOfNeighborChange(x, y, z+1, this);
			final World w2 = w;
			final int x2 = x, y2 = y, z2 =z;
			new Timer().schedule(new TimerTask() {

				@Override
				public void run() {
					
					w2.setBlockMetadataWithNotify(x2, y2, z2, 0, 2);
					w2.notifyBlockOfNeighborChange(x2, y2, z2+1,BlockMod.FuturTerminalInput);
				}
				
			}, 500);
		}
		
	}
 
	 public boolean canProvidePower()
	 {
	     return true;
	 }
	 
	 public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
	 {
	     return this.isProvidingWeakPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_);
	 }
	 
	 public int isProvidingWeakPower(IBlockAccess p_149709_1_, int p_149709_2_, int p_149709_3_, int p_149709_4_, int p_149709_5_)
	 {
		 if(p_149709_1_.getBlockMetadata(p_149709_2_, p_149709_3_, p_149709_4_) == 1 || p_149709_1_.getBlockMetadata(p_149709_2_, p_149709_3_, p_149709_4_) == 2)
		 {
			 return 2;
		 }
		 else {
			 return 0;
		 }
		 
	 }

}
