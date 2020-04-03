package fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityPet;
import fr.neocraft.main.Proxy.Serveur.Stage.Pet.PetRegister;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

@SideOnly(Side.CLIENT)
public class PetModelManager extends ModelBase {
	public PetModelManager () {  }
	
	private ModelBase d;
	
	public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_)
    {
		 super.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
		 
		 
		 d = PetRegister.getDataClient(((EntityPet)p_78088_1_).getId()).Model;
		 
		 
		 this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);

		   
		 d.render(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
	 }
	
	public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        super.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        d.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
    }
	
	public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
	{
		super.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
		
		d = PetRegister.getDataClient(((EntityPet)p_78086_1_).getId()).Model;
		
		d.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
	}
	
}
