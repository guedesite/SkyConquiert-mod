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
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemGrade extends Item{


		private IIcon[] icon = new IIcon[8];
	
		public ItemGrade()
		{
			this.setMaxStackSize(1);
			this.setCreativeTab(main.neocraft);
			this.setUnlocalizedName("gradeitem");
		}
		 @SideOnly(Side.CLIENT)
		    public void registerIcons(IIconRegister o)
		    {
		       icon[0] = o.registerIcon(neoreference.MOD_ID + ":G_0");
		       icon[1] = o.registerIcon(neoreference.MOD_ID + ":G_1");
		       icon[2] = o.registerIcon(neoreference.MOD_ID + ":G_2");
		       icon[3] = o.registerIcon(neoreference.MOD_ID + ":G_3");
		       icon[4] = o.registerIcon(neoreference.MOD_ID + ":G_6");
		       icon[5] = o.registerIcon(neoreference.MOD_ID + ":G_7");
		       icon[6] = o.registerIcon(neoreference.MOD_ID + ":G_8");
		       icon[7] = o.registerIcon(neoreference.MOD_ID + ":G_9");
		       
		    }
		@SideOnly(Side.CLIENT)
	    public IIcon getIconFromDamage(int p)
	    {
			switch(p) 
			{
				case 1:
					return icon[p];
				case 2:
					return icon[p];
				case 3:
					return icon[p];
				case 6:
					return icon[4];
				case 7:
					return icon[5];
				case 8:
					return icon[6];
				case 9:
					return icon[7];
				default:
					return icon[0];
			}
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
	        p_77624_3_.add(EnumChatFormatting.BOLD + "le grade "+switchDamage(p_77624_1_.getItemDamage()));
	        p_77624_3_.add(EnumChatFormatting.BOLD + new ChatComponentTranslation("neo.life").getUnformattedText());
	      
	    }
		
		private String switchDamage(int i )
		{
			switch(i)
			{
				case 0:
					return "guerrier";
				case 1:
					return "chevalier";
				case 2:
					return "seigneur";
				case 3:
					return "prince";
				case 4:
					return "modojoueur";
				case 5:
					return "youtubeur";
					
				case 6:
					return new ChatComponentTranslation("neo.heros").getUnformattedText();
					
				case 7:
					return "roi";
					
				case 8:
					return "immortel";
					
				case 9:
					return "dieu";
					
				case 10:
					return "annimateur";
					
				case 11:
					return "modo";
					
				case 12:
					return "admin";
					
				default:
					return "NULL";
			}
		}
		
		@Override
	    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	    {
			if(!w.isRemote) {
				fr.neocraft.main.Proxy.Serveur.Handler.NeoBukkit.executeCmd("modsetgrade "+p.getCommandSenderName()+" "+switchDamage(i.getItemDamage()));
				
			}
	    	return true;
	    }


}
