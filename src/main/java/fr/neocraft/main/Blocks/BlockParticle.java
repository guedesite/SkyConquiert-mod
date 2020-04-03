package fr.neocraft.main.Blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockParticle extends Block{
	
	private String effect = "smoke";
	private float fl = 6.0F;
	public BlockParticle(Material p_i45394_1_, String ef, float tf) {
		super(p_i45394_1_);
		this.effect = ef;
		this.fl = tf;
		this.setBlockName("effect " + ef);
		this.setCreativeTab(main.neocraft);
		this.setBlockTextureName(neoreference.MOD_ID + ":" + ef );
		this.setBlockUnbreakable();
		// TODO Auto-generated constructor stub
	}

	
	
	
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
    	double d0;
        double d1;
        double d2;
        double d3;
        double d4;
        double d5;
        for(int i = 0; i <= 10; i++)
        {
        	 d0 = (double)((float)p_149734_2_ + p_149734_5_.nextFloat());
             d1 = (double)((float)p_149734_3_ + fl + p_149734_5_.nextFloat());
             d2 = (double)((float)p_149734_4_ + p_149734_5_.nextFloat());
             d3 = 0.0D;
             d4 = 0.0D;
             d5 = 0.0D;
        	p_149734_1_.spawnParticle(effect, d0, d1, d2, d3, d4, d5);
        }
    
    }

}
