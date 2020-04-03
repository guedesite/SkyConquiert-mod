package fr.neocraft.main.Proxy.Serveur.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class PlanteurContainer extends Container
{

	private final PlanterTileEntity NeoTile;

   public PlanteurContainer(PlanterTileEntity tile, InventoryPlayer inventory)
   {
       this.NeoTile = tile;
       tile.openInventory();
       this.addSlotToContainer(new Slot(tile, 0, 80, 36));
       this.bindPlayerInventory(inventory);
   }
   
   protected Slot addSlotToContainer(Slot p_75146_1_)
   {
       p_75146_1_.slotNumber = this.inventorySlots.size();
       this.inventorySlots.add(p_75146_1_);
       this.inventoryItemStacks.add((Object)null);
       return p_75146_1_;
   }

   @Override
   public boolean canInteractWith(EntityPlayer player)
   {
       return this.NeoTile.isUseableByPlayer(player);
   }
   private void bindPlayerInventory(InventoryPlayer inventory)
   {

   	int i;
       for(i = 0; i < 3; ++i)
       {
           for(int j = 0; j < 9; ++j)
           {
               this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 86 + i * 18));
           }
       }

       for(i = 0; i < 9; ++i)
       {
           this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 144));
       }
   }
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
