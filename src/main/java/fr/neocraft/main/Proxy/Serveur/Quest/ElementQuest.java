package fr.neocraft.main.Proxy.Serveur.Quest;

import java.io.Serializable;
import java.util.Random;

import fr.neocraft.main.Proxy.Serveur.player.AnnimationManager;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ElementQuest implements Serializable{
	
	private  static  final  long serialVersionUID =  5464867684657468766L;
	
	public String info;
	public String stat, condition;
	public int val;
	public int money, maxEvent;
	public int nbEvent;
	public int stack, nbstack;
	public int infoFinish;
	public CheckElement ch;
	public ElementQuest() {
		
	}
	public ElementQuest(String stat, String txt, String Cond, ItemStack stack, int info, CheckElement check)
	{
		this.stat = stat;
		this.condition = Cond;
		this.info = txt;
		this.stack = Item.getIdFromItem(stack.getItem());
		this.nbstack = stack.stackSize;
		val = 1;
		this.infoFinish = info;
		this.ch = check;
	}
	public ElementQuest(String stat, String txt, String Cond, int money, int info, CheckElement check)
	{
		this.stat = stat;
		this.condition = Cond;
		this.info = txt;
		this.money = money;
		val = 2;
		this.infoFinish = info;
		this.ch = check;
	}
	public ElementQuest(String stat, String txt, String Cond, int money, int maxEvent, int info, CheckElement check)
	{
		this.stat = stat;
		this.condition = Cond;
		this.info = txt;
		this.money = money;
		val = 3;
		this.maxEvent = maxEvent;
		this.infoFinish = info;
		this.ch = check;
	}
	public ElementQuest(String stat, String txt, String Cond, ItemStack stack, int maxEvent, int info, CheckElement check)
	{
		this.stat = stat;
		this.condition = Cond;
		this.info = txt;
		this.stack = Item.getIdFromItem(stack.getItem());
		this.nbstack = stack.stackSize;
		val = 4;
		this.maxEvent = maxEvent;
		this.ch = check;
		this.infoFinish = info;
	}
	public ElementQuest(String stat, String txt, int val)
	{
		this.stat = stat;
		this.val = val;
		this.info = txt;
	}
	public ElementQuest(String stat, String txt, String Cond, ItemStack stack, int maxEvent, int info)
	{
		this.stat = stat;
		this.condition = Cond;
		this.info = txt;
		this.stack = Item.getIdFromItem(stack.getItem());
		this.nbstack = stack.stackSize;
		val = 4;
		this.maxEvent = maxEvent;
		this.infoFinish = info;
	}
	public ElementQuest(String stat, String txt, String Cond, ItemStack stack, int maxEvent, int info, String d, String o)
	{
		this.stat = stat;
		this.condition = Cond;
		this.info = txt;
		this.stack = Item.getIdFromItem(stack.getItem());
		this.nbstack = stack.stackSize;
		val = -3;
		this.maxEvent = maxEvent;
		this.infoFinish = info;
	}
	public void Recompense(EntityPlayer p)
	{
		if(val == 1 || val == 4)
		{
			p.inventory.addItemStackToInventory(new ItemStack(Item.getItemById(stack), nbstack));
		}else
		{
			PlayerStats.get(p).giveMoneyOutQuest(money, p);
		}
		AnnimationManager.DownUp(p, "happyVillager");
		p.worldObj.playSoundAtEntity(p, "random.levelup", 1F, 1F);
	}
	private Random r = new Random();
	public void RecompenseWithRandomDmg(EntityPlayer p, int nb)
	{
		if(val == 1 || val == 4)
		{
			ItemStack stack2 = new ItemStack(Item.getItemById(stack), nbstack);
			stack2.setItemDamage((r.nextInt(nb)+1));
			p.inventory.addItemStackToInventory(stack2);
		}
		AnnimationManager.DownUp(p, "happyVillager");
		p.worldObj.playSoundAtEntity(p, "random.levelup", 1F, 1F);
	}
	public void RecompenseWithRandomNb(EntityPlayer p, int nb)
	{
		if(val == 1 || val == 4)
		{
			ItemStack stack2 = new ItemStack(Item.getItemById(stack), nbstack);
			stack2.stackSize = (r.nextInt(nb)+1);
			p.inventory.addItemStackToInventory(stack2);
		}
		AnnimationManager.DownUp(p, "happyVillager");
		p.worldObj.playSoundAtEntity(p, "random.levelup", 1F, 1F);
	}
}
