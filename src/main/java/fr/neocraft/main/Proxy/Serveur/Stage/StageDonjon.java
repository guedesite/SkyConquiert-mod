package fr.neocraft.main.Proxy.Serveur.Stage;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Date;

import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardienEvil;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnjEvil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class StageDonjon {

	public EntityGardienEvil boss;
	private Date temps;
	public int X, Z, Id; 
	public World w;
	
	
	public StageDonjon(Stage s, int id)
	{
		this.Id = id;
		X = s.getXpos();
		Z = s.getZpos();
		w = DimensionManager.getWorld(s.getIdWorld());
		Calendar currenttime = Calendar.getInstance();
	    
	    currenttime.add(Calendar.HOUR, 1);
	    temps = new Date((currenttime.getTime()).getTime());
	    boss = new EntityGardienEvil(w);
	    boss.setPosition(X * 16 + 8, 164, Z* 16 + 8);
	    w.spawnEntityInWorld(boss);
	    for(int i = 0; i < w.rand.nextInt(3)+2; i++)
	    {
	    	EntityShopperPnjEvil en1 = new EntityShopperPnjEvil(w);
	    	en1.isDonjon = true;
	    	en1.setPosition(X * 16 + 8, 164, Z* 16 + 8);
	    	w.spawnEntityInWorld(en1);
	    }
	    
	}
	
	public void spawnDonjon()
	{
		try {
			 BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader("assets/donjon/donjon-"+this.Id+".data"));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			  String line = "";
			  int id,x,y,z,meta;
			  
			  int minx = this.X * 16;
			  int minz = this.Z * 16;
			  try {
				  while ((line =br.readLine()) != null) 
				  {
					  try {
						  id = Integer.parseInt(line);
						  x = Integer.parseInt(br.readLine());
						  y = Integer.parseInt(br.readLine());
						  z = Integer.parseInt(br.readLine());
						  meta = Integer.parseInt(br.readLine());
						  w.setBlock(minx+x, y, minz+z, Block.getBlockById(id), meta, 0);
					  } catch(Exception e) {
							
					    }
				  }
				  br.close();
			  } catch(Exception e) {
					
		    }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkTemps()
	{
	    Date date = new Date((Calendar.getInstance().getTime()).getTime());
	    return !(temps.compareTo(date) < 0);
	}
}
