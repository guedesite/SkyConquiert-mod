package fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity;

import fr.neocraft.main.Proxy.Serveur.Entity.EntityGardien;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelGardien extends ModelBase
{
	 //fields
    ModelRenderer head;
    ModelRenderer noze;
    ModelRenderer legd;
    ModelRenderer legg;
    ModelRenderer body;
    ModelRenderer armg;
    ModelRenderer armd;
    ModelRenderer bodybottom;
  
  public ModelGardien()
  {
    textureWidth = 128;
    textureHeight = 128;
    head = new ModelRenderer(this, 0, 0);
    head.addBox(-4F, -10F, -4F, 8, 10, 8);
    head.setRotationPoint(0.5F, -7F, 1F);
    head.setTextureSize(128, 128);
    head.mirror = true;
    setRotation(head, 0F, 0F, 0F);
    
    noze = new ModelRenderer(this, 24, 0);
    noze.addBox(-1F, -3F, -4.533333F, 2, 4, 2);
    noze.setRotationPoint(0.5F, -7F, 1F);
    noze.setTextureSize(128, 128);
    noze.mirror = true;
    setRotation(noze, -0.1115358F, 0F, 0F);
    
    legd = new ModelRenderer(this, 37, 0);
    legd.addBox(-3F, 0F, -2.5F, 6, 16, 5);
    legd.setRotationPoint(6F, 8F, 0F);
    legd.setTextureSize(128, 128);
    legd.mirror = true;
    setRotation(legd, 0F, 0F, 0F);
    
    legg = new ModelRenderer(this, 60, 0);
    legg.addBox(-3F, 0F, -2.5F, 6, 16, 5);
    legg.setRotationPoint(-5F, 8F, 0F);
    legg.setTextureSize(128, 128);
    legg.mirror = true;
    setRotation(legg, 0F, 0F, 0F);
    
    body = new ModelRenderer(this, 0, 40);
    body.addBox(0F, 0F, 0F, 17, 11, 12);
    body.setRotationPoint(-8.5F, -7F, -5F);
    body.setTextureSize(128, 128);
    body.mirror = true;
    setRotation(body, 0F, 0F, 0F);
    
    armg = new ModelRenderer(this, 60, 22);
    armg.addBox(0F, 0F, -3F, 4, 29, 6);
    armg.setRotationPoint(8.5F, -5F, 1F);
    armg.setTextureSize(128, 128);
    armg.mirror = true;
    setRotation(armg, 0F, 0F, 0F);
    
    armd = new ModelRenderer(this, 60, 58);
    armd.addBox(-4F, 0F, -3F, 4, 29, 6);
    armd.setRotationPoint(-8.5F, -5F, 1F);
    armd.setTextureSize(128, 128);
    armd.mirror = true;
    setRotation(armd, 0F, 0F, 0F);
    
    bodybottom = new ModelRenderer(this, 0, 70);
    bodybottom.addBox(0F, 0F, 0F, 9, 5, 6);
    bodybottom.setRotationPoint(-4F, 4F, -2.5F);
    bodybottom.setTextureSize(128, 128);
    bodybottom.mirror = true;
    setRotation(bodybottom, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    noze.render(f5);
    legd.render(f5);
    legg.render(f5);
    body.render(f5);
    armg.render(f5);
    armd.render(f5);
    bodybottom.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
    {
        this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        
        this.noze.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.noze.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        
        
        this.legg.rotateAngleX = -1.5F * this.func_78172_a(p_78087_1_, 13.0F) * p_78087_2_;
        this.legd.rotateAngleX = 1.5F * this.func_78172_a(p_78087_1_, 13.0F) * p_78087_2_;
        this.legg.rotateAngleY = 0.0F;
        this.legd.rotateAngleY = 0.0F;
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_)
    {
        EntityGardien entityirongolem = (EntityGardien)p_78086_1_;
        int i = entityirongolem.getAttackTimer();

        if (i > 0)
        {
            this.armd.rotateAngleX = -2.0F + 1.5F * this.func_78172_a((float)i - p_78086_4_, 10.0F);
        }
        else
        {
            boolean j = entityirongolem.getHold();

            if (j)
            {
                this.armd.rotateAngleX = -0.8F + 0.025F * this.func_78172_a(6, 70.0F);
            }
            else
            {
                this.armd.rotateAngleX = (-0.2F + 1.5F * this.func_78172_a(p_78086_2_, 13.0F)) * p_78086_3_;
                this.armg.rotateAngleX = (-0.2F - 1.5F * this.func_78172_a(p_78086_2_, 13.0F)) * p_78086_3_;
            }
        }
    }

    private float func_78172_a(float p_78172_1_, float p_78172_2_)
    {
        return (Math.abs(p_78172_1_ % p_78172_2_ - p_78172_2_ * 0.5F) - p_78172_2_ * 0.25F) / (p_78172_2_ * 0.25F);
    }
}