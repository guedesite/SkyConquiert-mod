package fr.neocraft.main.Proxy.Serveur.Stage.Pet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.neoreference;
import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class PetData {

	public ResourceLocation texture;
	public ModelBase Model;
	
	public PetData(ModelBase model,String img) 
	{
		Model = model;
		texture = new ResourceLocation(neoreference.MOD_ID + ":"+img);
	}
	
}
