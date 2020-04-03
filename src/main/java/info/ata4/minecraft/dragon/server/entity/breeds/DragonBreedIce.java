/*
 ** 2013 October 24
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package info.ata4.minecraft.dragon.server.entity.breeds;

import info.ata4.minecraft.dragon.server.entity.EntityTameableDragon;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonBreedIce extends DragonBreed {
    
    public DragonBreedIce() {
        super("ice", "ice", 0x6fc3ff);
        
        addImmunity(DamageSource.magic);
        
        addHabitatBlock(Blocks.snow);
        addHabitatBlock(Blocks.snow_layer);
        addHabitatBlock(Blocks.ice);
        
        addHabitatBiome(BiomeGenBase.frozenOcean);
        addHabitatBiome(BiomeGenBase.frozenRiver);
        addHabitatBiome(BiomeGenBase.iceMountains);
        addHabitatBiome(BiomeGenBase.icePlains);
    }

    @Override
    public int getId()
    {
    	return 3;
    }
}
