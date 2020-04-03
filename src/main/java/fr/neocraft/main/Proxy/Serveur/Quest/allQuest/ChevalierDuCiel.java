package fr.neocraft.main.Proxy.Serveur.Quest.allQuest;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Quest.ElementQuest;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import fr.neocraft.main.Proxy.Serveur.Quest.allCheck.CheckDragAdult;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientOpenGui;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ChevalierDuCiel extends Quest implements Serializable{

	public ElementQuest[] Element = new ElementQuest[14];
	public String Name = "";
	private int state = 0;
	private int idQuest = 0;
	public boolean Do = false;

	public  ChevalierDuCiel(){ }
	
	private  static  final  long serialVersionUID =  5464867684657468767L;
	@Override
	public void Init(Side side) {
		Element[0] = new ElementQuest(getTextQuest(side,"neo.quest1.title1"), getTextQuest(side,"neo.quest1.txt1"), "cmd_null", new ItemStack(Items.stone_axe), 1,null);
		Element[1] = new ElementQuest(getTextQuest(side,"neo.quest1.title2"), getTextQuest(side,"neo.quest1.txt2"), "break_log", 250, 4,0,null);
		Element[2] = new ElementQuest(getTextQuest(side,"neo.quest1.title3"), getTextQuest(side,"neo.quest1.txt3"), "place_sapling", 300,0,null);
		Element[3] = new ElementQuest(getTextQuest(side,"neo.quest1.title4"), getTextQuest(side,"neo.quest1.txt4"), "interact_seeds", new ItemStack(Items.iron_ingot, 5),0,null);
		Element[4] = new ElementQuest(getTextQuest(side,"neo.quest1.title5"), getTextQuest(side,"neo.quest1.txt5"), "place_stonebrick", 300, 32,0,null);
		Element[5] = new ElementQuest(getTextQuest(side,"neo.quest1.title6"), getTextQuest(side,"neo.quest1.txt6"), "touche_2", 350,0,null);
		Element[6] = new ElementQuest(getTextQuest(side,"neo.quest1.title7"), getTextQuest(side,"neo.quest1.txt7"), "touche_4", 350,2,null);
		Element[7] = new ElementQuest(getTextQuest(side,"neo.quest1.title8"), getTextQuest(side,"neo.quest1.txt8"), "break_crops" , new ItemStack(Items.iron_ingot, 5), 8,0,null);
		Element[8] = new ElementQuest(getTextQuest(side,"neo.quest1.title9"), getTextQuest(side,"neo.quest1.txt9"), "interact_reeds" , 200, 8,0,null);
		Element[9] = new ElementQuest(getTextQuest(side,"neo.quest1.title10"), null, "break_stonebrick",64, 100,0,null);
		Element[10] = new ElementQuest(getTextQuest(side,"neo.quest1.title11"), null, "break_reeds",new ItemStack(Items.iron_ingot, 15), 12,0,null);
		Element[11] = new ElementQuest(getTextQuest(side,"neo.quest1.title12"), getTextQuest(side,"neo.quest1.txt12"), "interact_pnj" , 250,3,null);
		Element[12] = new ElementQuest(getTextQuest(side,"neo.quest1.title13"), null, "money_win", new ItemStack(Items.diamond, 6), 500,0,null);
		Element[13] = new ElementQuest(getTextQuest(side,"neo.quest1.title14"), getTextQuest(side,"neo.quest1.txt14"), "dragon_3" , 1000,0, new CheckDragAdult());
		this.Name = getTextQuest(side,"neo.quest1.name");
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
		if(Element[state].infoFinish != 0)
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
		QuestManager.AddQuest(3, p);
		
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
		return new ChevalierDuCiel();
	}
	@Override
	public int Getid() {
		
		return this.idQuest;
	}
	


}
