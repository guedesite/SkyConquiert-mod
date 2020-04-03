package fr.neocraft.main.ServeurManager;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class StageRepair {
	// 27/09/19
	
	
	public void startCheckMultipleStage()
	{
		bdd bdd = main.getbdd();
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringChunk(), id);
		int c = 0;
		if(result != null)
		{
			try {
				ArrayList<DataStageIdXZW> StageToRegister = new ArrayList<DataStageIdXZW>();
				while(result.next())
				{
					
						int x = result.getInt("PosX");
						int z = result.getInt("PosZ");
						int idstage = result.getInt("IdStage");
						int w = result.getInt("IdWorld");
						Stage s = RegisterStage.getStageWithId(idstage);
						int idd;
						if(s != null)
						{
							idd = s.getIdStage();
						}
						else 
						{
							bdd.execute("DELETE FROM "+bdd.getStringChunk()+" WHERE id="+result.getInt("id"));
							continue;
						}
						boolean flag = true;
						for(int i = 0; i < StageToRegister.size(); i++)
						{
							DataStageIdXZW r = StageToRegister.get(i);
							if(r.X == x && r.Z == z && r.id == idstage && r.World == w)
							{
								if(!r.check())
								{
									bdd.execute("DELETE FROM "+bdd.getStringChunk()+" WHERE id="+result.getInt("id"));
									c++;
									flag = false;
								}
							}
						}
						if(flag)
						{
							
							StageToRegister.add(new DataStageIdXZW(idstage, x, z , w, idd));
							
						}
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}else 
		{
			System.out.println("null");
		}
		System.out.println("do Chunk: "+c); 
		bdd.CloseFreeId(id);
		id = bdd.GetFreeId();
		 result = bdd.query("SELECT * FROM "+bdd.getStringStage(), id);
		c = 0;
		if(result != null)
		{
			try {
				ArrayList<DataStageIdXZW> StageToRegister = new ArrayList<DataStageIdXZW>();
				while(result.next())
				{
					int x = result.getInt("XPos");
					int z = result.getInt("ZPos");
					int idstage = result.getInt("StageId");
					int w = result.getInt("WorldId");
					
					
					Stage s = RegisterStage.getStageWithId(idstage);
					int idd;
					if(s != null)
					{
						idd = s.getIdStage();
					}
					else 
					{
						bdd.execute("DELETE FROM "+bdd.getStringStage()+" WHERE id="+result.getInt("id"));
						continue;
					}
					boolean flag = true;
					for(int i = 0; i < StageToRegister.size(); i++)
					{
						DataStageIdXZW r = StageToRegister.get(i);
						if(r.X == x && r.Z == z && r.id == idstage && r.World == w)
						{
							if(!r.check())
							{
								bdd.execute("DELETE FROM "+bdd.getStringStage()+" WHERE id="+result.getInt("id"));
								c++;
								flag = false;
							}
						}
					}
					if(flag)
					{
						
						StageToRegister.add(new DataStageIdXZW(idstage, x, z , w, idd));
						
					}
					
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}else 
		{
			System.out.println("null");
		}
		System.out.println("do Stage: "+c); 
		bdd.CloseFreeId(id);
	}
	
	public void StartCheckNoChunkAndStage()
	{
		bdd bdd = main.getbdd();
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringChunk(), id);
		int c = 0;
		if(result != null)
		{
			try {

				while(result.next())
				{
					if(!bdd.Exist("SELECT * FROM "+bdd.getStringStage()+" WHERE id2="+result.getInt("IdStage")+" AND XPos="+result.getInt("PosX")+" AND ZPos="+result.getInt("PosZ")+" AND WorldId="+result.getInt("IdWorld")))
					{
						bdd.execute("DELETE FROM "+bdd.getStringChunk()+" WHERE id="+result.getInt("id"));
						c++;
					}
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}else 
		{
			System.out.println("null");
		}
		System.out.println("do chunk: "+c); 
		bdd.CloseFreeId(id);
		
		id = bdd.GetFreeId();
		result = bdd.query("SELECT * FROM "+bdd.getStringStage(), id);
		c = 0;
		if(result != null)
		{
			try {

				while(result.next())
				{
					if(!bdd.Exist("SELECT * FROM "+bdd.getStringChunk()+" WHERE IdStage="+result.getInt("id2")+" AND PosX="+result.getInt("XPos")+" AND PosZ="+result.getInt("ZPos")+" AND IdWorld="+result.getInt("WorldId")))
					{
						bdd.execute("DELETE FROM "+bdd.getStringStage()+" WHERE id="+result.getInt("id"));
						c++;
					}
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}else 
		{
			System.out.println("null");
		}
		System.out.println("do Stage: "+c); 
		bdd.CloseFreeId(id);
			
	}
	
	public void startCheckChunkStage()
	{
		bdd bdd = main.getbdd();
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringChunk(), id);
		int c = 0;
		if(result != null)
		{
			try {
				ArrayList<DataStageXZ> StageToRegister = new ArrayList<DataStageXZ>();
				while(result.next())
				{

					
					int x = result.getInt("PosX") - 1;
					int z = result.getInt("PosZ") - 1;
					int idstage = result.getInt("IdStage");
					World w = DimensionManager.getWorld(result.getInt("IdWorld"));
					Stage stage = RegisterStage.getStageWithId(idstage);
					if(stage.getIdStage() == 1 || stage.getIdStage() == 2 || stage.getIdStage() == 3)
					{
						
						for(int x2 = 0; x2 < 3; x2++ )
						{
							for(int z2 = 0; z2 < 3; z2++ )
							{
								Stage s = RegisterStage.getStageAtXY(x+x2, z+z2, w);
								if(s == null && HaveBlock(w, x+x2,z+z2))
								{
									boolean flag = true;
									for(int i = 0; i < StageToRegister.size(); i++)
									{
										DataStageXZ r = StageToRegister.get(i);
										if(r.X == (x+x2) && r.Z == (z+z2) && r.stage.getIdWorld() == w.provider.dimensionId)
										{
											flag = false;
										}
									}
									if(flag)
									{
										StageToRegister.add(new DataStageXZ(stage,x+x2, z+z2));
										c++;
									}
								}
							}
						}
					}
				}
				for(int i = 0; i < StageToRegister.size(); i++)
				{
					DataStageXZ r = StageToRegister.get(i);
					bdd.execute("INSERT INTO "+bdd.getStringChunk()+"(`PosX`, `PosZ`, `IdStage`, `IdWorld`) VALUES ('"+r.X+"','"+r.Z+"','"+r.stage.getId()+"', '"+r.stage.getIdWorld()+"')");
		            bdd.execute("INSERT INTO "+bdd.getStringStage()+"(`id2`, `WorldId`, `StageId`, `XPos`, `ZPos`, `PermAll`, `PermMember`, `isUse`,`x`,`y`,`z`, `hasHome`,`name`, `bank`) VALUES ('"+r.stage.getId()+"','"+r.stage.getIdWorld()+"','"+r.stage.getIdStage()+"','"+r.X+"','"+r.Z+"','"+RegisterStage.getPermForStage(r.stage.getIdStage())+"','"+RegisterStage.getPermForStageMember(r.stage.getIdStage())+"','0','"+r.stage.getX()+"','"+r.stage.getY()+"','"+r.stage.getZ()+"','1', '"+r.stage.getName()+"', '"+r.stage.getBank()+"')");
				}
				System.out.println("do: "+c); 
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		} else 
		{
			System.out.println("null");
		}
		bdd.CloseFreeId(id);
	}
	
	
	public void checkMultipleStage0()
	{
		bdd bdd = main.getbdd();
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringStage(), id);
		if(result != null)
		{
			try {
				
				ArrayList<DataStageXZCount> finale = new ArrayList<DataStageXZCount>();
				while(result.next())
				{
					if(result.getInt("StageId") == 0)
					{
						int x = result.getInt("XPos") - 1;
						int z = result.getInt("ZPos") - 1;
						
						int idstage = result.getInt("id2");
						World w = DimensionManager.getWorld(result.getInt("WorldId"));
						Stage stage = RegisterStage.getStageWithId(idstage);
						
						ArrayList<DataStageXZCount> ar = new ArrayList<DataStageXZCount>();
						
						for(int x2 = 0; x2 < 3; x2++ )
						{
							for(int z2 = 0; z2 < 3; z2++ )
							{
								Stage s = RegisterStage.getStageAtXY(x+x2, z+z2, w);
								if(s != null && HaveBlock(w, x+x2,z+z2))
								{
									ar.add(new DataStageXZCount(x+x2, z+z2, countBlock(w, x+x2, z+z2),w));
								}
							}
						}
						if(ar.size() > 1)
						{
							int pg = 0;
							
							for(DataStageXZCount e:ar)
							{
								if(pg < e.count)
								{
									pg = e.count;
								}
							}
							for(DataStageXZCount e:ar)
							{
								if(pg != e.count)
								{
									finale.add(e);
								}
							}
						}
					}
				}
				int c = 0;
				for(DataStageXZCount e:finale)
				{
				
					bdd.execute("DELETE FROM "+bdd.getStringStage()+" WHERE `XPos`="+e.X+" AND `ZPos`="+e.Z+" AND `WorldId`="+e.world.provider.dimensionId);
					bdd.execute("DELETE FROM "+bdd.getStringChunk()+" WHERE `PosX`="+e.X+" AND `PosZ`="+e.Z+" AND `IdWorld`="+e.world.provider.dimensionId);
					c++;
				}
				System.out.println("do: "+c); 
				

			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		bdd.CloseFreeId(id);
	}
	
	public void startRemoveSpawner()
	{
		int c = 0;
		World w = DimensionManager.getWorld(200);
		for(int y = 0; y < 255; y++)
		{
			for(int x = -1500; x < 1500; x++)
			{
				for(int z = -1500; z < 1500; z++)
				{
					if(w.getBlock(x, y, z) == Blocks.mob_spawner)
					{
						w.setBlock(x, y, z, Blocks.air);
						c++;
					}
				}
			}
		}
		System.out.println("do: "+c);
	}
	
	public static int countBlock(World w, int X2, int Z2)
	{
		int minx = X2 * 16;
		int minz = Z2 * 16;
		int miny = 0;
		int nb= 0;
		for(int y = 0; y < 255; y++)
		{
			for(int x = 0; x < 16; x++)
			{
				for(int z = 0; z < 16; z++)
				{
					if(w.getBlock(minx+x, miny+y, minz+z) != Blocks.air)
					{
						nb++;
					}
				}
			}
		}
		return nb;
	}
	
	public static int removeBlock(World w, int X2, int Z2)
	{
		int minx = X2 * 16;
		int minz = Z2 * 16;
		int miny = 0;
		int nb= 0;
		for(int y = 0; y < 255; y++)
		{
			for(int x = 0; x < 16; x++)
			{
				for(int z = 0; z < 16; z++)
				{
					if(w.getBlock(minx+x, miny+y, minz+z) != Blocks.air)
					{
						w.setBlock(minx+x, miny+y, minz+z, Blocks.air);
					}
				}
			}
		}
		return nb;
	}
	
	public static boolean HaveBlock(World w, int X2, int Z2)
	{
		int minx = X2 * 16;
		int minz = Z2 * 16;
		int miny = 0;
		for(int y = 0; y < 255; y++)
		{
			for(int x = 0; x < 16; x++)
			{
				for(int z = 0; z < 16; z++)
				{
					if(w.getBlock(minx+x, miny+y, minz+z) != Blocks.air)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public class DataStageXZ {
		public Stage stage;
		public int X;
		public int Z;
		
		public DataStageXZ(Stage s, int X2, int Z2)
		{
			stage = s;
			X = X2;
			Z = Z2;
		}
	}
	
	
	
	public class DataStageIdXZW {
		public int id;
		public int X;
		public int Z;
		public int World;
		public int idd;
		public int maxid;
		public int countid = 0;
		boolean ismax = false;
		public DataStageIdXZW(int s, int X2, int Z2, int w, int ids)
		{
			id = s;
			X = X2;
			Z = Z2;
			World = w;
			idd = ids;
			switch(ids)
			{
				case 0:
					maxid = 1;
					break;
				case 1:
					maxid = 4;
					break;
				case 2:
					maxid = 9;
					break;
				case 3:
					maxid = 49;
					break;
				default:
					ismax = true;
					break;
			}
		}
		public boolean check() {
			if(!ismax)
			{
				if(countid < maxid)
				{
					countid++;
					return true;
				}
				else {
					return false;
				}
			}
			
			else
			{
				return true;
			}
		}
	}
	
	public class DataStageXZCount {
		public int X;
		public int Z;
		public World world;
		public int count;
		public DataStageXZCount(int x, int z, int c, World w)
		{
			X =x;
			Z = z;
			world = w;
			count = c;
		}
	}
	
}
