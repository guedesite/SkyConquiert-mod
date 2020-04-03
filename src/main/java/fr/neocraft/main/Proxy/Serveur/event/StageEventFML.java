package fr.neocraft.main.Proxy.Serveur.event;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.tmtravlr.jaff.entities.EntityFish;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Blocks.BlockElevateur;
import fr.neocraft.main.Blocks.Special.BlockFuturist;
import fr.neocraft.main.Item.ItemFaux;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardien;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityPet;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientBossBoolean;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientOpenGui;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.Proxy.Serveur.player.teleport;
import fr.neocraft.main.ServeurManager.DailyManager;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.network.ForgeMessage;

public class StageEventFML {
	private static final bdd bdd = main.getbdd();
	public static SimpleNetworkWrapper nw = main.getNetWorkClient();
	@SubscribeEvent
    public void serverConnectionFromClientEvent(FMLNetworkEvent.ServerConnectionFromClientEvent event) {
        if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
            NetHandlerPlayServer server = ((NetHandlerPlayServer)event.handler);
            FMLEmbeddedChannel channel =  NetworkRegistry.INSTANCE.getChannel("FORGE", Side.SERVER);
            for (WorldServer w:DimensionManager.getWorlds()) {
            	channel.writeOutbound(new ForgeMessage.DimensionRegisterMessage(w.provider.dimensionId, DimensionManager.getProviderType(w.provider.dimensionId)));
            }
            
        }
    }


	
	@SubscribeEvent
	public void PlayerTick(PlayerTickEvent event)
	{

	
		if(event.side.isServer())
		{
			PlayerStats stat = PlayerStats.get((EntityPlayer) event.player);

			if(stat.cooldowndrag >= 0)
			{
				stat.cooldowndrag--;
			}
			if(stat.invul > 0)
			{
				stat.invul--;
			}
			
			if(event.player.getCurrentEquippedItem() != null && event.player.getCurrentEquippedItem().getItem() instanceof ItemFaux)
			{
				event.player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 120, 0));
			}
			if( DailyManager.getIdDayByName() == 3)
			{
				if( stat.Tick <= 100)
				{
					 event.player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 120, 0));
					 event.player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 120, 0));
					 stat.Tick = 0;
				}
				else {
					stat.Tick++;
				}
			}

			if(event.player.posY <= 0)
			{
				if(stat.AdminStage)
				{
					return;
				}
				
				int wid = event.player.worldObj.provider.dimensionId;
				if(RegisterStage.IsWorldStage(wid) && wid != 0)
				{
					Stage stage;
					for(int e:stat.idStage)
					{
						if(e != -1)
						{
							stage = RegisterStage.getStageWithId(e);
							if(stage != null)
							{
								if(stage.getIdWorld() == wid)
								{
									 event.player.fallDistance = 0;
							    		teleport.player((EntityPlayer) event.player,stage.getX(), stage.getY(), stage.getZ(), 0, false);
							    		 event.player.fallDistance = 0;
							    		 return;
								}
							}
						}
					}
					teleport.player(wid,(EntityPlayer) event.player,8, 102, -7, 0, false);
				}
				else
				{
					 event.player.fallDistance = 0;
					teleport.player(0,(EntityPlayer) event.player,520, 110, 600, 0, false);
					 event.player.fallDistance = 0;
				}
		    	
				
			} 
		}

	}

	private double oldX = 0, oldZ = 0;

	@SubscribeEvent
	public void PlayerSpawn(PlayerEvent.PlayerRespawnEvent event)
	{
		if(event.player.getCommandSenderName().equals("guedesite")) { System.out.println("guedesite - spawn"); }
		EntityJoinLoadStat(event.player, event.player.worldObj, false);
	}
	
	@SubscribeEvent
	 public void EntityJoinWorld(PlayerEvent.PlayerLoggedInEvent event)
	 {	
		if(event.player.getCommandSenderName().equals("guedesite")) { System.out.println("guedesite - loggedin"); }
		EntityJoinLoadStat(event.player, event.player.worldObj, false);
		 if(!bdd.Exist("SELECT * FROM "+bdd.getStringDrag()+" WHERE pseudo='" + event.player.getCommandSenderName() +"'") & !bdd.Exist("SELECT * FROM "+bdd.getStringPlayer()+" WHERE pseudo='" + event.player.getCommandSenderName() +"'") )
		 {
			final EntityPlayerMP player =(EntityPlayerMP) event.player;
			 nw.sendTo(new NetWorkClient(new ClientOpenGui(7, true)), (EntityPlayerMP) player);
			
				
		 }
		 else
		 {
				bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `LastLogin`=15 WHERE pseudo='"+event.player.getCommandSenderName()+"'");
				 if(bdd.Exist("SELECT amount FROM "+bdd.getStringLoyer3()+" WHERE player='" + event.player.getCommandSenderName() +"'") )
				 {
					 int id = bdd.GetFreeId();
					 ResultSet result = bdd.query("SELECT amount FROM "+bdd.getStringLoyer3()+" WHERE player='" + event.player.getCommandSenderName() +"'", id);
					 try {
						while(result.next())
						 {
							 if(result.getInt("amount") > 6)
							 {
								 
								
								 for(Quest i:PlayerStats.get(event.player).ArrayQuetePlayer)
								 {
									 if(i.Getid() == 4)
									 {
										 i.End(event.player);
									 }
								 }
							 }
						 }
					} catch (SQLException e) {
						e.printStackTrace();
					}
					 bdd.CloseFreeId(id);
				 }
		 }
		 
		 
		main.SendTabToAll();
		 
	 }
	
	
	@SubscribeEvent
	public void PlayerQuitWorld(PlayerLoggedOutEvent event) {
		PlayerStats stat = PlayerStats.get(event.player);
		if(stat.MyEntityPet != null)
		{
			stat.MyEntityPet.setDead();
		}
		new Timer().schedule(new TimerTask() {
			  @Override
			  public void run() {
				  main.SendTabToAll();
			  }
		}, 1500);
	}

	
	@SubscribeEvent
	 public void EntityJoinWorld(PlayerEvent.PlayerChangedDimensionEvent event)
	 {	
		if(event.player.getCommandSenderName().equals("guedesite")) { System.out.println("guedesite - change dim"); }
		EntityJoinLoadStat(event.player, event.player.worldObj, false);
	 }

	

	public static void EntityJoinLoadStat(final EntityPlayer player, World w, boolean i)
	 {
		 
		
					 if(!w.isRemote)
					 {
						 final PlayerStats stat = PlayerStats.get(player);
						 stat.init(player, player.worldObj);
						 if(main.BossActive)
						 {
							 nw.sendTo(new NetWorkClient(new ClientBossBoolean(true)), (EntityPlayerMP) player);
						 }
						 if(!bdd.Exist("SELECT * FROM "+bdd.getStringDrag()+" WHERE pseudo='" + player.getCommandSenderName() +"'") & !bdd.Exist("SELECT * FROM "+bdd.getStringPlayer()+" WHERE pseudo='" + player.getCommandSenderName() +"'") )
						 {
							 bdd.execute("INSERT INTO "+bdd.getStringDrag()+"(`pseudo`,`stage`,`breed`,`AllBreed`) VALUES ('"+player.getCommandSenderName()+"','EGG','0','0&')");
							 bdd.execute("INSERT INTO "+bdd.getStringPlayer()+"(`pseudo`,`Quest`,`Element`,`QuestAmount`,`QuestFinish`) VALUES ('"+player.getCommandSenderName()+"', '0&', '0&', '0&', '')");
						 }
						
							int id = bdd.GetFreeId();
							 ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringDrag()+" WHERE pseudo='" + player.getCommandSenderName() +"'", id);
			        		 if(result != null)
			        		 {
				        		 try {
									while ( result.next() ) {
											stat.lvlGuiDrag[0] = 
													result.getInt("lvlgui");
											stat.lvlGuiDrag[1] = 
													result.getInt("health");
											stat.lvlGuiDrag[2] = 
													result.getInt("speedground");
											stat.lvlGuiDrag[3] = 
													result.getInt("speedair");
											stat.lvlGuiDrag[4] = 
													result.getInt("fireballdamage");
											stat.lvlGuiDrag[5] = 
													result.getInt("fireballspeed");
											stat.DragBreed = 
													result.getInt("breed");
											stat.Allbreed = 
													bdd.GetArrayInt(
															result.getString("AllBreed"));
										}
								}catch (Exception e) {
									e.printStackTrace();
								}
			        		 }
			        		 else
			        		 {
			        			 bdd.execute("INSERT INTO "+bdd.getStringDrag()+"(`pseudo`,`stage`,`breed`,`AllBreed`) VALUES ('"+player.getCommandSenderName()+"','EGG','0','0&')");
							 }
			        		 bdd.CloseFreeId(id);
							 stat.idStage = RegisterStage.getStageIdWithPlayer(player.getCommandSenderName());
							 id = bdd.GetFreeId();
							 ResultSet result2 = bdd.query("SELECT * FROM "+bdd.getStringPlayer()+" WHERE pseudo='" + player.getCommandSenderName() +"'", id);
			        		 if(result2 != null)
			        		 {
				        		 try {
									while ( result2.next() ) {
											stat.Money = 
													result2.getInt("money");
										
											stat.LastLogin = 
													result2.getInt("LastLogin");
											stat.LasteDisband = 
													result2.getInt("LasteDisband");
											stat.EventHorraire = 
													result2.getInt("AmountHorraire");
											
											stat.PetId = result2.getInt("PetId");
											stat.PetExp = result2.getInt("PetExp");
											stat.PetLvl = result2.getInt("PetLvl");
						
									if(stat.PetId == -1)
									{
										nw.sendTo(new NetWorkClient(new ClientOpenGui(-3)), (EntityPlayerMP) player);
									}
									else
									{
										stat.updatePet(player, true);
										nw.sendTo(new NetWorkClient(new ClientOpenGui(-1)), (EntityPlayerMP) player);
									}
						
						
										
										}
								} catch (Exception e) {	
									e.printStackTrace();}
			        		 }
			        		 else
			        		 {
			        			 bdd.execute("INSERT INTO "+bdd.getStringPlayer()+"(`pseudo`,`Quest`,`Element`,`QuestAmount`,`QuestFinish`) VALUES ('"+player.getCommandSenderName()+"', '0&', '0&', '0&', '')");
							 }
			        		 bdd.CloseFreeId(id);
			        		 id = bdd.GetFreeId();
			        		 result = bdd.query("SELECT * FROM "+bdd.getStringAlerte()+" WHERE Pseudo='"+player.getCommandSenderName()+"'", id);
			        		 if(result != null) {
			        			 try {
			        				 final ArrayList<ChatComponentTranslation> chat = new ArrayList<ChatComponentTranslation>();
			        				 while(result.next())
			        				 {
			        					 String[] arg = new String[0];
			        					 
			        					 if(result.getString("arg") != null)
			        					 {
			        						 arg = bdd.GetArrayString(result.getString("arg"));
			        					 }
			        					
			        					 chat.add(new ChatComponentTranslation(result.getString("value"), arg));
			        					 
			        				 }
			        				 if(!chat.isEmpty())
			        				 {
			        					 new Timer().schedule(new TimerTask() {

												@Override
												public void run() {
													for(ChatComponentTranslation e:chat)
													{
														player.addChatMessage(e);
													}
													
												}
				        						 
				        					 }, 5000);
			        				 }
			        			 } catch(Exception e)
			        			 {
			        				 e.printStackTrace();
			        			 }
			        		 }
					 }
	
		 }
	
	@SubscribeEvent
	public void ServerTick(ServerTickEvent event)
	{
		if(event.side == Side.SERVER)
		{
			CheckChunkEntity();
		}
	}
	
	
	private int idStage = -3000;
	
	private void CheckChunkEntity()
	{
		idStage++;
		if(idStage >= RegisterStage.nbStage)
		{
			idStage =-3000;
		}
		
		Stage stage = RegisterStage.getStageWithId(idStage);
		if(idStage < 0 || stage == null || stage.getIdStage() > 3)
		{
			return;
			
		}
		int id = bdd.GetFreeId();
		
		int s = 0;
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringChunk()+" WHERE IdStage="+idStage, id);
		if(result != null)
		{
			try {
				while(result.next())
				{
					World w = DimensionManager.getWorld(result.getInt("IdWorld"));
					if(w != null)
					{
						Chunk c = w.getChunkFromChunkCoords(result.getInt("PosX"), result.getInt("PosZ"));
						int mob = 0, animaux = 0;
						if(c.entityLists != null)
						{
						
							for(int i = 0; i < c.entityLists.length; i++)
							{
								List l2 = c.entityLists[i];
								if(l2 != null)
								{
									
									for(int o =0; o< l2.size(); o++)
									{
										Entity e = (Entity) l2.get(o);
										if(e != null)
										{
											if(e instanceof EntityAnimal && !(e instanceof EntityTameableDragon) && ! (e instanceof EntityGardien) && !(e instanceof EntityPet) && !(e instanceof EntityFish))
											{
												if(animaux <= 50)
												{
													animaux++;
												} else {
													e.setDead();
													
													s++;
												}
											} else if(e instanceof EntityMob && !(e instanceof EntityTameableDragon) && ! (e instanceof EntityGardien) && !(e instanceof EntityPet) && !(e instanceof EntityFish))
											{
												if(mob <= 75)
												{
													mob++;
												} else {
													e.setDead();
												
													s++;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (SQLException e) {
				bdd.erreur("CheckChunkEntity", e);
			}
		}
		bdd.CloseFreeId(id);
		
	}
	
	 
}
