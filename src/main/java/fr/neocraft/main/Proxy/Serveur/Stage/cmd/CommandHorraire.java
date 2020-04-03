package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.ServeurManager.DonjonManager;
import fr.neocraft.main.ServeurManager.StageRepair;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class CommandHorraire extends CommandBase {

	private static final fr.neocraft.main.bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "Horraire";
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
    	return "";
    }
    
    /**
     * Return the required permission level for this command.
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
    	return true;
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 3;
    }
    public int compterOccurrences(String maChaine, char recherche)
	{
		 int nb = 0;
		 for (int i=0; i < maChaine.length(); i++)
		 {
			 if (maChaine.charAt(i) == recherche)
			 {
				 nb++;
			 }
		 }
		 return nb;
	}
    public String[] GetArrayString(String txt)
	{
		if(txt != null)
		{
			int t = txt.length();
			String[] finale = new String[compterOccurrences(txt, ':')];
			String pre = "";
			char cara, cara2;
			int d = 0;
			for(int i = 0; i < t; i++) 
			{
				
				cara = txt.charAt(i);
				if(cara == ':')
				{
					finale[d] = pre;
					pre = "";
					d++;
				}
				else
				{
					pre += cara;
				}
			}
			return finale;
		}
		else
		{
			return null;
		}
	}
    @Override
    public void processCommand(ICommandSender sender,final String[] params) {
    	if(params[0].equals("start"))
    	{
    		main.runHorraire();
    	} else if(params[0].equals("stop"))
    	{
    		main.stopHorraire();
    	} else if(params[0].equals("donjon"))
    	{
    		DonjonManager.tryDonjon();
    	} else if(params[0].equals("registerbox"))
    	{
    		EntityPlayer p = (EntityPlayer) sender;
    		for(ItemStack t:p.inventory.mainInventory)
    		{
    		
    
    			if(t != null)
    			{
					String enchId = "";
					String enchLvl = "";
					String Lore = "";
					int prix = 0;
					String name= new ItemStack(t.getItem()).getDisplayName();
					if(t.getDisplayName().contains(":"))
					{
						String[] r = GetArrayString(t.getDisplayName());
						prix = Integer.parseInt(r[0]);
						name = r[1];
					}
					else
					{
						 prix = Integer.parseInt(t.getDisplayName().replaceAll(" ", ""));
					}
					prix = Math.round(prix/t.stackSize);
					NBTTagCompound nbt = t.getTagCompound();
					if(nbt != null)
					{
						if (t.stackTagCompound.getTag("ench") != null) {
							NBTTagList enchants = (NBTTagList) t.stackTagCompound.getTag("ench");
							NBTTagCompound enchant;
							if(enchants != null)
							{
								for (int i = 0; i < enchants.tagCount(); i++) 
								{
									 enchant = ((NBTTagList) enchants).getCompoundTagAt(i);
									 enchId += ((int)enchant.getShort("id"))+"&";
									 enchLvl += ((int)enchant.getShort("lvl"))+"&";
								}
							}
						}
						if (t.stackTagCompound.getTag("StoredEnchantments") != null) {
							NBTTagList enchants = (NBTTagList) t.stackTagCompound.getTag("ench");
							NBTTagCompound enchant;
							if(enchants != null)
							{
								for (int i = 0; i < enchants.tagCount(); i++) 
								{
									 enchant = ((NBTTagList) enchants).getCompoundTagAt(i);
									 enchId += ((int)enchant.getShort("id"))+"&";
									 enchLvl += ((int)enchant.getShort("lvl"))+"&";
								}
							}
						}
						 NBTTagCompound disp = nbt.getCompoundTag("display");
					      if (disp != null) {
					         NBTTagList lore = (NBTTagList) disp.getTag("Lore") ;
					         if (lore != null) {
					        	 NBTTagCompound l;
					        	 for (int i = 0; i <lore.tagCount(); i++) 
								{
					        		 Lore += lore.getStringTagAt(i)+"&";
								}
					         }
					      }
					}
					bdd.execute("INSERT INTO "+bdd.getStringHdv()+"(`IdItem`, `IdItemDamage`, `NombreItem`, `NameItem`, `LoreItem`, `EnchantItemId`, `EnchantItemLvl`, `Player`, `Prix`,`effect`) VALUES "
							+ "("+getInfoBdd(Item.getIdFromItem(t.getItem()))+","+getInfoBdd(t.getItemDamage())+","+getInfoBdd(t.stackSize)+","+getInfoBdd(name)+","+getInfoBdd(Lore)+","+getInfoBdd(enchId)+","+getInfoBdd(enchLvl)+","+getInfoBdd("§Serveur")+","+getInfoBdd(prix)+","+getInfoBdd(1)+")");
					
    			}
    		}
    		M(p, "success");
    	} 
    	
    	else if(params[0].equals("setdragpos"))
    	{
    		
    		PrintWriter writer = null;
			try {
				writer = new PrintWriter(new File("assets/HorraireDrag.data"), "UTF-8");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			EntityPlayer p = (EntityPlayer)sender;
    		writer.write(p.posX+"");
    		writer.write(p.posY+"");
    		writer.write(p.posZ+"");
    		writer.write(p.rotationYawHead+"");
    		writer.write(p.rotationPitch+"");
    		M(p, "set");
    		writer.close();
    	} else if(params[0].equals("setDonjon"))
    	{
    		EntityPlayer player = (EntityPlayer)sender;
    		DonjonManager.RegisterDonjon(player);
    	}else if(params[0].equals("repairworld"))
    	{
    		StageRepair r = new StageRepair();
    		r.startCheckChunkStage();
    	}else if(params[0].equals("repairworlddouble"))
    	{
    		StageRepair r = new StageRepair();
    		r.startCheckMultipleStage();
    	}else if(params[0].equals("repairworldno"))
    	{
    		StageRepair r = new StageRepair();
    		r.StartCheckNoChunkAndStage();
    	}
    	else if(params[0].equals("repairspawner"))
    	{
    		StageRepair r = new StageRepair();
    		r.startRemoveSpawner();
    	}
    	else if(params[0].equals("repair0"))
    	{
    		StageRepair r = new StageRepair();
    		r.checkMultipleStage0();
    	}else if(params[0].equals("trydonjon"))
    	{
    		DonjonManager.tryDonjonTrue();
    	}
    	else if(params[0].equals("cleardonjon"))
    	{
    		DonjonManager.clearAllDonjon();
    	}else if(params[0].equals("reloadminage"))
    	{
    		RegisterStage.reloadMinage();
    	}else if(sender instanceof EntityPlayer){
    		M((EntityPlayer)sender, "nope");
    	}
    }
    private String getInfoBdd(String e)
	{
		if(e != null && e != " " && e != "")
		{
			return "'"+e+"'";
		}
		else
		{
			return "NULL";
		}
	}
	
	private String getInfoBdd(int e)
	{

	return e+"";

	}
    
    public boolean isOp(EntityPlayer player)
    {
    	boolean op = MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile());
        return op;
    }
    public void M(EntityPlayer p, String l)
    {
    	p.addChatMessage(new ChatComponentTranslation(l));
    }
}
