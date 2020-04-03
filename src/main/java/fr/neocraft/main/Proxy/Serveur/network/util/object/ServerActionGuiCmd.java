package fr.neocraft.main.Proxy.Serveur.network.util.object;


import java.util.HashMap;

import org.bukkit.Bukkit;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.Proxy.Serveur.player.teleport;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class ServerActionGuiCmd extends T{
	private  static  final  long serialVersionUID =  5464867684657468768L;

	private int Action, id, amount;
	private String Warp;
	
	public ServerActionGuiCmd(int idStage, int ac) {
		Action = ac;
		id = idStage;
	}
	
	public ServerActionGuiCmd(int idStage, int ac, int am) {
		Action = ac;
		id = idStage;
		amount = am;
	}
	public ServerActionGuiCmd(String string, int ac) {
		Action = ac;
		Warp = string;
	}



	@SideOnly(Side.SERVER)
	@Override
	public void A(EntityPlayer player) {
		PlayerStats stat = PlayerStats.get(player);
		Stage stage;
		switch(Action)
		{
			case -1:
				System.out.println("ERREUR RRRRRRRRRR");
				for(int d:stat.idStage)
				{
					stage = RegisterStage.getStageWithId(d);
					
					World w = DimensionManager.getWorld(stage.getIdWorld());
					int  y = stage.getY();
					int co = 257 - y;
					for(int i = 0; i < co; i++)
					{
						if(w.getBlock(stage.getX(), y+i,stage.getZ()).getMaterial() == Material.air)
						{
							if(w.getBlock(stage.getX(), y+1+i,stage.getZ()).getMaterial() == Material.air)
							{
								teleport.player(stage.getIdWorld(), player, stage.getX(), y+i, stage.getZ(), 2, false);
								break;
							}
						}
					}
					
				}
				
				break;
			case 0:
				//tp home
				for(int d:stat.idStage)
				{
					if(d == id)
					{
						stage = RegisterStage.getStageWithId(id);
						
						World w = DimensionManager.getWorld(stage.getIdWorld());
						int  y = stage.getY();
						int co = 257 - y;
						for(int i = 0; i < co; i++)
						{
							if(w.getBlock(stage.getX(), y+i,stage.getZ()).getMaterial() == Material.air)
							{
								if(w.getBlock(stage.getX(), y+1+i,stage.getZ()).getMaterial() == Material.air)
								{
									teleport.player(stage.getIdWorld(), player, stage.getX(), y+i, stage.getZ(), 2, false);
									break;
								}
							}
						}
						break;
					
					}
				
					
				}
				break;
			case 1:
				//sethome
				
				stage = RegisterStage.getStageAtXY((int) player.chunkCoordX,(int) player.chunkCoordZ, player.getEntityWorld());
	       		 if(stage != null && stage.getId() == id)
	       		 {
	       			 if(stage.isOwner(player.getCommandSenderName()))
	       			 {
			        		 stage.setHome((int) player.posX, (int) player.posY, (int) player.posZ);;
		        			 stage.SethasHome(true);
		        			 player.addChatMessage(new ChatComponentTranslation("neo.ALLOW_NEW_HOME"));
		 								
	       		 	} else {
	       		 		player.addChatMessage(new ChatComponentTranslation("neo.DENY_NOT_ADMIN"));
	       		 	}
	       			 
	       			 
	       			 
	       		 }
	       		 else
	       		 {
	       			player.addChatMessage(new ChatComponentTranslation("neo.DENY_HOME_NOT_IN_ILE"));
	       		 }
				break;
			case 2:
				for(int d:stat.idStage)
				{
					if(d == id)
					{
					stage = RegisterStage.getStageWithId(id);
	        			if(stat.AsMoney(amount))
	        			{
	        				stage.addtoBank(amount);
	        				player.addChatMessage(new ChatComponentTranslation("neo.ALLOW_ADD_TO_BANK",EnumChatFormatting.DARK_PURPLE+""+amount+"",EnumChatFormatting.DARK_PURPLE+""+stage.getIdStage()+""));
	        				 stat.removeMoney(amount, player);
	        				 
	        				 if(!stat.idStage.isEmpty())
	         	        	{
	         	        		HashMap<String, Integer> m = new HashMap<String, Integer>();
	         	        		
	         	        		m.put("money", stat.Money);
	         	        		for(int id:stat.idStage)
	         	        		{
	         	        			Stage s = RegisterStage.getStageWithId(id);
	         	        			m.put("("+s.getId()+") Stage "+s.getIdStage()+": "+s.getBank()+"$", id);
	         	        		}
	         	        		
	         	        		main.getNetWorkClient().sendTo(new NetWorkClient(new ClientStageCmd(m, 1)),(EntityPlayerMP) player);
	         		        	break;
	         	        	}
	        			}
	        			else
	        			{
	        				player.addChatMessage(new ChatComponentTranslation("neo.DENY_NO_MONEY"));	
	        			}
	        			break;
					}
				}

				break;
			case 3:
				for(int d:stat.idStage)
				{
					if(d == id)
					{
					stage = RegisterStage.getStageWithId(id);
	        			if(stage.AsBank(amount))
	        			{
	        				stage.removetoBank(amount);
	        				player.addChatMessage(new ChatComponentTranslation("neo.REMOVE_ADD_TO_BANK",EnumChatFormatting.DARK_PURPLE+""+amount+"",EnumChatFormatting.DARK_PURPLE+""+stage.getIdStage()+""));
	        				stat.giveMoney(amount, player);
	        				
	        				if(!stat.idStage.isEmpty())
	        	        	{
	        	        		HashMap<String, Integer> m = new HashMap<String, Integer>();
	        	        		
	        	        		m.put("money", stat.Money);
	        	        		for(int id:stat.idStage)
	        	        		{
	        	        			Stage s = RegisterStage.getStageWithId(id);
	        	        			m.put("("+s.getId()+") Stage "+s.getIdStage()+": "+s.getBank()+"$", id);
	        	        		}
	        	        		
	        	        		main.getNetWorkClient().sendTo(new NetWorkClient(new ClientStageCmd(m, 1)),(EntityPlayerMP) player);
	        		        	
	        	        	}
	        				
	        			}
	        			else
	        			{
	        				player.addChatMessage(new ChatComponentTranslation("neo.DENY_NO_MONEY"));	
	        			}
	        			break;
					}
				}

				break;
			case 4:
				for(int i = 0; i <stat.idStage.size(); i++)
				{
					if(stat.idStage.get(i) == id)
					{
						stat.idStage.remove(i);
						stage = RegisterStage.getStageWithId(id);
					if(stage.getIdStage() == 0)
					{
						return;
					}
			             if(!stage.getSubOwner().isEmpty())
			             {
			            	 for(String e:stage.getSubOwner())
			            	 {
			            		 EntityPlayer tempPlayer = RegisterStage.getPlayer(e);
			            		 if(tempPlayer != null)
			            		 {
			            			 PlayerStats s = PlayerStats.get(tempPlayer);
			            			 for(int o = 0; o <s.idStage.size(); o++)
			         				{
			            				 if(s.idStage.get(o) == id)
			         					{
			            					 s.idStage.remove(o);
			         					}
			         				}
			            			
			            		 }
			            	 }
			             }
			             stat.giveMoney((RegisterStage.getBankForStage(stage.getIdStage()) / 3)+ stage.getBank(), player);
			         	stage.setArraySubOwner(null);
    					stage.setOwner(null);
    					stage.SetIsUse(false);
    					stage.setAlly(null);
    					stage.setLvl(0);
    					stage.setEnnemy(null);
    					stage.setName("Ile-"+stage.getId());
    					stage.setBouclierNull();
    					stage.setBank(RegisterStage.getBankForStage(stage.getIdStage()));
    					stage.setPermAll(main.bdd.GetArrayListString(RegisterStage.getPermForStage(stage.getIdStage())));
    					stage.setPermMember(main.bdd.GetArrayListString(RegisterStage.getPermForStageMember(stage.getIdStage())));
    					stage.setPermAllie(null);
	        			player.addChatMessage(new ChatComponentTranslation("neo.sell.yes"));	
	        				break;
					}
				}
				break;
			case 5:
	
					fr.neocraft.main.Proxy.Serveur.Handler.NeoBukkit.executeCmd("warp "+Warp+" "+player.getCommandSenderName());

		
				break;
				
				
		}
	}
}
