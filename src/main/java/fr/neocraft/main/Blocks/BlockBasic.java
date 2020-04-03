package fr.neocraft.main.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Blocks.Special.BlockFuturist.Data;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class BlockBasic extends Block
{

    public BlockBasic(Material p_i45394_1_, int level)
    {
        super(p_i45394_1_);
        this.setHarvestLevel("pickaxe", level);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
        public float getAmbientOcclusionLightValue()
        {
            return 1.0F;
        }

	

}
