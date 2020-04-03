package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;
import java.util.ArrayList;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import fr.neocraft.main.Proxy.Serveur.network.util.T;

public class ClientUpdateQuest extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	Quest q;
	int am = 0;
	
	public ClientUpdateQuest(Quest qu,int amount) {
		q = qu;
		am = amount;
		
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void A() {
		if(q == null) { return; }
		ArrayList<Quest> e = QuestManager.ArrayQuete;
		q.Init(Side.CLIENT);
		for(int i = 0; i < e.size(); i++) {
			if(e.get(i).Getid() == q.Getid())
			{
				q.GetElement().nbEvent = am;
				e.set(i, q);
				return;
			}
		}
	}

}
