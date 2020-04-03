package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnj;
import fr.neocraft.main.Proxy.Serveur.Stage.Shopper.ShopperRegister;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

public class CommandShopper extends CommandBase {

	public static final fr.neocraft.main.bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "shopper";
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

   
    @Override
    public void processCommand(ICommandSender sender,final String[] params) {
    	final EntityPlayer player = (EntityPlayer) sender;
    	final PlayerStats stat = PlayerStats.get(player);
    	final String name = player.getCommandSenderName();
    	if(isOp(player))
    	{
	    	if (params.length < 1) {
	    		
	        }
	        else if(params[0].equals("add"))
	        {
	        	M(player, "adding entity");
	        	InventoryPlayer inv = player.inventory;
	        	String stack ="", prix="", damage="";
	        	
	        	int nb = 0;
	        	
	        	for(int i = 0; i < inv.getSizeInventory(); i++)
	        	{
	        		if(inv.getStackInSlot(i) != null)
	        		{
	        			try 
	        			{
	        				prix += Integer.parseInt(inv.getStackInSlot(i).getDisplayName())+"&";
	        				stack += Item.getIdFromItem(inv.getStackInSlot(i).getItem())+"&";
	        				damage += inv.getStackInSlot(i).getItemDamage()+"&";
	        			} catch(Exception e)
	        			{
	        				
	        			}
	        		}
	        	}
	        
	        	EntityShopperPnj entity = new EntityShopperPnj(player.worldObj);
	        	entity.setPos(new double[] { player.posX, player.posY, player.posZ});
	        	entity.setIsShopper(true);
	        	entity.setPosition(player.posX, player.posY, player.posZ);
	        	player.worldObj.spawnEntityInWorld(entity);
	        	M(player, "entity spawn");
	        	bdd.execute("INSERT INTO "+bdd.getStringShopper()+"(`id2`, `x`, `y`, `z`, `stack`, `prix`,`damage`) VALUES ("+player.worldObj.provider.dimensionId+","+(int)player.posX+","+(int)player.posY+","+(int)player.posZ+",'"+stack+"','"+prix+"', '"+damage+"')");
	        	M(player, "bdd set");
	        }else if(params[0].equals("add2"))
	        {
	        	M(player, "adding entity");
	        	EntityShopperPnj entity = new EntityShopperPnj(player.worldObj);
	        	entity.setPos(new double[] { player.posX, player.posY, player.posZ});
	        	entity.setIsShopper(true);
	        	entity.setPosition(player.posX, player.posY, player.posZ);
	        	player.worldObj.spawnEntityInWorld(entity);
	        	M(player, "entity spawn");
	        	M(player, "bdd set");
	        }else if(params[0].equals("addRepair"))
	        {
	        	int x = Integer.parseInt(params[1]);
	        	int y = Integer.parseInt(params[2]);
	        	int z = Integer.parseInt(params[3]);
	        	M(player, "adding entity");
	        	EntityShopperPnj entity = new EntityShopperPnj(player.worldObj);
	        	entity.setPos(new double[] { player.posX, player.posY, player.posZ});
	        	entity.setIsShopper(true);
	        	bdd.update("UPDATE "+bdd.getStringShopper()+" SET `x`="+player.posX+",`y`="+player.posY+",`z`="+player.posZ+" WHERE `x`="+x+" AND`y`="+y+" AND`z`="+z);

	        	entity.setPosition(player.posX, player.posY, player.posZ);
	        	player.worldObj.spawnEntityInWorld(entity);
	        	M(player, "entity spawn");
	        	M(player, "bdd set");
	        }
	        else if(params[0].equals("reload"))
	        {
	        	ShopperRegister.register();
	        	M(player, "reload");
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

