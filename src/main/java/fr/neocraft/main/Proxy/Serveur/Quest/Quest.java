package fr.neocraft.main.Proxy.Serveur.Quest;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public class Quest implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468767L;
	
	/*
	 * 
	 *  Cela n'est pas une interface ( alors que cela parait logique que cela en soit une)
	 *  car je devais initialis� serialVersionUID pour pouvoir r�cup�r� les extends de cette classe
	 *  pour la r�ception de packet.
	 *  
	 * 
	 */
	
	public Quest() {}
	
	public int Getid() {return 0;}
	public void Init(Side side) {}
	public void End(EntityPlayer p) {}
	public ElementQuest GetElement(){ return null;}
	public int GetState(){return 0;}
	public int GetNumberElement(){return 0;}
	public String GetName(){ return null;}
	public Quest copy(){ return null;}
	public int GetRecompense(EntityPlayer p){return 0;}
	
	public boolean GetDo(){return false;}
	public void Event(String type, EntityPlayer p, int amount){}
	public void SetStateClient(int i){}
}
