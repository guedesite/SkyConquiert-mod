package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.util.HashSet;
import java.util.Set;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.Init.ItemMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CarteContainer extends Container{

	private final CarteTileEntity NeoTile;
    private int field_94535_f = -1;
    private int field_94536_g;
    private EntityPlayer p;
    private final Set field_94537_h = new HashSet();
	private final World w;
	private Set playerList = new HashSet();
	private final NeoChat C = new NeoChat();
	private int[] infoSlot = new int[] {1, 3, 2, 0, 4, 5};
	
	@Override
	public boolean isPlayerNotUsingContainer(EntityPlayer p_75129_1_)
    {
        return !this.playerList.contains(p_75129_1_);
    }
	@Override
	public void setPlayerIsPresent(EntityPlayer p_75128_1_, boolean p_75128_2_)
    {
        if (p_75128_2_)
        {
            this.playerList.remove(p_75128_1_);
        }
        else
        {
            this.playerList.add(p_75128_1_);
        }
    }
    public CarteContainer(CarteTileEntity p_i1812_2_, InventoryPlayer tile, World w, EntityPlayer p)
    {
        this.NeoTile = p_i1812_2_;
        this.NeoTile.container = this;
        this.w = w;
        this.p = p;
        this.setPlayerIsPresent(p, true);
        p_i1812_2_.openInventory();
        this.addSlotToContainer(new CarteSlot(p_i1812_2_, 0, 101, 77, 59));
      
        this.addSlotToContainer(new CarteSlot(p_i1812_2_, 1, 112, 35, 37));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 2, 15, 89, 36));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 3, 112, 141, 37));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 4, 210, 89, 36));

       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 5, 94, 18, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 6, 151, 18, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 7, 165, 60, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 8, 191, 76, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 9, 191, 123, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 10, 165, 138, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 11, 151, 179, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 12, 94, 179, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 13, 80, 137, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 14, 54, 123, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 15, 54, 76, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 16, 80, 60, 16));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 17, 98, 59, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 18, 98, 41, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 19, 115, 20, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 20, 134, 20, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 21, 151, 41, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 22, 151, 59, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 23, 162, 79, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 24, 176, 79, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 25, 162, 93, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 26, 176, 93, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 27, 190, 93, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 28, 162, 107, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 29, 176, 107, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 30, 190, 107, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 31, 162, 121, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 32, 176, 121, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 33, 151, 141, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 34, 151, 160, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 35, 134, 181, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 36, 115, 181, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 37, 98, 160, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 38, 98, 141, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 39, 86, 121, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 40, 72, 121, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 41, 86, 107, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 42, 72, 107, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 43, 58, 107, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 44, 86, 93, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 45, 72, 93, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 46, 58, 93, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 47, 86, 79, 13));
       this.addSlotToContainer(new CarteSlot(p_i1812_2_, 48, 72, 79, 13));
     
       
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
    @Override
    protected boolean mergeItemStack(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_)
    {
    	if(p_75135_1_.getItem() != ItemMod.Vote_Key)
    	{
    		return false;
    	}
    	else
    	{
    		return super.mergeItemStack(p_75135_1_, p_75135_2_, p_75135_3_, p_75135_4_);
    	}
    }

    
    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
    	
    }

}
