package fr.neocraft.main.Proxy.Client.event;

import java.util.Random;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import fr.neocraft.main.Blocks.BlockElevateur;
import fr.neocraft.main.Blocks.Special.BlockFuturist;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Item.ItemArmorGod;
import fr.neocraft.main.Item.ItemFaux;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.Proxy.Serveur.player.teleport;
import fr.neocraft.main.ServeurManager.DailyManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import team.chisel.entity.fx.EntityHolystoneFX;
import team.chisel.init.ChiselBlocks;

public class ElevateurEvent {

	@SubscribeEvent
	public void PlayerTick(PlayerTickEvent event)
	{
	
			int x = (int)event.player.posX;
			int z = (int)event.player.posZ;
					
			if(x < 0)
			{
				x--;
			}if(z < 0)
			{
				z--;
			}
					for(int y = 1; y <= 20; y++)
					{
						Block b = event.player.worldObj.getBlock(x,(int) event.player.posY - y, z);
						if(b instanceof BlockElevateur && 2 < y )
						{
						
							if(y <= ((BlockElevateur)b).radius )
							{
								if(!event.player.isSneaking())
								{
									event.player.motionY = 0.2 * (event.player.worldObj.getBlockMetadata(x,(int) event.player.posY - y, z)+1);
								} else {
									event.player.motionY = -0.2 * (event.player.worldObj.getBlockMetadata(x,(int) event.player.posY - y, z)+1);
								}
								
							}
						} 
					}
				
			if(event.player.worldObj.isRemote)
			{
			
				double d0;
		        double d1;
		        double d2;
		        Random r = event.player.worldObj.rand;
				for(ItemStack s:event.player.inventory.armorInventory)
				{
					if(s != null)
					{
						if(s.getItem() == ItemMod.God_Boot)
						{
							if(event.player.worldObj.rand.nextInt(30) == 1)
							{
								 d0 = (double)((float)event.player.posX-0.5 + r.nextFloat());
					             d1 = (double)((float)event.player.posY-0.2 + (((float)r.nextInt(30))/100))-1;
					             d2 = (double)((float)event.player.posZ-0.5 + r.nextFloat());
					           
					        	Minecraft.getMinecraft().effectRenderer.addEffect(new EntityHolystoneFX(event.player.worldObj, ChiselBlocks.holystone, d0, d1, d2));
							}
						} else if(s.getItem() == ItemMod.God_Leggings)
						{
							if(event.player.worldObj.rand.nextInt(30) == 1)
							{
								
						
								 d0 = (double)((float)event.player.posX-0.5 + r.nextFloat());
					             d1 = (double)((float)event.player.posY-0.8+ (((float)r.nextInt(70))/100)) -0.5;
					             d2 = (double)((float)event.player.posZ-0.5 + r.nextFloat());
					           
					        	
					             Minecraft.getMinecraft().effectRenderer.addEffect(new EntityHolystoneFX(event.player.worldObj, ChiselBlocks.holystone, d0, d1, d2));
							}
						}else if(s.getItem() == ItemMod.God_ChestPlate)
						{
							if(event.player.worldObj.rand.nextInt(30) == 1)
							{
								
						
								 d0 = (double)((float)event.player.posX-0.5 + r.nextFloat());
					             d1 = (double)((float)event.player.posY-1 +0.3+ (((float)r.nextInt(50))/100));
					             d2 = (double)((float)event.player.posZ-0.5 + r.nextFloat());
					           
					        	Minecraft.getMinecraft().effectRenderer.addEffect(new EntityHolystoneFX(event.player.worldObj, ChiselBlocks.holystone, d0, d1, d2));
							}
						}else if(s.getItem() == ItemMod.God_Helmet)
						{
							if(event.player.worldObj.rand.nextInt(50) == 1)
							{
								
						
								 d0 = (double)((float)event.player.posX-0.5 + r.nextFloat());
								 d1 = (double)((float)event.player.posY-1 +0.8+ (((float)r.nextInt(50))/100));
					             d2 = (double)((float)event.player.posZ -0.5+ r.nextFloat());
					           
					        	Minecraft.getMinecraft().effectRenderer.addEffect(new EntityHolystoneFX(event.player.worldObj, ChiselBlocks.holystone, d0, d1, d2));
							}
						}
					}
				}
			}
		}

}


