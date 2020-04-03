package fr.neocraft.main.Proxy.Serveur.Quest.allQuest;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.Quest.ElementQuest;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class HorraireDragon extends Quest implements Serializable{
	
	private  static  final  long serialVersionUID =  5464867684657468767L;
	
	public ElementQuest[] Element = new ElementQuest[1];
	public String Name = "";
	private int state = 0;
	private int idQuest = 2;
	public boolean Do = false;
	
	public  HorraireDragon(){ }
	
	@Override
	public void Init(Side side) {
		Element[0] = new ElementQuest(getTextQuest(side,"neo.quest3.title1"), getTextQuest(side,"neo.quest3.txt1"), -2);
		this.Name = getTextQuest(side,"neo.quest2.name");
	}
	public String getTextQuest(Side s, String txt){
		if(s == Side.CLIENT)
		{
			return I18n.format(txt);
		}  else { return txt; }
	}
	@Override
	public int Getid() {
		
		return this.idQuest;
	}
	@Override
	public void End(EntityPlayer p) {
		
	}

	@Override
	public ElementQuest GetElement() {
		return Element[this.state];
	}




	@Override
	public int GetRecompense(EntityPlayer p) {
		p.inventory.addItemStackToInventory(new ItemStack(ItemMod.Ultime_Key, 1));
		QuestManager.sendToClienQuest(p,-1);
		SoundManager.PlaySound(EnumSound.NeoMLvl4.getSound(), p);
		this.Do = true;
		return 0;
	}

	
	@Override
	public void Event(String type, EntityPlayer p, int amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void SetStateClient(int i) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String GetName() {
		return Name;
	}
	
	@Override
	public int GetState() {
		return state;
	}
	@Override
	public int GetNumberElement() {
		return Element.length;
	}
	@Override
	public boolean GetDo() {
		return Do;
	}
	@Override
	public Quest copy() {
		
		return new HorraireDragon();
	}
}
