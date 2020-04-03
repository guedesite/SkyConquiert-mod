package fr.neocraft.main.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemArmorGod extends ItemArmorData{

	public ItemArmorGod(ArmorMaterial material, int metaData) {
		super(material, metaData);
	
	}
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack p_77636_1_)
    {
        return true;
    }
  
  	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        p_77624_3_.add(I18n.format("neo.godArmor.1"));
        p_77624_3_.add(I18n.format("neo.godArmor.2"));
        p_77624_3_.add(I18n.format("neo.godArmor.3"));
        p_77624_3_.add(I18n.format("neo.godArmor.4"));
        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
    }

}
