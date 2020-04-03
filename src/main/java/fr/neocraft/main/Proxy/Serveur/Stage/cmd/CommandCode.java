package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandCode extends CommandBase {

	public static final bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "neocode";
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
    
    private String[] Ch = new String[] {"A","B","C","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
    	return true;
    }
	@Override
	public void processCommand(final ICommandSender p, String[] arg) {
		if(arg.length > 0)
		{
			if(arg[0].equals("create") && isOp(p))
			{
				if(arg.length > 5)
				{
					int min = Integer.parseInt(arg[1]);
					int max = Integer.parseInt(arg[2]);
					int util = Integer.parseInt(arg[3]);
					int hour = Integer.parseInt(arg[4]);
					int usemax = Integer.parseInt(arg[5]);
					Random r = new Random();
					String code = Ch[r.nextInt(Ch.length)] +Ch[r.nextInt(Ch.length)]+""+r.nextInt(10)+""+r.nextInt(10)+"" +Ch[r.nextInt(Ch.length)] +Ch[r.nextInt(Ch.length)] ;
					M(p,"code: "+code);
					if(!bdd.Exist("SELECT * FROM "+bdd.getStringCode()+" WHERE code='"+code+"'"))
					{
						
						 Calendar currenttime = Calendar.getInstance();
					    
					    currenttime.add(Calendar.HOUR, hour);
				
						
						if(bdd.execute("INSERT INTO "+bdd.getStringCode()+"(`code`, `temps`, `min`, `max`,`usemax`) VALUES ('"+code+"','"+(currenttime.getTime()).getTime()+"','"+min+"','"+max+"', '"+usemax+"')"))
						{
							M(p,"success");
						}else
						{
							M(p,"error");
						}
					}
					else
					{
						M(p,"error code existed retry pls");
					}
					
				}
				else
				{
					M(p,"/code create minvalue maxvalue maxutil hour usemax");
				}
			}else if(arg[0].equals("free") && isOp(p))
			{
				if(arg.length > 5)
				{
				
					int id = bdd.GetFreeId();
					ResultSet r = bdd.query("SELECT * FROM "+bdd.getStringCode(), id);
					if(r != null)
					{
						try {
							while(r.next())
							{
							if(CanTemps(new Date(r.getLong("temps"))))
							{
								
								M(p,"-=-=-=-=-=-=-=-=-=-");
								M(p,"code: "+r.getString("code"));
								M(p,"Min: "+r.getInt("min"));
								M(p,"Max: "+r.getInt("max"));
								M(p,"UseMax: "+r.getInt("usemax"));
								M(p,"Use: "+r.getInt("use"));
								
								Calendar c = Calendar.getInstance();
			        			Calendar c2 = Calendar.getInstance();
			        			c2.setTime(new Date(r.getLong("temps")));
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
			        			M(p,"Temps: "+diffDays+" jour(s) "+diffHours + " heure(s) "+diffMinutes+" minute(s) restant");
							}
							}
							
						} catch(Exception e) {
							e.printStackTrace();
							M(p,"erreur");
						}
					}
					else
					{
						M(p,"null");
					}
					bdd.CloseFreeId(id);
				}
				
				else
				{
					M(p,"/code create minvalue maxvalue maxutil hour usemax");
				}
			}
			else {
				if(bdd.Exist("SELECT * FROM "+bdd.getStringCode()+" WHERE code='"+arg[0]+"'"))
				{
					int id = bdd.GetFreeId();
					ResultSet r = bdd.query("SELECT * FROM "+bdd.getStringCode()+" WHERE code='"+arg[0]+"'", id);
					if(r != null)
					{
						try {
							while(r.next())
							{
								if(CanTemps(new Date(r.getLong("temps"))))
								{
									if(r.getInt("use") < r.getInt("usemax"))
									{
										if(!PlayerDo(p.getCommandSenderName(), r.getString("player")))
										{
											int max = r.getInt("max");
											int min = r.getInt("min");
											int dif = max - min;
											if(bdd.update("UPDATE "+bdd.getStringCode()+" SET `use`="+(r.getInt("use") + 1)+",`player`='"+r.getString("player")+p.getCommandSenderName()+"&' WHERE code='"+arg[0]+"'")) {
												Random ran= new Random();
												int rec = min + ran.nextInt(dif);
												if(p instanceof EntityPlayer)
												{
													PlayerStats.get((EntityPlayer)p).giveMoney(rec, (EntityPlayer)p);
												}else {
													M(p,"win: "+rec);
												}
												
											}
											else {
												M(p,"neo.DENY_ERROR");
											}
										}
										else
										{
											M(p,"neo.code.ever");
										}
									}
									else
									{
										M(p,"neo.code.full");
									}
								}else
								{
									M(p,"neo.code.expire");
								}
							}
						} catch (SQLException e) {
							M(p,"neo.DENY_ERROR");
							e.printStackTrace();
						}
					}
					else {
						M(p,"neo.DENY_ERROR");
					}
					bdd.CloseFreeId(id);
				}
				else
				{
					M(p,"neo.code.void");
				}
			}
		
		}
		else
		{
			M(p, "neo.code_msg");
		}
		
	}
	
	public boolean PlayerDo(String name, String match)
	{
		if(match != null)
		{
			String[] d = bdd.GetArrayString(match);
			if(d != null)
			{
				for(int i = 0; i < d.length; i++)
				{
					if(d[i].equals(name))
					{
						return true;
					}
				}
				return false;
			}
			else
			{
				return false;
			}
		} else
		{
			return false;
		}
	}
	
	public boolean CanTemps(Date d)
	{
		
		Calendar currenttime = Calendar.getInstance();
	    Date sqldate = new Date((currenttime.getTime()).getTime());
	    if(d.compareTo(sqldate) < 0)
	    {
	    	return false;
	    }
	    else
	    {
	    	return true;
	    }
	}
	
	public void M(ICommandSender ic, String d)
	{
		if(ic instanceof EntityPlayer)
		{
			((EntityPlayer)ic).addChatMessage(new ChatComponentTranslation(d));
		}
		System.out.println("[BDD CMD] "+d);
    }
	

	
    public boolean isOp(ICommandSender ic)
    {
    	if(ic instanceof EntityPlayer)
		{
    		return MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayer)ic).getGameProfile()) || PlayerStats.get(((EntityPlayer)ic)).AdminStage;
		}
    	else
    	{
    		return true;
    	}
    }
}
