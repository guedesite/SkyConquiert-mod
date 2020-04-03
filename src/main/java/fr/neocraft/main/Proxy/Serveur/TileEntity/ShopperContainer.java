package fr.neocraft.main.Proxy.Serveur.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShopperContainer extends Container{

	public ShopperTileEntity NeoTile;
	private final World w;

    
    public  ShopperContainer(ShopperTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p)
    {
        this.NeoTile = tile;
        this.w = w;
        tile.openInventory();
       this.addSlotToContainer(new ShopperSlot(tile, 0, 80, 14)); 
       this.bindPlayerInventory(inventory);
       }
       private void bindPlayerInventory(InventoryPlayer inventory)
       {

    	   int i;
           for(i = 0; i < 3; ++i)
           {
               for(int j = 0; j < 9; ++j)
               {
                   this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 91 + i * 18));
               }
           }

           for(i = 0; i < 9; ++i)
           {
               this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 149));
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
      return true;
    }
    
    @Override
    public boolean canDragIntoSlot(Slot p_94531_1_)
    {
        return true;
    }
    
    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
    	super.onContainerClosed(p_75134_1_);
       this.NeoTile.closeInventory();
    }
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        if(slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(slotIndex < this.NeoTile.getSizeInventory())
            {
                if(!this.mergeItemStack(itemstack1, this.NeoTile.getSizeInventory(), this.inventorySlots.size(), true))
                {
                   return null;
                }
            }

            else if(!this.mergeItemStack(itemstack1, 0, this.NeoTile.getSizeInventory(), false))
            {
                return null;
            }
            if(itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
    

}