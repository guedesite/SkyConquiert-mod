package fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.Proxy.Serveur.Entity.EntityPet;
import fr.neocraft.main.Proxy.Serveur.Stage.Pet.PetRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderPet extends RenderLiving
{
    
    private String bar = I18n.format("neo.pet.bar");
    private String Color = I18n.format("neo.specq");
    public RenderPet()
    {
        super(new PetModelManager(), 1F);
    }
    
    protected ResourceLocation getEntiyTexture(EntityLiving living)
    {
        return this.getEntityTexture((EntityPet) living);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return PetRegister.getDataClient(((EntityPet)p_110775_1_).getId()).texture;
    }
    


    private int tickMaxLvl = 0;
    private int tick = 0;
    private char[] Rainbow = new char[] {'4','c','6','e','2','a','b','3','1','9','d','5' };
    
    
   
    public void doRender(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
    	super.doRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    	
    	EntityPet pet = ((EntityPet)p_76986_1_);
    	if(pet.getIsPlayerPet())
    	{
    		int lvl = pet.getLvl();
	        if(!Minecraft.getMinecraft().thePlayer.getCommandSenderName().equals(pet.getPseudo()))
	        {
	        	this.func_147906_a(p_76986_1_, getRainbowModifer(lvl)+pet.getPseudo(),p_76986_2_, p_76986_4_ +0.3D, p_76986_6_, 64);
	        	
	        }
	        	
	        	 if(pet.LastChange == 0 || lvl >= 50)
	             {
	             	this.func_147906_a(p_76986_1_, getRainbowModifer(lvl)+"niveau "+lvl,p_76986_2_, p_76986_4_, p_76986_6_, 64);
	             } else {
	             	this.func_147906_a(p_76986_1_, "niveau "+lvl,p_76986_2_, p_76986_4_, p_76986_6_, 64);
	             	this.func_147906_a(p_76986_1_, ((100*pet.getExp())/pet.getMustExpLvl(lvl))+"%",p_76986_2_, p_76986_4_-0.3D, p_76986_6_, 64);
	             }
    	}
        
    }
    
    private String getRainbowModifer(int lvl)
    {
    	if(lvl >= 50)
    	{
    		tick++;
    		if(tick >= 3)
    		{
    			tick = 0;
	    		tickMaxLvl++;
	    		if(tickMaxLvl >= Rainbow.length)
	    		{
	    			tickMaxLvl=0;
	    		}
    		}
    		return Color+Rainbow[tickMaxLvl];
    		
    	}
    	return "";
    	
    }
    
    protected void preRenderCallback(EntityPet p_77041_1_, float p_77041_2_)
    {
    	if(p_77041_1_.getId() == 3)
    	{
	        int i = p_77041_1_.getSlimeSize();
	        float f1 = (p_77041_1_.prevSquishFactor + (p_77041_1_.squishFactor - p_77041_1_.prevSquishFactor) * p_77041_2_) / ((float)i * 0.5F + 1.0F);
	        float f2 = 1.0F / (f1 + 1.0F);
	        float f3 = (float)i;
	        GL11.glScalef(f2 * f3, 1.0F / f2 * f3, f2 * f3);
    	}
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((EntityPet)p_77041_1_, p_77041_2_);
    }
   
    
}
