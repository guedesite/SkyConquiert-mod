package fr.neocraft.main.Proxy.Serveur.Stage.Pet;

import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Init.ItemMod;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.CatModel;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.GolemModel;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.MagmaModel;
import fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity.WolfModel;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class PetRegister {

	
	private static Map<Integer, PetData> data = new HashMap<Integer, PetData>();
	
	private static Map<Integer, PetFood> datS = new HashMap<Integer, PetFood>();
	
	@SideOnly(Side.CLIENT)
	public static void registerClient() {
		System.out.println("REGISTER PET MODEL CLIENT");
		data.put(0, new PetData(new WolfModel(), "textures/mobs/pet/wolf.png"));
		data.put(1, new PetData(new GolemModel(), "textures/mobs/pet/golem.png"));
		data.put(2, new PetData(new CatModel(), "textures/mobs/pet/cat.png"));
		data.put(3, new PetData(new MagmaModel(), "textures/mobs/pet/magma.png"));
	}
	
	public static void registerServer() {
		System.out.println("REGISTER PET SERVEUR");
		datS.put(0, new PetFood().addFood(Items.bone, 1).addFood(Items.porkchop,3).addFood(Items.beef, 3).addFood(Items.chicken, 3));
		datS.put(1, new PetFood().addFood(
				Items.
				iron_ingot, 9).
				addFood(
						ItemMod.
						Iron_Nuggets, 1).
		addFood(
				Item.
				getItemFromBlock(
						Blocks.
						iron_block)
				, 81));
		datS.put(2, new PetFood().addFood(Items.fish,5));
		datS.put(3, new PetFood().addFood(Items.blaze_powder, 2).addFood(Items.blaze_rod, 4).addFood(Items.magma_cream, 5));
	}


	public static PetData getDataClient(int id) {
		return data.get(id);
	}
	
	public static PetFood getPetFoodsSever(int id) {
		return datS.get(id);
	}
}
