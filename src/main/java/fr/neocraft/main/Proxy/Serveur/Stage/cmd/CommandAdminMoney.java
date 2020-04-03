package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandAdminMoney extends CommandBase {

	private static final NeoChat C = main.getChat();
	public static final fr.neocraft.main.bdd bdd = main.getbdd();
    
    public String getCommandName() {
        return "adminneomoney";
    }

    
    public String getCommandUsage(ICommandSender sender) {
        return "";
    }
    
    public int getRequiredPermissionLevel()
    {
        return 4;
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
    	EntityPlayer player = RegisterStage.getPlayer(params[0]);
    	final PlayerStats stat = PlayerStats.get(player);
    	final String name = player.getCommandSenderName();
        if(params[1].equals("give"))
        {

		        			try
				            {

					            stat.giveMoney(Integer.parseInt(params[2]), player);

				            }
				            catch (NumberFormatException ignore)
				            {
				            	
				            }
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

