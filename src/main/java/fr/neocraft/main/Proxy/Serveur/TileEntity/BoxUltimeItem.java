package fr.neocraft.main.Proxy.Serveur.TileEntity;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import net.minecraft.item.ItemStack;

public class BoxUltimeItem {
	private NeoChat C = main.getChat();
	private ItemStack Item;
	private int Max,  NbMax;
	private boolean nb;
	
	public BoxUltimeItem(ItemStack stack, int chanceMax)
	{
		this.Item = stack;
		this.Item.setStackDisplayName(C.d_purple+"["+C.l_purple+"Box"+C.d_purple+"]"+C.l_purple+ " " + this.Item.getDisplayName());
		this.Max = chanceMax;
		this.nb = false;
	}
	public BoxUltimeItem(ItemStack stack, int chanceMax, int nbchanceMax)
	{
		this.Item = stack;
		this.Item.setStackDisplayName(C.d_purple+"["+C.l_purple+"Box"+C.d_purple+"]"+C.l_purple+ " " + this.Item.getDisplayName());
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
