package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class PlanterTileEntity extends TileEntity implements IInventory
{
    private ItemStack[] contents = new ItemStack[1];
    private Random field_146021_j = new Random();
    private String customName;
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound); // exécute ce qui se trouve dans la fonction readFromNBT de la classe mère (lecture de la position du tile entity)
        if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) // si un tag custom name de type string existe
        {
            this.customName = compound.getString("CustomName"); // on le lit
        }

        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND); // on obtient la liste de tags nommée Items
        this.contents = new ItemStack[this.getSizeInventory()]; // on réinitialise le tableau
        for(int i = 0; i < nbttaglist.tagCount(); ++i) // i varie de 0 à la taille la liste
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i); // on lit le tag nbt
            int j = nbttagcompound1.getByte("Slot") & 255; // on lit à quel slot se trouve l'item stack

            if(j >= 0 && j < this.contents.length)
            {
                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1); // on lit l'item stack qui se trouve dans le tag
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound); // exécute se qui se trouve dans la fonction writeToNBT de la classe mère (écriture de la position du tile entity)
        if(this.hasCustomInventoryName()) // s'il y a un nom custom
        {
            compound.setString("CustomName", this.customName); // on le met dans le tag nbt
        }

        NBTTagList nbttaglist = new NBTTagList(); // on créé une nouvelle liste de tags
        for(int i = 0; i < this.contents.length; ++i) // i varie de 0 à la taille de notre tableau
        {
            if(this.contents[ i] != null) // si l'item stack à l'emplacement i du tableau n'est pas null
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound(); // on créé un tag nbt
                nbttagcompound1.setByte("Slot", (byte)i); // on enregistre son emplacement dans le tableau
                this.contents[ i].writeToNBT(nbttagcompound1); // on écrit l'item dans le tag
                nbttaglist.appendTag(nbttagcompound1); // on ajoute le tab à la liste
            }
        }
        compound.setTag("Items", nbttaglist); // on enregistre la liste dans le tag nbt
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
	    public ItemStack decrStackSize(int slotIndex, int amount)
	    {
	        if(this.contents[slotIndex] != null) // si le contenu dans l'emplacement n'est pas null
	        {
	            ItemStack itemstack;

	            if(this.contents[slotIndex].stackSize <= amount) // si la quantité est inférieur où égale à ce qu'on souhaite retirer
	            {
	                itemstack = this.contents[slotIndex]; // la variable itemstack prends la valeur du contenu
	                this.contents[slotIndex] = null; // on retire ce qui est dans la variable contents
	                this.markDirty(); // met à jour le tile entity
	                return itemstack; // renvoie itemstack
	            }
	            else // sinon
	            {
	                itemstack = this.contents[slotIndex].splitStack(amount); // la fonction splitStack(quantité) retire dans this.contents[slotIndex] le contenu et le met dans itemstack

	                if(this.contents[slotIndex].stackSize == 0) // au cas où la quantité passe à 0 (ce qui ne devrait pas arriver en temps normal)
	                {
	                    this.contents[slotIndex] = null; // on met sur null, ça évite de se retrouver avec des itemstack bugué qui contiennent 0
	                }
	                this.markDirty(); // met à jour le tile entity
	                return itemstack; // renvoie itemstack
	            }
	        }
	        else // sinon si le contenu dans cette emplacement est null
	        {
	            return null; // renvoie null, puisqu'il n'y a rien dans cette emplacement
	        }
	    }

	 @Override
	    public ItemStack getStackInSlotOnClosing(int slotIndex)
	    {
	        if(this.contents[slotIndex] != null)
	        {
	            ItemStack itemstack = this.contents[slotIndex];
	            this.contents[slotIndex] = null;
	            return itemstack;
	        }
	        else
	        {
	            return null;
	        }
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
	        return this.hasCustomInventoryName() ? this.customName : "Planter";
	    }
	 
	  public void setCustomName(String customName)
	    {
	        this.customName = customName;
	    }

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

	@Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	 @Override
	    public boolean isItemValidForSlot(int slotIndex, ItemStack stack)
	    {
	        return true;
	    }

	 	public boolean hasComparatorInputOverride()
	    {
	        return true;
	    }

	    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
	    {
	        return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
	    }
	    
	  public ItemStack GetDistantSlotInv(int i)
	  {
		  return getStackInSlot(i);
	  }
	  
	  public int func_146017_i()
	    {
	        int i = -1;
	        int j = 1;

	        for (int k = 0; k < this.contents.length; ++k)
	        {
	            if (this.contents[k] != null && this.field_146021_j.nextInt(j++) == 0)
	            {
	                i = k;
	            }
	        }

	        return i;
	    }
	  
	  
}