package fr.neocraft.main.Proxy.Serveur.Stage.Pet;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

public class PetFood {
	
	private Map<Item, Integer> food = new HashMap<Item, Integer>();

	public PetFood addFood(Item i, int amount)
	{
		food.put(i, amount);
		return this;
	}
	
	public boolean isFood(Item i)
	{
		return food.containsKey(i);
	}
	
	public int getFoodAmount(Item i)
	{
		return food.get(i);
	}
}

