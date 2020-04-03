/*
 ** 2014 March 19
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package info.ata4.minecraft.dragon.server.network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.neocraft.main.main;
import io.netty.channel.ChannelHandler.Sharable;

/**
 * Network handler for dragon control messages.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
@Sharable
public class DragonControlMessageHandler implements IMessageHandler<DragonControlMessage, IMessage> {

    private static final Logger L = LogManager.getLogger();
    private static final fr.neocraft.main.bdd bdd = main.getbdd();
    public String DragStage[] = new String[] {"EGG","HATCHLING","JUVENILE","ADULT","ADULT"};
    @Override
    public IMessage onMessage(DragonControlMessage message, MessageContext ctx) {
    	System.err.println("ERROR CLASS NOT BE LOADED HANDLER DRAG");
      /*  // check if the server is messing with the protocol
        if (ctx.side == Side.CLIENT) {
            L.warn("Recieved unexpected control message from server!");
            return null;
        }

        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        World world = player.getEntityWorld();
        int PosX = player.getPlayerCoordinates().posX;
        int PosY = player.getPlayerCoordinates().posY;
        int PosZ = player.getPlayerCoordinates().posZ;
        String messageH = message.getFlags().toString();
        if(messageH.contains("4"))
        {
        	player.openGui(main.instance, 0, world, PosX, PosY, PosZ);
        	//player.addChatMessage(new ChatComponentText("neoexe:OpenGui*1*"));
        	PlayerStats stat = PlayerStats.get(player);
        	stat.QuestEvent("touche_4", player, 1);
        }
        if(messageH.contains("5"))
        {
        	int id = world.provider.dimensionId;
        //	if(id != 0 && RegisterStage.IsWorldStage(id))
        	//{
        		player.openGui(main.instance, 7, world, PosX, PosY, PosZ);
        //	}else {
        	//	player.playSound(EnumSound.MNope.getSound(), 1, 1);
      //  	}
        	PlayerStats stat = PlayerStats.get(player);
        	stat.QuestEvent("touche_5", player, 1);
        }
        else if (player.ridingEntity instanceof EntityTameableDragon) 
        {
        	 EntityTameableDragon dragon = (EntityTameableDragon) player.ridingEntity;
            if(messageH.contains("2"))
            {
            	dragon.setDead();
            	PlayerStats stat = PlayerStats.get(player);
            	stat.QuestEvent("touche_2", player, 1);
            }
            else if(messageH.contains("3") && dragon.canfireball)
            {
            	dragon.BouBoule();
            	PlayerStats stat = PlayerStats.get(player);
            	stat.QuestEvent("touche_3", player, 1);
            }
       
         //   dragon.setControlFlags(message.getFlags());

        }
        else
        {
        	 if(messageH.contains("2"))
        	 {
        		 PlayerStats stat = PlayerStats.get(player);
        		 if(stat.cooldowndrag == 0)
        		 {
        			 stat.cooldowndrag = 60;
	        		 stat.QuestEvent("touche_2", player, 1);
	        		
	        		 DragonBreed breed2 = DragonBreedRegistry.getInstance().getBreedById(stat.DragBreed);
					 EntityTameableDragon dragon = new EntityTameableDragon(world, stat);
					 dragon.setPositionAndRotation(PosX + 0.5, PosY + 0.5, PosZ + 0.5, player.rotationYaw, player.rotationPitch);
				   	 dragon.getReproductionHelper().setBreederName(player.getCommandSenderName());
				     dragon.getLifeStageHelper().setLifeStage(DragonLifeStage.valueOf(DragStage[stat.lvlGuiDrag[0]]));
				   	 dragon.setBreed(breed2);
				   	 dragon.setSaddled(true);
				   	
				   	 world.spawnEntityInWorld(dragon);
				   	 dragon.setRidingStat(20);
				   	 dragon.setRidingPlayer(player);
				   	 dragon.tamedFor(player, true);
			
        		 }
        		 else
        		 {
        			 
        		 }
        	 }
	        	
        }
        
        // receive only*/
        return null; 
    }
    
}
