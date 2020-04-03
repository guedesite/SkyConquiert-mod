package fr.neocraft.main.Proxy.Serveur.Stage;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class StageData extends Object{

	public boolean IsChange = false;
	private int 
		IdStage, 
		IdWorld,
		Id, 
		MaxWidth,
		bank,
		X,
		Y,
		Z,
		Guardien,
		Xpos, 
		Zpos,
		lvl;
	
	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		IsChange =  true;
		this.lvl = lvl;
	}
	private String 
		Owner,
		Name;
	
	private Date
		Bouclier;
	
	private ArrayList<String>
		Ally,
		Ennemy,
		SubOwner, 
		PermAll, 
		PermMember,
		PermAllie;
	
	private boolean
		InAttack = false,
		IsUse = false,
		hasHome = false;
	

	/** 
	 *  PERM:
	 * 	enderpearl
	 *  attaque
	 *  hurt
	 *  bonemeal
	 *  harvest
	 *  interact
	 *  container
	 *  block
	 * **/
	
	public boolean CanBouclier()
	{
		if(this.Bouclier == null) { return true; }
		Calendar currenttime = Calendar.getInstance();
	    Date sqldate = new Date((currenttime.getTime()).getTime());
	    if(this.Bouclier.compareTo(sqldate) < 0)
	    {
	    	this.Bouclier = null;
	    	return true;
	    }
	    else
	    {
	    	return false;
	    }
	}
	
	public boolean IsHostile()
	{
		return 0 < this.Guardien;
	}
	public void SetGuardien(int i)
	{
		this.Guardien = i;
	}
	public void setAlly(ArrayList<String> i)
	{
		if(i == null)
		{
			i = new ArrayList<String>();
		}
		this.Ally = i;
		IsChange = true;
	}
	public void setEnnemy(ArrayList<String> i)
	{
		if(i == null)
		{
			i = new ArrayList<String>();
		}
		this.Ennemy = i;
		IsChange = true;
		
	}
	public ArrayList<String> getEnnemy()
	{
		return this.Ennemy;
	}
	public ArrayList<String> getAlly()
	{
		return this.Ally;
	}
	public void setBouclierString(long d)
	{
		if(d != 0)
		{
			try {
								
					this.Bouclier = new Date(d);
					IsChange = true;
			}catch(Exception e) {
				this.Bouclier = null;

				e.printStackTrace();
				System.err.println(d);
			}
		}else {
			this.Bouclier = null;
			IsChange = true;
		}
		
	
	}
	public void setBouclier(Date d)
	{

			this.Bouclier = d;
			IsChange = true;

	}
	public Date getBouclier()
	{
		Calendar currenttime = Calendar.getInstance();
	    Date sqldate = new Date((currenttime.getTime()).getTime());
	    
	    if(this.Bouclier!=null && this.Bouclier.compareTo(sqldate) < 0)
	    {
	    	this.Bouclier = null;
	    }
		return this.Bouclier;
	}
	public long getStringBouclier()
	{
		if(this.getBouclier() == null)
		{
			return 0;
		}
		else {

		return this.getBouclier().getTime();
		}
	}
	public int getBank()
	{
		return this.bank;
	}
	public boolean AsBank(int i )
	{
		return i <= this.bank;
	}
	public boolean hasHome()
	{
		return this.hasHome;
	}
	public int hasHomeCode()
	{
		if(this.hasHome)
		{
			return 1;
		}else {
			return 0;
		}
	}
	public int IsUseCode()
	{
		if(this.IsUse)
		{
			return 1;
		}else {
			return 0;
		}
	}
	public boolean IsUse()
	{
		return this.IsUse;
	}
	public boolean IsInAttack()
	{
		return this.InAttack;
	}
	public int getId()
	{
		return this.Id;
	}
	public int getIdStage()
	{
		return this.IdStage;
	}
	public int getIdWorld()
	{
		return this.IdWorld;
	}
	public String getOwner()
	{
		return this.Owner;
	}
	public ArrayList<String> getSubOwner()
	{
		return this.SubOwner;
	}
	
	public int getXpos()
	{
		return this.Xpos;
	}
	public int getZpos()
	{
		return this.Zpos;
	}

	public int getX()
	{
		return this.X;
	}
	public int getY()
	{
		return this.Y;
	}
	public int getZ()
	{
		return this.Z;
	}
	public int getMaxWidth()
	{
		return this.MaxWidth;
	}

	public ArrayList<String> getPermAll()
	{
		return this.PermAll;
	}
	public ArrayList<String> getPermMember()
	{
		return this.PermMember;
	}
	public ArrayList<String> getPermAllie()
	{
		return this.PermAllie;
	}
	public void SethasHome(boolean i)
	{
		this.hasHome = i;
		IsChange = true;
	}
	public void SetIsUse(boolean i)
	{
		this.IsUse = i;
		IsChange = true;
	}
	public void setInAttack(boolean i)
	{
		this.InAttack = i;
		IsChange = true;
	}
	public void setId(int i)
	{
		this.Id = i;
		IsChange = true;
	}
	public void setIdStage(int i)
	{
		this.IdStage = i;
		IsChange = true;
	}
	public void setIdWorld(int i)
	{
		this.IdWorld = i;
		IsChange = true;
	}
	public void setOwner(String i)
	{
		this.Owner = i;
		IsChange = true;
	}
	public void setArraySubOwner(ArrayList<String> i)
	{
		if(i == null)
		{
			i = new ArrayList<String>();
		}
		this.SubOwner = i;
		IsChange = true;
	}
	public void setBank(int i)
	{
		this.bank = i;
		IsChange = true;
	}
	public void addtoBank(int i)
	{
		this.bank= this.bank + i;
		IsChange = true;
	}
	public void removetoBank(int i)
	{
		this.bank= this.bank - i;
		IsChange = true;
	}

	public void setMaxWidth(int i)
	{
		this.MaxWidth = i;
		IsChange = true;
	}
	public void setXpos(int i)
	{
		this.Xpos = i;
		IsChange = true;
	}
	public void setZpos(int i)
	{
		this.Zpos = i;
		IsChange = true;
	}
	public void setBouclierNull()
	{
		this.Bouclier = null;
		IsChange = true;
	}
	public void setPermAll(ArrayList<String> i)
	{
		if(i == null)
		{
			i = new ArrayList<String>();
		}
		this.PermAll = i;
		IsChange = true;
	}
	public void setPermMember(ArrayList<String> i)
	{
		if(i == null)
		{
			i = new ArrayList<String>();
		}
		this.PermMember = i;
		IsChange = true;
	}
	public void setPermAllie(ArrayList<String> i)
	{
		if(i == null)
		{
			i = new ArrayList<String>();
		}
		this.PermAllie = i;
		IsChange = true;
	}
	public void setHome(int x, int y, int z)
	{
		this.X = x;
		this.Y = y;
		this.Z = z;
		IsChange = true;
	}
	public String getName()
	{
		return this.Name;
	}
	public void setName(String n)
	{
		this.Name = n;
		IsChange = true;
	}

	
}
