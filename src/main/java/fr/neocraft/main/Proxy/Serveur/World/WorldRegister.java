package fr.neocraft.main.Proxy.Serveur.World;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;

public class WorldRegister
{
    public static void Register()
    {
        registerWorldGen(new WorldGenNeo(), 0);
    }
    
    public static void registerWorldGen(IWorldGenerator iGenerator, int modGenerationWeight)
    {
        GameRegistry.registerWorldGenerator(iGenerator, modGenerationWeight);
    }
}