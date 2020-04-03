package fr.neocraft.main.Proxy.Serveur.Quest;

import java.io.Serializable;

import net.minecraft.entity.player.EntityPlayer;

public class CheckElement implements Serializable{
	
	private  static  final  long serialVersionUID =  5464867684657468765L;
	
	public CheckElement() {}
	
	public boolean IsEverDo(EntityPlayer p) { return false; }
}
