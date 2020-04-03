/*
 ** 2013 October 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package info.ata4.minecraft.dragon.client.handler;

import java.util.BitSet;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiShopNavigator;
import fr.neocraft.main.Proxy.Client.event.TextDraw;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkDragon;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerContainer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerDragon;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

/**
 * Client side event handler for dragon control messages.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonControl {
    
    public static final String KEY_CATEGORY = "key.categories.dragonmount";
    public static final String KEY_CATEGORY_QUEST = "key.categories.quest";
    public static final KeyBinding KEY_FLY_UP = new KeyBinding("key.dragon.flyUp", Keyboard.KEY_R, KEY_CATEGORY);
    public static final KeyBinding KEY_FLY_DOWN = new KeyBinding("key.dragon.flyDown", Keyboard.KEY_F, KEY_CATEGORY);
    public static final KeyBinding KEY_SPAWN = new KeyBinding("key.dragon.spawn", Keyboard.KEY_P, KEY_CATEGORY);
    public static final KeyBinding KEY_FIRE = new KeyBinding("key.dragon.fire", Keyboard.KEY_X, KEY_CATEGORY);
    public static final KeyBinding KEY_GUI = new KeyBinding("key.dragon.gui", Keyboard.KEY_O, KEY_CATEGORY);
    public static final KeyBinding KEY_QUEST = new KeyBinding("key.quest.hideshow", Keyboard.KEY_I, KEY_CATEGORY_QUEST);
 //   public static final KeyBinding KEY_MAP = new KeyBinding("key.map", Keyboard.KEY_U, KEY_CATEGORY_QUEST);
    public static final KeyBinding KEY_HDV = new KeyBinding("key.hdv", Keyboard.KEY_Y, KEY_CATEGORY_QUEST);
    public static final KeyBinding KEY_PET = new KeyBinding("key.Pet", Keyboard.KEY_U, KEY_CATEGORY_QUEST);
    private final SimpleNetworkWrapper network, network2;
    
    Minecraft nc = Minecraft.getMinecraft();
    private final NetWorkDragon dcm = new NetWorkDragon();
    
    public DragonControl(SimpleNetworkWrapper network, SimpleNetworkWrapper neonet) {
        this.network = network;
        this.network2 = neonet;
        
        ClientRegistry.registerKeyBinding(KEY_FLY_UP);
       
        ClientRegistry.registerKeyBinding(KEY_FLY_DOWN);
      	
        ClientRegistry.registerKeyBinding(KEY_SPAWN);
        
        ClientRegistry.registerKeyBinding(KEY_FIRE);
      
        ClientRegistry.registerKeyBinding(KEY_GUI);
        ClientRegistry.registerKeyBinding(KEY_QUEST);
       // ClientRegistry.registerKeyBinding(KEY_MAP);
        ClientRegistry.registerKeyBinding(KEY_HDV);
        ClientRegistry.registerKeyBinding(KEY_PET);
    }
    
    @SubscribeEvent
    public void onTick(ClientTickEvent evt) {
    	
    	
    	
    	BitSet flags = dcm.getFlags();
        flags.set(0, KEY_FLY_UP.getIsKeyPressed());
        flags.set(1, KEY_FLY_DOWN.getIsKeyPressed());
        
        // send message to server if it has changed
        if (dcm.hasChanged()) {
            network.sendToServer(dcm);
        }
     
        if(KEY_SPAWN.isPressed())
        {
        	network2.sendToServer(new NetWorkServer(new ServerDragon(0)));
        }
        if(KEY_PET.isPressed())
        {
        	network2.sendToServer(new NetWorkServer(new ServerDragon(2)));
        }
        if(KEY_FIRE.isPressed())
        {
        	network2.sendToServer(new NetWorkServer(new ServerDragon(1)));
        	/*if(nc.thePlayer.ridingEntity != null && nc.thePlayer.ridingEntity instanceof EntityTameableDragon)
        	{
        		((EntityTameableDragon)nc.thePlayer.ridingEntity).BouBoule();
        	} */
        }
        
        if(KEY_QUEST.isPressed())
        {
        	if(TextDraw.Questbool == false)
        	{
        		TextDraw.Questbool = true;
        	}else {
        		TextDraw.Questbool =false;
        	}
        }
        if(KEY_HDV.isPressed())
        {
        	Minecraft.getMinecraft().displayGuiScreen(new GuiShopNavigator());
        }
        if(KEY_GUI.isPressed())
        {
        	network2.sendToServer(new NetWorkServer(new ServerContainer("DRAGUI")));
        }       
     /*   if(KEY_MAP.isPressed())
        {
        	network2.sendToServer(new NetWorkServer(new ServerContainer("MAP")));
        } */
      
    }
}
