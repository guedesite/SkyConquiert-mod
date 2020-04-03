package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;
import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Client.event.TextDraw;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import net.minecraft.client.resources.I18n;

public class ClientPlayerList extends T implements Serializable{

	
	private  static  final  long serialVersionUID =  5464867684657468768L;
	ArrayList<String> e;
	
	public ClientPlayerList(ArrayList<String> d) {
		e = d;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void A() {
		String q = I18n.format("neo.specq", new Object[0]), n = I18n.format("neo.specn", new Object[0]);
		for(int i = 0; i < e.size(); i++)
		{
			e.set(i, e.get(i).replaceAll("\\(", q).replaceAll("\\)", n));
		}
		TextDraw.Player_List = e;
	}

}
