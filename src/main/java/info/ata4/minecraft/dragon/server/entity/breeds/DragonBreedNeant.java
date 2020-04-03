package info.ata4.minecraft.dragon.server.entity.breeds;

import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;

public class DragonBreedNeant extends DragonBreed {

    public DragonBreedNeant() {
        super("neant", "neant", 0xab39be);
        
        addImmunity(DamageSource.magic);
        
        addHabitatBlock(Blocks.end_stone);
        addHabitatBlock(Blocks.obsidian);
        addHabitatBiome(BiomeGenBase.sky);
    }
    @Override
    public int getId()
    {
    	return 8;
    }
}
