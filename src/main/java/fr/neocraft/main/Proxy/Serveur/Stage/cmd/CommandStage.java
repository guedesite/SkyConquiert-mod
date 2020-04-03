package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.neocraft.main.NeoChat;
import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityPet;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.Stage.StageXpManager;
import fr.neocraft.main.Proxy.Serveur.event.StageEventFML;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientOpenGui;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientStageCmd;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.Proxy.Serveur.player.teleport;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;

public class CommandStage extends CommandBase {

	private static final NeoChat C = main.getChat();
	private static final bdd bdd = main.getbdd();
	private static final SimpleNetworkWrapper nw = main.getNetWorkClient();
	
    public String getCommandName() {
        return "neois";
    }
    
   
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.GRAY + "/is help";
    }
    
    /**
     * Return the required permission level for this command.
     */
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }

    public boolean checkCommandPermission(ICommandSender sender)
    {
       return true;
    }
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
	    	return true;
	}
    /**
     * Return the required permission level for this command.
     */

   
    public void processCommand(ICommandSender sender, final String[] params) {
    	int nb = params.length;
    	final EntityPlayer player = RegisterStage.getPlayer(params[0]);
    	final PlayerStats stat = PlayerStats.get(player);
    	final String name = player.getCommandSenderName();
        if (params.length < 2) {
        	stat.QuestEvent("cmd_null", player, 0);
        	if(RegisterStage.getFirstStageIdPlayer(stat) == -1)
            {
        			Stage stage = RegisterStage.getNoUseStage();
                	if(stage != null)
                	{
                		stat.idStage.add(stage.getId());
                		stage.setOwner(name);
                		player.refreshDisplayName();
						stage.SetIsUse(true);
						// teleport.player(stage.getIdWorld(), player, stage.getX(), stage.getY(), stage.getZ(), 5, false);
						 M(player,C.ALLOW_SUCCESS_GAIN_ILE);
						return;
                	}
		    	 M(player,C.DENY_ILE_FULL);
            }
            else
            {
            	Stage stage = RegisterStage.getFirstStagePlayer(stat);
        		if(stage != null)
        		{
	        		M(player, "neo.stageinfo.header");
	        		String chef = stage.getOwner();
	        		if(RegisterStage.getPlayer(chef) != null)
					{
    					chef = EnumChatFormatting.DARK_PURPLE+chef+" ["+new ChatComponentTranslation("neo.online").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"]";
    				}
	        		else
	        		{
	        			int id = bdd.GetFreeId();
	        			try {
    						ResultSet result = bdd.query("SELECT LastLogin FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+chef+"'", id);
    						result.next();
    						int jour = result.getInt("LastLogin");
    						String fjour = "null";
    						switch(jour)
    						{
    						case(15):
    							fjour=0+"j";
    							break;
    						case(14):
    							fjour=1+"j";
    							break;
    						case(13):
    							fjour=2+"j";
    							break;
    						case(12):
    							fjour=3+"j";
    							break;
    						case(11):
    							fjour=4+"j";
    							break;
    						case(10):
    							fjour=5+"j";
    							break;
    						case(9):
    							fjour=6+"j";
    							break;
    						case(8):
    							fjour=7+"j";
    							break;
    						case(7):
    							fjour=8+"j";
    							break;
    						case(6):
    							fjour=9+"j";
    							break;
    						case(5):
    							fjour=10+"j";
    							break;
    						case(4):
    							fjour=11+"j";
    							break;
    						case(3):
    							fjour=12+"j";
    							break;
    						case(2):
    							fjour=13+"j";
    							break;
    						case(1):
    							fjour=14+"j";
    							break;
    						case(0):
    							fjour="+14j";
    							break;
    						}
    						chef += EnumChatFormatting.DARK_PURPLE+" ["+new ChatComponentTranslation("neo.offline", "("+fjour+")").getUnformattedText()+""+EnumChatFormatting.DARK_PURPLE+"], ";
    					} catch(Exception o)
    					{
    						o.printStackTrace();
    						chef+= "[null]";
    					}
	        			bdd.CloseFreeId(id);
	        		}
	        		M(player, "neo.stageinfo.chef", EnumChatFormatting.DARK_PURPLE+""+chef );
	        		if(stage.getSubOwner() != null)
	        		{
	        			String F = "";
	        			for(String e: stage.getSubOwner())
	        			{
							if(RegisterStage.getPlayer(e) != null)
							{
	        					F += EnumChatFormatting.DARK_PURPLE+e+" ["+new ChatComponentTranslation("neo.online").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"], ";
	        				} else
	        				{
	        					int id = bdd.GetFreeId();
	        					try {
	        						ResultSet result = bdd.query("SELECT LastLogin FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+e+"'", id);
	        						result.next();
	        						int jour = result.getInt("LastLogin");
	        						String fjour = "null";
	        						switch(jour)
	        						{
	        						case(15):
	        							fjour=0+"j";
	        							break;
	        						case(14):
	        							fjour=1+"j";
	        							break;
	        						case(13):
	        							fjour=2+"j";
	        							break;
	        						case(12):
	        							fjour=3+"j";
	        							break;
	        						case(11):
	        							fjour=4+"j";
	        							break;
	        						case(10):
	        							fjour=5+"j";
	        							break;
	        						case(9):
	        							fjour=6+"j";
	        							break;
	        						case(8):
	        							fjour=7+"j";
	        							break;
	        						case(7):
	        							fjour=8+"j";
	        							break;
	        						case(6):
	        							fjour=9+"j";
	        							break;
	        						case(5):
	        							fjour=10+"j";
	        							break;
	        						case(4):
	        							fjour=11+"j";
	        							break;
	        						case(3):
	        							fjour=12+"j";
	        							break;
	        						case(2):
	        							fjour=13+"j";
	        							break;
	        						case(1):
	        							fjour=14+"j";
	        							break;
	        						case(0):
	        							fjour="+14j";
	        							break;
	        						}
	        						F += (EnumChatFormatting.DARK_PURPLE+e+" ["+new ChatComponentTranslation("neo.offline", "("+fjour+")").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"], ");
	        					} catch(Exception o)
	        					{
	        						o.printStackTrace();
	        						F+= "[null], ";
	        					}
	        					bdd.CloseFreeId(id);
	        				}
	        			}
	        			
	        			
	        			if(stage.getSubOwner().size() >1)
	        			{
	        				M(player, "neo.stageinfo.membre", F.substring(0, F.lastIndexOf(", ")));
	        			}
	        		}
	        		if(stage.getBouclier() != null)
	        		{
	        			M(player, " ");
	        			Calendar c = Calendar.getInstance();
	        			Calendar c2 = Calendar.getInstance();
	        			c2.setTime(stage.getBouclier());
	        			long diff = c2.getTimeInMillis() - c.getTimeInMillis();
	        			
	        			long diffMinutes = diff / (60 * 1000) % 60;
	        			long diffHours = diff / (60 * 60 * 1000) % 24;
	        			long diffDays = diff / (24 * 60 * 60 * 1000);
	        			if(1 > diffMinutes)
	        			{
	        				diffMinutes = 0;
	        			}
	        			if(1 > diffHours)
	        			{
	        				diffHours = 0;
	        			}
	        			if(1 > diffDays)
	        			{
	        				diffDays = 0;
	        			}
	        			M(player, EnumChatFormatting.LIGHT_PURPLE+"Bouclier: "+EnumChatFormatting.DARK_PURPLE+""+diffDays+" jour(s) "+diffHours + " heure(s) "+diffMinutes+" minute(s)");
	        		}
	        		if(!stat.idStage.isEmpty())
	        		{
		        		M(player, " ");
		        		M(player, "neo.stagelvl.info", EnumChatFormatting.DARK_PURPLE+""+RegisterStage.getFirstStagePlayer(stat).getLvl());
	        		}
		        	M(player, " ");
	        		M(player, "neo.stageinfo.div");

	        		Stage stage2;
	        		for(int e:stat.idStage)
	        		{ 
	        			
	        				stage2 = RegisterStage.getStageWithId(e);
	        				if(stage2 != null)
	        				{
	        					M(player, "neo.stageinfo.stage", EnumChatFormatting.DARK_PURPLE+""+stage2.getIdStage(), EnumChatFormatting.DARK_PURPLE+""+stage2.getBank()+"$");
	        					if(stage2.getIdStage() == 3) { 
	        						
	        						if(stage2.getSubOwner() != null) 
	        						{
	        							int all = main.LoyerStage3 + (stage2.getSubOwner().size()*main.FacteurLoyerStage3);
	        		        			M(player, "neo.loyer.mensuel", EnumChatFormatting.DARK_PURPLE+""+all);
	        						}
	        					}
	        				}
	        	
	        		}
	        		
	        		M(player, " ");
        		}
	        	else
	        	{
	        		 M(player,C.DENY_NO_ILE);		
	        	}
            }
        }
        else
        {
        	stat.QuestEvent("cmd_"+params[1], player, 0);
        	if(params[1].equals("welcome"))
	        {
        		main.getNetWorkClient().sendTo(new NetWorkClient(new ClientOpenGui(-1)), (EntityPlayerMP) player);
	        }
        	else  if(params[1].equals("home"))
	        {
	        	if(!stat.idStage.isEmpty())
	        	{
	        		if(stat.isInAttackWithMessage(player))
	        		{
		        		HashMap<String, Integer> m = new HashMap<String, Integer>();
		        		
		        		for(int id:stat.idStage)
		        		{
		        			Stage s = RegisterStage.getStageWithId(id);
		        			m.put("("+s.getId()+") Stage "+s.getIdStage()+": World="+s.getIdWorld()+", X="+s.getX()+", Y="+s.getY()+", Z="+s.getZ(), id);
		        		}
		        		
		        		nw.sendTo(new NetWorkClient(new ClientStageCmd(m, 0)),(EntityPlayerMP) player);
	        		}
		        	/*if (params.length > 2 ) {
			        	try
			            {
			             int i = Integer.parseInt(params[2]);
			              if(i > 4)
			              {
			            	   M(player,C.DENY_NOMBRE_NOT_VALID);
			            	  return;
			              }
			              else
			              {
			            	  if(stat.idStage[i] != -1)
			            	  {
					              Stage stage = RegisterStage.getStageWithId(stat.idStage[i]);
					              if(stage != null)
					              {
						            	 if(stage.hasHome())
						            	 {
							        		 teleport.player(stage.getIdWorld(), player, stage.getX(), stage.getY(), stage.getZ(), 3, false);
							        	 }
							        	 else
							        	 {
							        		  M(player,C.DENY_NO_HOME);	
							        	 }
					              }
					              else
					              {
					            	   M(player,C.DENY_NOMBRE_NOT_VALID);	

										  					            	   
					              }
			            	  }
			            	  else
			            	  {
			            		   M(player,C.DENY_NOMBRE_NOT_VALID);	
			            	  }
			              }
			            }
			            catch (NumberFormatException ignore)
			            {
			            	 M(player,C.DENY_NOMBRE_NOT_VALID);	
			            } */
	
		       
	        	}
	        	else
	        	{
	        		 M(player,C.DENY_NO_ILE);		
	        	}
	
	        }
	      /*  else if(params[1].equals("sethome"))
	        {
	        	if(!stat.idStage.isEmpty())
	        	{
	        		HashMap<String, Integer> m = new HashMap<String, Integer>();
	        		
	        		for(int id:stat.idStage)
	        		{
	        			Stage s = RegisterStage.getStageWithId(id);
	        			m.put("("+s.getId()+") Stage "+s.getIdStage()+": World="+s.getIdWorld()+", X="+s.getX()+", Y="+s.getY()+", Z="+s.getZ(), id);
		        	}
	        		
	        		nw.sendTo(new NetWorkClient(new ClientStageCmd(m, 0)),(EntityPlayerMP) player);
	        	}
	        	else
	        	{
	        		 M(player,C.DENY_NO_ILE);		
	        	}
	
	        } */
	        else if(params[1].equals("ally") || params[1].equals("alliance"))
	        {
	        	if(!stat.idStage.isEmpty())
	        	{
	        		if(params.length == 3)
	        		{
	        			boolean flag = false;
	        			
	        			Stage stage;
	        			for(int e:stat.idStage)
	        			{
	        				if(e != -1)
	        				{
	        					stage = RegisterStage.getStageWithId(e);
	        					if(stage != null)
	        					{
	        						if(!stage.isAlly(params[2]))
	        						{
		        						stage.addAlly(params[2]);
		        						flag = true;
	        						}
	        						if(stage.isEnnemy(params[2]))
	        						{
	        							stage.removeEnnemy(params[2]);
	        						}
	        					}
	        				}
	        			}
	        			if(flag)
	        			{
	        				M(player,"neo.ALLOW_ALLIANCE", params[2]);
	        				EntityPlayer get = RegisterStage.getPlayer(player.getCommandSenderName());
		        			if(get != null)
		        			{
		        				M(get,"neo.ALLOW_ALLIANCE_WITH", name);
		        			}
	        			} else {
	        				M(player,"neo.DENY_EVER_ALLIANCE");
	        			}
	        		}
	        		else
	        		{
	        			M(player,getCommandUsage(sender));
	        		}
	        	}else
	        	{
	        		 M(player,C.DENY_NO_ILE);		
	        	}
	        }  else if(params[1].equals("ennemy") || params[1].equals("ennemie")|| params[1].equals("�nemie"))
	        {
	        	if(!stat.idStage.isEmpty())
	        	{
	        		if(params.length == 3)
	        		{
	        			
	        			Stage stage;
	        			boolean flag = false;
	        			for(int e:stat.idStage)
	        			{
	        				if(e != -1)
	        				{
	        					stage = RegisterStage.getStageWithId(e);
	        					if(stage != null)
	        					{
	        						if(!stage.isEnnemy(params[2]))
	        						{
	        							flag = true;
	        							stage.addEnnemy(params[2]);
	        						} 
	        						if(stage.isAlly(params[2]))
	        						{
	        							stage.removeAlly(params[2]);
	        						}
	        					}
	        				}
	        			}
	        			if(flag)
	        			{
	        				M(player,"neo.ALLOW_ENNEMY", params[2]);
	        				EntityPlayer get = RegisterStage.getPlayer(params[2]);
		        			if(get != null)
		        			{
		        				M(get,"neo.ALLOW_ENNEMY_WITH", name);
		        			}
	        			} else {
	        				M(player,"neo.DENY_EVER_ENNEMY");
	        			}
	        		}
	        		else
	        		{
	        			M(player,getCommandUsage(sender));
	        		}
	        	}else
	        	{
	        		 M(player,C.DENY_NO_ILE);		
	        	}
	        } else if(params[1].equals("neutral"))
	        {
	        	if(!stat.idStage.isEmpty())
	        	{
	        		if(params.length < 3)
	        		{
	        			boolean flag = false;
	        			
	        			Stage stage;
	        			for(int e:stat.idStage)
	        			{
	        				if(e != -1)
	        				{
	        					stage = RegisterStage.getStageWithId(e);
	        					if(stage != null)
	        					{
	        						if(stage.isEnnemy(params[2]))
	        						{
	        							stage.removeEnnemy(params[2]);
	        							flag = true;
	        						}
	        						if(stage.isAlly(params[2]))
	        						{
	        							stage.removeAlly(params[2]);
	        							flag = true;
	        						}
	        					}
	        				}
	        			}
	        			if(flag)
	        			{
	        				M(player,"neo.ALLOW_NEUTRAL", params[2]);
	        				EntityPlayer get = RegisterStage.getPlayer(params[2]);
		        			if(get != null)
		        			{
		        				M(get,"neo.ALLOW_NEUTRAL_WITH", name);
		        			}
	        			}else {
	        				M(player,"neo.DENY_EVER_NEUTRAL");
	        			}
	        		}
	        		else
	        		{
	        			M(player,getCommandUsage(sender));
	        		}
	        	}else
	        	{
	        		 M(player,C.DENY_NO_ILE);		
	        	}
	        }
	        else if(params[1].equals("info"))
	        {
	        		Stage stage = RegisterStage.getStageAtXY(player.chunkCoordX, player.chunkCoordZ, player.worldObj);
	        		if(stage != null)
	        		{
		        		M(player, "neo.stageinfother.header", EnumChatFormatting.DARK_PURPLE+stage.getName());
		        		String chef = stage.getOwner();
		        		if(RegisterStage.getPlayer(chef) != null)
						{
	    					chef = EnumChatFormatting.DARK_PURPLE+chef+" ["+new ChatComponentTranslation("neo.online").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"]";
	    				}
		        		else
		        		{
		        			int id = bdd.GetFreeId();
		        			try {
	    						ResultSet result = bdd.query("SELECT LastLogin FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+chef+"'", id);
	    						result.next();
	    						int jour = result.getInt("LastLogin");
	    						String fjour = "null";
	    						switch(jour)
        						{
        						case(15):
        							fjour=0+"j";
        							break;
        						case(14):
        							fjour=1+"j";
        							break;
        						case(13):
        							fjour=2+"j";
        							break;
        						case(12):
        							fjour=3+"j";
        							break;
        						case(11):
        							fjour=4+"j";
        							break;
        						case(10):
        							fjour=5+"j";
        							break;
        						case(9):
        							fjour=6+"j";
        							break;
        						case(8):
        							fjour=7+"j";
        							break;
        						case(7):
        							fjour=8+"j";
        							break;
        						case(6):
        							fjour=9+"j";
        							break;
        						case(5):
        							fjour=10+"j";
        							break;
        						case(4):
        							fjour=11+"j";
        							break;
        						case(3):
        							fjour=12+"j";
        							break;
        						case(2):
        							fjour=13+"j";
        							break;
        						case(1):
        							fjour=14+"j";
        							break;
        						case(0):
        							fjour="+14j";
        							break;
        						}
	    						chef += EnumChatFormatting.DARK_PURPLE+" ["+new ChatComponentTranslation("neo.offline", "("+fjour+")").getUnformattedText()+""+EnumChatFormatting.DARK_PURPLE+"], ";
	    					} catch(Exception o)
	    					{
	    						o.printStackTrace();
	    						chef+= "[null]";
	    					}
		        			bdd.CloseFreeId(id);
		        		}
		        		M(player, "neo.stageinfo.chef", EnumChatFormatting.DARK_PURPLE+""+chef );
		        		if(stage.getSubOwner() != null)
		        		{
		        			String F = "";
		        			for(String e: stage.getSubOwner())
		        			{
								if(RegisterStage.getPlayer(e) != null)
								{
		        					F += EnumChatFormatting.DARK_PURPLE+e+" ["+new ChatComponentTranslation("neo.online").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"], ";
		        				} else
		        				{
		        					int id = bdd.GetFreeId();
		        					try {
		        						ResultSet result = bdd.query("SELECT LastLogin FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+e+"'", id);
		        						result.next();
		        						int jour = result.getInt("LastLogin");
		        						String fjour = "null";
		        						switch(jour)
		        						{
		        						case(15):
		        							fjour=0+"j";
		        							break;
		        						case(14):
		        							fjour=1+"j";
		        							break;
		        						case(13):
		        							fjour=2+"j";
		        							break;
		        						case(12):
		        							fjour=3+"j";
		        							break;
		        						case(11):
		        							fjour=4+"j";
		        							break;
		        						case(10):
		        							fjour=5+"j";
		        							break;
		        						case(9):
		        							fjour=6+"j";
		        							break;
		        						case(8):
		        							fjour=7+"j";
		        							break;
		        						case(7):
		        							fjour=8+"j";
		        							break;
		        						case(6):
		        							fjour=9+"j";
		        							break;
		        						case(5):
		        							fjour=10+"j";
		        							break;
		        						case(4):
		        							fjour=11+"j";
		        							break;
		        						case(3):
		        							fjour=12+"j";
		        							break;
		        						case(2):
		        							fjour=13+"j";
		        							break;
		        						case(1):
		        							fjour=14+"j";
		        							break;
		        						case(0):
		        							fjour="+14j";
		        							break;
		        						}
		        						F += (EnumChatFormatting.DARK_PURPLE+e+" ["+new ChatComponentTranslation("neo.offline", "("+fjour+")").getUnformattedText()+EnumChatFormatting.DARK_PURPLE+"], ");
		        					} catch(Exception o)
		        					{
		        						F+= "[null], ";
		        					}
		        					bdd.CloseFreeId(id);
		        				}
		        			}
		        			if(stage.getSubOwner().size() >1)
		        			{
		        				M(player, "neo.stageinfo.membre", F.substring(0, F.lastIndexOf(", ")));
		        			}
		        		}
		        		if(stage.getBouclier() != null)
		        		{
		        			M(player, " ");
		        			Calendar c = Calendar.getInstance();
		        			Calendar c2 = Calendar.getInstance();
		        			c2.setTime(stage.getBouclier());
		        			long diff = c2.getTimeInMillis() - c.getTimeInMillis();
		        			
		        			long diffMinutes = diff / (60 * 1000) % 60;
		        			long diffHours = diff / (60 * 60 * 1000) % 24;
		        			long diffDays = diff / (24 * 60 * 60 * 1000);
		        			if(1 > diffMinutes)
		        			{
		        				diffMinutes = 0;
		        			}
		        			if(1 > diffHours)
		        			{
		        				diffHours = 0;
		        			}
		        			if(1 > diffDays)
		        			{
		        				diffDays = 0;
		        			}
		        			M(player, EnumChatFormatting.LIGHT_PURPLE+"Bouclier: "+EnumChatFormatting.DARK_PURPLE+""+diffDays+" jour(s) "+diffHours + " heure(s) "+diffMinutes+" minute(s)");
		        		}
		        		
			        		M(player, " ");
			        		M(player, "neo.stagelvl.info", EnumChatFormatting.DARK_PURPLE+""+stage.getLvl());
		        		
		        		M(player, " ");
		        		M(player, "neo.stageinfo.idstage", EnumChatFormatting.DARK_PURPLE+""+stage.getIdStage());
		        		M(player, "neo.stageinfo.bank", EnumChatFormatting.DARK_PURPLE+""+stage.getBank()+"$");
		        		M(player, " ");
	        		}
		        	else
		        	{
		        		 M(player,"neo.DENY_BEILE");		
		        	}
	
	        }else if(params[1].equals("disband"))
	        {
	        	if(!stat.idStage.isEmpty())
	        	{
	        		if(stat.LasteDisband == 0)
	        		{
		        		final Stage stage = RegisterStage.getFirstStagePlayer(stat);
		        		if(stage.isOwner(name))
			        	{
				        	if (params.length > 2 ) 
				        	{
				        		if(params[2].equals("confirmer") || params[2].equals("confirm") || params[2] == "confirmer" || params[2] == "confirm")
				        		{

				        			Stage stage2;
				        			ArrayList<String> sub = stage.getSubOwner();
				        			
	            						 EntityPlayer ptemp;
	            						 Stage stagetemp;
	            						 
	            						 
	            						 for(String d:sub)
	            						 {
	            							 ptemp = RegisterStage.getPlayer(d);
	            							 if(ptemp !=null)
	            							 {
	            								 PlayerStats.get(ptemp).idStage = new ArrayList<Integer>();
	            								 if(RegisterStage.getStageAtXY(ptemp.chunkCoordX, ptemp.chunkCoordZ, ptemp.worldObj).getId() == stage.getId())
	            								 {
	            									 teleport.player(0, ptemp,520, 110, 600, 0, false);
	            								 }
	            								 M(ptemp,"neo.ALLOW_ILS_BEING_DISBAND", name);
	            							 }
	            						 }
	            						 
	            					 
						        			for(int o:stat.idStage)
						        			{
						        				if(o != -1)
						        				{
						        				
							        				stage2 = RegisterStage.getStageWithId(o);
							        				stage2.setBouclierNull();
							        				stage2.setArraySubOwner(null);
							        				stage2.setAlly(null);
							        				stage2.setEnnemy(null);
							        				stage2.setLvl(0);
							        				stage2.setName("Ile-"+stage2.getId());
							        				stage2.SetIsUse(false);
							        				stat.giveMoney(stage2.getBank(), player);
							        				stage2.setBank(RegisterStage.getBankForStage(stage2.getIdStage()));
							        				stage2.setOwner(null);
							        				stage2.setPermAll(bdd.GetArrayListString(RegisterStage.getPermForStage(stage2.getIdStage())));
							        				stage2.setPermMember(bdd.GetArrayListString(RegisterStage.getPermForStageMember(stage2.getIdStage())));
							        				stage2.setPermAllie(null);
						        				}
						        			}
						        			CommandStage0Gen.ResetStage0(DimensionManager.getWorld(stage.getIdWorld()), stage);
						        			stat.idStage = new ArrayList<Integer>();
						        			stat.LasteDisband = 7;
						        			bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `LasteDisband`=7 WHERE pseudo='"+name+"'");
						        			 teleport.player(0, player,520, 110, 600, 0, false);
						        			 M(player,C.ALLOW_DISBAND);	
						
				        		}
				        		else
				        		{
				        			 M(player,C.CONFIRM_DISBAND);	
				        		}
					        	
				        	}
				        	else
				        	{
				        		 M(player,C.CONFIRM_DISBAND);	
				        	}
			        	}
		        		else
		        		{
		        			 M(player,C.DENY_NOT_ADMIN);	
		        		}
	        		} else
	        		{
	        			M(player,"neo.DENY_CANT_DISBAND_ALOT", stat.LasteDisband+"");		
	        		}
	        	}
	        	else
	        	{
	        		 M(player,C.DENY_NO_ILE);	
	        	}
	
	        }else if(params[1].equals("vendre") || params[1].equals("sell"))
	        {
	        	if(params.length > 2)
	        	{
	        		try
		            {
		              int i = Integer.parseInt(params[2]);
		              if(i > 3 || i == 0)
		              {
		            	   M(player,C.DENY_NOMBRE_NOT_VALID);
		            	  return;
		              }
		              else
		              {
		            	/*  if(stat.idStage[i] != -1)
		            	  {
				             Stage stage = RegisterStage.getStageWithId(stat.idStage[i]);
				             String[] sub =  stage.getSubOwner();
				             stat.idStage[i] = -1;
				             if(sub != null)
				             {
				            	 for(String e:sub)
				            	 {
				            		 EntityPlayer tempPlayer = RegisterStage.getPlayer(e);
				            		 if(e != null)
				            		 {
				            			 PlayerStats.get(tempPlayer).idStage[i] = -1;
				            		 }
				            	 }
				             }
				             	stage.setBouclierNull();
		        				stage.setArraySubOwner(null);
		        				stage.setAlly(null);
		        				stage.setEnnemy(null);
		        				stage.setName("Ile-"+stage.getId());
		        				stage.SetIsUse(false);
		        				stat.giveMoney(stage.getBank(), player);
		        				stage.setBank(RegisterStage.getBankForStage(stage.getIdStage()));
		        				stage.setOwner(null);
		        				stage.setPermAll(bdd.GetArrayString(RegisterStage.getPermForStage(stage.getIdStage())));
		        				stage.setPermMember(bdd.GetArrayString(RegisterStage.getPermForStageMember(stage.getIdStage())));
		        				stage.setPermAllie(null);
		        				M(player,"neo.sell.yes");
		            	  }
		            	  else
		            	  {
		            		   M(player,C.DENY_NOMBRE_NOT_VALID);	
		            	  } */
		              }
		            }
		            catch (NumberFormatException ignore)
		            {
		            	 M(player,C.DENY_NOMBRE_NOT_VALID);	
		            }
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	
	        }
	        else if(params[1].equals("invite"))
	        {
	        	if (params.length > 2 ) {
		        		if(RegisterStage.getPlayer(params[2]) != null)
		        		{
			        		if(!stat.idStage.isEmpty())
			                {
			            		 Stage stage = RegisterStage.getFirstStagePlayer(stat);
			            		 if(stage != null)
			            		 {
				            		 if(stage.isOwner(name))
				            		 {
				            			 EntityPlayer player2 = (EntityPlayer) RegisterStage.getPlayer(params[2]);
				            			if(!stat.hasInvite(name))
				            			{
				            				PlayerStats.get(player2).addInvite(name, player);
				            				 M(player,C.ALLOW_INVITE);	
				            				M(player2,C.ALLOW_RECIEVE_INVITE);	
				            			 }
					            		 else
					          			{
					                  		  M(player,C.DENY_DEJA_INVITE);			
					          			} 
				            				
				            		 }
				            		 else
				          			{
				                  		  M(player,C.DENY_NOT_ADMIN);			
				          			} 
			            		 }
			            		 else
			            		 {
			            			 M(player,C.DENY_NO_ILE);		
			            		 }
			                } 
			        		else
			    			{
			           		  M(player,C.DENY_NO_ILE);			
			    			}
		        		}
		        		else
		    			{
		           			 M(player,C.DENY_PLAYER_NOT_ONLINE);			
		    			}
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }
	        else if(params[1].equals("setname") | params[1].equals("name") | params[1].equals("rename"))
	        {
	        	if(!stat.idStage.isEmpty())
	            {
	        		 Stage stage = RegisterStage.getFirstStagePlayer(stat);
	        		 if(stage.isOwner(name))
	        		 {
	        			 if(params.length > 2)
	        			 {
	        				 String newname = params[2].replaceAll(" ", "-");
	        				 if(newname.length() > 15)
	        				 {
	        					 newname.substring(0, newname.length() - newname.length() - 15);
	        				 }
	        				 Stage e;
	        				 for(int id:stat.idStage)
	        				 {
	        					 e=RegisterStage.getStageWithId(id);
	        					 if(e != null)
	        					 {
	        						 e.setName(newname);
	        					 }
	        				 }
	        				 player.refreshDisplayName();
	        				 ArrayList<String> name2 = stage.getSubOwner();
	        				 if(!name2.isEmpty())
	        				 {
		        				 for(String s: name2)
		        				 {
		        					 EntityPlayer pp = RegisterStage.getPlayer(s);
		        					 if(pp != null)
		        					 {
		        						 pp.refreshDisplayName();
		        					 }
		        				 }
	        				 }
	        				 M(player,C.ALLOW_NEW_NAME);
	        						 
	        			 }
	        			 else
	        			 {
	        				 M(player,getCommandUsage(sender)); 
	        			 }
	        		 } else
	        		 {
	        			 M(player,C.DENY_NOT_ADMIN);
	        		 }
	        		
	            }
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("myinvite"))
	        {
	        		List inv = stat.getInvite();
	        		if(!inv.isEmpty())
	        		{
	        			 M(player,C.ALLOW_LIST_INVITE);	
	        			for (int i = 0; i < inv.size(); i++)
	        	        {
	        				
	        					 M(player,C.red + "_" + inv.get(i));
	        	        }
	        		}
	            	else
	            	{
	            		 M(player,C.DENY_NO_INVITE);	
	            	}
	        }
	        else if(params[1].equals("join"))
	        {
	        	if (params.length > 2 ) {
	        		if(params[2].equals("confirm") || params[2] == "confirm")
	        		{
	        			if(stat.confirmjoin)
	        			{

	
	        				ArrayList<Stage> stage2 = RegisterStage.getStageWithPlayer(stat.confirmjoindata);
		        			if(!stage2.isEmpty())
		        			{
		        				boolean verif = false;
			        			if(stage2.get(0) != null)
			        			{
				        			if(stage2.get(0).getSubOwner().size() <= RegisterStage.MaxSubOwner)
				        			{
				        				verif = true;
				        			}
			        			}
			        			else
			        			{
			        				verif = true;
			        			}
		        				if(verif)
		        				{
		        					if(!stat.idStage.isEmpty())
			        				{
			        					Stage stage = RegisterStage.getFirstStagePlayer(stat);
			        					if(stage.isOwner(name))
			        					{
			        						 M(player,C.DENY_OWNER_OF_HIS_ILE);
			        						return;
			        					}
			        					Stage stage3 = null;
					        			for(int o:stat.idStage)
					        			{
					        				if(o != -1)
					        				{
						        				stage3 = RegisterStage.getStageWithId(o);
						        				stage3.removeSubOwner(player.getCommandSenderName());
					        				}
					        			}
					        			if(stage3 != null)
					        			{
						        			if(RegisterStage.getPlayer(stage3.getOwner()) != null)
						        			{
						        				M(player,"neo.EVENT_PLAYER_QUIT", name);
						        			}if(stage3.getSubOwner() != null)
						        			{
						        				for(String e:stage3.getSubOwner())
						        				{
						        					if(RegisterStage.getPlayer(e) != null)
								        			{
						        						M(player,"neo.EVENT_PLAYER_QUIT", name);
								        			}
						        				}
						        			}
					        			}
			        				}
		        					stat.idStage = new ArrayList<Integer>();
		        					for(int i = 0; i < stage2.size(); i++)
		        					{
		        						if(stage2.get(i) != null)
		        						{
			        						
			        						stage2.get(i).addSubOwner(name);
			        						stat.idStage.add(stage2.get(i).getId());
		        						}
		        					}
			        				stat.clearInvite(stat.confirmjoindata);
			        				 M(player,C.ALLOW_NEW_ILE);
			        				
		        				}
		        				else
		        				{
		        					 M(player,C.DENY_TO_MANY_PLAYER_IN_ILE);
		        				}
		        			}
		        			else
		        			{
		        				 M(player,C.DENY_INVITE_NOT_EXIST);
		        			}
	        			}
	        			else
	        			{
	        				 M(player,C.DENY_INVITE_NOT_EXIST);
	        			}
	        				
	        		}
	        		else
	        		{
		        		if(stat.hasInvite(params[2]))
		        		{
		        			if(!stat.idStage.isEmpty())
		        			{
		        				stat.confirmjoin = true;
		        				stat.confirmjoindata = params[2];
		        				 M(player,C.CONFIRM_INVITE_HAS_STAGE);	
		        				 M(player,C.CONFIRM_INVITE_HAS_STAGE_2);	
		        			}
		        			else
		        			{
		        				stat.confirmjoin = true;
		        				stat.confirmjoindata = params[2];
		        				 M(player,C.CONFIRM_INVITE);			
		        			}
		        		}
		        		else
		        		{
		        			 M(player,C.DENY_INVITE_NOT_EXIST);			
		        		}
	        		}
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        		
	        } else if(params[1].equals("quit"))
	        {
	        	if(!stat.idStage.isEmpty())
                {
	        		Stage stage = RegisterStage.getStageWithId(stat.idStage.get(0));
	        		if(stage != null)
	        		{
	        			if(stage.isOwner(name))
	        			{
	        				M(player, "neo.DENY_OWNER_OF_HIS_ILE");
	        			}else {
	        				for(int e:stat.idStage)
		        			{
		        				if(e != -1)
		        				{
		        					stage = RegisterStage.getStageWithId(e);
		        					stage.removeSubOwner(name);
		        					
		        				}
		        			}
	        				M(player, "neo.succ.left");
	        				stat.idStage.clear();
	        			}
	        		}else
	    			{
	             		  M(player,C.DENY_NO_ILE);			
	      			}
                } 
        		else
    			{
           		  M(player,C.DENY_NO_ILE);			
    			}
	        }
	        else if(params[1].equals("kick"))
	        {
	        	if (params.length > 2 ) 
	        	{
		        		if(!stat.idStage.isEmpty())
		                {
		            		 Stage stage = RegisterStage.getFirstStagePlayer(stat);
		            		 
		            		 if(stage.isOwner(name))
		            		 {
		            			 if(name != params[2])
		            			 {
		            				 if(stage.isSubOwner(params[2]))
		            				 {
		            					 stage.removeSubOwner(params[2]);
		            					 ArrayList<String> sub = stage.getSubOwner();
		            					 if(!sub.isEmpty())
		            					 {
		            						 EntityPlayer ptemp;
		            						 for(String e:sub)
		            						 {
		            							 ptemp = RegisterStage.getPlayer(e);
		            							 if(e !=null)
		            							 {
		            								 M(ptemp,"neo.EVENT_PLAYER_KICK", params[2], name);
		            							 }
		            						 }
		            					 }
		            					 EntityPlayer kicked = RegisterStage.getPlayer(params[2]);
		            					 if(kicked != null)
		            					 {
		            						 M(kicked, "neo.ALLOW_BEING_KICK");
		            					 }
		            					 M(player, "neo.ALLOW_BEING_KICK");
		            				 } else {
		            					 EntityPlayer playertokick = RegisterStage.getPlayer(params[2]);
		            					 if(playertokick != null)
		            					 {
		            						 Stage stageplayer = RegisterStage.getStageAtXY(player.chunkCoordZ, player.chunkCoordX, player.worldObj);
		            						 if(stageplayer != null)
		            						 {
		            							 if(stageplayer.getId() == stage.getId())
		            							 {
		            								 teleport.player(0, playertokick, 5000, 81, 5000,0, false);
		            								 M(playertokick,"neo.ALLOW_BE_KICK");
		            								 M(player,"neo.ALLOW_KICK_PLAYER", playertokick.getCommandSenderName());
		            							 } else
			            						 {
			            							 M(player,"neo.DENY_PLAYER_NOT_IN_ILS");
			            						 }
		            						 }
		            						 else
		            						 {
		            							 M(player,"neo.DENY_PLAYER_NOT_IN_ILS");
		            						 }
		            					 } else
		            					 {
		            						 M(player,C.DENY_NO_PLAYER);		 
		            					 }
		            				 }
		            			 }
		            			 else {
		            				 M(player,C.DENY_OWNER_OF_HIS_ILE);		
		            			 }
		            			 
		            		
		            		 }
		            		 else
		          			{
		                  		  M(player,C.DENY_NOT_ADMIN);			
		          			} 
		                } 
		        		else
		    			{
		           		  M(player,C.DENY_NO_ILE);			
		    			}

	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("by") || params[1].equals("acheter"))
	        {

	        		Stage stage = RegisterStage.getFirstStagePlayer(stat);
	        		if(stage.isOwner(name))
	        		{
	        			Stage stagetoby = RegisterStage.getStageAtXY(player.chunkCoordX, player.chunkCoordZ, player.worldObj);
	        			if(stagetoby != null && !stagetoby.isOwner(name))
	        			{
	        				if(stagetoby.getIdStage() == 3 && stagetoby.IsUse())
		        			{
		        			
		        				M(player,"neo.DENY_NO_GOOD2");	
		        				M(player,"neo.DENY_NO_GOOD3");	
		        				return;
		        			
		        			}
		        			if(stagetoby.getIdStage() != 0 && stagetoby.getIdStage() < 4 )
		        			{
		        			/*	if(stat.idStage[stagetoby.getIdStage()] == -1 )
		        				{ */
		        					if(stagetoby.CanBouclier())
			        				{
			        					if(stat.AsMoney(stagetoby.getBank()))
			        					{
			        						if(params.length == 3)
			        						{
			        							stat.removeMoney(stagetoby.getBank(), player);
			        							if(stagetoby.IsUse())
			        							{
				        							EntityPlayer p2 = RegisterStage.getPlayer(stagetoby.getOwner());
				        							if(p2 != null)
				        							{
				        								PlayerStats st = PlayerStats.get(p2);
				        								st.giveMoney(stagetoby.getBank(), p2);
				        								for(int i = 0; i < st.idStage.size(); i++)
				        								{
				        									if(st.idStage.get(i) == stagetoby.getId())
				        									{
				        										st.idStage.remove(i);
				        									}
				        								}
				        								M(p2, C.EVENT_BY_YOUR_STAGE, name, stagetoby.getIdStage()+"");
				        							}
				        							
				        							else
				        							{
				        								bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+stagetoby.getBank()+" WHERE pseudo='"+stagetoby.getOwner()+"'");
				        							}
			        							}
			        							stagetoby.PushAlerte("neo.alerte.by", new String[] { EnumChatFormatting.DARK_PURPLE+""+name, EnumChatFormatting.DARK_PURPLE+""+stagetoby.getIdStage() });
			        							stat.QuestEvent("event_by_stage_"+stagetoby.getIdStage(), player, 1);
			        							stagetoby.setOwner(name);
			        							stagetoby.setBouclierString(stage.getStringBouclier());
			        							stagetoby.giveBouclierDay(1);
			        							stagetoby.setBank(0);
			        							stagetoby.setAlly(stage.getAlly());
			        							stagetoby.setEnnemy(stage.getEnnemy());
			        							stagetoby.SetIsUse(true);
			        							stagetoby.setLvl(stage.getLvl());
			        							stagetoby.setArraySubOwner(stage.getSubOwner());
			        							stagetoby.setPermAll(stage.getPermAll());
			        							stagetoby.setPermMember(stage.getPermMember());
			        							stagetoby.setPermAllie(stage.getPermAllie());
			        							stat.idStage.add(stagetoby.getId());
			        							ArrayList<String> sub = stage.getSubOwner();
			        							if(!sub.isEmpty())
			        							{
			        								EntityPlayer p3;
			        								for(String e:sub)
			        								{
			        									p3 = RegisterStage.getPlayer(e);
			        									if(p3 != null)
			        									{
			        										PlayerStats.get(p3).idStage.add(stagetoby.getId());
			        										M(p3, C.EVENT_PLAYER_BY_STAGE);
			        									}
			        								}
			        							}
			        							M(player, C.ALLOW_BY_STAGE);
			        							M(player, C.ALLOW_CONGRATULATION);
			        						}else {
			        							M(player,C.CONFIRM_BY);	
			        							M(player,C.CONFIRM_BY_2, stagetoby.getBank()+"", stagetoby.getIdStage()+"");	
			        						}
			        					}
			        					else
			        					{
			        						M(player,C.DENY_NO_MONEY_ALSO, (stagetoby.getBank()-stat.Money)+"");	
			        					}
			        				}
			        				else
			        				{
			        					M(player,"neo.DENY_BOUCLIER");	
			        				}
		        			/*	}
		        				else
		        				{
		        					M(player,C.DENY_ALSO_ILE);	
		        				} */
		        			}
		        			else
		        			{
		        				M(player,C.DENY_NO_GOOD);	
		        			}
	        			
	        			}
	        			else
	        			{
	        				M(player,"neo.DENY_ERROR_BY");	
	        			}
	        	}else
	        	{
	        		 M(player,C.DENY_NOT_ADMIN);	
	        	}

	        }/*else if(params[1].equals("pay") || params[1].equals("addbank"))
	        {
	        	if(!stat.idStage.isEmpty())
	        	{
	        		HashMap<String, Integer> m = new HashMap<String, Integer>();
	        		
	        		m.put("argent", stat.Money);
	        		for(int id:stat.idStage)
	        		{
	        			Stage s = RegisterStage.getStageWithId(id);
	        			m.put("("+s.getId()+") Stage "+s.getIdStage()+": "+s.getBank()+"$", id);
	        		}
	        		
	        		nw.sendTo(new NetWorkClient(new ClientStageCmd(m, 1)),(EntityPlayerMP) player);
		        	
	        	}
	        	else
	        	{
	        		 M(player,C.DENY_NO_ILE);		
	        	}
	
	        			
	        			/*int i = Integer.parseInt(params[3]);
	        			if(i < 4 & i >= 0)
	        			{
	        				
			        		if(stat.idStage[i] != -1) 
			        		{
			        			Stage stage = RegisterStage.getStageWithId(stat.idStage[i]);
				        		try {
				        			i = Integer.parseInt(params[2]);
				        			if(stat.AsMoney(i))
				        			{
				        				stage.addtoBank(i);
				        				 M(player,"neo.ALLOW_ADD_TO_BANK",i+"",stage.getIdStage()+"");
				        				 stat.removeMoney(i, player);
				        			}
				        			else
				        			{
				        				 M(player,C.DENY_NO_MONEY);	
				        			}
		
				        		}
					            catch (NumberFormatException ignore)
					            {
					            	 M(player,C.DENY_NOMBRE_NOT_VALID);	
					            }
			        		}else
				        	{
				        		M(player,getCommandUsage(sender));
				        	}
	        			}
	        			else
	        			{
	        				 M(player,C.DENY_NOMBRE_NOT_VALID);	
	        			} 
		        	
				
	        }else if(params[1].equals("take") || params[1].equals("takebank"))
	        {
	        	if (params.length == 4)
	        	{
	        		try {
	        			/*	int i = Integer.parseInt(params[3]);
	        			if(i < 4 & i >= 0)
	        			{
	        				
			        		if(stat.idStage[i] != -1) 
			        		{
			        			Stage stage = RegisterStage.getStageWithId(stat.idStage[i]);
				        		try {
				        			i = Integer.parseInt(params[2]);
				        			if(i<stage.getBank())
				        			{
				        				stage.removetoBank(i);
				        				 M(player,"neo.ALLOW_REMOVE_TO_BANK",i+"",stage.getIdStage()+"");
				        				 stat.giveMoneyOutQuest(i, player);
				        			}
				        			else
				        			{
				        				 M(player,C.DENY_NO_MONEY);	
				        			}
		
				        		}
					            catch (NumberFormatException ignore)
					            {
					            	 M(player,C.DENY_NOMBRE_NOT_VALID);	
					            }
			        		}else
				        	{
				        		M(player,getCommandUsage(sender));
				        	}
	        			}
	        			else
	        			{
	        				 M(player,C.DENY_NOMBRE_NOT_VALID);	
	        			} 
		        	}catch (NumberFormatException ignore)
			        {
			            M(player,C.DENY_NOMBRE_NOT_VALID);	
			        }
					
					
	        	}else if (params.length == 3)
	        	{
	        		if(!stat.idStage.isEmpty()) 
	        		{
	        			Stage stage = RegisterStage.getFirstStagePlayer(stat);
		        		try {
		        			int i = Integer.parseInt(params[2]);
		        			if(i<stage.getBank())
		        			{
		        				stage.removetoBank(i);
		        				 M(player,"neo.ALLOW_REMOVE_TO_BANK",i+"",stage.getIdStage()+"");
		        				 stat.giveMoney(i, player);
		        			}
		        			else
		        			{
		        				 M(player,C.DENY_NO_MONEY);	
		        			}

		        		}
			            catch (NumberFormatException ignore)
			            {
			            	 M(player,C.DENY_NOMBRE_NOT_VALID);	
			            }
	        		}else
		        	{
		        		M(player,getCommandUsage(sender));
		        	}

	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }*/else if(params[1].equals("bank") || params[1].equals("money"))
	        {
	        	if(!stat.idStage.isEmpty())
	        	{
	        		if(stat.isInAttackWithMessage(player))
	        		{
		        		HashMap<String, Integer> m = new HashMap<String, Integer>();
		        		
		        		m.put("money", stat.Money);
		        		for(int id:stat.idStage)
		        		{
		        			Stage s = RegisterStage.getStageWithId(id);
		        			m.put("("+s.getId()+") Stage "+s.getIdStage()+": "+s.getBank()+"$", id);
		        		}
		        		
		        		nw.sendTo(new NetWorkClient(new ClientStageCmd(m, 1)),(EntityPlayerMP) player);
	        		}
		        	
	        	}
	        	else
	        	{
	        		 M(player,C.DENY_NO_ILE);		
	        	}
	        }else if(params[1].equals("warps"))
	        {
	        	if(stat.isInAttackWithMessage(player))
	        	{
	        		nw.sendTo(new NetWorkClient(new ClientStageCmd(null, 2)),(EntityPlayerMP) player);
	        	}
	        
	        }else if(params[1].equals("stagetop") ||params[1].equals("top")  )
	        {
		        
	        	int param =1;
        		if(1 < params.length)
        		{
        			try {
        				param = Integer.parseInt(params[2]);
        			} catch(Exception e)
        			{
        				param=1;
        			}
        		}
        		M(player,"neo.stagetop.header");
        		
        		int max = param*10;
        		int base = (param-1)*10;
        	
        		
        		int id = bdd.GetFreeId();
        		ResultSet result = bdd.query("SELECT owner,lvl FROM "+bdd.getStringStage()+" ORDER BY `lvl` DESC", id);
        		if(result != null)
        		{
        			try {
        				int index = 0;
        				int index2 = 1;
        				ArrayList<String> ever = new ArrayList<String>();
        				while(result.next())
        				{
        					if(!ever.contains(result.getString("owner")))
        					{
	        					if(index <= base)
	        					{
	        						
	        					}
	        					else if(index > max)
	        					{
	        						M(player,"neo.stagetop.end", EnumChatFormatting.DARK_PURPLE+""+(param+1)+"");
	        						return;
	        						
	        					} else {
	        						ever.add(result.getString("owner"));
	        						M(player,"neo.stagetop.player", EnumChatFormatting.DARK_PURPLE+""+(base+index2)+"",EnumChatFormatting.DARK_PURPLE+""+result.getString("owner"),EnumChatFormatting.DARK_PURPLE+""+result.getInt("lvl")+"");
	        						index2++;
	        					}
	        					index++;
        					}
        				}
        			} catch(Exception e)
        			{
        				e.printStackTrace();
        			}
        		}
        
	        }
	        else if(params[1].equals("create"))
	        {
	        	if(stat.AdminStage)
	        	{
	        		try
		            {
		              int id = Integer.parseInt(params[3]);
		              int idstage = Integer.parseInt(params[2]);
		              int PosX = (int) player.posX;
		              int PosY = (int) player.posY;
		              int PosZ = (int) player.posZ;
		              int x = player.chunkCoordX;
		              int z = player.chunkCoordZ;
		              int worldid = player.dimension;
		              if(!bdd.Exist("SELECT * FROM "+bdd.getStringChunk()+" WHERE PosX="+x+" AND PosZ="+z+" AND IdWorld="+worldid))
		              {
			              bdd.execute("INSERT INTO "+bdd.getStringChunk()+"(`PosX`, `PosZ`, `IdStage`, `IdWorld`) VALUES ('"+x+"','"+z+"','"+id+"', '"+player.worldObj.provider.dimensionId+"')");
			              bdd.execute("INSERT INTO "+bdd.getStringStage()+"(`id2`, `WorldId`, `StageId`, `XPos`, `ZPos`, `PermAll`, `PermMember`, `isUse`,`x`,`y`,`z`, `hasHome`,`name`, `bank`) VALUES ('"+id+"','"+worldid+"','"+idstage+"','"+x+"','"+z+"','"+RegisterStage.getPermForStage(idstage)+"','"+RegisterStage.getPermForStageMember(idstage)+"','0','"+PosX+"','"+PosY+"','"+PosZ+"','1', 'Ile-"+id+"', '"+RegisterStage.getBankForStage(idstage)+"')");
			               M(player,"new stage create width id:" +  id);
		              }
		              else
		              {
		            	   M(player,"ce chunk est déjà utilisé");	
		              }
		            }
		            catch (NumberFormatException ignore)
		            {
		            	 M(player,"format non valide ...");	
		            }
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("createlog"))
	        {
	        	if(stat.AdminStage)
	        	{
	        		try
		            {
		              int idstage = Integer.parseInt(params[2]);
		              int id = Integer.parseInt(params[3]);
		              int PosX = (int) player.posX;
		              int PosY = (int) player.posY;
		              int PosZ = (int) player.posZ;
		              int x = player.chunkCoordX;
		              int z = player.chunkCoordZ;
		              int worldid = player.dimension;
		              
		            	  if(!this.Stage.contains(id+"$INSERT INTO "+bdd.getStringChunk()+"(`PosX`, `PosZ`, `IdStage`, `IdWorld`) VALUES ('"+x+"','"+z+"','%id%', '%world%')$"))
			              {
			            	  this.Stage.add(id+"$INSERT INTO "+bdd.getStringChunk()+"(`PosX`, `PosZ`, `IdStage`, `IdWorld`) VALUES ('"+x+"','"+z+"','%id%', '%world%')$");
			            	  this.Stage.add(id+"$INSERT INTO "+bdd.getStringStage()+"(`id2`, `WorldId`, `StageId`, `XPos`, `ZPos`, `PermAll`, `PermMember`, `isUse`,`x`,`y`,`z`, `hasHome`,`name`, `bank`) VALUES ('%id%','%world%','"+idstage+"','"+x+"','"+z+"','"+RegisterStage.getPermForStage(idstage)+"','"+RegisterStage.getPermForStageMember(idstage)+"','0','"+PosX+"','"+PosY+"','"+PosZ+"','1', 'Ile-%id%', '"+RegisterStage.getBankForStage(idstage)+"')$");
				              M(player,"new stage log create");
			              }
			              else
			              {
			            	   M(player,"ce chunk est déjà utilisé");	
			              }
		             
		            }
		            catch (NumberFormatException ignore)
		            {
		            	 M(player,"format non valide ...");	
		            }
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("savelog"))
	        {
	        	if(stat.AdminStage)
	        	{
	        		try
		            {
	        			PrintWriter writer = null;
	        			try {
	        				writer = new PrintWriter(new File("assets/dataMapStage.nc"), "UTF-8");
	        			} catch (FileNotFoundException e) {
	        				e.printStackTrace();
	        			
	        			} catch (UnsupportedEncodingException e) {
	        				e.printStackTrace();
	        			
	        			}
	        			for (int i = 0; i < this.Stage.size(); i++) {
	        	           writer.println(this.Stage.get(i));
	        	        }
	        			writer.close();
		             
		            }
		            catch (NumberFormatException ignore)
		            {
		            	 M(player,"format non valide ...");	
		            }
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("createworldlog"))
	        {
	        	if(stat.AdminStage)
	        	{
	        		try
		            {
	        			 BufferedReader br;
							try {
								br = new BufferedReader(new FileReader("assets/dataMapStage.nc"));
								String line = "";
								String[] d;
								int id23 = bdd.getMaxOf("id2", bdd.getStringStage()) + 1;
								while((line = br.readLine()) != null)
								{
									d = GetArrayString(line);
									int pos = Integer.parseInt(d[0]);
									int id = id23 + pos;
									bdd.execute(d[1].replaceAll("%id%", id+"").replaceAll("%world%", ""+player.worldObj.provider.dimensionId));
								}
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
		
							}	
		             
		            }
		            catch (NumberFormatException ignore)
		            {
		            	 M(player,"format non valide ...");	
		            }
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("getnextid"))
	        {
	        	if(stat.AdminStage)
	        	{
	        		int m = bdd.getMaxOf("id2", bdd.getStringStage()) + 1;
	        		 M(player,"next free id: " + m);	
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("reload"))
	        {
	        	if(stat.AdminStage)
	        	{
	        	
			        		RegisterStage.Register();
			        		RegisterStage.RegisterChunk();
			        		RegisterStage.RegisterItemDeny();
			        		 M(player,"reload");	
			    	
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("save"))
	        {
	        	if(stat.AdminStage)
	        	{
	        	main.getTimerManager().saver.SaveStage(true);
	        		 M(player,"save");	
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("adminsethome"))
	        {
	        	if(stat.AdminStage)
	        	{
			        		try
				            {
				              int id = Integer.parseInt(params[2]);
				              int PosX = (int) player.posX;
				              int PosY = (int) player.posY;
				              int PosZ = (int) player.posZ;
				              bdd.update("UPDATE `Neo-Stage` SET `x`='"+PosX+"',`y`='"+PosY+"',`z`='"+PosZ+"',`hasHome`=1 WHERE id2="+id);
				              	 M(player,"new home for id:" +  id);
				            }
				            catch (NumberFormatException ignore)
				            {
				            	 M(player,"format non valide ...");	
				            }
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("perm") || params[1].equals("permission"))
	        {
	        	Stage stage = RegisterStage.getFirstStagePlayer(stat);
	        	if(stage != null)
	        	{
		        	if(stage.isOwner(name))
		            {
		        	//	player.addChatMessage(new ChatComponentText("neoexe:OpenGui*2*"));
		        		player.openGui(main.NeogetInstance(), 2, player.worldObj,(int) player.posX,(int) player.posY, (int)player.posZ);
		        		
		            }
		            else
		            {
		            	 M(player,C.DENY_NOT_ADMIN);
		            }
	        	}
	        	else
	        	{
	        		 M(player,"neo.DENY_NOT_IN_ILE");	
	        	}
	        	
	        }else if(params[1].equals("dragon"))
	        {
	        	player.openGui(main.NeogetInstance(), 1, player.worldObj,(int) player.posX,(int) player.posY, (int)player.posZ);
	        	
	        }else if(params[1].equals("reloadplayer"))
	        {
	        	if(stat.AdminStage)
	        	{
	        		 ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();

	        		    while (li.hasNext()){

	        		        EntityPlayer p = (EntityPlayer) li.next();
	        		        StageEventFML.EntityJoinLoadStat(p, p.worldObj, true);
	        		        M(player,p.getCommandSenderName());
	        		    }
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("reloadplayerquest"))
	        {
	        	if(stat.AdminStage)
	        	{
	        	
	        		 ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();

	        		    while (li.hasNext()){

	        		        EntityPlayer p = (EntityPlayer) li.next();
	        		        QuestManager.sendToClienQuest(p,main.getHourlyQuest());
	        		        M(player,p.getCommandSenderName());
	        		    }
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("pet"))
	        {
	        	if(isOp(player))
	        	{
	        	
	        		if(stat.MyEntityPet != null)
	        		{
	        			stat.MyEntityPet.setDead();
	        		}
	        		int id = Integer.parseInt(params[2]);
		        	EntityPet p = new EntityPet(player.worldObj);
		        	p.setPet(player, stat.PetId, stat.PetLvl, stat.PetExp);
		        	p.setPosition(player.posX,player.posY, player.posZ);
		        	player.worldObj.spawnEntityInWorld(p);
	        		stat.MyEntityPet = p;
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("adminsetowner"))
	        {
	        	if(stat.AdminStage)
	        	{
			        		try
				            {
				        		String p;
				        		if (params.length < 2) {
				        			 p = params[3];
				        		}
				        		else
				        		{
				        			p = name;
				        		}
					              int id = Integer.parseInt(params[2]);
					              bdd.update("UPDATE `Neo-Stage` SET `owner`='"+p+"' WHERE id2="+id);
				              	 M(player,"new owner for id:" +  id);
				            }
				            catch (NumberFormatException ignore)
				            {
				            	 M(player,"format non valide ...");	
				            }

	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        } else if(params[1].equals("quest"))
	        {
	        	QuestManager.sendToClienQuest(player,main.getHourlyQuest());
	        }else if(params[1].equals("quesaco"))
	        {
	        	int dd = 0;
	        	for(Object e:player.worldObj.loadedEntityList)
	        	{
	        		Entity en = (Entity)e;
	        		if(!(en instanceof EntityPlayer))
	        		{

	        			player.worldObj.removeEntity(en);
	        			dd++;
	        		}
	        	}
	        
	        	M(player,"total: "+dd);	
	        }
	        else if(params[1].equals("admin"))
	        {
	        	if(isOp(player))
	        	{
	        		if(stat.AdminStage)
	        		{
	        			stat.AdminStage = false;
	        			 M(player,"mode admin: false");	
	        		}
	        		else
	        		{
	        			stat.AdminStage = true;
	        			 M(player,"mode admin: true");	
	        		}
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else if(params[1].equals("remstage"))
	        {
	        	if(isOp(player))
	        	{
	        		int i = -1;
	        		if(params.length > 1)
	        		{
	        			i = Integer.parseInt(params[2]);
	        		}
	        		else
	        		{
	        			Stage s = RegisterStage.getStageAtXY(player.chunkCoordX, player.chunkCoordZ, player.worldObj);
	        			if(s != null)
	        			{
	        				i = s.getId();
	        			}
	        		}
	        		if(i != -1)
	        		{
	        			bdd.execute("DELETE FROM "+bdd.getStringStage()+" WHERE `id2`="+i);
	        			bdd.execute("DELETE FROM "+bdd.getStringChunk()+" WHERE `IdStage`="+i);
	        			M(player, "success");
	        			
	        		}else {
	        			M(player, "nope");
	        		}
	        	}
	        	else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        } else  if(params[1].equals("runHorraire"))
	        {
	        	if(isOp(player))
	        	{
	        		main.runHorraire();
	        		M(player,"run");
	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        } else  if(params[1].equals("ReloadBdd"))
	        {
	        	if(isOp(player))
	        	{
	        		main.reloadBdd();
	        		M(player,"reload");
	        		
	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        } else  if(params[1].equals("SetContainer"))	
	        {
	        	if(isOp(player))
	        	{
	        		int type = Integer.parseInt(params[2]);
	        		int x =(int) player.posX, y = (int)player.posY - 1, z = (int)player.posZ;
	        		bdd.execute("INSERT INTO "+bdd.getStringContainerRandom()+"(`world`, `type`, `x`, `y`, `z`) VALUES ("+player.worldObj.provider.dimensionId+","+type+","+x+","+y+","+z+")");
	        		M(player,"new container type "+type+" x:"+x+" y:"+y+" z:"+z);
	      
	        		
	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else  if(params[1].equals("chunk"))	
	        {
	        	
	        	if(isOp(player))
	        	{
	        		int type = Integer.parseInt(params[2]);
	        		Block b = null;
	        		switch(type)
	        		{
	        			case -1:
	        				b = Blocks.obsidian;
	        				break;
	        			case 0:
	        				b = Blocks.coal_block;
	        				break;
	        			case 1:
	        				b = Blocks.iron_block;
	        				break;
	        			case 2:
	        				b = Blocks.gold_block;
	        				break;
	        			case 3:
	        				b = Blocks.emerald_block;
	        				break;
	        			case 4:
	        				b = Blocks.diamond_block;
	        				break;
	        			case 5:
	        				b = Blocks.glowstone;
	        				break;
	        		}
	        		
	        		int x2 = player.chunkCoordX * 16;
	        		int z2 = player.chunkCoordZ * 16;
	        		for(int x = 0; x < 16; x++)
					{
						for(int z = 0; z < 16; z++)
						{
							player.worldObj.setBlock(x2+x, 100, z2+z, b);

						}
					}
	        		
	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else  if(params[1].equals("StartContainer"))
	        {
	        	if(isOp(player))
	        	{
	        		main.startContainer();
	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else  if(params[1].equals("hdv"))
	        {
	        	
	        	main.getNetWorkClient().sendTo(new NetWorkClient(new ClientOpenGui(-2)), (EntityPlayerMP) player);
	        }else  if(params[1].equals("getXY"))
	        {
	        	
	        	String pseudo = params[2];
	        	ArrayList<Stage> stage = RegisterStage.getStageWithPlayer(pseudo);
	        	if(stage != null)
	        	{
	        		for(Stage s:stage)
	        		{
	        			M(player,"id: "+s.getId()+", idStage: "+s.getIdStage()+", worldId:"+s.getIdWorld()+", X:"+(s.getXpos()*16)+", Z:"+(s.getZpos()*16) );
	        		}
	        	}
	        	else {
	        		M(player,"null");
	        	}
	        }else  if(params[1].equals("CheckContainer"))
	        {
	        	if(isOp(player))
	        	{
	        		main.startCheckContainer();
	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else  if(params[1].equals("reloadXp"))
	        {
	        	if(isOp(player))
	        	{
	        		StageXpManager.register();
	        		M(player,"reload Xp");
	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }else  if(params[1].equals("Bouclier"))
	        {
	        	if(isOp(player))
	        	{

	        		try {
	        			int nbh = Integer.parseInt(params[2]);
	        			player.inventory.addItemStackToInventory(new ItemStack(ItemMod.bouclier,64,nbh));
	        		} catch(Exception e)
	        		{
	        			player.inventory.addItemStackToInventory(new ItemStack(ItemMod.bouclier,64,1));
	        		}
	        	}else
	        	{
	        		M(player,getCommandUsage(sender));
	        	}
	        }
	        else if(params[1].equals("help"))
	        {
	        	M(player, "neo.STAGE_CMD_0");
	        	M(player, "neo.STAGE_CMD_1");
	        	M(player, "neo.STAGE_CMD_2");
	        	M(player, "neo.STAGE_CMD_3");
	        	M(player, "neo.STAGE_CMD_4");
	        	M(player, "neo.STAGE_CMD_5");
	        	M(player, "neo.STAGE_CMD_6");
	        	M(player, "neo.STAGE_CMD_7");
	        	M(player, "neo.STAGE_CMD_8");
	        	M(player, "neo.STAGE_CMD_9");
	        	M(player, "neo.STAGE_CMD_10");
	        	M(player, "neo.STAGE_CMD_11");
	        	
	        	M(player, "neo.STAGE_CMD_12");
	        	M(player, "neo.STAGE_CMD_13");
	        	M(player, "neo.STAGE_CMD_14");
	        	M(player, "neo.STAGE_CMD_15");
	        }else
	    	{
	    		M(player,getCommandUsage(sender));
	    	}
        }
    }
    
    private List Stage = new ArrayList<String>();
    
    public boolean isOp(EntityPlayer player)
    {
    	return MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile()) || PlayerStats.get(player).AdminStage;
    }
    public void M(EntityPlayer p, String l)
    {
    	p.addChatMessage(new ChatComponentTranslation(l));
    }
    
    public void M(EntityPlayer p, String l, String l2)
    {
    	p.addChatMessage(new ChatComponentTranslation(l, l2));
    }
    
    public void M(EntityPlayer p, String l, String l2, String l3)
    {
    	p.addChatMessage(new ChatComponentTranslation(l, l2,l3));
    }
    
    public void M(EntityPlayer p, String l, String l2, String l3, String l4)
    {
    	p.addChatMessage(new ChatComponentTranslation(l,l2,l3,l4));
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
			String[] finale = new String[compterOccurrences(txt, '$')];
			String pre = "";
			char cara, cara2;
			int d = 0;
			for(int i = 0; i < t; i++) 
			{
				
				cara = txt.charAt(i);
				if(cara == '$')
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
}

