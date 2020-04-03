package fr.neocraft.main.Item;

import fr.neocraft.main.neoreference;
import info.ata4.minecraft.dragon.server.entity.breeds.DragonBreed;
import net.minecraft.item.Item;

public class ItemDragon2 extends Item{
	
	private DragonBreed breed;
	public ItemDragon2(DragonBreed b) 
	{
		this.breed = b;
		this.setMaxStackSize(1);
		this.setUnlocalizedName("Dragon2Item");
		this.setTextureName(neoreference.MOD_ID + ":D_"+this.breed.getId());
	}
	
	public DragonBreed getBreed() {
		return this.breed;
	}


}
