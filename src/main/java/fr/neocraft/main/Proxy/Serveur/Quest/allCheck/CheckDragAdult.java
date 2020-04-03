package fr.neocraft.main.Proxy.Serveur.Quest.allCheck;

import java.io.Serializable;

import fr.neocraft.main.Proxy.Serveur.Quest.CheckElement;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;

public class CheckDragAdult extends CheckElement implements Serializable{
	
	private  static  final  long serialVersionUID =  5464867684657468765L;
	
	public CheckDragAdult() {
		
	}
	
	@Override
	public boolean IsEverDo(EntityPlayer p) {
		return 2 < PlayerStats.get(p).lvlGuiDrag[0];
	}

}
