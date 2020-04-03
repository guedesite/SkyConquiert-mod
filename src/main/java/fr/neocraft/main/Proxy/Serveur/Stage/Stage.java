package fr.neocraft.main.Proxy.Serveur.Stage;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardien;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;

public class Stage extends StageData {
	
	public int XpFictive = 0;
	
	public Stage(int idWorld, int StageId, int id, String owner, ArrayList subowner, int Xpos, int Zpos, ArrayList permAll, ArrayList permMember,ArrayList permAllie, int IsUse, int x, int y, int z, int hasHome, int Money, String name, Long bouclier, ArrayList Ally, ArrayList Ennemy, int lvl)
	{
		this.setIdWorld(idWorld);
		this.setIdStage(StageId);
		this.setId(id);
		this.setOwner(owner);
		this.setArraySubOwner(subowner);
		this.setXpos(Xpos);
		this.setZpos(Zpos);
		this.setPermAll(permAll);
		this.setPermMember(permMember);
		this.setPermAllie(permAllie);
		this.setBank(Money);
		switch (IsUse) {
			case 0:  this.SetIsUse(false);
				break;
			case 1:  this.SetIsUse(true);
				break;
			default: this.SetIsUse(false);
				break;
			}
		this.setHome(x, y, z);
		
		switch (hasHome) {
		case 0:  this.SethasHome(false);
			break;
		case 1:  this.SethasHome(true);
			break;
		default: this.SethasHome(false);
			break;
		}
		this.setBouclierString(bouclier);
		this.setName(name);
		this.setAlly(Ally);
		this.setEnnemy(Ennemy);
		this.setLvl(lvl);
	}

	public boolean addSubOwner(String pseudo)
	{
		this.getSubOwner().add(pseudo);
		return true;
	}
	
	public void addAlly(String ally)
	{
		this.getAlly().add(ally);
	}
	public void addEnnemy(String ennemy)
	{
		this.getEnnemy().add(ennemy);
	}
	public void removeAlly(String deleteMe) {
		this.getAlly().remove(deleteMe);
	}
	public void removeEnnemy(String deleteMe) {
		this.getEnnemy().remove(deleteMe);
	}
	public void giveBouclierHour(int h)
	{
		 Date stagedate = this.getBouclier();
		 Calendar currenttime = Calendar.getInstance();
	    if(stagedate != null)
	    {
	    	currenttime.setTime(stagedate);
	    }
	    currenttime.add(Calendar.HOUR, h);
	    Date sqldate = new Date((currenttime.getTime()).getTime());
    	this.setBouclier(sqldate);
	}
	public void giveBouclierDay( int d)
	{
		 giveBouclierHour(d*24);
	}
	public boolean isSubOwner(String pseudo)
	{
		return this.getSubOwner().contains(pseudo);
	}
	
	public void removeSubOwner(String deleteMe) {
		this.getSubOwner().remove(deleteMe);
	}
	
	public void removePermAll(String perm)
	{
		this.getPermAll().remove(perm);
	}
	
	public void removePermAllie(String perm)
	{
		this.getPermAllie().remove(perm);
	}
	
	public void addPermAll(String perm)
	{
		this.getPermAll().add(perm);
	}
	
	public void addPermAllie(String perm)
	{
		this.getPermAllie().add(perm);
	}
	
	public void removePermMember(String perm)
	{
		this.getPermMember().remove(perm);
	}
	
	public void addPermMember(String perm)
	{
		this.getPermMember().add(perm);

	}

	public boolean isOwner(String o)
	{
		if(this.getOwner() == null || this.getOwner() == " " || this.getOwner() == "") { return false; }
		return o.toLowerCase().replace(" ", "").equals(this.getOwner().toLowerCase().replace(" ", ""));
	}

	/*public void updateGardien(boolean t) 
	{
		if(this.getIdStage() != 4 && this.getIdStage() != 5)
		{
			if(!RegisterStage.IsWorldStage(this.getIdWorld()))
			{
				return;
			}
			World w = DimensionManager.getWorld(this.getIdWorld());
			if(w == null) {return;}
			Chunk[] All = new Chunk[RegisterStage.getNombreChunkForStage(this.getIdStage())];
			Stage stageprinc = RegisterStage.getStageAtXY(this.getXpos(), this.getZpos(), w);
			if(stageprinc != null)
			{
				int x = 1;
				int z = 1;
				while(RegisterStage.getStageWithChunk(w.getChunkFromChunkCoords(this.getXpos()- x, this.getZpos())) == stageprinc)
				{
					x--;
				}
				x++;
				while(RegisterStage.getStageWithChunk(w.getChunkFromChunkCoords(this.getXpos(), this.getZpos()-z)) == stageprinc)
				{
					z--;
				}
				z++;
				int i = 0;
				while(RegisterStage.getStageWithChunk(w.getChunkFromChunkCoords(this.getXpos(), this.getZpos()+z)) == stageprinc)
				{
					All[i] = w.getChunkFromChunkCoords(this.getXpos(), this.getZpos()+z);
					i++;
					while(RegisterStage.getStageWithChunk(w.getChunkFromChunkCoords(this.getXpos()+x, this.getZpos()+z)) == stageprinc)
					{
						All[i] = w.getChunkFromChunkCoords(this.getXpos() +x, this.getZpos()+z);
						i++;
						x++;
					}
					z++;
				}
				List[] L;
				int entityall = 0;
			
				for(Chunk k:All)
				{
					 	
					try {
					if(k.hasEntities)
					{
						L = k.entityLists;
			
							for(int u =0; u < L.length; u++)
							{
								for(int o =0; o < L[u].size(); o++)
								{
									if((Entity)L[u].get(o) instanceof EntityGardien)
									{
										if(t)
										{
											t = false;
											k.removeEntityAtIndex((Entity)L[u].get(o), u);
										}
										else
										{
											entityall++;
										}
									
									}
								}
							}
						
					}
				} catch(NullPointerException e)
					{
					
					}
				}
				this.SetGuardien(entityall);
			}
		}
	}
	public void updateGardien() {
		
		if(this.getIdStage() != 5 &&  this.getIdStage() != 4)
		{
			updateGardien(false);
		}
	} 
*/
	public boolean isAlly(String e) {
	
		return this.getAlly().contains(e);
	
	}

	public boolean isEnnemy(String e) {
		
		return this.getEnnemy().contains(e);
	}

	public boolean isIn(String e) {
		// TODO Auto-generated method stub
		return this.isOwner(e) | this.isSubOwner(e);
	}

	public void PushAlerte(String value, String[] arg) {

		String argfinal = LocalDate.now().toString().replace("-", "/")+" "+new SimpleDateFormat("HH:mm:ss").format(java.util.Calendar.getInstance().getTime())+"&";
		for(int i = 0; i < arg.length; i++)
		{
			argfinal+= arg[i]+"&";
		}
		
		if(RegisterStage.getPlayer(this.getOwner()) == null)
		{
			main.getbdd().execute("INSERT INTO "+main.getbdd().getStringAlerte()+"(`Pseudo`, `value`, `arg`) VALUES ('"+this.getOwner()+"','"+value+"','"+main.getbdd().SetArrayString(arg)+"')");
		}
		for(String e:this.getSubOwner())
		{
			if(RegisterStage.getPlayer(e) == null)
			{
				main.getbdd().execute("INSERT INTO "+main.getbdd().getStringAlerte()+"(`Pseudo`, `value`, `arg`) VALUES ('"+e+"','"+value+"','"+argfinal+"')");
			}
		}
	}
	

}
