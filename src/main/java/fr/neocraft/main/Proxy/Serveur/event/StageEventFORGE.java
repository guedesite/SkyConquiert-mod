package fr.neocraft.main.Proxy.Serveur.event;

import java.util.ArrayList;
import java.util.Random;

import com.tmtravlr.jaff.entities.EntityFish;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Blocks.BlockElevateur;
import fr.neocraft.main.Blocks.BlockSeedLaitue;
import fr.neocraft.main.Blocks.BlockSeedRadis;
import fr.neocraft.main.Blocks.BlockSeedTomate;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.Entity.DragFire;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardien;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardienEvil;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnj;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnjEvil;
import fr.neocraft.main.Proxy.Serveur.Entity.MoneyOrb;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.DailyManager;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.EntityEvent.EnteringChunk;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerFlyableFallEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.event.world.ExplosionEvent;


public class StageEventFORGE {

	private static final bdd bdd = main.getbdd();
	
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
	  if(event.entity instanceof EntityPlayer)
	  {
		  event.entity.registerExtendedProperties("neocraft", 
		      	 new PlayerStats((EntityPlayer) event.entity));
		  }
	  }
	
	@SubscribeEvent 
	 public void EnterChunk(EnteringChunk event)
	 {
		 if(event.entity instanceof EntityPlayer & !event.entity.worldObj.isRemote)
		 {
			 

			EntityPlayer player = (EntityPlayer) event.entity;
			PlayerStats stat = PlayerStats.get(player);
			Stage stage = RegisterStage.getStageAtXY(event.newChunkX, event.newChunkZ, event.entity.worldObj);
			Stage oldstage = RegisterStage.getStageAtXY(event.oldChunkX, event.oldChunkZ, event.entity.worldObj);
			if(stage != null)
			{
				if(stat.AdminStage)
				{
					if(stat.lastidStage != stage.getId())
					{
						if(stage.IsUse())
						{
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GRAY + "Owner: " + stage.getOwner() + " id: " + stage.getId() + " idStage: " + stage.getIdStage() +  " idWorld: " + stage.getIdWorld()));
						}
						else
						{
							player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GRAY + "No owner id: " + stage.getId() + " idStage: " + stage.getIdStage() +  " idWorld: " + stage.getIdWorld()));
							
						}
						stat.lastidStage = stage.getId();
					}
				}
				/*else if(stage.getIdStage() == 0)
				{
					if(stage.isOwner(player.getCommandSenderName()) & stat.lastidStage != stage.getId())
					{
						stat.lastidStage = stage.getId();
						player.addChatMessage(new ChatComponentTranslation("neo.event.enter.domaine.my"));
					}
					else
					{
						if(stat.lastidStage != -1)
						{
							stat.lastidStage = -1;
							player.addChatMessage(new ChatComponentTranslation("neo.event.enter.void"));
						}
					}
				}*/
				else if(stage.getIdStage() == 5)
				{
					if(stat.lastidStage != stage.getId())
					{
						stat.lastidStage = stage.getId();
						player.addChatMessage(new ChatComponentTranslation("neo.event.enter.secure"));
					}
					
				}
				else if(stage.getIdStage() == 4 || stage.getIdStage() == 6)
				{
					if(stat.lastidStage != stage.getId())
					{
						stat.lastidStage = stage.getId();
						player.addChatMessage(new ChatComponentTranslation("neo.event.enter.dangerous"));
					}
					
				}
				else if(stage.getIdStage() != 7)
				{
					try {
					
						if(stage.IsUse())
						{
							if(stage.isIn(player.getCommandSenderName()) & stat.lastidStage != stage.getId())
							{
								stat.lastidStage = stage.getId();
								player.addChatMessage(new ChatComponentTranslation("neo.event.enter.domaine.my"));
							}else if(stage.getStringBouclier() != 0 && RegisterStage.getFirstStageIdPlayer(stat) != stage.getId() && !stage.isAlly(player.getCommandSenderName()) && !stage.isIn(player.getCommandSenderName()) && player.posY <= 256 && stat.invul == 0)
							{ 
								int  u =2;
								if(player.isRiding())
								{
									u += 4;
								}
								if(event.newChunkX == event.oldChunkX)
								{
									if(event.newChunkZ> event.oldChunkZ)
									{
										player.setPositionAndUpdate(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ-u);
									}
									else {
										player.setPositionAndUpdate(player.lastTickPosX, player.lastTickPosY, player.lastTickPosZ+u);
									}
								}
								else {
										if(event.newChunkX> event.oldChunkX)
										{
											player.setPositionAndUpdate(player.lastTickPosX-u, player.lastTickPosY, player.lastTickPosZ);
										}
										else {
											player.setPositionAndUpdate(player.lastTickPosX+u, player.lastTickPosY, player.lastTickPosZ);
										}
									
								}
							}
							if(stat.lastidStage != stage.getId())
							{
								player.addChatMessage(new ChatComponentTranslation("neo.event.enter.domaine.use",stage.getName(), stage.getOwner()));
								stat.lastidStage = stage.getId();
							}
						}
						else
						{
							if(stat.lastidStage != stage.getId() && stage.getIdStage() != 0) // add
							{
								player.addChatMessage(new ChatComponentTranslation("neo.event.enter.by", EnumChatFormatting.DARK_PURPLE+""+ stage.getIdStage()+"", EnumChatFormatting.DARK_PURPLE+""+stage.getBank()+""));
								stat.lastidStage = stage.getId();
							}else
							if(stat.lastidStage != -1)
							{
								player.addChatMessage(new ChatComponentTranslation("neo.event.enter.void"));
								stat.lastidStage = -1;
							}
							
						}
					}catch(NullPointerException e)
					{
						System.out.println("player logged to early");
					}
				}

			}
			else
			{
				if(stat.lastidStage != -1)
				{
					player.addChatMessage(new ChatComponentTranslation(EnumChatFormatting.GRAY + "Vous entrez dans un domaine vide."));
					stat.lastidStage = -1;
				}
			}
			
		 }
	 }
	 
	 @SubscribeEvent
	 public void EnderPearl(EnderTeleportEvent event)
	 {
		 if(event.entity instanceof EntityPlayer & !event.entity.worldObj.isRemote)
		 {
			 Stage stage = RegisterStage.getStageAtXY((int) event.entity.worldObj.getChunkFromBlockCoords((int)event.targetX, (int)event.targetZ).xPosition, (int) event.entity.worldObj.getChunkFromBlockCoords((int)event.targetX, (int)event.targetZ).zPosition, event.entity.worldObj);
			 
			 if(!this.CanDo("enderpearl",stage, (EntityPlayer) event.entity,(int) event.targetY))
			 {
				 event.setCanceled(true);
			 }
		 }
	 }
	 
	 @SubscribeEvent
	 public void Fall(LivingFallEvent event)
	 {
		 if(event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		 {
			 Stage stage = RegisterStage.getStageAtXY((int) event.entity.chunkCoordX, event.entity.chunkCoordZ, event.entity.worldObj);
			 if(stage != null)
			 {
				 if(!stage.isOwner(((EntityPlayer)event.entity).getCommandSenderName()))
				 {
					 if(!stage.isSubOwner(((EntityPlayer)event.entity).getCommandSenderName()) && !stage.isAlly(((EntityPlayer)event.entity).getCommandSenderName()))
					 {
						 event.distance = 0;
						 event.setCanceled(true);
					 }
				 }
			 }
			 else
			 {
				 if(event.entity.worldObj.provider.dimensionId != 201)
				 {
					 event.distance = 0;
					 event.setCanceled(true);
				 }

			 }
			 
		 }
	 }
	 @SubscribeEvent
	 public void Fallplayer(PlayerFlyableFallEvent event)
	 {
		 if(event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		 {
			 Stage stage = RegisterStage.getStageAtXY((int) event.entity.chunkCoordX, event.entity.chunkCoordZ, event.entity.worldObj);
			 if(stage != null)
			 {
				 if(!stage.isOwner(((EntityPlayer)event.entity).getCommandSenderName()))
				 {
					 if(!stage.isSubOwner(((EntityPlayer)event.entity).getCommandSenderName()) && !stage.isAlly(((EntityPlayer)event.entity).getCommandSenderName()))
					 {
						 event.distance = 0;
					 }
				 }
			 }
			 else
			 {
				 if(event.entity.worldObj.provider.dimensionId != 201)
				 {
					 event.distance = 0;
				 }
			 }
			 
		 }
	 }
	 
	 /*
	 @SubscribeEvent
	 public void EntityItemPickup(EntityItemPickupEvent event)
	 {
		 if(event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote)
		 {
			 Stage stage = RegisterStage.getStageAtXY((int) event.entity.chunkCoordX, event.entity.chunkCoordZ, event.entity.worldObj);
			 if(stage != null)
			 {
				 if(stage.getIdStage() != 4 && stage.getIdStage() != 3 && stage.getIdStage() != 5)
				 {
					 if(!stage.isOwner(((EntityPlayer)event.entity).getCommandSenderName()))
					 {
						 if(!stage.isSubOwner(((EntityPlayer)event.entity).getCommandSenderName()))
						 {
							 if(!this.CanDo("block",stage, (EntityPlayer)event.entity) && stage.getIdStage() != 4 && stage.getIdStage() != 5)
							 {
								 event.setCanceled(true);
							 }
							 else {
								 PlayerStats.get((EntityPlayer)event.entity).QuestEvent("pickup_"+event.item.getEntityItem().getItem().getUnlocalizedName().replaceAll("item.", "").replaceAll(".name", ""), (EntityPlayer)event.entity, event.item.getEntityItem().stackSize);
							 }
						 }
					 }
				 }
			 }
			 else
			 {
				
				 if(event.entity.worldObj.provider.dimensionId != 201)
				 {
					 event.setCanceled(true);
				 }
			 }
			 
		 } 
	 } */
	 
	/* @SubscribeEvent
	 public void NameFormat(PlayerEvent.NameFormat event)
	 {		
		 PlayerStats s = PlayerStats.get(event.entityPlayer);
		 if(s.idStage[0] != -1)
		 {
			 try {
				 event.displayname = RegisterStage.getStageWithId(s.idStage[0]).getName()+" | " + event.username;
			 } catch(Exception e)
			 {
				 event.displayname = EnumChatFormatting.ITALIC + "NEW | "+event.username;
			 }
		 }
		 else
		 {
			 event.displayname = EnumChatFormatting.ITALIC + "NEW | "+event.username;
		 }
	 }
	 */
	 @SubscribeEvent
	 public void onEntityJoinWorld(EntityJoinWorldEvent event)
	 {
		 if(!event.world.isRemote)
		 {
			 
		
			 if(event.entity instanceof EntitySpider || event.entity instanceof EntityZombie ||  event.entity instanceof EntityCreeper ||  event.entity instanceof EntitySkeleton ||  event.entity instanceof EntityEnderman ||  event.entity instanceof EntitySlime || 	 event.entity instanceof EntityPigZombie ||  event.entity instanceof EntityWitch)
			 {
				 Chunk c = event.world.getChunkFromBlockCoords((int)event.entity.posX,(int) event.entity.posZ);
				 Stage stage = RegisterStage.getStageAtXY(c.xPosition, c.zPosition, event.world);

				 if(stage != null)
				 {

					if(!stage.IsUse() || !RegisterStage.canMobSpawnInStage(stage.getIdStage()) )
					{
						
							 event.setCanceled(true);
							 return ;
					} else if(main.getHourlyQuest() == 1)
					{
						if(new Random().nextInt(4) == 1)
						{
							 event.setCanceled(true);
							 EntityShopperPnjEvil ent = new EntityShopperPnjEvil(event.world);
							 ent.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
							 event.world.spawnEntityInWorld(ent);
							 return;
						}
					} else {
						if(new Random().nextInt(20) == 1)
						{
							 event.setCanceled(true);
							 EntityShopperPnjEvil ent = new EntityShopperPnjEvil(event.world);
							 ent.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
							 event.world.spawnEntityInWorld(ent);
							 return;
						}
					}
				 }
				 else {
					 if(event.entity.worldObj.provider.dimensionId != 201)
					 {
						 event.setCanceled(true);
					 }
				}
				
				
			
			/*	else if(DailyManager.getIdDayByName() == 5 )
				{
					if(event.entity instanceof EntityMob)
					{
						Entity next = null;
						if(event.entity instanceof EntitySpider)
						{
							next = new EntitySpider(event.world);
						} else if(event.entity instanceof EntityZombie)
						{
							next = new EntityZombie(event.world);
						} else if(event.entity instanceof EntityCreeper)
						{
							next = new EntityCreeper(event.world);
						} else if(event.entity instanceof EntityWitch)
						{
							next = new EntityWitch(event.world);
						} else if(event.entity instanceof EntitySkeleton)
						{
							next = new EntitySkeleton(event.world);
						} else if(event.entity instanceof EntityEnderman)
						{
							next = new EntityEnderman(event.world);
						} else if(event.entity instanceof EntitySlime)
						{
							next = new EntitySlime(event.world);
						}
						if(next != null)
						{
							next.setPosition(event.entity.posX, event.entity.posY, event.entity.posZ);
							event.world.spawnEntityInWorld(next);
						}
					}
				} */
			 }	
		 }
	 }
	
	 @SubscribeEvent
	  public void PlayerPickupXp(PlayerPickupXpEvent event)
	  {
		  if(!event.entity.worldObj.isRemote)
		  {
			  if(DailyManager.getIdDayByName() == 7)
			  {
				  event.orb.xpValue = (int) (event.orb.xpValue * PlayerStats.get(event.entityPlayer).bonusday);
			  }
			  
		  }
	  } 
	 
	 private boolean canStageAttack(Stage s)
	 {
		 if(s.getIdStage() == 4)
		 {
			 return false;
		 } else  if(s.getIdStage() == 6)
		 {
			 return !s.IsInAttack();
		 } 
		 return true;
	 }
	 
	 @SubscribeEvent
	 public void AttackEntity(AttackEntityEvent event)
	 {
		 if(!event.entity.worldObj.isRemote)
		 {
			 if(event.entity instanceof EntityPlayer)
			 {
				 Stage stage = RegisterStage.getStageAtXY(event.entity.chunkCoordX, event.entity.chunkCoordZ, event.entity.worldObj);
				 Stage stage2 = RegisterStage.getStageAtXY(event.target.chunkCoordX, event.target.chunkCoordZ, event.entity.worldObj);
				 if(stage == null || stage2 == null)
				 {
					 if(stage == null &&  stage2 == null && event.entity.worldObj.provider.dimensionId == 201)
					 {
						 return;
					 }
					 else
					 {
						 event.setCanceled(true);
						 return;
					 }
				 }
				 else if(event.target instanceof EntityShopperPnj)
				 {
					 event.setCanceled(true);
				 }
				 else if(event.target instanceof EntityFish)
				 {
					 if(event.target.worldObj.getBlock((int)event.target.posX, (int)event.target.posY,(int)event.target.posZ) == Blocks.water)
					 {
						 event.setCanceled(true);
					 }
				 }

				 else if(event.target instanceof EntityPlayer)
				 {
					 if(canStageAttack(stage) || canStageAttack(stage2))
					 {
						 event.setCanceled(true); 
					 }
					 else {
						PlayerStats.get((EntityPlayer)event.target).setInAttack();
						PlayerStats.get((EntityPlayer)event.entity).setInAttack();
					 }
				 }
				 else if((event.target instanceof EntityGardien))
				 {
					 if(!this.CanDo("attaque",stage2, (EntityPlayer)event.entity, -10) )
					 {
						 event.setCanceled(true);
					 }
				 }

			 }
			 else
			 {
				 if(event.target instanceof EntityPlayer && !(event.entity instanceof EntityGardien))
				 {
					 Stage stage = RegisterStage.getStageAtXY(event.entity.chunkCoordX, event.entity.chunkCoordZ, event.entity.worldObj);
					 Stage stage2 = RegisterStage.getStageAtXY(event.target.chunkCoordX, event.target.chunkCoordZ, event.entity.worldObj);
					 
					 if(stage == null || stage2 == null)
					 {
						 if(stage == null &&  stage2 == null && event.entity.worldObj.provider.dimensionId == 201)
						 {
							 return;
						 }
						 else
						 {
							 event.setCanceled(true);
							 return;
						 }
					 }
					 else if(stage.getId() != stage2.getId())
					 {
						 
						 event.setCanceled(true);
						
					 } 
						 
					 else if(!this.CanDo("attaque",stage2, (EntityPlayer)event.target, -10))
					 {
						
							 event.setCanceled(true);
						
					 }
				 }
			 }
		 }
	 }
	 
	 @SubscribeEvent
	 public void Bonemeal(BonemealEvent event)
	 {
		 if(!event.world.isRemote)
		 {
			 EntityPlayer player = (EntityPlayer) event.entityPlayer;
			 Stage stage2 = RegisterStage.getStageAtXY(event.world.getChunkFromBlockCoords(event.x, event.z).xPosition, event.world.getChunkFromBlockCoords(event.x, event.z).zPosition, event.world);
			 if(stage2 == null && event.world.provider.dimensionId == 201)
			 {
				 return;
			 }
			 if(!this.CanDo("bonemeal",stage2, player, event.y))
			 {
				 event.setCanceled(true);
			 }
		 }
	 }
	 
	 @SubscribeEvent
	 public void FillBucket(FillBucketEvent event)
	 {
		 if(!event.world.isRemote)
		 {
			 EntityPlayer player = (EntityPlayer) event.entityPlayer;
			 Stage stage = RegisterStage.getStageAtXY(event.world.getChunkFromBlockCoords(event.target.blockX, event.target.blockZ).xPosition, event.world.getChunkFromBlockCoords(event.target.blockX, event.target.blockZ).zPosition, event.world);
			 if(stage != null)
			 {
				 if(!this.CanDo("block",stage, player, -10))
				 {
					 event.setCanceled(true);
				 }
			 } else {
				 if(event.entity.worldObj.provider.dimensionId == 201)
				 {
					 return;
				 }
				 else
				 {
					 event.setCanceled(true);
					 return;
				 }
			 }
		 }
	 }
	 
	 @SubscribeEvent
	public void ClickGauche(PlayerEvent.BreakSpeed event)
	{
		 if(!event.entity.worldObj.isRemote)
		 {
			 EntityPlayer player = (EntityPlayer) event.entityPlayer;
			 PlayerStats stat = PlayerStats.get(player);
				 Stage stage2 = RegisterStage.getStageAtXY((int) event.entityPlayer.worldObj.getChunkFromBlockCoords(event.x, event.z).xPosition, (int) event.entityPlayer.worldObj.getChunkFromBlockCoords(event.x, event.z).zPosition, event.entityPlayer.worldObj);
				 if(stage2 == null && event.entityPlayer.worldObj.provider.dimensionId == 201)
				 {
					 return;
				 }
				 if(stage2 != null)
				 {
					 
					 if(stage2.getIdStage()==7)
					 {
						
						 if(event.y < 102 && event.y > 72)
						 {
						
							 return;
						 }
					 }
				 }
				 if(!this.CanDo("container",stage2, player, event.y) & !RegisterStage.IsAllowedBlockSpecial(Block.getIdFromBlock(event.entityPlayer.worldObj.getBlock(event.x, event.y, event.z))))
				 {
					 event.setCanceled(true);
					 player.closeScreen();
					 return;
				 }
				 else if(!this.CanDo("interact",stage2, player, event.y))
				 {
					 event.setCanceled(true);
					 return;
				 }
		 }
	}
	 @SubscribeEvent
	 public void LivingHurt(LivingHurtEvent event)
	 {
		 if(!event.entity.worldObj.isRemote)
		 {
			 if(event.source.getSourceOfDamage() != null)
			 {
				 if(event.entity instanceof EntityPlayer)
				 {
					 PlayerStats stat = PlayerStats.get((EntityPlayer) event.entity);
					 if(stat.PetId == 1)
					 {
						event.ammount -= stat.PetLvl * 0.1;
					 } else  if(stat.PetId == 2) {
						 event.ammount += stat.PetLvl * 0.04;
					 }
					 if(event.ammount  < 0)
					 {
						 event.ammount = 0;
					 }
				 }
				 if(event.source.getEntity() instanceof EntityPlayer & event.source.isProjectile())
				 { 
					if(event.entity instanceof EntityPlayer || event.entity instanceof EntityGardien)
					 {
						Stage stage = RegisterStage.getStageAtXY(event.source.getEntity().chunkCoordX, event.source.getEntity().chunkCoordZ, event.entity.worldObj);
						Stage stage2 = RegisterStage.getStageAtXY(event.entity.chunkCoordX, event.entity.chunkCoordZ, event.entity.worldObj);
						if(stage == null || stage2 == null)
						{
							if(stage == null &&  stage2 == null && event.entity.worldObj.provider.dimensionId == 201)
							 {
								 return;
							 }
							 else
							 {
								 event.setCanceled(true);
								 return;
							 }
						}
						else {
							if(canStageAttack(stage) && canStageAttack(stage2))
							{
								event.setCanceled(true);
							}
						}

					 } 
					else if(event.source.getSourceOfDamage() instanceof EntityPlayer)
					{
						Stage stage = RegisterStage.getStageAtXY(event.source.getEntity().chunkCoordX, event.source.getEntity().chunkCoordZ, event.entity.worldObj);
						Stage stage2 = RegisterStage.getStageAtXY(event.entity.chunkCoordX, event.entity.chunkCoordZ, event.entity.worldObj);
						if(stage.getId() != stage2.getId())
						{
							event.setCanceled(true);
						} else {
							if(!this.CanDo("attaque",stage, (EntityPlayer)event.source.getSourceOfDamage(), -10))
							 {
								 event.setCanceled(true);
							 }
						}
					}
				 }
			 }
		 }
	 } 
	 
	 @SubscribeEvent
	 public void Dead(LivingDeathEvent event)
	 {
		 if(!event.entityLiving.worldObj.isRemote)
		 { 
			 int mont = 5;
			 int ratio = 2000;
			 int r= 0;
			 if(event.entity instanceof EntitySpider 
					 || event.entity instanceof EntityZombie 
					 ||  event.entity instanceof EntityCreeper
					 ||  event.entity instanceof EntitySkeleton 
					 ||  event.entity instanceof EntityEnderman 
					 ||  event.entity instanceof EntitySlime 
					 ||  event.entity instanceof EntityPigZombie 
					 ||  event.entity instanceof EntityWitch
					 ||  event.entity instanceof EntityPig
					 ||  event.entity instanceof EntityCow
					 ||  event.entity instanceof EntityChicken
					 ||  event.entity instanceof EntitySheep
					 ||  event.entity instanceof EntityShopperPnj)
			{
				 r= event.entity.worldObj.rand.nextInt((5) + 1);
				 if (event.entity instanceof EntityZombie)
				 {
					 if(event.source.getEntity() instanceof EntityPlayer)
					 {
						 PlayerStats.get((EntityPlayer)event.source.getEntity()).QuestEvent("kill_zombie", (EntityPlayer)event.source.getEntity(), 1);
					 }
				 }else if (event.entity instanceof EntityEnderman)
				 {
					 if(event.source.getEntity() instanceof EntityPlayer)
					 {
						 PlayerStats.get((EntityPlayer)event.source.getEntity()).QuestEvent("kill_enderman", (EntityPlayer)event.source.getEntity(), 1);
					 }
				 }else if (event.entity instanceof EntitySkeleton)
				 {
					 if(event.source.getEntity() instanceof EntityPlayer)
					 {
						 PlayerStats.get((EntityPlayer)event.source.getEntity()).QuestEvent("kill_skeleton", (EntityPlayer)event.source.getEntity(), 1);
					 }
				 }
			 } else if (event.entity instanceof EntityShopperPnjEvil)
			 {
				 ratio = 1000;
				 r= event.entity.worldObj.rand.nextInt((20) + 1);
				 if(event.source.getEntity() instanceof EntityPlayer)
				 {
					 PlayerStats.get((EntityPlayer)event.source.getEntity()).QuestEvent("kill_evil", (EntityPlayer)event.source.getEntity(), 1);
				 }
				 mont = 10;
				 
			 }else if (event.entity instanceof EntityGardienEvil)
			 {
				 ratio = 1000;
				 r= event.entity.worldObj.rand.nextInt((75) + 1);
				 if(event.source.getEntity() instanceof EntityPlayer)
				 {
					 PlayerStats.get((EntityPlayer)event.source.getEntity()).QuestEvent("kill_gardienevil", (EntityPlayer)event.source.getEntity(), 1);
				 }
				 mont = 50;
				 
			 }else if (event.entity instanceof EntityWitch)
			 {
				 if(event.source.getEntity() instanceof EntityPlayer)
				 {
					 PlayerStats.get((EntityPlayer)event.source.getEntity()).QuestEvent("kill_witch", (EntityPlayer)event.source.getEntity(), 1);
				 }
			 }else if (event.entity instanceof EntityGardien)
			 {
				 r= event.entity.worldObj.rand.nextInt((15) + 1);
				
			 }
			 if(r != 0)
			 {
				 int nbb = 5;
				 double rat = 1.5;
				 if(event.source.getEntity() instanceof EntityPlayer && DailyManager.getIdDayByName() == 1)
				 {
					
					 rat = PlayerStats.get((EntityPlayer)event.source.getEntity()).bonusday;
					
					 r = (int) ( r*rat);
				 }
				 if(event.source.getEntity() instanceof EntityPlayer && DailyManager.getIdDayByName() == 3)
				 {
					
					 rat = PlayerStats.get((EntityPlayer)event.source.getEntity()).bonusday;
					 nbb = (int) ( nbb*rat);
				
				 }
				 for(int i =0; i<=r;i++)
				 {
					 event.entity.worldObj.spawnEntityInWorld(new MoneyOrb(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, mont));
				 } 
			
			
				 if (event.entity.worldObj.rand.nextInt(ratio) == nbb && event.source.getEntity() instanceof EntityPlayer)
				 {
					 SoundManager.PlaySoundWithDistance(EnumSound.NeoMClose.getSound(), (EntityPlayer)event.source.getEntity()); 
					 int tp = event.entity.worldObj.rand.nextInt(8);
					 if (tp == 0)
					 {
						 dropBlockAsItem(event.entity.worldObj,(int) event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ, new ItemStack(ItemMod.gardien, 1));
					 } else  if (tp == 1)
					 {
						 dropBlockAsItem(event.entity.worldObj,(int) event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ, new ItemStack(ItemMod.gardien, 2));
					 }
					 else  if (tp == 2)
					 {
						 dropBlockAsItem(event.entity.worldObj,(int) event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ, new ItemStack(ItemMod.Ultime_Key, 1));
					 }else  if (tp == 3)
					 {
						 dropBlockAsItem(event.entity.worldObj,(int) event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ, new ItemStack(ItemMod.gardien, 1));
					 }else  if (tp == 4)
					 {
						 dropBlockAsItem(event.entity.worldObj,(int) event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ, new ItemStack(ItemMod.bouclier, 1, 24));
					 }else  if (tp == 5)
					 {
						 dropBlockAsItem(event.entity.worldObj,(int) event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ, new ItemStack(ItemMod.bouclier, 1,12));
					 }else  if (tp == 6)
					 {
						 dropBlockAsItem(event.entity.worldObj,(int) event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ, new ItemStack(ItemMod.bouclier, 1,12));
					 }else  if (tp == 7)
					 {
						 dropBlockAsItem(event.entity.worldObj,(int) event.entity.posX,(int) event.entity.posY,(int) event.entity.posZ, new ItemStack(ItemMod.bouclier, 1,6));
					 }

				 }
					
			 }
			 
		 }
	 }
	 protected void dropBlockAsItem(World p_149642_1_, int p_149642_2_, int p_149642_3_, int p_149642_4_, ItemStack p_149642_5_)
	    {
	        if (!p_149642_1_.isRemote && p_149642_1_.getGameRules().getGameRuleBooleanValue("doTileDrops") && !p_149642_1_.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
	        {
	       
	            float f = 0.7F;
	            double d0 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	            double d1 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	            double d2 = (double)(p_149642_1_.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
	            EntityItem entityitem = new EntityItem(p_149642_1_, (double)p_149642_2_ + d0, (double)p_149642_3_ + d1, (double)p_149642_4_ + d2, p_149642_5_);
	            entityitem.delayBeforeCanPickup = 10;
	            p_149642_1_.spawnEntityInWorld(entityitem);
	        }
	    }
	 
	 @SubscribeEvent
	 public void PlayerInteract(PlayerInteractEvent event)
	 { 
		 if(!event.world.isRemote)
		 { 
					 EntityPlayer player = (EntityPlayer) event.entityPlayer;
						 PlayerStats stat = PlayerStats.get(player);
							 Stage stage2 = RegisterStage.getStageAtXY((int) event.world.getChunkFromBlockCoords(event.x, event.z).xPosition, (int) event.world.getChunkFromBlockCoords(event.x, event.z).zPosition, event.world);
							 if(stage2 == null && event.world.provider.dimensionId == 201)
							 {
								 return;
							 }
							 if(stage2 != null)
							 {
								 
								 if(stage2.getIdStage()==7)
								 {
									
									 if(event.y < 102 && event.y > 72)
									 {
									
										 return;
									 }
								 }
							 }
							 if(event.action.equals(Action.RIGHT_CLICK_BLOCK) || event.action.equals(Action.LEFT_CLICK_BLOCK))
							 {
								 if(!this.CanDo("container",stage2, player, event.y) & !RegisterStage.IsAllowedBlockSpecial(Block.getIdFromBlock(event.world.getBlock(event.x, event.y, event.z))))
								 {
									 event.setCanceled(true);
									 player.closeScreen();
									 return;
								 }
								 else if(!this.CanDo("interact",stage2, player, event.y))
								 {
									 event.setCanceled(true);
									 return;
								 }
								 
							 }
							 if(stage2 != null)
							 {
								 if(stage2.isOwner(player.getCommandSenderName())  && event.world.getBlock(event.x, event.y, event.z) == Blocks.bedrock && event.action.equals(event.action.LEFT_CLICK_BLOCK)&& event.face != -1 && event.entity.worldObj.provider.dimensionId != 201)
									{
										int x =0,y=0,z=0;
										switch(event.face)
										{
											case 0:
												y++;
												break;
											case 1:
												y--;
												break;
											case 2:
												z++;
												break;
											case 3:
												z--;
												break;
											case 4:
												x++;
												break;
											case 5:
												x--;
												break;
											
										}
										Chunk c = event.world.getChunkFromBlockCoords(event.x+x, event.z+z);
										Stage stage3 = RegisterStage.getStageAtXY(c.xPosition, c.zPosition, c.worldObj);
										if(stage3 != null && stage2 != null)
										{
											if(stage3.getId() == stage2.getId() && event.world.getBlock(event.x+x, event.y+y, event.z+z)==Blocks.air)
											{
												event.world.setBlock(event.x,event.y, event.z, Blocks.air);
												event.world.setBlock(event.x+x,event.y+y, event.z+z, Blocks.bedrock);
												
											}
										}
										
										
										
										
									}
							 }
							 if(event.world.getBlock(event.x,event.y,event.z) instanceof BlockElevateur) {
								 int l = event.world.getBlockMetadata(event.x, event.y,event.z);
									
								 
								 if(2 <= l)
								 {
									 event.world.setBlockMetadataWithNotify(event.x, event.y,event.z,0, 2);
	
								 } else {
									 event.world.setBlockMetadataWithNotify(event.x, event.y,event.z,l++, 2);
	
								 }
								 return;
							 }
							if(player.getCurrentEquippedItem() != null)
							 {
								stat.QuestEvent("interact_"+player.getCurrentEquippedItem().getUnlocalizedName().replace("tile.", "").replace(".name", "").replace("item.", ""), player, 1);
								if(player.getCurrentEquippedItem().getItem() == ItemMod.Vote_Key && event.world.getBlock(event.x, event.y, event.z) == BlockMod.BoxVote)
								{
									player.openGui(main.instance, 5, event.world, event.x, event.y, event.z);
								} else if(player.getCurrentEquippedItem().getItem() == ItemMod.Ultime_Key && event.world.getBlock(event.x, event.y, event.z) == BlockMod.UltimeBox)
								{
									player.openGui(main.instance, 8,event. world, event.x, event.y, event.z);
								}
								else if(player.getCurrentEquippedItem().getItem() == Items.wheat_seeds && event.face == 1 && event.world.getBlock(event.x, event.y, event.z) == BlockMod.FarmLand && event.world.isAirBlock(event.x, event.y+1, event.z))
								{
									event.world.setBlock(event.x, event.y+1, event.z, BlockMod.Wheat_culture);
									player.getCurrentEquippedItem().splitStack(1);
								}
								else if(player.getCurrentEquippedItem().getItem() == Items.carrot && event.face == 1 && event.world.getBlock(event.x, event.y, event.z) == BlockMod.FarmLand && event.world.isAirBlock(event.x, event.y+1, event.z))
								{
									event.world.setBlock(event.x, event.y+1, event.z, BlockMod.Carrot_Culture);
									player.getCurrentEquippedItem().splitStack(1);
								}
								else if(player.getCurrentEquippedItem().getItem() == Items.potato && event.face == 1 && event.world.getBlock(event.x, event.y, event.z) == BlockMod.FarmLand && event.world.isAirBlock(event.x, event.y+1, event.z))
								{
									event.world.setBlock(event.x, event.y+1, event.z, BlockMod.Potato_Culture);
									player.getCurrentEquippedItem().splitStack(1);
								}
								else if(player.getCurrentEquippedItem().getItem() == Items.lead)
								 {
									 event.setCanceled(true); 
								 }
								else if(player.getCurrentEquippedItem().getItem() == ItemMod.tuteur)
								 {
									 if(event.world.getBlock(event.x, event.y, event.z) == Blocks.farmland)
									 {
										 event.world.setBlock(event.x, event.y, event.z, BlockMod.FarmLand, event.world.getBlockMetadata(event.x, event.y, event.z),2);
										 player.getCurrentEquippedItem().splitStack(1);
									 }
								 }else if(player.getCurrentEquippedItem().getItem() == Items.water_bucket || player.getCurrentEquippedItem().getItem() == Items.lava_bucket)
								 {
									 if(event.face == 2)
									 {
										 Stage stage3 = RegisterStage.getStageAtXY((int) event.world.getChunkFromBlockCoords(event.x, event.z-1).xPosition, (int) event.world.getChunkFromBlockCoords(event.x, event.z-1).zPosition, event.world);
										 if(!this.CanDo("interact",stage3, player, event.y))
										 {
											 event.setCanceled(true);
										 }
									 }	else if(event.face == 3)
									 {
										 Stage stage3 = RegisterStage.getStageAtXY((int) event.world.getChunkFromBlockCoords(event.x, event.z+1).xPosition, (int) event.world.getChunkFromBlockCoords(event.x, event.z+1).zPosition, event.world);
										 if(!this.CanDo("interact",stage3, player, event.y))
										 {
											 event.setCanceled(true);
										 }
									 }	else if(event.face == 4)
									 {
										 Stage stage3 = RegisterStage.getStageAtXY((int) event.world.getChunkFromBlockCoords(event.x - 1, event.z).xPosition, (int) event.world.getChunkFromBlockCoords(event.x -1 , event.z-1).zPosition, event.world);
										 if(!this.CanDo("interact",stage3, player, event.y))
										 {
											 event.setCanceled(true);
										 }
									 }	
									 if(event.face == 5)
									 {
										 Stage stage3 = RegisterStage.getStageAtXY((int) event.world.getChunkFromBlockCoords(event.x+1, event.z).xPosition, (int) event.world.getChunkFromBlockCoords(event.x+1, event.z).zPosition, event.world);
										 if(!this.CanDo("interact",stage3, player, event.y))
										 {
											 event.setCanceled(true);
										 }
									 }	
									 
								 }
							 }else
							 {
								 
							 }
			 }
		 }
	 
	 
	 
	

	 
	 @SubscribeEvent
	 public void UseHoe(UseHoeEvent event)
	 {
		 if(!event.world.isRemote)
		 {
			 EntityPlayer player = (EntityPlayer) event.entityPlayer;
			 Stage stage2 = RegisterStage.getStageAtXY(event.world.getChunkFromBlockCoords(event.x, event.z).xPosition, event.world.getChunkFromBlockCoords(event.x, event.z).zPosition, event.world);
			 if(stage2 == null && event.world.provider.dimensionId == 201)
			 {
				 return;
			 }
			 if(!this.CanDo("harvest",stage2, player, event.y))
			 {
				 event.setCanceled(true);
			 }
		 }
	 }
	 
	 @SubscribeEvent
	 public void BlockEvent(BreakEvent event)
	 {
		 if(!event.world.isRemote)
		 {
			 EntityPlayer player = (EntityPlayer) event.getPlayer();
			 Stage stage2 = RegisterStage.getStageAtXY(event.world.getChunkFromBlockCoords(event.x, event.z).xPosition, event.world.getChunkFromBlockCoords(event.x, event.z).zPosition, event.world);
			 if(stage2 == null && event.world.provider.dimensionId == 201)
			 {
				 return;
			 }
			 if(stage2 != null)
			 {
				 
				 if(stage2.getIdStage()==7)
				 {
					
					 if(event.y < 102 && event.y > 72)
					 {
					
						 return;
					 }
				 }
			 }
			 
			if(!this.CanDo("block",stage2, player, event.y) )
			 {
				 	
					 event.setCanceled(true);
					 if(stage2 != null && stage2.getIdStage() == 3 &&  stage2.IsUse())
					 {
				
						 if(event.world.getBlock(event.x, event.y, event.z) instanceof fr.neocraft.main.Blocks.BlockOre || event.world.getBlock(event.x, event.y, event.z) instanceof net.minecraft.block.BlockOre ||event.world.getBlock(event.x, event.y, event.z) instanceof net.minecraft.block.BlockRedstoneOre)
						 {
						
							 int x = event.x;
							 int y = event.y;
							 int z = event.z;
							 if(event.world.getBlock(event.x, event.y+1, event.z) == Blocks.air)
							 {
								 y++;
							 }else if(event.world.getBlock(event.x, event.y, event.z+1) == Blocks.air)
							 {
									 z++;
							 }
							 else if(event.world.getBlock(event.x+1, event.y, event.z) == Blocks.air)
							 {
								 x++;
							 }
							 else if(event.world.getBlock(event.x, event.y-1, event.z) == Blocks.air)
							 {
								 y--;
							 }
							 else if(event.world.getBlock(event.x-1, event.y, event.z) == Blocks.air)
							 {
								 x--;
							 }
							 else if(event.world.getBlock(event.x, event.y, event.z-1) == Blocks.air)
							 {
								 z--;
							 }
							 int i1 = EnchantmentHelper.getFortuneModifier(event.getPlayer());
							this.dropBlockAsItemWithChance(event.world, x,y,z, event.x, event.y, event.z, event.blockMetadata, 1.0F, i1);
							event.world.setBlock(event.x, event.y, event.z, Blocks.cobblestone);
						 }
					
					
				 }

			 }
			 else {
				 PlayerStats stat = PlayerStats.get(event.getPlayer());
				 stat.QuestEvent("break_"+event.block.getUnlocalizedName().replace("tile.", "").replace(".name", ""), event.getPlayer(), 1);
				 if(event.world.getBlock(event.x, event.y, event.z) == BlockMod.Laitue_Culture)
				 {
					 ((BlockSeedLaitue) event.world.getBlock(event.x, event.y, event.z)).dropBlockAsItemWithChance(event.world, event.x, event.y, event.z, event.blockMetadata, 0F, 0);
				 } else if(event.world.getBlock(event.x, event.y, event.z) == BlockMod.Radis_Culture)
				 {
					 ((BlockSeedRadis) event.world.getBlock(event.x, event.y, event.z)).dropBlockAsItemWithChance(event.world, event.x, event.y, event.z, event.blockMetadata, 0F, 0);
				 } else if(event.world.getBlock(event.x, event.y, event.z) == BlockMod.Tomate_Culture)
				 {
					 ((BlockSeedTomate) event.world.getBlock(event.x, event.y, event.z)).dropBlockAsItemWithChance(event.world, event.x, event.y, event.z, event.blockMetadata, 0F, 0);
				 } else  if(event.world.getBlock(event.x, event.y, event.z) instanceof fr.neocraft.main.Blocks.BlockOre || event.world.getBlock(event.x, event.y, event.z) instanceof net.minecraft.block.BlockOre ||event.world.getBlock(event.x, event.y, event.z) instanceof net.minecraft.block.BlockRedstoneOre)
				 {
					 if(stage2 != null && stage2.getIdStage() != 7)
					 {
						 event.setCanceled(true);
					
						 int x = event.x;
						 int y = event.y;
						 int z = event.z;
						 if(event.world.getBlock(event.x, event.y+1, event.z) == Blocks.air)
						 {
							 y++;
						 }else if(event.world.getBlock(event.x, event.y, event.z+1) == Blocks.air)
						 {
								 z++;
						 }
						 else if(event.world.getBlock(event.x+1, event.y, event.z) == Blocks.air)
						 {
							 x++;
						 }
						 else if(event.world.getBlock(event.x, event.y-1, event.z) == Blocks.air)
						 {
							 y--;
						 }
						 else if(event.world.getBlock(event.x-1, event.y, event.z) == Blocks.air)
						 {
							 x--;
						 }
						 else if(event.world.getBlock(event.x, event.y, event.z-1) == Blocks.air)
						 {
							 z--;
						 }
						 int i1 = EnchantmentHelper.getFortuneModifier(event.getPlayer());
						 dropXpOnBlockBreak(event.world, x,y,z,event.world.getBlock(event.x, event.y, event.z).getExpDrop(event.world, event.blockMetadata, i1));
						this.dropBlockAsItemWithChance(event.world, x,y,z, event.x, event.y, event.z, event.blockMetadata, 1.0F, i1);
						event.world.setBlock(event.x, event.y, event.z, BlockMod.FakeCobble);
						
					 }
				 }
			 }
	 	 }
	}

	 public void dropXpOnBlockBreak(World p_149657_1_, int p_149657_2_, int p_149657_3_, int p_149657_4_, int p_149657_5_)
	    {
	        if (!p_149657_1_.isRemote)
	        {
	            while (p_149657_5_ > 0)
	            {
	                int i1 = EntityXPOrb.getXPSplit(p_149657_5_);
	                p_149657_5_ -= i1;
	                p_149657_1_.spawnEntityInWorld(new EntityXPOrb(p_149657_1_, (double)p_149657_2_ + 0.5D, (double)p_149657_3_ + 0.5D, (double)p_149657_4_ + 0.5D, i1));
	            }
	        }
	    }
	 public void dropBlockAsItemWithChance(World p_149690_1_, int p_149690_2_, int p_149690_3_, int p_149690_4_ , int p_149690_2_2, int p_149690_3_2, int p_149690_4_2, int p_149690_5_, float p_149690_6_, int p_149690_7_)
	    {
	        if (!p_149690_1_.isRemote && !p_149690_1_.restoringBlockSnapshots) // do not drop items while restoring blockstates, prevents item dupe
	        {
	            ArrayList<ItemStack> items = p_149690_1_.getBlock(p_149690_2_2, p_149690_3_2,p_149690_4_2).getDrops(p_149690_1_, p_149690_2_2, p_149690_3_2,p_149690_4_2, p_149690_5_, p_149690_7_);
	            for (ItemStack item : items)
	            {
	                if (p_149690_1_.rand.nextFloat() <= p_149690_6_)
	                {
	                    this.dropBlockAsItem(p_149690_1_, p_149690_2_, p_149690_3_, p_149690_4_, item);
	                }
	            }
	        }
	    }
	 
	
	 @SubscribeEvent
	 public void BlockEvent2(PlaceEvent event)
	 {
		 if(!event.world.isRemote)
		 {

			 EntityPlayer player = (EntityPlayer) event.player;
			 String name = player.getCommandSenderName();
			 Stage stage2 = RegisterStage.getStageAtXY(event.world.getChunkFromBlockCoords(event.x, event.z).xPosition, event.world.getChunkFromBlockCoords(event.x, event.z).zPosition, event.world);
			 if(stage2 == null && event.world.provider.dimensionId == 201)
			 {
				 return;
			 }
			 if(stage2 != null)
			 {
				 
				 if(stage2.getIdStage()==7)
				 {
					
					 if(event.y < 102 && event.y > 72)
					 {
					
						 return;
					 }
				 }
			 }
			 if(!this.CanDo("block",stage2, player, event.y))
			 {
				 event.setCanceled(true);
			 }else {
				 PlayerStats stat = PlayerStats.get(event.player);
				 stat.QuestEvent("place_"+event.block.getUnlocalizedName().replace("tile.", "").replace(".name", ""), event.player, 1);
			 }
		 }
	 }
	 


	 @SubscribeEvent
	 public void Explosion(ExplosionEvent.Start event)
	 {
		 event.setCanceled(true);
	 }
	 
	 
	 public boolean CanDo(String perm, Stage stage, EntityPlayer player, int ybase)
	 {
		 PlayerStats stat = PlayerStats.get(player);
		 if(!stat.AdminStage)
		 {
		
			
			 if(stage == null)
			 {
				 return false;
			 }
			 
			
				 if(stage.getIdStage()==7)
				 {
					
					 if(ybase < 102 && ybase > 72)
					 {
					
						 return true;
					 }
				 }
			
			 
			 String name = player.getCommandSenderName();
				 if(!stage.isOwner(name))
				 {
					if(stage.isAlly(name))
					{
						if(!stage.getPermAllie().contains(perm))
						{
							return false;
						}
					}
					else if(stage.isSubOwner(name))
					{
						if(!stage.getPermMember().contains(perm))
						{
							return false;
						}

					}
					else
					{
						if(!stage.getPermAll().contains(perm))
						{
							return false;
						}
					}
				 }
		 }
		
		return true;
		 
	 }
/*	 public boolean CanDo(String perm, Stage stage, Stage stage2, EntityPlayer player)
	 {
		 PlayerStats stat = PlayerStats.get(player);
		 if(!stat.AdminStage)
		 {
			 if(player.posY > 256)
			 {
				return false; 
			 }
			 if(stage == null && stage2 == null &&  player.worldObj.provider.dimensionId == 201)
			 {
				 return true;
			 }
			 if(stage == null || stage2 == null)
			 {
				 return false;
			 }

			 	String name = player.getCommandSenderName();
				 if(!stage.isOwner(name) || !stage.isOwner(name) )
				 {
					if(!stage.isSubOwner(name) ||!stage2.isSubOwner(name))
					{
						 
					}
					 else if(!stage.isSubOwner(name) ||!stage2.isSubOwner(name))
					{
						if(!RegisterStage.ExistInStringArray(stage.getPermAll(), perm) || !RegisterStage.ExistInStringArray(stage2.getPermAll(), perm))
						{
							return false;
						}
					}
					else
					{
						if(!RegisterStage.ExistInStringArray(stage.getPermMember(), perm) || !RegisterStage.ExistInStringArray(stage2.getPermMember(), perm))
						{
							return false;
						}
					}
				 }
		 }
		
		return true;
		 
	 } */

}

