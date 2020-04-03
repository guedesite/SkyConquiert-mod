package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.neocraft.main.main;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class hdvTileEntityPublic extends TileEntity implements IInventory
{

    private ItemStack[] contents = new ItemStack[72];
    public EntityPlayer player;
    private World w;
    private boolean isRemote;
    private fr.neocraft.main.bdd bdd = main.getbdd();
    public hdvContainerPublic Container;
    public int page = 1;
    public int type = 1;

    public hdvTileEntityPublic(EntityPlayer player, boolean isRemote, World w, int type) {
		this.player = player;
		this.isRemote = isRemote;
		this.w = w;
		this.type = type;
	}
    
    public void OpenHdv()
    {
		if(!this.isRemote)
		{
			
			contents = new ItemStack[72];
			ResultSet result = null;
	
			int id = bdd.GetFreeId();
			if(type == 1)
			{
				 result = bdd.query("SELECT * FROM "+bdd.getStringHdv()+" WHERE effect=0", id);
			}
			else
			{
				result = bdd.query("SELECT * FROM "+bdd.getStringHdv()+" WHERE effect=2", id);
			}
			if(result != null)
			{
				try {
					int i = 0, o = 0;
					
					String name = "";
					while(result.next())
					{
						if(i > 71 * page)
						{
							result.close();
							return;
						}else if(i >= 71 * (page-1))
						{
							
							this.contents[o] = SetNbtStack(
									new ItemStack(Item.getItemById(result.getInt("IdItem")), result.getInt("NombreItem"), result.getInt("IdItemDamage"))
									,bdd.GetArrayString(result.getString("LoreItem")),
									bdd.GetArrayInt(result.getString("EnchantItemId")),
									bdd.GetArrayInt(result.getString("EnchantItemLvl")), 
									result.getInt("Prix"),
									result.getInt("id"), result.getString("Player")).setStackDisplayName(result.getString("NameItem")+EnumChatFormatting.DARK_GRAY+" | "+result.getInt("Prix")+"$");
							o++;
						}
						i++;
						
					}
				} catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			bdd.CloseFreeId(id);
		}
    }
    
	@Override
	public void openInventory() {
		OpenHdv();
		
	}

	public ItemStack SetNbtStack(ItemStack stack, String[] l, int[] ench, int[] lvl,int prix, int id, String player)
	{

		
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) nbt = new NBTTagCompound();


		if(lvl != null && ench != null)
		{
			if(stack.getItem() == Items.enchanted_book || stack.getItem() == Items.book)
			{
				if (!nbt.hasKey("StoredEnchantments", 9))
		        {
					nbt.setTag("StoredEnchantments", new NBTTagList());
		        }
					NBTTagList nbttaglist = nbt.getTagList("StoredEnchantments", 10);
			        NBTTagCompound nbttagcompound;
					for(int o = 0; o < ench.length ; o++)
					{
		
		
							nbttagcompound = new NBTTagCompound();
					        nbttagcompound.setShort("id", (short)ench[o]);
					        nbttagcompound.setShort("lvl", (short)((byte)lvl[o]));
					        nbttaglist.appendTag(nbttagcompound);
					}
			}
			else
			{
				if (!nbt.hasKey("ench", 9))
		        {
					nbt.setTag("ench", new NBTTagList());
		        }
					NBTTagList nbttaglist = nbt.getTagList("ench", 10);
			        NBTTagCompound nbttagcompound;
				for(int o = 0; o < ench.length ; o++)
				{
	
	
						nbttagcompound = new NBTTagCompound();
				        nbttagcompound.setShort("id", (short)ench[o]);
				        nbttagcompound.setShort("lvl", (short)((byte)lvl[o]));
				        nbttaglist.appendTag(nbttagcompound);
				}
			}
		}
		
			NBTTagList lore = new NBTTagList();
			if(l != null && l.length > 0)
			{
			 for (int i = 0; i <  l.length; i++)
		       {
				 lore.appendTag(new NBTTagString(l[i]));
		       }
			}
			lore.appendTag(new NBTTagString(EnumChatFormatting.GRAY+"joueur: "+player));
			 lore.appendTag(new NBTTagString(EnumChatFormatting.GRAY+"Prix: "+(prix * stack.stackSize)+"$"));
			 lore.appendTag(new NBTTagString(EnumChatFormatting.GRAY+"Sois "+prix+"$ par item."));
			 NBTTagCompound display = new NBTTagCompound();
				display.setTag("Lore", lore);
				nbt.setTag("display", display);
	

		nbt.setInteger("prix", prix);
		nbt.setInteger("id", id);
		nbt.setString("player", player);

		

		stack.setTagCompound(nbt);
		return stack;
	}
	@Override
	public int getSizeInventory() {
		return this.contents.length;
	}

	@Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        return this.contents[slotIndex];
    }



	 @Override
	    public void setInventorySlotContents(int slotIndex, ItemStack stack)
	    {
		        this.contents[slotIndex] = stack; // met l'item stack dans le tableau
	
		        if(stack != null && stack.stackSize > this.getInventoryStackLimit()) // si la taille de l'item stack dépasse la limite maximum de l'inventaire
		        {
		            stack.stackSize = this.getInventoryStackLimit(); // on le remet sur la limite
		        }
	
		        this.markDirty(); // met à jour le tile entity
	    }

	 @Override
	    public String getInventoryName()
	    {
			 if(this.type == 1)
			 {
				 return I18n.format("neo.hotel");
			 }else {
				 return I18n.format("neo.marche");
			 }
	    }
	 

	public void NextPage()
	{
		page++;
		this.OpenHdv();
		
	}
	
	public void LastPage()
	{
		if(page > 1)
		{
			page--;
			this.OpenHdv();
		}
		
	}
	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
    public int getInventoryStackLimit()
    {
        return 64;
    }
	 @Override
	    public ItemStack decrStackSize(int slotIndex, int amount)
	    {
		  	if(slotIndex ==0)
		  	{
		        if(this.contents[slotIndex] != null) // si le contenu dans l'emplacement n'est pas null
		        {
		            ItemStack itemstack;
	
		            if(this.contents[slotIndex].stackSize <= amount) // si la quantit顥st inf곩eur o񠩧ale ࡣe qu'on souhaite retirer
		            {
		                itemstack = this.contents[slotIndex]; // la variable itemstack prends la valeur du contenu
		                this.contents[slotIndex] = null; // on retire ce qui est dans la variable contents
		                this.markDirty(); // met ࡪour le tile entity
		                return itemstack; // renvoie itemstack
		            }
		            else // sinon
		            {
		                itemstack = this.contents[slotIndex].splitStack(amount); // la fonction splitStack(quantit驠retire dans this.contents[slotIndex] le contenu et le met dans itemstack
	
		                if(this.contents[slotIndex].stackSize == 0) // au cas o񠬡 quantit顰asse ࠰ (ce qui ne devrait pas arriver en temps normal)
		                {
		                    this.contents[slotIndex] = null; // on met sur null, 衠귩te de se retrouver avec des itemstack bugu顱ui contiennent 0
		                }
		                this.markDirty(); // met ࡪour le tile entity
		                return itemstack; // renvoie itemstack
		            }
		        }
		        else // sinon si le contenu dans cette emplacement est null
		        {
		            return null; // renvoie null, puisqu'il n'y a rien dans cette emplacement
		        }
		  	}else // sinon si le contenu dans cette emplacement est null
	        {
	            return null; // renvoie null, puisqu'il n'y a rien dans cette emplacement
	        }
	    }

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
	
			return null;
 
	}
	  
	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean isItemValidForSlot(int s, ItemStack p_94041_2_) {
		if(s < 72)
		{
			return false;
		} else {
			return true;
		}
	}
	

}
	  