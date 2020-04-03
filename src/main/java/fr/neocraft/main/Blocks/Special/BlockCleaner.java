package fr.neocraft.main.Blocks.Special;

import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Blocks.BlockBasic;
import fr.neocraft.main.Proxy.Serveur.TileEntity.CleanerTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCleaner extends BlockBasic{

	public BlockCleaner(Material p_i45394_1_, int level) {
		super(p_i45394_1_, level);

	}
	
	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true; // signale que le bloc a une entité
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new CleanerTileEntity(); // indique quelle est l'entité de bloc
    }
    
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_)
    {
     
        int l;
        float f;
        float f1;
        float f2;

        if (!World.doesBlockHaveSolidTopSurface(p_149734_1_, p_149734_2_, p_149734_3_ - 1, p_149734_4_) && !Blocks.fire.canCatchFire(p_149734_1_, p_149734_2_, p_149734_3_ - 1, p_149734_4_, UP))
        {
      
            for (l = 0; l < 3; ++l)
            {
                f = (float)p_149734_2_ + p_149734_5_.nextFloat();
                f1 = (float)p_149734_3_ + p_149734_5_.nextFloat() * 0.5F + 0.5F;
                f2 = (float)p_149734_4_ + p_149734_5_.nextFloat();
                p_149734_1_.spawnParticle("largesmoke", (double)f, (double)f1, (double)f2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
}
