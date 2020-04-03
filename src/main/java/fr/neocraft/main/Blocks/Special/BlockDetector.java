package fr.neocraft.main.Blocks.Special;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.BlockBasic;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BuilderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDetector extends BlockBasic
{
	 public BlockDetector(Material p_i45394_1_, int level) {
		super(p_i45394_1_, level);
	
	}

	@SideOnly(Side.CLIENT)
	    private IIcon face;

		
		
		 public void registerBlockIcons(IIconRegister p_149651_1_)
		    {
		        this.face = p_149651_1_.registerIcon(neoreference.MOD_ID + ":BlockDetector_face");
		        this.blockIcon = p_149651_1_.registerIcon(neoreference.MOD_ID + ":BlockDetector_default");
		    }
		 
		 
		 @SideOnly(Side.CLIENT)
		    public IIcon getIcon(int side, int meta)
		    {

				if(meta == side)
				{
					return this.face;
				}
			 
			 return this.blockIcon;
			 
		    }
   
  
   public int tickRate(World p_149738_1_)
   {
       return 10;
   }
   @Override
   public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
   {
       super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
       
   }

  
   @Override
   public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
   {
       int l = BlockPistonBase.determineOrientation(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_);
       p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
   }
  
   public boolean hasComparatorInputOverride()
   {
       return true;
   }

   public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
   {
       return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
   }
   
   public boolean canProvidePower()
	 {
	     return true;
	 }
	 
	 public int isProvidingStrongPower(IBlockAccess p_149748_1_, int p_149748_2_, int p_149748_3_, int p_149748_4_, int p_149748_5_)
	 {
	     return this.isProvidingWeakPower(p_149748_1_, p_149748_2_, p_149748_3_, p_149748_4_, p_149748_5_);
	 }
	 
	 public int isProvidingWeakPower(IBlockAccess w, int x, int y, int z, int p_149709_5_)
	 {
		 int l =w.getBlockMetadata(x, y, z);
		 int y2 = 0, x2 = 0, z2 = 0;
		 switch(l) 
		 {
		 	case 0:
		 		// return w.getBlock(x, y-1, z).isBlockSolid(w, x, y-1, z, 1) ? 10 : 0;
		 		
		 		y =-1;
		 		break;
		 	case 1:
		 		//return w.getBlock(x, y+1, z) .isBlockSolid(w, x, y+1, z, 0) ? 10 : 0;
		 		y=+1;
		 		break;
		 	case 2:
		 		z=-1;
		 		//return w.getBlock(x, y, z-1).isBlockSolid(w, x, y, z-1, 3) ? 10 : 0;
		 		break;
		 	case 3:
		 		z=+1;
		 		//return w.getBlock(x, y, z+1).isBlockSolid(w, x, y, z+1, 2) ? 10 : 0;
		 		break;
		 	case 4:
		 		x =-1;
		 	//	return w.getBlock(x-1, y, z).isBlockSolid(w, x-1, y, z, 5) ? 10 : 0;
		 		break;
		 	case 5:
		 		x =+1;
		 		break; 
		 	//return w.getBlock(x+1, y-1, z).isBlockSolid(w, x+1, y, z, 4) ? 10 : 0;
		 	default:
		 		return 0;
		 }
		 if(w.getBlock(x+x2, y + y2, z + z2) instanceof BlockCrops )
		 {
			 if(l >= 7)
			 {
				 return 10;
			 }
		 }else if(!(w.getBlock(x+x2, y + y2, z + z2) instanceof BlockPistonExtension ) && !(w.getBlock(x+x2, y + y2, z + z2) instanceof BlockPistonBase) && !(w.getBlock(x+x2, y + y2, z + z2) instanceof BlockPistonMoving ) 	) {
			 return 10;
		 }
		 return 0;
		 
	 }
	 
	 @Override
	 public void setBlockBoundsBasedOnState(IBlockAccess w, int x, int y, int z)
	    {
		 int l =w.getBlockMetadata(x, y, z);
		 switch(l) 
		 {
		 	case 0:
		 		this.setBlockBounds(0.0F, 0.1F, 0.0F, 1.0F, 1.0F, 1.0F);
		 		break; // return w.getBlock(x, y-1, z) != Blocks.air ? 10 : 0;
		 	case 1:
		 		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9F, 1.0F);
		 		break; // return w.getBlock(x, y+1, z) != Blocks.air ? 10 : 0;
		 	case 2:
		 		this.setBlockBounds(0.0F, 0.0F, 0.1F, 1.0F, 1.0F, 1.0F);
		 		break; // return w.getBlock(x, y, z-1) != Blocks.air ? 10 : 0;
		 	case 3:
		 		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.9F);
		 		break; // return w.getBlock(x, y, z+1) != Blocks.air ? 10 : 0;
		 	case 4:
		 		this.setBlockBounds(0.1F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		 		break; // return w.getBlock(x-1, y, z) != Blocks.air ? 10 : 0;
		 	case 5:
		 		this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.9F, 1.0F, 1.0F);
		 		break; // return w.getBlock(x+1, y-1, z) != Blocks.air ? 10 : 0;
		 	default:
		 		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		 		break; // return 0;
		 }
	       
			
	       // this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
	    }
	 
	 @Override
	 public void onNeighborBlockChange(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_, Block b)
	 {
		 int l =p_149726_1_.getBlockMetadata(p_149726_2_, p_149726_3_, p_149726_4_);
		
		p_149726_1_.notifyBlocksOfNeighborChange(p_149726_2_, p_149726_3_ , p_149726_4_, this);
		      
		
		 
	 }

  
}
