package fr.neocraft.main.Blocks.Special;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Client.ClientProxy;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperTileEntity;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ShopperBlock extends Block{

	public static bdd bdd = main.getbdd();
    
	public ShopperBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		this.setHardness(0.6F);
		this.setLightOpacity(0);
		this.setBlockTextureName(neoreference.MOD_ID+":barrier");
		this.setStepSound(soundTypeStone);
	}
	@Override
    public boolean hasTileEntity(int metadata)
    {
        return true; // signale que le bloc a une entit�
    }
	@Override
	public void onBlockPreDestroy(World w, int x, int y, int z, int meta) 
	{
		if(w.getTileEntity(x, y, z) != null & w.getTileEntity(x, y, z) instanceof ShopperTileEntity)
		{
			this.dropBlockAsItem(w, x, y, z, ((ShopperTileEntity)w.getTileEntity(x, y, z)).getStackInSlot(0));
		}
	}
	@SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 1;
    }
	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new ShopperTileEntity();
	}



	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderType() {
        return ClientProxy.renderShopper;
    }
	@Override
    public boolean isOpaqueCube() {
        return false;
    }
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
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
	        	interact(world, x, y, z, player, 1);
	            return true;
	        }
	    }
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
    	super.onBlockPlacedBy(world,x, y,z,living, stack);
    	ShopperTileEntity tile2 = new ShopperTileEntity();
    	world.addTileEntity(tile2);
    	world.setTileEntity(x, y, z, tile2);
    	world.markTileEntityChunkModified(x, y, z, tile2);
        int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        if(direction == 3) { direction = 1; } else if (direction == 1) { direction = 3; }
        world.setBlockMetadataWithNotify(x, y, z, direction, 2);
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof ShopperTileEntity)
        {
        	((ShopperTileEntity)tile).setdirection(direction);
        }
    }

    // interact(world, x, y, z, player, 1);
    public void interact(World world, int x, int y, int z, EntityPlayer player, int s)
    {
    	Stage stage = RegisterStage.getStageAtXY(world.getChunkFromBlockCoords(x, z).xPosition, world.getChunkFromBlockCoords(x, z).zPosition, world);
    	if(stage != null)
    	{
    		if(!stage.isOwner(player.getCommandSenderName()))
    		{
    			ByAt(world, x,y, z, player, s);
    		}
    		else
    		{
    			if(s == 1)
    			{
    				player.openGui(main.instance, 4, world, x, y, z);
    			}
    		}
    	}
    	else
    	{
    		ByAt(world, x,y, z, player, s);
    	}
    }
    private void ByAt(World world, int x, int y, int z, EntityPlayer player, int s)
    { 
    	TileEntity tileentity = world.getTileEntity(x, y, z);
    	if(tileentity instanceof ShopperTileEntity)
    	{
    		ShopperTileEntity tile = (ShopperTileEntity) tileentity;
    		if(tile.getStackInSlot(0) != null)
    		{
    			if(tile.getStackInSlot(0).stackSize > s)
    			{
    				PlayerStats stat = PlayerStats.get(player);
    				int prix = tile.getValue();
    				if(stat.AsMoney(prix * s))
    				{
    					player.inventory.addItemStackToInventory(new ItemStack(tile.getStackInSlot(0).getItem(), s));
    					tile.getStackInSlot(0).splitStack(s);
    					stat.removeMoney(prix * s, player);
    					EntityPlayer pp = RegisterStage.getPlayer(tile.getowner());
    					if(pp != null)
    					{
    						PlayerStats.get(pp).giveMoney(prix*s, pp);
    					}
    					else 
    					{
    						bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+(prix*s)+" WHERE pseudo='"+tile.getowner()+"'");
    					}
    				}
    			}
    		}
    	}
    	
    }


}
