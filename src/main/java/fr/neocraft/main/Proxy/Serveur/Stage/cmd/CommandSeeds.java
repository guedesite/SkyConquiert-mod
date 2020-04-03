package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class CommandSeeds extends CommandBase {


    @Override
    public String getCommandName() {
        return "seeds";
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

		private int x = 0, y = 0,z = 0;
		private int x2 = 0, y2 = 0,z2 = 0;
		private Block[] block;
		private int[] blockX, blockY, blockZ, blockMeta;
		private Entity entity[];
		private TileEntity tile[];
		private boolean pp = false;
		private boolean aa = false;
    @Override
    public void processCommand(ICommandSender sender,final String[] params) {
    	final EntityPlayer player = (EntityPlayer) sender;
    	final PlayerStats stat = PlayerStats.get(player);
    	final String name = player.getCommandSenderName();
    	if (params.length < 1) {
    		M(player, "nope");
        }
        else if(params[0].equals("set1") & isOp(player))
        {
        	this.x = (int)player.posX;
        	this.y = (int)player.posY;
        	this.z = (int)player.posZ;
        	M(player, this.x+" - "+this.y+" - "+this.z);
        }
        else if(params[0].equals("set2") & isOp(player))
        {
        	this.x2 = (int)player.posX;
        	this.y2 = (int)player.posY;
        	this.z2 = (int)player.posZ;
        	M(player, this.x2+" - "+this.y2+" - "+this.z2);
        }
        else if(params[0].equals("copy2") & isOp(player))
        {
        	PrintWriter writer;
			try {
				World w = player.worldObj;
				int nbx = (x2 - x) + 1;
    			int nby = (y2 - y) + 1;
    			int nbz = (z2 - z) + 1;
				writer = new PrintWriter(new File("assets/SeedsStatBlockNeoCraft.fg"), "UTF-8");
				for(int nbx2 = 0; nbx2 < nbx; nbx2++)
    			{
    				for(int nby2 = 0; nby2 < nby; nby2++)
        			{
    					for(int nbz2 = 0; nbz2 < nbz; nbz2++)
            			{
    						Block b = w.getBlock(x + nbx2, y + nby2, z + nbz2);
    						if(b != Blocks.air)
    						{
    							writer.println(Block.getIdFromBlock(b));
								writer.println(x + nbx2);
								writer.println(y + nby2);
								writer.println(z + nbz2);
								writer.println(w.getBlockMetadata(x + nbx2, y + nby2, z + nbz2));
    						}
            			}	
        			}
    			}
				writer.close();
			} catch(Exception e)
			{
				
			}
        }
        else if(params[0].equals("copy") & isOp(player))
        {
        	if(this.x< this.x2 & this.y< this.y2 & this.z< this.z2 )
        	{
        		pp = false;
	    		aa = false;
	    		if (params.length == 3) {
	    			if(params[1].equals("-p"))
	    			{
	    				pp = true;
	    			}
	    			else if(params[2].equals("-p"))
	    			{
	    				pp = true;
	    			}
	    			if(params[1].equals("-a"))
	    			{
	    				aa = true;
	    			}else if(params[2].equals("-a"))
	    			{
	    				aa = true;
	    			}
	    			
	    		}
	    		
	    		else if (params.length == 2) {
	    			if(params[1].equals("-p"))
	    			{
	    				pp = true;
	    			}else if(params[1].equals("-a"))
	    			{
	    				aa = true;
	    			}
	    		}
		        		try{
		        			
		        					
		                			
		                			
		                			if(aa)
		                			{
		                				int nbx = (x2 - x) + 1;
			                			int nby = (y2 - y) + 1;
			                			int nbz = (z2 - z) + 1;
			                			int total = nbx * nby * nbz;
			                			int nb = 0;
			                			
			                			World w = player.worldObj;
		                				int nbaire = 0;
			                			for(int nbx2 = 0; nbx2 < nbx; nbx2++)
			                			{
			                				for(int nby2 = 0; nby2 < nby; nby2++)
			                    			{
			                					for(int nbz2 = 0; nbz2 < nbz; nbz2++)
			                        			{
			                						if(w.getBlock(x + nbx2, y + nby2, z + nbz2) != Blocks.air)
			                						{
			                							nbaire++;
			                						}
			                        			}	
			                    			}
			                			}
			                			nb = 0;
			                			total -= nbaire;
			                			M(player, "Total: "+total);
			                			block = new Block[total + 1];
			                			blockX = new int[total + 1];
			                			tile = new TileEntity[total + 1];
			                			blockY = new int[total + 1];
			                			blockZ = new int[total + 1];
			                			blockMeta = new int[total + 1];
			                			for(int nbx2 = 0; nbx2 < nbx; nbx2++)
			                			{
			                				for(int nby2 = 0; nby2 < nby; nby2++)
			                    			{
			                					for(int nbz2 = 0; nbz2 < nbz; nbz2++)
			                        			{
			                						block[nb] = w.getBlock(x + nbx2, y + nby2, z + nbz2);
			                						blockX[nb] = x + nbx2;
			                						blockY[nb] = y + nby2;
			                						blockZ[nb] = z + nbz2;
			                						tile[nb] = w.getTileEntity(x + nbx2, y + nby2, z + nbz2);
			                						blockMeta[nb] = w.getBlockMetadata(x + nbx2, y + nby2, z + nbz2);
			                						nb++;
			                        			}	
			                    			}
			                				M(player, (nbx2*nbx) / 100 +" / 100");
			                			}
		                			}
		                			else
		                			{
		                				int nbx = (x2 - x) + 1;
			                			int nby = (y2 - y) + 1;
			                			int nbz = (z2 - z) + 1;
			                			int total = nbx * nby * nbz;
			                			int nb = 0;
			                			block = new Block[total + 1];
			                			blockX = new int[total + 1];
			                			tile = new TileEntity[total + 1];
			                			blockY = new int[total + 1];
			                			blockZ = new int[total + 1];
			                			blockMeta = new int[total + 1];
			                			World w = player.worldObj;
		                				for(int nbx2 = 0; nbx2 < nbx; nbx2++)
			                			{
		                					for(int nbz2 = 0; nbz2 < nbz; nbz2++)
		                        			{
				                				for(int nby2 = 0; nby2 < nby; nby2++)
				                    			{
			                						block[nb] = w.getBlock(x + nbx2, y + nby2, z + nbz2);
			                						blockX[nb] = x + nbx2;
			                						blockY[nb] = y + nby2;
			                						tile[nb] = w.getTileEntity(x + nbx2, y + nby2, z + nbz2);
			                						blockZ[nb] = z + nbz2;
			                						blockMeta[nb] = w.getBlockMetadata(x + nbx2, y + nby2, z + nbz2);
			                						nb++;
			                        			}	
			                    			}
		                					M(player, (nbx2*nbx) / 100 +" / 100");
			                			}
		                			}

		        		}		
		        		catch (Exception e){
		        			System.out.println(e.toString());
		        			M(player, e.toString());
		        		}

        	}
        	else
        	{
        		M(player, "erreur dans la config des pos");
        	}
        }else if(params[0].equals("paste") & isOp(player))
        {
        	if(this.x< this.x2 & this.y< this.y2 & this.z< this.z2 )
        	{
        		pp = false;
        		aa = false;
        		if (params.length == 3) {
        			if(params[1].equals("-p"))
        			{
        				pp = true;
        			}
        			else if(params[2].equals("-p"))
        			{
        				pp = true;
        			}
        			if(params[1].equals("-a"))
        			{
        				aa = true;
        			}else if(params[2].equals("-a"))
        			{
        				aa = true;
        			}
        			
        		}
        		else if (params.length == 2) {
        			if(params[1].equals("-p"))
        			{
        				pp = true;
        			}else if(params[1].equals("-a"))
        			{
        				aa = true;
        			}
        		}
        		
	        		try{
	        			
	        					int nbx = (x2 - x) + 1;
	                			int nby = (y2 - y) + 1;
	                			int nbz = (z2 - z) + 1;
	                			int total = nbx * nby * nbz;
	                			int nb = 0;
	                			World w = player.worldObj;
	                			
	                			if(aa)
	                			{
	                				int o = block.length / 1000, p=0;;
	                				for(int i = 0; i < block.length; i++)
		                			{
		                				if(block[i] != Blocks.air)
		                				{
		                					w.setBlock(blockX[i], blockY[i], blockZ[i], block[i], blockMeta[i], 2);
		                					if(tile[i] != null)
		                					{
		                						tile[i].setWorldObj(w);
		                						tile[i].xCoord = blockX[i];
		                						tile[i].yCoord = blockY[i];
		                						tile[i].zCoord = blockZ[i];
		                						w.addTileEntity(tile[i]);
		                					}
		                				}p++;
		                				if(o ==p )
		                				{
		                					p = 0;
		                					M(player, (i*100) / block.length +" / 100");
		                				}
		                			}
	                			}
	                			else
	                			{
	                				int o = block.length / 1000, p=0;;
		                			for(int i = 0; i < block.length; i++)
		                			{
		                				w.setBlock(blockX[i], blockY[i], blockZ[i], block[i], blockMeta[i], 2);
		                				if(tile[i] != null)
	                					{
	                						tile[i].setWorldObj(w);
	                						tile[i].xCoord = blockX[i];
	                						tile[i].yCoord = blockY[i];
	                						tile[i].zCoord = blockZ[i];
	                						w.addTileEntity(tile[i]);
	                					}
		                				p++;
		                				if(o ==p )
		                				{
		                					p = 0;
		                					
		                					M(player, (i*100) / block.length +" / 100");
		                				}
		                			}
		                			
	                				
	                			}
	                			if(entity != null)
	                			{
	                				Entity temp;
	                				for(Entity e:entity)
		            				{
	                					
	                					temp = null;
		        						if(e instanceof EntityCow)
		        						{
		        							temp = new EntityCow(w);
		        						} else if(e instanceof EntityPig)
		        						{
		        							temp = new EntityPig(w);
		        						} else if(e instanceof EntitySheep)
		        						{
		        							temp = new EntitySheep(w);
		        						}else if(e instanceof EntityChicken)
		        						{
		        							temp = new EntityChicken(w);
		        						}
		        						if(temp != null)
		        						{
		        							temp.setPosition(e.posX, e.posY, e.posZ);
		        							w.spawnEntityInWorld(temp);
		        							M(player, "entity");
		        						}
		            				}
	                			}
			        			
			        	
	  
	        		}		
	        		catch (Exception e){
	        			System.out.println(e.toString());
	        			M(player, e.toString());
	        		}
        		}
        	else
        	{
        		M(player, "erreur dans la config des pos");
        	}
        }else if(params[0].equals("paste2") & isOp(player))
        {
        	if(this.x< this.x2 & this.y< this.y2 & this.z< this.z2 )
        	{
        	
        		
	        		try{
	        			

	                			int nb = 0;
	                			World w = player.worldObj;
	              
		                			BufferedReader br = new BufferedReader(new FileReader("assets/SeedsStatBlockNeoCraft.fg"));
									String line;
		                			while ((line = br.readLine()) != null) {
										Block b = Block.getBlockById(Integer.parseInt(line));
										line = br.readLine();
										int xx = Integer.parseInt(line);
										line = br.readLine();
										int yy = Integer.parseInt(line);
										line = br.readLine();
										int zz = Integer.parseInt(line);
										line = br.readLine();
										int meta = Integer.parseInt(line);
										


		                				w.setBlock(xx, yy, zz, b, meta, 0);
		                				
										
									}
									br.close();
		                			
		                			M(player, "end");
		                	
	  
	        		}		
	        		catch (Exception e){
	        			System.out.println(e.toString());
	        			M(player, e.toString());
	        		}
        		}
        	else
        	{
        		M(player, "erreur dans la config des pos");
        	}
        }else if(params[0].equals("pasteest") & isOp(player))
        {
        	if(this.x< this.x2 & this.y< this.y2 & this.z< this.z2 )
        	{
        	
        		
	        		try{
	        			
	        					int nbx = (x2 - x) + 1;
	                			int nby = (y2 - y) + 1;
	                			int nbz = (z2 - z) + 1;
	                			int total = nbx * nby * nbz;
	                			int nb = 0;
	                			World w = player.worldObj;
	              
		                			BufferedReader br = new BufferedReader(new FileReader("assets/SeedsStatBlockNeoCraft.fg"));
									String line;
		                			while ((line = br.readLine()) != null) {
										Block b = Block.getBlockById(Integer.parseInt(line));
										line = br.readLine();
										int xx = Integer.parseInt(line)+1;
										line = br.readLine();
										int yy = Integer.parseInt(line);
										line = br.readLine();
										int zz = Integer.parseInt(line);
										line = br.readLine();
										int meta = Integer.parseInt(line);
										
										int xf = (xx - x)*2;

		                				w.setBlock(xx-xf, yy, zz, b, meta, 0);
		                				
										
									}
									br.close();
		                			
		                			M(player, "end");
		                	
	  
	        		}		
	        		catch (Exception e){
	        			System.out.println(e.toString());
	        			M(player, e.toString());
	        		}
        		}
        	else
        	{
        		M(player, "erreur dans la config des pos");
        	}
        }else if(params[0].equals("pastnord") & isOp(player))
        {
        	if(this.x< this.x2 & this.y< this.y2 & this.z< this.z2 )
        	{
        	
        		
	        		try{
	        			
	        					int nbx = (x2 - x) + 1;
	                			int nby = (y2 - y) + 1;
	                			int nbz = (z2 - z) + 1;
	                			int total = nbx * nby * nbz;
	                			int nb = 0;
	                			World w = player.worldObj;
	                			
		                			BufferedReader br = new BufferedReader(new FileReader("assets/SeedsStatBlockNeoCraft.fg"));
									String line;
		                			while ((line = br.readLine()) != null) {
										Block b = Block.getBlockById(Integer.parseInt(line));
										line = br.readLine();
										int xx = Integer.parseInt(line);
										line = br.readLine();
										int yy = Integer.parseInt(line);
										line = br.readLine();
										int zz = Integer.parseInt(line) + 2;
										line = br.readLine();
										int meta = Integer.parseInt(line);
										
										int zf = (zz - z)*2;

		                				w.setBlock(xx, yy, zz-zf, b, meta, 0);
		                				
										
									}
									br.close();
		                			
		                			M(player, "end");
		                	
	  
	        		}		
	        		catch (Exception e){
	        			System.out.println(e.toString());
	        			M(player, e.toString());
	        		}
        		}
        	else
        	{
        		M(player, "erreur dans la config des pos");
        	}
        }else if(params[0].equals("pastnordest") & isOp(player))
        {
        	if(this.x< this.x2 & this.y< this.y2 & this.z< this.z2 )
        	{
        	
        		
	        		try{
	        			
	        					int nbx = (x2 - x) + 1;
	                			int nby = (y2 - y) + 1;
	                			int nbz = (z2 - z) + 1;
	                			int total = nbx * nby * nbz;
	                			int nb = 0;
	                			World w = player.worldObj;
	                			
	                			
	                			BufferedReader br = new BufferedReader(new FileReader("assets/SeedsStatBlockNeoCraft.fg"));
								String line;
	                			while ((line = br.readLine()) != null) {
									Block b = Block.getBlockById(Integer.parseInt(line));
									line = br.readLine();
									int xx = Integer.parseInt(line)+1;
									line = br.readLine();
									int yy = Integer.parseInt(line);
									line = br.readLine();
									int zz = Integer.parseInt(line)+2;
									line = br.readLine();
									int meta = Integer.parseInt(line);
									
									int zf = (zz - z)*2;
	                				int xf = (xx - x)*2;
	                				w.setBlock(xx - xf, yy, zz-zf, b, meta, 0);
	                				
									
								}
								br.close();
	                			
	                			M(player, "end");
		                	
	  
	        		}		
	        		catch (Exception e){
	        			e.printStackTrace();
	        			M(player, e.toString());
	        		}
        		}
        	else
        	{
        		M(player, "erreur dans la config des pos");
        	}
        }else if(params[0].equals("save") & isOp(player))
        {
        	if(this.x< this.x2 & this.y< this.y2 & this.z< this.z2 )
        	{
        		try{
        			
        					
        					PrintWriter writer;
							try {
								writer = new PrintWriter(new File("assets/SeedsStatBlockNeoCraft.fg"), "UTF-8");
								int o = block.length / 1000, p=0;;
								for(int i = 0; i < block.length; i++)
	                			{
									writer.println(Block.getIdFromBlock(block[i]));
									writer.println(blockX[i]);
									writer.println(blockY[i]);
									writer.println(blockZ[i]);
									writer.println(blockMeta[i]);
									if(o ==p )
	                				{
	                					p = 0;
	                					M(player, (i*100) / block.length +" / 100");
	                				}
	                			}
	        					writer.close();
	        					if(entity != null)
	        					{
	        						writer = new PrintWriter(new File("assets/SeedsStatMobNeoCraft.fg"), "UTF-8");
		        					M(player, "register entity");
		        					for(Entity e:entity)
		            				{
	
		        						if(e instanceof EntityCow)
		        						{
		        							writer.println("cow");
		        						} else if(e instanceof EntityPig)
		        						{
		        							writer.println("pig");
		        						} else if(e instanceof EntitySheep)
		        						{
		        							writer.println("sheep");
		        						}else if(e instanceof EntityChicken)
		        						{
		        							writer.println("chicken");
		        						}
		        						writer.println((int) e.posX);
		        						writer.println((int) e.posY);
		        						writer.println((int) e.posZ);
		            				}
		        					M(player, "END");
		        					writer.close();
	        					}
							} catch (Exception e) {
								M(player, e.getMessage());
								e.printStackTrace();
							}
  
        		}		
        		catch (Exception e){
        			System.out.println(e.toString());
        			M(player, e.toString());
        		}
        	}
        	else
        	{
        		M(player, "erreur dans la config des pos");
        	}
        }else if(params[0].equals("load") & isOp(player))
        {
        		try{
        			

							try {
								BufferedReader br = new BufferedReader(new FileReader("assets/SeedsStatBlockNeoCraft.fg"));
								int compteur = 0;
								while ((br.readLine()) != null) {
									compteur++;  
								}
								int nb = compteur / 5;
								int o = compteur / 1000, p=0;;
								block = new Block[nb];
	                			blockX = new int[nb];
	                			blockY = new int[nb];
	                			blockZ = new int[nb];
	                			blockMeta = new int[nb];
								String line;
								int i = 0;
								br.close();
								br = new BufferedReader(new FileReader("assets/SeedsStatBlockNeoCraft.fg"));
								while ((line = br.readLine()) != null) {
									block[i] = Block.getBlockById(Integer.parseInt(line));
									line = br.readLine();
									blockX[i] = Integer.parseInt(line);
									line = br.readLine();
									blockY[i] = Integer.parseInt(line);
									line = br.readLine();
									blockZ[i] = Integer.parseInt(line);
									line = br.readLine();
									blockMeta[i] = Integer.parseInt(line);
									i++;
									if(o ==p )
	                				{
	                					p = 0;
	                					M(player, (compteur*i) / 100 +" / 100");
	                				}
								}
								br.close();
								
								br = new BufferedReader(new FileReader("assets/SeedsStatMobNeoCraft.fg"));
								compteur = 0;
								while ((br.readLine()) != null) {
									compteur++;  
								}
								nb = compteur / 4;
								entity = new Entity[nb];
								line = "";
								i = 0;
								br.close();
								br = new BufferedReader(new FileReader("assets/SeedsStatMobNeoCraft.fg"));
								World w = player.worldObj;
								while ((line = br.readLine()) != null) {
									if(line == "cow")
									{
										entity[i] = new EntityCow(w);
									}else if(line == "pig")
									{
										entity[i] = new EntityPig(w);
									} else if(line == "chicken")
									{
										entity[i] = new EntityChicken(w);
									} else if(line == "sheep")
									{
										entity[i] = new EntitySheep(w);
									}
									entity[i].setPosition(Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()));
									i++;
								}
								br.close();
								M(player, "END");
							} catch (Exception e) {
								M(player, e.getMessage());
								e.printStackTrace();
							}
  
        		}		
        		catch (Exception e){
        			System.out.println(e.toString());
        			M(player, e.toString());
        		}
        }else if(params[0].equals("addmob") & isOp(player))
        {
        	if (params.length == 2) {
        		World w = player.worldObj;
        		Entity temp = null;
        		if(params[1].equals("cow"))
        		{
        			temp = new EntityCow(w);
        			temp.setLocationAndAngles(player.posX,player.posY, player.posZ, 0, 0);
        		} else if(params[1].equals("pig"))
        		{
        			temp = new EntityPig(w);
        			temp.setLocationAndAngles(player.posX,player.posY, player.posZ, 0, 0);
        		}else if(params[1].equals("sheep"))
        		{
        			temp = new EntitySheep(w);
        			temp.setLocationAndAngles(player.posX,player.posY, player.posZ, 0, 0);
        		}else if(params[1].equals("chicken"))
        		{
        			temp = new EntityChicken(w);
        			temp.setLocationAndAngles(player.posX,player.posY, player.posZ, 0, 0);
        		}
        		if(temp != null)
        		{
        			if(entity == null)
        			{
        				entity = new Entity[1];
        				entity[0] = temp;
        			}
        			else
        			{
        				Entity[] entitytemp = new Entity[entity.length + 1];
        				int i = 0;
        				for(Entity e:entity)
        				{
        					entitytemp[i] = e;
        					i++;
        				}
        				entitytemp[i] = temp;
        				entity = entitytemp;
        				M(player, "Mob référencer:p");
        			}
        		}
        		else
        		{
        			M(player, "Mob non référencer go voir guedesite bitch :p");
        		}
            }
        	else
        	{
        		M(player, "nope");
        	}
        }
    }
    
    public boolean isOp(EntityPlayer player)
    {
    	boolean op = MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile());
        return op;
    }
    public void M(EntityPlayer p, String l)
    {
    	p.addChatMessage(new ChatComponentTranslation(l));
    }
}
