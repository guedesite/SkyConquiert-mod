package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.util.Random;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class BoxVoteTileEntity extends TileEntity implements IInventory
{

	private NeoChat C = new NeoChat();
    private ItemStack[] contents = new ItemStack[6];
    private EntityPlayer player;
    private World w;
    private static final BoxItem[] Box = main.getVoteBox();
    private boolean isRemote, isOpen = false, isScrolling = false;
    public BoxVoteContainer container;
    public static Thread o, i;
    public BoxVoteTileEntity(EntityPlayer player, boolean isRemote, World w) {
		this.player = player;
		this.isRemote = isRemote;
		this.w = w;
	}
    public void markDirty()
    {
       
    }
	@Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound); // exécute ce qui se trouve dans la fonction readFromNBT de la classe mère (lecture de la position du tile entity)
       
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
	        return "Box Vote";
	    }
	 


	@Override
	public boolean hasCustomInventoryName() {
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
        return true;
    }


	// cos(x/20)*30+30
	public void Scrolling()
	{
		if(this.contents[0] == null)
		{
			return;
		}
		if(this.contents[0].stackSize == 1) { this.contents[0]= null;  } else { this.contents[0].splitStack(1); }
		if(!this.isScrolling)
		{
			this.isScrolling = true;
			o = new Thread() {
				@Override
				public void run() {
					for(int i = 16; i <= 128; i++)
					{
						long sleep = (long) (Math.cos(i/20)*30)+30;
						try {
							Thread.sleep(sleep);
							UpdateScrolling();
						} catch (InterruptedException e) {
							try {
								Thread.sleep(sleep);
							} catch (InterruptedException o) { }
						}
						if(!check())
						{
							return;
						}
					}
					try {
						Thread.sleep(1000);
						if(!check())
						{
							return;
						}
						FinishAnim(true);
						Thread.sleep(250);
						if(!check())
						{
							return;
						}
						FinishAnim(false);
						Thread.sleep(500);
						if(!check())
						{
							return;
						}
						giveWin();
						isScrolling = false;
						Thread.sleep(1000);
						openInventory();
						
					} catch (InterruptedException e) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException o) { }
					}
					return;
				}
			};
			o.interrupt();
			o.setName("BOX-THREAD-"+player.getCommandSenderName());
			o.setPriority(Thread.MIN_PRIORITY);
			o.start();
		}
	}
	// -0.1(x-1)^2
	
	public void FinishAnim(boolean stat)
	{
		if(stat)
		{
			this.contents[1] = null;
			this.contents[5] = null;
			this.markDirty();
		}
		else
		{
			this.contents[2] = null;
			this.contents[4] = null;
			this.markDirty();
		}
	}
	//random.levelup
	public void giveWin()
	{
		if(this.contents[3] != null)
		{
			ItemStack item2  = this.contents[3].copy();
			item2.setStackDisplayName(this.contents[3].getDisplayName().replace(" "+C.d_yellow+"["+C.l_yellow+"Box"+C.d_yellow+"]"+C.l_yellow, ""));
			player.inventory.addItemStackToInventory(item2);
			SoundManager.PlaySound(EnumSound.NeoILoot.getSound(), this.player);
		}
		else
		{
			giveRanWin();
		}
	}
	public void giveRanWin()
	{
		ItemStack item = NewItem();
		ItemStack item2  = item.copy();
		item2.setStackDisplayName(this.contents[3].getDisplayName().replace(" "+C.d_yellow+"["+C.l_yellow+"Box"+C.d_yellow+"]"+C.l_yellow, ""));
		player.inventory.addItemStackToInventory(item2);
		this.w.playSoundAtEntity(this.player, "minecraft:random.levelup", 1, 1);
	}
	Random rand = new Random();
	public ItemStack NewItem()
	{
		boolean stat = false;
		int  o = 0;
		while(stat == false)
		{
			o = rand.nextInt(Box.length);
			if(Box[o] != null)
			{
				stat = Box[o].canItem();
			}
		}
		return Box[o].getItem().copy();
	}
	
	public void UpdateScrolling()
	{
		int i = 6;
		while (2 <= i--)
		{
			if(i == 1)
			{
				this.contents[i] = NewItem();
			}
			else if(this.contents[i - 1]!=null)
			{
				this.contents[i] = this.contents[i-1].copy();
			}
		}
		this.w.playSoundAtEntity(this.player, "minecraft:random.pop",0.5F, 1);
		this.w.playSoundAtEntity(this.player, "minecraft:random.orb", 	0.5F, 1);
		this.markDirty();
	}

	@Override
	public void openInventory() {
		if(!this.isRemote)
		{
			i = new Thread() {
				@Override
				public void run() {
					while(isScrolling == false)
					{
						try {
							Thread.sleep(500);
							UpdateScrolling();
						} catch (InterruptedException e) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException o) { }
						}
						if(!check())
						{
							return;
						}
					}
					return; 
				}
			};
			i.interrupt();
			i.setName("BOX-THREAD-"+player.getCommandSenderName());
			i.setPriority(Thread.MIN_PRIORITY);
			i.start();
		}
	}

	@Override
	public Block getBlockType()
    {
        return Blocks.air;
    }
	@Override
	public void closeInventory() {
		if(!this.isRemote)
		{
			this.isOpen = false;
		}
	}

	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	 {
		if(p_94041_2_ == null) { return true; }
    	if(p_94041_1_ == 0)
    	{
    		if(p_94041_2_.getItem() == ItemMod.Vote_Key)
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else if(p_94041_1_ == 1 || p_94041_1_ == 2 || p_94041_1_ == 3 || p_94041_1_ == 4 || p_94041_1_ == 5)
    	{
    		if(p_94041_2_.getDisplayName().contains(C.d_yellow+"["+C.l_yellow+"Box"+C.d_yellow+"]"+C.l_yellow))
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}
    	}
    	else {
    		return true;
    	}
	    }

	    
	  public ItemStack GetDistantSlotInv(int i)
	  {
		  return getStackInSlot(i);
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
		if(p_70304_1_ == 0)
    	{
			return null;
    	}
		else
		{
			return null;
		}
	}
	  

	public boolean check()
	{
		if(this.container != null)
		{
			if(!this.container.isPlayerNotUsingContainer(player))
			{
				if(isScrolling)
				{
					giveRanWin();
					return false;
				}
				else
				{
					this.isOpen = false;
					this.player.closeScreen();
					return false;
				}
			}
		}
		return true;
	}
}
	  