package fr.neocraft.main.Proxy.Serveur.TileEntity;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Random;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class CarteTileEntity extends TileEntity implements IInventory
{

	private NeoChat C = new NeoChat();
    private ItemStack[] contents = new ItemStack[49];
    private EntityPlayer player;
    private World w;
    private static fr.neocraft.main.bdd bdd = main.getbdd();
    private boolean isRemote;
    public CarteContainer container;
    public int[] PosChunkZ = new int[] {
    		111,105,111,117,111,101,101,105,107,114,116,120,120,116,114,107,105,106,104,102,102,104,106,108,108,110,110,110,112,122,122,114,114,116,118,120,120,118,116,114,114,112,122,122,110,110,110,108,108
    }, PosChunkX = new int[] {
    		219,220,208,220,232,2177,224,225,229,230,226,223,217,215,211,211,215,217,217,219,221,223,223,225,227,225,227,229,225,227,229,225,227,223,233,221,219,217,217,215,213,215,213,211,215,213,211,215,213
    };
    public CarteTileEntity(EntityPlayer player, boolean isRemote, World w) {
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
	        return "CarteBackground";
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


	

	@Override
	public void openInventory() {
		if(!this.isRemote)
		{
			Stage stage, stageRef = RegisterStage.getFirstStagePlayer(PlayerStats.get(this.player));
			
			int idw = this.player.worldObj.provider.dimensionId;
			for(int i = 0; i < this.contents.length; ++i) // i varie de 0 à la taille de notre tableau
	        {
				stage = RegisterStage.getStageAtXY(PosChunkX[i], PosChunkZ[i], idw);
				if(stage != null)
				{
					if(stage.IsUse())
					{
						
							int o = 0;
						
							String[] lore = new String[10];
							String chef = stage.getOwner();
			        		if(RegisterStage.getPlayer(chef) != null)
							{
		    					chef = EnumChatFormatting.DARK_PURPLE+chef+" ["+new ChatComponentTranslation("neo.online").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"]";
		    				}
			        		else
			        		{
			        			int id = bdd.GetFreeId();
			        			try {
		    						ResultSet result = bdd.query("SELECT LastLogin FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+chef+"'",id);
		    						String fjour = "?";
		    						while(result.next())
	        						{
		    							
			    						int jour = result.getInt("LastLogin");
			    				
			    						switch(jour)
		        						{
		        						case(15):
		        							fjour=0+"j";
		        							break;
		        						case(14):
		        							fjour=1+"j";
		        							break;
		        						case(13):
		        							fjour=2+"j";
		        							break;
		        						case(12):
		        							fjour=3+"j";
		        							break;
		        						case(11):
		        							fjour=4+"j";
		        							break;
		        						case(10):
		        							fjour=5+"j";
		        							break;
		        						case(9):
		        							fjour=6+"j";
		        							break;
		        						case(8):
		        							fjour=7+"j";
		        							break;
		        						case(7):
		        							fjour=8+"j";
		        							break;
		        						case(6):
		        							fjour=9+"j";
		        							break;
		        						case(5):
		        							fjour=10+"j";
		        							break;
		        						case(4):
		        							fjour=11+"j";
		        							break;
		        						case(3):
		        							fjour=12+"j";
		        							break;
		        						case(2):
		        							fjour=13+"j";
		        							break;
		        						case(1):
		        							fjour=14+"j";
		        							break;
		        						case(0):
		        							fjour="+14j";
		        							break;
		        						}
	        						}
		    						chef += EnumChatFormatting.DARK_PURPLE+" ["+new ChatComponentTranslation("neo.offline", "("+fjour+")").getUnformattedText()+""+EnumChatFormatting.DARK_PURPLE+"], ";
		    					} catch(Exception p)
		    					{
		    						p.printStackTrace();
		    						chef+= "[?]";
		    					}
			        			bdd.CloseFreeId(id);
			        			
			        		}
			        		lore[o] = new ChatComponentTranslation("neo.stageinfo.chef", EnumChatFormatting.DARK_PURPLE+""+chef).getUnformattedText();
			        		o++;
			        		if(stage.getSubOwner() != null)
			        		{
			        			String F = "";
			        			for(String e: stage.getSubOwner())
			        			{
									if(RegisterStage.getPlayer(e) != null)
									{
			        					F += EnumChatFormatting.DARK_PURPLE+e+" ["+new ChatComponentTranslation("neo.online").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"], ";
			        				} else
			        				{
			        					int id = bdd.GetFreeId();
			        					try {
			        						ResultSet result = bdd.query("SELECT LastLogin FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+e+"'", id);
			        						String fjour = "?";
			        						while(result.next())
			        						{
				        						int jour = result.getInt("LastLogin");
		
				        						switch(jour)
				        						{
				        						case(15):
				        							fjour=0+"j";
				        						case(14):
				        							fjour=1+"j";
				        						case(13):
				        							fjour=2+"j";
				        						case(12):
				        							fjour=3+"j";
				        						case(11):
				        							fjour=4+"j";
				        						case(10):
				        							fjour=5+"j";
				        						case(9):
				        							fjour=6+"j";
				        						case(8):
				        							fjour=7+"j";
				        						case(7):
				        							fjour=8+"j";
				        						case(6):
				        							fjour=9+"j";
				        						case(5):
				        							fjour=10+"j";
				        						case(4):
				        							fjour=11+"j";
				        						case(3):
				        							fjour=12+"j";
				        						case(2):
				        							fjour=13+"j";
				        						case(1):
				        							fjour=14+"j";
				        						case(0):
				        							fjour="+14j";
				        						}
			        						}
			        						F += (EnumChatFormatting.DARK_PURPLE+e+" ["+new ChatComponentTranslation("neo.offline", "("+fjour+")").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"], ");
			        					} catch(Exception p)
			        					{
			        						p.printStackTrace();
			        						F+= "[?], ";
			        					}
			        					bdd.CloseFreeId(id);
			        				}
			        			}
			        			lore[o] = new ChatComponentTranslation("neo.stageinfo.membre", F.substring(0, F.lastIndexOf(", "))).getUnformattedText();
			        			o++;
			        		}
			        		if(stage.getBouclier() != null)
			        		{
			        			lore[o] =" ";
			        			o++;
			        			Calendar c = Calendar.getInstance();
			        			Calendar c2 = Calendar.getInstance();
			        			c2.setTime(stage.getBouclier());
			        			long diff = c2.getTimeInMillis() - c.getTimeInMillis();
			        			
			        			long diffMinutes = diff / (60 * 1000) % 60;
			        			long diffHours = diff / (60 * 60 * 1000) % 24;
			        			long diffDays = diff / (24 * 60 * 60 * 1000);
			        			if(1 > diffMinutes)
			        			{
			        				diffMinutes = 0;
			        			}
			        			if(1 > diffHours)
			        			{
			        				diffHours = 0;
			        			}
			        			if(1 > diffDays)
			        			{
			        				diffDays = 0;
			        			}
			        			lore[o++] = EnumChatFormatting.LIGHT_PURPLE+"Bouclier: "+EnumChatFormatting.DARK_PURPLE+""+diffDays+" jour(s) "+diffHours + " heure(s) "+diffMinutes+" minute(s)";
			        		}
			        		lore[o] = " ";
			        		o++;
			        		
			        		lore[o] = new ChatComponentTranslation("neo.stageinfo.idstage", EnumChatFormatting.DARK_PURPLE+""+stage.getIdStage()).getUnformattedText();
			        		o++;
			        		lore[o] = new ChatComponentTranslation("neo.stageinfo.bank", EnumChatFormatting.DARK_PURPLE+""+stage.getBank()+"$").getUnformattedText();
			        		o++;
			        		lore[o] = " ";
			        		o++;
			        		ItemStack tem = null;
			        		int effect = 0; 
			        		int u = new Random().nextInt(6);
			        		if(stage.isIn(this.player.getCommandSenderName()))
			        		{
			        			effect = 0;
			        			tem = new ItemStack(ItemMod.guiDrag[16]);
			        		}
			        		else if(stageRef != null && stageRef.isAlly(player.getCommandSenderName()))
			        		{
			        			effect = 0;
			        			tem = new ItemStack(ItemMod.guiDrag[17]);
			        		}
			        		else if(stageRef != null && stageRef.isEnnemy(player.getCommandSenderName()))
			        		{
			        			effect = 2;
			        			 tem = new ItemStack(ItemMod.guiDrag[15]);
			        		}else {
			        			tem = new ItemStack(ItemMod.guiDrag[12]);
			        		}
							this.contents[i] = S(tem, lore, new ChatComponentTranslation("neo.stageinfother.header", EnumChatFormatting.DARK_PURPLE+stage.getName()).getUnformattedText(), effect );
						}
			        	
			        		
					}	
		        
	   
			}
		}
	}
	public ItemStack S(ItemStack stack, String[] Lore, String name, int effect)
	{
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null) nbt = new NBTTagCompound();

		NBTTagList lore = new NBTTagList();
		 for (int i = 0; i <  Lore.length; i++)
	       {
			 if(Lore[i] != null)
			 {
				 lore.appendTag(new NBTTagString(Lore[i]));
			 }
	       }

		
		NBTTagCompound display = new NBTTagCompound();
		display.setTag("Lore", lore);

		nbt.setTag("display", display);
		nbt.getCompoundTag("display").setString("Name", name);
		nbt.setInteger("NeoEffect", effect);
		stack.setTagCompound(nbt);
		return stack;
	}
	@Override
	public Block getBlockType()
    {
        return Blocks.air;
    }


	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	 {
		return false;
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
	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}
}
	  

