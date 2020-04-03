package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandMoney2 extends CommandBase {

	public static final bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "neomoney2";
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
		if(arg[0].equals("give"))
		{
			EntityPlayer p = RegisterStage.getPlayer(arg[1]);
			int nb = Integer.parseInt(arg[2]);
			if(p != null)
			{
				PlayerStats.get(p).giveMoney(nb, p);
			}else {
				bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+nb+" WHERE pseudo="+arg[1]);
			}
		}
		
	}
	
	
}
