package fr.neocraft.main.Proxy.Serveur.Stage.Pet;

import net.minecraft.item.ItemStack;

public class ShopperItemStack {
	
	public ShopperItemStack(ItemStack itemStack) {
		stack = itemStack;
	}

	public ItemStack stack;
	
	private int stock = 0;
	

	public void addStock()
	{
		if(stock+50 < 350)
		{
			stock += 50;
		}
	}
	
	public void addAchat(int i)
	{
		stock -= i;
	}
	
	public float getVariant()
	{
		if(stock < -300)
		{
			return -50;
		}
		float nb = (stock*100)/600;
		if(nb > 100 )
		{
			nb = 100;
		} else if(nb < -100 )
		{
			nb = -100;
		}
		return nb;
	}
}
