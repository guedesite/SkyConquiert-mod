package fr.neocraft.main.Blocks.Special;

import java.util.Random;

import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.TileEntity.PlanterTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBehaviorDispenseItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IIcon;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistryDefaulted;
import net.minecraft.world.World;

public class BlockPlanteur extends Block
{
	private IIcon top;
	public static final IRegistry dispenseBehaviorRegistry = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
	public static final ItemStack DyeWhite = new ItemStack(Items.dye, 1, 15);
		public BlockPlanteur(Material material)
	    {
	    	super(material);
	    	

	    }
		
		 public void registerBlockIcons(IIconRegister iiconRegister)
		    {
		        this.blockIcon = iiconRegister.registerIcon(neoreference.MOD_ID + ":Planteur_default");
		        this.top = iiconRegister.registerIcon(neoreference.MOD_ID + ":Planteur_top");
		    }
		 
		    public IIcon getIcon(int side, int metadata)
		    {
		    	if(side == 1)
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
        return new PlanterTileEntity(); // indique quelle est l'entité de bloc
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
        {
            return true;
        }
        else
        {
            player.openGui(main.instance, 13, world, x, y, z);
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
            Block block = p_149938_1_.getBlock(p_149938_2_, p_149938_3_, p_149938_4_ - 1);
            Block block1 = p_149938_1_.getBlock(p_149938_2_, p_149938_3_, p_149938_4_ + 1);
            Block block2 = p_149938_1_.getBlock(p_149938_2_ - 1, p_149938_3_, p_149938_4_);
            Block block3 = p_149938_1_.getBlock(p_149938_2_ + 1, p_149938_3_, p_149938_4_);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j())
            {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j())
            {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j())
            {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j())
            {
                b0 = 4;
            }

            p_149938_1_.setBlockMetadataWithNotify(p_149938_2_, p_149938_3_, p_149938_4_, b0, 2);
        }
    }
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
            this.func_149941_e(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
        }
    }
    public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
    {
        boolean flag = p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_) || p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_ + 1, p_149695_4_);
        int l = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_);
        boolean flag1 = (l & 8) != 0;

        if (flag && !flag1)
        {
            p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, this.tickRate(p_149695_1_));
            p_149695_1_.setBlockMetadataWithNotify(p_149695_2_, p_149695_3_, p_149695_4_, l | 8, 4);
        }
        else if (!flag && flag1)
        {
            p_149695_1_.setBlockMetadataWithNotify(p_149695_2_, p_149695_3_, p_149695_4_, l & -9, 4);
        }
    }
    protected void func_149941_e(World p_149941_1_, int p_149941_2_, int p_149941_3_, int p_149941_4_)
    {
    	
    	BlockSourceImpl blocksourceimpl = new BlockSourceImpl(p_149941_1_, p_149941_2_, p_149941_3_, p_149941_4_);
        PlanterTileEntity tileentitydispenser = (PlanterTileEntity)blocksourceimpl.getBlockTileEntity();

        if (tileentitydispenser != null)
        {
            int l = tileentitydispenser.func_146017_i();

            if (l < 0)
            {
                p_149941_1_.playAuxSFX(1001, p_149941_2_, p_149941_3_, p_149941_4_, 0);
            }
            else
            {
                ItemStack itemstack = tileentitydispenser.getStackInSlot(l);
                IBehaviorDispenseItem ibehaviordispenseitem = this.func_149940_a(itemstack);

	                if (ibehaviordispenseitem != IBehaviorDispenseItem.itemDispenseBehaviorProvider)
	                {
		                	if(p_149941_1_.getBlock(p_149941_2_, p_149941_3_ + 2, p_149941_4_) == Blocks.farmland || p_149941_1_.getBlock(p_149941_2_, p_149941_3_ + 2, p_149941_4_) == BlockMod.FarmLand) 
		                	{
		                		SetPlaceFarmBlock(p_149941_1_, p_149941_2_, p_149941_3_ + 1, p_149941_4_, itemstack, l, tileentitydispenser);
		                	}
		                	else if(p_149941_1_.getBlock(p_149941_2_, p_149941_3_ + 1, p_149941_4_) == Blocks.farmland || p_149941_1_.getBlock(p_149941_2_, p_149941_3_ + 1, p_149941_4_) == BlockMod.FarmLand) 
		                	{
		                		SetPlaceFarmBlock(p_149941_1_, p_149941_2_, p_149941_3_, p_149941_4_, itemstack, l, tileentitydispenser);
		                	}
		                	
		                	else if(p_149941_1_.getBlock(p_149941_2_, p_149941_3_ + 2, p_149941_4_) == Blocks.dirt || p_149941_1_.getBlock(p_149941_2_, p_149941_3_ + 2, p_149941_4_) == Blocks.grass) 
		                	{
		                		SetPlaceFarmLand(p_149941_1_, p_149941_2_, p_149941_3_ + 1, p_149941_4_, itemstack, l, tileentitydispenser);
		                	}
		                	else if(p_149941_1_.getBlock(p_149941_2_, p_149941_3_ + 1, p_149941_4_) == Blocks.dirt || p_149941_1_.getBlock(p_149941_2_, p_149941_3_ + 1, p_149941_4_) == Blocks.grass) 
		                	{
		                		SetPlaceFarmLand(p_149941_1_, p_149941_2_, p_149941_3_, p_149941_4_, itemstack, l, tileentitydispenser);
		                	}
		                	
                	}
                }
            }
        }
   
    private void SetPlaceFarmBlock(World p_149941_1_, int p_149941_2_, int p_149941_3_, int p_149941_4_,
			ItemStack itemstack, int l, PlanterTileEntity tileentitydispenser) {
    	int  NE_y = p_149941_3_;
    	if(itemstack.getItem() == ItemMod.Radis_Seed && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, BlockMod.Radis_Culture); 
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	else if(itemstack.getItem() == ItemMod.Laitue && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, BlockMod.Laitue_Culture); 
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	else if(itemstack.getItem() == ItemMod.Tomate_Seeds && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, BlockMod.Tomate_Culture); 
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	
    	else if(itemstack.getItem() == ItemMod.Corn_Seeds && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air && p_149941_1_.getBlock(p_149941_2_, NE_y + 3, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 3, p_149941_4_, BlockMod.Corn_Culture_Up); 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, BlockMod.Corn_Culture_Down); 
    		
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	
    	else if(itemstack.getItem() == ItemMod.Aubergine_Seed && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air && p_149941_1_.getBlock(p_149941_2_, NE_y + 3, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 3, p_149941_4_, BlockMod.Aubergine_Culture_Up); 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, BlockMod.Aubergine_Culture_down); 
    		
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	
    	else if(itemstack.getItem() == Items.melon_seeds && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, Blocks.melon_stem); 
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	else if(itemstack.getItem() == Items.pumpkin_seeds && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, Blocks.pumpkin_stem); 
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	else if((itemstack.getItem() == Items.wheat_seeds || itemstack.getItem() == ItemMod.Neo_wheat_seeds) && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, BlockMod.Wheat_culture); 
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	else if((itemstack.getItem() == Items.carrot || itemstack.getItem() == ItemMod.Neo_Carrot) && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, BlockMod.Carrot_Culture); 
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	else if((itemstack.getItem() == Items.potato || itemstack.getItem() == ItemMod.Neo_Potato)&& p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 2, p_149941_4_, BlockMod.Potato_Culture); 
    		tileentitydispenser.setInventorySlotContents(l, itemstack.stackSize == 1 ? null : itemstack.splitStack(itemstack.stackSize - 1));
    		p_149941_1_.playAuxSFX(1000, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
    	else
    	{
    		p_149941_1_.playAuxSFX(1001, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
		
	}
    private void SetPlaceFarmLand(World p_149941_1_, int p_149941_2_, int p_149941_3_, int p_149941_4_,
			ItemStack itemstack, int l, PlanterTileEntity tileentitydispenser) {
    	int  NE_y = p_149941_3_;
    	
    	if(itemstack.getItem() == Items.wooden_hoe && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 1, p_149941_4_, Blocks.farmland); 
    		itemstack.setItemDamage(itemstack.getItemDamage() + 1);
    	}
    	else if(itemstack.getItem() == Items.stone_hoe && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 1, p_149941_4_, Blocks.farmland); 
    		itemstack.setItemDamage(itemstack.getItemDamage() + 1);
    	}
    	else if(itemstack.getItem() == Items.iron_hoe && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 1, p_149941_4_, Blocks.farmland); 
    		itemstack.setItemDamage(itemstack.getItemDamage() + 1);
    	}
    	else if(itemstack.getItem() == Items.golden_hoe && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 1, p_149941_4_, Blocks.farmland); 
    		itemstack.setItemDamage(itemstack.getItemDamage() + 1);
    	}
    	else if(itemstack.getItem() == Items.diamond_hoe && p_149941_1_.getBlock(p_149941_2_, NE_y + 2, p_149941_4_) == Blocks.air) { 
    		p_149941_1_.setBlock(p_149941_2_, NE_y + 1, p_149941_4_, Blocks.farmland); 
    		itemstack.setItemDamage(itemstack.getItemDamage() + 1);
    	}
    	else
    	{
    		p_149941_1_.playAuxSFX(1001, p_149941_2_, NE_y, p_149941_4_, 0);
    	}
		
	}



	protected IBehaviorDispenseItem func_149940_a(ItemStack p_149940_1_)
    {
        return (IBehaviorDispenseItem)dispenseBehaviorRegistry.getObject(p_149940_1_.getItem());
    }
   
    public boolean GetAcceptPlace(ItemStack itemstack)
    {
    	if(itemstack == DyeWhite)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
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
