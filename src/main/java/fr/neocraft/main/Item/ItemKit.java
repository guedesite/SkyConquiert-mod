package fr.neocraft.main.Item;

import java.util.List;


import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemKit extends Item{
	
	private boolean isTemp;
	private String Kit;
	
	public ItemKit(String kit)
	{
		this.Kit = kit;
		if(kit == "tank" | kit == "tyran")
		{
			this.isTemp = true;
		}
		else
		{
			this.isTemp = false;
		}
		this.setMaxStackSize(1);
		this.setCreativeTab(main.neocraft);
		this.setUnlocalizedName("K_"+kit);
		this.setTextureName(neoreference.MOD_ID+":K_"+kit);
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
        
        p_77624_3_.add(EnumChatFormatting.BOLD + "Clique droit et gagnez");
        p_77624_3_.add(EnumChatFormatting.BOLD + "le kit "+this.Kit);
        if(this.isTemp) {
        	p_77624_3_.add(EnumChatFormatting.BOLD + "pendant 1 semaine !");
        }else {
        	p_77624_3_.add(EnumChatFormatting.BOLD + new ChatComponentTranslation("neo.life").getUnformattedText());
        }
      
    }
	@Override
    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
    	if(!w.isRemote) { 

    			fr.neocraft.main.Proxy.Serveur.Handler.NeoBukkit.executeCmd("addKit "+p.getCommandSenderName()+" "+this.Kit);
    		
    	}
    	return true;
    }
}	
