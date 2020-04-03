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

public class BoxVoteContainer extends Container{

	private final BoxVoteTileEntity NeoTile;
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
    public BoxVoteContainer(BoxVoteTileEntity tile, InventoryPlayer p_i1812_2_, World w, EntityPlayer p)
    {
        this.NeoTile = tile;
        this.NeoTile.container = this;
        this.w = w;
        this.p = p;
        this.setPlayerIsPresent(p, true);
        tile.openInventory();
        this.addSlotToContainer(new BoxVoteSlot(tile, 0, 80, 4));
        this.addSlotToContainer(new BoxVoteSlot(tile, 1, 42, 36));
        this.addSlotToContainer(new BoxVoteSlot(tile, 2, 60, 36));
        this.addSlotToContainer(new BoxVoteSlot(tile, 3, 80, 36));
        this.addSlotToContainer(new BoxVoteSlot(tile, 4, 100, 36));
        this.addSlotToContainer(new BoxVoteSlot(tile, 5, 118, 36));
        this.bindPlayerInventory(p_i1812_2_);
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

    public void scroll()
    {
    	NeoTile.Scrolling();
    }
    
    public BoxVoteTileEntity getTile()
    {
    	return NeoTile;
    }
    
    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
    	if(!w.isRemote)
    	{
    		w.removeTileEntity(this.NeoTile.xCoord, this.NeoTile.yCoord,  this.NeoTile.zCoord);
    		this.setPlayerIsPresent(p, false);
    		Slot slot = (Slot)this.inventorySlots.get(0);
    		ItemStack stack = slot.getStack();
    		if(stack != null)
    		{
    			p_75134_1_.inventory.addItemStackToInventory(stack);
    		}
    	}
    }

}
