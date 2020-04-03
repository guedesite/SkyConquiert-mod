package fr.neocraft.main.Proxy.Serveur.Quest.allQuest;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.Quest.ElementQuest;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientUpdateQuest;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class HorraireKillSkeleton extends Quest implements Serializable{
	
	private  static  final  long serialVersionUID =  5464867684657468767L;
	
	public ElementQuest[] Element = new ElementQuest[1];
	public String Name = "";
	private int state = 0;
	private int idQuest = 7;
	
	public boolean Do = false;
	
	public HorraireKillSkeleton() {
		
	}
	
	@Override
	public void Init(Side side) {
		Element[0] = new ElementQuest(getTextQuest(side,"neo.quest6.title"), getTextQuest(side,"neo.quest8.text"), "kill_skeleton", new ItemStack(ItemMod.gardien), 20,-1, "", "");
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
		Do = true;
		GetElement().RecompenseWithRandomNb(p, 2);
		main.getHorraire().addPlayerDo(p.getCommandSenderName());
		QuestManager.sendToClienQuest(p,-1);
		SoundManager.PlaySound(EnumSound.NeoMLvl4.getSound(), p);
	}

	@Override
	public ElementQuest GetElement() {
		return Element[this.state];
	}




	@Override
	public int GetRecompense(EntityPlayer p) {
		return 0;
	}

	
	@Override
	public void Event(String type, EntityPlayer p, int amount) {
		PlayerStats stat = PlayerStats.get(p);
		if(Element[state].condition.toLowerCase() == type.toLowerCase() || Element[state].condition.toLowerCase().contains(type.toLowerCase()) || Element[state].condition.toLowerCase().equals(type.toLowerCase()))
		{
			stat.EventHorraire++;
			if(GetElement().maxEvent <= stat.EventHorraire)
			{
				this.End(p);
				main.getbdd().update("UPDATE "+main.getbdd().getStringPlayer()+" SET `AmountHorraire`=0 WHERE `pseudo`='"+p.getCommandSenderName()+"'");
			}else {
				main.getbdd().update("UPDATE "+main.getbdd().getStringPlayer()+" SET `AmountHorraire`="+stat.EventHorraire+" WHERE `pseudo`='"+p.getCommandSenderName()+"'");
				main.getNetWorkClient().sendTo(new NetWorkClient(new ClientUpdateQuest(this, stat.EventHorraire)), (EntityPlayerMP) p);
			}
		}
		
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
		return new HorraireKillSkeleton();
	}
}
