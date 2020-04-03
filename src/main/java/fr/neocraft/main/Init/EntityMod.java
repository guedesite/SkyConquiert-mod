	package fr.neocraft.main.Init;

import fr.neocraft.main.Proxy.Serveur.Entity.EntityAviator;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardien;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardienEvil;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityPet;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityRat;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnj;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnjEvil;
import fr.neocraft.main.Proxy.Serveur.Handler.NeoEntityHandler;

public class EntityMod
{
    public static void init()
    {	
        NeoEntityHandler.registerAmbiants(EntityShopperPnj.class, "ShopperPnj");
        NeoEntityHandler.registerMonster(EntityShopperPnjEvil.class, "ShopperPnjEvil");
        NeoEntityHandler.registerMonster(EntityGardien.class, "Gardien");
        NeoEntityHandler.registerMonster(EntityAviator.class, "Aviator");
        NeoEntityHandler.registerMonster(EntityRat.class, "NeoRat");
		 NeoEntityHandler.registerAmbiants(EntityPet.class, "NeoPet");
		 NeoEntityHandler.registerMonster(EntityGardienEvil.class, "GardienEvil");
      }
}