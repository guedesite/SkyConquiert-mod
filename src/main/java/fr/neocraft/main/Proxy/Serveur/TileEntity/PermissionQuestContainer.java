package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.util.ArrayList;
import java.util.List;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PermissionQuestContainer extends Container{

	private final NeoPermissionTileEntity NeoTile;
	private final World w;
	private final fr.neocraft.main.bdd bdd = main.getbdd();
	private final NeoChat C = main.getChat();
	private int[] infoSlot = new int[] {1, 3, 2, 0, 4, 5};
	private int nb = 0;
	private int[] nbStat = new int[] { 2,3,12};
	private Stage[] stage;
    public List inventoryItemStacks = new ArrayList();
    public List inventorySlots = new ArrayList();
    private String[] base = {"enderpearl","interact","attaque","harvest","bonemeal","container","block"};
    
    public  PermissionQuestContainer(NeoPermissionTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p, int i)
    {
    	this.stage = RegisterStage.getStageArrayWithIdArray(PlayerStats.get(p).idStage);
        this.NeoTile = tile;
        this.w = w;
        this.nb = i;
        tile.openInventory();
       this.addSlotToContainer(new Slot(tile, 0, 63, 22));
       this.addSlotToContainer(new Slot(tile, 1, 144, 22));
       this.addSlotToContainer(new Slot(tile, 2, 63, 49));
       this.addSlotToContainer(new Slot(tile, 3, 144, 49));
       this.addSlotToContainer(new Slot(tile, 4, 63, 76));
       this.addSlotToContainer(new Slot(tile, 5, 144, 76));
       this.addSlotToContainer(new Slot(tile, 6, 104, 103));
       

    }
  
    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return this.NeoTile.isUseableByPlayer(player);
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        return null;
    }
  
    @Override
    public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
    {
    	if(!this.w.isRemote)
    	{
	    	ItemStack stack = this.NeoTile.getStackInSlot(p_75144_1_);
	    	if(stack != null)
	    	{
		    	if(stack.getItem().getUnlocalizedName().contains("14"))
		    	{
		    		PlayerStats stat = PlayerStats.get(p_75144_4_);
		    		if(this.nb == 0)
		    		{
		    			for(Stage e:stage)
		    			{
		    				if(e != null)
		    				{
		    					e.removePermAll(base[p_75144_1_]);
		    				}
		    			}
		    		}
		    		else if(this.nb == 1)
		    		{
		    			for(Stage e:stage)
		    			{
		    				if(e != null)
		    				{
		    					e.removePermMember(base[p_75144_1_]);
		    				}
		    			}
		    		}
		    		else {
		    			for(Stage e:stage)
		    			{
		    				if(e != null)
		    				{
		    					e.removePermAllie(base[p_75144_1_]);
		    				}
		    			}
		    		}
		    		p_75144_4_.openGui(main.instance, nbStat[nb], p_75144_4_.worldObj,(int) p_75144_4_.posX, (int)p_75144_4_.posY,(int) p_75144_4_.posZ);
		    	
		    	}
		    	else if(stack.getItem().getUnlocalizedName().contains("15"))
		    	{
		    		PlayerStats stat = PlayerStats.get(p_75144_4_);
		    		if(this.nb == 0)
		    		{

		    			for(Stage e:stage)
		    			{
		    				if(e != null)
		    				{
		    					e.addPermAll(base[p_75144_1_]);
		    				}
		    			}
		    		}
		    		else if(this.nb == 1)
		    		{
		    			for(Stage e:stage)
		    			{
		    				if(e != null)
		    				{
		    					e.addPermMember(base[p_75144_1_]);
		    				}
		    			}
		    		}else
		    		{
		    			for(Stage e:stage)
		    			{
		    				if(e != null)
		    				{
		    					e.addPermAllie(base[p_75144_1_]);
		    				}
		    			}
		    		}
		    		p_75144_4_.openGui(main.instance, nbStat[nb], p_75144_4_.worldObj,(int) p_75144_4_.posX, (int)p_75144_4_.posY,(int) p_75144_4_.posZ);
		    	
		    	}
		        return null;
	    	}
    	}
    	return null;
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
       this.NeoTile.closeInventory();
    }

}
