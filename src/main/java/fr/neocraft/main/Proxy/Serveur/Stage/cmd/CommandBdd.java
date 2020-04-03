package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.ListIterator;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.event.StageEventFML;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;


public class CommandBdd extends CommandBase{

	public static fr.neocraft.main.bdd bdd = main.getbdd();
	
	@Override
    public String getCommandName() {
        return "bdd";
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }
    
    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }
    /**
     * Return the required permission level for this command.
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
    	return true;
    }

    private Exception d;
  
	@Override
	public void processCommand(ICommandSender ic, String[] arg) {
		if(arg.length > 0)
		{
			if(arg[0].equals("help"))
			{
				log(ic, "/open");
				log(ic, "/close");
				log(ic, "/stop");
				log(ic, "/start");
				log(ic, "/restart");
				log(ic, "/debug");
				log(ic, "/requete");
				log(ic, "/closerequete");
				log(ic, "/reloadstage");
				log(ic, "/reloadplayer");
				log(ic, "/save");
				log(ic, "/reload");
				log(ic, "/get (requete sql ex: id2 Neo-Stage owner='guedesite')");
				log(ic, "/update (requete sql ex: idQuest Neo-Stage-Player 1& pseudo='guedesite')");
				log(ic, "/error");
				log(ic, "/colum (from sql table ex: Neo-Stage)");
				log(ic, "/table");
			}else if(arg[0].equals("get")) {
				if(arg.length == 4)
				{
					String query = "SELECT "+arg[1]+" FROM `"+arg[2]+"` WHERE "+arg[3];
					int id = bdd.GetFreeId();
					log(ic, "TRY: "+query);
					log(ic, "QUERY ID: "+id);
					ResultSet r = bdd.query(query, id);
					if(r != null)
					{
						try {
							log(ic, "START LOOP");
							while(r.next())
							{
								log(ic, ""+r.getObject(arg[1]));
							}
							log(ic, "STOP LOOP");
						} catch(Exception e)
						{
							this.d=e;
							log(ic, "EXCEPTION");
							log(ic, "/bdd error to see error message");
						}
					} else {
						log(ic, "RESULTSET NULL");
					}
					bdd.CloseFreeId(id);
					log(ic, "END");

				}
				else
				{
					log(ic, "NOPE ARG");
				}
				
			}else if(arg[0].equals("update")) {
				if(arg.length == 5)
				{
					String query = "UPDATE `"+arg[2]+"` SET `"+arg[1]+"`='"+arg[3]+"' WHERE "+arg[4];
				
					log(ic, "TRY: "+query);
					if(bdd.update(query))
					{
						log(ic, "SUCCESS ");
					}
					else
					{
						log(ic, "ERROR ");
					}
				}
				
				else
				{
					log(ic, "NOPE ARG");
				}
			}else if(arg[0].equals("colum")) {
				if(arg.length == 2)
				{
					String query = "SELECT * FROM `"+arg[1]+"`";
				
					int id = bdd.GetFreeId();
					log(ic, "TRY: "+query);
					log(ic, "QUERY ID: "+id);
					ResultSet r = bdd.query(query, id);
					if(r != null)
					{
						 ResultSetMetaData rsMetaData;
						try {
							rsMetaData = r.getMetaData();
							 int numberOfColumns = rsMetaData.getColumnCount();
							 log(ic, "START LOOP");
							    // get the column names; column indexes start from 1
							    for (int i = 1; i < numberOfColumns + 1; i++) {
							      String columnName = rsMetaData.getColumnName(i);
							      String tableName = rsMetaData.getTableName(i);
							      log(ic, columnName+"::"+tableName);
							    }
							    log(ic, "STOP LOOP");
						} catch (SQLException e) {
							this.d=e;
							log(ic, "EXCEPTION");
							log(ic, "/bdd error to see error message");
						}

					} else {
						log(ic, "RESULTSET NULL");
					}
					bdd.CloseFreeId(id);
					log(ic, "END");
				}
				
				else
				{
					log(ic, "NOPE ARG");
				}
			}
			else if(arg[0].equals("error")) {
				if(this.d != null)
				{
					log(ic, "error:");
					log(ic,this.d.getMessage());
					log(ic, "END ERROR");
				}
				else
				{
					log(ic, "NO ERROR");
				}
			}else if(arg[0].equals("table")) {
				try {
					DatabaseMetaData md = bdd.connexion.getMetaData();
					ResultSet rs = md.getTables(null, null, "%", null);
					 log(ic, "START LOOP");
					while (rs.next()) {
					  log(ic,rs.getString(3));
					}
					log(ic, "STOP LOOP");
				}catch (SQLException e) {
					this.d=e;
					log(ic, "EXCEPTION");
					log(ic, "/bdd error to see error message");
				}
				log(ic, "END");
			}
			else if(arg[0].equals("open")) {
				if(!bdd.IsOpen)
				{
					bdd.Openbdd();
					log(ic, "open");
				}
				else
				{
					log(ic, "NOPE");
				}
			}else if(arg[0].equals("close")) {
				if(bdd.IsOpen)
				{
					bdd.Closebdd();
					log(ic, "close");
				}
				else
				{
					log(ic, "NOPE");
				}
			}else if(arg[0].equals("start")) {
				if(!bdd.IsClass)
				{
					main.bdd = new fr.neocraft.main.bdd();
				
		    		log(ic, "start");
				}else
				{
					log(ic, "NOPE");
				}
			}else if(arg[0].equals("stop")) {
				if(bdd.IsClass)
				{
					bdd.Closebdd();
					bdd.CloseClass();
					
					log(ic, "stop");
				}else
				{
					log(ic, "NOPE");
				}
			}else if(arg[0].equals("restart")) {
				bdd.Closebdd();
				bdd.CloseClass();
				
				main.bdd = new fr.neocraft.main.bdd();
				main.bdd.Openbdd();
	    		log(ic, "restart");
			}else if(arg[0].equals("debug")) {
				if(bdd.IsDebug)
				{
					bdd.IsDebug = false;
				}
				else
				{
					bdd.IsDebug = true;
				}
				log(ic, Boolean.toString(bdd.IsDebug));
			}else if(arg[0].equals("requete")) {
				String t = "";
				Iterator it = bdd.getStatement().keySet().iterator();
				int i = 0;
				while(it.hasNext())
				{
					t+= " |-"+((Integer)it.next());
				}
				log(ic,t);
			}
			else if(arg[0].equals("closerequete")) {
				try {
					Iterator it = bdd.getStatement().values().iterator();
					int i = 0;
					while(it.hasNext())
					{
						((Statement)it.next()).close();
						i++;
					}
					log(ic,"close "+i);
				} catch (SQLException e) {
					e.printStackTrace();
					log(ic,"erreur");
				}
				log(ic,"END");
			}
			else if(arg[0].equals("reloadstage")) {
				RegisterStage.Register();
        		RegisterStage.RegisterChunk();
        		RegisterStage.RegisterItemDeny();
				log(ic,"reload");
			}
			else if(arg[0].equals("reloadplayer")) {
				ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();

    		    while (li.hasNext()){
    		    	
    		        EntityPlayer p = (EntityPlayer) li.next();
    		        StageEventFML.EntityJoinLoadStat(p, p.worldObj, true);
    		        log(ic,p.getCommandSenderName());
    		    }
				log(ic,"reload");
			}else if(arg[0].equals("save")) {
				main.getTimerManager().saver.SaveStage(true);
				log(ic,"save");
			}else if(arg[0].equals("reload")) {
				RegisterStage.Register();
        		RegisterStage.RegisterChunk();
        		RegisterStage.RegisterItemDeny();
        		
        		ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();

    		    while (li.hasNext()){
    		    	
    		        EntityPlayer p = (EntityPlayer) li.next();
    		        StageEventFML.EntityJoinLoadStat(p, p.worldObj, true);
    		        log(ic,p.getCommandSenderName());
    		    }
				log(ic,"reload all");
			}
		}
		else
		{
			log(ic, "BDD STAT:");
			log(ic, "CLASS: "+Boolean.toString(bdd.IsClass));
			log(ic, "OPEN: "+Boolean.toString(bdd.IsOpen));
			log(ic, "DEBUG: "+Boolean.toString(bdd.IsDebug));
			log(ic, "NB REQUETE: "+bdd.getId());
			log(ic, "NB REQUETE ACTIVE: "+bdd.getNbStatementActive());
			log(ic, "RUN /bdd HELP TO SEE ALL CMD");
		}
		
	}
	
	private void log(ICommandSender ic, String d)
	{
		if(ic instanceof EntityPlayer)
		{
			((EntityPlayer)ic).addChatMessage(new ChatComponentText(d));
		}
		System.out.println("[BDD CMD] "+d);
	}

}
