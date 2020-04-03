package fr.neocraft.main.Proxy.Serveur.Stage.cmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.neocraft.main.bdd;
import fr.neocraft.main.main;
import fr.neocraft.main.Init.SavingData;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.player.teleport;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandPb extends CommandBase {

	public static final bdd bdd = main.getbdd();
    @Override
    public String getCommandName() {
        return "neopb";
    }
    
    @Override
    public String getCommandUsage(ICommandSender sender) {
        return EnumChatFormatting.GRAY + "/pb | /pb erreur | /pb crash (nombre)";
    }
    
    /**
     * Return the required permission level for this command.
     */
    
    private String cmd ="";
    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }
    /**
     * Return the required permission level for this command.
     */
    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
    	return true;
    }
    private List<String> erreur = new ArrayList<String>();
	@Override
	public void processCommand(final ICommandSender ic, String[] arg) {
		if(arg.length > 0)
		{
			if(arg[0].equals("erreur"))
			{
				if(!erreur.isEmpty())
				{
					 for (int i = 0; i < erreur.size(); i++) 
					 {
						 ic.addChatMessage(new ChatComponentText(erreur.get(i)));
				     }
				}
				else
				{
					ic.addChatMessage(new ChatComponentText("no error to display at this time"));
				}
			}else if(arg[0].equals("crash"))
			{
				if(arg.length > 1)
				{
					try {
						int i = Integer.parseInt(arg[1]);
						if(new File("assets/crashCMD/crash-"+i).exists())
						{
							 BufferedReader br;
							try {
								br = new BufferedReader(new FileReader("assets/crashCMD/crash-"+i+".nc"));
								String line = "";
								ic.addChatMessage(new ChatComponentText("start"));
								while((line = br.readLine()) != null)
								{
									ic.addChatMessage(new ChatComponentText(line));
								}
								ic.addChatMessage(new ChatComponentText("end"));
							} catch (FileNotFoundException e) {
								ic.addChatMessage(new ChatComponentText("file exception"));
								e.printStackTrace();
							} catch (IOException e) {
								ic.addChatMessage(new ChatComponentText("connection file exception"));
								e.printStackTrace();
		
							}
						} else
						{
							ic.addChatMessage(new ChatComponentText("no file at "+"LOCAL DECOMP assets/crashCMD/crash-"+i+".nc"));
						}
					} catch(Exception e)
					{
						throw new WrongUsageException(getCommandUsage(ic));
					}
				}
				else
				{
					throw new WrongUsageException(getCommandUsage(ic));
				}
			}
			else if(arg[0].equals("cmd"))
			{
				int id = Integer.parseInt(arg[1]);
				EntityPlayer p = (EntityPlayer) ic;
				 int x = p.chunkCoordX;
	             int z = p.chunkCoordZ;
	             int w = p.worldObj.provider.dimensionId;
	             bdd.execute("INSERT INTO "+bdd.getStringChunk()+"(`PosX`, `PosZ`, `IdStage`, `IdWorld`) VALUES ('"+x+"','"+z+"','"+id+"', '"+w+"')");
	             ic.addChatMessage(new ChatComponentText("yep"));

			}else if(arg[0].equals("repair"))
			{
				int id = bdd.GetFreeId();
				ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringStage(), id);
				if(result != null)
				{
					try {
						while(result.next())
						{
								if(!bdd.Exist("SELECT * FROM "+bdd.getStringChunk()+" WHERE IdStage="+result.getInt("id2")))
								{
									ic.addChatMessage(new ChatComponentText("repair "+result.getInt("id2")));
									ic.addChatMessage(new ChatComponentText("X: "+result.getInt("XPos")+", Z: "+result.getInt("ZPos")));
									teleport.player(result.getInt("WorldId"),(EntityPlayer) ic, result.getInt("XPos")*16, 150, result.getInt("ZPos"), 0, false);
									ic.addChatMessage(new ChatComponentText("neopb cmd "+result.getInt("id2")));

									return;
								}
						}
					}catch(SQLException e)
					{
						ic.addChatMessage(new ChatComponentText("erreur 2"));
						e.printStackTrace();
					}
				}else {
					ic.addChatMessage(new ChatComponentText("null"));
				}
				bdd.CloseFreeId(id);
		}
		
	}else
		{
			if(Startlog())
			{
				final Thread t = new Thread() {
					@Override
					public void run() {
						erreur = new ArrayList<String>();
						log(ic, "start");
						log(ic, "save stage system");
						SavingData saver = new SavingData();
		        		saver.SaveStage(true);
		        		
		        		log(ic, "reload stage system");
		        		RegisterStage.Register();
		        		RegisterStage.RegisterChunk();
		        		RegisterStage.RegisterItemDeny();
		        		
		        		
		        		log(ic, "start check system");
						int nbStage = bdd.compterligne(bdd.getStringStage());
						int nbChunk = bdd.compterligne(bdd.getStringChunk());
						log(ic, "found "+nbStage+" colum of stage in bdd");
						log(ic, "found "+nbChunk+" colum of chunk in bdd");
						int iderreur = 1;
						if(nbStage != nbChunk)
						{
							erreur.add("ERREUR "+ iderreur+ ": "+" stage and chunk in bdd colum not coordinate");
							iderreur++;
						}
						log(ic, "start check stage");
						int id = bdd.GetFreeId();
						ResultSet result = bdd.query("SELECT * FROM "+bdd.getStringStage(), id);
						if(result != null)
						{
							try {
								while(result.next())
								{
									try {
									if(result.getInt("StageId") < 4)
									{
									/*	if(result.getString("owner") == "null" || result.getInt("IsUse") == 0)
										{
											bdd.update("UPDATE "+bdd.getStringStage()+" SET `owner`=NULL WHERE id2="+result.getInt("id2"), 0);
										} */
										
										if(result.getString("owner") == null )
										{
											if(result.getInt("IsUse") == 1)
											{
												erreur.add("ERREUR "+ iderreur+ ": "+" stage "+result.getInt("StageId")+" id "+result.getInt("id2")+" is use but no owner" );
												iderreur++;
											}
											if(result.getString("subowner") != null )
											{
												erreur.add("ERREUR "+ iderreur+ ": "+" stage "+result.getInt("StageId")+" id "+result.getInt("id2")+" no owner but have subowner" );
												iderreur++;
											}
										}
										if(result.getString("subowner") != null && result.getInt("IsUse") == 0)
										{
										
												erreur.add("ERREUR "+ iderreur+ ": "+" stage "+result.getInt("StageId")+" id "+result.getInt("id2")+" is not use but have subowner" );
												iderreur++;
											
										}
										if(result.getString("owner") != null && result.getInt("IsUse") == 0)
										{
										
												erreur.add("ERREUR "+ iderreur+ ": "+" stage "+result.getInt("StageId")+" id "+result.getInt("id2")+" is not use but have owner" );
												iderreur++;
											
										}
										
										
											int nb = bdd.compterligne(bdd.getStringStage(), "id2="+result.getInt("id2"));
											if(nb < getAllChunkFromid(result.getInt("StageId")))
											{
												erreur.add("ERREUR "+ iderreur+ ": "+" stage "+result.getInt("StageId")+" id "+result.getInt("id2")+" are not totaly register" );
												iderreur++;
											} else if(nb < getAllChunkFromid(result.getInt("StageId")))
											{
												erreur.add("ERREUR "+ iderreur+ ": "+" stage "+result.getInt("StageId")+" id "+result.getInt("id2")+" TO MANY REGISTER ! DANGEROUS !" );
												iderreur++;
											}
										nb = bdd.compterligne(bdd.getStringStage(), " owner='"+result.getString("owner")+"' AND id2!="+result.getInt("id2")+ " AND StageId="+result.getInt("StageId"));
										if(nb > 0)
										{
											erreur.add("ERREUR "+ iderreur+ ": "+" owner "+result.getString("owner")+" has to many stage with same id");
											iderreur++;
										}
										if(!bdd.Exist("SELECT * FROM "+bdd.getStringChunk()+" WHERE IdStage="+result.getInt("id2")))
										{
											erreur.add("ERREUR "+ iderreur+ ": "+" no chunk found for stage id:"+result.getInt("id2"));
											iderreur++;
										}
									
									}
									}catch(Exception e)
									{
										erreur.add("ERREUR "+ iderreur+ ": "+" while after result error, file id:"+createErrorFile(e));
										iderreur++;
									}
								}
							} catch (SQLException e) {
								erreur.add("ERREUR "+ iderreur+ ": "+" while result error, file id:"+createErrorFile(e));
								iderreur++;
							}
						}
						else
						{
							erreur.add("ERREUR "+ iderreur+ ": "+" no stage found on bdd");
							iderreur++;
						}
						bdd.CloseFreeId(id);
						log(ic, "end check stage");
						log(ic, "start check chunk");
						id = bdd.GetFreeId();
						 result = bdd.query("SELECT * FROM "+bdd.getStringChunk(), id);
						if(result != null)
						{
							try {
								while( result.next())
								{
									try {
									if(!bdd.Exist("SELECT * FROM "+bdd.getStringStage()+" WHERE id2="+result.getInt("IdStage")))
									{
										erreur.add("ERREUR "+ iderreur+ ": "+" no stage found for chunk id:"+result.getInt("IdStage"));
										iderreur++;
									}
									if(RegisterStage.getStageAtXY(result.getInt("PosX"), result.getInt("PosZ"), result.getInt("IdWorld")) == null )
									{
										erreur.add("ERREUR "+ iderreur+ ": "+" no stage found for chunk coordinate x:"+result.getInt("PosX") + " z:"+result.getInt("PosZ")+ " worldid:"+result.getInt("IdWorld")+" in server data var");
										iderreur++;
									}else if(RegisterStage.getStageAtXY(result.getInt("PosX"), result.getInt("PosZ"), result.getInt("IdWorld")).getId() != result.getInt("IdStage"))
									{
										erreur.add("ERREUR "+ iderreur+ ": "+"ID STAGE NOT CORRECT ! DANGEROUS ! for chunk coordinate x:"+result.getInt("PosX") + " z:"+result.getInt("PosZ")+ " worldid:"+result.getInt("IdWorld")+" in server data var");
										iderreur++;
									}
								} catch (SQLException e) {
									erreur.add("ERREUR "+ iderreur+ ": "+" while result error, file id:"+createErrorFile(e));
									iderreur++;
								}
								}
							}catch (SQLException e) {
								erreur.add("ERREUR "+ iderreur+ ": "+" while result error, file id:"+createErrorFile(e));
								iderreur++;
							}
						}
						else
						{
							erreur.add("ERREUR "+ iderreur+ ": "+" no chunk found on bdd");
							iderreur++;
						}
						bdd.CloseFreeId(id);
						log(ic, "end check chunk");
						if(!erreur.isEmpty())
						{
							 
								 ic.addChatMessage(new ChatComponentText("error: "+erreur.size()));
								 ic.addChatMessage(new ChatComponentText("/pb erreur to see all error"));
						   
						}
						else
						{
							ic.addChatMessage(new ChatComponentText("no error"));
						}
						Stoplog();
						log(ic, "end");
					}
			    };
			    t.setPriority(Thread.MIN_PRIORITY);
			    t.start();
			}else
			{
				ic.addChatMessage(new ChatComponentText("can't start log ... see console error"));
			}

			
		}
		
	}
	
	private int getAllChunkFromid(int e)
	{
		switch(e)
		{

			case 1:
				return 4;
			case 2:
				return 9;
			case 3:
				return 49;
			default:
				return 1;
		}
	}
	private void Stoplog()
	{
		if(!erreur.isEmpty())
		{
			writer.println(" ");
			writer.println("########################	error	########################");
			writer.println(" ");
			 for (int i = 0; i < erreur.size(); i++) 
			 {
				 writer.println(erreur.get(i));
				
		     }
			 	writer.println(" ");
				writer.println("########################	error	########################");
				writer.println(" ");
		}
		writer.close();
	}
	private PrintWriter writer;
	private boolean Startlog()
	{
		writer = null;
		try {
			writer = new PrintWriter(new File("assets/crashCMD/logs.nc"), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	private void log(ICommandSender i, String e)
	{
		i.addChatMessage(new ChatComponentText(e));
		writer.println(e);
	}
	private int createErrorFile(Exception e)
	{
	int i = 0;
		try {
			PrintWriter writer2 = new PrintWriter(new File("assets/crashCMD/crash-"+i+".nc"), "UTF-8");
			e.printStackTrace(writer2);
			writer2.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		return -1;
	}

}
