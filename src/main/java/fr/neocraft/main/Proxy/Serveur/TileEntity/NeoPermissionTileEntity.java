package fr.neocraft.main.Proxy.Serveur.TileEntity;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class NeoPermissionTileEntity extends TileEntity implements IInventory
{

	private NeoChat C = new NeoChat();
    private ItemStack[] contents = new ItemStack[7];
    private EntityPlayer player;
    private PlayerStats stat;
    private boolean isRemote;
    private World w;
    private int x,y,z;
    private int nb = 0;
    private fr.neocraft.main.bdd bdd;
    private String[] base = {"enderpearl","interact","attaque","harvest","bonemeal","container","block"};
    private String[] baseInfo = {
    		new ChatComponentTranslation("neo.perm.1").getUnformattedText(), 
    		new ChatComponentTranslation("neo.perm.2").getUnformattedText(),
    		new ChatComponentTranslation("neo.perm.3").getUnformattedText(),
    		new ChatComponentTranslation("neo.perm.4").getUnformattedText(),
    		new ChatComponentTranslation("neo.perm.5").getUnformattedText(),
    		new ChatComponentTranslation("neo.perm.6").getUnformattedText(),
    		new ChatComponentTranslation("neo.perm.7").getUnformattedText()};
    public NeoPermissionTileEntity(EntityPlayer player, boolean isRemote, World w, int x, int y, int z, int i) {
		this.player = player;
		this.isRemote = isRemote;
		this.w = w;
		this.x=x;
		this.y=y;
		this.z=z;
		this.stat = PlayerStats.get(player);
		this.nb = i;
		if(!this.isRemote)
		{
			this.bdd = main.getbdd();
		}
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
		try {
			return this.contents[slotIndex];
		} catch(Exception e) {
			return null;
		}
    }

	 @Override
	    public ItemStack decrStackSize(int slotIndex, int amount)
	    {
	        return null;
	    }

	 @Override
	    public ItemStack getStackInSlotOnClosing(int slotIndex)
	    {
		 return null;
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
	        return "Permission Manager";
	    }
	 
	 @Override
	 public void markDirty()
	    {
	        if (this.w != null)
	        {
	            this.w.markTileEntityChunkModified(this.x, this.y, this.z, this);

	        }
	    }

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

	@Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return true;
    }

	public void updateinv()
	{
		Stage stage = RegisterStage.getFirstStagePlayer(PlayerStats.get(player));
		if(stage == null) { player.openContainer = null; return;}
		int i = 0;
		for(ItemStack item:this.contents)
		{
			if(nb == 0)
			{
				if(!stage.getPermAll().contains(base[i]))
				{
					String[] temp = new String[] {C.red+"Permission: "+base[i], C.red+"infos: "+baseInfo[i]};
					this.contents[i]=StackWithNBT(new ItemStack(ItemMod.guiDrag[14]), temp); //13 check - 14 cross
				}
				else
				{
					String[] temp = new String[] {C.l_green+"Permission: "+base[i], C.l_green+"infos: "+baseInfo[i]};
					this.contents[i]=StackWithNBT(new ItemStack(ItemMod.guiDrag[13]), temp);
				}
			}
			else if(nb == 1)
			{
				if(!stage.getPermMember().contains(base[i]))
				{
					String[] temp = new String[] {C.red+"Permission: "+base[i], C.red+"infos: "+baseInfo[i]};
					this.contents[i]=StackWithNBT(new ItemStack(ItemMod.guiDrag[14]), temp);
				}
				else
				{
					String[] temp = new String[] {C.l_green+"Permission: "+base[i], C.l_green+"infos: "+baseInfo[i]};
					this.contents[i]=StackWithNBT(new ItemStack(ItemMod.guiDrag[13]), temp);
				}
			}
			else {
				if(!stage.getPermAllie().contains(base[i]))
				{
					String[] temp = new String[] {C.red+"Permission: "+base[i], C.red+"infos: "+baseInfo[i]};
					this.contents[i]=StackWithNBT(new ItemStack(ItemMod.guiDrag[14]), temp);
				}
				else
				{
					String[] temp = new String[] {C.l_green+"Permission: "+base[i], C.l_green+"infos: "+baseInfo[i]};
					this.contents[i]=StackWithNBT(new ItemStack(ItemMod.guiDrag[13]), temp);
				}
			}
			i++;
		}
		this.markDirty();
	}
	
	@Override
	public void openInventory() {
		if(!this.isRemote)
		{
		
			this.updateinv();
		}
	}
	
	
	
	public ItemStack StackWithNBT(ItemStack stack, String[] Lore, int prix)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) nbt = new NBTTagCompound();

		NBTTagList lore = new NBTTagList();
		 for (int i = 0; i <  Lore.length; i++)
	       {
			 lore.appendTag(new NBTTagString(Lore[i]));
	       }

		
		NBTTagCompound display = new NBTTagCompound();
		display.setTag("Lore", lore);

		nbt.setTag("display", display);
		nbt.setInteger("prix", prix);
		stack.setTagCompound(nbt);
		return stack;
	}
	public ItemStack StackWithNBT(ItemStack stack, String[] Lore)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) nbt = new NBTTagCompound();

		NBTTagList lore = new NBTTagList();
		 for (int i = 0; i <  Lore.length; i++)
	       {
			 lore.appendTag(new NBTTagString(Lore[i]));
	       }

		
		NBTTagCompound display = new NBTTagCompound();
		display.setTag("Lore", lore);

		nbt.setTag("display", display);
		stack.setTagCompound(nbt);
		return stack;
	}


	@Override
	public void closeInventory() {
		this.w.removeTileEntity(this.x, this.y, this.z);
		
	}

	 @Override
	    public boolean isItemValidForSlot(int slotIndex, ItemStack stack)
	    {
	        return false;
	    }


	    public int getComparatorInputOverride(World p_149736_1_, int p_149736_2_, int p_149736_3_, int p_149736_4_, int p_149736_5_)
	    {
	        return Container.calcRedstoneFromInventory((IInventory)p_149736_1_.getTileEntity(p_149736_2_, p_149736_3_, p_149736_4_));
	    }
	    
	  public ItemStack GetDistantSlotInv(int i)
	  {
		  return getStackInSlot(i);
	  }
	  

}
	  
