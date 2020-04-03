package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RenderBoxVote extends ModelBase
{
	  ModelRenderer centre;
	    ModelRenderer model1;
	    ModelRenderer model2;
	    ModelRenderer model3;
	    ModelRenderer model4;
	    ModelRenderer haut_top;
	    ModelRenderer haut_down;
	    ModelRenderer bas_top;
	    ModelRenderer bas_down;
	  
	  public RenderBoxVote()
	  {
		  textureWidth = 64;
		    textureHeight = 64;
		      centre = new ModelRenderer(this, 0, 32);
		      centre.addBox(0F, 0F, 0F, 12, 12, 12);
		      centre.setRotationPoint(0F, 1F, 0F);
		      centre.setTextureSize(64, 64);
		      centre.mirror = true;
		      setRotation(centre, 0F, 0F, 0F);
		      
		      model1 = new ModelRenderer(this, 56, 0);
		      model1.addBox(0F, 0F, 0F, 2, 12, 2);
		      model1.setRotationPoint(-1F, 1F, -1F);
		      model1.setTextureSize(64, 64);
		      model1.mirror = true;
		      setRotation(model1, 0F, 0F, 0F);
		      
		      model2 = new ModelRenderer(this, 56, 0);
		      model2.addBox(0F, 0F, 0F, 2, 12, 2);
		      model2.setRotationPoint(-1F, 1F, 11F);
		      model2.setTextureSize(64, 64);
		      model2.mirror = true;
		      setRotation(model2, 0F, 0F, 0F);
		      
		      model3 = new ModelRenderer(this, 56, 0);
		      model3.addBox(0F, 0F, 0F, 2, 12, 2);
		      model3.setRotationPoint(11F, 1F, -1F);
		      model3.setTextureSize(64, 64);
		      model3.mirror = true;
		      setRotation(model3, 0F, 0F, 0F);
		      
		      model4 = new ModelRenderer(this, 56, 0);
		      model4.addBox(0F, 0F, 0F, 2, 12, 2);
		      model4.setRotationPoint(11F, 1F, 11F);
		      model4.setTextureSize(64, 64);
		      model4.mirror = true;
		      setRotation(model4, 0F, 0F, 0F);
		      
		      haut_top = new ModelRenderer(this, 0, 15);
		      haut_top.addBox(0F, 0F, 0F, 16, 1, 16);
		      haut_top.setRotationPoint(-2F, -1F, -2F);
		      haut_top.setTextureSize(64, 64);
		      haut_top.mirror = true;
		      setRotation(haut_top, 0F, 0F, 0F);
		      
		      haut_down = new ModelRenderer(this, 0, 0);
		      haut_down.addBox(0F, 0F, 0F, 14, 1, 14);
		      haut_down.setRotationPoint(-1F, 0F, -1F);
		      haut_down.setTextureSize(64, 64);
		      haut_down.mirror = true;
		      setRotation(haut_down, 0F, 0F, 0F);
		      
		      bas_top = new ModelRenderer(this, 0, 0);
		      bas_top.addBox(0F, 0F, 0F, 14, 1, 14);
		      bas_top.setRotationPoint(-1F, 13F, -1F);
		      bas_top.setTextureSize(64, 64);
		      bas_top.mirror = true;
		      setRotation(bas_top, 0F, 0F, 0F);
		      
		      bas_down = new ModelRenderer(this, 0, 15);
		      bas_down.addBox(0F, 0F, 0F, 16, 1, 16);
		      bas_down.setRotationPoint(-2F, 14F, -2F);
		      bas_down.setTextureSize(64, 64);
		      bas_down.mirror = true;
	  }
	  
	  public void render()
	  {
		 float f5 = 0.0625F;
		 centre.render(f5);
		    model1.render(f5);
		    model2.render(f5);
		    model3.render(f5);
		    model4.render(f5);
		    haut_top.render(f5);
		    haut_down.render(f5);
		    bas_top.render(f5);
		    bas_down.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  }

	}
