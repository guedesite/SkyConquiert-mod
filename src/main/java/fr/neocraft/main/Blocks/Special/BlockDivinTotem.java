package fr.neocraft.main.Blocks.Special;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.BlockBasic;
import fr.neocraft.main.Proxy.Client.ClientProxy;
import fr.neocraft.main.Proxy.Serveur.TileEntity.DivinTotemTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDivinTotem extends BlockBasic {
	@SideOnly(Side.CLIENT)
    private IIcon top;
    
	public BlockDivinTotem(Material p_i45394_1_) {
		super(p_i45394_1_, 1);
		this.setHardness(0.6F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 0.85F, 1F);
		this.setLightOpacity(0);
		this.setStepSound(soundTypeStone);
		
	} //DivinTotem_top

	@SideOnly(Side.CLIENT)
	@Override
    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(neoreference.MOD_ID + ":DivinTotem_base");
        this.top = iiconRegister.registerIcon(neoreference.MOD_ID + ":DivinTotem_top");
    }
	@SideOnly(Side.CLIENT)
	@Override
    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {
            return this.top;
        }
        return this.blockIcon;

    }
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	@SideOnly(Side.CLIENT)
	@Override
    public boolean shouldSideBeRendered(IBlockAccess state, int x, int y, int z, int side)
    {
        return true;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
        return ClientProxy.renderDivinTotem;
    }
	@Override
    public boolean isOpaqueCube() {
        return false;
    }  
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World w, int x, int y, int z, Random p_149734_5_)
    {
		if( w.getTileEntity(x,y, z) != null)
		{
			((DivinTotemTileEntity) w.getTileEntity(x,y, z)).updateEntity();
		}
		if(w.getBlockMetadata(x, y, z) == 1)
		{
			w.spawnParticle("smoke", x+0.5, y+1,z+0.5, 0, 0.01, 0);
		}
	
    }
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
    	return new DivinTotemTileEntity();
    }
   

}

