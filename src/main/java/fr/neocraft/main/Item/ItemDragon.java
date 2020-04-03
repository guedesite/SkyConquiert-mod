package fr.neocraft.main.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import info.ata4.minecraft.dragon.server.entity.breeds.DragonBreed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemDragon extends Item{
	private DragonBreed breed;
	public ItemDragon(DragonBreed b) 
	{
		this.breed = b;
		this.setMaxStackSize(1);
		this.setCreativeTab(main.neocraft);
		this.setUnlocalizedName("DragonItem");
		this.setTextureName(neoreference.MOD_ID + ":D_"+this.breed.getId());
	}
	
	public DragonBreed getBreed() {
		return this.breed;
	}
	
	

	@Override
    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	if(w.isRemote) { return true;}
    	PlayerStats stat = PlayerStats.get(p);
    	if(!stat.AsDrag(getBreed().getId()))
    	{
    		stat.AddDrag(getBreed().getId(), p.getCommandSenderName());
    		p.addChatMessage(new ChatComponentTranslation("neo.successdrag", getBreed().getName()));
    		if(p.isRiding())
    		{
    			if(p.ridingEntity instanceof EntityTameableDragon)
    			{
    				((EntityTameableDragon)p.ridingEntity).setBreed(getBreed());
    			}
    		}
    		i.splitStack(1);
    		return true;
    	}
    	else
    	{
    		p.addChatMessage(new ChatComponentTranslation("neo.errordrag", getBreed().getName()));
    	}
    	return false;
    	
    }
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
    {
        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        
        p_77624_3_.add(EnumChatFormatting.BOLD + new ChatComponentTranslation("neo.dragonlore").getUnformattedText());
        p_77624_3_.add(EnumChatFormatting.BOLD + new ChatComponentTranslation("neo.dragon2lore", EnumChatFormatting.GRAY+""+EnumChatFormatting.BOLD+""+getBreed().getName()).getUnformattedText());
    }
}
