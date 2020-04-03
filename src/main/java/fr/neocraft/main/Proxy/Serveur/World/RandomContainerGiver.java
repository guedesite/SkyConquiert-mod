package fr.neocraft.main.Proxy.Serveur.World;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import fr.neocraft.main.main;
import fr.neocraft.main.Init.ItemMod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class RandomContainerGiver {
	private static final fr.neocraft.main.bdd bdd = main.getbdd();

	
	private ItemStack getRecByType(int i, Random r)
	{
		switch(i)
		{
			case 1: // minerai
				switch(r.nextInt(4000))
				{
					case 0:
						return new ItemStack(ItemMod.NeoDium_Ingot, r.nextInt(2)+1);
					case 1:
						return new ItemStack(Items.nether_star, 1);
					case 2:
						return new ItemStack(ItemMod.Ultime_Key, 1);
					default:
						switch(r.nextInt(12))
						{
							case 0:
								return new ItemStack(Items.coal, r.nextInt(10)+1);
							case 1:
								return new ItemStack(Items.iron_ingot, r.nextInt(3)+1);
							case 2:
								return new ItemStack(ItemMod.Iron_Nuggets, r.nextInt(15)+1);
							case 3:
								return new ItemStack(ItemMod.Mythrile_Ingot, r.nextInt(2)+1);
							case 4:
								return new ItemStack(ItemMod.Mythrile_Nuggets, r.nextInt(8)+1);
						}
				}
			case 2: // graine
				switch(r.nextInt(35))
				{
				case 0:
					return new ItemStack(ItemMod.Tomate_Seeds, 1);
				case 1:
					return new ItemStack(ItemMod.Aubergine_Seed, 1);
				case 2:
					return new ItemStack(ItemMod.Corn_Seeds, 1);
				case 3:
					return new ItemStack(ItemMod.Radis_Seed, 1);
				case 4:
					return new ItemStack(ItemMod.Laitue, 1);
				case 5:
					return new ItemStack(Items.poisonous_potato, 1);
				case 6:
					return new ItemStack(Items.nether_wart, 1);
				default:
					switch(r.nextInt(16))
					{
						case 0:
							return new ItemStack(Items.melon_seeds, r.nextInt(3)+1);	
						case 1:
							return new ItemStack(Items.pumpkin_seeds, r.nextInt(3)+1);	
						case 2:
							return new ItemStack(Items.wheat_seeds, r.nextInt(8)+1);	
						case 3:
							return new ItemStack(Items.potato, r.nextInt(4)+1);	
						case 4:
							return new ItemStack(Items.carrot, r.nextInt(4)+1);	
					}
					
				
				}
			case 3: //bois
				switch(r.nextInt(30))
				{
					case 0:
						return new ItemStack(Blocks.sapling,r.nextInt(2)+1);
					case 1:
						return new ItemStack(Blocks.sapling,1,r.nextInt(2)+1);
					case 2:
						return new ItemStack(Blocks.sapling,2,r.nextInt(2)+1);
					case 3:
						return new ItemStack(Blocks.sapling,3,r.nextInt(2)+1);
					case 4:
						return new ItemStack(Blocks.sapling,4,r.nextInt(2)+1);
					case 5:
						return new ItemStack(Blocks.sapling,5,r.nextInt(2)+1);
					case 6:
						return new ItemStack(Items.stick,r.nextInt(3)+1);
					case 7:
						return new ItemStack(ItemMod.tuteur,r.nextInt(3)+1);
					case 8:
						return new ItemStack(Blocks.log,r.nextInt(5)+1);
					
				}
			case 4: //stockage de mass
				switch(r.nextInt(8))
				{
					case 0:
						return new ItemStack(Blocks.cobblestone,r.nextInt(30)+1);
					case 1:
						return new ItemStack(Items.stick,r.nextInt(15)+1);
					case 2:
						return new ItemStack(Blocks.log,r.nextInt(20)+1);
					case 3:
						return new ItemStack(Items.coal,r.nextInt(20)+1);
					
				}
			case 5: // utilitaire
				switch(r.nextInt(15))
				{
					case 0:
						return new ItemStack(Items.book,1);
					case 1:
						return new ItemStack(Blocks.chest,1);
					default:
						switch(r.nextInt(8))
						{
							case 0:
								return new ItemStack(Blocks.torch,r.nextInt(20)+1);
							case 1:
								return new ItemStack(Items.lava_bucket,1);
							case 2:
								return new ItemStack(Items.water_bucket,1);
							case 3:
								return new ItemStack(Items.bucket,r.nextInt(1)+1);
						}
				}
				case 6: // Materiaux
			/*	switch(r.nextInt(5))
				{
					case 0: */
						switch(r.nextInt(25))
						{
							case 0:
								return new ItemStack(Blocks.gravel,r.nextInt(10)+1);
							case 1:
								return new ItemStack(Blocks.sand,r.nextInt(10)+1);
							case 2:
								return new ItemStack(Blocks.gravel,r.nextInt(10)+1);
							case 3:
								return new ItemStack(Blocks.sand,r.nextInt(10)+1);
							case 4:
								return new ItemStack(Blocks.grass,r.nextInt(5)+1);
							case 5:
								return new ItemStack(Blocks.dirt,r.nextInt(8)+1);
							case 6:
								return new ItemStack(Blocks.soul_sand,r.nextInt(5)+1);
							
						}
				

			default:
				return null; 
		}
	}
	
	

	public void launch() {

		int id = bdd.GetFreeId();
		try {
			
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringContainerRandom(), id);
		if(result != null)
		{
			try {
				TileEntity tile;
				World w;
				ItemStack[] Tempstack;
				int lenght;
				while(result.next())
				{
					 w = DimensionManager.getWorld(result.getInt("world"));
					 int type = result.getInt("type");
					 if(w != null)
					 {
						 tile = w.getTileEntity(result.getInt("x"), result.getInt("y"), result.getInt("z"));
						 
						 if(tile != null)
						 {
							 if(tile instanceof TileEntityChest) 
							{
								 lenght = 27;
									for(int i = 0; i < lenght; i++)
									{
										((TileEntityChest) tile).setInventorySlotContents(i, (ItemStack) null);
									}
									Tempstack = new ItemStack[lenght];
									((TileEntityChest) tile).setInventorySlotContents(w.rand.nextInt(lenght), getRecByType(type, w.rand));
									if(w.rand.nextInt(2) == 1)
									{
										((TileEntityChest) tile).setInventorySlotContents(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
									}
									if(w.rand.nextInt(4) == 1)
									{
										((TileEntityChest) tile).setInventorySlotContents(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
									}
									if(w.rand.nextInt(6) == 1)
									{
										((TileEntityChest) tile).setInventorySlotContents(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
									}
									((TileEntityChest) tile).markDirty();
							}
					else if(tile instanceof cpw.mods.ironchest.TileEntityIronChest) 
							{
								Tempstack = ((cpw.mods.ironchest.TileEntityIronChest) tile).getContents().clone();
								lenght = Tempstack.length;
								Tempstack = new ItemStack[lenght];
								Tempstack[w.rand.nextInt(lenght)] = getRecByType(type,w.rand);
								if(w.rand.nextInt(3) == 1)
								{
									((TileEntityChest) tile).setInventorySlotContents(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
								}
								if(w.rand.nextInt(2) == 1)
								{
									((TileEntityChest) tile).setInventorySlotContents(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
								}
								if(w.rand.nextInt(4) == 1)
								{
									((TileEntityChest) tile).setInventorySlotContents(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
								}
								if(w.rand.nextInt(6) == 1)
								{
									((TileEntityChest) tile).setInventorySlotContents(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
								}
								((cpw.mods.ironchest.TileEntityIronChest) tile).markDirty();
							}
							else if(tile instanceof noppes.npcs.blocks.tiles.TileNpcContainer)
							{
								lenght = 54;
								for(int i = 0; i < lenght; i++)
								{
									((noppes.npcs.blocks.tiles.TileNpcContainer) tile).func_70299_a(i, (ItemStack) null);
								}
								Tempstack = new ItemStack[lenght];
								((noppes.npcs.blocks.tiles.TileNpcContainer) tile).func_70299_a(w.rand.nextInt(lenght), getRecByType(type, w.rand));
								if(w.rand.nextInt(2) == 1)
								{
									((noppes.npcs.blocks.tiles.TileNpcContainer) tile).func_70299_a(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
								}
								if(w.rand.nextInt(4) == 1)
								{
									((noppes.npcs.blocks.tiles.TileNpcContainer) tile).func_70299_a(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
								}
								if(w.rand.nextInt(6) == 1)
								{
									((noppes.npcs.blocks.tiles.TileNpcContainer) tile).func_70299_a(w.rand.nextInt(lenght), getRecByType(result.getInt("type"),w.rand));
								}
								((noppes.npcs.blocks.tiles.TileNpcContainer) tile).markDirty();
					} 
							else
							{
								 System.out.println("INVALID TILE-ENTITY WorlId:"+result.getInt("world")+" POS:" +result.getInt("x")+" "+ result.getInt("y")+" "+ result.getInt("z"));
									
							}
						 }
						 else
						 {
							 System.out.println("TILE NULL WorlId:"+result.getInt("world")+" POS:" +result.getInt("x")+" "+ result.getInt("y")+" "+ result.getInt("z"));
						 } 
					 }else {
						 System.out.println("WORLD ID NULL "+result.getInt("world"));
					 }
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("NO BDD STAT FOUND");
		}

		} catch(Exception e)
		{
			e.printStackTrace();
		}
		bdd.CloseFreeId(id);
	}



	public void check() {
		int id = bdd.GetFreeId();
		ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringContainerRandom(), id);
		if(result != null)
		{
			try {
				World w;
				while(result.next())
				{
					w = DimensionManager.getWorld(result.getInt("world"));
					if(w.getTileEntity(result.getInt("x"), result.getInt("y"), result.getInt("z")) == null)
					{
						bdd.execute("DELETE FROM "+bdd.getStringContainerRandom()+" WHERE x="+result.getInt("x")+" AND y="+result.getInt("y")+" AND z="+ result.getInt("z")+" AND world="+result.getInt("world"));
					}
					else {
						int nb = bdd.compterligne(bdd.getStringContainerRandom(), "x="+result.getInt("x")+" AND y="+result.getInt("y")+" AND z="+ result.getInt("z")+" AND world="+result.getInt("world"));
						if(nb > 1)
						{
							bdd.execute("DELETE FROM "+bdd.getStringContainerRandom()+" WHERE x="+result.getInt("x")+" AND y="+result.getInt("y")+" AND z="+ result.getInt("z")+" AND world="+result.getInt("world"));
							bdd.execute("INSERT INTO "+bdd.getStringContainerRandom()+"(`world`, `type`, `x`, `y`, `z`) VALUES ("+result.getInt("world")+","+result.getInt("type")+","+result.getInt("x")+","+result.getInt("y")+","+result.getInt("z")+")");
						}
						
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		bdd.CloseFreeId(id);
	}
}
