package fr.neocraft.main.Init;

import java.util.ArrayList;
import java.util.Iterator;

import fr.neocraft.main.Item.*;
import info.ata4.minecraft.dragon.server.entity.breeds.DragonBreed;
import info.ata4.minecraft.dragon.server.entity.helper.DragonBreedRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Blocks.BlockPortal;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack; 
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.item.ItemFood;
import net.minecraft.world.World;


public class ItemMod
{
		public static ToolMaterial NeoDiumHAR = EnumHelper.addToolMaterial("NeoDium Hammer", 4, 3951, 18.5F, 7.5F, 2);
		public static ToolMaterial OnyxRHAR = EnumHelper.addToolMaterial("OnyxR Hammer", 4, 3251, 17.5F, 6.9F, 3);
	    public static ToolMaterial SilithiumHAR = EnumHelper.addToolMaterial("Silithium Hammer", 4, 2556, 16.0F, 6.0F, 4);
	    public static ToolMaterial OnyxHAR = EnumHelper.addToolMaterial("Onyx Hammer", 3, 2153, 13.5F, 4.9F, 6);
	    public static ToolMaterial TitaneHAR = EnumHelper.addToolMaterial("Titane Hammer", 3, 1985, 11.25F, 3.25F, 8);
	    public static ToolMaterial DiamondHAR = EnumHelper.addToolMaterial("Diamond Hammer", 3, 1761, 8.0F, 3.0F, 10);
	    public static ToolMaterial MythrileHAR = EnumHelper.addToolMaterial("Mythrile Hammer", 3, 1252, 6.5F, 2.5F, 15);
	    public static ToolMaterial IronHAR = EnumHelper.addToolMaterial("Iron Hammer", 2, 265, 3.5F, 2.5F, 12);
	    public static ToolMaterial GoldHAR = EnumHelper.addToolMaterial("Gold Hammer", 1, 150, 2.0F, 2.0F, 40);
	    public static ToolMaterial StoneHAR = EnumHelper.addToolMaterial("Stone Hammer", 1, 100, 1.5F, 1.5F, 20);
	    public static ToolMaterial WoodHAR = EnumHelper.addToolMaterial("Wood Hammer", 1, 75, 1.0F, 1.0F, 30);

	    public static ToolMaterial EventSword2 = EnumHelper.addToolMaterial("EventSword2", 4,4500, 23.0F, 11.5F, 1);
	    public static ToolMaterial EventSword1 = EnumHelper.addToolMaterial("EventSword1", 4, 2356, 18.5F, 8.0F, 4);
	    public static ToolMaterial NeoDium = EnumHelper.addToolMaterial("NeoDium", 4, 3751, 21.0F, 9.5F, 2);
	    public static ToolMaterial NeoDiumF = EnumHelper.addToolMaterial("NeoDiumF", 4, 3751, 21.0F, 12.5F, 1);
	    public static ToolMaterial OnyxR = EnumHelper.addToolMaterial("OnyxR", 4, 4500, 20.0F, 8.9F, 3);
	    public static ToolMaterial OnyxRF = EnumHelper.addToolMaterial("OnyxRF", 4, 4500, 20.0F, 10F, 2);
	    public static ToolMaterial Onyx = EnumHelper.addToolMaterial("Onyx", 4, 2120, 17.0F, 7.5F, 6);
	    public static ToolMaterial OnyxF = EnumHelper.addToolMaterial("OnyxF", 4, 2120, 17.0F, 9F, 4);
	    
	    
	    public static ToolMaterial Silithium = EnumHelper.addToolMaterial("Silithium", 4, 2356, 18.5F, 8.0F, 4);
	    public static ToolMaterial Titane = EnumHelper.addToolMaterial("Titane", 4, 1785, 11.25F, 5.0F, 8);

	    public static ToolMaterial Mythrile = EnumHelper.addToolMaterial("Mythrile", 3, 1052, 6.5F, 1.7F, 15);
	    public static ToolMaterial Marchand = EnumHelper.addToolMaterial("Mythrile", 3, 1052, 6.5F, 4F, 15);
	    
	    public static ArmorMaterial NeoDiumAR = EnumHelper.addArmorMaterial("NeoDium Armor", 235, new int[]{6, 10, 9, 7}, 3);
	    public static ArmorMaterial OnyxRAR = EnumHelper.addArmorMaterial("OnyxR Armor", 300, new int[]{6, 9, 8, 7}, 5);
	    public static ArmorMaterial SilithiumAR = EnumHelper.addArmorMaterial("Silithium Armor", 125, new int[]{5, 9, 8, 6}, 10);
	    public static ArmorMaterial GodAR = EnumHelper.addArmorMaterial("God Armor", 500, new int[]{6, 9, 8, 7}, 2);
	    
	    public static ArmorMaterial OnyxAR = EnumHelper.addArmorMaterial("Onyx Armor", 110, new int[]{5, 8, 7, 5}, 15);
	    public static ArmorMaterial TitaneAR = EnumHelper.addArmorMaterial("Titane Armor", 105, new int[]{3, 8, 7, 4}, 16);
	    public static ArmorMaterial MythrileAR = EnumHelper.addArmorMaterial("Mythrile Armor", 55, new int[]{1, 6, 4, 1}, 18);
    
    public static Item Copyright_NeoCraft,Coca, Vote_Key, Ultime_Key, CModoR1, CModoR2, CAdminR3, NeoDium_Core, tuteur, Portal_Transformer,NeoMap, gardien, epeemarchand, bouclier, p, ItemGrade;
    public static Item[] guiDrag = new Item[20], Itemkit = new Item[9], ItemDragon = new Item[10], ItemDragon2= new Item[10];
    private static String[] StringKit = new String[] { "tyran","mineur", "archer", "tank", "chasseur", "fermier", "combattant", "eleveur", "sorcier" };
    public static Item 	NeoDium_Faux, NeoDium_Hammer, Silithium_Hammer, Titane_Hammer, Mythrile_Hammer, Diamond_Hammer, Iron_Hammer, Gold_Hammer, Stone_Hammer, Wood_Hammer;
    public static Item Titane_Ingot, Mythrile_Ingot, NeoDium_Ingot, Silithium_Ingot;
    public static Item NeoDium_Sword, NeoDium_Pickaxe, NeoDium_Axe, NeoDium_Shovel, Silithium_Sword, Silithium_Pickaxe, Silithium_Axe, Silithium_Shovel, Titane_Sword, Titane_Pickaxe, Titane_Axe, Titane_Shovel, Mythrile_Sword, Mythrile_Pickaxe, Mythrile_Axe, Mythrile_Shovel;
    public static Item NeoDium_Armor, Silithium_Armor, Titane_Armor, Mythrile_Armor;
    public static Item NeoDium_Helmet, NeoDium_ChestPlate, NeoDium_Leggings, NeoDium_Boot;
    public static Item Silithium_Helmet, Silithium_ChestPlate, Silithium_Leggings, Silithium_Boot;
    public static Item Titane_Helmet, Titane_ChestPlate, Titane_Leggings, Titane_Boot;
    public static Item Mythrile_Helmet, Mythrile_ChestPlate, Mythrile_Leggings, Mythrile_Boot;
    public static Item Iron_Nuggets, Diamond_Nuggets, Gold_Nuggets, Emerald_Nuggets;
    public static Item Mythrile_Nuggets, Titane_Nuggets, NeoDium_Nuggets, Silithium_Nuggets;
    
    public static Item OnyxR_Faux, OnyxR_Stick, OnyxR_Sword, OnyxR_Pickaxe, OnyxR_Shovel, OnyxR_Axe, OnyxR_Ingot, OnyxR_Nuggets, OnyxR_Helmet, OnyxR_ChestPlate, OnyxR_Leggings, OnyxR_Boot, OnyxR_Hammer;
    
    public static Item Onyx_Faux, Onyx_Stick, Onyx_Sword, Onyx_Pickaxe, Onyx_Shovel, Onyx_Axe, Onyx_Ingot, Onyx_Nuggets, Onyx_Helmet, Onyx_ChestPlate, Onyx_Leggings, Onyx_Boot, Onyx_Hammer;
    
    public static Item NeoDium_Stick,  Powerfull_Stick, SwordEvent_1, SwordEvent_2, SwordEvent_3, SwordEvent_4, SwordEvent_5, SwordEvent_6, SwordEvent_7, SwordEvent_8;
    
    public static Item Tomate_Seeds, Tomate, Corn_Seeds, Corn, Aubergine, Aubergine_Seed, Laitue, Radis, Radis_Seed, Neo_wheat_seeds, Neo_Potato, Neo_Carrot, fishSpawnEgg,fishBucket,ironHook;
   
    public static Item ItemWood, ItemCobble, ItemWool1, ItemWool2;
    
    public static Item God_Boot, God_Leggings, God_ChestPlate, God_Helmet, Divin_Soulth;
    
    public static void Init()
    {
    	
    	
    	NeoDiumHAR.setRepairItem(new ItemStack(ItemMod.NeoDium_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
		OnyxRHAR.setRepairItem(new ItemStack(ItemMod.OnyxR_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    SilithiumHAR.setRepairItem(new ItemStack(ItemMod.Silithium_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	  
	    OnyxHAR.setRepairItem(new ItemStack(ItemMod.Onyx_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    TitaneHAR.setRepairItem(new ItemStack(ItemMod.Titane_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    DiamondHAR.setRepairItem(new ItemStack(Items.diamond, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    MythrileHAR.setRepairItem(new ItemStack(ItemMod.Mythrile_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    IronHAR.setRepairItem(new ItemStack(Items.iron_ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    GoldHAR.setRepairItem(new ItemStack(Items.gold_ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    NeoDium.setRepairItem(new ItemStack(ItemMod.NeoDium_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    NeoDiumF.setRepairItem(new ItemStack(ItemMod.NeoDium_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    OnyxR.setRepairItem(new ItemStack(ItemMod.OnyxR_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    OnyxRF.setRepairItem(new ItemStack(ItemMod.OnyxR_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    Onyx.setRepairItem(new ItemStack(ItemMod.Onyx_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    OnyxF.setRepairItem(new ItemStack(ItemMod.Onyx_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    Silithium.setRepairItem(new ItemStack(Items.apple, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    Titane.setRepairItem(new ItemStack(ItemMod.Titane_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    Mythrile.setRepairItem(new ItemStack(ItemMod.Mythrile_Ingot, 1, net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE));
	    
	    NeoDiumAR.customCraftingMaterial = ItemMod.NeoDium_Ingot;
	    OnyxRAR.customCraftingMaterial = ItemMod.OnyxR_Ingot;
	    SilithiumAR.customCraftingMaterial = ItemMod.Silithium_Ingot;
	    OnyxAR.customCraftingMaterial= ItemMod.Onyx_Ingot;
	    TitaneAR.customCraftingMaterial= ItemMod.Titane_Ingot;
	    MythrileAR.customCraftingMaterial= ItemMod.Mythrile_Ingot;
	    GodAR.customCraftingMaterial = ItemMod.Divin_Soulth;
	    
    	SwordEvent_1 = new ItemSwordData(ItemMod.EventSword1).setUnlocalizedName("Sword event1").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":event1");
    	SwordEvent_2 = new ItemSwordData(ItemMod.EventSword1).setUnlocalizedName("Sword event2").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":event2");
    	SwordEvent_3 = new ItemSwordData(ItemMod.EventSword1).setUnlocalizedName("Sword event3").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":event3");
    	SwordEvent_4 = new ItemSwordData(ItemMod.EventSword2).setUnlocalizedName("Sword event4").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":event4");
    	SwordEvent_5 = new ItemSwordData(ItemMod.EventSword1).setUnlocalizedName("Sword event5").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":event5");
    	SwordEvent_6 = new ItemSwordData(ItemMod.EventSword1).setUnlocalizedName("Sword event6").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":event6");
    	SwordEvent_7 = new ItemSwordData(ItemMod.EventSword1).setUnlocalizedName("Sword event7").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":event7");
    	SwordEvent_8 = new ItemSwordData(ItemMod.EventSword1).setUnlocalizedName("Sword event8").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":event8");

    	Divin_Soulth= new Item().setUnlocalizedName("Divin_Soulth").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":Divin_Soulth");
    	
    	ItemWood = new Item().setUnlocalizedName("Item Wood").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":ItemWood");
    	ItemCobble = new Item().setUnlocalizedName("Item Cobble").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":ItemCobble");
    	ItemWool1 = new Item().setUnlocalizedName("Item Wool1").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":ItemWool1");
    	ItemWool2 = new Item().setUnlocalizedName("Item Wool2").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":ItemWool2");
    	
    	NeoDium_Stick = new Item().setUnlocalizedName("NeoDium Stick").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium_Stick");
    	Powerfull_Stick = new Item().setUnlocalizedName("Powerfull Stick").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Powerfull_Stick");
    	
    	OnyxR_Ingot = new Item().setUnlocalizedName("OnyxR Ingot").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Ingot");
    	OnyxR_Nuggets = new Item().setUnlocalizedName("OnyxR Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Nuggets");
    	OnyxR_Hammer = new HammerData(ItemMod.OnyxRHAR).setUnlocalizedName("OnyxR Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Hammer");
    	OnyxR_Sword = new ItemSwordData(ItemMod.OnyxR).setUnlocalizedName("OnyxR Sword").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Sword");
    	OnyxR_Pickaxe = new ItemPickaxeData(ItemMod.OnyxR).setUnlocalizedName("OnyxR Pickaxe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Pickaxe");
    	OnyxR_Axe = new ItemAxeData(ItemMod.OnyxR).setUnlocalizedName("OnyxR Axe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Axe");
    	OnyxR_Shovel = new ItemShovelData(ItemMod.OnyxR).setUnlocalizedName("OnyxR Shovel").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Shovel");
    	OnyxR_Helmet = new ItemArmorData(OnyxRAR, 0).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Helmet" ).setUnlocalizedName("OnyxR Helmet");
    	OnyxR_ChestPlate = new ItemArmorData(OnyxRAR, 1).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_ChestPlate" ).setUnlocalizedName("OnyxR ChestPlate");
    	OnyxR_Leggings = new ItemArmorData(OnyxRAR, 2).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Leggings" ).setUnlocalizedName("OnyxR Leggings");
    	OnyxR_Boot = new ItemArmorData(OnyxRAR, 3).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Boot").setUnlocalizedName("OnyxR Boot");
        OnyxR_Stick = new Item().setUnlocalizedName("OnyxR Stick").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Stick");
        OnyxR_Faux = new ItemFaux(ItemMod.OnyxRF).setUnlocalizedName("OnyxR Faux").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":OnyxR_Faux");
    	
        Onyx_Ingot = new Item().setUnlocalizedName("Onyx Ingot").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Ingot");
    	Onyx_Nuggets = new Item().setUnlocalizedName("Onyx Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Nuggets");
    	Onyx_Hammer = new HammerData(ItemMod.OnyxHAR).setUnlocalizedName("Onyx Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Hammer");
    	Onyx_Sword = new ItemSwordData(ItemMod.Onyx).setUnlocalizedName("Onyx Sword").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Sword");
    	Onyx_Pickaxe = new ItemPickaxeData(ItemMod.Onyx).setUnlocalizedName("OnyxR Pickaxe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Pickaxe");
    	Onyx_Axe = new ItemAxeData(ItemMod.Onyx).setUnlocalizedName("Onyx Axe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Axe");
    	Onyx_Shovel = new ItemShovelData(ItemMod.Onyx).setUnlocalizedName("Onyx Shovel").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Shovel");
    	Onyx_Helmet = new ItemArmorData(OnyxAR, 0).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Helmet" ).setUnlocalizedName("Onyx Helmet");
    	Onyx_ChestPlate = new ItemArmorData(OnyxAR, 1).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_ChestPlate" ).setUnlocalizedName("Onyx ChestPlate");
    	Onyx_Leggings = new ItemArmorData(OnyxAR, 2).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Leggings" ).setUnlocalizedName("Onyx Leggings");
    	Onyx_Boot = new ItemArmorData(OnyxAR, 3).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Boot").setUnlocalizedName("Onyx Boot");
        Onyx_Stick = new Item().setUnlocalizedName("Onyx Stick").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Stick");
        Onyx_Faux = new ItemFaux(ItemMod.OnyxF).setUnlocalizedName("Onyx Faux").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Onyx_Faux");
    	
        NeoDium_Faux = new ItemFaux(ItemMod.NeoDiumF).setUnlocalizedName("NeoDium Faux").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium_Faux");
    	
        
    	Neo_wheat_seeds = new NeoSeeds(BlockMod.Wheat_culture, BlockMod.FarmLand).setUnlocalizedName("seeds").setTextureName("seeds_wheat");
    	Neo_Potato = new NeoSeedFood(1, 0.3F,BlockMod.Potato_Culture, BlockMod.FarmLand).setUnlocalizedName("potato").setTextureName("potato");
    	Neo_Carrot = new NeoSeedFood(4, 0.6F,BlockMod.Carrot_Culture, BlockMod.FarmLand).setUnlocalizedName("carrots").setTextureName("carrot");
    	
    	
    	
    	int o =0;
    	for(int i = 0; i < ItemDragon.length; i++)
    	{
    		ItemDragon[i] = new ItemDragon(DragonBreedRegistry.getInstance().getBreedById(i));
        	ItemDragon2[i] = new ItemDragon2(DragonBreedRegistry.getInstance().getBreedById(i));
    	}
    
    	p = new ItemPiece().setUnlocalizedName("piece").setCreativeTab(main.neocraft);
    	ItemGrade = new ItemGrade();
    	Coca = new ItemFood(-2, 0.1F , false).setPotionEffect(Potion.confusion.id, 5, 0, 0.6F).setTextureName(neoreference.MOD_ID + ":coca").setCreativeTab(main.neocraft).setUnlocalizedName("coca");
    	
    	epeemarchand = new ItemSwordData(ItemMod.Marchand).setUnlocalizedName("Marchand Sword").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":EpeeMarchand");
    	gardien = new ItemGardien().setUnlocalizedName("Gardien Spawn").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":gardien");
    	NeoMap = new CarteMapEmpty().setUnlocalizedName("map").setTextureName("map_filled");
    	Portal_Transformer=new Item().setUnlocalizedName("Portal Transformer").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":Portal_Transformer");
    	bouclier = new ItemBouclier().setUnlocalizedName("NeoBouclier").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":Bouclier");
    	tuteur = new Item().setUnlocalizedName("Tuteur").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":NeoTuteur");
    	
    	Aubergine_Seed = new NeoSeedsExtendStage2(BlockMod.Aubergine_Culture_down, BlockMod.Aubergine_Culture_Up, Blocks.farmland).setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Aubergine Seeds").setTextureName(neoreference.MOD_ID + ":Aubergine Seed");
    	Aubergine = new Item().setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Aubergine").setTextureName(neoreference.MOD_ID + ":Aubergine");
    	
    	Laitue = new NeoSeedsExtendStage(BlockMod.Laitue_Culture, Blocks.farmland).setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Laitue Seeds").setTextureName(neoreference.MOD_ID + ":Laitue");
    	
    	Radis_Seed = new NeoSeedsExtendStage(BlockMod.Radis_Culture, Blocks.farmland).setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Radis Seed").setTextureName(neoreference.MOD_ID + ":Radis Seed");
    	Radis = new Item().setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Radis").setTextureName(neoreference.MOD_ID + ":Radis");
    	
    	Corn_Seeds = new NeoSeedsExtendStage2(BlockMod.Corn_Culture_Down, BlockMod.Corn_Culture_Up, Blocks.farmland).setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Corn Seeds").setTextureName(neoreference.MOD_ID + ":Corn Seeds");
    	Corn = new Item().setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Corn").setTextureName(neoreference.MOD_ID + ":Corn");
    	
    	Tomate_Seeds = new NeoSeedsExtendStage(BlockMod.Tomate_Culture, Blocks.farmland).setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Tomate Seeds").setTextureName(neoreference.MOD_ID + ":Tomate Seeds");
    	Tomate = new Item().setCreativeTab(main.neocraft_seeds).setUnlocalizedName("Tomate").setTextureName(neoreference.MOD_ID + ":Tomate");
    	
    	Iron_Nuggets = new Item().setUnlocalizedName("Iron Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Iron Nuggets");
        
        Diamond_Nuggets = new Item().setUnlocalizedName("Diamond Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Diamond Nuggets");
        Emerald_Nuggets = new Item().setUnlocalizedName("Emerald Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Emerald Nuggets");
        CModoR1 = new Item().setUnlocalizedName("Carte Modo R1").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":Carte Modo R1");
        CModoR2 = new Item().setUnlocalizedName("Carte Modo R2").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":Carte Modo R2");
        CAdminR3 = new Item().setUnlocalizedName("Carte Admin R3").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":Carte Admin R3");
        Copyright_NeoCraft = new Item().setUnlocalizedName("Copyright NeoCraft").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":COP");
        
        Ultime_Key = new ItemUltimeKey().setUnlocalizedName("Ultime Key").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":UltimeKey");
        Vote_Key = new ItemVoteKey().setUnlocalizedName("Vote Key").setCreativeTab(main.neocraft).setTextureName(neoreference.MOD_ID + ":VoteKey");
        NeoDium_Ingot = new Item().setUnlocalizedName("NeoDium Ingot").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Ingot");
        NeoDium_Nuggets = new Item().setUnlocalizedName("NeoDium Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Nuggets");
        NeoDium_Core = new ItemNeoDiumCore().setUnlocalizedName("NeoDium Core").setMaxStackSize(16).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Core");
        
        Silithium_Ingot = new Item().setUnlocalizedName("Silithium Ingot").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Ingot");
        Silithium_Nuggets = new Item().setUnlocalizedName("Silithium Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Nuggets");

        Titane_Ingot = new Item().setUnlocalizedName("Titane Ingot").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Ingot");
        Titane_Nuggets = new Item().setUnlocalizedName("Titane Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Nuggets");
        
        Mythrile_Ingot = new Item().setUnlocalizedName("Mythrile Ingot").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Ingot");
        Mythrile_Nuggets = new Item().setUnlocalizedName("Mythrile Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Nuggets");
        
        Mythrile_Ingot = new Item().setUnlocalizedName("Mythrile Ingot").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Ingot");
        Mythrile_Nuggets = new Item().setUnlocalizedName("Mythrile Nuggets").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Nuggets");
        
        NeoDium_Hammer = new HammerData(ItemMod.NeoDiumHAR).setUnlocalizedName("NeoDium Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Hammer");
        Silithium_Hammer = new HammerData(ItemMod.SilithiumHAR).setUnlocalizedName("Silithium Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Hammer");
        Titane_Hammer = new HammerData(ItemMod.TitaneHAR).setUnlocalizedName("Titane Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Hammer");
        Mythrile_Hammer = new HammerData(ItemMod.MythrileHAR).setUnlocalizedName("Mythrile Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Hammer");
        Diamond_Hammer = new HammerData(ItemMod.DiamondHAR).setUnlocalizedName("Diamond Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Diamond Hammer");
        Iron_Hammer = new HammerData(ItemMod.IronHAR).setUnlocalizedName("Iron Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Iron Hammer");
        Gold_Hammer = new HammerData(ItemMod.GoldHAR).setUnlocalizedName("Gold Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Gold Hammer");
        Stone_Hammer = new HammerData(ItemMod.StoneHAR).setUnlocalizedName("Stone Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Stone Hammer");
        Wood_Hammer = new HammerData(ItemMod.WoodHAR).setUnlocalizedName("Wood Hammer").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Wood Hammer");
        
        NeoDium_Sword = new ItemSwordData(ItemMod.NeoDium).setUnlocalizedName("NeoDium Sword").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Sword");
        NeoDium_Pickaxe = new ItemPickaxeData(ItemMod.NeoDium).setUnlocalizedName("NeoDium Pickaxe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Pickaxe");
        NeoDium_Axe = new ItemAxeData(ItemMod.NeoDium).setUnlocalizedName("NeoDium Axe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Axe");
        NeoDium_Shovel = new ItemShovelData(ItemMod.NeoDium).setUnlocalizedName("NeoDium Shovel").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Shovel");
        
        Silithium_Sword = new ItemSwordData(ItemMod.Silithium).setUnlocalizedName("Silithium Sword").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Sword");
        Silithium_Pickaxe = new ItemPickaxeData(ItemMod.Silithium).setUnlocalizedName("Silithium Pickaxe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Pickaxe");
        Silithium_Axe = new ItemAxeData(ItemMod.Silithium).setUnlocalizedName("Silithium Axe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Axe");
        Silithium_Shovel = new ItemShovelData(ItemMod.Silithium).setUnlocalizedName("Silithium Shovel").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Shovel");
        
        Titane_Sword = new ItemSwordData(ItemMod.Titane).setUnlocalizedName("Titane Sword").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Sword");
        Titane_Pickaxe = new ItemPickaxeData(ItemMod.Titane).setUnlocalizedName("Titane Pickaxe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Pickaxe");
        Titane_Axe = new ItemAxeData(ItemMod.Titane).setUnlocalizedName("Titane Axe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Axe");
        Titane_Shovel = new ItemShovelData(ItemMod.Titane).setUnlocalizedName("Titane Shovel").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Shovel");
        
        Mythrile_Sword = new ItemSwordData(ItemMod.Mythrile).setUnlocalizedName("Mythrile Sword").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Sword");
        Mythrile_Pickaxe = new ItemPickaxeData(ItemMod.Mythrile).setUnlocalizedName("Mythrile Pickaxe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Pickaxe");
        Mythrile_Axe = new ItemAxeData(ItemMod.Mythrile).setUnlocalizedName("Mythrile Axe").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Axe");
        Mythrile_Shovel = new ItemShovelData(ItemMod.Mythrile).setUnlocalizedName("Mythrile Shovel").setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Shovel");
        
        NeoDium_Helmet = new ItemArmorData(NeoDiumAR, 0).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Helmet" ).setUnlocalizedName("NeoDium Helmet");
        NeoDium_ChestPlate = new ItemArmorData(NeoDiumAR, 1).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium ChestPlate" ).setUnlocalizedName("NeoDium ChestPlate");
        NeoDium_Leggings = new ItemArmorData(NeoDiumAR, 2).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Leggings" ).setUnlocalizedName("NeoDium Leggings");
        NeoDium_Boot = new ItemArmorData(NeoDiumAR, 3).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":NeoDium Boot").setUnlocalizedName("NeoDium Boot");
        
        Silithium_Helmet = new ItemArmorData(SilithiumAR, 0).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Helmet" ).setUnlocalizedName("Silithium Helmet");
        Silithium_ChestPlate = new ItemArmorData(SilithiumAR, 1).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium ChestPlate" ).setUnlocalizedName("Silithium ChestPlate");
        Silithium_Leggings = new ItemArmorData(SilithiumAR, 2).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Leggings" ).setUnlocalizedName("Silithium Leggings");
        Silithium_Boot = new ItemArmorData(SilithiumAR, 3).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Silithium Boot" ).setUnlocalizedName("Silithium Boot");

        God_Helmet = new ItemArmorData(GodAR, 0).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":God Helmet" ).setUnlocalizedName("God Helmet");
        God_ChestPlate = new ItemArmorData(GodAR, 1).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":God ChestPlate" ).setUnlocalizedName("God ChestPlate");
        God_Leggings = new ItemArmorData(GodAR, 2).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":God Leggings" ).setUnlocalizedName("God Leggings");
        God_Boot = new ItemArmorData(GodAR, 3).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":God Boot" ).setUnlocalizedName("God Boot");

        
        Titane_Helmet = new ItemArmorData(TitaneAR, 0).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Helmet" ).setUnlocalizedName("Titane Helmet");
        Titane_ChestPlate = new ItemArmorData(TitaneAR, 1).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane ChestPlate" ).setUnlocalizedName("Titane ChestPlate");
        Titane_Leggings = new ItemArmorData(TitaneAR, 2).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Leggings" ).setUnlocalizedName("Titane Leggings");
        Titane_Boot = new ItemArmorData(TitaneAR, 3).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Titane Boot" ).setUnlocalizedName("Titane Boot");
		
		Mythrile_Helmet = new ItemArmorData(MythrileAR, 0).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Helmet" ).setUnlocalizedName("Mythrile Helmet");
        Mythrile_ChestPlate = new ItemArmorData(MythrileAR, 1).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile ChestPlate" ).setUnlocalizedName("Mythrile ChestPlate");
        Mythrile_Leggings = new ItemArmorData(MythrileAR, 2).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Leggings" ).setUnlocalizedName("Mythrile Leggings");
        Mythrile_Boot = new ItemArmorData(MythrileAR, 3).setCreativeTab(main.neocraft_armor).setTextureName(neoreference.MOD_ID + ":Mythrile Boot" ).setUnlocalizedName("Mythrile Boot");

        for (int i = 0; i < guiDrag.length; i++)
        {
        	guiDrag[i] = new ItemGuiDragon(i + 1);
        }
        
        for (int i = 0; i < Itemkit.length; i++)
        {
        	Itemkit[i] = new ItemKit(StringKit[i]);
        }
    }
    
    public static void register()
    {
    	
    	
    	GameRegistry.registerItem(Aubergine_Seed, "Aubergine_Seed");
    	GameRegistry.registerItem(Aubergine, "Aubergine");
    	GameRegistry.registerItem(Laitue, "Laitue");
    	GameRegistry.registerItem(Radis, "Radis");
    	GameRegistry.registerItem(Radis_Seed, "Radis_Seed");

    	GameRegistry.registerItem(SwordEvent_1, "SwordEvent_1");
    	GameRegistry.registerItem(SwordEvent_2, "SwordEvent_2");
    	GameRegistry.registerItem(SwordEvent_3, "SwordEvent_3");
    	GameRegistry.registerItem(SwordEvent_4, "SwordEvent_4");
    	GameRegistry.registerItem(SwordEvent_5, "SwordEvent_5");
    	GameRegistry.registerItem(SwordEvent_6, "SwordEvent_6");
    	GameRegistry.registerItem(SwordEvent_7, "SwordEvent_7");
    	GameRegistry.registerItem(SwordEvent_8, "SwordEvent_8");

    	
    	GameRegistry.registerItem(Powerfull_Stick, "Powerfull_Stick");
    	GameRegistry.registerItem(NeoDium_Stick, "NeoDium_Stick");
    	
    	GameRegistry.registerItem(OnyxR_Axe, "OnyxR_Axe");
    	GameRegistry.registerItem(OnyxR_Boot, "OnyxR_Boot");
    	GameRegistry.registerItem(OnyxR_ChestPlate, "OnyxR_ChestPlate");
    	GameRegistry.registerItem(OnyxR_Hammer, "OnyxR_Hammer");
    	GameRegistry.registerItem(OnyxR_Helmet, "OnyxR_Helmet");
    	GameRegistry.registerItem(OnyxR_Ingot, "OnyxR_Ingot");
    	GameRegistry.registerItem(OnyxR_Leggings, "OnyxR_Leggings");
    	GameRegistry.registerItem(OnyxR_Nuggets, "OnyxR_Nuggets");
    	GameRegistry.registerItem(OnyxR_Pickaxe, "OnyxR_Pickaxe");
    	GameRegistry.registerItem(OnyxR_Shovel, "OnyxR_Shovel");
    	GameRegistry.registerItem(OnyxR_Stick, "OnyxR_Stick");
    	GameRegistry.registerItem(OnyxR_Sword, "OnyxR_Sword");
    	GameRegistry.registerItem(OnyxR_Faux, "OnyxR_Faux");
    	
    	GameRegistry.registerItem(NeoDium_Faux, "NeoDium_Faux");
    	
    	GameRegistry.registerItem(Onyx_Axe, "Onyx_Axe");
    	GameRegistry.registerItem(Onyx_Boot, "Onyx_Boot");
    	GameRegistry.registerItem(Onyx_ChestPlate, "Onyx_ChestPlate");
    	GameRegistry.registerItem(Onyx_Hammer, "Onyx_Hammer");
    	GameRegistry.registerItem(Onyx_Helmet, "Onyx_Helmet");
    	GameRegistry.registerItem(Onyx_Ingot, "Onyx_Ingot");
    	GameRegistry.registerItem(Onyx_Leggings, "Onyx_Leggings");
    	GameRegistry.registerItem(Onyx_Nuggets, "Onyx_Nuggets");
    	GameRegistry.registerItem(Onyx_Pickaxe, "Onyx_Pickaxe");
    	GameRegistry.registerItem(Onyx_Shovel, "Onyx_Shovel");
    	GameRegistry.registerItem(Onyx_Stick, "Onyx_Stick");
    	GameRegistry.registerItem(Onyx_Sword, "Onyx_Sword");
    	GameRegistry.registerItem(Onyx_Faux, "Onyx_Faux");
    	
    	
    	Iterator<DragonBreed> it = DragonBreedRegistry.getInstance().getMapId().values().iterator();
    	int o =0;
    	DragonBreed d;
    	while(it.hasNext())
    	{
    		d = ((DragonBreed)it.next());
        	GameRegistry.registerItem(ItemDragon[o], "ItemDragon-"+o);
        	GameRegistry.registerItem(ItemDragon2[o], "ItemDragon2-"+o);
        	o++;
    	}
    	

    	GameRegistry.registerItem(ItemGrade, "ItemGrade");
    	GameRegistry.registerItem(p, "piece");
    	
    	GameRegistry.registerItem(bouclier, "Bouclier");
  
    /*	GameRegistry.registerItem(Neo_Carrot, "Neo_Carrot");
    	GameRegistry.registerItem(Neo_Potato, "Neo_Potato");
    	GameRegistry.registerItem(Neo_wheat_seeds, "Neo_wheat_seeds"); */
    	
    	GameRegistry.registerItem(Portal_Transformer, "Portal_Transformer");
    	
    	GameRegistry.registerItem(Corn, "Corn");
    	GameRegistry.registerItem(epeemarchand, "epeemarchand");
    	GameRegistry.registerItem(gardien, "gardien");
    	GameRegistry.registerItem(Corn_Seeds, "Corn_Seeds");
    	
    	GameRegistry.registerItem(Tomate, "Tomate");
    	GameRegistry.registerItem(Tomate_Seeds, "Tomate_Seeds");
    	GameRegistry.registerItem(tuteur, "tuteur");
        GameRegistry.registerItem(Copyright_NeoCraft, "Copyright_NeoCraft");
        GameRegistry.registerItem(Vote_Key, "Vote_Key");
        GameRegistry.registerItem(Ultime_Key, "Ultime_Key");
        GameRegistry.registerItem(CModoR1, "CModoR1");
        GameRegistry.registerItem(CModoR2, "CModoR2");
        GameRegistry.registerItem(CAdminR3, "CAdminR3");
        GameRegistry.registerItem(NeoMap, "NeoMap");
        GameRegistry.registerItem(NeoDium_Hammer, "NeoDium_Hammer");
        GameRegistry.registerItem(Silithium_Hammer, "Silithium_Hammer");
        GameRegistry.registerItem(Titane_Hammer, "Titane_Hammer");
        GameRegistry.registerItem(Mythrile_Hammer, "Mythrile_Hammer");
        GameRegistry.registerItem(Diamond_Hammer, "Diamond_Hammer");
        GameRegistry.registerItem(Iron_Hammer, "Iron_Hammer");
        GameRegistry.registerItem(Gold_Hammer, "Gold_Hammer");
        GameRegistry.registerItem(Stone_Hammer, "Stone_Hammer");
        GameRegistry.registerItem(Wood_Hammer, "Wood_Hammer");
        
        
        GameRegistry.registerItem(Iron_Nuggets, "Iron_Nuggets");
        GameRegistry.registerItem(Diamond_Nuggets, "Diamond_Nuggets");
        GameRegistry.registerItem(Emerald_Nuggets, "Emerald_Nuggets");
        
        GameRegistry.registerItem(NeoDium_Ingot, "NeoDium_Ingot");
        GameRegistry.registerItem(NeoDium_Nuggets, "NeoDium_Nuggets");
        GameRegistry.registerItem(NeoDium_Core, "NeoDium_Core");
        
        GameRegistry.registerItem(Silithium_Ingot, "Silithium_Ingot");
        GameRegistry.registerItem(Silithium_Nuggets, "Silithium_Nuggets");

        GameRegistry.registerItem(Titane_Ingot, "Titane_Ingot");
        GameRegistry.registerItem(Titane_Nuggets, "Titane_Nuggets");
        
        GameRegistry.registerItem(Mythrile_Ingot, "Mythrile_Ingot");
        GameRegistry.registerItem(Mythrile_Nuggets, "Mythrile_Nuggets");
        

        GameRegistry.registerItem(NeoDium_Sword, "NeoDium_Sword");
        GameRegistry.registerItem(NeoDium_Pickaxe, "NeoDium_Pickaxe");
        GameRegistry.registerItem(NeoDium_Axe, "NeoDium_Axe");
        GameRegistry.registerItem(NeoDium_Shovel, "NeoDium_Shovel");
        
        
        GameRegistry.registerItem(Silithium_Sword, "Silithium_Sword");
        GameRegistry.registerItem(Silithium_Pickaxe, "Silithium_Pickaxe");
        GameRegistry.registerItem(Silithium_Axe, "Silithium_Axe");
        GameRegistry.registerItem(Silithium_Shovel, "Silithium_Shovel");
        

        GameRegistry.registerItem(Titane_Sword, "Titane_Sword");
        GameRegistry.registerItem(Titane_Pickaxe, "Titane_Pickaxe");
        GameRegistry.registerItem(Titane_Axe, "Titane_Axe");
        GameRegistry.registerItem(Titane_Shovel, "Titane_Shovel");
        
        
        GameRegistry.registerItem(Mythrile_Sword, "Mythrile_Sword");
        GameRegistry.registerItem(Mythrile_Pickaxe, "Mythrile_Pickaxe");
        GameRegistry.registerItem(Mythrile_Axe, "Mythrile_Axe");
        GameRegistry.registerItem(Mythrile_Shovel, "Mythrile_Shovel");
        
        
        
        GameRegistry.registerItem(NeoDium_Helmet, "NeoDium_Helmet");
        GameRegistry.registerItem(NeoDium_ChestPlate, "NeoDiumChestPlate");
        GameRegistry.registerItem(NeoDium_Leggings, "NeoDium_Leggings");
        GameRegistry.registerItem(NeoDium_Boot, "NeoDium_Boot");
        
        GameRegistry.registerItem(Silithium_Helmet, "Silithium_Helmet");
        GameRegistry.registerItem(Silithium_ChestPlate, "SilithiumChestPlate");
        GameRegistry.registerItem(Silithium_Leggings, "Silithium_Leggings");
        GameRegistry.registerItem(Silithium_Boot, "Silithium_Boot");
        
        GameRegistry.registerItem(God_Helmet, "God_Helmet");
        GameRegistry.registerItem(God_ChestPlate, "GodChestPlate");
        GameRegistry.registerItem(God_Leggings, "God_Leggings");
        GameRegistry.registerItem(God_Boot, "God_Boot");

        GameRegistry.registerItem(Divin_Soulth, "Divin_Soulth");
        
        GameRegistry.registerItem(Titane_Helmet, "Titane_Helmet");
        GameRegistry.registerItem(Titane_ChestPlate, "TitaneChestPlate");
        GameRegistry.registerItem(Titane_Leggings, "Titane_Leggings");
        GameRegistry.registerItem(Titane_Boot, "Titane_Boot");
        
        GameRegistry.registerItem(Mythrile_Helmet, "Mythrile_Helmet");
        GameRegistry.registerItem(Mythrile_ChestPlate, "MythrileChestPlate");
        GameRegistry.registerItem(Mythrile_Leggings, "Mythrile_Leggings");
        GameRegistry.registerItem(Mythrile_Boot, "Mythrile_Boot");
    
        GameRegistry.registerItem(Coca, "Cocaine");
        
        for (int i = 0; i < guiDrag.length; i++)
        {
        	GameRegistry.registerItem(guiDrag[i], guiDrag[i].getUnlocalizedName());
        }

        for (int i = 0; i < Itemkit.length; i++)
        {
        	GameRegistry.registerItem(Itemkit[i], Itemkit[i].getUnlocalizedName());
        }
        
        GameRegistry.registerItem(ItemWood, "ItemWood");
        GameRegistry.registerItem(ItemCobble, "ItemCobble");
        GameRegistry.registerItem(ItemWool1, "ItemWool1");
        GameRegistry.registerItem(ItemWool2, "ItemWool2");

        GameRegistry.addRecipe(new ItemStack(ItemMod.Tomate_Seeds), new Object[]{"#", '#', ItemMod.Tomate});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Aubergine_Seed), new Object[]{"#", '#', ItemMod.Aubergine});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Radis_Seed), new Object[]{"#", '#', ItemMod.Radis});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Corn_Seeds), new Object[]{"#", '#', ItemMod.Corn});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.tuteur), new Object[]{"###","H#H","###", '#', Items.stick, 'H', Items.string});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.gardien), new Object[]{"#T#","#O#","OTO", '#', Blocks.dirt, 'T', Items.iron_ingot, 'O', Items.diamond});
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.Shopper), new Object[]{"BFB","F#F","BFB", '#', Blocks.chest, 'B', Blocks.planks, 'F', Items.iron_ingot});
        
        GameRegistry.addRecipe(new ItemStack(Items.experience_bottle), new Object[]{"B","F","B", 'B', Items.glowstone_dust, 'F', Items.glass_bottle});
        
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.NeoDium_Block, 1), new Object[]{"###", "###", "###", '#', ItemMod.NeoDium_Ingot});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Silithium_Block, 1), new Object[]{"###", "###", "###", '#', ItemMod.Silithium_Ingot});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Titane_Block, 1), new Object[]{"###", "###", "###", '#', ItemMod.Titane_Ingot});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Mythrile_Block, 1), new Object[]{"###", "###", "###", '#', ItemMod.Mythrile_Ingot});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Ingot, 1), new Object[]{"###", "###", "###", '#', ItemMod.NeoDium_Nuggets});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Ingot, 1), new Object[]{"###", "###", "###", '#', ItemMod.Silithium_Nuggets});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Ingot, 1), new Object[]{"###", "###", "###", '#', ItemMod.Titane_Nuggets});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Ingot, 1), new Object[]{"###", "###", "###", '#', ItemMod.Mythrile_Nuggets});

        
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Ingot, 9), new Object[]{ "#", '#', BlockMod.NeoDium_Block});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Ingot, 9), new Object[]{"#", '#', BlockMod.Silithium_Block});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Ingot, 9), new Object[]{"#", '#', BlockMod.Titane_Block});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Ingot, 9), new Object[]{"#", '#', BlockMod.Mythrile_Block});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Stick, 1), new Object[]{" # "," # ", '#', ItemMod.Onyx_Nuggets});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Stick, 1), new Object[]{" # "," # ", '#', ItemMod.OnyxR_Nuggets});	
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Stick, 1), new Object[]{" # "," # ", '#', ItemMod.NeoDium_Nuggets});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Powerfull_Stick, 1), new Object[]{" # "," I ", " T ",'#', ItemMod.OnyxR_Stick,'I', ItemMod.Onyx_Stick,'T', ItemMod.NeoDium_Stick});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Nuggets, 9), new Object[]{"#", '#', ItemMod.Onyx_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Ingot, 9), new Object[]{ "#", '#', BlockMod.Onyx_Block});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Onyx_Block, 1), new Object[]{"###", "###", "###", '#', ItemMod.Onyx_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Sword, 1), new Object[]{" # ", " # ", " T ", '#', ItemMod.Onyx_Ingot, 'T', ItemMod.Onyx_Stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Pickaxe, 1), new Object[]{"###", " T ", " T ", '#', ItemMod.Onyx_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Axe, 1), new Object[]{"## ", "#T ", " T ", '#', ItemMod.Onyx_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Axe, 1), new Object[]{" ##", " T#", " T ", '#', ItemMod.Onyx_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Shovel, 1), new Object[]{" # ", " T ", " T ", '#', ItemMod.Onyx_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Helmet, 1), new Object[]{"###", "# #", '#', ItemMod.Onyx_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_ChestPlate, 1), new Object[]{"# #", "#O#", "###", '#', ItemMod.Onyx_Ingot, 'O', ItemMod.Silithium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Leggings, 1), new Object[]{"###", "# #", "# #", '#', ItemMod.Onyx_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Boot, 1), new Object[]{"# #", "# #", '#', ItemMod.Onyx_Ingot});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Faux, 1), new Object[]{"###", "  O", "  O", '#', ItemMod.Onyx_Ingot, 'O', ItemMod.Onyx_Stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Faux, 1), new Object[]{"###", "  O", "  O", '#', ItemMod.OnyxR_Ingot, 'O', ItemMod.OnyxR_Stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Faux, 1), new Object[]{"##T", "  O", "  O", '#', ItemMod.NeoDium_Ingot, 'O', ItemMod.NeoDium_Stick, 'T', ItemMod.NeoDium_Core});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Nuggets, 9), new Object[]{"#", '#', ItemMod.OnyxR_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Ingot, 9), new Object[]{ "#", '#', BlockMod.OnyxR_Block});
        GameRegistry.addRecipe(new ItemStack(BlockMod.OnyxR_Block, 1), new Object[]{"###", "###", "###", '#', ItemMod.OnyxR_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Sword, 1), new Object[]{" # ", " # ", " T ", '#', ItemMod.OnyxR_Ingot, 'T', ItemMod.OnyxR_Stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Pickaxe, 1), new Object[]{"###", " T ", " T ", '#', ItemMod.OnyxR_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Axe, 1), new Object[]{"## ", "#T ", " T ", '#', ItemMod.OnyxR_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Axe, 1), new Object[]{" ##", " T#", " T ", '#', ItemMod.OnyxR_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Shovel, 1), new Object[]{" # ", " T ", " T ", '#', ItemMod.OnyxR_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Helmet, 1), new Object[]{"###", "# #", '#', ItemMod.OnyxR_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_ChestPlate, 1), new Object[]{"# #", "#O#", "###", '#', ItemMod.OnyxR_Ingot, 'O', ItemMod.NeoDium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Leggings, 1), new Object[]{"###", "# #", "# #", '#', ItemMod.OnyxR_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Boot, 1), new Object[]{"# #", "# #", '#', ItemMod.OnyxR_Ingot});
        
        GameRegistry.addRecipe(new ItemStack(Items.iron_ingot, 1), new Object[]{"###", "###", "###", '#', ItemMod.Iron_Nuggets});
        GameRegistry.addRecipe(new ItemStack(Items.diamond, 1), new Object[]{"###", "###", "###", '#', ItemMod.Diamond_Nuggets});
        GameRegistry.addRecipe(new ItemStack(Items.emerald, 1), new Object[]{"###", "###", "###", '#', ItemMod.Emerald_Nuggets});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Wood_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', Blocks.log, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Stone_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', Blocks.stone, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Iron_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', Blocks.iron_block, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Gold_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', Blocks.gold_block, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Diamond_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', Blocks.diamond_block, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', BlockMod.Mythrile_Block, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', BlockMod.Titane_Block, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', BlockMod.Silithium_Block, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Ingot, 9), new Object[]{ "#", '#', BlockMod.NeoDium_Block});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Nuggets, 9), new Object[]{"#", '#', ItemMod.NeoDium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Nuggets, 9), new Object[]{"#", '#', ItemMod.Silithium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Nuggets, 9), new Object[]{"#", '#', ItemMod.Titane_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Nuggets, 9), new Object[]{"#", '#', ItemMod.Mythrile_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Emerald_Nuggets, 9), new Object[]{"#", '#', Items.emerald});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Iron_Nuggets, 9), new Object[]{"#", '#', Items.iron_ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Diamond_Nuggets, 9), new Object[]{"#", '#', Items.diamond});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Core, 1), new Object[]{" # ", "#F#", " # ", '#', ItemMod.NeoDium_Ingot, 'F', Items.nether_star});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Sword, 1), new Object[]{" # ", " F ", " T ", '#', ItemMod.NeoDium_Ingot, 'F', NeoDium_Core, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Pickaxe, 1), new Object[]{"#F#", " T ", " T ", '#', ItemMod.NeoDium_Ingot, 'F', NeoDium_Core, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Axe, 1), new Object[]{"#F ", "#T ", " T ", '#', ItemMod.NeoDium_Ingot, 'F', NeoDium_Core, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Axe, 1), new Object[]{" F#", " T#", " T ", '#', ItemMod.NeoDium_Ingot, 'F', NeoDium_Core, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Shovel, 1), new Object[]{" # ", " T ", " T ", '#', ItemMod.NeoDium_Ingot, 'F', NeoDium_Core, 'T', Items.stick});
        
        GameRegistry.addRecipe(new ItemStack(Blocks.dirt, 1), new Object[]{"#OT", '#', Blocks.cobblestone, 'O', Blocks.gravel, 'T', Blocks.sand});
        
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Sword, 1), new Object[]{" # ", " # ", " T ", '#', ItemMod.Silithium_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Pickaxe, 1), new Object[]{"###", " T ", " T ", '#', ItemMod.Silithium_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Axe, 1), new Object[]{"## ", "#T ", " T ", '#', ItemMod.Silithium_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Axe, 1), new Object[]{" ##", " T#", " T ", '#', ItemMod.Silithium_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Shovel, 1), new Object[]{" # ", " T ", " T ", '#', ItemMod.Silithium_Ingot, 'T', Items.stick});

        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Sword, 1), new Object[]{" # ", " # ", " T ", '#', ItemMod.Titane_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Pickaxe, 1), new Object[]{"###", " T ", " T ", '#', ItemMod.Titane_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Axe, 1), new Object[]{"## ", "#T ", " T ", '#', ItemMod.Titane_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Axe, 1), new Object[]{" ##", " T#", " T ", '#', ItemMod.Titane_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Shovel, 1), new Object[]{" # ", " T ", " T ", '#', ItemMod.Titane_Ingot, 'T', Items.stick});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Sword, 1), new Object[]{" # ", " # ", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Pickaxe, 1), new Object[]{"###", " T ", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Axe, 1), new Object[]{"## ", "#T ", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Axe, 1), new Object[]{" ##", " T#", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Shovel, 1), new Object[]{" # ", " T ", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Sword, 1), new Object[]{" # ", " # ", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Pickaxe, 1), new Object[]{"###", " T ", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Axe, 1), new Object[]{"## ", "#T ", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Axe, 1), new Object[]{" ##", " T#", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Shovel, 1), new Object[]{" # ", " T ", " T ", '#', ItemMod.Mythrile_Ingot, 'T', Items.stick});
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.IronSpike, 8), new Object[]{"# #", " # ", "###", '#', Items.iron_ingot});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Onyx_Spike, 8), new Object[]{"# #", " # ", "###", '#', ItemMod.Onyx_Ingot});
        GameRegistry.addRecipe(new ItemStack(BlockMod.NeoDium_Spike, 8), new Object[]{"# #", " # ", "###", '#', ItemMod.NeoDium_Ingot});
        GameRegistry.addRecipe(new ItemStack(BlockMod.DiamondSpike, 8), new Object[]{"# #", " # ", "###", '#', Items.diamond});
        GameRegistry.addRecipe(new ItemStack(BlockMod.TitaneSpike, 8), new Object[]{"# #", " # ", "###", '#', ItemMod.Titane_Ingot});
        GameRegistry.addRecipe(new ItemStack(BlockMod.SilithiumSpike, 8), new Object[]{"# #", " # ", "###", '#', ItemMod.Silithium_Ingot});

        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Helmet, 1), new Object[]{"###", "# #", '#', ItemMod.NeoDium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_ChestPlate, 1), new Object[]{"# #", "#F#", "###", '#', ItemMod.NeoDium_Ingot, 'F', NeoDium_Core});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Leggings, 1), new Object[]{"###", "# #", "# #", '#', ItemMod.NeoDium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.NeoDium_Boot, 1), new Object[]{"# #", "# #", '#', ItemMod.NeoDium_Ingot});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Helmet, 1), new Object[]{"###", "# #", '#', ItemMod.Silithium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_ChestPlate, 1), new Object[]{"# #", "###", "###", '#', ItemMod.Silithium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Leggings, 1), new Object[]{"###", "# #", "# #", '#', ItemMod.Silithium_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Silithium_Boot, 1), new Object[]{"# #", "# #", '#', ItemMod.Silithium_Ingot});

        GameRegistry.addRecipe(new ItemStack(ItemMod.God_Helmet, 1), new Object[]{"###", "# #", '#', ItemMod.Divin_Soulth});
        GameRegistry.addRecipe(new ItemStack(ItemMod.God_ChestPlate, 1), new Object[]{"# #", "###", "###", '#', ItemMod.Divin_Soulth});
        GameRegistry.addRecipe(new ItemStack(ItemMod.God_Leggings, 1), new Object[]{"###", "# #", "# #", '#', ItemMod.Divin_Soulth});
        GameRegistry.addRecipe(new ItemStack(ItemMod.God_Boot, 1), new Object[]{"# #", "# #", '#', ItemMod.Divin_Soulth});

        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Helmet, 1), new Object[]{"###", "# #", '#', ItemMod.Titane_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_ChestPlate, 1), new Object[]{"# #", "###", "###", '#', ItemMod.Titane_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Leggings, 1), new Object[]{"###", "# #", "# #", '#', ItemMod.Titane_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Titane_Boot, 1), new Object[]{"# #", "# #", '#', ItemMod.Titane_Ingot});
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Helmet, 1), new Object[]{"###", "# #", '#', ItemMod.Mythrile_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_ChestPlate, 1), new Object[]{"# #", "###", "###", '#', ItemMod.Mythrile_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Leggings, 1), new Object[]{"###", "# #", "# #", '#', ItemMod.Mythrile_Ingot});
        GameRegistry.addRecipe(new ItemStack(ItemMod.Mythrile_Boot, 1), new Object[]{"# #", "# #", '#', ItemMod.Mythrile_Ingot});
    
        GameRegistry.addRecipe(new ItemStack(BlockMod.Planteur, 1), new Object[]{"#O#", "OFO", "#C#", 'O', Blocks.stone, 'F', Blocks.dispenser, 'C', Items.redstone, '#', ItemMod.Titane_Ingot});
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.StaticBlock, 1), new Object[]{"###", "###", "###",  '#', Blocks.cobblestone});
       
        GameRegistry.addRecipe(new ItemStack(BlockMod.Builder, 1), new Object[]{"#A#", "#B#", "#C#",  '#', Blocks.cobblestone, 'A', Blocks.dispenser, 'B', Blocks.chest, 'C', Items.redstone});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Elevateur1, 1), new Object[]{"###", "#A#", "#B#",  '#', Blocks.obsidian, 'A', ItemMod.Titane_Ingot, 'B', Blocks.stone_button});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Elevateur1, 1), new Object[]{"###", "#A#", "#B#",  '#', Blocks.obsidian, 'A', ItemMod.Titane_Ingot, 'B', Blocks.wooden_button});
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.Elevateur2, 1), new Object[]{"###", "#A#", "#B#",  '#', Blocks.obsidian, 'A', ItemMod.Silithium_Ingot, 'B', Blocks.stone_button});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Elevateur2, 1), new Object[]{"###", "#A#", "#B#",  '#', Blocks.obsidian, 'A', ItemMod.Silithium_Ingot, 'B', Blocks.wooden_button});
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.Elevateur3, 1), new Object[]{"###", "#A#", "#B#",  '#', Blocks.obsidian, 'A', ItemMod.NeoDium_Ingot, 'B', Blocks.stone_button});
        GameRegistry.addRecipe(new ItemStack(BlockMod.Elevateur3, 1), new Object[]{"###", "#A#", "#B#",  '#', Blocks.obsidian, 'A', ItemMod.NeoDium_Ingot, 'B', Blocks.wooden_button});
        
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.FuturTransmetter, 1), new Object[]{"#A#", "#A#", "#A#",  '#', Blocks.stone, 'A', Items.redstone});
        GameRegistry.addRecipe(new ItemStack(BlockMod.FuturInput, 1), new Object[]{"#A#", "#B#", "#C#",  '#', Blocks.stone, 'A', Blocks.obsidian, 'B', Blocks.gold_block, 'C', Items.redstone});
        GameRegistry.addRecipe(new ItemStack(BlockMod.FuturTerminalInput, 1), new Object[]{"#A#", "ABA", "#A#",  '#', Blocks.stone, 'A', Items.redstone, 'B', Blocks.gold_block});
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.BlockCleaner, 1), new Object[]{"###", "#B#", "#A#",  '#', Blocks.stone, 'A', Blocks.hopper, 'B', BlockMod.BlockDetector});
        
        GameRegistry.addSmelting(BlockMod.Onyx_Ore, new ItemStack(ItemMod.Onyx_Ingot, 1), 75.0F);
        GameRegistry.addSmelting(BlockMod.OnyxR_Ore, new ItemStack(ItemMod.OnyxR_Ingot, 1), 40.0F);
        GameRegistry.addSmelting(BlockMod.NeoDium_Ore, new ItemStack(ItemMod.NeoDium_Ingot, 1), 50.0F);
        GameRegistry.addSmelting(BlockMod.Silithium_Ore, new ItemStack(ItemMod.Silithium_Ingot, 1), 40.0F);
        GameRegistry.addSmelting(BlockMod.Titane_Ore, new ItemStack(ItemMod.Titane_Ingot, 1), 20.0F);
        GameRegistry.addSmelting(BlockMod.Mythrile_Ore, new ItemStack(ItemMod.Mythrile_Ingot, 1), 10.0F);
        
        GameRegistry.addSmelting(Items.rotten_flesh, new ItemStack(Items.leather, 1), 40.0F);
        
        GameRegistry.addRecipe(new ItemStack(ItemMod.Onyx_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', BlockMod.Onyx_Block, 'T', Items.stick});
        GameRegistry.addRecipe(new ItemStack(ItemMod.OnyxR_Hammer, 1), new Object[]{" # ", " T#", "T  ", '#', BlockMod.OnyxR_Block, 'T', Items.stick});
        
        GameRegistry.addRecipe(new ItemStack(BlockMod.BlockDivinTotem, 1), new Object[]{"#O#", "#T#", "###", '#', Blocks.stone, 'O', Blocks.gold_block, 'T', Items.nether_star});
        
        
        RemoveRecipe(Items.fishing_rod, 1);
        RemoveRecipe(Items.carrot_on_a_stick, 1);
    }
    public static void RemoveRecipe(Item resultItem, int stacksize) {
    	ItemStack resultStack = new ItemStack(resultItem, stacksize);
    	ItemStack recipeResult = null;
    	ArrayList recipes = (ArrayList) CraftingManager.getInstance().getRecipeList();
    	for (int scan = 0; scan < recipes.size(); scan++) {
    		 IRecipe tmpRecipe = (IRecipe) recipes.get(scan);
    		 if (tmpRecipe instanceof ShapedRecipes) {
    			 ShapedRecipes recipe = (ShapedRecipes)tmpRecipe;
    			 recipeResult = recipe.getRecipeOutput();
    		 }
    		 if (tmpRecipe instanceof ShapelessRecipes) {
    			 ShapelessRecipes recipe = (ShapelessRecipes)tmpRecipe;
    			 recipeResult = recipe.getRecipeOutput();
    		 }
    		 if (ItemStack.areItemStacksEqual(resultStack, recipeResult)) {
    			 System.out.println("Removed Recipe: " + recipes.get(scan) + " -> " + recipeResult);
    			 recipes.remove(scan);
    		 }
    	}
    }
}
