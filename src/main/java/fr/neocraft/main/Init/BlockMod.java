package fr.neocraft.main.Init;


import cpw.mods.fml.common.registry.GameRegistry;

import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.BlockAntiFallSpawn;
import fr.neocraft.main.Blocks.BlockAntiFallStage;
import fr.neocraft.main.Blocks.BlockBasic;
import fr.neocraft.main.Blocks.BlockBat;
import fr.neocraft.main.Blocks.BlockBook;
import fr.neocraft.main.Blocks.BlockBoost;
import fr.neocraft.main.Blocks.BlockElevateur;
import fr.neocraft.main.Blocks.BlockEndPortal;
import fr.neocraft.main.Blocks.BlockFakeCobble;
import fr.neocraft.main.Blocks.BlockFireStatic;
import fr.neocraft.main.Blocks.BlockHorraireDragon;
import fr.neocraft.main.Blocks.BlockLum;
import fr.neocraft.main.Blocks.BlockNoelPresent;
import fr.neocraft.main.Blocks.BlockOre;
import fr.neocraft.main.Blocks.BlockParticle;
import fr.neocraft.main.Blocks.BlockPortal;
import fr.neocraft.main.Blocks.BlockPortalStage;
import fr.neocraft.main.Blocks.BlockPresent;
import fr.neocraft.main.Blocks.BlockSeed2DownAubergine;
import fr.neocraft.main.Blocks.BlockSeed2DownCorn;
import fr.neocraft.main.Blocks.BlockSeed2UpAubergine;
import fr.neocraft.main.Blocks.BlockSeed2UpCorn;
import fr.neocraft.main.Blocks.BlockSeedLaitue;
import fr.neocraft.main.Blocks.BlockSeedRadis;
import fr.neocraft.main.Blocks.BlockSeedTomate;
import fr.neocraft.main.Blocks.NeoBlockCarrot;
import fr.neocraft.main.Blocks.NeoBlockPotato;
import fr.neocraft.main.Blocks.NeoBlockWheat;
import fr.neocraft.main.Blocks.NeoExtendsPlate;
import fr.neocraft.main.Blocks.ItemBlock.ItemBlockCleaner;
import fr.neocraft.main.Blocks.Special.BlockBuilder;
import fr.neocraft.main.Blocks.Special.BlockFarmLand;
import fr.neocraft.main.Blocks.Special.BlockFuturistInput;
import fr.neocraft.main.Blocks.Special.BlockFuturistTerminalInput;
import fr.neocraft.main.Blocks.Special.BlockFuturistTransmetter;
import fr.neocraft.main.Blocks.Special.BlockPlanteur;
import fr.neocraft.main.Blocks.Special.BlockSpike;
import fr.neocraft.main.Blocks.Special.BoxUltimeBlock;
import fr.neocraft.main.Blocks.Special.BoxVoteBlock;
import fr.neocraft.main.Blocks.Special.ShopperBlock;
import fr.neocraft.main.Blocks.Special.SpawnerBlock;
import fr.neocraft.main.Blocks.Special.SpawnerCreeper;
import fr.neocraft.main.Blocks.Special.SpawnerSkeleton;
import fr.neocraft.main.Blocks.Special.SpawnerSpider;
import fr.neocraft.main.Blocks.Special.SpawnerZombie;
import fr.neocraft.main.Blocks.Special.TileEntityMobSpawner;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeTileNull;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxVoteTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BuilderTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.CarteTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.CleanerTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.FuturistInputTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.PlanterTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperPnjTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.TileEntityFarmLand;
import fr.neocraft.main.Proxy.Serveur.TileEntity.TileEntityNull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.block.material.Material;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.*;
import oortcloud.hungryanimals.entities.*;


public class BlockMod
{
    
	
    public static Block Barrier, StagePresent, firestatic, Blockbat ,BlockHDrag,FakeCobble, EnchantBook;
    
    public static Block PressurePlate, Portail_End, BlockFlame, BlockSmoke, lum_1, lum_2, lum_3, lum_4, Shopper, BoxVote, UltimeBox;
    
    public static Block  AntiFallStage, AntiFallSpawn, Planteur, Builder, StaticBlock;
    
    public static Block FuturInput, FuturTransmetter, FuturTerminalInput, BlockDetector;
    
    public static Block[] boost = new Block[8];
    public static Block[] portail = new Block[18];
    public static Block[] portailStage = new Block[1];
    private static String[] couleur = {"bleu", "bleu", "bleu", "jaune", "jaune", "jaune", "rose", "rose", "rose", "rouge", "rouge", "rouge", "vert", "vert", "vert", "violet", "violet", "violet"};
    public static Block NeoDium_Ore, Silithium_Ore, Titane_Ore, Mythrile_Ore;
    public static Block NeoDium_Block, Silithium_Block, Titane_Block, Mythrile_Block;
   
    public static Block IronSpike, DiamondSpike, TitaneSpike,  SilithiumSpike, FarmLand;
    public static Block Tomate_Culture, Corn_Culture_Up, Corn_Culture_Down, Aubergine_Culture_Up, Aubergine_Culture_down, Laitue_Culture, Radis_Culture, Wheat_culture, Carrot_Culture, Potato_Culture;
   

    public static Block OnyxR_Spike, OnyxR_Ore, OnyxR_Block;
    public static Block Onyx_Spike, Onyx_Ore, Onyx_Block,NeoDium_Spike;
    
    public static Block Elevateur1, Elevateur2, Elevateur3, BlockCleaner;
    
    public static Block[] Spawner;
    
    public static Block NoelPresent, BlockDivinTotem;

    public static void init()
    {
    	
    	 BlockDivinTotem = new fr.neocraft.main.Blocks.Special.BlockDivinTotem(Material.iron).setBlockName("DivinTotem").setCreativeTab(main.neocraft);
    	
    	NoelPresent = new BlockNoelPresent(Material.cloth).setBlockName("Noel Present").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":present");
    	
    	BlockCleaner = new fr.neocraft.main.Blocks.Special.BlockCleaner(Material.iron, 1).setHardness(3.5F).setResistance(10.0F).setBlockName("BlockCleaner").setCreativeTab(main.neocraft_futur).setBlockTextureName(neoreference.MOD_ID + ":BlockCleaner"); ;
    	
    	BlockDetector = new fr.neocraft.main.Blocks.Special.BlockDetector(Material.iron, 1).setBlockName("BlockDetector").setCreativeTab(main.neocraft_futur);
    	
    	FuturInput = new BlockFuturistInput(Material.iron, 1).setHardness(3.5F).setResistance(10.0F).setBlockName("FuturInput").setCreativeTab(main.neocraft_futur);
    	FuturTransmetter = new BlockFuturistTransmetter(Material.iron, 1).setHardness(3.5F).setResistance(10.0F).setBlockName("FuturTransmeter").setCreativeTab(main.neocraft_futur);
    	FuturTerminalInput = new BlockFuturistTerminalInput(Material.iron, 1).setHardness(3.5F).setResistance(10.0F).setBlockName("FuturTerminalInput").setCreativeTab(main.neocraft_futur);
    	
    	
    	Elevateur1 = new BlockElevateur(Material.iron, 1, 5).setBlockName("Elevateur1").setCreativeTab(main.neocraft_futur);
    	Elevateur2 = new BlockElevateur(Material.iron, 1, 10).setBlockName("Elevateur2").setCreativeTab(main.neocraft_futur);
    	Elevateur3 = new BlockElevateur(Material.iron, 1,20).setBlockName("Elevateur3").setCreativeTab(main.neocraft_futur);
    	
    	Wheat_culture=new NeoBlockWheat().setBlockName("crops").setBlockTextureName("wheat");
    	Carrot_Culture=new NeoBlockCarrot().setBlockName("carrots").setBlockTextureName("carrots");
    	Potato_Culture=new NeoBlockPotato().setBlockName("potatoes").setBlockTextureName("potatoes");
    	EnchantBook = new BlockBook().setBlockName("BlockBook").setBlockUnbreakable().setBlockTextureName(neoreference.MOD_ID + ":barrier" );
    	BlockHDrag = new BlockHorraireDragon().setBlockName("BlockHDrag").setCreativeTab(main.neocraft).setBlockUnbreakable();
    	firestatic = new BlockFireStatic().setLightLevel(1.0F).setBlockName("neofire").setBlockTextureName("fire").setCreativeTab(main.neocraft);
    	
    	Blockbat = new BlockBat(Material.air).setBlockName("Spawn Bat").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":barrier" ).setBlockUnbreakable();
    	
    	StagePresent = new BlockPresent(Material.cloth).setBlockName("Stage Present").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":present");
    	
    	Planteur = new BlockPlanteur(Material.rock).setHardness(3.5F).setResistance(10.0F).setCreativeTab(main.neocraft_futur).setStepSound(Block.soundTypePiston).setBlockName("Planteur").setBlockTextureName(neoreference.MOD_ID + ":Planteur"); 
    	Builder = new BlockBuilder(Material.rock).setHardness(3.5F).setResistance(10.0F).setCreativeTab(main.neocraft_futur).setStepSound(Block.soundTypePiston).setBlockName("Builder").setBlockTextureName(neoreference.MOD_ID + ":Builder"); 
    	StaticBlock = new BlockBasic(Material.rock, 1).setHardness(3.5F).setResistance(10.0F).setCreativeTab(main.neocraft_futur).setStepSound(Block.soundTypePiston).setBlockName("Static").setBlockTextureName(neoreference.MOD_ID + ":Planteur_default"); 
    	//Planteur_default
    	int[] idForPortal = new int[] { 200};
    	
    	for(int i = 0; i < idForPortal.length;i++)
	    {	
    		portailStage[i] = new BlockPortalStage("Stage", idForPortal[i]).setBlockName("Portal Stage "+ idForPortal[i]).setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Portal_Stage");
    	    		   
	    }
    	for(int i = 0; i < boost.length;i++)
	    {	
    		boost[i] = new BlockBoost(i).setBlockName("NeoWood-"+i);
    	    		   
	    }
 
    	OnyxR_Block = new BlockBasic(Material.iron, 4).setBlockName("OnyxR Block").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":OnyxR_Block" ).setHardness(12.0F).setLightLevel(1F).setResistance(16.5F).setStepSound(Block.soundTypeMetal); 
    	OnyxR_Ore = new BlockBasic(Material.iron, 4).setBlockName("OnyxR Ore").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":OnyxR_Ore" ).setHardness(12.0F).setLightLevel(1F).setResistance(16.5F).setStepSound(Block.soundTypeMetal); 
    	OnyxR_Spike= new BlockSpike("OnyxR", 60.0F).setHardness(9.5F).setResistance(33.0F).setCreativeTab(main.neocraft_armor);
    	
    	Onyx_Block = new BlockBasic(Material.iron, 3).setBlockName("Onyx Block").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Onyx_Block" ).setHardness(12.0F).setLightLevel(1F).setResistance(16.5F).setStepSound(Block.soundTypeMetal); 
    	Onyx_Ore = new BlockBasic(Material.iron, 3).setBlockName("Onyx Ore").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Onyx_Ore" ).setHardness(12.0F).setLightLevel(1F).setResistance(16.5F).setStepSound(Block.soundTypeMetal); 
    	Onyx_Spike= new BlockSpike("Onyx", 40.0F).setHardness(8.5F).setResistance(33.0F).setCreativeTab(main.neocraft_armor);
    	
    	NeoDium_Spike= new BlockSpike("NeoDium", 80.0F).setHardness(10.5F).setResistance(40.0F).setCreativeTab(main.neocraft_armor);
    	
    	
    	FakeCobble = new BlockFakeCobble();
    	Aubergine_Culture_down = new BlockSeed2DownAubergine().setBlockName("Aubergine Culture Down").setBlockTextureName(neoreference.MOD_ID + ":Aubergine"); 
    	Aubergine_Culture_Up = new BlockSeed2UpAubergine().setBlockName("Aubergine Culture Up").setBlockTextureName(neoreference.MOD_ID + ":Aubergine");
    	Laitue_Culture = new BlockSeedLaitue().setBlockName("Laitue Culture").setBlockTextureName(neoreference.MOD_ID + ":Laitue"); 
    	Radis_Culture = new BlockSeedRadis().setBlockName("Radis Culture").setBlockTextureName(neoreference.MOD_ID + ":Radis");
    	
    	UltimeBox = new BoxUltimeBlock(Material.iron).setBlockName("BoxUltime").setCreativeTab(main.neocraft);
    	
    	BoxVote = new BoxVoteBlock(Material.iron).setBlockName("BoxVote").setCreativeTab(main.neocraft);
    	Shopper = new ShopperBlock(Material.iron).setBlockName("Shopper").setHardness(1.5F).setResistance(10.0F).setCreativeTab(main.neocraft);
    	Corn_Culture_Down = new BlockSeed2DownCorn().setBlockName("Corn Culture Down").setBlockTextureName(neoreference.MOD_ID + ":Corn");
    	Corn_Culture_Up = new BlockSeed2UpCorn().setBlockName("Corn Culture Up").setBlockTextureName(neoreference.MOD_ID + ":Corn");
    	Tomate_Culture = new BlockSeedTomate().setBlockName("Tomate Culture").setBlockTextureName(neoreference.MOD_ID + ":Tomate");
    	FarmLand = new BlockFarmLand().setBlockName("FarmLand").setCreativeTab(main.neocraft);
    	 NeoDium_Ore = new BlockOre(Material.rock, 4).setBlockName("NeoDium Ore").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":NeoDium Ore" ).setHardness(9.0F).setLightLevel(0.7F).setResistance(10.0F).setStepSound(Block.soundTypeMetal);
         Silithium_Ore = new BlockOre(Material.rock, 4).setBlockName("Silithium Ore").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Silithium Ore" ).setHardness(7.0F).setLightLevel(0.5F).setResistance(9.0F).setStepSound(Block.soundTypeStone);
         Titane_Ore = new BlockOre(Material.rock, 3).setBlockName("Titane Ore").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Titane Ore" ).setHardness(5.0F).setLightLevel(0.1F).setResistance(7.0F).setStepSound(Block.soundTypeStone);
         Mythrile_Ore = new BlockOre(Material.rock, 2).setBlockName("Mythrile Ore").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Mythrile Ore" ).setHardness(3.0F).setResistance(5.0F).setStepSound(Block.soundTypeStone);
         NeoDium_Block = new BlockBasic(Material.iron, 4).setBlockName("NeoDium Block").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":NeoDium Block" ).setHardness(10.0F).setLightLevel(0.7F).setResistance(16.0F).setStepSound(Block.soundTypeMetal);
         Silithium_Block = new BlockBasic(Material.iron, 3).setBlockName("Silithium Block").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Silithium Block" ).setHardness(9.0F).setLightLevel(0.5F).setResistance(14.0F).setStepSound(Block.soundTypeStone);
         Titane_Block = new BlockBasic(Material.iron, 3).setBlockName("Titane Block").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Titane Block" ).setHardness(7.0F).setLightLevel(0.1F).setResistance(12.0F).setStepSound(Block.soundTypeStone);
         Mythrile_Block = new BlockBasic(Material.iron, 2).setBlockName("Mythrile Block").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":Mythrile Block" ).setHardness(4.0F).setResistance(8.0F).setStepSound(Block.soundTypeStone);
        
         IronSpike = new BlockSpike("Iron", 5.0F).setHardness(1.5F).setResistance(10.0F).setCreativeTab(main.neocraft_armor);
         DiamondSpike = new BlockSpike("Diamond", 10.0F).setHardness(3.0F).setResistance(15.0F).setCreativeTab(main.neocraft_armor);
         TitaneSpike = new BlockSpike("Titane", 20.0F).setHardness(4.5F).setResistance(20.0F).setCreativeTab(main.neocraft_armor);
         SilithiumSpike = new BlockSpike("Silithium", 40.0F).setHardness(8.5F).setResistance(30.0F).setCreativeTab(main.neocraft_armor);
         
        BlockSmoke = new BlockParticle(Material.iron, "smoke", 6.0F);
        BlockFlame = new BlockParticle(Material.iron, "flame", 6.0F);
        AntiFallStage = new BlockAntiFallStage(Material.rock, 6).setBlockName("Barrier Anti Fall Stage").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":barrier" ).setBlockUnbreakable().setLightOpacity(1);
        AntiFallSpawn = new BlockAntiFallSpawn(Material.rock, 6).setBlockName("Barrier Anti Fall spawn").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":barrier" ).setBlockUnbreakable().setLightOpacity(1);
        Barrier = new BlockBasic(Material.rock, 6).setBlockName("Barrier Admin").setCreativeTab(main.neocraft).setBlockTextureName(neoreference.MOD_ID + ":barrier" ).setBlockUnbreakable().setLightOpacity(1);
        lum_1 = new BlockBasic(Material.rock, 6).setLightLevel(1.0F).setBlockName("lumiere 1").setCreativeTab(main.neocraft_portal).setBlockTextureName(neoreference.MOD_ID + ":barrier" ).setBlockUnbreakable().setLightOpacity(1);
        lum_2 = new BlockBasic(Material.rock, 6).setLightLevel(2.0F).setBlockName("lumiere 2").setCreativeTab(main.neocraft_portal).setBlockTextureName(neoreference.MOD_ID + ":barrier" ).setBlockUnbreakable().setLightOpacity(1);
        lum_3 = new BlockBasic(Material.rock, 6).setLightLevel(3.0F).setBlockName("lumiere 3").setCreativeTab(main.neocraft_portal).setBlockTextureName(neoreference.MOD_ID + ":barrier" ).setBlockUnbreakable().setLightOpacity(1);
        lum_4 = new BlockLum(Material.rock, 6).setLightLevel(4.0F).setBlockName("lumiere 4").setCreativeTab(main.neocraft_portal).setBlockTextureName(neoreference.MOD_ID + ":barrier" ).setBlockUnbreakable().setLightOpacity(1);
        Portail_End = new BlockEndPortal(Material.rock).setCreativeTab(main.neocraft).setBlockName("End Portal").setBlockUnbreakable().setLightOpacity(1);
        
        PressurePlate = new NeoExtendsPlate( Material.rock, BlockPressurePlate.Sensitivity.players).setBlockName("pressurePlate").setCreativeTab(main.neocraft);
        
        
        
        int p = 1;
        for (int i = 0; i < portail.length; i++)
        {
        	portail[i] = new BlockPortal(couleur[i], p).setBlockName("Portail " + couleur[i] + " " + p).setCreativeTab(main.neocraft_portal).setHardness(-1.0F).setLightLevel(0.75F);
        	p++;
        	if(p == 4)
        	{
        		p = 1;
        	}
        }

        AllSpawner.put(0, EntitySkeleton.class);
        AllSpawner.put(1, EntityZombie.class);
        AllSpawner.put(2, EntitySpider.class);
        AllSpawner.put(3, EntityCreeper.class);
        AllSpawner.put(4, EntityBlaze.class);
        AllSpawner.put(5, EntityHungryPig.class);
        AllSpawner.put(6, EntityHungryChicken.class);
        AllSpawner.put(7, EntityHungryCow.class);
        AllSpawner.put(8, EntityHungrySheep.class);
        AllSpawner.put(9, EntityHungryMooshroom.class);
        
        
        Spawner = new Block[AllSpawner.size()];
        java.util.Iterator<Integer> it = AllSpawner.keySet().iterator();
        while(it.hasNext())
        {
        	int id = it.next();
        	Spawner[id] = new SpawnerBlock(Material.rock, 2, id).setHardness(5.0F).setCreativeTab(main.neocraft_spawner).setStepSound(Block.soundTypeMetal).setBlockName("Spawner"+AllSpawner.get(id).getName()).setBlockTextureName("mob_spawner");
        }

        
    }
    public static java.util.HashMap<Integer, Class> AllSpawner = new java.util.HashMap<Integer, Class>();
    public static void register()
    {
    	
    	
    	GameRegistry.registerTileEntity(TileEntityNull.class, neoreference.MOD_ID + ":TileEntityNull");
    //	GameRegistry.registerTileEntity(TileEntityEnchantmentTableNull.class, neoreference.MOD_ID + ":TileEntityEnchantmentTableNull");
    	GameRegistry.registerTileEntity(BoxUltimeTileNull.class, neoreference.MOD_ID + ":BoxUltimeTileNull");
    	GameRegistry.registerTileEntity(BoxVoteTileEntity.class, neoreference.MOD_ID + ":BoxVoteTile"); 
    	GameRegistry.registerTileEntity(BoxUltimeTileEntity.class, neoreference.MOD_ID + ":BoxUltimeTile"); 
    	GameRegistry.registerTileEntity(ShopperTileEntity.class, neoreference.MOD_ID + ":NeoShopperTile"); 
    	GameRegistry.registerTileEntity(ShopperPnjTileEntity.class, neoreference.MOD_ID + ":NeoShopperPnjTile"); 
		GameRegistry.registerTileEntity(TileEntityMobSpawner.class, neoreference.MOD_ID + ":MobSpawner");
		GameRegistry.registerTileEntity(TileEntityFarmLand.class, neoreference.MOD_ID + ":FarmLandTile");
		GameRegistry.registerTileEntity(CarteTileEntity.class, neoreference.MOD_ID + ":CarteTileEntity");
		GameRegistry.registerTileEntity(PlanterTileEntity.class, neoreference.MOD_ID + ":PlanterTileEntity");
		GameRegistry.registerTileEntity(BuilderTileEntity.class, neoreference.MOD_ID + ":BuilderTileEntity");
		GameRegistry.registerTileEntity(FuturistInputTileEntity.class, neoreference.MOD_ID + ":FuturistInputTileEntity");
		GameRegistry.registerTileEntity(CleanerTileEntity.class, neoreference.MOD_ID + ":CleanerTileEntity");
		GameRegistry.registerBlock(AntiFallStage, AntiFallStage.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(AntiFallSpawn, AntiFallSpawn.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(EnchantBook, EnchantBook.getUnlocalizedName().substring(5));
		
		
		
		
		GameRegistry.registerBlock(OnyxR_Block, OnyxR_Block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(OnyxR_Ore, OnyxR_Ore.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(OnyxR_Spike,OnyxR_Spike.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(Onyx_Block, Onyx_Block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Onyx_Ore, Onyx_Ore.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Onyx_Spike,Onyx_Spike.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(NeoDium_Spike,NeoDium_Spike.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(FakeCobble, FakeCobble.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BlockHDrag, "BlockHDrag");
		GameRegistry.registerBlock(Blockbat, Blockbat.getLocalizedName().substring(5));
		
		GameRegistry.registerBlock(Wheat_culture, "Wheat_culture");
		GameRegistry.registerBlock(Potato_Culture, "Potato_Culture");
		GameRegistry.registerBlock(Carrot_Culture, "Carrot_Culture"); 
		
		GameRegistry.registerBlock(firestatic, firestatic.getLocalizedName().substring(5));
		
		GameRegistry.registerBlock(StagePresent, StagePresent.getUnlocalizedName().substring(5));
		
		
    	for(int i = 0; i < portailStage.length;i++)
	    {	
    		GameRegistry.registerBlock(portailStage[i], portailStage[i].getUnlocalizedName().substring(5));		   
	    }
		
    	for(int i = 0; i < boost.length;i++)
	    {	
    		GameRegistry.registerBlock(boost[i], boost[i].getUnlocalizedName().substring(5));		   
	    }
		
		GameRegistry.registerBlock(Aubergine_Culture_down, Aubergine_Culture_down.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Aubergine_Culture_Up, Aubergine_Culture_Up.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Laitue_Culture, Laitue_Culture.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Radis_Culture, Radis_Culture.getUnlocalizedName().substring(5));
		
		GameRegistry.registerBlock(Shopper, Shopper.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(BoxVote, BoxVote.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(UltimeBox, UltimeBox.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(Portail_End, Portail_End.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(Corn_Culture_Down, Corn_Culture_Down.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(Corn_Culture_Up, Corn_Culture_Up.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(PressurePlate, PressurePlate.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(Tomate_Culture, Tomate_Culture.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(lum_1, lum_1.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(lum_2, lum_2.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(lum_3, lum_3.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(lum_4, lum_4.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(Barrier, Barrier.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(BlockSmoke, BlockSmoke.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(BlockFlame, BlockFlame.getUnlocalizedName().substring(5));
        for (int i = 0; i < portail.length; i++)
        {
        	GameRegistry.registerBlock(portail[i], portail[i].getUnlocalizedName().substring(5));
        }
        


        	GameRegistry.registerBlock(FarmLand, FarmLand.getUnlocalizedName().substring(5));

        	GameRegistry.registerBlock(IronSpike, IronSpike.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(DiamondSpike, DiamondSpike.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(TitaneSpike, TitaneSpike.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(SilithiumSpike, SilithiumSpike.getUnlocalizedName().substring(5));
            
            GameRegistry.registerBlock(NeoDium_Ore, NeoDium_Ore.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(Silithium_Ore, Silithium_Ore.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(Titane_Ore, Titane_Ore.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(Mythrile_Ore, Mythrile_Ore.getUnlocalizedName().substring(5));
            
            GameRegistry.registerBlock(NeoDium_Block, NeoDium_Block.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(Silithium_Block, Silithium_Block.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(Titane_Block, Titane_Block.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(Mythrile_Block, Mythrile_Block.getUnlocalizedName().substring(5));

            
            java.util.Iterator<Integer> it = AllSpawner.keySet().iterator();
            while(it.hasNext())
            {
            	int id = it.next();
            	GameRegistry.registerBlock(Spawner[id], Spawner[id].getUnlocalizedName().substring(5));
            }
            
            GameRegistry.registerBlock(Planteur, Planteur.getUnlocalizedName().substring(5));
            
            GameRegistry.registerBlock(Builder, Builder.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(StaticBlock, StaticBlock.getUnlocalizedName().substring(5));
            
            GameRegistry.registerBlock(Elevateur1,Elevateur1.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(Elevateur2,Elevateur2.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(Elevateur3,Elevateur3.getUnlocalizedName().substring(5));
            
            GameRegistry.registerBlock(FuturInput,FuturInput.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(FuturTransmetter,FuturTransmetter.getUnlocalizedName().substring(5));
            GameRegistry.registerBlock(FuturTerminalInput,FuturTerminalInput.getUnlocalizedName().substring(5));
     
         GameRegistry.registerBlock(BlockDetector, BlockDetector.getUnlocalizedName().substring(5));
         
         GameRegistry.registerBlock(NoelPresent, NoelPresent.getUnlocalizedName().substring(5));
         
         
         GameRegistry.registerBlock(BlockCleaner, ItemBlockCleaner.class ,BlockCleaner.getUnlocalizedName().substring(5));
         GameRegistry.registerBlock(BlockDivinTotem, BlockDivinTotem.getUnlocalizedName().substring(5));

    }
    
}


    