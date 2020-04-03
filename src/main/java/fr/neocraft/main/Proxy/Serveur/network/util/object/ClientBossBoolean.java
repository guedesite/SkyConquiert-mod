package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.network.util.T;

public class ClientBossBoolean extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	boolean b;
	
	public ClientBossBoolean(boolean t)
	{
		b = t;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void A()
	{
		main.BossActive = b;
	}
	
}
