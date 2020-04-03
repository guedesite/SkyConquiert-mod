package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandItemDeny extends CommandBase {

	private static final NeoChat C = main.getChat();
	public static final fr.neocraft.main.bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "itemdeny";
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
        return 0;
    }

    public void init()
    {
    	
    }
    
    private double x;
	private double y;
	private double z;
	private float Yaw, Pitch;
    // -0.01x+6.05
    @Override
    public void processCommand(ICommandSender sender,final String[] params) {
    	final EntityPlayer player = (EntityPlayer) sender;
    	final PlayerStats stat = PlayerStats.get(player);
    	final String name = player.getCommandSenderName();
    	if (params.length < 1) {
    		
        }
        else if(params[0].equals("add"))
        {
        	if(isOp(player))
        	{
        		if(player.getCurrentEquippedItem() != null)
				 {
        			if(RegisterStage.IsAllowedBlockSpecial(Item.getIdFromItem(player.getCurrentEquippedItem().getItem())))
        			{
        				bdd.execute("INSERT INTO "+bdd.getStringDenySpecial()+"(`idBlock`) VALUES ("+Item.getIdFromItem(player.getCurrentEquippedItem().getItem())+")");
        				RegisterStage.RegisterItemDeny();
        				M(player, "Item d�sormait pr�sent");
        			}
        			else
        			{
        				M(player, "Item Deja Present");
        			}
				 }
        	}
        }else if(params[0].equals("reload"))
        {
        	if(isOp(player))
        	{
        		RegisterStage.RegisterItemDeny();
        		M(player, "Item Deny Reload");
        	}
        }else if(params[0].equals("remove"))
        {
        	if(isOp(player))
        	{
        		if(player.getCurrentEquippedItem() != null)
				 {
        			if(!RegisterStage.IsAllowedBlockSpecial(Item.getIdFromItem(player.getCurrentEquippedItem().getItem())))
        			{
        				bdd.execute("DELETE FROM "+bdd.getStringDenySpecial()+" WHERE idBlock="+Item.getIdFromItem(player.getCurrentEquippedItem().getItem()));
        				RegisterStage.RegisterItemDeny();
        				M(player, "Item d�sormait pr�sent");
        			}
        			else
        			{
        				M(player, "Item Deja Present");
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

