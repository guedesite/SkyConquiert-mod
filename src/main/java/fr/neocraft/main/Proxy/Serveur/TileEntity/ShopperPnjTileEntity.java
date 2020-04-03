package fr.neocraft.main.Proxy.Serveur.TileEntity;

import fr.neocraft.main.Proxy.Serveur.Stage.Shopper.ShopperData;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.DailyManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ShopperPnjTileEntity extends TileEntity implements IInventory{
	private ItemStack[] contents = new ItemStack[8*6+1];
	private EntityPlayer player;
    private World w;
    private boolean isRemote;
    public ShopperData Entity;
    
    public ShopperPnjTileEntity(EntityPlayer player, boolean isRemote, World w,  ShopperData entity, int X, int Y, int Z) {
		this.player = player;
		this.Entity = entity;
		this.isRemote = isRemote;
		this.w = w;
		this.xCoord =X;
		this.yCoord = Y;
		this.zCoord = Z;
		this.worldObj = w;

			int nb = 0;
			for(int i = 0; i < entity.stack.length; i++)
        	{
				if(entity.stack[i] != null)
				{
	        		for(int o = 0; o < 6; o++)
	        		{
	        			int nbfinalprix = 1;
	        			int nbfinalprix2 = 1;
	        			if(o == 0)
	        			{
        					nbfinalprix = 1 ;
        					nbfinalprix2 = 1;
        				} else if(o == 1)
        				{
        					nbfinalprix =16;
        					nbfinalprix2 = 16;
        				}else if(o == 2)
        				{
        					nbfinalprix =32;
        					nbfinalprix2 = 32;
        				}else if(o == 3)
        				{
        					nbfinalprix =  64;
        					nbfinalprix2 = 64;
        				}else if(o == 4)
        				{
        					nbfinalprix = 128;
        					nbfinalprix2 = 64;
        				}else if(o == 5)
        				{
        					nbfinalprix = 256;
        					nbfinalprix2 = 64;
        				}
	        			contents[nb] = StackWithNBT(new ItemStack(entity.stack[i].stack.getItem(), nbfinalprix2, entity.damage[i]), entity.prix[i], nbfinalprix, entity.damage[i], entity.getVariant(entity.stack[i].stack.getItem(),entity.damage[i]));
        				nb++;
        			}
        		}
        	}
	}
    public ItemStack StackWithNBT(ItemStack stack, int prix, int nb, int dmg, float var)
	{
    	if(DailyManager.getIdDayByName() == 6)
		{
    	//	prix =(int)( prix * PlayerStats.get(this.player).bonusday);
		}
   // 	prix *= nb;

    	int prixFinal = Math.round((prix*nb)*(100+var)/100);
    	
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) nbt = new NBTTagCompound();
		NBTTagList lore = new NBTTagList();
		
		lore.appendTag(new NBTTagString(EnumChatFormatting.DARK_GRAY +"Gains: " + EnumChatFormatting.GRAY+(prixFinal)+"$"));
		lore.appendTag(new NBTTagString(EnumChatFormatting.DARK_GRAY +"Nombres d'item: " + EnumChatFormatting.GRAY+nb));
		
		NBTTagCompound display = new NBTTagCompound();
		display.setTag("Lore", lore);

		nbt.setFloat("var", var);
		nbt.setTag("display", display);
		nbt.setInteger("prix", prix);
		nbt.setInteger("prixfinal", prixFinal);
		nbt.setInteger("nombre", nb);
		nbt.setInteger("damage", dmg);
		
		stack.setTagCompound(nbt);
		return stack;
	}
    public ShopperPnjTileEntity(EntityPlayer player2, boolean isRemote2, World world) {
    	this.player = player2;
		this.isRemote = isRemote2;
		this.w = world;
	}
	@Override
    public void markDirty()
    {
       
    }
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
         NBTTagList nbttaglist = compound.getTagList("Items", net.minecraftforge.common.util.Constants.NBT.TAG_COMPOUND); // on obtient la liste de tags nomm�e Items
        this.contents = new ItemStack[getSizeInventory()]; // on r�initialise le tableau
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
 

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
    	 super.writeToNBT(compound);
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
	public int getSizeInventory() {
		return contents.length;
	}



	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		try {
			return contents[p_70301_1_];
		} catch(ArrayIndexOutOfBoundsException e)
		{
			return null;
		}
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
		return "Shopper Pnj";
	}



	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}



	@Override
	public int getInventoryStackLimit() {
		return 512;
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
		return false;
	}

}
