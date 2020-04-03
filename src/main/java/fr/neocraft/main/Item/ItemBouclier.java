package fr.neocraft.main.Item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBouclier extends Item {
	
	public ItemBouclier() {
		this.setMaxStackSize(1);
	}
	
	  @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack p_77636_1_)
	    {
	        return true;
	    }
	  
	  	@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
	    {
	        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	        p_77624_3_.add(EnumChatFormatting.BOLD + "Cliques droit pour activer le bouclier:");
	        int d = this.getDamage(p_77624_1_);
	        String txt="";
	        if(d> 24)
	        {
	        	d = d / 24;
	        	txt =d+" jour(s) !";
	        }
	        else {
	        	txt = d+" heur(s) !";
	        }

	        p_77624_3_.add(EnumChatFormatting.BOLD + "+"+txt);
	    }
	  	
	  	@Override
	    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	    {
	    	if(w.isRemote) { return true;}
	    	int d = this.getDamage(i);
	    	if(!PlayerStats.get(p).idStage.isEmpty())
	    	{
	    		Stage[] stage = RegisterStage.getStageArrayWithIdArray(PlayerStats.get(p).idStage);
	    		if(stage != null)
	    		{
		    		for(Stage e:stage)
		    		{
		    			if(e!= null)
		    			{
		    				e.giveBouclierHour(d);
		    			}
		    		}
		    		i.splitStack(1);
		    		ArrayList<String> sub = stage[0].getSubOwner();

			    		EntityPlayer tp;
			    		for(String e:sub)
			    		{
			    			if(e != null)
			    			{
				    			tp = RegisterStage.getPlayer(e);
				    			if(tp != null && tp.getCommandSenderName() != p.getCommandSenderName())
				    			{
				    				tp.addChatMessage(new ChatComponentTranslation("neo.EVENT_PLAYER_ADD_BOUCLIEROTHER", EnumChatFormatting.DARK_PURPLE+p.getCommandSenderName()));
				    			}
			    			}
			    		}

		    		p.addChatMessage(new ChatComponentTranslation("neo.EVENT_PLAYER_ADD_BOUCLIER"));
	    			return true;
	    		}else {
	    			p.addChatMessage(new ChatComponentTranslation("neo.DENY_PLAYER_ADD_BOUCLIER"));
	    			return false;
	    		}
	    	 }else {
	    		 p.addChatMessage(new ChatComponentTranslation("neo.DENY_PLAYER_ADD_BOUCLIER"));
	    		
	    	}
			return false;
	    	
	    }
	    	
}

