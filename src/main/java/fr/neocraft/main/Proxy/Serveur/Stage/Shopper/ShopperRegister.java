package fr.neocraft.main.Proxy.Serveur.Stage.Shopper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ShopperRegister {
	private static final bdd bdd = main.getbdd();
	public static ArrayList<ShopperData> ShopperPnj;
	public static void register()
	{
		int id = bdd.GetFreeId();
		try 
		{
			ShopperPnj = new ArrayList<ShopperData>();
			LogStage("START REGISTER ALL SHOPPER");
				ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringShopper(), id);
				try {
					if(result != null)
					{
						while ( result.next() ) 
						{
							
							try
							{
								ShopperPnj.add(new ShopperData(result.getInt("x"), result.getInt("y"), result.getInt("z"), result.getInt("id2"), bdd.GetArrayInt(result.getString("stack")), bdd.GetArrayInt(result.getString("prix")), bdd.GetArrayInt(result.getString("damage"))));
	
							} catch(ClassCastException e)
							{
								e.printStackTrace();
								LogStage("ERROR ENTITY WITH ID " + result.getInt("id2"));
							}
						}
					}
				} catch (SQLException e) {
					bdd.erreur("REGISTER STAGE", e, "SELECT * FROM "+bdd.getStringStage());
				}
		} catch(Exception e)
		{
			System.err.println("ERREUR LORS DU CHARGEMENT");
			System.err.println("NOUVELLE TENTATIVE DANS 5 SEC !");
			e.printStackTrace();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
	
				e1.printStackTrace();
			}
			register();

		}
		bdd.CloseFreeId(id);
	}
	public static void LogStage(String i)
	{
		System.out.println("[NEOCRAFT REGISTER SHOPPER] " + i);
	}
	public static ShopperData getEntityShopperByPlayer(EntityPlayer p)
	{
		return getEntityShopperByPos(p.worldObj.provider.dimensionId,(int) p.posX, (int)p.posY, (int)p.posZ);
	}
	
	public static ShopperData getEntityShopperByPos(int w, int x, int y, int z)
	{
		try
		{
			for(ShopperData e:ShopperPnj)
			{
				if((int)e.posX == x & (int)e.posY == y & (int)e.posZ == z & e.world == w)
				{
					return e;
				}
			}
			return null;
		} catch(Exception e )
		{
			return null;
		} 
	}
	
	public static ShopperData getEntityShopperByEntity(Entity p)
	{

			return getEntityShopperByPos(p.worldObj.provider.dimensionId,(int) p.posX, (int)p.posY, (int)p.posZ);
	}
	public static void ShopperAddStock() {
		for(int i = 0; i < ShopperPnj.size(); i++)
		{
			ShopperPnj.get(i).addStock();
		}
		
	}
}
