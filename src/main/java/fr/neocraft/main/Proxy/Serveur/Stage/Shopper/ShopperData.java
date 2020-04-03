package fr.neocraft.main.Proxy.Serveur.Stage.Shopper;

import fr.neocraft.main.Proxy.Serveur.Stage.Pet.ShopperItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShopperData {
	

	
	public int posX, posY, posZ, world;
	public ShopperItemStack[] stack;
	public int[] prix = new int[8], damage = new int[8];
	public ShopperData(int x, int y, int z, int w, int[] idstack, int[] px, int[] js)
	{
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.world = w;
		int i = 0;
		if(idstack == null) { return; }
		stack = new ShopperItemStack[idstack.length];
		for(int e:idstack)
		{
			stack[i] = new ShopperItemStack(new ItemStack(Item.getItemById(e), 1));
			i++;
		}
		this.damage = js;
		this.prix = px;
	}
	
	public void addStock()
	{
		for(ShopperItemStack e:stack)
		{
			e.addStock();
		}
	}
	
	public void addAchat(int i, Item s, int dmg)
	{
		for(ShopperItemStack e:stack)
		{
			if(e.stack.getItem() == s && e.stack.getItemDamage() == dmg)
			{
				e.addAchat(i);
			}
		}
	}
	
	public float getVariant(Item s, int dmg)
	{
		for(ShopperItemStack e:stack)
		{
			if(e.stack.getItem()==s && e.stack.getItemDamage() == dmg)
			{
				return e.getVariant();
			}
		}
		return 1;
	}
}
