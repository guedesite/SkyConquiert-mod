package fr.neocraft.main.Proxy.Serveur.Quest.allQuest;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Serveur.Quest.ElementQuest;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import fr.neocraft.main.Proxy.Serveur.Quest.allCheck.CheckStageBy1;
import fr.neocraft.main.Proxy.Serveur.Quest.allCheck.CheckStageBy2;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientOpenGui;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

public class SeigneurDuCiel extends Quest implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468767L;
	
	public ElementQuest[] Element = new ElementQuest[6];
	public String Name = "";
	private int state = 0;
	private int idQuest = 3;
	public boolean Do = false;

		public SeigneurDuCiel() {
			
		}
	
	@Override
	public void Init(Side side) {
		Element[0] = new ElementQuest(getTextQuest(side,"neo.quest4.title1"), getTextQuest(side,"neo.quest4.txt1"), "event_by_stage_1", 15000, 4, new CheckStageBy1());
		Element[1] = new ElementQuest(getTextQuest(side,"neo.quest4.title2"), getTextQuest(side,"neo.quest4.txt2"), "money_win",new ItemStack(ItemMod.Diamond_Hammer, 1), 30000,0,null);
		Element[2] = new ElementQuest(getTextQuest(side,"neo.quest4.title3"), getTextQuest(side,"neo.quest4.txt3"), "kill_evil",new ItemStack(ItemMod.bouclier,1, 6),15,0,null);
		Element[3] = new ElementQuest(getTextQuest(side,"neo.quest4.title4"), getTextQuest(side,"neo.quest4.txt4"), "place_gardien",new ItemStack(ItemMod.Titane_Ingot, 8),1,0,null);
		Element[4] = new ElementQuest(getTextQuest(side,"neo.quest4.title5"), getTextQuest(side,"neo.quest4.txt5"), "event_by_stage_2",50000,5,new CheckStageBy2());
		Element[5] = new ElementQuest(getTextQuest(side,"neo.quest4.title6"), getTextQuest(side,"neo.quest4.txt6"), "pickup_NeoDium Ingot",new ItemStack(ItemMod.Ultime_Key,1),1,0,new CheckStageBy2());
		
		this.Name = getTextQuest(side,"neo.quest4.name");
	}@Override
	public int Getid() {
		
		return this.idQuest;
	}
	public String getTextQuest(Side s, String txt){
		if(s == Side.CLIENT)
		{
			return I18n.format(txt);
		}  else { return txt; }
	}
	@Override
	public void Event(String type, EntityPlayer p, int amount) {
		PlayerStats stat = PlayerStats.get(p);
		
		
		if(Element[state].condition.toLowerCase() == type.toLowerCase() || Element[state].condition.toLowerCase().contains(type.toLowerCase()) || Element[state].condition.toLowerCase().equals(type.toLowerCase()))
		{
			
			if(2 <Element[state].val)
			{	
				Element[state].nbEvent += amount;
				if(Element[state].maxEvent<= Element[state].nbEvent)
				{
					state =GetRecompense( p);
					
				}else {
					QuestManager.UpdateQuest(p, idQuest, state, Element[state].nbEvent);
				}
			}
			else
			{
				state =GetRecompense(p);
			}
		}
		if(state < Element.length)
		{
			if(Element[state].ch != null && Element[state].ch.IsEverDo(p))
			{
				state =GetRecompense(p);
				if(state < Element.length)
				{
					if(Element[state].ch != null && Element[state].ch.IsEverDo(p))
					{
						state =GetRecompense(p);
						if(state < Element.length)
						{
							if(Element[state].ch != null && Element[state].ch.IsEverDo(p))
							{
								state =GetRecompense(p);
								if(state < Element.length)
								{
									if(Element[state].ch != null && Element[state].ch.IsEverDo(p))
									{
										state =GetRecompense(p);
									}
								}
							}
						}
					}
				}
			}
		}

		
	
	
	}
	
	@Override
	public int GetRecompense(EntityPlayer p) {

		Element[state].Recompense(p);
		if(Element[state].infoFinish == 7)
		{
			main.getbdd().update("UPDATE "+main.getbdd().getStringDrag()+" SET `IdQuest`=1 WHERE pseudo='"+p.getCommandSenderName()+"'");
		}
		else if(Element[state].infoFinish != 0)
		{
			main.getNetWorkClient().sendTo(new NetWorkClient(new ClientOpenGui(Element[state].infoFinish)), (EntityPlayerMP) p);
		}
		
		state++;
		if(state == Element.length)
		{
			this.End(p);
		}else {
			QuestManager.UpdateQuest(p, idQuest, state, 0);
		}
		return state;
	}


	@Override
	public ElementQuest GetElement() {
		return Element[this.state];
	}

	
	@Override
	public void End(EntityPlayer p) {
		Do = true;
		SoundManager.PlaySound(EnumSound.NeoMLvl4.getSound(), p);
		QuestManager.EndQuest(idQuest, p);
		QuestManager.AddQuest(4, p);
		
	}
	@Override
	public void SetStateClient(int i) {
		this.state = i;
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
		// TODO Auto-generated method stub
		return new SeigneurDuCiel();
	}


}
