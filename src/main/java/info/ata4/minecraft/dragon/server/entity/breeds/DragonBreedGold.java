package info.ata4.minecraft.dragon.server.entity.breeds;

import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeGenBase;

public class DragonBreedGold extends DragonBreed {

    public DragonBreedGold() {
        super("gold", "gold", 0xab39be);
        
        addImmunity(DamageSource.magic);
        
        addHabitatBlock(Blocks.gold_block);
        addHabitatBiome(BiomeGenBase.sky);
    }
    @Override
    public int getId()
    {
    	return 9;
    }
}

