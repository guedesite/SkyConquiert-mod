package fr.neocraft.main.Blocks.Special;

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockSpike  extends Block
{
	
	
	private String Stat = null;
	private float dmg = 1.0F;
	  public BlockSpike(String name, float dmg)
	  {
		  super(Material.iron);
		  this.Stat = name;
		  this.dmg = dmg;
		  this.setBlockTextureName(neoreference.MOD_ID + ":" + this.Stat + "Spike");
		  this.setBlockName(this.Stat + " Spike");
	  }
	  
	  @SideOnly(Side.CLIENT)
	  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check)
	  {  
	        list.add(EnumChatFormatting.BLUE + "damage : " + this.dmg);
	   }
	  
	
	 public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	    {
		 int l = p_149668_1_.getBlockMetadata(p_149668_2_, p_149668_3_, p_149668_4_) & 7;
	        if (l == 0)
	        {
	        	AxisAlignedBB.getBoundingBox(0.0625D, 1.0D, 0.0625D, 0.9375D, 0.625D, 0.9375D);
	        }
	        else if (l == 1)
	        {
	        	AxisAlignedBB.getBoundingBox(0.0D, 0.0625D, 0.0625D, 0.375D, 0.9375D, 0.9375D);
	        }
	        else if (l == 2)
	        {
	        	AxisAlignedBB.getBoundingBox(1.0D, 0.0625D, 0.0625D, 0.625D, 0.9375D, 0.9375D);
	        }
	        else if (l == 3)
	        {
	        	AxisAlignedBB.getBoundingBox(0.0625D, 0.0625D, 0.0D, 0.9375D, 0.9375D, 0.375D);
	        }
	        else if (l == 4)
	        {
	        	AxisAlignedBB.getBoundingBox(0.0625D, 0.0625D, 1.0D, 0.9375D, 0.9375D, 0.625D);
	        }
	        else if (l == 5)
	        {
	        	AxisAlignedBB.getBoundingBox(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.375D, 0.9375D);
	        }
	        return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
	    }
    

	  
	  @SideOnly(Side.CLIENT)
	    public float getAmbientOcclusionLightValue()
	    {
	        return 1.0F;
	    }
	  

	  public boolean isOpaqueCube() {
	        return false;
	    }

	    public boolean renderAsNormalBlock()
	    {
	        return false;
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public int getRenderType()
	    {
	        return ClientProxy.spikeRenderID;
	    }
	    
	    @SideOnly(Side.CLIENT)
	    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
	    {
	        return true;
	    }
	  
		
	  public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
	    if ((entity != null) && (!(entity instanceof EntityItem))) {
	      entity.attackEntityFrom(DamageSource.cactus, this.dmg);
	    
	    }
	  }
	    public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	    {
	        return p_149742_1_.isSideSolid(p_149742_2_ - 1, p_149742_3_, p_149742_4_, EAST,  true) ||
	               p_149742_1_.isSideSolid(p_149742_2_ + 1, p_149742_3_, p_149742_4_, WEST,  true) ||
	               p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ - 1, SOUTH, true) ||
	               p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_, p_149742_4_ + 1, NORTH, true) ||
	               p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ + 1, p_149742_4_, ForgeDirection.UP, true) ||
	               p_149742_1_.isSideSolid(p_149742_2_, p_149742_3_ - 1, p_149742_4_, ForgeDirection.DOWN, true) ||
	               func_150107_m(p_149742_1_, p_149742_2_, p_149742_3_ - 1, p_149742_4_);
	    }
	  
	    public MovingObjectPosition collisionRayTrace(World p_149731_1_, int p_149731_2_, int p_149731_3_, int p_149731_4_, Vec3 p_149731_5_, Vec3 p_149731_6_)
	    {
	        int l = p_149731_1_.getBlockMetadata(p_149731_2_, p_149731_3_, p_149731_4_) & 7;
	        if (l == 0)
	        {
	        	this.setBlockBounds(0.0625F, 1.0F, 0.0625F, 0.9375F, 0.625F, 0.9375F);
	        }
	        else if (l == 1)
	        {
	        	this.setBlockBounds(0.0F, 0.0625F, 0.0625F, 0.375F, 0.9375F, 0.9375F);
	        }
	        else if (l == 2)
	        {
	        	this.setBlockBounds(1.0F, 0.0625F, 0.0625F, 0.625F, 0.9375F, 0.9375F);
	        }
	        else if (l == 3)
	        {
	        	this.setBlockBounds(0.0625F, 0.0625F, 0.0F, 0.9375F, 0.9375F, 0.375F);
	        }
	        else if (l == 4)
	        {
	        	this.setBlockBounds(0.0625F, 0.0625F, 1.0F, 0.9375F, 0.9375F, 0.625F);
	        }
	        else if (l == 5)
	        {
	        	 this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.375F, 0.9375F);
	        }
	        return super.collisionRayTrace(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_, p_149731_5_, p_149731_6_);
	    }
	    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	    {
	        if (p_149726_1_.getBlockMetadata(p_149726_2_, p_149726_3_, p_149726_4_) == 0)
	        {
	        	if (p_149726_1_.isSideSolid(p_149726_2_, p_149726_3_ - 1, p_149726_4_, ForgeDirection.DOWN, true))
	            {
	                p_149726_1_.setBlockMetadataWithNotify(p_149726_2_, p_149726_3_, p_149726_4_, 0, 2);
	            }
	        	else if (p_149726_1_.isSideSolid(p_149726_2_ - 1, p_149726_3_, p_149726_4_, EAST, true))
	            {
	                p_149726_1_.setBlockMetadataWithNotify(p_149726_2_, p_149726_3_, p_149726_4_, 1, 2);
	            }
	            else if (p_149726_1_.isSideSolid(p_149726_2_ + 1, p_149726_3_, p_149726_4_, WEST, true))
	            {
	                p_149726_1_.setBlockMetadataWithNotify(p_149726_2_, p_149726_3_, p_149726_4_, 2, 2);
	            }
	            else if (p_149726_1_.isSideSolid(p_149726_2_, p_149726_3_, p_149726_4_ - 1, SOUTH, true))
	            {
	                p_149726_1_.setBlockMetadataWithNotify(p_149726_2_, p_149726_3_, p_149726_4_, 3, 2);
	            }
	            else if (p_149726_1_.isSideSolid(p_149726_2_, p_149726_3_, p_149726_4_ + 1, NORTH, true))
	            {
	                p_149726_1_.setBlockMetadataWithNotify(p_149726_2_, p_149726_3_, p_149726_4_, 4, 2);
	            }
	            else if (this.func_150107_m(p_149726_1_, p_149726_2_, p_149726_3_ - 1, p_149726_4_))
	            {
	                p_149726_1_.setBlockMetadataWithNotify(p_149726_2_, p_149726_3_, p_149726_4_, 5, 2);
	            }
	        }

	        this.func_150109_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
	    }
	    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	    {
	        this.func_150108_b(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, p_149695_5_);
	    }
	    protected boolean func_150108_b(World p_150108_1_, int p_150108_2_, int p_150108_3_, int p_150108_4_, Block p_150108_5_)
	    {
	        if (this.func_150109_e(p_150108_1_, p_150108_2_, p_150108_3_, p_150108_4_))
	        {
	            int l = p_150108_1_.getBlockMetadata(p_150108_2_, p_150108_3_, p_150108_4_);
	            boolean flag = false;

	            if (!p_150108_1_.isSideSolid(p_150108_2_ - 1, p_150108_3_, p_150108_4_, EAST, true) && l == 1)
	            {
	                flag = true;
	            }

	            if (!p_150108_1_.isSideSolid(p_150108_2_ + 1, p_150108_3_, p_150108_4_, WEST, true) && l == 2)
	            {
	                flag = true;
	            }

	            if (!p_150108_1_.isSideSolid(p_150108_2_, p_150108_3_, p_150108_4_ - 1, SOUTH, true) && l == 3)
	            {
	                flag = true;
	            }

	            if (!p_150108_1_.isSideSolid(p_150108_2_, p_150108_3_, p_150108_4_ + 1, NORTH, true) && l == 4)
	            {
	                flag = true;
	            }

	            if (!this.func_150107_m(p_150108_1_, p_150108_2_, p_150108_3_ - 1, p_150108_4_) && l == 5)
	            {
	                flag = true;
	            }

	            if (flag)
	            {
	                this.dropBlockAsItem(p_150108_1_, p_150108_2_, p_150108_3_, p_150108_4_, p_150108_1_.getBlockMetadata(p_150108_2_, p_150108_3_, p_150108_4_), 0);
	                p_150108_1_.setBlockToAir(p_150108_2_, p_150108_3_, p_150108_4_);
	                return true;
	            }
	            else
	            {
	                return false;
	            }
	        }
	        else
	        {
	            return true;
	        }
	    }
	  protected boolean func_150109_e(World p_150109_1_, int p_150109_2_, int p_150109_3_, int p_150109_4_)
	    {
	        if (!this.canPlaceBlockAt(p_150109_1_, p_150109_2_, p_150109_3_, p_150109_4_))
	        {
	            if (p_150109_1_.getBlock(p_150109_2_, p_150109_3_, p_150109_4_) == this)
	            {
	                this.dropBlockAsItem(p_150109_1_, p_150109_2_, p_150109_3_, p_150109_4_, p_150109_1_.getBlockMetadata(p_150109_2_, p_150109_3_, p_150109_4_), 0);
	                p_150109_1_.setBlockToAir(p_150109_2_, p_150109_3_, p_150109_4_);
	            }

	            return false;
	        }
	        else
	        {
	            return true;
	        }
	    }
	 // public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
	   // {
	       
	    //}


	    
	    private boolean func_150107_m(World p_150107_1_, int p_150107_2_, int p_150107_3_, int p_150107_4_)
	    {
	        if (World.doesBlockHaveSolidTopSurface(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_))
	        {
	            return true;
	        }
	        else
	        {
	            Block block = p_150107_1_.getBlock(p_150107_2_, p_150107_3_, p_150107_4_);
	            return block.canPlaceTorchOnTop(p_150107_1_, p_150107_2_, p_150107_3_, p_150107_4_);
	        }
	    }
	    
	    public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_, float p_149660_7_, float p_149660_8_, int p_149660_9_)
	    {
	        int j1 = p_149660_9_;
	        
	        if (p_149660_5_ == 1 && this.func_150107_m(p_149660_1_, p_149660_2_, p_149660_3_ - 1, p_149660_4_))
	        {
	            j1 = 5;
	           
	        }

	        if (p_149660_5_ == 2 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ + 1, NORTH, true))
	        {
	            j1 = 4;
	        }

	        if (p_149660_5_ == 3 && p_149660_1_.isSideSolid(p_149660_2_, p_149660_3_, p_149660_4_ - 1, SOUTH, true))
	        {
	            j1 = 3;
	        }

	        if (p_149660_5_ == 4 && p_149660_1_.isSideSolid(p_149660_2_ + 1, p_149660_3_, p_149660_4_, WEST, true))
	        {
	            j1 = 2;
	        }

	        if (p_149660_5_ == 5 && p_149660_1_.isSideSolid(p_149660_2_ - 1, p_149660_3_, p_149660_4_, EAST, true))
	        {
	            j1 = 1;
	            
	        }
	        return j1;
	    }

}

