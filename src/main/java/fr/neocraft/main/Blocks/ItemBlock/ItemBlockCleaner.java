package fr.neocraft.main.Blocks.ItemBlock;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockCleaner extends ItemBlock{

	public ItemBlockCleaner(Block p_i45328_1_) {
		super(p_i45328_1_);
	}

	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        
        p_77624_3_.add(EnumChatFormatting.BOLD + new ChatComponentTranslation("neo.cleaner").getUnformattedText());
   }
	
}
