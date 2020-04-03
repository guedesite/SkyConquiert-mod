package fr.neocraft.main.Proxy.Serveur.TileEntity;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Item.ItemDragon2;
import fr.neocraft.main.Proxy.Serveur.player.AnnimationManager;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import info.ata4.minecraft.dragon.server.entity.helper.DragonLifeStage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class DragonQuestContainer extends Container{

	
	private final NeoDragonTileEntity NeoTile;
	private final World w;
	private final fr.neocraft.main.bdd bdd = main.getbdd();
	private final NeoChat C = main.getChat();
	private int nb = 0;
	
    public  DragonQuestContainer(NeoDragonTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p, int i)
    {
        this.NeoTile = tile;
        this.w = w;
        this.nb = i;
        tile.openInventory();
        if(nb == 0)
        {
	        this.addSlotToContainer(new Slot(tile, 0, 120, 207));
	        this.addSlotToContainer(new Slot(tile, 1, 120, 166));
	        this.addSlotToContainer(new Slot(tile, 2, 120, 125));
	        this.addSlotToContainer(new Slot(tile, 3, 120, 87));
	        this.addSlotToContainer(new Slot(tile, 4, 24, 83));
	        this.addSlotToContainer(new Slot(tile, 5, 40, 113));
	        this.addSlotToContainer(new Slot(tile, 6, 70, 117));
	        this.addSlotToContainer(new Slot(tile, 7, 164, 105));
	        this.addSlotToContainer(new Slot(tile, 8, 198, 113));
	        this.addSlotToContainer(new Slot(tile, 9, 216, 82));
        }
        else {
        	this.addSlotToContainer(new Slot(tile, 0, 120, 207));
	        this.addSlotToContainer(new Slot(tile, 1, 121, 165));
	        this.addSlotToContainer(new Slot(tile, 8, 121, 125));
	        this.addSlotToContainer(new Slot(tile, 9, 120, 87));
	        this.addSlotToContainer(new Slot(tile, 4, 25, 83));
	        this.addSlotToContainer(new Slot(tile, 5, 40, 112));
	        this.addSlotToContainer(new Slot(tile, 6, 70, 118));
	        this.addSlotToContainer(new Slot(tile, 7, 216, 83));
	        this.addSlotToContainer(new Slot(tile, 3, 197, 112));
	        this.addSlotToContainer(new Slot(tile, 2, 164, 105));
        }
    }
    @Override
    protected Slot addSlotToContainer(Slot p_75146_1_)
    {
        p_75146_1_.slotNumber = this.inventorySlots.size();
        this.inventorySlots.add(p_75146_1_);
        this.inventoryItemStacks.add((Object)null);
        return p_75146_1_;
    }
    
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        return null;
    }


	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		return this.NeoTile.isUseableByPlayer(p_75145_1_);
	}
	private int[] infoSlot = new int[] {1, 2, 3, 0, 4, 5};
	private int[] amelio = new int[] {0, 500, 750, 1000, 1500, 2000, 2500, 5000, 7500, 15000};

    @Override
    public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
    {
    	if(!this.w.isRemote)
    	{
    		try
    		{
		    	ItemStack stack = this.NeoTile.getStackInSlot(p_75144_1_);
		    	if(this.nb == 0)
		    	{
			    	if(!stack.getItem().getUnlocalizedName().contains("11") & !stack.getItem().getUnlocalizedName().contains("12"))
			    	{
			    		PlayerStats stat = PlayerStats.get(p_75144_4_);
			    		if(stack.getItem().getUnlocalizedName().contains("13"))
			    		{
			    			   NBTTagCompound nbt = stack.getTagCompound();
			    			   if(nbt == null) { System.out.println("stack null"); return null; }
			    			  
			    			int prix = nbt.getInteger("prix");
			    			if(prix != 10)
			    			{
			    				if(stat.AsMoney(prix))
				    			{
				    				stat.removeMoney(prix, p_75144_4_);
				    				int nb;
				    				if(p_75144_1_ == 7)
				    				{
				    					nb = 4;
				    				}
				    				else
				    				{
				    					nb = p_75144_1_;
				    				}
				    				
				    				stat.lvlGuiDrag[0] = nb;
				    				 stat.QuestEvent("dragon_"+nb, p_75144_4_, 1);
				    				bdd.update("UPDATE `Neo-Stage-Dragon` SET `lvlgui`="+ nb +" WHERE  pseudo='" + p_75144_4_.getCommandSenderName() +"'");
				    				AnnimationManager.DownUp(p_75144_4_, "dripLava");
				    				p_75144_4_.playSound("mob.enderdragon.end", 0.5F, 0.5F);
				    				SoundManager.PlaySound(EnumSound.NeoEnderDrag.getSound(), p_75144_4_);
				    				p_75144_4_.openGui(main.instance, 0, p_75144_4_.worldObj,(int) p_75144_4_.posX, (int)p_75144_4_.posY,(int) p_75144_4_.posZ);
				    		    	
				    				
				    			}
					    		else
					    		{
					    			p_75144_4_.addChatMessage(new ChatComponentTranslation(C.DENY_NO_MONEY));	
					    		}
			    			}
			    		}
			    		else
			    		{
			    			try
				            {
				    			NBTTagCompound nbt = stack.getTagCompound();
						        int id = nbt.getInteger("prix");
						        if(id != 10)
				    			{
					    			if(stat.AsMoney(amelio[id]))
					    			{
					    				if(id == 0 || id == 1 || id == 2)
					    				{
					    					SoundManager.PlaySound(EnumSound.NeoMLvl1.getSound(),p_75144_4_);
					    				}else if(id == 3 || id == 4 || id == 5)
					    				{
					    					SoundManager.PlaySound(EnumSound.NeoMLvl2.getSound(),p_75144_4_);
					    				}else if(id == 6 || id == 7 || id == 8)
					    				{
					    					SoundManager.PlaySound(EnumSound.NeoMLvl3.getSound(),p_75144_4_);
					    				} else if(id == 9) {
					    					SoundManager.PlaySound(EnumSound.NeoMLvl4.getSound(),p_75144_4_);
					    				}
					    				
					    				stat.removeMoney(amelio[id], p_75144_4_);
								        stat.lvlGuiDrag[infoSlot[p_75144_1_ - 4]] = id + 1;
								        stat.QuestEvent("dragon_"+getSwitchNumber(infoSlot[p_75144_1_ - 4])+"_"+stat.lvlGuiDrag[infoSlot[p_75144_1_ - 4]], p_75144_4_, 1);
								        bdd.update("UPDATE `Neo-Stage-Dragon` SET `"+getSwitchNumber(infoSlot[p_75144_1_ - 4])+"`="+(id + 1)+" WHERE pseudo='"+p_75144_4_.getCommandSenderName()+"'");
								        p_75144_4_.openGui(main.instance, 0, p_75144_4_.worldObj,(int) p_75144_4_.posX, (int)p_75144_4_.posY,(int) p_75144_4_.posZ);
					    		    	
					    			}
					    			else
					    			{
					    				p_75144_4_.addChatMessage(new ChatComponentTranslation(C.DENY_NO_MONEY));	
					    			}
				    			}
				            }
				            catch (NumberFormatException ignore)
				            {
				            	p_75144_4_.addChatMessage(new ChatComponentTranslation(C.DENY_ERROR));	
				            }
			    		}
			    	}
		    	} else {
		    		if(!stack.getItem().getUnlocalizedName().contains("16"))
		    		{
		    			PlayerStats stat = PlayerStats.get(p_75144_4_);
		    			if(stack.getItem() instanceof ItemDragon2)
		    			{
		    			ItemDragon2 d = (ItemDragon2)stack.getItem();
		    			if(stat.DragBreed != d.getBreed().getId() && stat.AsDrag(d.getBreed().getId()))
		    			{
		    				System.out.println(d.getBreed().getId());
			    			stat.DragBreed = d.getBreed().getId();
			    			if(p_75144_4_.isRiding())
			        		{
			        			if(p_75144_4_.ridingEntity instanceof EntityTameableDragon)
			        			{
			        				((EntityTameableDragon)p_75144_4_.ridingEntity).setBreed(d.getBreed());
			        			}
			        		}
			    			else
			    			{
			    				 if(stat.cooldowndrag == 0)
			            		 {
			            			 stat.cooldowndrag = 60;
			    	        		 stat.QuestEvent("touche_2", p_75144_4_, 1);
			    	        		
			    	        	
			    					 EntityTameableDragon dragon = new EntityTameableDragon(p_75144_4_.worldObj, stat);
			    					 dragon.setPositionAndRotation(p_75144_4_.posX + 0.5, p_75144_4_.posY + 0.5, p_75144_4_.posZ + 0.5, p_75144_4_.rotationYaw, p_75144_4_.rotationPitch);
			    				   	 dragon.getReproductionHelper().setBreederName(p_75144_4_.getCommandSenderName());
			    				     dragon.getLifeStageHelper().setLifeStage(DragonLifeStage.valueOf(DragStage[stat.lvlGuiDrag[0]]));
			    				   	 dragon.setBreed(d.getBreed());
			    				   	 dragon.setSaddled(true);
			    				   	
			    				   	p_75144_4_.worldObj.spawnEntityInWorld(dragon);
			    				   	 dragon.setRidingStat(20);
			    				   	 dragon.setRidingPlayer(p_75144_4_);
			    				   	 dragon.tamedFor(p_75144_4_, true);
			    			
			            		 }
			    				 else
			    				 {
			    					 SoundManager.PlaySound(EnumSound.NeoMNope.getSound(), p_75144_4_);
			    				 }
			    			}
			    			SoundManager.PlaySound(EnumSound.NeoEnderDrag.getSound(), p_75144_4_);
		    				
			    			bdd.update("UPDATE "+bdd.getStringDrag()+" SET `breed`="+d.getBreed().getId()+" WHERE `pseudo`='"+p_75144_4_.getCommandSenderName()+"'");
			    			p_75144_4_.openGui(main.instance, 1, p_75144_4_.worldObj,(int) p_75144_4_.posX, (int)p_75144_4_.posY,(int) p_75144_4_.posZ);
		    		    	
		    			}
		    		}
		    			
		    		}
		    		
		    	}
	    	}
    		catch(ArrayIndexOutOfBoundsException e) {p_75144_4_.openGui(main.instance, 1, p_75144_4_.worldObj,(int) p_75144_4_.posX, (int)p_75144_4_.posY,(int) p_75144_4_.posZ);}
	        return null;
    	}
    	return null;
    }
    public String DragStage[] = new String[] {"EGG","HATCHLING","JUVENILE","ADULT","ADULT"};
    @Override
    public void onContainerClosed(EntityPlayer p_75134_1_)
    {
       this.NeoTile.closeInventory();
    }
    
    private String getSwitchNumber(int i)
    {
    	switch(i)
    	{
	    	case 1:
	    		return "health";
	    	case 2:
	    		return "speedground";
	    	case 3:
	    		return "speedair";
	    	case 4:
	    		return "fireballdamage";
	    	case 5:
	    		return "fireballspeed";
    	}
    	return "";
    }
}
