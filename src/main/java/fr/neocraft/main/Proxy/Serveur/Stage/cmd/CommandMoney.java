package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.sql.ResultSet;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;

import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;

public class CommandMoney extends CommandBase {

	private static final NeoChat C = main.getChat();
	public static final fr.neocraft.main.bdd bdd = main.getbdd();
    
    public String getCommandName() {
        return "neomoney";
    }

    
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.GRAY + "/money help";
    }
    
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
    public void processCommand(ICommandSender sender,final String[] params) {
    	int nb = params.length;
    	final EntityPlayer player = RegisterStage.getPlayer(params[0]);
    	final PlayerStats stat = PlayerStats.get(player);
    	final String name = player.getCommandSenderName();
        if (params.length < 2) {
        	 M(player,C.ALLOW_MY_MONEY, EnumChatFormatting.DARK_PURPLE+""+stat.Money+"");
        }
        else if(params[1].equals("pay"))
        {
        	if (params.length == 4)
        	{

		        		if(bdd.Exist("SELECT * FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+params[2]+"'"))
		        		{
		        			try
				            {
					            int id = Integer.parseInt(params[3]);
					            if(id < 1) {M(player,C.DENY_NOMBRE_NOT_VALID);return; }
					            if(stat.AsMoney(id))
				        		{
					            		stat.removeMoney(id, player);
						              if(RegisterStage.getPlayer(params[2])!= null)
						              {
						            	  EntityPlayer p = RegisterStage.getPlayer(params[2]);
						            	  PlayerStats.get(p).giveMoney(id, p, player);
						              }
						              else
						              {
						            	  bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+id+" WHERE pseudo='"+params[1]+"'");
						              }
						               M(player,C.ALLOW_PAY,id +"$",params[2]);
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
		        		}
		        		else
		        		{
		        			 M(player,C.DENY_NO_PLAYER);
		        		}

        	}
        	else
        	{
        		M(player,getCommandUsage(sender));
        	}
        }else if(params[1].equals("give"))
        {
        	if (params.length == 4 & isOp(player))
        	{

		        		if(bdd.Exist("SELECT * FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+params[2]+"'"))
		        		{
		        			try
				            {
					              int id = Integer.parseInt(params[3]);
					              if(id < 1) {M(player,C.DENY_NOMBRE_NOT_VALID);return; }
					              if(RegisterStage.getPlayer(params[2])!= null)
					              {
					            	  EntityPlayer p = RegisterStage.getPlayer(params[2]);
					            	  PlayerStats.get(p).giveMoney(id, p, player);
					              }
					              else
					              {
					            	  bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+id+" WHERE pseudo='"+params[2]+"'");
					              }
				            }
				            catch (NumberFormatException ignore)
				            {
				            	 M(player,C.DENY_NOMBRE_NOT_VALID);	
				            }
		        		}
		        		else
		        		{
		        			 M(player,C.DENY_NO_PLAYER);
		        		}

        	}else
        	{
        		M(player,getCommandUsage(sender));
        	}
        }else if(params[1].equals("balancetop") || params[1].equals("baltop")|| params[1].equals("top") )
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
        		M(player,"neo.baltop.header");
        		
        		int max = param*10;
        		int base = (param-1)*10;
        	
        		
        		int id = bdd.GetFreeId();
        		ResultSet result = bdd.query("SELECT pseudo,money FROM "+bdd.getStringPlayer()+" ORDER BY `money` DESC", id);
        		if(result != null)
        		{
        			try {
        				int index = 0;
        				int index2 = 1;
        				while(result.next())
        				{
        					if(index <= base)
        					{
        						
        					}
        					else if(index > max)
        					{
        						M(player,"neo.baltop.end", EnumChatFormatting.GOLD+""+(param+1)+"");
        						return;
        						
        					} else {
        						M(player,"neo.baltop.player", EnumChatFormatting.GOLD+""+(base+index2)+"",EnumChatFormatting.GOLD+""+result.getString("pseudo"),EnumChatFormatting.GOLD+""+result.getInt("money")+"");
        						index2++;
        					}
        					index++;
        				}
        			} catch(Exception e)
        			{
        				e.printStackTrace();
        			}
        		}
        	
        	
        
        	
        }else if(params[1].equals("remove") || params[1].equals("take") )
        {
        	if (params.length == 4 & isOp(player))
        	{
		        		if(bdd.Exist("SELECT * FROM "+bdd.getStringPlayer()+" WHERE pseudo='"+params[2]+"'"))
		        		{
		        			try
				            {
					              int id = Integer.parseInt(params[3]);
					              if(id < 1) {M(player,C.DENY_NOMBRE_NOT_VALID);return; }
					              if(RegisterStage.getPlayer(params[2])!= null)
					              {
					            	  EntityPlayer p = RegisterStage.getPlayer(params[2]);
					            	  p.addChatMessage(new ChatComponentTranslation(C.d_purple + name + C.l_purple +" vous a enlevé " + id +"$"));
					            	  PlayerStats.get(p).removeMoney(id, p);;
					              }
					              else
					              {
					            	  bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money-"+id+" WHERE pseudo='"+params[2]+"'");
					              }
					               M(player,C.l_purple +" vous avez enleve " + id +"$ a "+C.d_purple +params[2] );
				            }
				            catch (NumberFormatException ignore)
				            {
				            	 M(player,C.DENY_NOMBRE_NOT_VALID);	
				            }
		        		}
		        		else
		        		{
		        			 M(player,C.DENY_NO_PLAYER);
		        		}
        	}else
        	{
        		M(player,getCommandUsage(sender));
        	}
        }else if(params[1].equals("help") || params[1].equals("?") )
        {
        	M(player, "neo.MONEY_CMD_0");
        	M(player, "neo.MONEY_CMD_1");
        	M(player, "neo.MONEY_CMD_2");
        }
        else if(RegisterStage.getPlayer(params[1])!= null && isOp( player))
        {
        	M(player, params[1] +": "+PlayerStats.get(RegisterStage.getPlayer(params[1])).Money);
        }
       
    	else
    	{
    		M(player,getCommandUsage(sender));
    	}
    }
    
    public boolean isOp(EntityPlayer player)
    {
    	return MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile());
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

}

