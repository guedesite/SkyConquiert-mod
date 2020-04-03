package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.sql.ResultSet;
import java.util.Iterator;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandStageLoyer extends CommandBase {

	public static fr.neocraft.main.bdd bdd = main.getbdd();

    @Override
    public String getCommandName() {
        return "stageloyer";
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
        return 3;
    }

    @Override
    public void processCommand(ICommandSender sender,final String[] params) {
    
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
    							int id = bdd.GetFreeId();
    							ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringLoyer3()+"WHERE player='"+e.getOwner()+"'", id);
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
    							bdd.CloseFreeId(id);
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
    							e.SetIsUse(false);
    							e.setOwner(null);
    							e.setArraySubOwner(null);
    							e.setBank(RegisterStage.getBankForStage(3));
    							e.setBouclierNull();
    							bdd.execute("DELETE FROM "+bdd.getStringLoyer3()+" WHERE player='"+e.getOwner()+"'");
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
