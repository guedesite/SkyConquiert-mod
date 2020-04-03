package fr.neocraft.main.Proxy.Serveur.player;

import java.util.Timer;
import java.util.TimerTask;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.network.ForgeMessage;

public class teleport {
	private static Timer timer = new Timer();
	private static final NeoChat C = new NeoChat();
	public static void player(final int world, final EntityPlayer player, final int x, final int y, final int z, int temps, boolean stat)
	{
		
		if(ValidWorld(world, player))
		{
			if(player.getEntityWorld().provider.dimensionId == DimensionManager.getWorld(world).provider.dimensionId)
			{
				player(player, x,  y, z, temps, false);
			}
			else
			{
				player.addChatMessage(new ChatComponentTranslation(C.TELEPORT_IN_PROGRESS));
				if(temps != 0)
				{
					try {
						timer.schedule(new TimerTask() {
							  @Override
							  public void run() {
								if(RegisterStage.getPlayer(player.getCommandSenderName()) == null)
								{
									return;
								}
								  checkRiding(player);
								  int exp = player.experienceTotal;
								  WorldServer ws = ((EntityPlayerMP) player).mcServer.worldServerForDimension(world);

								  Teleporter teleporter = new NeoTeleporter(ws);
								  ((EntityPlayerMP) player).mcServer.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player),world, teleporter);
								  player.fallDistance = 0;
						
								  player.setPositionAndUpdate(x + 0.5, y, z + 0.5);
								  player.fallDistance = 0;
								  player.experienceTotal = exp;
								  
						
							  }
							}, 1000 * temps);
					} catch(Exception e)
					{
						if(!stat)
						{
							timer = new Timer();
							player(world, player, x, y,z, temps, true);
						}
						else
						{
							e.printStackTrace();
							player.addChatMessage(new ChatComponentTranslation(C.DENY_ERROR));
						}
					}
				}else {
					try {
						
						 checkRiding(player);
						int exp = player.experienceTotal;
						 ((EntityPlayerMP) player).mcServer.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player),world, DimensionManager.getWorld(world).getDefaultTeleporter());
						 player.fallDistance = 0;
					//	 player.travelToDimension(world);
						// player.setWorld(DimensionManager.getWorld(world));
						player.setPositionAndUpdate((double) x, (double) y, (double) z);
						player.fallDistance = 0;
						player.experienceTotal = exp;
					} catch(Exception e)
					{
						
						if(stat == false)
						{
							timer = new Timer();
							player(world, player, x, y,z, temps, true);
						}
						else
						{
							e.printStackTrace();
							player.addChatMessage(new ChatComponentTranslation(C.DENY_ERROR));
						}
					}
				}
			}
		}
	}
	public static void player(final EntityPlayer player, final int x, final int y, final int z, int temps, boolean stat)
	{
	
		try {
			
			player.addChatMessage(new ChatComponentTranslation(C.TELEPORT_IN_PROGRESS));
			timer.schedule(new TimerTask() {
				  @Override
				  public void run() {
					  if(RegisterStage.getPlayer(player.getCommandSenderName()) == null)
						{
							return;
						}
					  checkRiding(player);
					  player.fallDistance = 0;
						player.setPositionAndUpdate((double) x, (double) y, (double) z);
				  }
				}, 1000 * temps);
		} catch(Exception e)
		{
			if(stat == false)
			{
				timer = new Timer();
				player(player, x, y, z, temps, true);
			}
			else
			{
				e.printStackTrace();
				player.addChatMessage(new ChatComponentTranslation(C.DENY_ERROR));
			}
		}
	}
	
	
	private static boolean ValidWorld(int world, EntityPlayer player) {
		if(DimensionManager.getWorld(world) != null)
		{
			return true;
		}
		else
		{
			player.addChatMessage(new ChatComponentTranslation(C.DENY_WORLD_NOT_VALIDE));
			return false;
		}
	}
	
	private static boolean ValidWorldEntity(int world, Entity player) {
		if(DimensionManager.getWorld(world) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public static void entity(final Entity player, final int x, final int y, final int z, int temps)
	{
		timer.schedule(new TimerTask() {
			  @Override
			  public void run() {
				  if(player instanceof EntityPlayer && RegisterStage.getPlayer(player.getCommandSenderName()) == null)
					{
						return;
					}
				  player.fallDistance = 0;
				  checkRiding(player);
					player.setPosition((double) x, (double) y, (double) z);
			  }
			}, 1000 * temps);
	}
	public static void entity(final int world, final Entity player, final int x, final int y, final int z, int temps)
	{
		if(ValidWorldEntity(world, player))
		{
			if(player.worldObj.provider.dimensionId == DimensionManager.getWorld(world).provider.dimensionId)
			{
				entity(player, x,  y, z, temps);
			}
			else
			{
				if(temps != 0)
				{
					try {
						timer.schedule(new TimerTask() {
							  @Override
							  public void run() {
								  if(player instanceof EntityPlayer && RegisterStage.getPlayer(player.getCommandSenderName()) == null)
									{
										return;
									}
								  if(player instanceof EntityPlayer)
									{
									  
									  checkRiding(player);
									  int exp = (((EntityPlayer) player).experienceTotal);
										 WorldServer ws = ((EntityPlayerMP) player).mcServer.worldServerForDimension(world);
				
										  Teleporter teleporter = new NeoTeleporter(ws);
										  ((EntityPlayerMP) player).mcServer.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player),world, teleporter);
										  player.fallDistance = 0;
								
										  player.setPosition(x + 0.5, y, z + 0.5);
										  player.fallDistance = 0;
										  ((EntityPlayer) player).experienceTotal = exp;
									}
									else
									{
									 player.fallDistance = 0;
									 player.travelToDimension(world);
									 player.setWorld(DimensionManager.getWorld(world));
									 player.setPosition(x + 0.5, y, z + 0.5);
									}
							  }
							}, 1000 * temps);
					} catch(Exception e)
					{
					}
				}else {
					try {
						
						 if(player instanceof EntityPlayer && RegisterStage.getPlayer(player.getCommandSenderName()) == null)
							{
								return;
							}
						if(player instanceof EntityPlayer)
						{
							 checkRiding(player);
							int exp = (((EntityPlayer) player).experienceTotal);
							 WorldServer ws = ((EntityPlayerMP) player).mcServer.worldServerForDimension(world);
	
							  Teleporter teleporter = new NeoTeleporter(ws);
							  ((EntityPlayerMP) player).mcServer.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player),world, teleporter);
							  player.fallDistance = 0;
					
							  player.setPosition(x + 0.5, y, z + 0.5);
							  player.fallDistance = 0;
							  ((EntityPlayer) player).experienceTotal = exp;
						}
						else
						{
						 player.fallDistance = 0;
						 player.travelToDimension(world);
						 player.setWorld(DimensionManager.getWorld(world));
						 player.setPosition(x + 0.5, y, z + 0.5);
						}
					} catch(Exception e)
					{
					}
				}
			}
		}
	}
	
	
	public static void checkRiding(Entity en)
	{
		if(en.isRiding())
		{
			en.ridingEntity.riddenByEntity = null;
			en.ridingEntity = null;
		}
		
	}

}
