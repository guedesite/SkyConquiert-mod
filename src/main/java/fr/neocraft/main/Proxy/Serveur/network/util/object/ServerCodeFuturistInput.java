package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import fr.neocraft.main.Blocks.Special.BlockFuturist;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Proxy.Serveur.TileEntity.FuturistInputTileEntity;


public class ServerCodeFuturistInput extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	private int X , Y ,Z, world, action;
	private String newcode = "";
	public ServerCodeFuturistInput(int x, int y, int z, int w, String c, int ac)
	{
		X =x;
		Y = y; 
		Z = z;
		world = w;
		newcode = c;
		action = ac;
	}
	
	// action == 0 = define code
	//action == 1 = try code
	
	@SideOnly(Side.SERVER)
	@Override
	public void A(EntityPlayer player) {
		try {
			if(action == 0)
			{
				final World w = DimensionManager.getWorld(world);
				((FuturistInputTileEntity) w.getTileEntity(X, Y, Z)).code = newcode;
				((BlockFuturist) w.getBlock(X, Y, Z)).SendData(w, X, Y, Z, X,Y,Z, BlockFuturist.Data.TrueOutPut, 0);
				final int l = w.getBlockMetadata(X, Y, Z);
				if(5 < l )
				 {
					 if(11 < l )
					 {
						 
					 } else {
						 w.setBlockMetadataWithNotify(X, Y, Z,l+6, 2);
						
					 }
				 }
				else {
					w.setBlockMetadataWithNotify(X, Y, Z,l+12, 2);
					
				}
				
				AxisAlignedBB box = BlockMod.FuturInput.getCollisionBoundingBoxFromPool(w, X, Y, Z).expand(5D, 5D, 5D);
		        List list = w.getEntitiesWithinAABB(EntityPlayer.class, box);
		
		        if (list != null && !list.isEmpty())
		        {
		            for (int k = 0; k < list.size(); ++k)
		            {
		                Entity entity = (Entity)list.get(k);
		                if(entity instanceof EntityPlayer)
		                {
		                	 SoundManager.PlaySound(EnumSound.FuturSuccess.getSound(), (EntityPlayer)entity);
		                }
		            }
		        }
				
			} else if(action == 1)
			{
				final World w = DimensionManager.getWorld(world);
				final int l = w.getBlockMetadata(X, Y, Z);
				if(((FuturistInputTileEntity) w.getTileEntity(X, Y, Z)).code.equals(newcode))
				{
					if(5 < l )
					 {
						 if(11 < l )
						 {
							
							
						 } else {
							 w.setBlockMetadataWithNotify(X, Y, Z,l+6, 2);
							
						 }
					 }
					else {
						w.setBlockMetadataWithNotify(X, Y, Z,l+12, 2);
						
					}
					
					if(!((BlockFuturist) w.getBlock(X, Y, Z)).SendData(w, X, Y, Z, X,Y,Z, BlockFuturist.Data.TrueOutPut, 0)) {
						player.addChatMessage(new ChatComponentTranslation("neo.futur.deny"));
						player.addChatMessage(new ChatComponentTranslation("neo.futur.deny2"));
						
						AxisAlignedBB box = BlockMod.FuturInput.getCollisionBoundingBoxFromPool(w, X, Y, Z).expand(5D, 5D, 5D);
				        List list = w.getEntitiesWithinAABB(EntityPlayer.class, box);
				
				        if (list != null && !list.isEmpty())
				        {
				            for (int k = 0; k < list.size(); ++k)
				            {
				                Entity entity = (Entity)list.get(k);
				                if(entity instanceof EntityPlayer)
				                {
				                	 SoundManager.PlaySound(EnumSound.FuturError.getSound(), (EntityPlayer)entity);
				                }
				            }
				        }
					}
					else {
						
					
					
						AxisAlignedBB box = BlockMod.FuturInput.getCollisionBoundingBoxFromPool(w, X, Y, Z).expand(5D, 5D, 5D);
				        List list = w.getEntitiesWithinAABB(EntityPlayer.class, box);
				
				        if (list != null && !list.isEmpty())
				        {
				            for (int k = 0; k < list.size(); ++k)
				            {
				                Entity entity = (Entity)list.get(k);
				                if(entity instanceof EntityPlayer)
				                {
				                	 SoundManager.PlaySound(EnumSound.FuturSuccess.getSound(), (EntityPlayer)entity);
				                }
				            }
				        }
					}
				} else {
					if(5 < l )
					 {
						 if(11 < l )
						 {
							 w.setBlockMetadataWithNotify(X, Y, Z,l-6, 2);
							
						 } else {
							 
							
						 }
					 }
					else {
						w.setBlockMetadataWithNotify(X, Y, Z,l+6, 2);
						
					}
					if(!((BlockFuturist) w.getBlock(X, Y, Z)).SendData(w, X, Y, Z, X,Y,Z, BlockFuturist.Data.FalseOutPut, 0)) {
						player.addChatMessage(new ChatComponentTranslation("neo.futur.deny"));
						player.addChatMessage(new ChatComponentTranslation("neo.futur.deny2"));
						
					}
					
					AxisAlignedBB box = BlockMod.FuturInput.getCollisionBoundingBoxFromPool(w, X, Y, Z).expand(5D, 5D, 5D);
			        List list = w.getEntitiesWithinAABB(EntityPlayer.class, box);
			
			        if (list != null && !list.isEmpty())
			        {
			            for (int k = 0; k < list.size(); ++k)
			            {
			                Entity entity = (Entity)list.get(k);
			                if(entity instanceof EntityPlayer)
			                {
			                	 SoundManager.PlaySound(EnumSound.FuturError.getSound(), (EntityPlayer)entity);
			                }
			            }
			        }
				}
				
			}
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}

}
