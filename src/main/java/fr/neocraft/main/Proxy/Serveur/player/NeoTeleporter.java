package fr.neocraft.main.Proxy.Serveur.player;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
public class NeoTeleporter extends Teleporter
{

	private final WorldServer worldServerInstance;



	public NeoTeleporter(WorldServer par1WorldServer)
	{
		super(par1WorldServer);
		this.worldServerInstance = par1WorldServer;
	}

	@Override
	public void placeInPortal(Entity entity, double p2, double p3, double p4, float p5)
	{
	       int i = MathHelper.floor_double(entity.posX);
	       int j = MathHelper.floor_double(entity.posY);
	       int k = MathHelper.floor_double(entity.posZ);
	       this.worldServerInstance.getBlock(i, j, k); //dummy load to maybe gen chunk
	       int height = this.worldServerInstance.getHeightValue(i, k);
	       entity.setPosition( i, height, k );
	       return;
	} 

}
