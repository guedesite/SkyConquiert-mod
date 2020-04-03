package fr.neocraft.main.Init;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.ModelAviator;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.ModelRat;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.ModelShopperPnj;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.RenderAviator;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.RenderGardien;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.RenderGardienEvil;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.RenderPet;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.RenderRat;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.RenderShopperPnj;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.RenderShopperPnjEvil;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityAviator;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardien;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardienEvil;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityPet;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityRat;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnj;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnjEvil;

public class RenderMod
{
    public static void init()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityShopperPnj.class, new RenderShopperPnj(new ModelShopperPnj(), 1));
        
        RenderingRegistry.registerEntityRenderingHandler(EntityGardien.class, new RenderGardien());
        RenderingRegistry.registerEntityRenderingHandler(EntityAviator.class, new RenderAviator(new ModelAviator(), 1));
        RenderingRegistry.registerEntityRenderingHandler(EntityRat.class, new RenderRat(new ModelRat(), 1));
        RenderingRegistry.registerEntityRenderingHandler(EntityPet.class, new RenderPet());
        RenderingRegistry.registerEntityRenderingHandler(EntityGardienEvil.class, new RenderGardienEvil());
        
        RenderingRegistry.registerEntityRenderingHandler(EntityShopperPnjEvil.class, new RenderShopperPnjEvil());
    }
}