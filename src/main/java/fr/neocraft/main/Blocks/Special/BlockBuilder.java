package fr.neocraft.main.Blocks.Special;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BuilderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistryDefaulted;
import net.minecraft.world.World;

public class BlockBuilder extends Block
{
	 @SideOnly(Side.CLIENT)
	    private IIcon top;

		public BlockBuilder(Material material)
	    {
	    	super(material);
	    	

	    }
		
		 public void registerBlockIcons(IIconRegister p_149651_1_)
		    {
		        this.top = p_149651_1_.registerIcon(neoreference.MOD_ID + ":Builder_top");
		        this.blockIcon = p_149651_1_.registerIcon(neoreference.MOD_ID + ":Planteur_default");
		    }
		 
		 @SideOnly(Side.CLIENT)
		    public IIcon getIcon(int side, int meta)
		    {
			 	if(5 < meta)
			 	{
			 		meta -= 6;
			 	}
			 	
				if(meta == side)
				{
					return this.top;
				}
			 
			 return this.blockIcon;
			 
		    }
    
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true; // signale que le bloc a une entité
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new BuilderTileEntity(); // indique quelle est l'entité de bloc
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
        {
            return true;
        }
        else
        {
            player.openGui(main.instance, 14, world, x, y, z);
            return true;
        }
    }
    
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntity tileentity = world.getTileEntity(x, y, z);

        if(tileentity instanceof IInventory)
        {
            IInventory inv = (IInventory)tileentity;
            for(int i1 = 0; i1 < inv.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = inv.getStackInSlot(i1);

                if(itemstack != null)
                {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for(float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int j1 = world.rand.nextInt(21) + 10;

                        if(j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);

                        if(itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }
    public int tickRate(World p_149738_1_)
    {
        return 4;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
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
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
            this.func_149941_e(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_, p_149674_1_.getBlockMetadata(p_149674_2_,p_149674_3_,p_149674_4_));
        }
    }
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        boolean flag = p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_) || p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_ + 1, p_149695_4_);
        int l = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_);

        if (flag && l < 6)
        {
            p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, this.tickRate(p_149695_1_));
            p_149695_1_.setBlockMetadataWithNotify(p_149695_2_, p_149695_3_, p_149695_4_, l + 6 ,2);

        } else if(5 < l && !flag){
        	 p_149695_1_.setBlockMetadataWithNotify(p_149695_2_, p_149695_3_, p_149695_4_, l - 6 ,2);
        }
      
    }
    protected void func_149941_e(World w, int x, int y, int z, int meta)
    {
    	
    	BlockSourceImpl blocksourceimpl = new BlockSourceImpl(w, x, y, z);
        BuilderTileEntity tileentitydispenser = (BuilderTileEntity)blocksourceimpl.getBlockTileEntity();

        if(5 < meta )
        {
        	meta -= 6;
        }
        if (tileentitydispenser != null)
        {
        	boolean flag  = false;
        	for(int i = 0; i<tileentitydispenser.GetDistantSlotInv().length; i++)
        	{
        		if(tileentitydispenser.GetDistantSlotInv()[i] != null)
        		{
        			flag = true;
        		}
        	}
        	if (!flag)
            {
        		
        	
                boolean first = false;
                switch(meta) {
                	case 0:
                		for(int i = 1; i < 9; i++)
               			{
                			if(w.getBlock(x, y-i, z) == BlockMod.StaticBlock) {
                				return;
                			}
               				if(canPushBlock(w, x, y - i, z))
               				{
               					tileentitydispenser.setInventorySlotContents(i-1, 
               							processBlock(w, x, y - i, z));
               				}
               			}
                		break;
                	case 1 :
                		for(int i = 1; i < 9; i++)
               			{
                			if(w.getBlock(x, y+i, z) == BlockMod.StaticBlock) {
                				return;
                			}
                			if(canPushBlock(w, x, y + i, z))
               				{
               					tileentitydispenser.setInventorySlotContents(i-1, 
               							processBlock(w, x, y + i, z));
               				}
               			}
                		break;
                	case 2:
                		for(int i = 1; i < 9; i++)
               			{
                			if(w.getBlock(x, y, z-i) == BlockMod.StaticBlock) {
                				return;
                			}
                			if(canPushBlock(w, x, y, z-i))
               				{
               					tileentitydispenser.setInventorySlotContents(i-1, 
               							processBlock(w, x, y , z-i));
               				}
               			}
                		break;
                	case 3:
                		for(int i = 1; i < 9; i++)
               			{
                			if(w.getBlock(x, y, z+i) == BlockMod.StaticBlock) {
                				return;
                			}
                			if(canPushBlock(w, x, y, z+i))
               				{
               					tileentitydispenser.setInventorySlotContents(i-1, 
               							processBlock(w, x, y, z+i));
               				}
               			}
                		break;
                	case 4:
                		for(int i = 1; i < 9; i++)
               			{
                			if(w.getBlock(x-i, y, z) == BlockMod.StaticBlock) {
                				return;
                			}
                			if(canPushBlock(w,x-i, y, z))
               				{
               					tileentitydispenser.setInventorySlotContents(i-1, 
               							processBlock(w, x-i, y, z));
               				}
               			}
                		break;
                	case 5:
                		
                		for(int i = 1; i < 9; i++)
               			{
                			if(w.getBlock(x+i, y, z) == BlockMod.StaticBlock) {
                				return;
                			}
                			if(canPushBlock(w, x+i, y, z))
               				{
               					tileentitydispenser.setInventorySlotContents(i-1, 
               							processBlock(w, x+i, y, z));
               				}
               			}
                		break;
                	default:
                		System.out.println("nope: "+meta);
                }
                
              
            }
            else 
            {
            	
            	Block b;
            	int size = tileentitydispenser.GetDistantSlotInv().length;
               switch(meta) {
               		case 0:
               			for(int i = 1; i < (size+1); i++)
               			{
               				if(w.getBlock(x, y-i, z) == BlockMod.StaticBlock) {
                				return;
                			}
               				ItemStack s = tileentitydispenser.getStackInSlot(i-1);
               				if(s != null && s.getItem() != null)
               				{
               					b = Block.getBlockFromItem(s.getItem());
               					if(processBlock2(w, x, y-i, z, b, s.getItemDamage())) {
               						tileentitydispenser.setInventorySlotContents(i-1, (ItemStack) null);
               					}
               				}
               			}
               			break;
               		case 1:
               			
               			for(int i = 1; i < (size+1); i++)
               			{
               				if(w.getBlock(x, y+i, z) == BlockMod.StaticBlock) {
                				return;
                			}
               				ItemStack s = tileentitydispenser.getStackInSlot(i-1);
               				if(s != null && s.getItem() != null)
               				{
               					b = Block.getBlockFromItem(s.getItem());
               					if(processBlock2(w, x, y+i, z, b, s.getItemDamage())) {
               						tileentitydispenser.setInventorySlotContents(i-1, (ItemStack) null);
               					}
               				}
               			}
               			break;
               		case 2:
               			for(int i = 1; i < (size+1); i++)
               			{
               				
               				if(w.getBlock(x, y, z-i) == BlockMod.StaticBlock) {
                				return;
                			}
               				ItemStack s = tileentitydispenser.getStackInSlot(i-1);
               				if(s != null && s.getItem() != null)
               				{
               					b = Block.getBlockFromItem(s.getItem());
               					if(processBlock2(w, x, y, z-i, b, s.getItem().getDamage(s))) {
               						tileentitydispenser.setInventorySlotContents(i-1, (ItemStack) null);
               					}
               				}
               			}
               			break;
               		case 3:
               			for(int i = 1; i < (size+1); i++)
               			{
               				
               				if(w.getBlock(x, y, z+i) == BlockMod.StaticBlock) {
                				return;
                			}
               				ItemStack s = tileentitydispenser.getStackInSlot(i-1);
               				if(s != null && s.getItem() != null)
               				{
               					b = Block.getBlockFromItem(s.getItem());
               					if(processBlock2(w, x, y, z+i, b, s.getItemDamage())) {
               						tileentitydispenser.setInventorySlotContents(i-1, (ItemStack) null);
               					}
               				}
               			}
               			break;
               		case 4:
               			for(int i = 1; i < (size+1); i++)
               			{
               				
               				if(w.getBlock(x-i, y, z) == BlockMod.StaticBlock) {
                				return;
                			}
               				ItemStack s = tileentitydispenser.getStackInSlot(i-1);
               				if(s != null && s.getItem() != null)
               				{
               					b = Block.getBlockFromItem(s.getItem());
               					if(processBlock2(w, x-i, y, z, b, s.getItemDamage())) {
               						tileentitydispenser.setInventorySlotContents(i-1, (ItemStack) null);
               					}
               				}
               			}
               			break;
               		case 5:
               			for(int i = 1; i < (size+1); i++)
               			{
               				
               				if(w.getBlock(x+i, y, z) == BlockMod.StaticBlock) {
                				return;
                			}
               				ItemStack s = tileentitydispenser.getStackInSlot(i-1);
               				if(s != null && s.getItem() != null)
               				{
               					b = Block.getBlockFromItem(s.getItem());
               					if(processBlock2(w, x+i, y, z, b, s.getItemDamage())) {
               						tileentitydispenser.setInventorySlotContents(i-1, (ItemStack) null);
               					}
               				}
               			}
               			break;
               }
            }
            
        }
    }
    
	
    
    private static ItemStack processBlock(World w, int x, int y, int z)
    {
    	ItemStack s = new ItemStack(w.getBlock(x, y, z),1,w.getBlockMetadata(x, y, z) );
    	w.setBlockToAir(x, y, z);
    	return s;
    }
    
    private static boolean processBlock2(World w, int x, int y, int z, Block b, int meta)
    {
    	if(b != Blocks.air && 
          !(b.hasTileEntity(meta)) &&
          !(b instanceof BlockCrops) && 
          b.renderAsNormalBlock())
    	{
    		w.setBlock(x, y, z,b);
    		w.setBlockMetadataWithNotify(x, y, z, meta, 3);
    		return true;
    	}
    		
    	
    	
    	return false;
    }
   
  
    private static boolean canPushBlock(World p_150080_1_, int p_150080_2_, int p_150080_3_, int p_150080_4_)
    {
      
        Block b = p_150080_1_.getBlock(p_150080_2_, p_150080_3_,p_150080_4_);
       
            return 
            
          b != Blocks.air && 
          !(b.hasTileEntity(p_150080_1_.getBlockMetadata(p_150080_2_, p_150080_3_, p_150080_4_))) &&
          !(b instanceof BlockCrops) && 
          b.renderAsNormalBlock();
    
    }
   
    
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = BlockPistonBase.determineOrientation(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_);
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);

       
    }
    
    public static EnumFacing func_149937_b(int p_149937_0_)
    {
        return EnumFacing.getFront(p_149937_0_ & 7);
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
    {
        return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
    }
    
   
}
