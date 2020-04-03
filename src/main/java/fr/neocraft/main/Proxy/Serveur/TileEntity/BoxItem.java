package fr.neocraft.main.Proxy.Serveur.TileEntity;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import net.minecraft.item.ItemStack;

public class BoxItem {
	private NeoChat C = main.getChat();
	private ItemStack Item;
	private int Max,  NbMax;
	private boolean nb;
	
	public BoxItem(ItemStack stack, int chanceMax)
	{
		this.Item = stack;
		this.Item.setStackDisplayName(C.d_yellow+"["+C.l_yellow+"Box"+C.d_yellow+"]"+C.l_yellow+ " " + this.Item.getDisplayName());
		this.Max = chanceMax;
		this.nb = false;
	}
	public BoxItem(ItemStack stack, int chanceMax, int nbchanceMax)
	{
		this.Item = stack;
		this.Item.setStackDisplayName(C.d_yellow+"["+C.l_yellow+"Box"+C.d_yellow+"]"+C.l_yellow+ " " + this.Item.getDisplayName());
		this.Max = chanceMax;
		this.NbMax = nbchanceMax;
		this.nb = true;
	}
	
	public ItemStack getItem()
	{
		if(this.nb)
		{
			Item.stackSize = 1 + (int)(Math.random() * (NbMax + 1));
		}
		return Item;
	}
	
	public boolean canItem()
	{
		int nb = (int) (Math.random() * (Max + 1));
		if(nb < 1)
		{
			return true;
		}
		return false;
	}
	
}
