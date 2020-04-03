package fr.neocraft.main.Proxy.Serveur.Handler;

import java.awt.Color;

import cpw.mods.fml.common.registry.EntityRegistry;
import fr.neocraft.main.main;

public class NeoEntityHandler
{
    public static void registerAmbiants(Class entityClass, String string)
    {
        int entiyID = EntityRegistry.findGlobalUniqueEntityId();
        
      EntityRegistry.registerGlobalEntityID(entityClass, string, entiyID, new Color(0, 221, 243).getRGB(), new Color(243, 0, 0).getRGB());
        EntityRegistry.registerModEntity(entityClass, string,  entiyID, main.instance, 64, 1, true);
    }
    
    public static void registerMonster(Class entityClass, String string)
    {
        int entiyID = EntityRegistry.findGlobalUniqueEntityId();
        
      EntityRegistry.registerGlobalEntityID(entityClass, string, entiyID, new Color(0, 221, 243).getRGB(), new Color(243, 0, 0).getRGB());
        EntityRegistry.registerModEntity(entityClass, string, entiyID, main.instance, 64, 1, true);
}
    
}