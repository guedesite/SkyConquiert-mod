package fr.neocraft.main.Proxy.Serveur.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import fr.neocraft.main.ServeurManager.DonjonManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;

import com.google.common.collect.Table;
import com.google.common.collect.HashBasedTable;

public class RegisterStage {
	public static Map<Integer, Stage> Stage = new HashMap<Integer, Stage>();
	public static Map<Integer, Stage> OldStage = new HashMap<Integer, Stage>();
	public static final bdd bdd = main.getbdd();
	public static Map<Integer, Table> AllWorld = new HashMap<Integer, Table>();
	public static List<Integer> ItemDenySpecial = new ArrayList<Integer>();
	public static int nbStage = 0;
	
	private static ArrayList<Integer> Stage7 = new  ArrayList<Integer>();

	
	public static int MaxSubOwner = 5;
	public static void Register()
	{
		
		//temp
		

		int id = bdd.GetFreeId();
		try {
			Stage = new HashMap<Integer, Stage>();
			OldStage = new HashMap<Integer, Stage>();
			ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringStage()+" ORDER BY `WorldId` ASC", id);
			try {
				if(result != null)
				{
					LogStage("START REGISTER ALL STAGE");
					int i = 0;
					int lastid = -1;
					int[] contain = new int[] {-1};
					boolean stat = false;
					
					while ( result.next() ) 
					{
						if(Stage.get(result.getInt("id2")) == null)
						{
							Stage.put(result.getInt("id2"), new Stage(result.getInt("WorldId"), result.getInt("StageId"), result.getInt("id2") ,result.getString("owner"), bdd.GetArrayListString(result.getString("subowner")),  result.getInt("XPos"), result.getInt("ZPos"), bdd.GetArrayListString(result.getString("PermAll")), bdd.GetArrayListString(result.getString("PermMember")), bdd.GetArrayListString(result.getString("PermAllie")), result.getInt("isUse"), result.getInt("x"), result.getInt("y"), result.getInt("z"), result.getInt("hasHome"), result.getInt("bank"), result.getString("name"), result.getLong("bouclier"), bdd.GetArrayListString(result.getString("Ally")),bdd.GetArrayListString( result.getString("Ennemy")), result.getInt("lvl")));
							if(nbStage < result.getInt("id2"))
							{
								nbStage = result.getInt("id2");
							}
							if(result.getInt("StageId") == 7)
							{
								Stage7.add(result.getInt("id2"));
							}
						}
						
					
					}
					LogStage("INJECTED "+(nbStage)+" STAGE");
				}
				else
				{
					System.err.println("REGISTER STAGE LA CLASSE STAGE EST VIDE ! " + bdd.getStringStage());
				}
			} catch (SQLException e) {
				e.printStackTrace();
				bdd.erreur("REGISTER STAGE", e, "SELECT * FROM "+bdd.getStringStage());
			}

			
			OldStage = new HashMap<Integer,Stage>(Stage);
			
			Iterator it = Stage.values().iterator();
			ArrayList<String> ps = new  ArrayList<String>();
			while(it.hasNext())
			{
				Stage s = (Stage)it.next();
				if(s.getIdStage() == 6)
				{
					DonjonManager.id6.add(s);
				} else if(s.IsUse()) {
					if(!ps.contains(s.getOwner()) && s.getOwner() != null)
					{
						ps.add(s.getOwner());
						StageXpManager.UpdateXpStage(s.getOwner());
						
					}
				}
			}
			
			
			
		} catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("ERREUR LORS DU CHARGEMENT");
			System.err.println("NOUVELLE TENTATIVE DANS 5 SEC !");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
	
				e1.printStackTrace();
			}
			Register();
		}
		bdd.CloseFreeId(id);
	}
	
	private static int[] addContain(int int1, int[] contain) {
		int[] temp = new int[contain.length+1];
		int i =0;
		for(int e:contain)
		{
			temp[i] = e;
			i++;
		}
		temp[i] = int1;
		return temp;
	}

	
	private static boolean DontContain(int int1, int[] contain) {
		for(int e:contain)
		{
			if(e == int1)
			{
				return false;
			}
		}
		return true;
	}


	public static void RegisterChunk() {
		int id = bdd.GetFreeId();
		int Do = -1;
		try {
			ResultSet result;
			
			LogStage("START REGISTER ALL Chunk");
			AllWorld = new HashMap<Integer, Table>();
			result = bdd.query("SELECT * FROM "+bdd.getStringChunk()+" ORDER BY `IdWorld` ASC", id);
			try {
				if(result != null)
				{
					int lastw = -1;
					Table<Integer, Integer, Stage> Chunk = null;
					while ( result.next() ) 
					{
						Do = result.getInt("IdStage");
						if(lastw != result.getInt("IdWorld"))
						{

							if(lastw != -1)
							{
								AllWorld.put(lastw, Chunk);
							}
							Chunk = HashBasedTable.create();
							lastw = result.getInt("IdWorld");
						}
						Chunk.put(result.getInt("PosX"), result.getInt("PosZ"), Stage.get(result.getInt("IdStage")));
					}
					AllWorld.put(lastw, Chunk);
				}
				else
				{
					System.err.println("REGISTER CHUNK LA CLASSE CHUNK EST VIDE ! "+ bdd.getStringChunk());
				}
			} catch (SQLException e) {
				bdd.erreur("REGISTER Chunk", e, "SELECT * FROM "+bdd.getStringChunk());
				System.out.println(Do);
			}
		} catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(Do);
			System.err.println("ERREUR LORS DU CHARGEMENT");
			System.err.println("NOUVELLE TENTATIVE DANS 5 SEC !");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
	
				e1.printStackTrace();
			}
			RegisterChunk();
		}
		bdd.CloseFreeId(id);
	}
	
	
	public static void RegisterItemDeny()
	{
		int id = bdd.GetFreeId();
		try {
			ItemDenySpecial = new ArrayList<Integer>();
			ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringDenySpecial(), id);
			try {
				if(result != null)
				{
					LogStage("START REGISTER ALL DENY Special Block ");
					while ( result.next() ) 
					{
						ItemDenySpecial.add(result.getInt("idBlock"));
					}
				}
				else
				{
					System.err.println("REGISTER CHUNK LA CLASSE BLOCK EST VIDE ! " + bdd.getStringChunk());
				}
			} catch (SQLException e) {
				bdd.erreur("REGISTER Chunk", e, "SELECT * FROM "+bdd.getStringChunk());
			}
		} catch(Exception e)
		{
			e.printStackTrace();
			System.err.println("ERREUR LORS DU CHARGEMENT");
			System.err.println("NOUVELLE TENTATIVE DANS 5 SEC !");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			RegisterItemDeny();
		}
		bdd.CloseFreeId(id);
	}
	
	public static Stage getNoUseStage()
	{
		Iterator it = Stage.values().iterator();
		Stage e;
	    while (it.hasNext()) {
	        e = ((Stage)it.next());
	       if(!e.IsUse() && e.getIdStage() == 0)
	       {
	    	   return e;
	       }

	    }
	    return null;
		
	}
	public static boolean IsWorldStage(int id)
	{
		return AllWorld.get(id) != null;
	}
	public static Stage getStageWithChunk(Chunk c)
	{
		return getStageAtXY(c.xPosition, c.zPosition, c.worldObj);
	}
	
	public static Stage getFirstStagePlayer(PlayerStats s)
	{
		for(int d: s.idStage)
		{
			return RegisterStage.getStageWithId(d);
		}
		
		return null;
	}
	
	public static int getFirstStageIdPlayer(PlayerStats s)
	{
		for(int d: s.idStage)
		{
			return RegisterStage.getStageWithId(d).getId();
		}
		
		return -1;
	}
	
	public static Stage[] getStageArrayWithIdArray(ArrayList<Integer> idStage)
	{
		if(!idStage.isEmpty())
		{
			Stage[] stage = new Stage[idStage.size()];
			for(int i = 0; i< idStage.size(); i++)
			{
				stage[i] = getStageWithId(idStage.get(i));
			}
			return stage;
		} 
		return null;
	}
	public static Stage getStageAtXY(int x, int z, World world)
	{
		
			return getStageAtXY( x,  z, world.provider.dimensionId);
	}
	
	public static String getBukkitNameFormat(String name)
	{
		PlayerStats s = PlayerStats.get(getPlayer(name));
		for(int d: s.idStage)
		{
			return RegisterStage.getStageWithId(d).getName().replace("&", "");
		}
		
		return EnumChatFormatting.ITALIC + "NEW";
	}
	
	public static Stage getStageAtXY(int x, int z, int world)
	{
		Table d = AllWorld.get(world);
		if( d != null)
		{
			return (Stage)d.get(x, z);
		}
		return null;
	}
	
	public static Stage getStageWithId(int id)
	{
		return Stage.get(id);
	
	}

	public static void LogStage(String i)
	{
		System.out.println("[NEOCRAFT REGISTER STAGE] " + i);
	}
	
	public static boolean ExistInStringArray(String[] txt, String name)
	{
		if(txt != null)
		{
			if(Arrays.asList(txt).contains(name.replace(" ", "")))
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean ChanceOf(int per)
	{
	    return (int)(Math.random() * (100-1)) + 1 < per;
	}

	public static ArrayList<Integer> getStageIdWithPlayer(String name) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		Iterator it = Stage.values().iterator();
		Stage e;
	    while (it.hasNext()) {
	    	e = ((Stage)it.next());
	       if(e.isIn(name))
	       {
	    	  temp.add(e.getId());
	       }

	    }
	    return temp;
		
	}
	public static ArrayList<Stage> getStageWithPlayer(String name) {
		ArrayList<Stage> temp = new ArrayList<Stage>();
		Iterator it = Stage.values().iterator();
		Stage e;
	    while (it.hasNext()) {
	    	e = ((Stage)it.next());
	       if(e.isIn(name))
	       {
	    	  temp.add(e);
	       }

	    }
	    return temp;
	}
	


	public static EntityPlayer getPlayer(String name){


	    ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();

	    while (li.hasNext()){

	        EntityPlayer p = (EntityPlayer) li.next();
	        if(p.getGameProfile().getName().equals(name)){

	            return p;

	        }

	    }
	    return null;

	}
	public static int getChunkLongeurByStage(int i)
	{
		if(i == 0)
		{
			return 16;
		} else if(i == 1)
		{
			return 32;
		} else if(i == 2)
		{
			return 48;
		} else
		{
			return 112;
		} 
		
	}
	public static String getPermForStage(int i)
	{
		if(i == 5)
		{
			return "attaque&enderpearl&interact&container&";
		}else if(i == 4)
		{
			return "attaque&enderpearl&interact&container&";
		}else {
			return "enderpearl&attaque&";
		}
	}
	
	public static String getPermForStageMember(int i)
	{
			return "enderpearl&attaque&bonemeal&block&container&harvest&interact&";
	}


	
	public static int getIntUrl(final String req)
	{
		URL url;
		try {
			url = new URL(req);
			InputStream is = url.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );

			String line = null;
			int i = 0;
			while( ( line = reader.readLine() ) != null )  {
			   i = Integer.parseInt(line.replace(" ", ""));
			}
			reader.close();
			return i;
		} catch (Exception e) {
			return 0;
		}
	}

	

	public static int getBankForStage(int i) {
		switch(i)
		{
			case 0: return 1000;
			case 1: return 250000;
			case 2: return 1000000;
			case 3: return 5000000;
			default: return 0;
		}
	}
	public static int getNombreChunkForStage(int i)
	{
		switch(i)
		{
			case 0: return 1;
			case 1: return 4;
			case 2: return 9;
			case 3: return 49;
			default: return 0;
		}
	}
	public static boolean IsAllowedBlockSpecial(int id) {
		for(int i = 0; i < ItemDenySpecial.size();i++)
		{
			if(ItemDenySpecial.get(i).equals(id))	
			{
				return false;
			}
		}
		return true;
	}
	
	
	public static ArrayList<String> getDiscordListBalTop(int index) 
	{
		return getListBalTop(index) ;
	}
	
	public static ArrayList<String> getListBalTop(int param) 
	{
		param += 1;
		ArrayList<String> list = new ArrayList<String>();
		int max = param*10;
		int base = (param-1)*10;
	
		
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT pseudo,money FROM "+bdd.getStringPlayer()+" ORDER BY `money` DESC", id);
		if(result != null)
		{
			try {
				int index = 0;
				int index2 = 1;
				while(result.next())
				{
					if(index <= base)
					{
						
					}
					else if(index > max)
					{
						list.add("> !baltop "+(param+1));
						return list;
						
					} else {
						list.add((base+index2)+" > "+result.getString("pseudo")+" > "+result.getInt("money") +"$");
						index2++;
					}
					index++;
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static ArrayList<String> getDiscordListStageTop(int index) 
	{
		return getListStageTop(index) ;
	}
	
	public static ArrayList<String> getListStageTop(int param) 
	{
		param += 1;
		ArrayList<String> list = new ArrayList<String>();
		int max = param*10;
		int base = (param-1)*10;
	
		
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT owner,lvl FROM "+bdd.getStringStage()+" ORDER BY `lvl` DESC", id);
		if(result != null)
		{
			try {
				int index = 0;
				int index2 = 1;
				ArrayList<String> ever = new ArrayList<String>();
				while(result.next())
				{
					if(!ever.contains(result.getString("owner")))
					{
						if(index <= base)
						{
							
						}
						else if(index > max)
						{
							list.add("> !stagetop "+(param+1));
							return list;
							
						} else {
							ever.add(result.getString("owner"));
							list.add((base+index2)+" > "+result.getString("owner")+" > lvl"+result.getInt("lvl"));
							index2++;
						}
						index++;
					}
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}


	public static boolean canMobSpawnInStage(int id)
	{
		switch(id)
		{
			case 5:
				return false;
			case 6:
				return false;
			case 7:
				return false;
			default:
				return true;
		}
	}
	
	public static void reloadMinage() {
		//min: 73
		//max: 100
		//<  >
		for(int i:Stage7)
		{
			Stage s = getStageWithId(i);
			World w = DimensionManager.getWorld(s.getIdWorld());
			Chunk c = w.getChunkFromChunkCoords(s.getXpos(), s.getZpos());
			if(c.hasEntities && c.entityLists !=null)
			{
				for(List<Entity> e:c.entityLists)
				{
					for(Entity e2:e)
					{
						if(e2.posY >= 73 && e2.posY <= 100)
						{
							e2.setPosition(e2.posX, 101, e2.posZ);
						}
					}
				}
			}
			
			int miny = 73;
			int minx = s.getXpos() * 16;
			int minz = s.getZpos() * 16;
			StageMineraiGen[] Ore = new StageMineraiGen[9];
			Ore[0] = new StageMineraiGen(Blocks.coal_ore, 1, 400, true);
	    	Ore[1] = new StageMineraiGen(Blocks.iron_ore, 400, 700, true);
	    	Ore[2] = new StageMineraiGen(Blocks.gold_ore, 700, 900, true);
	    	Ore[3] = new StageMineraiGen(Blocks.diamond_ore, 900, 920, true);
	    	Ore[4] = new StageMineraiGen(Blocks.lapis_ore, 920, 930, true);
	    	Ore[5] = new StageMineraiGen(Blocks.redstone_ore, 930, 1000, true);
	    	Ore[6] = new StageMineraiGen(BlockMod.Mythrile_Ore, 1000, 1020, true);
	    	Ore[7] = new StageMineraiGen(BlockMod.Titane_Ore, 1030, 1035, false);
	    	Ore[8] = new StageMineraiGen(Blocks.emerald_ore, 1030, 1032, false);
			for(int y = 0; y < 29; y++)
			{
				for(int x = 0; x < 16; x++)
				{
					for(int z = 0; z < 16; z++)
					{
						w.setBlock(minx+x, miny+y, minz+z, Blocks.stone);
					}
				}
			}
			minx +=1;
			minz +=1;
			for(int y = 0; y < 29; y++)
			{
				for(int x = 0; x < 14; x++)
				{
					for(int z = 0; z < 14; z++)
					{
						setBlockGen(w, minx+ x, miny+ y, minz+ z, true);
					}
				}
			}
		}

	}
	private static StageMineraiGen[] Ore = new StageMineraiGen[] {
		new StageMineraiGen(Blocks.coal_ore, 1, 400, true),
		new StageMineraiGen(Blocks.iron_ore, 400, 700, true),
		new StageMineraiGen(Blocks.gold_ore, 700, 900, true),
		new StageMineraiGen(Blocks.diamond_ore, 900, 920, true), 
		new StageMineraiGen(Blocks.lapis_ore, 920, 930, true),
		new StageMineraiGen(Blocks.redstone_ore, 930, 1000, true),
		new StageMineraiGen(BlockMod.Mythrile_Ore, 1000, 1030, true),
		new StageMineraiGen(BlockMod.Titane_Ore, 1030, 1035, false)
	};
	
	private static void setBlockGen(World w, int x, int y, int z, boolean b) {
		
		 int r= w.rand.nextInt((15000) + 1);
		 boolean m = false;
			 for(StageMineraiGen e:Ore)
			 {
				 if(e.Can(r))
				 {
					 m = true;
					 w.setBlock(x, y, z, e.block);
					 if(e.isextanded)
					 {
						 if(w.rand.nextInt((2-1) + 1)+1 == 1)
						 {
							 if(w.getBlock(x+1, y, z)== Blocks.stone || w.getBlock(x+1, y, z)== Blocks.cobblestone || w.getBlock(x+1, y, z)==BlockMod.FakeCobble)
							 {
								 w.setBlock(x + 1, y, z, e.block);
							 }
						 }
						 if(w.rand.nextInt((2-1) + 1)+1 == 1)
						 {
							 if(w.getBlock(x-1, y, z)== Blocks.stone || w.getBlock(x-1, y, z)== Blocks.cobblestone || w.getBlock(x-1, y, z)==BlockMod.FakeCobble)
							 {
								 w.setBlock(x - 1, y, z, e.block);
							 }
						 }
						 if(w.rand.nextInt((2-1) + 1)+1 == 1)
						 {
							 if(w.getBlock(x, y+1, z)== Blocks.stone || w.getBlock(x, y+1, z)== Blocks.cobblestone || w.getBlock(x, y+1, z)==BlockMod.FakeCobble)
							 {
								 w.setBlock(x, y+1,  z, e.block);
							 }
						 }
						 if(w.rand.nextInt((2-1) + 1)+1 == 1)
						 {
							 if(w.getBlock(x, y-1, z)== Blocks.stone || w.getBlock(x, y-1, z)== Blocks.cobblestone || w.getBlock(x, y-1, z)==BlockMod.FakeCobble)
							 {
								 w.setBlock(x , y-1, z, e.block);
							 }
						 }
						 if(w.rand.nextInt((2-1) + 1)+1 == 1)
						 {
							 if(w.getBlock(x, y, z+1)== Blocks.stone || w.getBlock(x, y, z+1)== Blocks.cobblestone || w.getBlock(x, y, z+1)==BlockMod.FakeCobble)
							 {
								 w.setBlock(x , y, z+1, e.block);
							 }
						 }
						 if(w.rand.nextInt((2-1) + 1)+1 == 1)
						 {
							 if(w.getBlock(x, y, z-1)== Blocks.stone || w.getBlock(x, y, z-1)== Blocks.cobblestone || w.getBlock(x, y, z-1)==BlockMod.FakeCobble)
							 {
								 w.setBlock(x, y, z-1, e.block);
							 }
						 }
					 }
				 }
		 }
			 if(b & !m)
			 {
				 setBlockGen(w, x, y, z, false);
			 }
	}
	
}
