package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandCode2 extends CommandBase {

	public static final bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "neocode2";
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
	@Override
	public void processCommand(final ICommandSender ic, String[] arg) {
		EntityPlayer p = RegisterStage.getPlayer(arg[0]);
		if(arg.length > 1)
		{
			
				if(bdd.Exist("SELECT * FROM "+bdd.getStringCode()+" WHERE code='"+arg[1]+"'"))
				{
					int id = bdd.GetFreeId();
					ResultSet r = bdd.query("SELECT * FROM "+bdd.getStringCode()+" WHERE code='"+arg[1]+"'", id);
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
											if(bdd.update("UPDATE "+bdd.getStringCode()+" SET `use`="+(r.getInt("use") + 1)+",`player`='"+r.getString("player")+p.getCommandSenderName()+"&' WHERE code='"+arg[1]+"'")) {
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
	
	public void M(EntityPlayer ic, String d)
	{
		
			ic.addChatMessage(new ChatComponentTranslation(d));
	
		
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
