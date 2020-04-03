package fr.neocraft.main.Blocks.Special;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.BlockBasic;
import fr.neocraft.main.Blocks.Special.BlockFuturist.Data;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockFuturistTransmetter extends BlockFuturist{

	@SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
		this.blockIcon = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistTransmetter");  
   
    }
 
 @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
	
	 return this.blockIcon;
	 
    }
    
	public BlockFuturistTransmetter (Material p_i45394_1_, int level) {
		super(p_i45394_1_, level);
	}
	
	@Override
	public void RecieveData(World w, int x, int y, int z, int lastx, int lasty, int lastz, Data type, int nbreq) {
		if(nbreq < 15)
		{
			SendData( w,x, y, z, lastx, lasty,lastz,  type,nbreq);
		}
	}
	
	
	
}
