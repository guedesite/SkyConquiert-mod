package fr.neocraft.main.Proxy.Serveur.TileEntity;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ShopperPnjContainer extends Container{

	public ShopperPnjTileEntity NeoTile;
	private final World w;

    
    public  ShopperPnjContainer(ShopperPnjTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p)
    {
        this.NeoTile = tile;
        this.w = w;
        tile.openInventory();
        int p1=0, p2= 0;
        for(int i = 0; i< 8; i++)
        {
        	
        	for(int o = 0; o < 6; o++)
        	{
	        	
	        	if(p1 == 3)
	        	{
	        		this.addSlotToContainer(new ShopperSlot(tile, p2, 8+(29*p1), 24+(i*18)));
	        	}
	        	else
	        	{
	        		this.addSlotToContainer(new ShopperSlot(tile, p2, 8+(29*p1), 24+(i*18)));
	        	}
	        	p1++;
	        	p2++;
        	}
        	p1 = 0;
        }
       this.bindPlayerInventory(inventory);
       }
      
	private void bindPlayerInventory(InventoryPlayer inventory)
       {

    	   int i;
           for(i = 0; i < 3; ++i)
           {
               for(int j = 0; j < 9; ++j)
               {
                   this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 174 + i * 18));
               }
           }

           for(i = 0; i < 9; ++i)
           {
               this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 232));
           }
       }
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.NeoTile.isUseableByPlayer(player);
    }
    

    @Override
    public boolean mergeItemStack(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_)
    {
      return false;
    }
    
    @Override
    public boolean canDragIntoSlot(Slot p_94531_1_)
    {
        return false;
    }
    
    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
    	super.onContainerClosed(p_75134_1_);
    	
    	w.removeTileEntity(this.NeoTile.xCoord, this.NeoTile.yCoord,  this.NeoTile.zCoord);
       this.NeoTile.closeInventory();
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        return null;
    }
    
    @Override
    public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
    {
    	if(!this.w.isRemote && this.NeoTile.getStackInSlot(p_75144_1_) != null)
    	{
    		NBTTagCompound nbt = this.NeoTile.getStackInSlot(p_75144_1_).getTagCompound();
			   if(nbt == null) { System.out.println("stack null"); return null; }
			   int nb = nbt.getInteger("nombre");
			   int nbnombre = 0;
	    	ItemStack stack = new ItemStack(this.NeoTile.getStackInSlot(p_75144_1_).getItem(), nb,  nbt.getInteger("damage"));
	    	Item it = stack.getItem();
	    	int dmg = stack.getItemDamage();
	    	PlayerStats stat = PlayerStats.get(p_75144_4_);
	    		InventoryPlayer inv = p_75144_4_.inventory;
	    			for(int i =0; i < inv.getSizeInventory(); i++)
	    			{
	    				if(inv.getStackInSlot(i) != null)
	    				{
	    					if(inv.getStackInSlot(i).getItem() == it && 0 < nb  && inv.getStackInSlot(i).getItemDamage() == dmg)
	    					{
	    						if(inv.getStackInSlot(i).stackSize <= nb)
	    						{
	    							nbnombre += inv.getStackInSlot(i).stackSize;
	    							nb -= inv.getStackInSlot(i).stackSize;
	    							inv.setInventorySlotContents(i, null);
	    						}
	    						else
	    						{
	    							nbnombre += nb;
	    							inv.getStackInSlot(i).splitStack(nb);
	    							nb = 0;
	    						}
	    					}
	    				}
	    			}
	    			if(nbnombre != 0)
	    			{
	    				p_75144_4_.openGui(main.instance,6, p_75144_4_.worldObj,(int) this.NeoTile.xCoord, (int)this.NeoTile.yCoord,(int) this.NeoTile.zCoord);
	    				this.NeoTile.Entity.addAchat(nbnombre, it,dmg);
	    				stat.giveMoney((nbt.getInteger("prixfinal") / nbt.getInteger("nombre")) *  nbnombre, p_75144_4_);
	    			}
	        return null;
    	}
    	return null;
    }
}