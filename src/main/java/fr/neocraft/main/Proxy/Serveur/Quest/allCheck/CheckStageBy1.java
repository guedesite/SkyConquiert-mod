package fr.neocraft.main.Proxy.Serveur.Quest.allCheck;

import java.io.Serializable;

import fr.neocraft.main.Proxy.Serveur.Quest.CheckElement;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;

public class CheckStageBy1 extends CheckElement implements Serializable{
	
	
	private  static  final  long serialVersionUID =  5464867684657468765L;
	
	public CheckStageBy1() {}
	
	@Override
	public boolean IsEverDo(EntityPlayer p) {
		PlayerStats stat = PlayerStats.get(p);
		for(int id:stat.idStage)
		{
			if(RegisterStage.getStageWithId(id).getIdStage() == 1)
			{
				return true;
			}
		}
		return false;
	}

}
