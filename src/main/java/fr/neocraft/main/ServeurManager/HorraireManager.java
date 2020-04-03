package fr.neocraft.main.ServeurManager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandHorraireDrag;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;

public class HorraireManager{

	private List PlayerDo = null;
	public static int idQuestHorraire = -1;
	private int[] all = new int[] { 1,5,7,8};
	private int stat=0;
	private static final bdd bdd = main.getbdd();
	private Random r=new Random();
	
	
	
	public void start()
	{
		main.startContainer();
		startMarcheBox();
		int d = r.nextInt(all. length);
		if(DailyManager.getIdDayByName() == 7)
		{
			while(d == 1)
			{
				d = r.nextInt(all. length);
			}
		}
			idQuestHorraire = all[d];
			this.PlayerDo = new ArrayList();
			
			
			
			 ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();
			 SoundManager.PlaySoundAll(EnumSound.NeoEHorraire.getSound());
			 EntityPlayer p ;
			 while (li.hasNext()){
			    	p = ((EntityPlayer) li.next());
			        QuestManager.sendToClienQuest(p,idQuestHorraire);
			    }
			    switch(idQuestHorraire)
			    {
			 		case 1:
			 			break;
			 		case 2:
			 			lastDrag = r.nextInt(3);
			 			CommandHorraireDrag.startH(DimensionManager.getWorld(0), lastDrag);
			 			li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();
			 		    while (li.hasNext()){
			 		    	p = ((EntityPlayer) li.next());
			 		    	PlayerStats.get(p).DragHorX = new int[] { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
			 		    	PlayerStats.get(p).DragHorZ = new int[] { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
			 		    }
			 			break;
			 		    
			    }

		
	
	}
	private int lastDrag = -1;
	public boolean isPlayerDo(String p) {
		if(this.PlayerDo == null) { return false;}
		 for (int i = 0; i < this.PlayerDo.size(); i++) {
	            if (this.PlayerDo.get(i).equals(p)) {
	            	this.PlayerDo.remove(i);
	            }
	        }
		return false;
	}
	
	public void addPlayerDo(String s)
	{
		this.PlayerDo.add(s);
	}
	/*public enum Enum {
		PnjEvil(1),
		DragHor(2);
		
		private int id;
		 
	    Enum(int envUrl) {
	        this.id = envUrl;
	    }
	 
	    public int getId() {
	        return this.id;
	    }
	} */
	public void stopH() {
		this.PlayerDo = null;
		if(lastDrag != -1)
		{
			CommandHorraireDrag.RemoveH(DimensionManager.getWorld(0), lastDrag);
		}
		lastDrag = -1;
		ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();
	    
		while (li.hasNext()){
			EntityPlayer p = (EntityPlayer) li.next();
	    	QuestManager.RemoveHorraire(this.idQuestHorraire, p);
	        QuestManager.sendToClienQuest(p,-1);
	    }
	    this.idQuestHorraire = -1;
	    bdd.update("UPDATE "+main.getbdd().getStringPlayer()+" SET `AmountHorraire`=0 WHERE `AmountHorraire`>0");
	}
	public void startMarcheBox() {
		er = 0;
		List t = new ArrayList<Integer>();
		bdd.update("UPDATE "+bdd.getStringHdv()+" SET `effect`=1 WHERE effect=2");
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringHdv()+" WHERE effect=1",id);
		if(result != null)
		{
			try {
				while(result.next())
				{
					t.add(result.getInt("id"));
				}
			} catch(Exception e)
			{
				e.printStackTrace();
				erreurMarcheBox();
			}
		}
		else
		{
			erreurMarcheBox();
		}
		bdd.CloseFreeId(id);
		if(!t.isEmpty())
		{
			Random r = new Random();
			for(int o = 0; o<72;  o++)
			{
				bdd.update("UPDATE "+bdd.getStringHdv()+" SET `effect`=2 WHERE id="+t.get(r.nextInt(t.size())));
			}
		}
			
		
	}
	private int er = 0;
	public void erreurMarcheBox() {
		System.err.println("ERREUR START BOX NEXT TRY IN 5 SEC");
		new Timer().schedule(new TimerTask() {
			  @Override
			  public void run() {
				  startMarcheBox();
			  }
		}, 5000);
	}
}
