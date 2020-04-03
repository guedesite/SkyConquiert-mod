package fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.Entity.EntityShopperPnj;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;


@SideOnly(Side.CLIENT)
public class RenderRat  extends RenderLiving
{
	 private ResourceLocation texture = new ResourceLocation(neoreference.MOD_ID + ":textures/mobs/ratw.png");

    public RenderRat(ModelBase p_i1262_1_, float p_i1262_2_)
    {
        super(p_i1262_1_, p_i1262_2_);
    }
    
    protected ResourceLocation getEntiyTexture(EntityLiving living)
    {
        return this.getEntityTexture((EntityShopperPnj) living);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return texture;
    }
	
 /* public RenderRat(ModelBase modelbase, float f)
  {
    super(modelbase, f);
  }
  

  public void func_76986_a(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
  {
    MoCEntityRat entityrat = (MoCEntityRat)entityliving;
    super.func_76986_a(entityrat, d, d1, d2, f, f1);
  }
  

  protected float func_77044_a(EntityLivingBase entityliving, float f)
  {
    stretch(entityliving);
    return field_70173_aa + f;
  }
  

  protected void func_77041_b(EntityLivingBase entityliving, float f)
  {
    MoCEntityRat entityrat = (MoCEntityRat)entityliving;
    if (entityrat.climbing())
    {
      rotateAnimal(entityliving);
    }
  }
  
  protected void rotateAnimal(EntityLivingBase entityliving)
  {
    GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
  }
  
  protected void stretch(EntityLivingBase entityliving)
  {
    float f = 0.8F;
    GL11.glScalef(f, f, f);
  }
  
  protected ResourceLocation func_110775_a(Entity par1Entity) {
    return ((MoCEntityRat)par1Entity).getTexture();
  } */
}
