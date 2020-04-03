package fr.neocraft.main.Blocks.Special;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.BlockBasic;
import fr.neocraft.main.Proxy.Client.ClientProxy;
import fr.neocraft.main.Proxy.Serveur.TileEntity.TileEntityNull;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BoxVoteBlock extends BlockBasic {
	@SideOnly(Side.CLIENT)
    private IIcon top;
    @SideOnly(Side.CLIENT)
    private IIcon face;
    @SideOnly(Side.CLIENT)
    private IIcon other;
    
	public BoxVoteBlock(Material p_i45394_1_) {
		super(p_i45394_1_, 0);
		this.setHardness(0.6F);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
		this.setLightOpacity(0);
		this.setBlockUnbreakable();
		this.setStepSound(soundTypeStone);
		this.setBlockTextureName(neoreference.MOD_ID+":barrier");
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
        return ClientProxy.renderBoxVote;
    }
	@Override
    public boolean isOpaqueCube() {
        return false;
    }  
    
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
    	return new TileEntityNull();
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
        {
            return true;
        }
        else
        {
            player.openGui(main.instance, 5, world, x, y, z);
            return true;
        }
    }


}
