package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;
import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import fr.neocraft.main.Proxy.Serveur.network.util.T;

public class ClientQuest extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	ArrayList<Quest> list;
	
	public ClientQuest(ArrayList<Quest> l) 
	{
		list = l;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void A() {
		for(int i = 0; i < list.size(); i++)
		{
			list.get(i).Init(Side.CLIENT);
		}
		QuestManager.ArrayQuete = list;
		
	}

}
