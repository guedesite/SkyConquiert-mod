package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class CommandStageAfk extends CommandBase {

	public static final fr.neocraft.main.bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "stageafk";
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
        return 4;
    }

   
    @Override
    public void processCommand(ICommandSender sender,String[] param) {

    		int id2 = bdd.GetFreeId();
    			ResultSet result = bdd.query("SELECT pseudo, LastLogin FROM "+bdd.getStringPlayer()+" WHERE LastLogin = 0", id2);
    			try {
    				while(result.next())
    				{
    					String[] params = new String[] { result.getString("pseudo")};
    					if(RegisterStage.getPlayer(params[0]) == null)
    			    	{
    			    		ArrayList<Stage> stage = RegisterStage.getStageWithPlayer(params[0]);
    			    		if(!stage.isEmpty() && stage.get(0) != null)
    			    		{
    			    			stage.get(0).PushAlerte("neo.error.alerte.reset", new String[0]);
    			    		}
    			    		for(Stage e:stage)
    			    		{
    			    			
    			    				if(e.isOwner(params[0]))
    			    				{
    			    					
    			    					
    			    					
    			    					ArrayList<String> sub = e.getSubOwner();
    			    					
    			    						for(String o:sub)
    				    					{
    				    						EntityPlayer p = RegisterStage.getPlayer(o);
    				    						if(p != null)
    				    						{
    				    							PlayerStats.get(p).idStage = new ArrayList<Integer>();
    				    							p.addChatMessage(new ChatComponentTranslation("neo.EVENT_PLAYER_BE_KICKALL"));
    				    						} else {
    				    							
    				    						}
    				    					}
    			    					
    			    					e.setArraySubOwner(null);
    			    					e.setOwner(null);
    			    					e.SetIsUse(false);
    			    					e.setLvl(0);
    			    					e.setAlly(null);
    			    					e.setEnnemy(null);
    			    					e.setName("Ile-"+e.getId());
    			    					e.setBouclierNull();
    			    					bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+e.getBank()+", `LastLogin`=-1 WHERE pseudo='"+params[0]+"'");
    			    					e.setBank(RegisterStage.getBankForStage(e.getIdStage()));
    			    					e.setPermAll(bdd.GetArrayListString(RegisterStage.getPermForStage(e.getIdStage())));
    			    					e.setPermMember(bdd.GetArrayListString(RegisterStage.getPermForStageMember(e.getIdStage())));
    			    					e.setPermAllie(null);
    			    					if(e.getIdStage() == 0)
    			    					{
    			    						try {
    			    							int idbdd2 = bdd.GetFreeId();
    			    							ResultSet result2 = bdd.query("SELECT * FROM "+bdd.getStringChunk()+" WHERE IdStage="+e.getId(), idbdd2);
    			    							if(result2 != null)
    			    							{
    			    								try {
    			    					
    			    								 	while(result2.next())
    			    								 	{
    			    								 		if(e.getXpos() != result2.getInt("PosX") || e.getZpos() != result2.getInt("PosZ"))
    			    								 		{
    			    								 			bdd.execute("DELETE FROM "+bdd.getStringChunk()+" WHERE id="+result2.getInt("id"));
    			    								 		}
    			    								 	}
    			    								} catch(Exception ex)
    			    								{
    			    									ex.printStackTrace();
    			    								}
    			    							}
    			    							bdd.CloseFreeId(idbdd2);
    			    							
    			    							idbdd2 = bdd.GetFreeId();
    			    							result2 = bdd.query("SELECT * FROM "+bdd.getStringStage()+" WHERE id2="+e.getId(), idbdd2);
    			    							if(result2 != null)
    			    							{
    			    								try {
    			    									int o = 0;
    			    								 	while(result2.next())
    			    								 	{
    			    								 		o++;
    			    								 		if(o != 1)
    			    								 		{
    			    								 			bdd.execute("DELETE FROM "+bdd.getStringStage()+" WHERE id="+result2.getInt("id"));
    			    								 		}
    			    								 	}
    			    								} catch(Exception ex)
    			    								{
    			    									ex.printStackTrace();
    			    								}
    			    							}
    			    							bdd.CloseFreeId(idbdd2);
    			    							
    			    							
    			    				    		int minx = e.getXpos() * 16;
    			    				    		int minz = e.getZpos()*16;
    			    				    		int miny = 0;
    			    							 BufferedReader br = new BufferedReader(new FileReader("assets/Stage0.data"));
    			    							  String line = "";
    			    							  int id,x,y,z,meta;
    			    							  World w = DimensionManager.getWorld(e.getIdWorld());
    			    					
    			    							  while ((line =br.readLine()) != null) 
    			    							  {
    			    								  try {
    			    									  id = Integer.parseInt(line);
    			    									  x = Integer.parseInt(br.readLine());
    			    									  y = Integer.parseInt(br.readLine());
    			    									  z = Integer.parseInt(br.readLine());
    			    									  meta = Integer.parseInt(br.readLine());
    			    									 w.setBlock(minx+x, miny+y, minz+z, Block.getBlockById(id), meta, 2);
    			    								  } catch(NullPointerException o) {
    			    									  o.printStackTrace();
    			    							    	}
    			    							  } 
    			    				    	
    			    							  br.close();
    			    				    	} catch(Exception o2) {
    			    				    		o2.printStackTrace();
    			    				    	}
    			    					}
    			
    			    					
    			    				} else if(e.isSubOwner(params[0]))
    			    				{
    			    					e.removeSubOwner(params[0]);
    			    					ArrayList<String> sub = e.getSubOwner();
    			    					for(String o:sub)
    			    					{
    			    						EntityPlayer p = RegisterStage.getPlayer(o);
    			    						if(p != null)
    			    						{
    			    							p.addChatMessage(new ChatComponentTranslation("neo.EVENT_PLAYER_BE_KICK_AFK",params[0]));
    			    						}
    			    					}
    			    					EntityPlayer p = RegisterStage.getPlayer(e.getOwner());
    			    					if(p != null)
    									{
    										p.addChatMessage(new ChatComponentTranslation("neo.EVENT_PLAYER_BE_KICK_AFK",params[0]));
    									}
    			    				}
    			    			
    			    		}
    			    	}else {
    			    		System.out.println("player online");
    			    	}
    				}
    			}
    			catch(SQLException e)
    			{
    				e.printStackTrace();
    			}
    			bdd.CloseFreeId(id2);
    			bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `LastLogin`=LastLogin-1 WHERE LastLogin > 0");
    			bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `LasteDisband`=LasteDisband-1 WHERE LasteDisband > 0");
    			Iterator it = RegisterStage.Stage.values().iterator();
    			Stage e;
    		    while (it.hasNext()) {
    		        e = ((Stage)it.next());
    				if(e != null)
    				{
    					if(e.getIdStage() == 3 && e.IsUse())
    					{
    						int nb = 0;
    						if(!bdd.Exist("SELECT * FROM "+bdd.getStringLoyer3()+" WHERE player='"+e.getOwner()+"'"))
    						{
    							bdd.execute("INSERT INTO "+bdd.getStringLoyer3()+"(`player`, `amount`) VALUES ('"+e.getOwner()+"','1')");
    						}
    						else
    						{
    							id2 = bdd.GetFreeId();
    							result = bdd.query("SELECT * FROM "+bdd.getStringLoyer3()+"WHERE player='"+e.getOwner()+"'", id2);
    							try {
    								if(result != null)
    								{
    									while ( result.next() ) 
    									{
    										nb = result.getInt("amount");
    										
    									}
    								}
    								bdd.update("UPDATE "+bdd.getStringLoyer3()+" SET `amount`=amount+1 WHERE player='"+e.getOwner()+"'");
    							}catch(Exception e1)
    							{
    								e1.printStackTrace();
    							}
    							bdd.CloseFreeId(id2);
    						}
    					
    						int sub = 0;
    						if(e.getSubOwner() != null)
    						{
    							sub = e.getSubOwner().size();
    						}
    						int all = main.LoyerStage3 + (sub*main.FacteurLoyerStage3) + nb * main.FacteurLoyerStage3;
    						
    						if(e.AsBank(all))
    						{
    							e.removetoBank(all);
    							if(nb == 7)
    							{
    								EntityPlayer player = RegisterStage.getPlayer(e.getOwner());
    								
    								if(player != null)
    								{
    									PlayerStats.get(player).QuestEvent("event_keep_stage_3_7", player, 1);
    								}
    							}
    						}
    						else
    						{
    							
        			    			e.PushAlerte("neo.error.alerte.stage3", new String[0]);
        			    		
    							bdd.execute("DELETE FROM "+bdd.getStringLoyer3()+" WHERE player='"+e.getOwner()+"'");
    							e.SetIsUse(false);
    							e.setAlly(null);
    							e.setOwner(null);
    							e.setLvl(0);
    							e.setArraySubOwner(null);
    							e.setBank(RegisterStage.getBankForStage(3));
    							e.setBouclierNull();
    							e.setPermAll(bdd.GetArrayListString(RegisterStage.getPermForStage(e.getIdStage())));
		    					e.setPermMember(bdd.GetArrayListString(RegisterStage.getPermForStageMember(e.getIdStage())));
		    					e.setPermAllie(null);
    						}
    					}
    				}
    			}
    	
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

