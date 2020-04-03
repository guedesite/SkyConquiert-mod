package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.neocraft.main.main;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import fr.neocraft.main.Proxy.Serveur.Stage.StageMineraiGen;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class CommandStage2Gen extends CommandBase {
	
	private static fr.neocraft.main.bdd bdd = main.getbdd();
	private StageMineraiGen[] Ore = new StageMineraiGen[11]; 
	private StageMineraiGen[] OreNether = new StageMineraiGen[1]; 
//	private StageFishGen[] FishWater = new StageFishGen[4]; 
	 public static int nbjour = 1;
	public CommandStage2Gen()
	{
		Ore[0] = new StageMineraiGen(Blocks.coal_ore, 1, 400, true);
    	Ore[1] = new StageMineraiGen(Blocks.iron_ore, 400, 700, true);
    	Ore[2] = new StageMineraiGen(Blocks.gold_ore, 700, 900, true);
    	Ore[3] = new StageMineraiGen(Blocks.diamond_ore, 900, 950, true);
    	Ore[4] = new StageMineraiGen(Blocks.lapis_ore, 1000, 1200, true);
    	Ore[5] = new StageMineraiGen(Blocks.redstone_ore, 1200, 1400, true);
    	Ore[6] = new StageMineraiGen(Blocks.emerald_ore, 1400, 1420, false);
    	Ore[7] = new StageMineraiGen(BlockMod.Mythrile_Ore, 1420, 1450, true);
    	Ore[8] = new StageMineraiGen(BlockMod.Titane_Ore, 1450, 1460, false);
    	Ore[9] = new StageMineraiGen(BlockMod.Silithium_Ore, 1460, 1464, false);
    	Ore[10] = new StageMineraiGen(BlockMod.NeoDium_Ore, 1464,1465, false);
    	
    	OreNether[0] = new StageMineraiGen(Blocks.quartz_ore, 1, 400, true);
    	
    /*	FishWater[0] = new StageFishGen(new com.tmtravlr.jaff.entities.EntityFishCod(null), 1, 10);
    	FishWater[1] = new StageFishGen(new com.tmtravlr.jaff.entities.EntityFishSalmon(null), 10, 15);
    	FishWater[2] = new StageFishGen(new com.tmtravlr.jaff.entities.EntityFishClownfish(null), 15, 18);
    	FishWater[3] = new StageFishGen(new com.tmtravlr.jaff.entities.EntityFishPufferfish(null), 18, 20); */
	}
	
    @Override
    public String getCommandName() {
        return "Stage2Gen";
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
    	return "";
    }
    
    /**
     * Return the required permission level for this command.
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
    	return true;
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 3;
    }

    @Override
    public void processCommand(ICommandSender sender,final String[] params) {
    	
				if(nbjour != 6)
				{
					nbjour++;
				}
				else
				{
					nbjour = 1;
				}
				Stage stage; 
				int id = bdd.GetFreeId();
    			ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringChunk(), id);
    			if(result != null)
    			{
    				try {

						while ( result.next() ) 
						{
							stage = RegisterStage.getStageWithId(result.getInt("IdStage"));
							if(stage != null)
							{
								
									int minx = result.getInt("PosX") * 16;
									int minz = result.getInt("PosZ") * 16;
									int miny = 1;
									World w = DimensionManager.getWorld(result.getInt("IdWorld"));
									Block b;
									if(w != null)
									{
										 boolean boolday = stage.getIdStage() == 3 & nbjour == 5;
										for(int y = 0; y < 150; y++)
										{
											for(int x = 0; x < 16; x++)
											{
												for(int z = 0; z < 16; z++)
												{
													b = w.getBlock(minx+x, miny+y, minz+z);
													if(stage.getIdStage() ==2 || stage.getIdStage() ==3 & nbjour == 5)
													{
														if(b==Blocks.coal_ore 
																|| b==Blocks.iron_ore
																|| b==Blocks.gold_ore
																|| b==Blocks.diamond_ore
																|| b==Blocks.lapis_ore
																|| b==Blocks.redstone_ore
																|| b==Blocks.emerald_ore
																|| b==BlockMod.Mythrile_Ore
																|| b==BlockMod.Titane_Ore
																|| b==BlockMod.Silithium_Ore
																|| b==BlockMod.NeoDium_Ore)
														{
															w.setBlock(minx+x, miny+y, minz+z,Blocks.stone);
															b = Blocks.stone;
														}
														if(b==Blocks.cobblestone || b==Blocks.stone || b==BlockMod.FakeCobble )
														{
															setBlockGen(w,minx+x, miny+y, minz+z, boolday);
														}
														if(b==Blocks.netherrack)
														{
															setBlockGenNether(w,minx+x, miny+y, minz+z, boolday);
														}
													}
													
													
												}
											}
										}
										
									}
						
							}
						 }
					} catch (SQLException e) {
						e.printStackTrace();
					}
    			}
    			bdd.CloseFreeId(id);
    		}
    		private void setBlockGen(World w, int x, int y, int z, boolean b) {
				 int r= w.rand.nextInt((200000) + 1);
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
    		private void setBlockGenNether(World w, int x, int y, int z, boolean b) {
				 int r= w.rand.nextInt((100000) + 1);
				 boolean m = false;
					 for(StageMineraiGen e:OreNether)
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
						 setBlockGenNether(w, x, y, z, false);
					 }

    }

}
