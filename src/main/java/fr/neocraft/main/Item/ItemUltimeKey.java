package fr.neocraft.main.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Blocks.Special.BoxUltimeBlock;
import fr.neocraft.main.Init.BlockMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemUltimeKey extends Item {
	
	  @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack p_77636_1_)
	    {
	        return true;
	    }
	  
	  	@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
	    {
	        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	        
	        p_77624_3_.add(EnumChatFormatting.BOLD + "/warp ultime");
	    } 
	  	
	  	@Override
	    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	    {
	    	if(w.isRemote) { return true;}
	    		if(w.getBlock(x, y, z) == BlockMod.UltimeBox)
	    		{
	    			((BoxUltimeBlock) w.getBlock(x, y, z)).onBlockActivated(w, x, y,  z, p, meta,  p_77648_8_,  p_77648_9_, p_77648_10_);
	    		}
	    	
	    	return true;
	    }

}
