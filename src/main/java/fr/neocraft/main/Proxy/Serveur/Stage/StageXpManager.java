package fr.neocraft.main.Proxy.Serveur.Stage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.Recompense.RargentStage;
import fr.neocraft.main.ServeurManager.StageRepair.DataStageXZ;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class StageXpManager {

	private static ArrayList<StageLvl> stageLvl;
	private static HashMap<Integer, Integer> BlockXp;
	public static void register() {
		stageLvl= new ArrayList<StageLvl>();
		BlockXp = new HashMap<Integer, Integer>();
		for(int i = 0; i  < 100; i++)
		{
			stageLvl.add(new StageLvl(i+1,(500+150*i*i), new RargentStage((500+300*i*i))));
		}
		
		bdd bdd = main.getbdd();
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringXpBlock(), id);
		if(result != null)
		{
			try {
	
				while(result.next())
				{
					BlockXp.put(result.getInt("BlockId"),result.getInt("Xp"));
				}
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		bdd.CloseFreeId(id);
		
	}
	
	public static void UpdateXpStage(String pseudo)
	{
		ArrayList<Stage> s = RegisterStage.getStageWithPlayer(pseudo);
		if(s != null)
		{
			UpdateXpStage(s);
		} else {
			return;
		}
	}
	
	public static void UpdateXpStage(ArrayList<Stage> stage)
	{
		int xp = 0;
		Stage Stage0 = null;
		for(Stage s:stage)
		{
			xp += CalculXpStage(s.getId());
			if(s.getIdStage() == 0)
			{
				Stage0 = s;
			}
		}

		if(Stage0 != null)
		{
			for(int i = 0; i < stageLvl.size(); i++)
			{
				if(stageLvl.get(i).getLvl() == (Stage0.getLvl()+1))
				{
					if(stageLvl.get(i).getXp() <= xp)
					{
						Stage0.setLvl(Stage0.getLvl()+1);
						for(Stage s:stage)
						{
							s.setLvl(Stage0.getLvl());
						}
						stageLvl.get(i).getRecompense().PushStage(Stage0);
						
					} else {
						return;
					}
				}
			}
		}
	}
	
	private static int CalculXpStage(int idStage)
	{
		bdd bdd = main.getbdd();
		int xp = 0;
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringChunk()+" WHERE IdStage="+idStage, id);
		if(result != null)
		{
			try {
	
				while(result.next())
				{

					if(result.getInt("IdWorld") != 0)
					{
						int minx = result.getInt("PosX")*16;
						int minz = result.getInt("PosZ")*16;
						int miny = 0;
						World w = DimensionManager.getWorld(result.getInt("IdWorld"));
						
						if(w != null)
						{
							for(int y = 0; y < 256; y++)
							{
								for(int x = 0; x < 16; x++)
								{
									for(int z = 0; z < 16; z++)
									{
										Block b = w.getBlock(minx+x, miny+y, minz+z);
										if(b != Blocks.air)
										{
											if(BlockXp.containsKey(Block.getIdFromBlock(b)))
											{
												xp += BlockXp.get(Block.getIdFromBlock(b));
											} else {
												xp++;
											}
										}
									}
								}
							}
						}
					}
				}
				
			} catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		bdd.CloseFreeId(id);
		return xp;
	}
	
	
	
	
}
