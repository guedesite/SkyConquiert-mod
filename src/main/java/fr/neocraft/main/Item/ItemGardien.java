package fr.neocraft.main.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardien;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemGardien extends Item {
	
	  @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack p_77636_1_)
	    {
	        return true;
	    }
	  
	  	@SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_)
	    {
	        super.addInformation(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
	        
	        p_77624_3_.add(EnumChatFormatting.BOLD + "Protecteur,");
	        p_77624_3_.add(EnumChatFormatting.BOLD + new ChatComponentTranslation("neo.gardien.serment").getUnformattedText());
	        p_77624_3_.add(EnumChatFormatting.BOLD + new ChatComponentTranslation("neo.gardien.next").getUnformattedText());
	    }
	  	
	  	@Override
	    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int meta, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	    {
	    	if(w.isRemote) { return true;}
	    	Chunk c = w.getChunkFromBlockCoords((int)p.posX, (int)p.posZ);
	    	Stage stage = RegisterStage.getStageAtXY(p.chunkCoordX, p.chunkCoordZ, w);
	    	if(stage != null)
	    	{
	    		if(stage.isIn(p.getCommandSenderName()) || PlayerStats.get(p).AdminStage)
	    		{
	    		
	    			PlayerStats.get(p).QuestEvent("place_gardien", p, 1);
	    			EntityGardien gard = new EntityGardien(w);
	    			gard.setLocationAndAngles(p.posX+0.5, p.posY+1, p.posZ+0.5, p.rotationYaw, p.rotationPitch);
	    			gard.setStageId(stage.getId());
	    			w.spawnEntityInWorld(gard);
	    			gard.setStageId(stage.getId());
	    			c.addEntity(gard);
	    			i.splitStack(1);
	    			
	    			return true;
	    		}
	    	}
			return false;
	    	
	    }
	    	
}
