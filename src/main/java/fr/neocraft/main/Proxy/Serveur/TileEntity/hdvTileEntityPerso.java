package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
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


public class hdvTileEntityPerso extends TileEntity implements IInventory
{
  private ItemStack[] contents = new ItemStack[28];
  public EntityPlayer player;
  private bdd bdd = main.getbdd();
  private World w;
  private boolean isRemote;
  public hdvContainerPerso Container;
  
  public hdvTileEntityPerso(EntityPlayer player, boolean isRemote, World w) {
    this.player = player;
    this.isRemote = isRemote;
    this.w = w;
  }
  
  public void OpenHdv()
  {
    if (!isRemote)
    {
      contents = new ItemStack[28];
      int id = bdd.GetFreeId();
      ResultSet result = bdd.query("SELECT * FROM " + bdd.getStringHdv() + " WHERE Player='" + player.getCommandSenderName() + "'", id);
      if (result != null) {
        try
        {
          int i = 1;
          String name = "";
          while (result.next())
          {






            contents[i] = SetNbtStack(new ItemStack(Item.getItemById(result.getInt("IdItem")), result.getInt("NombreItem"), result.getInt("IdItemDamage")), bdd.GetArrayString(result.getString("LoreItem")), bdd.GetArrayInt(result.getString("EnchantItemId")), bdd.GetArrayInt(result.getString("EnchantItemLvl")), result.getInt("Prix"), result.getInt("id")).setStackDisplayName(result.getString("NameItem") + EnumChatFormatting.DARK_GRAY + " | " + result.getInt("Prix") + "$");
            

            i++;
          }
        }
        catch (SQLException e)
        {
          e.printStackTrace();
        }
      }
      bdd.CloseFreeId(id);
    }
  }
  
  public ItemStack SetNbtStack(ItemStack stack, String[] l, int[] ench, int[] lvl,int prix, int id)
	{

		
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) nbt = new NBTTagCompound();


		if(lvl != null && ench != null)
		{
			if(stack.getItem() == Items.enchanted_book)
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
			 lore.appendTag(new NBTTagString(EnumChatFormatting.GRAY+"Prix: "+(prix * stack.stackSize)+"$"));
			 lore.appendTag(new NBTTagString(EnumChatFormatting.GRAY+"Sois "+prix+"$ par item."));
			 NBTTagCompound display = new NBTTagCompound();
				display.setTag("Lore", lore);
				nbt.setTag("display", display);
	

		nbt.setInteger("prix", prix);
		nbt.setInteger("id", id);

		

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
		    return "Mes stocks";
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
    if ((s > 0) && (s < 28))
    {
      return false;
    }
    return true;
  }
  
  public void AddHdv(int p) {
    if (getStackInSlot(0) != null)
    {
      ItemStack t = getStackInSlot(0);
      
      String enchId = "";
      String enchLvl = "";
      String Lore = "";
      NBTTagCompound nbt = t.getTagCompound();
      if (nbt != null)
      {
        if (nbt.getTag(("ench")) != null) {
          NBTTagList enchants = (NBTTagList)nbt.getTag("ench");
          
          if (enchants != null)
          {
            for (int i = 0; i < enchants.tagCount(); i++)
            {
              NBTTagCompound enchant = enchants.getCompoundTagAt(i);
              enchId = enchId + enchant.getInteger("id") + "&";
              enchLvl = enchLvl + enchant.getInteger("lvl") + "&";
            }
          }
        }
        if (nbt.getTag(("StoredEnchantments")) != null) {
	        NBTTagList enchants = (NBTTagList)nbt.getTag("StoredEnchantments");
	          
	          if (enchants != null)
	          {
	            for (int i = 0; i < enchants.tagCount(); i++)
	            {
	              NBTTagCompound enchant = enchants.getCompoundTagAt(i);
	              enchId = enchId + enchant.getInteger("id") + "&";
	              enchLvl = enchLvl + enchant.getInteger("lvl") + "&";
	            }
	          }
        }
        
        NBTTagCompound disp = nbt.getCompoundTag("display");
        if (disp != null) {
          NBTTagList lore = (NBTTagList)disp.getTag("Lore");
          if (lore != null)
          {
            for (int i = 0; i < lore.tagCount(); i++)
            {
              Lore = Lore + lore.getStringTagAt(i) + "&";
            }
          }
        }
      }
      String name = t.getDisplayName();
      if ((name == null) || (name == "") || (name == " "))
      {
        name = t.getItem().getUnlocalizedName().replace("'", " ");
      }
      if (bdd.execute("INSERT INTO " + bdd.getStringHdv() + "(`IdItem`, `IdItemDamage`, `NombreItem`, `NameItem`, `LoreItem`, `EnchantItemId`, `EnchantItemLvl`, `Player`, `Prix`) VALUES (" + 
        getInfoBdd(Item.getIdFromItem(t.getItem())) + "," + getInfoBdd(t.getItemDamage()) + "," + getInfoBdd(t.stackSize) + "," + getInfoBdd(name) + "," + getInfoBdd(Lore) + "," + getInfoBdd(enchId) + "," + getInfoBdd(enchLvl) + "," + getInfoBdd(player.getCommandSenderName()) + "," + getInfoBdd(p) + ")"))
      {
        contents = new ItemStack[28];
        OpenHdv();
      }
    }
  }
  
  private String getInfoBdd(String e)
  {
    if ((e != null) && (e != " ") && (e != ""))
    {
      return "'" + e + "'";
    }
    

    return "NULL";
  }
  


  private String getInfoBdd(int e)
  {
    return e + "";
  }

	@Override
	public void openInventory() {
		OpenHdv();	
	}
}
