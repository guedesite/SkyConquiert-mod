package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RenderFarmLandSpecial extends ModelBase{
	  
	  //fields
	 ModelRenderer shape;
	    ModelRenderer shape2;
	    ModelRenderer shape3;
	    ModelRenderer shape4;
	    ModelRenderer shape5;
	    ModelRenderer baton2;
	    ModelRenderer baton3;
	    ModelRenderer baton4;
	    ModelRenderer baton5;
	    ModelRenderer base1;
	    ModelRenderer base2;
	    ModelRenderer base3;
	    ModelRenderer base4;
	    ModelRenderer base5;
	    ModelRenderer base6;
	    ModelRenderer base7;
	    ModelRenderer base8;
	    ModelRenderer bottom;
	  
	  public RenderFarmLandSpecial()
	  {
		  textureWidth = 64;
		    textureHeight = 32;
		    
		    shape = new ModelRenderer(this, 16, 0);
		      shape.addBox(0F, 0F, 0F, 16, 0, 16);
		      shape.setRotationPoint(-8F, 9F, -8F);
		      shape.setTextureSize(64, 32);
		      shape.mirror = true;
		      setRotation(shape, 0F, 0F, 0F);
		      
		      
		      shape2 = new ModelRenderer(this, 0, 0);
		      shape2.addBox(0F, 0F, 0F, 16, 15, 0);
		      shape2.setRotationPoint(-8F, 9F, 8F);
		      shape2.setTextureSize(64, 32);
		      shape2.mirror = true;
		      setRotation(shape2, 0F, 0F, 0F);
		      
		     
		      shape3 = new ModelRenderer(this, 0, -16);
		      shape3.addBox(0F, 0F, 0F, 0, 15, 16);
		      shape3.setRotationPoint(8F, 9F, -8F);
		      shape3.setTextureSize(64, 32);
		      shape3.mirror = true;
		      setRotation(shape3, 0F, 0F, 0F);
		      
		      shape4 = new ModelRenderer(this, 0, 0);
		      shape4.addBox(0F, 0F, 0F, 16, 15, 0);
		      shape4.setRotationPoint(-8F, 9F, -8F);
		      shape4.setTextureSize(64, 32);
		      shape4.mirror = true;
		      setRotation(shape4, 0F, 0F, 0F);
		      
		      shape5 = new ModelRenderer(this, 0, -16);
		      shape5.addBox(0F, 0F, 0F, 0, 15, 16);
		      shape5.setRotationPoint(-8F, 9F, -8F);
		      shape5.setTextureSize(64, 32);
		      shape5.mirror = true;
		      setRotation(shape5, 0F, 0F, 0F);
		      
		      baton2 = new ModelRenderer(this, 16, 22);
		      baton2.addBox(0F, 0F, 0F, 1, 9, 1);
		      baton2.setRotationPoint(-5F, 0F, 4F);
		      baton2.setTextureSize(64, 32);
		      baton2.mirror = true;
		      setRotation(baton2, 0F, 0F, 0F);
		      
		      baton3 = new ModelRenderer(this, 16, 22);
		      baton3.addBox(0F, 0F, 0F, 1, 9, 1);
		      baton3.setRotationPoint(4F, 0F, 4F);
		      baton3.setTextureSize(64, 32);
		      baton3.mirror = true;
		      setRotation(baton3, 0F, 0F, 0F);
		      
		      baton4 = new ModelRenderer(this, 16, 22);
		      baton4.addBox(0F, 0F, 0F, 1, 9, 1);
		      baton4.setRotationPoint(4F, 0F, -5F);
		      baton4.setTextureSize(64, 32);
		      baton4.mirror = true;
		      setRotation(baton4, 0F, 0F, 0F);
		      
		      baton5 = new ModelRenderer(this, 16, 22);
		      baton5.addBox(0F, 0F, 0F, 1, 9, 1);
		      baton5.setRotationPoint(-5F, 0F, -5F);
		      baton5.setTextureSize(64, 32);
		      baton5.mirror = true;
		      setRotation(baton5, 0F, 0F, 0F);
		      
		      base1 = new ModelRenderer(this, 24, 21);
		      base1.addBox(0F, 0F, 0F, 1, 1, 10);
		      base1.setRotationPoint(-6F, 1F, -5F);
		      base1.setTextureSize(64, 32);
		      base1.mirror = true;
		      setRotation(base1, 0F, 0F, 0F);
		      
		      base2 = new ModelRenderer(this, 24, 21);
		      base2.addBox(0F, 0F, 0F, 1, 1, 10);
		      base2.setRotationPoint(5F, 1F, -5F);
		      base2.setTextureSize(64, 32);
		      base2.mirror = true;
		      setRotation(base2, 0F, 0F, 0F);
		      
		      base3 = new ModelRenderer(this, 24, 21);
		      base3.addBox(0F, 0F, 0F, 12, 1, 1);
		      base3.setRotationPoint(-6F, 1F, 5F);
		      base3.setTextureSize(64, 32);
		      base3.mirror = true;
		      setRotation(base3, 0F, 0F, 0F);
		      
		      base4 = new ModelRenderer(this, 24, 21);
		      base4.addBox(0F, 0F, 0F, 12, 1, 1);
		      base4.setRotationPoint(-6F, 1F, -6F);
		      base4.setTextureSize(64, 32);
		      base4.mirror = true;
		      setRotation(base4, 0F, 0F, 0F);
		      
		      base5 = new ModelRenderer(this, 24, 21);
		      base5.addBox(0F, 0F, 0F, 12, 1, 1);
		      base5.setRotationPoint(-6F, 5F, 5F);
		      base5.setTextureSize(64, 32);
		      base5.mirror = true;
		      setRotation(base5, 0F, 0F, 0F);
		      
		      base6 = new ModelRenderer(this, 24, 21);
		      base6.addBox(0F, 0F, 0F, 12, 1, 1);
		      base6.setRotationPoint(-6F, 5F, -6F);
		      base6.setTextureSize(64, 32);
		      base6.mirror = true;
		      setRotation(base6, 0F, 0F, 0F);
		      
		      base7 = new ModelRenderer(this, 24, 21);
		      base7.addBox(0F, 0F, 0F, 1, 1, 10);
		      base7.setRotationPoint(-6F, 5F, -5.533333F);
		      base7.setTextureSize(64, 32);
		      base7.mirror = true;
		      setRotation(base7, 0F, 0F, 0F);
		      
		      base8 = new ModelRenderer(this, 24, 21);
		      base8.addBox(0F, 0F, 0F, 1, 1, 10);
		      base8.setRotationPoint(5F, 5F, -5F);
		      base8.setTextureSize(64, 32);
		      base8.mirror = true;
		      setRotation(base8, 0F, 0F, 0F);
		      
		      bottom = new ModelRenderer(this, -16, 0);
		      bottom.addBox(0F, 0F, 0F, 16, 0, 16);
		      bottom.setRotationPoint(-8F, 24F, -8F);
		      bottom.setTextureSize(64, 32);
		      bottom.mirror = true;
		      setRotation(bottom, 0F, 0F, 0F);
	  }
	  
	  public void renderAll()
	  {
		  shape.render(0.0625F);
		    shape2.render(0.0625F);
		    shape3.render(0.0625F);
		    shape4.render(0.0625F);
		    shape5.render(0.0625F);
		    baton2.render(0.0625F);
		    baton3.render(0.0625F);
		    baton4.render(0.0625F);
		    baton5.render(0.0625F);
		    base1.render(0.0625F);
		    base2.render(0.0625F);
		    base3.render(0.0625F);
		    base4.render(0.0625F);
		    base5.render(0.0625F);
		    base6.render(0.0625F);
		    base7.render(0.0625F);
		    base8.render(0.0625F);
		    bottom.render(0.0625F);
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
