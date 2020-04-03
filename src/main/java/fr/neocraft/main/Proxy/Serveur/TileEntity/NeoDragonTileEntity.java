package fr.neocraft.main.Proxy.Serveur.TileEntity;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import info.ata4.minecraft.dragon.server.entity.helper.DragonBreedRegistry;
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

public class NeoDragonTileEntity extends TileEntity implements IInventory
{

	private NeoChat C = new NeoChat();
    private ItemStack[] contents = new ItemStack[10];
    private EntityPlayer player;
    private boolean isRemote;
    private World w;
    private int nb = 0;
    private int x,y,z;
    private fr.neocraft.main.bdd bdd;
    public NeoDragonTileEntity(EntityPlayer player, boolean isRemote, World w, int x, int y, int z, int i) {
		this.player = player;
		this.isRemote = isRemote;
		this.w = w;
		this.x=x;
		this.y=y;
		this.nb = i;
		this.z=z;
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
        return this.contents[slotIndex];
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
			 return "Dragon Evolution";
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
		return true;
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

	private int[] amelio = new int[] {0, 500, 750, 1000, 1500, 2000, 2500, 5000, 7500, 15000};
	private int[] ameliodrag = new int[] { 100, 500, 750, 1500 };
	public void updateinv()
	{
		if(nb == 0)
		{
			PlayerStats stat = PlayerStats.get(player);
			if(stat.lvlGuiDrag[0] == 0)
			{
				SetCheck(0, "Dragon stage oeuf");
				SetVoid(1, this.ameliodrag[stat.lvlGuiDrag[0]], "dragon stage hatchling");
				for(int i = 2; i < this.getSizeInventory(); i++)
				{
					
					SetCross(i);
				}
			}else if(stat.lvlGuiDrag[0] == 1)
			{
				SetCheck(0, "Dragon stage oeuf");
				SetCheck(1, "dragon stage hatchling");
				SetVoid(2, this.ameliodrag[stat.lvlGuiDrag[0]], "dragon stage juvenile");
				for(int i = 3; i < this.getSizeInventory(); i++)
				{
					SetCross(i);
				}
			}else if(stat.lvlGuiDrag[0] == 2)
			{
				SetCheck(0, "Dragon stage oeuf");
				SetCheck(1, "dragon stage hatchling");
				SetCheck(2, "dragon stage juvenile");
				SetVoid(3, this.ameliodrag[stat.lvlGuiDrag[0]], "dragon stage adult");
				for(int i = 4; i < this.getSizeInventory(); i++)
				{
					SetCross(i);
				}
			}else if(stat.lvlGuiDrag[0] == 3)
			{
				SetCheck(0, "Dragon stage oeuf");
				SetCheck(1, "dragon stage hatchling");
				SetCheck(2, "dragon stage juvenile");
				SetCheck(3, "dragon stage adult");
				
				Setlvl(4, stat.lvlGuiDrag[1], new ChatComponentTranslation("neo.dragon.gui.stat1").getUnformattedText(), (double) 20 + 20 * stat.lvlGuiDrag[1], (double)20 + 20 *( stat.lvlGuiDrag[1] + 1));
				Setlvl(5, stat.lvlGuiDrag[2], new ChatComponentTranslation("neo.dragon.gui.stat2").getUnformattedText(), (double) 0.5 + 0.1 * stat.lvlGuiDrag[2], (double) 0.5 + 0.1 * (stat.lvlGuiDrag[2] + 1));
				Setlvl(6, stat.lvlGuiDrag[3], new ChatComponentTranslation("neo.dragon.gui.stat3").getUnformattedText(), (double) 0.3 + 0.5 * stat.lvlGuiDrag[3], (double) 0.3 + 0.5 * (stat.lvlGuiDrag[3] + 1));
				
				SetVoid(7, this.ameliodrag[stat.lvlGuiDrag[0]], new ChatComponentTranslation("neo.dragon.gui.stat0").getUnformattedText());
				for(int i = 8; i < this.getSizeInventory(); i++)
				{
					SetCross(i);
				}
				
			}else if(stat.lvlGuiDrag[0] == 4)
			{
				SetCheck(0, "dragon stage oeuf");
				SetCheck(1, "dragon stage hatchling");
				SetCheck(2, "dragon stage juvenile");
				SetCheck(3, "dragon stage adult");
				SetCheck(7, new ChatComponentTranslation("neo.dragon.gui.stat0").getUnformattedText());
				
				Setlvl(4, stat.lvlGuiDrag[1], new ChatComponentTranslation("neo.dragon.gui.stat1").getUnformattedText(), (double) 20 + 20 * stat.lvlGuiDrag[1], (double)20 + 20 *( stat.lvlGuiDrag[1] + 1));
				Setlvl(5, stat.lvlGuiDrag[2], new ChatComponentTranslation("neo.dragon.gui.stat2").getUnformattedText(), (double) 0.5 + 0.1 * stat.lvlGuiDrag[2], (double) 0.5 + 0.1 * (stat.lvlGuiDrag[2] + 1));
				Setlvl(6, stat.lvlGuiDrag[3], new ChatComponentTranslation("neo.dragon.gui.stat3").getUnformattedText(), (double) 0.3 + 0.5 * stat.lvlGuiDrag[3], (double) 0.3 + 0.5 * (stat.lvlGuiDrag[3] + 1));
				
				Setlvl(8, stat.lvlGuiDrag[4], new ChatComponentTranslation("neo.dragon.gui.stat4").getUnformattedText(), (float) stat.lvlGuiDrag[4] * 0.15F + 0.2F, (float)0.2F + 0.15F *( stat.lvlGuiDrag[4] + 1));
				Setlvl(9, stat.lvlGuiDrag[5], new ChatComponentTranslation("neo.dragon.gui.stat5").getUnformattedText(), (double) 0.2D + 0.15 * stat.lvlGuiDrag[5], (double) 0.2D + 0.15 * (stat.lvlGuiDrag[5] + 1));
			}
		} else {
			ItemStack t = StackWithNBT(new ItemStack(ItemMod.guiDrag[19]), new String[] { new ChatComponentTranslation("neo.dragon.gui.stat6").getUnformattedText() }, 0);
			
			contents = new ItemStack[] {t.copy(),t.copy() ,t.copy() ,t.copy() ,t.copy() ,t.copy() ,t.copy(), t.copy() ,t.copy(), t.copy()  };
			PlayerStats stat = PlayerStats.get(this.player);
			for(int e: stat.Allbreed)
			{
				if(stat.DragBreed == e)
				{
					contents[e] = StackWithNBT(new ItemStack(ItemMod.ItemDragon2[e],1), new String[] { new ChatComponentTranslation("neo.dragon.gui.stat7").getUnformattedText() }, e);
				}
				else
				{
					contents[e] = StackWithNBT(new ItemStack(ItemMod.ItemDragon2[e],1), new String[] { new ChatComponentTranslation("neo.dragon.gui.stat8", DragonBreedRegistry.getInstance().getBreedById(e).getName()).getUnformattedText() }, e);
				}
			}
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
	
	
	public void SetCross(int slot)
	{
		this.contents[slot]=StackWithNBT(new ItemStack(ItemMod.guiDrag[11]), new String[] { new ChatComponentTranslation("neo.dragon.gui.upgrade5").getUnformattedText(), new ChatComponentTranslation("neo.dragon.gui.upgrade6").getUnformattedText()});
	}
	public void SetCheck(int slot, String string)
	{
		String[] temp = new String[] {new ChatComponentTranslation("neo.dragon.gui.upgrade3", string).getUnformattedText(), new ChatComponentTranslation("neo.dragon.gui.upgrade4").getUnformattedText()};
		this.contents[slot]=StackWithNBT(new ItemStack(ItemMod.guiDrag[10]), temp);
	}
	public void SetVoid(int slot, int prix, String txt)
	{
		String[] temp = new String[] {C.l_purple +txt, new ChatComponentTranslation("neo.dragon.gui.upgrade2",prix+"").getUnformattedText()};
		this.contents[slot]=StackWithNBT(new ItemStack(ItemMod.guiDrag[12]), temp, prix);
	}
	public void Setlvl(int slot, int lvl, String cap, double av, double ap)
	{
		String[] temp;
		if(lvl == 10)
		{
			temp = new String[] {new ChatComponentTranslation("neo.dragon.gui.fullupgrade").getUnformattedText(),C.l_purple + cap + " "+ av};
			
		}
		else
		{
			temp = new String[] {new ChatComponentTranslation("neo.dragon.gui.upgrade1").getUnformattedText(), C.l_purple +"Level " + lvl + C.d_purple +" → " + C.l_purple+ (lvl + 1), C.l_purple + cap + " "+ av + " → " + ap, new ChatComponentTranslation("neo.dragon.gui.upgrade2",this.amelio[lvl]+"").getUnformattedText()};
			
		}
		this.contents[slot]=StackWithNBT(new ItemStack(ItemMod.guiDrag[lvl - 1]), temp, lvl);
	}
	public void Setlvl(int slot, int lvl, String cap, float av, float ap)
	{ 	
		String[] temp;
		if(lvl == 10)
		{
			temp = new String[] {new ChatComponentTranslation("neo.dragon.gui.fullupgrade").getUnformattedText(),C.l_purple + cap + " "+ av};
			
		}
		else
		{
			temp = new String[] {new ChatComponentTranslation("neo.dragon.gui.upgrade7").getUnformattedText(), C.l_purple +"Level " + lvl + C.d_purple +" → " + C.l_purple+ (lvl + 1), C.l_purple + cap + " "+ av + " → " + ap, new ChatComponentTranslation("neo.dragon.gui.upgrade2",this.amelio[lvl]+"").getUnformattedText()};
			
		}this.contents[slot] = StackWithNBT(new ItemStack(ItemMod.guiDrag[lvl - 1]), temp, lvl);
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
	  
