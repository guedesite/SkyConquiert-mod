package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class CommandRangTab extends CommandBase {

	public static final bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "rangtab";
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.GRAY + "";
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
	public void processCommand(ICommandSender ic, String[] arg) {
		int rang = Integer.parseInt(arg[0]);
		String pl = arg[1];
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `rang`="+rang+" WHERE pseudo='"+pl+"'");
		main.SendTabToAll();
	}

}
