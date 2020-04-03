package fr.neocraft.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ListIterator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Init.EntityMod;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Init.SavingData;
import fr.neocraft.main.Proxy.Client.event.ElevateurEvent;
import fr.neocraft.main.Proxy.Client.event.OnChat;
import fr.neocraft.main.Proxy.Client.event.TextDraw;
import fr.neocraft.main.Proxy.Serveur.CommonProxy;
import fr.neocraft.main.Proxy.Serveur.Handler.NeoGuiHandler;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;

import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.StageXpManager;
import fr.neocraft.main.Proxy.Serveur.Stage.Pet.PetRegister;
import fr.neocraft.main.Proxy.Serveur.Stage.Shopper.ShopperRegister;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandAdminMoney;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandBdd;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandCode;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandCode2;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandFurnace;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandHorraire;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandHorraireDrag;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandItemDeny;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandMoney;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandPb;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandRangTab;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandRepair;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandSeeds;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandShopper;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandStage;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandStage0Gen;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandStage2Gen;
import fr.neocraft.main.Proxy.Serveur.Stage.cmd.CommandStageAfk;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxItem;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeItem;
import fr.neocraft.main.Proxy.Serveur.World.RandomContainerGiver;
import fr.neocraft.main.Proxy.Serveur.World.WorldRegister;
import fr.neocraft.main.Proxy.Serveur.event.StageEventFML;
import fr.neocraft.main.Proxy.Serveur.event.StageEventFORGE;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientBossBoolean;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ClientPlayerList;
import fr.neocraft.main.ServeurManager.DailyManager;
import fr.neocraft.main.ServeurManager.DonjonManager;
import fr.neocraft.main.ServeurManager.HorraireManager;
import fr.neocraft.main.ServeurManager.TimerManager;
import info.ata4.minecraft.dragon.server.cmd.CommandDragon;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = neoreference.MOD_ID, name = neoreference.MOD_NAME, version = neoreference.VERSION, useMetadata = true)

public class main
{   
	public static int LoyerStage3 = 300000;
	public static int FacteurLoyerStage3 = 75000;
	public static bdd bdd = new bdd();
	public static final boolean DEBUG = false;
    public static boolean verif = false;
    public static TimerManager  cron;
    public static HorraireManager H = new HorraireManager();


    
    public static int getHourlyQuest() { return H.idQuestHorraire; }
    public static void runHorraire() { H.start(); }
    public static final NeoChat C = new NeoChat();
	public static boolean BossActive = false;
    public static SimpleNetworkWrapper NetWorkClient, NetWorkServer;
    private ModMetadata metadata;
	
	public static CreativeTabs neocraft,neocraft_portal,neocraft_spawner,neocraft_armor, neocraft_seeds, jaffTab, neocraft_futur;
	
    @SidedProxy(clientSide = neoreference.CLIENT_PROXY, serverSide = neoreference.SERVER_PROXY)
    public static CommonProxy proxy;
    @Instance(neoreference.MOD_ID)
    public static main instance;
	
    public static BoxItem[] VoteBox;
    public static BoxUltimeItem[] UltimeBox;
	// CLIENT SIDE
    	public static int dataHorraire = -1;
    	public static int dataHorraire2 = -1;
    	public static int dataHorraire3 = -1;
    //
    
    public static void RebootTimerManager() { 
	    cron = new TimerManager();
		
    }
    
    public static TimerManager getTimerManager() {
    	return cron;
    }

    public static double[] DragPos = new double[3] ;
    public static float[] Dragpitch= new float[2];
    public static HorraireManager getHorraire() { return H; }
    
    public static SimpleNetworkWrapper getNetWorkClient(){return NetWorkClient; }
    public static SimpleNetworkWrapper getNetWorkServer(){return NetWorkServer; }
    
 
    public static RandomContainerGiver Container;
	public static int eventNbEvent =0;
    public ModMetadata getMetadata() {return metadata; }
    public static final bdd getbdd(){return bdd; }
    public static final NeoChat getChat() {return C;  }
    public static main NeogetInstance() {return instance;  }
    public static final BoxItem[] getVoteBox() {return VoteBox;}
    public static final BoxUltimeItem[] getUltimeBox() {return UltimeBox;}
    private void registerTab()
    {
    	neocraft= new CreativeTabs("neocraft")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return ItemMod.Copyright_NeoCraft;
            }
        };
        neocraft_portal = new CreativeTabs("neocraft portal")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(BlockMod.portail[1]);
            }
        };
        neocraft_spawner = new CreativeTabs("neocraft spawner")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(Blocks.mob_spawner);
            }
        };
        neocraft_armor = new CreativeTabs("neocraft armor")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return ItemMod.NeoDium_ChestPlate;
            }
        };
        neocraft_futur = new CreativeTabs("neocraft futur")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return Item.getItemFromBlock(BlockMod.FuturInput);
            }
        };
        neocraft_seeds = new CreativeTabs("neocraft seeds")
        {
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem()
            {
                return ItemMod.Tomate_Seeds;
            }
        };
       
   
    }
    
    
    
	@EventHandler
    public void
    preInit(FMLPreInitializationEvent event)
    {    	
    	if(event.getSide()==Side.SERVER) {
    		verif = NeoCraftCheck.CheckIfIsValidServer();
    	}
    	else if(event.getSide()==Side.CLIENT) {
    		verif = NeoCraftCheck.CheckIfIsValidClient();
    	}
    	

    	if(verif == true)
    	{

    	 metadata = event.getModMetadata();
         
         NetWorkClient = NetworkRegistry.INSTANCE.newSimpleChannel("NeoNetWorkClient");
         NetWorkClient.registerMessage(NetWorkClient.Handler.class, NetWorkClient.class, 0, Side.CLIENT);
        
         NetWorkServer = NetworkRegistry.INSTANCE.newSimpleChannel("NeoNetWorkServer");
	        NetWorkServer.registerMessage(NetWorkServer.Handler.class, NetWorkServer.class, 0, Side.SERVER);
		      
	        registerTab();
	        EntityMod.init();
	        QuestManager.Register();
	        BlockMod.init();
	        BlockMod.register();
	        ItemMod.Init();
	        ItemMod.register(); 	
	        WorldRegister.Register();
	      }
    }

    

    @EventHandler
    public void
    init(FMLInitializationEvent event)
    {
    	if(verif == true)
    	{
    		proxy.registerRenders(event);
	    	proxy.onInit(event);

    		if(DEBUG)
    		{
    			
	    		MinecraftForge.EVENT_BUS.register(new TextDraw());
			    MinecraftForge.EVENT_BUS.register(new OnChat());
		    	FMLCommonHandler.instance().bus().register(new StageEventFML());
		    	MinecraftForge.EVENT_BUS.register(new StageEventFORGE());
		    	QuestManager.initQuestServer(); 
    		}
    		else if(event.getSide() == Side.SERVER && bdd.IsClass)
    		{
		    	FMLCommonHandler.instance().bus().register(new StageEventFML());
		    	MinecraftForge.EVENT_BUS.register(new StageEventFORGE());
		    	QuestManager.initQuestServer(); 
    		}
    		else
    		{
    			FMLCommonHandler.instance().bus().register(new ElevateurEvent());
    			MinecraftForge.EVENT_BUS.register(new TextDraw());
			    MinecraftForge.EVENT_BUS.register(new OnChat());
			    
		
    		}
    		NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new NeoGuiHandler());
    	}


    }
    @EventHandler
    public void
    postInit(FMLPostInitializationEvent event)
    {
    	if(event.getSide() == Side.SERVER && bdd.IsClass)
    	{
	    	try {
		  
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(new FileReader("assets/HorraireDrag.data"));
				  String line = "";
			
				  DragPos[0] = Double.parseDouble(br.readLine());
				  DragPos[1] = Double.parseDouble(br.readLine());
				  DragPos[2] = Double.parseDouble(br.readLine());
				  Dragpitch[0] = Float.parseFloat(br.readLine());
				  Dragpitch[0] = Float.parseFloat(br.readLine());
	    	} catch(Exception e ) {
	    		
	    	}
    	}
    }
    
    public static void setBoss(boolean e)
    {
    	BossActive =e;
    	getNetWorkClient().sendToAll(new NetWorkClient(new ClientBossBoolean(e)));
    
    }
    
	@EventHandler
    public void serverStarting(FMLServerStartedEvent event)
    {	
    	if(verif == true)
	    {
    		if(event.getSide() == Side.SERVER && bdd.IsClass || DEBUG)
			{
    			
    			bdd.Openbdd();
    		StageXpManager.register();
		    	RegisterStage.Register();
		    	RegisterStage.RegisterItemDeny();
		    	RegisterStage.RegisterChunk();
		    	ShopperRegister.register();
		  	DonjonManager.LoadDonjonId();	
		    	DonjonManager.clearAllDonjon();
		   	PetRegister.registerServer();
		    	ServerCommandManager cmdman = (ServerCommandManager) MinecraftServer.getServer().getCommandManager(); 
		    	cmdman.registerCommand(new CommandDragon());
		    	cmdman.registerCommand(new CommandStage());
		    	cmdman.registerCommand(new CommandMoney());
		    	cmdman.registerCommand(new CommandSeeds());
		    	cmdman.registerCommand(new CommandItemDeny());
		    	cmdman.registerCommand(new CommandShopper());
		    	cmdman.registerCommand(new CommandStage2Gen());
		    	cmdman.registerCommand(new CommandStage0Gen());
		    	cmdman.registerCommand(new CommandAdminMoney());
		    	cmdman.registerCommand(new CommandStageAfk());
		    	cmdman.registerCommand(new CommandHorraireDrag());
		    	cmdman.registerCommand(new CommandRangTab());
		    	cmdman.registerCommand(new CommandPb());
		    	cmdman.registerCommand(new CommandRepair());
		    	cmdman.registerCommand(new CommandFurnace());
		    	cmdman.registerCommand(new CommandHorraire());
		    	cmdman.registerCommand(new CommandBdd());
		    	cmdman.registerCommand(new CommandCode());
		    	cmdman.registerCommand(new CommandCode2());
		    	Container = new RandomContainerGiver();
		    	VoteBox = new BoxItem[24];
				VoteBox[0] = new BoxItem(new ItemStack(ItemMod.CModoR1), 5);
				VoteBox[1] = new BoxItem(new ItemStack(ItemMod.CModoR2), 10);
				VoteBox[3] = new BoxItem(new ItemStack(ItemMod.CAdminR3), 50);
				VoteBox[4] = new BoxItem(new ItemStack(ItemMod.tuteur), 10, 16);
				VoteBox[5] = new BoxItem(new ItemStack(ItemMod.Vote_Key), 5, 2);
				VoteBox[6] = new BoxItem(new ItemStack(ItemMod.tuteur), 1, 16);
				VoteBox[7] = new BoxItem(new ItemStack(Items.iron_pickaxe), 7);
				VoteBox[8] = new BoxItem(new ItemStack(Items.coal), 3, 20);
				VoteBox[9] = new BoxItem(new ItemStack(Items.iron_ingot), 7, 10);
				VoteBox[10] = new BoxItem(new ItemStack(Items.fireworks), 30, 5);
				VoteBox[11] = new BoxItem(new ItemStack(Items.spawn_egg, 1 ,93), 25, 2);
				VoteBox[12] = new BoxItem(new ItemStack(Items.spawn_egg, 1 ,96), 50, 1);
				VoteBox[13] = new BoxItem(new ItemStack(Items.spawn_egg, 1 ,92), 25, 2);
				VoteBox[14] = new BoxItem(new ItemStack(Items.spawn_egg, 1 ,91), 25, 2);
				VoteBox[15] = new BoxItem(new ItemStack(Items.spawn_egg, 1 ,90), 25, 2);
				VoteBox[16] = new BoxItem(new ItemStack(BlockMod.Spawner[5], 1), 100, 1);
				VoteBox[17] = new BoxItem(new ItemStack(BlockMod.Spawner[7], 1), 100, 1);
				VoteBox[18] = new BoxItem(new ItemStack(BlockMod.Spawner[8], 1), 100, 1);
				VoteBox[19] = new BoxItem(new ItemStack(BlockMod.Spawner[0], 1), 100, 1);
				VoteBox[20] = new BoxItem(new ItemStack(Blocks.mob_spawner, 1 ,99), 200, 2);
				VoteBox[21] = new BoxItem(new ItemStack(Items.ender_pearl),15, 16);
				VoteBox[22] = new BoxItem(new ItemStack(Items.dye, 1 ,15), 10, 64);
				VoteBox[23] = new BoxItem(new ItemStack(BlockMod.StagePresent, 1), 35);
		    	
				UltimeBox = new BoxUltimeItem[13];
		    	UltimeBox[0] = new BoxUltimeItem(new ItemStack(ItemMod.Ultime_Key, 1), 10);
		    	UltimeBox[1] = new BoxUltimeItem(new ItemStack(ItemMod.Ultime_Key, 2), 20);
		    	UltimeBox[2] = new BoxUltimeItem(new ItemStack(ItemMod.bouclier, 1, 6), 5, 3);
		    	UltimeBox[3] = new BoxUltimeItem(new ItemStack(ItemMod.bouclier, 1, 12), 9, 2);
		    	UltimeBox[4] = new BoxUltimeItem(new ItemStack(ItemMod.bouclier, 1, 24), 15);
		    	UltimeBox[5] = new BoxUltimeItem(new ItemStack(ItemMod.gardien, 1), 5, 2);
		    	UltimeBox[6] = new BoxUltimeItem(new ItemStack(ItemMod.gardien, 2), 7, 2);
		    	UltimeBox[7] = new BoxUltimeItem(new ItemStack(ItemMod.gardien, 3), 9 , 2);
		    	UltimeBox[8] = new BoxUltimeItem(new ItemStack(ItemMod.gardien, 4), 11, 2);
		    	UltimeBox[9] = new BoxUltimeItem(new ItemStack(ItemMod.gardien, 5), 13, 2);
		    	UltimeBox[10] = new BoxUltimeItem(new ItemStack(ItemMod.gardien, 6), 15, 2);
		    	UltimeBox[11] = new BoxUltimeItem(new ItemStack(ItemMod.NeoDium_Ingot, 1), 7, 5);
		    	UltimeBox[12] = new BoxUltimeItem(new ItemStack(ItemMod.Silithium_Ingot, 1), 5, 7);
		    	System.out.println("DAY: "+DailyManager.getIdDayByName());
		    	cron = new TimerManager();
		    	cron.setPriority(Thread.MIN_PRIORITY);
		    	cron.start();

		    	
		    /*	if(new File("discord.jar").exists())
		    	{
		    		System.out.println("start discord.jar");
		    		 ProcessBuilder pb = new ProcessBuilder(new String[] { "java", "-jar", "discord.jar" });
		    	        try {
							discord = pb.start();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    	} else {
		    		System.out.println("no discord.jar found");
		    	} */
			}
    	}
    }
    
	@EventHandler
    public void onServerStopped(FMLServerStoppingEvent event)
    {	
		/*if(discord != null)
		{
			if(discord.isAlive())
			{
				discord.destroy();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("destroy discord process");
			}
		} */
    	if(verif == true)
    	{
    		if((event.getSide() == Side.SERVER  && bdd.IsClass)|| DEBUG)
			{

		    	if(RegisterStage.nbStage != 0)
		    	{
		    		SavingData saver = new SavingData();
		    		saver.SaveStage( true);
		    	}
		    	bdd.Closebdd();
			}
    	}
    }
	public static void stopHorraire() {
		getHorraire().stopH();
		
	}
	public static void reloadBdd() {
		bdd.Closebdd();
		bdd.Openbdd();
	}
	public static void startContainer() {
			Container.launch();
	}
	public static void startCheckContainer() {
		Container.check();
		
	}
	
	public static void SendTabToAll()
	{
		String all ="";
		ArrayList<String> d = new ArrayList<String>();
		boolean first = true;
		 EntityPlayer pls = null;
		 ListIterator li = (ListIterator) MinecraftServer.getServer().getConfigurationManager().playerEntityList.listIterator();
		    while (li.hasNext()){
		    	if(first)
		    	{
		    		first = false;
		    		all += "pseudo='"+((EntityPlayer) li.next()).getCommandSenderName()+"'";
		    	}
		    	else
		    	{
		    		all += " OR pseudo='"+((EntityPlayer) li.next()).getCommandSenderName()+"'";
		    	}
		    }
		    if(all != "")
		    {
		    	int id2 = bdd.GetFreeId();
		    	ResultSet result = bdd.query("SELECT rang, pseudo FROM `Neo-Stage-Player` WHERE "+all+" ORDER BY rang DESC", id2);
	    		if(result != null)
	    		{
	    			try {
	    				while(result.next())
						{
	    					switch(result.getInt("rang"))
	    					{
	    						case 0:
	    							d.add("(8[(fG(8] (f" + result.getString("pseudo"));
	    							break;
	    						case 1:
	    							d.add( "(9[(1(l)(9C(1(l)(9] (9" + result.getString("pseudo"));
	    							break;
	    						case 2:
	    							d.add("(e[(6(l)(eS(6(l)(e] (e" + result.getString("pseudo"));
	    							break;
	    						case 3:
	    							d.add("(6[(e(l)(6P(e(l)(6] (6" + result.getString("pseudo"));
	    							break;
	    						case 4:
	    							d.add("(6[(cM(6] (c" + result.getString("pseudo"));
	    							break;
	    						case 5:
	    							d.add("(4[(f(l)(4Y(fT(f(l)(4] (f" + result.getString("pseudo"));
	    							break;
	    						case 6:
	    							d.add("(2[(a(l)(2H(a(l)(2] (a" + result.getString("pseudo"));
	    							break;
	    						case 7:
	    							d.add("(a[(2(l)(aR(2(l)(a] (2" + result.getString("pseudo"));
	    							break;
	    						case 8:
	    							d.add("(d[(5(l)(dI(5(l)(d] (d" + result.getString("pseudo"));
	    							break;
	    						case 9:
	    							d.add("(5[(d(l)(5D(d(l)(5] (5" + result.getString("pseudo"));
	    							break;
	    						case 10:
	    							d.add("(6[(e(l)(6A(e(l)(6] (e" + result.getString("pseudo"));
	    							break;
	    						case 11:
	    							d.add("(c[(4(l)(cM(4(l)(c] (c" + result.getString("pseudo"));
	    							break;
	    						case 12:
	    							d.add("(4[(c(l)(4A(c(l)(4] (4" + result.getString("pseudo"));
	    							break;
	    					}
						}
	    					
	    			}catch(Exception e) {
	    				e.printStackTrace();
	    			}
	    		}
	    		bdd.CloseFreeId(id2);
	    		getNetWorkClient().sendToAll(new NetWorkClient(new ClientPlayerList(d)));
	    		
		    }
	}
}
