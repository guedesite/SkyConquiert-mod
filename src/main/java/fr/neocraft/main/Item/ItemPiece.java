package fr.neocraft.main.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemPiece extends Item{

		private IIcon small = null;
		private IIcon medium = null;
		private IIcon big = null;
	
		public ItemPiece()
		{
			this.setMaxStackSize(1);
		}
	
	 @SideOnly(Side.CLIENT)
	    public IIcon getIconFromDamage(int p)
	    {

	         if(p < 501)
		     {
		        	return this.small;
		     }
	        else if(p < 1001)
		     {
	        	return this.medium;	
		     }
	        else
	        {
	        	return this.big;	
	        }
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public void registerIcons(IIconRegister p_94581_1_)
	    {
	        this.small = p_94581_1_.registerIcon(neoreference.MOD_ID + ":p250");
	        this.medium = p_94581_1_.registerIcon(neoreference.MOD_ID + ":p500");
	        this.big = p_94581_1_.registerIcon(neoreference.MOD_ID + ":p1000");
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack p_77636_1_)
	    {
		 	if(p_77636_1_.getItem() == this && p_77636_1_.getItemDamage() > 999)
		 	{
		 		return true;
		 	}
		 	return false;
	    }
	  
	  	@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
	    {
	        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	        
	        p_77624_3_.add(EnumChatFormatting.BOLD + "Clique droit et gagnez");
	        p_77624_3_.add(EnumChatFormatting.BOLD + ""+p_77624_1_.getItemDamage() +"$ !");
	      
	    }
	  	
	  	@Override
	    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	    {
	    	if(w.isRemote) { return true;}
	    	PlayerStats.get(p).giveMoney(i.getItemDamage(), p);
	    	i.splitStack(1);
	    	return true;
	    	
	    }
}
