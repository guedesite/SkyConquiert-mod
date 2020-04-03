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
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class DragonBreedGhost extends DragonBreed {

    public DragonBreedGhost() {
        super("ghost", "undead", 0xbebebe);
        
        addImmunity(DamageSource.wither);
        
        addHabitatBlock(Blocks.web);
    }
    @Override
    public int getId()
    {
    	return 4;
    }
    
    

    
//    @Override
//    public void onUpdate(EntityTameableDragon dragon) {
//        // start burning when in contact with sunlight
//        if (dragon.worldObj.isDaytime() && dragon.isServer()) {
//            float brightness = dragon.getBrightness(1);
//            float threshold = (brightness - 0.4f) * 2;
//            int bx = MathHelper.floor_double(dragon.posX);
//            int by = MathHelper.floor_double(dragon.posY);
//            int bz = MathHelper.floor_double(dragon.posZ);
//
//            if (brightness > 0.5f && dragon.worldObj.rand.nextFloat() * 30 < threshold && dragon.worldObj.canBlockSeeTheSky(bx, by, bz)) {
//                dragon.setFire(8);
//            }
//        }
//    }



    @Override
    public String getLivingSound(EntityTameableDragon dragon) {
        return "mob.skeleton.say";
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }
}
