package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class CommandStage0Gen extends CommandBase {


    @Override
    public String getCommandName() {
        return "Stage0Gen";
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
	private Block[] block;
	private int[] blockX, blockY, blockZ, blockMeta;
	private Entity entity[];
	private boolean pp = false;
	private boolean aa = false;
    @Override
    public void processCommand(ICommandSender sender,final String[] params) {
    	final EntityPlayer player = (EntityPlayer) sender;
    	final PlayerStats stat = PlayerStats.get(player);
    	final World w = player.worldObj;
    	if(params[0].equals("set"))
    	{
    		Stage stage = RegisterStage.getStageAtXY(player.chunkCoordX, player.chunkCoordZ, player.worldObj);
    		if(stage.getIdStage() != 0) { return;}
    		int minx = stage.getXpos() * 16;
    		int minz = stage.getZpos()*16;
    		int miny = 0;
    		Block b;
    		PrintWriter writer = null;
			try {
				writer = new PrintWriter(new File("assets/Stage0.data"), "UTF-8");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
    		for(int y = 0; y < 255; y++)
			{
				for(int x = 0; x < 16; x++)
				{
					for(int z = 0; z < 16; z++)
					{
							writer.println(Block.getIdFromBlock(w.getBlock(minx+x, miny+y, minz+z)));
	        				writer.println(x);
    						writer.println(y);
    						writer.println(z);
    						writer.println(w.getBlockMetadata(minx+x, miny+y, minz+z));

					}
				}
			}
    		writer.close();
    	}
    	else if(params[0].equals("setstageId"))
    	{
    		
    		Stage stage = RegisterStage.getStageWithId(Integer.parseInt(params[1]));
    		if(stage.getIdStage() != 0) { return;}
    		ResetStage0(w, stage);
    	}else if(params[0].equals("setstagePos"))
    	{
    		
    		Stage stage = RegisterStage.getStageAtXY(player.chunkCoordX, player.chunkCoordZ, player.worldObj);
    		if(stage.getIdStage() != 0) { return;}
    		ResetStage0(w, stage);
    	}

    }
    
    public static boolean ResetStage0(World w, Stage stage) {
    	try {
    		int minx = stage.getXpos() * 16;
    		int minz = stage.getZpos()*16;
    		int miny = 0;
			 BufferedReader br = new BufferedReader(new FileReader("assets/Stage0.data"));
			  String line = "";
			  int id,x,y,z,meta;
			  while ((line =br.readLine()) != null) 
			  {
				  try {
					  id = Integer.parseInt(line);
					  x = Integer.parseInt(br.readLine());
					  y = Integer.parseInt(br.readLine());
					  z = Integer.parseInt(br.readLine());
					  meta = Integer.parseInt(br.readLine());
					  w.setBlock(minx+x, miny+y, minz+z, Block.getBlockById(id), meta, 2);
				  } catch(NullPointerException e) {

			    	}
			  }
    	
			  br.close();
    		return true;
    	} catch(Exception e) {
    		e.printStackTrace();
    		return false;
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
