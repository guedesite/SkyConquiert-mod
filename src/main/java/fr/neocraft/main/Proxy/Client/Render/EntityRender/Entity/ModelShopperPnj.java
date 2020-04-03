package fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelShopperPnj extends ModelBase
{
	  //fields
	    ModelRenderer head;
	    ModelRenderer noze;
	    ModelRenderer jambe_gauche;
	    ModelRenderer jambe_droite;
	    ModelRenderer body;
	    ModelRenderer avantbras1;
	    ModelRenderer avantbras2;
	    ModelRenderer milieu;
	    ModelRenderer poche1;
	    ModelRenderer piece1;
	    ModelRenderer poche2;
	    ModelRenderer poche3;
	    ModelRenderer poche4;
	    ModelRenderer sword;
	    ModelRenderer pocheSword1;
	    ModelRenderer pocheSword3;
	    ModelRenderer pocheSword4;
	  
	  public ModelShopperPnj()
	  {
	    textureWidth = 64;
	    textureHeight = 64;
	    
	    head = new ModelRenderer(this, 0, 0);
	      head.addBox(-4F, -5F, -4F, 8, 10, 8);
	      head.setRotationPoint(0F, -4F, 1F);
	      head.setTextureSize(64, 64);
	      head.mirror = true;
	      setRotation(head, 0F, 0F, 0F);
	      
	      noze = new ModelRenderer(this, 24, 0);
	      noze.addBox(-1F, 0F, -5F, 2, 4, 2);
	      noze.setRotationPoint(0F, -2.666667F, 1F);
	      noze.setTextureSize(64, 64);
	      noze.mirror = true;
	      setRotation(noze, 0F, 0F, 0F);
	      
	      jambe_gauche = new ModelRenderer(this, 0, 22);
	      jambe_gauche.addBox(0F, 0F, 0F, 4, 12, 4);
	      jambe_gauche.setRotationPoint(-4.25F, 11.9F, -1F);
	      jambe_gauche.setTextureSize(64, 64);
	      jambe_gauche.mirror = true;
	      setRotation(jambe_gauche, 0F, 0F, 0F);
	      
	      jambe_droite = new ModelRenderer(this, 0, 22);
	      jambe_droite.addBox(0F, 0F, 0F, 4, 12, 4);
	      jambe_droite.setRotationPoint(0.25F, 11.9F, -1F);
	      jambe_droite.setTextureSize(64, 64);
	      jambe_droite.mirror = true;
	      setRotation(jambe_droite, 0F, 0F, 0F);
	      
	      body = new ModelRenderer(this, 16, 20);
	      body.addBox(0F, 0F, 0F, 8, 12, 6);
	      body.setRotationPoint(-4F, 1F, -2F);
	      body.setTextureSize(64, 64);
	      body.mirror = true;
	      setRotation(body, 0F, 0F, 0F);
	      
	      avantbras1 = new ModelRenderer(this, 44, 22);
	      avantbras1.addBox(0F, 0F, 0F, 4, 8, 4);
	      avantbras1.setRotationPoint(4F, 1.5F, 1F);
	      avantbras1.setTextureSize(64, 64);
	      avantbras1.mirror = true;
	      setRotation(avantbras1, -0.8726646F, 0F, 0F);
	      
	      avantbras2 = new ModelRenderer(this, 44, 22);
	      avantbras2.addBox(0F, 0F, 0F, 4, 8, 4);
	      avantbras2.setRotationPoint(-8F, 1.5F, 1F);
	      avantbras2.setTextureSize(64, 64);
	      avantbras2.mirror = true;
	      setRotation(avantbras2, -0.8726646F, 0F, 0F);
	      
	      milieu = new ModelRenderer(this, 40, 38);
	      milieu.addBox(0F, 0F, 0F, 8, 4, 4);
	      milieu.setRotationPoint(-4F, 4.1F, -1.9F);
	      milieu.setTextureSize(64, 64);
	      milieu.mirror = true;
	      setRotation(milieu, -0.8726646F, 0F, 0F);
	      
	      poche1 = new ModelRenderer(this, 30, 17);
	      poche1.addBox(-2F, 0F, 0F, 0, 3, 3);
	      poche1.setRotationPoint(-2.8F, 13F, -0.5F);
	      poche1.setTextureSize(64, 64);
	      poche1.mirror = true;
	      setRotation(poche1, 0F, 0F, 0F);
	      
	      piece1 = new ModelRenderer(this, 36, 5);
	      piece1.addBox(-9F, 0F, 0F, 4, 3, 2);
	      piece1.setRotationPoint(4.4F, 12.2F, -0.3F);
	      piece1.setTextureSize(64, 64);
	      piece1.mirror = true;
	      setRotation(piece1, 0.2230717F, 0F, -0.0371786F);
	      
	      poche2 = new ModelRenderer(this, 30, 20);
	      poche2.addBox(0F, 7F, 0F, 1, 0, 3);
	      poche2.setRotationPoint(-4.8F, 9F, -0.5F);
	      poche2.setTextureSize(64, 64);
	      poche2.mirror = true;
	      setRotation(poche2, 0F, 0F, 0F);
	      
	      poche3 = new ModelRenderer(this, 33, 22);
	      poche3.addBox(0F, 20F, 0F, 1, 3, 0);
	      poche3.setRotationPoint(-4.8F, -7F, -0.5F);
	      poche3.setTextureSize(64, 64);
	      poche3.mirror = true;
	      setRotation(poche3, 0F, 0F, 0F);
	      
	      poche4 = new ModelRenderer(this, 33, 22);
	      poche4.addBox(0F, 13F, 0F, 1, 3, 0);
	      poche4.setRotationPoint(-4.8F, 0F, 2.4F);
	      poche4.setTextureSize(64, 64);
	      poche4.mirror = true;
	      setRotation(poche4, 0F, 0F, 0F);
	      
	      sword = new ModelRenderer(this, 5, 38);
	      sword.addBox(0F, 0F, 0F, 0, 16, 6);
	      sword.setRotationPoint(4.6F, 23F, -1.5F);
	      sword.setTextureSize(64, 64);
	      sword.mirror = true;
	      setRotation(sword, 0F, 0F, 3.141593F);
	      
	      pocheSword1 = new ModelRenderer(this, 30, 17);
	      pocheSword1.addBox(0F, 0F, 0F, 0, 3, 3);
	      pocheSword1.setRotationPoint(4.7F, 13F, -0.5F);
	      pocheSword1.setTextureSize(64, 64);
	      pocheSword1.mirror = true;
	      setRotation(pocheSword1, 0F, 0F, 0F);
	      
	      pocheSword3 = new ModelRenderer(this, 33, 22);
	      pocheSword3.addBox(0F, 13F, 0F, 1, 3, 0);
	      pocheSword3.setRotationPoint(3.7F, 0F, -0.5F);
	      pocheSword3.setTextureSize(64, 64);
	      pocheSword3.mirror = true;
	      setRotation(pocheSword3, 0F, 0F, 0F);
	      
	      pocheSword4 = new ModelRenderer(this, 33, 22);
	      pocheSword4.addBox(3.7F, 10F, 2.5F, 1, 3, 0);
	      pocheSword4.setRotationPoint(0F, 3F, 0F);
	      pocheSword4.setTextureSize(64, 64);
	      pocheSword4.mirror = true;
	      setRotation(pocheSword4, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    head.render(f5);
	    noze.render(f5);
	    jambe_gauche.render(f5);
	    jambe_droite.render(f5);
	    body.render(f5);
	    avantbras1.render(f5);
	    avantbras2.render(f5);
	    milieu.render(f5);
	    poche1.render(f5);
	    piece1.render(f5);
	    poche2.render(f5);
	    poche3.render(f5);
	    poche4.render(f5);
	    sword.render(f5);
	    pocheSword1.render(f5);
	    pocheSword3.render(f5);
	    pocheSword4.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_)
	  {
	    this.head.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.head.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.noze.rotateAngleY = p_78087_4_ / (180F / (float)Math.PI);
        this.noze.rotateAngleX = p_78087_5_ / (180F / (float)Math.PI);
        this.jambe_droite.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F) * 1.4F * p_78087_2_ * 0.5F;
        this.jambe_gauche.rotateAngleX = MathHelper.cos(p_78087_1_ * 0.6662F + (float)Math.PI) * 1.4F * p_78087_2_ * 0.5F;
        this.jambe_droite.rotateAngleY = 0.0F;
        this.jambe_gauche.rotateAngleY = 0.0F;
	  }

	}
