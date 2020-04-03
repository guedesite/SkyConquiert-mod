package fr.neocraft.main.Proxy.Client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.RenderMod;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.DragFireRender;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.MoneyOrbRender;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.RenderSpecialShopper;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.BoxUltimeTileEntitySpecialRenderer;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.BoxVoteTileEntitySpecialRenderer;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.DivinTotemRender;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.DivinTotemTileEntityRenderer;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.RenderFarmLand;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.RenderSpecialBoxUltime;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.RenderSpecialBoxVote;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.RenderSpike;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.ShopperTileEntitySpecialRenderer;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity.TileEntityFarmLandSpecial;
import fr.neocraft.main.Proxy.Serveur.CommonProxy;
import fr.neocraft.main.Proxy.Serveur.Entity.DragFire;
import fr.neocraft.main.Proxy.Serveur.Entity.MoneyOrb;
import fr.neocraft.main.Proxy.Serveur.Stage.Pet.PetRegister;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeTileNull;
import fr.neocraft.main.Proxy.Serveur.TileEntity.DivinTotemTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.TileEntityFarmLand;
import fr.neocraft.main.Proxy.Serveur.TileEntity.TileEntityNull;
import info.ata4.minecraft.dragon.client.handler.DragonControl;
import info.ata4.minecraft.dragon.client.handler.DragonEntityWatcher;
import info.ata4.minecraft.dragon.client.render.DragonRenderer;
import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;

public class ClientProxy extends CommonProxy
{
	public static int spikeRenderID,renderFarmland,renderShopper,renderShopperEvil,renderBoxVote,renderBoxUltime, renderDivinTotem;
	
	@Override
	public void registerRenders(FMLInitializationEvent event){

		RenderMod.init();
		
		RenderingRegistry.registerEntityRenderingHandler(DragFire.class, new DragFireRender());
		
		RenderingRegistry.registerEntityRenderingHandler(MoneyOrb.class, new MoneyOrbRender());
		
		
	
		spikeRenderID = RenderingRegistry.getNextAvailableRenderId();
	    RenderingRegistry.registerBlockHandler(spikeRenderID, new RenderSpike()); 
	    
	    ClientRegistry.bindTileEntitySpecialRenderer(ShopperTileEntity.class, new ShopperTileEntitySpecialRenderer()); 
	    renderShopper = RenderingRegistry.getNextAvailableRenderId();
	    RenderingRegistry.registerBlockHandler(new RenderSpecialShopper());
	    
	    renderBoxVote = RenderingRegistry.getNextAvailableRenderId();
	    RenderingRegistry.registerBlockHandler(new RenderSpecialBoxVote());
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNull.class, new BoxVoteTileEntitySpecialRenderer()); 
	    
	    renderBoxUltime = RenderingRegistry.getNextAvailableRenderId();
	    RenderingRegistry.registerBlockHandler(new RenderSpecialBoxUltime());
	    ClientRegistry.bindTileEntitySpecialRenderer(BoxUltimeTileNull.class, new BoxUltimeTileEntitySpecialRenderer()); 

	    
	    ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFarmLand.class, new TileEntityFarmLandSpecial());
	    renderFarmland = RenderingRegistry.getNextAvailableRenderId();
	    RenderingRegistry.registerBlockHandler(new RenderFarmLand());

	    ClientRegistry.bindTileEntitySpecialRenderer(DivinTotemTileEntity.class, new DivinTotemTileEntityRenderer());
	    renderDivinTotem = RenderingRegistry.getNextAvailableRenderId();
	    RenderingRegistry.registerBlockHandler(new DivinTotemRender());
	    
	}
	@Override
    public void onInit(FMLInitializationEvent evt) {
        super.onInit(evt);

        RenderingRegistry.registerEntityRenderingHandler(EntityTameableDragon.class, new DragonRenderer());

        FMLCommonHandler.instance().bus().register(new DragonEntityWatcher());
        FMLCommonHandler.instance().bus().register(new DragonControl(getNetwork(), main.getNetWorkServer()));
        
        PetRegister.registerClient();
		PetRegister.registerServer();
  
    }
}
