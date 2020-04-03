 package fr.neocraft.main.Proxy.Serveur.Quest;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.ChevalierDuCiel;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.EmpereurDuCiel;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.HorraireDragon;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.HorraireKillEnderman;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.HorraireKillSkeleton;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.HorraireKillWitch;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.HorraireKillZombie;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.HorraireShopperEvil;
import fr.neocraft.main.Proxy.Serveur.Quest.allQuest.SeigneurDuCiel;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientQuest;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientUpdateQuest;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class QuestManager implements Serializable{
	private static  fr.neocraft.main.bdd bdd = main.getbdd();
	
	public static Map<Integer, Quest> Quest = new HashMap<Integer, Quest>();
	public static SimpleNetworkWrapper nw = main.getNetWorkClient();
	public static ArrayList<Quest> ArrayQuete = new ArrayList<Quest>();
	
	 public static void Register()
	 {

		 PutInMap(new ChevalierDuCiel());
		 PutInMap(new HorraireShopperEvil());
		 PutInMap(new HorraireDragon());
		 PutInMap(new SeigneurDuCiel());
		 PutInMap(new EmpereurDuCiel());
		 PutInMap(new HorraireKillZombie());
		 PutInMap(new HorraireKillWitch());
		 PutInMap(new HorraireKillSkeleton());
		 PutInMap(new HorraireKillEnderman());
	 }
	 
	 private static void PutInMap(Quest q) {
		 Quest.put(q.Getid(), q);
	 }
	 
	 public static void initQuestServer()
	 {
		 Iterator it = Quest.values().iterator();
		 while(it.hasNext())
		 {
			 ((Quest)it.next()).Init(Side.SERVER);
		 }
	 }
	
	 
	 
	 
	/* public static void initQuestClient(int id, int el, int am) {
		 Quest q = Quest.get(id).copy();;
		 q.Init(Side.CLIENT);
		 q.SetStateClient(el);
		 q.GetElement().nbEvent=am;
		 ArrayQuete.add(q);
	} */
	 
	/* public static void UpdateQuestClient(int id, int el, int am) {
		 for(int i =0; i < ArrayQuete.size(); i++)
		{
			 Quest q = ArrayQuete.get(i);
			 if(q.Getid() == id)
			 {
				 q.Init(Side.CLIENT);
				 q.SetStateClient(el);
				 q.GetElement().nbEvent=am;
				 return;
			 }
		}
	
	} */
	 public static void sendToClienQuest(EntityPlayer p,int id)
	 {
		 PlayerStats stat = PlayerStats.get(p);
		 stat.ArrayQuetePlayer = new ArrayList<Quest>();
		 if(id!=-1)
		 {
			 if(main.getHorraire().isPlayerDo(p.getCommandSenderName()))
			 {
				 id = -1;
			 }
			 else
			 {
				 Quest q = Quest.get(id).copy();
				 q.Init(Side.SERVER);
				 q.GetElement().nbEvent = stat.EventHorraire;
				 stat.ArrayQuetePlayer.add(q);
			 }
		 }
		 int id2 = bdd.GetFreeId();
		 ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringPlayer()+" WHERE pseudo='" + p.getCommandSenderName() +"'", id2);
			if(result != null)
			{
				try {
					while ( result.next() ) {
						int[] qu = bdd.GetArrayInt(result.getString("Quest"));
						int[] ele = bdd.GetArrayInt(result.getString("Element"));
						int[] amo = bdd.GetArrayInt(result.getString("QuestAmount"));
						for(int i =0; i<qu.length; i++)
						{
							
							 Quest q = Quest.get(qu[i]).copy();
							 q.Init(Side.SERVER);
							 q.SetStateClient(ele[i]);
							 q.GetElement().nbEvent=amo[i];
							 stat.ArrayQuetePlayer.add(q);
						}
					}
					
					nw.sendTo(new NetWorkClient(new ClientQuest(stat.ArrayQuetePlayer)), (EntityPlayerMP) p);
				} catch (Exception e) {
					bdd.erreur("sendToClienQuest", e, "SELECT * FROM "+bdd.getStringPlayer()+" WHERE pseudo='" + p.getCommandSenderName() +"'");
					e.printStackTrace();
				
				}
			 }
			else
			{
				bdd.erreur("NULL QUEST BDD", "sendToClienQuest");
			}
			bdd.CloseFreeId(id2);
	 }
	
	 public static void UpdateQuest(EntityPlayer p,int id, int state, int amount)
	 {
		 PlayerStats stat = PlayerStats.get(p);

		
		 String qu="",e="",a="";
		for(int i =0; i < stat.ArrayQuetePlayer.size(); i++)
		{
			Quest q = stat.ArrayQuetePlayer.get(i);

				if(q.Getid() == id)
				{
					q.SetStateClient(state);
					q.GetElement().nbEvent = amount;
					nw.sendTo(new NetWorkClient(new ClientUpdateQuest(q, amount)), (EntityPlayerMP) p);
					
					qu+= q.Getid()+"&";
					 e+= q.GetState()+"&";
					 a+= q.GetElement().nbEvent+"&";
				} else if(main.getHourlyQuest() != q.Getid())
				{
					qu+= q.Getid()+"&";
					 e+= q.GetState()+"&";
					 a+= q.GetElement().nbEvent+"&";
					
				}
		}
		
			bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `Quest`='"+qu+"', `Element`='"+e+"', `QuestAmount`='"+a+"' WHERE pseudo='" + p.getCommandSenderName() +"'");
		
	}

	 
	 public static void RemoveHorraire(int id, EntityPlayer p) 
	 {
		 PlayerStats stat = PlayerStats.get(p);
		 for(int i =0; i < stat.ArrayQuetePlayer.size(); i++)
		{
				Quest q = stat.ArrayQuetePlayer.get(i);
				if(q.Getid() == id)
				{
					stat.ArrayQuetePlayer.remove(i);
				}
		}
	 }

	public static void EndQuest(int id, EntityPlayer p) {
		PlayerStats stat = PlayerStats.get(p);
		String qu="",e="",a="";
		for(int i =0; i < stat.ArrayQuetePlayer.size(); i++)
		{
			Quest q = stat.ArrayQuetePlayer.get(i);
			
			if(q.Getid() != id && main.getHourlyQuest() != q.Getid())
			{
				qu+= q.Getid()+"&";
				 e+= q.GetState()+"&";
				 a+= q.GetElement().nbEvent+"&";
			}
			else if(main.getHourlyQuest() != q.Getid()){
				stat.ArrayQuetePlayer.remove(i);
			}
		}
		int id2 = bdd.GetFreeId();
		ResultSet r = bdd.query("SELECT QuestFinish FROM "+bdd.getStringPlayer()+" WHERE `pseudo`='"+p.getCommandSenderName()+"'", id2);
		if(r != null)
		{
			try {
				while(r.next())
				{
					bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `QuestFinish`='"+r.getString("QuestFinish")+""+id+"&' WHERE `pseudo`='"+p.getCommandSenderName()+"'");
				}
			} catch (SQLException e1) {
				bdd.erreur("EndQuest", e1, "SELECT QuestFinish FROM "+bdd.getStringPlayer()+" WHERE `pseudo`='"+p.getCommandSenderName()+"'");
			}
		}
		bdd.CloseFreeId(id2);
		
		
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `Quest`='"+qu+"', `Element`='"+e+"', `QuestAmount`='"+a+"' WHERE pseudo='" + p.getCommandSenderName() +"'");
	
		sendToClienQuest(p,main.getHourlyQuest());
	}
	
	public static void AddQuest(int idQuest,EntityPlayer p) 
	{
		PlayerStats stat = PlayerStats.get(p);
		Quest t = Quest.get(idQuest).copy();
		t.Init(Side.SERVER);
		t.SetStateClient(0);
		t.GetElement().nbEvent = 0;
		stat.ArrayQuetePlayer.add(t);
		
		 String qu="",e="",a="";
		for(int i =0; i < stat.ArrayQuetePlayer.size(); i++)
		{
			Quest q = stat.ArrayQuetePlayer.get(i);
			
			if(main.getHourlyQuest() != q.Getid())
 			{
				qu+= q.Getid()+"&";
				 e+= q.GetState()+"&";
				 a+= q.GetElement().nbEvent+"&";
 			}
			
		}
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `Quest`='"+qu+"', `Element`='"+e+"', `QuestAmount`='"+a+"' WHERE pseudo='" + p.getCommandSenderName() +"'");
	
		
		sendToClienQuest(p,main.getHourlyQuest());
		
				
	}


}
