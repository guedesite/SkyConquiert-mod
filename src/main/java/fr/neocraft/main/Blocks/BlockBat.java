package fr.neocraft.main.Blocks;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class BlockBat extends Block
{
	private EntityBat[] entity;
	
    public BlockBat(Material p_i45394_1_) {
		super(p_i45394_1_);
		this.setTickRandomly(true);
	}
	@Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
        public float getAmbientOcclusionLightValue()
        {
            return 1.0F;
        }
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
    {
        return null;
    }
    private static Timer timer = new Timer();
    @Override
	public void updateTick(final World w, final int x,final int y, final int z, Random p_149674_5_)
    {
		if(!w.isRemote & !w.isDaytime())
		{
			if(entity == null)
			{
				entity = new EntityBat[1];
				entity[0] = new EntityBat(w);
				entity[0].setPosition(x+0.5, y+0.5,z+0.5);
				w.spawnEntityInWorld(entity[0]);
			}if(entity.length != 25){
				for(int i =0; i < 5; i++)
				{
					timer.schedule(new TimerTask() {
						  @Override
						  public void run() {
	
							EntityBat[] tempentity = new EntityBat[entity.length + 1];
							int i =0;
							for(EntityBat e:entity)
							{
								tempentity[i] = e;
								i++;
							}
							tempentity[i] = new EntityBat(w);
							tempentity[i].setPosition(x+0.5, y+0.5,z+0.5);
							w.spawnEntityInWorld(tempentity[i]);
							entity = tempentity;
						  }
					}, 1000);
				}
			}
		} else if(!w.isRemote & w.isDaytime())
		{
			if(entity != null)
			{
				for(EntityBat e:entity)
				{
					if(e != null)
					{
						e.setDead();
					}
				}
				entity=null;
			}
		}
    }
    
    
  
}