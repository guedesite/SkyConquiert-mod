package fr.neocraft.main.Proxy.Serveur.Stage;

import net.minecraft.block.Block;

public class StageMineraiGen {

	public Block block;
	public int min, max;
	public boolean isextanded = false;
	public StageMineraiGen(Block b, int min, int max, boolean c)
	{
		this.block = b;
		this.min = min;
		this.max = max;
		this.isextanded = c;
	}
	
	public boolean Can(int rand)
	{
		return rand >= min && rand <= max;

	}
}//> 100.000 <
