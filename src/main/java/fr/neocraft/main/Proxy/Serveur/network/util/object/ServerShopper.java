package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperTileEntity;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.DimensionManager;

public class ServerShopper extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	int nb, px, py, pz, world;
	
	public ServerShopper(int n, int x, int y, int z, int w)
	{
		nb = n;
		px = x;
		py = y;
		pz = z;
		world = w;
	}
	
	@SideOnly(Side.SERVER)
	@Override
	public void A(EntityPlayer p) {
		((ShopperTileEntity)DimensionManager.getWorld(world).getTileEntity(px, py, pz)).setValue(nb);
	}

}
