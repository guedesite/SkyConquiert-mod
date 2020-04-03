package fr.neocraft.main.Blocks.Special;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.Special.BlockFuturist.Data;
import fr.neocraft.main.Proxy.Serveur.TileEntity.FuturistInputTileEntity;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientOpenGui;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockFuturistInput extends BlockFuturist{


	    @SideOnly(Side.CLIENT)
	    private IIcon faceDef;
	    @SideOnly(Side.CLIENT)
	    private IIcon faceTrue;
	    @SideOnly(Side.CLIENT)
	    private IIcon faceFalse;
	    @SideOnly(Side.CLIENT)
	    private IIcon top;
	    @SideOnly(Side.CLIENT)
	    private IIcon bottom;
	    
	    @SideOnly(Side.CLIENT)
	    public void registerBlockIcons(IIconRegister p_149651_1_)
	    {
	        this.faceDef = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistInput_face");
	        this.blockIcon = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistInput_default");
	        this.faceTrue = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistInput_facetrue");
	        this.faceFalse = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistInput_facefalse");
	        this.top = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistInput_top");
	        this.bottom = p_149651_1_.registerIcon(neoreference.MOD_ID + ":FuturistInput_bottom");
	    }
	 
	 @SideOnly(Side.CLIENT)
	    public IIcon getIcon(int side, int meta)
	    {
		 	if(side == 0) {
		 		return this.bottom;
		 	} else if(side == 1)
		 	{
		 		return this.top;
		 	}
		 	
			if((meta - 12) == side)
			{
				return this.faceTrue;
			}
			else if((meta - 6) == side)
			{
				return this.faceFalse;
			}
			else if(meta == side)
			{
				return this.faceDef;
			}
		 
		 return this.blockIcon;
		 
	    }
	    
	public BlockFuturistInput(Material p_i45394_1_, int level) {
		super(p_i45394_1_, level);
	}

	@Override
	public void RecieveData(World w, int x, int y, int z, int lastx, int lasty, int lastz, Data type, int nbr) {
		if(type.equals(BlockFuturist.Data.Reset))
		{
			int meta = w.getBlockMetadata(x, y, z);
			if(5 < meta )
			 {
				 if(11 < meta )
				 {
					w.setBlockMetadataWithNotify(x, y, z, meta-12, 2);
				 } else {
					 w.setBlockMetadataWithNotify(x, y, z, meta-6, 2);
				 }
			 }
		}
		
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
	            player.openGui(main.instance, 15, world, x, y, z);
	            return true;
    }
	
	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true; // signale que le bloc a une entité
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new FuturistInputTileEntity(); // indique quelle est l'entité de bloc
    }
    
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = BlockPistonBase.determineOrientation(p_149689_1_, p_149689_2_, p_149689_3_, p_149689_4_, p_149689_5_);
        p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);

       
    }
    
    public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
    {
        super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
        this.func_149938_m(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
    }

    private void func_149938_m(World p_149938_1_, int p_149938_2_, int p_149938_3_, int p_149938_4_)
    {
        if (!p_149938_1_.isRemote)
        {
         
            p_149938_1_.setBlockMetadataWithNotify(p_149938_2_, p_149938_3_, p_149938_4_, 0, 2);
        }
    }
}
