package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import info.ata4.minecraft.dragon.server.entity.breeds.DragonBreed;
import info.ata4.minecraft.dragon.server.entity.helper.DragonBreedRegistry;
import info.ata4.minecraft.dragon.server.entity.helper.DragonLifeStage;
import net.minecraft.entity.player.EntityPlayer;

public class ServerDragon extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	int id;
	String DragStage[] = new String[] {"EGG","HATCHLING","JUVENILE","ADULT","ADULT"};
	
	public ServerDragon(int i)
	{
		id = i;
	}
	
	@SideOnly(Side.SERVER)
	@Override
	public void A(EntityPlayer player) {
		if(player != null)
		{
			PlayerStats stat = PlayerStats.get(player);
			switch(id)
			{
				case 0:
					if (player.ridingEntity != null && player.ridingEntity instanceof EntityTameableDragon) 
			        {
						stat.QuestEvent("touche_2", player, 1);
						((EntityTameableDragon) player.ridingEntity).setDead();
			        }else if (player.ridingEntity == null) {
			        	
			        	
			        		 if(0 >= stat.cooldowndrag && stat.isInAttackWithMessage(player))
			        		 {
			        			 stat.cooldowndrag = 60;
				        		 stat.QuestEvent("touche_2", player, 1);
				        		
				        		 DragonBreed breed2 = DragonBreedRegistry.getInstance().getBreedById(stat.DragBreed);
								 EntityTameableDragon dragon = new EntityTameableDragon(player.worldObj, stat);
								 dragon.setPositionAndRotation(player.posX + 0.5, player.posY + 0.5, player.posZ + 0.5, player.rotationYaw, player.rotationPitch);
							   	 dragon.getReproductionHelper().setBreederName(player.getCommandSenderName());
							     dragon.getLifeStageHelper().setLifeStage(DragonLifeStage.valueOf(DragStage[stat.lvlGuiDrag[0]]));
							   	 dragon.setBreed(breed2);
							   	 dragon.setSaddled(true);
							   	
							   	 player.worldObj.spawnEntityInWorld(dragon);
							   	 dragon.setRidingStat(20);
							   	 dragon.setRidingPlayer(player);
							   	 dragon.tamedFor(player, true);
						
			        		 }
			        		 else
			        		 {
			        			 SoundManager.PlaySound(EnumSound.NeoMNope.getSound(), player);
			        		 }
			        } else {
			        	SoundManager.PlaySound(EnumSound.NeoMNope.getSound(), player);
			        }
					break;
				case 1:
					if (player.ridingEntity != null && player.ridingEntity instanceof EntityTameableDragon) 
			        {
						EntityTameableDragon dr = ((EntityTameableDragon) player.ridingEntity);
						if(dr.canfireball)
						{
							dr.BouBoule();
						}
						else {
							SoundManager.PlaySound(EnumSound.NeoMNope.getSound(), player);
						}
			        }
					break;
				case 2:
						if(stat.MyEntityPet == null)
						{
							stat.updatePet(player, true);
						}
						else {
							stat.MyEntityPet.setDead();
							stat.MyEntityPet = null;
						}
					
			}
		}
		
	}

}
