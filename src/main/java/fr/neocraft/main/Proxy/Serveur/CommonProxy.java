package fr.neocraft.main.Proxy.Serveur;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Entity.DragFire;
import fr.neocraft.main.Proxy.Serveur.Entity.MoneyOrb;
import fr.neocraft.main.Proxy.Serveur.Stage.Pet.PetRegister;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkDragon;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkDragonHandler;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import info.ata4.minecraft.dragon.server.handler.DragonEggBlockHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy
{

   private SimpleNetworkWrapper network;  
    
    public SimpleNetworkWrapper getNetwork() {
        return network;
    } 
    
    public void onInit(FMLInitializationEvent evt) {
        registerEntities();

        
        MinecraftForge.EVENT_BUS.register(new DragonEggBlockHandler());

        network = NetworkRegistry.INSTANCE.newSimpleChannel("NetWorkDragon");
        network.registerMessage(NetWorkDragonHandler.class, NetWorkDragon.class, 0, Side.SERVER); 
        
        
    }

    
    public void onServerStopped(FMLServerStoppedEvent evt) {
    }
    
    private void registerEntities() {

          EntityRegistry.registerModEntity(MoneyOrb.class, "MoneyOrb", EntityRegistry.findGlobalUniqueEntityId(), main.instance, 10, 20, false); 
          
          EntityRegistry.registerModEntity(DragFire.class, "neoDragFire", EntityRegistry.findGlobalUniqueEntityId(), main.instance, 10, 20, false); 

          
        EntityRegistry.registerGlobalEntityID(EntityTameableDragon.class, "DragonMount",
        		EntityRegistry.findGlobalUniqueEntityId(), 0, 0xcc00ff);
    }
    
    public void registerChestItems() {
        ChestGenHooks chestGenHooksDungeon = ChestGenHooks.getInfo(ChestGenHooks.DUNGEON_CHEST);
        chestGenHooksDungeon.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.dragon_egg), 1, 1, 70));
        // chance < saddle (1/16, ca. 6%, in max 8 slots -> 40% at least 1 egg, 0.48 eggs per chest): I think that's okay

        ChestGenHooks chestGenHooksMineshaft = ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR);
        chestGenHooksMineshaft.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.dragon_egg), 1, 1, 5));
        // chance == gold ingot (1/18, ca. 6%, in 3-6 slots -> 23% at least 1 egg, 0.27 eggs per chest):
        // exploring a random mine shaft in creative mode yielded 2 eggs out of about 10 chests in 1 hour

        ChestGenHooks chestGenHooksJungleChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_JUNGLE_CHEST);
        chestGenHooksJungleChest.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.dragon_egg), 1, 1, 15));
        // chance == gold ingot (15/81, ca. 18%, in 2-5 slots -> 51% at least 1 egg, 0.65 eggs per chest, 1.3 eggs per temple):
        // jungle temples are so rare, it should be rewarded

        ChestGenHooks chestGenHooksDesertChest = ChestGenHooks.getInfo(ChestGenHooks.PYRAMID_DESERT_CHEST);
        chestGenHooksDesertChest.addItem(new WeightedRandomChestContent(new ItemStack(Blocks.dragon_egg), 1, 1, 10));
        // chance == iron ingot (10/76, ca. 13%, in 2-5 slots -> 39% at least 1 egg, 0.46 eggs per chest, 1.8 eggs per temple):
        // desert temples are so rare, it should be rewarded
    }

	public void registerRenders(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}
    
}
