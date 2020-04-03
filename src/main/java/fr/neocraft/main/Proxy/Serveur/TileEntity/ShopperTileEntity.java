package fr.neocraft.main.Proxy.Serveur.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class ShopperTileEntity extends TileEntity implements IInventory{
	private int direction = 0;
	private ItemStack[] contents = new ItemStack[1];
	private int value = 1;
	private String owner = " ";
	
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.owner = compound.getString("owner");
        this.direction = compound.getInteger("direction");
        this.value = compound.getInteger("value");
         NBTTagList nbttaglist = compound.getTagList("Items", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND); // on obtient la liste de tags nomm�e Items
        this.contents = new ItemStack[1]; // on r�initialise le tableau
        for(int i = 0; i < nbttaglist.tagCount(); ++i) // i varie de 0 � la taille la liste
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i); // on lit le tag nbt
            int j = nbttagcompound1.getByte("Slot") & 255; // on lit � quel slot se trouve l'item stack

            if(j >= 0 && j < this.contents.length)
            {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1); // on lit l'item stack qui se trouve dans le tag
            }
        } 
    }

	
	 public String getowner()
	 {
		 return this.owner;
	 }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
    	 super.writeToNBT(compound);
    	 compound.setString("owner", this.owner);
        compound.setInteger("direction", this.direction);
        compound.setInteger("value", this.value);
      NBTTagList nbttaglist = new NBTTagList(); // on cr�� une nouvelle liste de tags
        for(int i = 0; i < this.contents.length; ++i) // i varie de 0 � la taille de notre tableau
        {
            if(this.contents[i] != null) // si l'item stack � l'emplacement i du tableau n'est pas null
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound(); // on cr�� un tag nbt
                nbttagcompound1.setByte("Slot", (byte)i); // on enregistre son emplacement dans le tableau
                this.contents[i].writeToNBT(nbttagcompound1); // on �crit l'item dans le tag
                nbttaglist.appendTag(nbttagcompound1); // on ajoute le tab � la liste
            }
        }
        compound.setTag("Items", nbttaglist); // on enregistre la liste dans le tag nbt 
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, compound);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        this.readFromNBT(pkt.func_148857_g());
        this.worldObj.markBlockRangeForRenderUpdate(this.xCoord, this.yCoord, this.zCoord, this.xCoord, this.yCoord, this.zCoord);
    }
    
    public int getValue() // et une fonction pour obtenir sa valeur (on appelle �a un getter)
    {
        return this.value;
    }
    public void setValue(int i) // et une fonction pour obtenir sa valeur (on appelle �a un getter)
    {
       this.value = i;
       this.markDirty();
    }
    public int getdirection() // et une fonction pour obtenir sa valeur (on appelle �a un getter)
    {
        return this.direction;
    }
    public void setdirection( int i ) // et une fonction pour obtenir sa valeur (on appelle �a un getter)
    {
        this.direction = i;
    }

	@Override
	public int getSizeInventory() {
		return contents.length;
	}



	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		return contents[p_70301_1_];
	}

	 @Override
	    public ItemStack decrStackSize(int slotIndex, int amount)
	    {
	        if(this.contents[slotIndex] != null) // si le contenu dans l'emplacement n'est pas null
	        {
	            ItemStack itemstack;
	            if(this.contents[slotIndex].stackSize <= amount) // si la quantit� est inf�rieur o� �gale � ce qu'on souhaite retirer
	            {
	                itemstack = this.contents[slotIndex]; // la variable itemstack prends la valeur du contenu
	                this.contents[slotIndex] = null; // on retire ce qui est dans la variable contents
	                this.markDirty(); // met � jour le tile entity
	                return itemstack; // renvoie itemstack
	            }
	            else // sinon
	            {
	                itemstack = this.contents[slotIndex].splitStack(amount); // la fonction splitStack(quantit�) retire dans this.contents[slotIndex] le contenu et le met dans itemstack
	                if(this.contents[slotIndex].stackSize == 0) // au cas o� la quantit� passe � 0 (ce qui ne devrait pas arriver en temps normal)
	                {
	                    this.contents[slotIndex] = null; // on met sur null, �a �vite de se retrouver avec des itemstack bugu� qui contiennent 0
	                }
	                this.markDirty(); // met � jour le tile entity
	                return itemstack; // renvoie itemstack
	            }
	        }
	        else // sinon si le contenu dans cette emplacement est null
	        {
	            return null; // renvoie null, puisqu'il n'y a rien dans cette emplacement
	        }
	    }



	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return contents[p_70304_1_];
	}



	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		contents[p_70299_1_]=p_70299_2_;
		this.markDirty();
	}



	@Override
	public String getInventoryName() {
		return "Shopper";
	}



	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}



	@Override
	public int getInventoryStackLimit() {
		return 640;
	}



	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {
		this.markDirty();
	}



	@Override
	public void closeInventory() {
		this.markDirty();
	}



	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
		return true;
	}

}
