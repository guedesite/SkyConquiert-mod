package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class hdvContainerPublic extends Container{

	public final hdvTileEntityPublic NeoTile;
    private int field_94535_f = -1;
    private int field_94536_g;
    private EntityPlayer p;
    private final Set field_94537_h = new HashSet();
	private final World w;
	private Set playerList = new HashSet();
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
    public hdvContainerPublic(hdvTileEntityPublic tile, InventoryPlayer p_i1812_2_, World w, EntityPlayer p)
    {
        this.NeoTile = tile;
        this.NeoTile.Container = this;
        this.w = w;
        this.p = p;
        this.setPlayerIsPresent(p, true);

        this.bindPlayerInventory(p_i1812_2_, tile);
        tile.openInventory();
    }
    private void bindPlayerInventory(InventoryPlayer inventory, hdvTileEntityPublic tile)
    {

    	 for(int o = 0; o < 8; ++o)
         {
             for(int y = 0; y < 9; ++y)
             {
                 this.addSlotToContainer(new NeoSlotDeny(tile,y + o * 9, 17 + y * 17 ,28 + o * 17));
             }
         }
    	
    	int i;
        for(i = 0; i < 3; ++i)
        {
            for(int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 14 + j * 18 ,189 + i * 18));
            }
        }

        this.addSlotToContainer(new Slot(inventory, 0,185 , 189));
        this.addSlotToContainer(new Slot(inventory, 1,203 , 189));
        this.addSlotToContainer(new Slot(inventory, 2,221 , 189));
        
        this.addSlotToContainer(new Slot(inventory, 3,185 , 207));
        this.addSlotToContainer(new Slot(inventory, 4,203 , 207));
        this.addSlotToContainer(new Slot(inventory, 5,221 , 207));
        
        this.addSlotToContainer(new Slot(inventory, 6,185 , 225));
        this.addSlotToContainer(new Slot(inventory, 7,203 , 225));
        this.addSlotToContainer(new Slot(inventory, 8,221 , 225));
      
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
    	
    		return super.mergeItemStack(p_75135_1_, p_75135_2_, p_75135_3_, p_75135_4_);
    
    }

  
    public hdvTileEntityPublic getTile()
    {
    	return NeoTile;
    }
    
    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
    	if(!w.isRemote)
    	{
    		this.setPlayerIsPresent(p, false);
    	
    	}
    }
    public ItemStack slotClick(int id, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
    {
    	if(id == -999)
    	{
    		return null;
    	}
    	if(id < 72  && !p_75144_4_.worldObj.isRemote) 
    	{
    		NeoSlotDeny slot = (NeoSlotDeny) this.inventorySlots.get(id);
    		if(slot.getStack() != null)
    		{
    			if(ByHdv(slot.getStack(), p_75144_4_))
    			{
	    			SendChangeForEveryOne();
    			}
    		}
    		return null;
    		
    	}
    	else
    	{
    		return super.slotClick(id, p_75144_2_, p_75144_3_, p_75144_4_);
    	}
    
    }
    
    private boolean ByHdv(ItemStack stack, EntityPlayer p) {
    	NBTTagCompound nbt = stack.getTagCompound();
    	if(nbt != null )
    	{
    		String pseudo = nbt.getString("player");
    		if(!pseudo.equals(p.getCommandSenderName()))
    		{
	    		PlayerStats  stat = PlayerStats.get(p);
	    		int prix = nbt.getInteger("prix")*stack.stackSize;
	        	if(stat.AsMoney(prix) && main.getbdd().Exist("SELECT id FROM "+main.getbdd().getStringHdv()+" WHERE id="+nbt.getInteger("id")))
	        	{

	        		stat.removeMoney(prix, p);
	        		
	    			stack = RemoveLore(stack);
	        		if(!p.inventory.addItemStackToInventory(stack))
	        		{
	        			dropBlockAsItem(this.p.worldObj,(int) this.p.posX,(int) this.p.posY, (int)this.p.posZ, stack);
	        		}
	        	
	        		if(this.NeoTile.type == 1)
	        		{
	        			EntityPlayer p2 = RegisterStage.getPlayer(pseudo);
	        			if(!RemoveHdv(stack))
		        		{
		        			return false;
		        		}
		        		if(p2 != null )
		        		{
		        			PlayerStats.get(p2).giveMoney(prix, p2);
		        		} else {
		        			main.getbdd().update("UPDATE "+main.getbdd().getStringPlayer()+" SET `money`=money+"+prix+" WHERE pseudo='"+pseudo+"'");
		        		}
	        		}
	        		else
	        		{
	        			main.getbdd().update("UPDATE "+main.getbdd().getStringHdv()+" SET `effect`=1 WHERE id="+getIdStack(stack));
	        		}
	        		SendChangeForEveryOne();
	        		return true;
	        	}
	    		
    		}
    	}
    	return false;

	}
    private int getIdStack(ItemStack s)
    {
    	NBTTagCompound nbt = s.getTagCompound();
    	if(nbt != null)
    	{
    		return nbt.getInteger("id");
    	}return 0;
    }
    private ItemStack RemoveLore(ItemStack s)
    {
    	NBTTagCompound nbt = s.getTagCompound();
    	if(nbt != null)
    	{
	    	int prix = nbt.getInteger("prix");
	    	String player = nbt.getString("player");
	    	s.setStackDisplayName(s.getDisplayName().replace(EnumChatFormatting.DARK_GRAY+" | "+prix+"$", ""));
	    	NBTTagCompound disp = nbt.getCompoundTag("display");
	    	if (disp != null) 
	    	{
	    		NBTTagList lorenew = new NBTTagList();
	    		NBTTagList lore = (NBTTagList) disp.getTag("Lore") ;
	    		if (lore != null) 
	    		{
	    			for (int i = 0; i <lore.tagCount(); i++) 
	    			{
	    			
	    				if(!lore.getStringTagAt(i).equals(EnumChatFormatting.GRAY+"joueur: "+player) && !lore.getStringTagAt(i).equals(EnumChatFormatting.GRAY+"Prix: "+(prix * s.stackSize)+"$") && !lore.getStringTagAt(i).equals(EnumChatFormatting.GRAY+"Sois "+prix+"$ par item."))
	    				{
	    					lorenew.appendTag(new NBTTagString(lore.getStringTagAt(i)));
	    				}
					}
	    			disp.setTag("Lore", lorenew);
					nbt.setTag("display", disp);
	    		}
	    	}
	    	s.setTagCompound(nbt);
    	}
    	
    	return s;
    }
    protected void dropBlockAsItem(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_)
    {
        if (!p_149642_1_.isRemote && p_149642_1_.getGameRules().getGameRuleBooleanValue("doTileDrops") && !p_149642_1_.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
        {
       
            float f = 0.7F;
            double d0 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d2 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(p_149642_1_, (double)p_149642_2_ + d0, (double)p_149642_3_ + d1, (double)p_149642_4_ + d2, p_149642_5_);
            entityitem.delayBeforeCanPickup = 10;
            p_149642_1_.spawnEntityInWorld(entityitem);
        }
    }
	public boolean RemoveHdv(ItemStack i)
    {
    	NBTTagCompound nbt = i.getTagCompound();
    	if(nbt != null)
    	{
    		int id = nbt.getInteger("id");
    		if(id != 0)
    		{
    			if(main.getbdd().execute("DELETE FROM "+main.getbdd().getStringHdv()+" WHERE id="+id))
    			{
    				return true;
    			}
    		}
    		
    	}
    	return false;
    }
	private void SendChangeForEveryOne()
	{
		ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();
		 EntityPlayer p ;
		 while (li.hasNext()){
		    	p = ((EntityPlayer) li.next());
		    	if(p.openContainer != null && p.openContainer instanceof hdvContainerPublic)
		    	{
		    		((hdvContainerPublic) p.openContainer).NeoTile.OpenHdv();
		    	}
		    }
	}
}
