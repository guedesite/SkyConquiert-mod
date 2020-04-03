package fr.neocraft.main.Proxy.Serveur.player;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityPet;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientPetAttribute;
import info.ata4.minecraft.dragon.server.entity.helper.DragonBreedRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class PlayerStats implements IExtendedEntityProperties,Serializable
{


	
	public EntityPet MyEntityPet;
	private static final fr.neocraft.main.bdd bdd = main.getbdd();
	private static final NeoChat C = new NeoChat();
	public int Tick = 0;
	public boolean AdminStage = false;
	public double bonusday = 1.5;
	public boolean isMoneyTake = false;
	public int lastidStage = -1;
	public int LastLogin = 15;
	public int EventHorraire = 0;
	public int LasteDisband = 0;
	public ArrayList<Integer> idStage = new ArrayList<Integer>();
	public List<String> invite = new ArrayList<String>();
	public int[] Allbreed = new int[] {0};
	public boolean confirmjoin = false;
	public String confirmjoindata = " ";
	public int DragBreed = 0;
	public int Money = 50;
	public int MoneyTake = 0;
	public boolean IsDoingHorraire = false;
	public boolean IsFinishHorraire = false;
	public int cooldowndrag = 0;

	public int PetLvl = 0;
	public int PetExp = 0;
	public int PetId = -1;
	
	
	public ArrayList<Quest> ArrayQuetePlayer = new ArrayList<Quest>();
	
	public int[] DragHorX = new int[] { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	public int[] DragHorZ = new int[] { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
	
	
	
	public int[] lvlGuiDrag = new int[]
	{
		0, //lvl du gui				 	 :0 
		1, //lvl de vie			     	 :1
		1, //lvl de la vitesse       	 :2
		1, //lvl de la vitesse en vol    :3
		1, //lvl de d�gat de la boule		 :4
		1  //lvl de vitesse de la boule	 :5
	};
			
	private Date AttackTime;
	
	public boolean isInAttack() {
		if(this.AttackTime == null) { return false; }
		Calendar currenttime = Calendar.getInstance();
	    Date sqldate = new Date((currenttime.getTime()).getTime());
	    if(this.AttackTime.compareTo(sqldate) < 0)
	    {
	    	this.AttackTime = null;
	    	return false;
	    }
	    else
	    {
	    	return true;
	    }
	}
	
	public boolean isInAttackWithMessage(EntityPlayer p)
	{
		if(isInAttack())
		{
			p.addChatMessage(new ChatComponentTranslation("neo.DENY_COMBAT"));
			return false;
		}
		else {
			return true;
		}
	}
	
	public void setInAttack() {
		 Calendar currenttime = Calendar.getInstance();
	    currenttime.add(Calendar.SECOND, 10);
	    this.AttackTime = new Date((currenttime.getTime()).getTime());
	}
	
	public EntityPlayer player;
	public int invul = 20;
	
	@Override
	public void saveNBTData(NBTTagCompound compound)
	{
		
	/*	compound.setInteger("PetLvl", this.PetLvl);
		compound.setInteger("PetExp", this.PetExp);
		compound.setDouble("bonusday", this.bonusday);
		compound.setInteger("invul", this.invul);
		compound.setInteger("CooldownDrag", this.cooldowndrag);
		compound.setBoolean(("isMoneyTake"), this.isMoneyTake);

		compound.setInteger("Money", this.Money);
		compound.setInteger("LastLogin", this.LastLogin);
		compound.setInteger("LasteDisband", this.LasteDisband);
		compound.setInteger("MoneyTake", this.MoneyTake);
		compound.setIntArray("draggui", this.lvlGuiDrag);
		compound.setBoolean("AdminStage", this.AdminStage);
		compound.setInteger("breed", this.DragBreed);
		compound.setIntArray("DragHorX", this.DragHorX);
		compound.setIntArray("DragHorZ", this.DragHorZ);
		compound.setBoolean("IsDoingHorraire", this.IsDoingHorraire);
		compound.setInteger("EventHorraire", this.EventHorraire);
		compound.setBoolean("IsFinishHorraire", this.IsFinishHorraire);

		compound.setBoolean("IsFinishHorraire", this.IsFinishHorraire);
		compound.setIntArray("Allbreed", this.Allbreed);

		compound.setInteger("lastidStage", this.lastidStage);
		compound.setIntArray("idStage", this.idStage);
		
		
		compound.setBoolean("confirmjoin", this.confirmjoin);
		if(this.confirmjoindata != null)
		{
			compound.setString("confirmjoindata", this.confirmjoindata);
		} */

	}
	
	@Override
	public void loadNBTData(NBTTagCompound c)
	{
	/*this.invul = c.getInteger("invul");
		this.cooldowndrag = c.getInteger("CooldownDrag");
		this.IsDoingHorraire = c.getBoolean("IsDoingHorraire");
		this.IsFinishHorraire = c.getBoolean("IsFinishHorraire");
		this.isMoneyTake = c.getBoolean("isMoneyTake");

		this.Money = c.getInteger("Money");
		this.LasteDisband = c.getInteger("LasteDisband");
		this.LastLogin = c.getInteger("LastLogin");
		this.MoneyTake = c.getInteger("MoneyTake");
		this.DragBreed = c.getInteger("breed");
		this.lvlGuiDrag = c.getIntArray("draggui");
		this.AdminStage = c.getBoolean("AdminStage");
		this.lastidStage = c.getInteger("lastidStage");
		this.EventHorraire = c.getInteger("EventHorraire");
		this.idStage = c.getIntArray("idStage");
		this.confirmjoin = c.getBoolean("confirmjoin");
		this.confirmjoindata = c.getString("confirmjoindata");
		this.DragHorZ = c.getIntArray("DragHorZ");
		this.DragHorX = c.getIntArray("DragHorX");
		int i =0;
		this.bonusday = c.getDouble("bonusday");
		i=0;
	
		this.Allbreed = c.getIntArray("Allbreed");

		this.PetExp = c.getInteger("PetExp");
		this.PetLvl = c.getInteger("PetLvl");
		*/
	}
	
	public boolean CanBy(int i)
	{
		return i < this.Money;
	}
	public List getInvite()
	{
		return this.invite;
	}

	public void clearInvite(String n) {
		this.invite.remove(n);
	}
	public boolean hasInvite(String n)
	{
		for(int i =0; i < this.invite.size(); i++)
		{
			if(this.invite.get(i).equals(n))
			{
				return true;
			}
		}
		return false;
	}
	public void clearInviteAll()
	{
		this.invite = new ArrayList<String>();
	}
	public void addInvite(String n, EntityPlayer p)
	{
		if(20 < this.invite.size() )
		{
			p.addChatMessage(new ChatComponentTranslation(C.DENY_PLAYER_HAS_TO_MANY_INVITE));
		} else {
			this.invite.add(n);
		}
	}
	
	
	public PlayerStats(EntityPlayer player)
	{
		this.player = player;
	}
	
	private static Timer timer = new Timer();
	public void AddPlayerTakeOrbMoney(final EntityPlayer p, int nb)
	{
		if(!this.isMoneyTake)
		{
			this.isMoneyTake = true;
			this.MoneyTake+=nb;
			try {
				timer.schedule(new TimerTask() {
					  @Override
					  public void run() {
						  PlayerStats.get(p).giveMoneyTake(p);
					  }
					}, 10000);
			} catch(Exception e)
			{
				bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+this.MoneyTake+" WHERE pseudo='"+p.getCommandSenderName()+"'");
				this.isMoneyTake = false;
			}
		}
		else
		{
			this.MoneyTake+=4;
		}
	}

	@Override
	public void init(Entity entity, World world) {
		this.isMoneyTake = false;
		this.IsDoingHorraire = false;
		this.IsFinishHorraire = false;
		this.AdminStage = false;
		this.lastidStage = -1;
		this.idStage = new ArrayList<Integer>();
		this.invite = new ArrayList<String>();
		this.confirmjoin = false;
		this.confirmjoindata = " ";
		this.DragBreed = 0;
		this.Money = 50;

		this.LastLogin = 15;
		this.LasteDisband =0;
		this.bonusday = 1.5;
		this.EventHorraire = 0;
		this.Allbreed = new int[9];
		this.DragHorX = new int[] { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		this.DragHorZ = new int[] { -1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
		this.lvlGuiDrag = new int[]
		{
			0, //lvl du gui				 	 :0 
			1, //lvl de vie			     	 :1
			1, //lvl de la vitesse       	 :2
			1, //lvl de la vitesse en vol    :3
			1, //lvl de d�gat de la boule		 :4
			1  //lvl de vitesse de la boule	 :5
		};

		
	}
	
	public static final void reset(EntityPlayer player)
	{
		player.getExtendedProperties("neocraft").init(player, player.worldObj);
	}
	public static final void registerPlayer(EntityPlayer player)
	{
		if(player.getExtendedProperties("neocraft") == null)
		{
			player.registerExtendedProperties("neocraft", new PlayerStats(player));
		}
	}
	
	public static boolean isExist(EntityPlayer player)
	{
		return player.getExtendedProperties("neocraft") != null;
	}

	public static PlayerStats get(EntityPlayer player)
	{
		if(player.getExtendedProperties("neocraft") == null)
		{
			registerPlayer(player);
		}
		return (PlayerStats) player.getExtendedProperties("neocraft");
	}
	public void giveMoneyTake(EntityPlayer p) {
		this.Money = this.Money + this.MoneyTake;
		this.isMoneyTake = false;
		QuestEvent("money_win", p, this.MoneyTake);
		p.addChatMessage(new ChatComponentTranslation("neo.player_win_event", EnumChatFormatting.DARK_PURPLE+""+this.MoneyTake));
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+this.MoneyTake+" WHERE pseudo='"+p.getCommandSenderName()+"'");
		this.MoneyTake = 0;

	}
	public void giveMoney(int money2, EntityPlayer p) {
		this.Money = this.Money + money2;
		QuestEvent("money_win", p, money2);
		p.addChatMessage(new ChatComponentTranslation("neo.player_win_event",EnumChatFormatting.DARK_PURPLE+""+ money2));
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+money2+" WHERE pseudo='"+p.getCommandSenderName()+"'");
	}
	public void giveMoneyOutQuest(int money2, EntityPlayer p) {
		this.Money = this.Money + money2;
		p.addChatMessage(new ChatComponentTranslation("neo.player_win_event", EnumChatFormatting.DARK_PURPLE+""+money2));
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+money2+" WHERE pseudo='"+p.getCommandSenderName()+"'");
	}

	public void giveMoney(int money2, EntityPlayer p, EntityPlayer p2) {
		this.Money = this.Money + money2;
		QuestEvent("money_win", p, money2);
		p.addChatMessage(new ChatComponentTranslation("neo.player_pay_player_event",EnumChatFormatting.DARK_PURPLE+""+ p2.getCommandSenderName(),EnumChatFormatting.DARK_PURPLE+""+money2));
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money+"+money2+" WHERE pseudo='"+p.getCommandSenderName()+"'");
	}
	
	public void removeMoney(int money2, EntityPlayer p) {
		this.Money = this.Money - money2;
		 QuestEvent("money_lose", p, money2);
		p.addChatMessage(new ChatComponentTranslation("neo.loose_monay_event", EnumChatFormatting.DARK_PURPLE+""+money2));
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `money`=money-"+money2+" WHERE pseudo='"+p.getCommandSenderName()+"'");
	}
	public void QuestEvent(String type, EntityPlayer p, int amount)
	{
		if(p.getCommandSenderName().equals("guedesite")) { System.out.println(type+ " - "+amount); }
		if(ArrayQuetePlayer != null && !ArrayQuetePlayer.isEmpty())
		{
			if(p.getCommandSenderName().equals("guedesite")) { System.out.println("start type"); }
			for(int i = 0; i < ArrayQuetePlayer.size(); i++)
			{
				if(p.getCommandSenderName().equals("guedesite")) { System.out.println("event quest "+ArrayQuetePlayer.get(i).Getid()); }
				ArrayQuetePlayer.get(i).Event(type, p, amount);
			}
		
		}
		
	}
	
	public boolean AsDrag(int d)
	{
		for(int e:this.Allbreed)
		{
			if(e == d)
			{
				return true;
			}
		}
        return false;
	}
	public boolean AsDragString(String d)
	{
		int o = DragonBreedRegistry.getInstance().getBreedByName(d).getId();
		for(int e:this.Allbreed)
		{
			if(e == o)
			{
				return true;
			}
		}
        return false;
	}

	public void AddDrag(int d, String p)
	{
		int[] tempb = new int[this.Allbreed.length + 1];
		for(int i = 0 ; i < this.Allbreed.length; i++)
		{
			tempb[i] = this.Allbreed[i];
		}
		tempb[tempb.length - 1] = d;
		
        this.Allbreed = tempb;
        bdd.update("UPDATE "+bdd.getStringDrag()+" SET `AllBreed`='"+(bdd.SetArrayInt(this.Allbreed))+"' WHERE pseudo='"+p+"'");
	}
	public boolean AsMoney(int i )
	{
		return i <= this.Money;
	}

	public double getIntBonus() {
		
		return 1.5;
	}

	public void updatePet(String commandSenderName, int curentxp, int lvl) {
		
		this.PetLvl =lvl; 
		this.PetExp = curentxp;
		bdd.update("UPDATE "+bdd.getStringPlayer()+" SET `PetExp`="+curentxp+",`PetLvl`="+lvl+" WHERE pseudo='"+commandSenderName+"'");
		
	}
	

	public void updatePet(EntityPlayer p, boolean mustdie) {
		boolean flag = false;
		if(this.MyEntityPet != null)
		{
			
			if(this.MyEntityPet.isDead)
			{
				flag = true;
			} else if(mustdie)
			{
				flag = true;
				this.MyEntityPet.setDead();
			}
		} else {
			flag = true;
		}
		
		if(flag)
		{
			if(this.PetId != -1)
			{
				EntityPet pet = new EntityPet(p.worldObj);
				pet.setPosition(p.posX, p.posY, p.posZ);
				p.worldObj.spawnEntityInWorld(pet);
				pet.setPet(p, this.PetId, this.PetLvl, this.PetExp);
				this.MyEntityPet = pet;
			} else {
				this.MyEntityPet = null;
			}
				ClientPetAttribute d = new ClientPetAttribute(this.PetId, this.PetLvl);
				 
				d.PutAttribute(player, -1, 0, false);
				
				d.PutAttribute(player, this.PetId, this.PetLvl, false);
				 
				main.getNetWorkClient().sendTo(new NetWorkClient(d),(EntityPlayerMP) player); 
			
		}
		
		
	}
	
}