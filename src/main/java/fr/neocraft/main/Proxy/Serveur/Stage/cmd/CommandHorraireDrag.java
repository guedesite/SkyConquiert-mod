package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientBossBoolean;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class CommandHorraireDrag extends CommandBase {


    @Override
    public String getCommandName() {
        return "HorraireDrag";
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

	private int x = 0, y = 0,z = 0;
	private int x2 = 0, y2 = 0,z2 = 0;
    @Override
    public void processCommand(ICommandSender sender,final String[] params) {
    	if(params[0].equals("set1"))
        {
    		final EntityPlayer player = (EntityPlayer) sender;
        	final PlayerStats stat = PlayerStats.get(player);
        	final World w = player.worldObj;
        	this.x = (int)player.posX;
        	this.y = (int)player.posY;
        	this.z = (int)player.posZ;
        	M(player, this.x+" - "+this.y+" - "+this.z);
        }
        else if(params[0].equals("set2"))
        {
        	final EntityPlayer player = (EntityPlayer) sender;
        	final PlayerStats stat = PlayerStats.get(player);
        	final World w = player.worldObj;
        	this.x2 = (int)player.posX;
        	this.y2 = (int)player.posY;
        	this.z2 = (int)player.posZ;
        	M(player, this.x2+" - "+this.y2+" - "+this.z2);
        }
        else if(params[0].equals("set"))
    	{
        	final EntityPlayer player = (EntityPlayer) sender;
        	final PlayerStats stat = PlayerStats.get(player);
        	final World w = player.worldObj;
        	if( 0< this.x & 0< this.y & 0< this.z & 0< this.x2 & 0< this.y2 & 0< this.z2 & this.x< this.x2 & this.y< this.y2 & this.z< this.z2 )
        	{
	    		PrintWriter writer = null;
	    		int i = Integer.parseInt(params[1]);
				try {
					writer = new PrintWriter(new File("assets/DragonHorraire-"+i+".data"), "UTF-8");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				int nbx = (x2 - x) + 1;
				int nby = (y2 - y) + 1;
				int nbz = (z2 - z) + 1;
				for(int nbx2 = 0; nbx2 < nbx; nbx2++)
				{
					for(int nby2 = 0; nby2 < nby; nby2++)
	    			{
						for(int nbz2 = 0; nbz2 < nbz; nbz2++)
	        			{
							if(w.getBlock(x + nbx2, y + nby2, z + nbz2) != Blocks.air)
							{
								writer.println(Block.getIdFromBlock(w.getBlock(x + nbx2, y + nby2, z + nbz2)));
		        				writer.println(x + nbx2);
	    						writer.println(y + nby2);
	    						writer.println(z + nbz2);
	    						writer.println(w.getBlockMetadata(x + nbx2, y + nby2, z + nbz2));
							}
	        			}	
	    			}
				}
				M(player, "finish");
	    		writer.close();
        	}else {
        		M(player, "NOPE");
        	}
    	}
    	else if(params[0].equals("start"))
    	{
    		final EntityPlayer player = (EntityPlayer) sender;
        	final PlayerStats stat = PlayerStats.get(player);
        	final World w = player.worldObj;
    		startH(w, Integer.parseInt(params[1]));
    	
    	}else if(params[0].equals("remove"))
    	{
    		final EntityPlayer player = (EntityPlayer) sender;
        	final PlayerStats stat = PlayerStats.get(player);
        	final World w = player.worldObj;
    		RemoveH(w, Integer.parseInt(params[1]));
    	
    	}else if(params[0].equals("startboss"))
    	{
    		CommandHorraireDrag.startH(DimensionManager.getWorld(0), -1);
			main.setBoss(true);
					
			main.getNetWorkClient().sendToAll(new NetWorkClient(new ClientBossBoolean(true)));
    			
    		
	    	
    	}else if(params[0].equals("stopboss"))
    	{
		    CommandHorraireDrag.RemoveH(DimensionManager.getWorld(0), -1);
			main.setBoss(false);
			main.getNetWorkClient().sendToAll(new NetWorkClient(new ClientBossBoolean(false)));
    	
    	}
    		
    	

    }
    
    public static void startH(final World w, final int o) {
    	try {
    	
					 BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader("assets/DragonHorraire-"+o+".data"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					  String line = "";
					  int id,x,y,z,meta;
					  try {
						  while ((line =br.readLine()) != null) 
						  {
							  try {
								  id = Integer.parseInt(line);
								  x = Integer.parseInt(br.readLine());
								  y = Integer.parseInt(br.readLine());
								  z = Integer.parseInt(br.readLine());
								  meta = Integer.parseInt(br.readLine());
								  w.setBlock(x, y, z, Block.getBlockById(id), meta, 2);
							  } catch(Exception e) {
									
							    }
						  }
						  br.close();
					  } catch(Exception e) {
							
				    }

    	
    	} catch(Exception e) {
    		e.printStackTrace();
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

	public static void RemoveH(final World w, final int lastDrag) {
		try {

					 BufferedReader br = null;
					try {
						br = new BufferedReader(new FileReader("assets/DragonHorraire-"+lastDrag+".data"));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					  String line = "";
					  int id,x,y,z,meta;
					  try {
						  while ((line =br.readLine()) != null) 
						  {
							  try {
								  id = Integer.parseInt(line);
								  x = Integer.parseInt(br.readLine());
								  y = Integer.parseInt(br.readLine());
								  z = Integer.parseInt(br.readLine());
								  meta = Integer.parseInt(br.readLine());
								  w.setBlock(x, y, z, Blocks.air);
							  } catch(Exception e) {
									
							    }
						  }
						  br.close();
					  } catch(Exception e) {
							
				    }

    	
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		
	}
}
