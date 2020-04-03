package fr.neocraft.main.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Init.ItemMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemGuiDragon extends Item{

	
		public ItemGuiDragon(int id)
		{
			this.setCreativeTab(main.neocraft);
			this.setUnlocalizedName("lvl "+id);
			this.setTextureName(neoreference.MOD_ID + ":lvl_"+id);
		}
	    @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack p_77636_1_)
	    {
	    	return false;
	    }

	    
	    
	  	@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
	    {
	  		 super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	    }
}
