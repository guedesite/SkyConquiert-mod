package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import fr.neocraft.main.ServeurManager.SoundManager;

public class ClientSound extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	String s;
	float vol, pitch;
	
	public ClientSound(String d, float i, float j)
	{
		s= d;
		vol = i;
		pitch = j;
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public void A() {
		SoundManager.PlaySound(s, vol, pitch);
		
	}

}
