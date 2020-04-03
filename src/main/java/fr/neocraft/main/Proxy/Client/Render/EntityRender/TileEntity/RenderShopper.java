package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RenderShopper extends ModelBase
{
	  //fields
	    ModelRenderer other1;
	    ModelRenderer other2;
	    ModelRenderer other3;
	    ModelRenderer Top;
	    ModelRenderer face;
	    ModelRenderer Bottom;
	  
	  public RenderShopper()
	  {
	    textureWidth = 32;
	    textureHeight = 32;
	    
	      other1 = new ModelRenderer(this, 0, 0);
	      other1.mirror = true;
	      other1.addBox(0F, 0F, 0F, 0, 16, 16);
	      other1.setRotationPoint(8F, 8F, -8F);
	      other1.setTextureSize(32, 32);
	      setRotation(other1, 0F, 0F, 0F);
	      
	      other2 = new ModelRenderer(this, 0, 0);
	      other2.mirror = true;
	      other2.addBox(0F, 0F, 0F, 0, 16, 16);
	      other2.setRotationPoint(-8F, 8F, -8F);
	      other2.setTextureSize(32, 32);
	      setRotation(other2, 0F, 0F, 0F);
	      
	      other3 = new ModelRenderer(this, 0, 16);
	      other3.mirror = true;
	      other3.addBox(0F, 0F, 0F, 16, 16, 0);
	      other3.setRotationPoint(-8F, 8F, 8F);
	      other3.setTextureSize(32, 32);
	      setRotation(other3, 0F, 0F, 0F);
	      
	      Top = new ModelRenderer(this, 0, 0);
	      Top.mirror = true;
	      Top.addBox(0F, 0F, 0F, 16, 0, 16);
	      Top.setRotationPoint(-8F, 8F, -8F);
	      Top.setTextureSize(32, 32);
	      setRotation(Top, 0F, 0F, 0F);
	      
	      face = new ModelRenderer(this, 0, 0);
	      face.mirror = true;
	      face.addBox(0F, 0F, 0F, 16, 16, 0);
	      face.setRotationPoint(-8F, 8F, -7F);
	      face.setTextureSize(32, 32);
	      setRotation(face, 0F, 0F, 0F);
	      
	      Bottom = new ModelRenderer(this, 0, 16);
	      Bottom.mirror = true;
	      Bottom.addBox(0F, 0F, 0F, 16, 0, 16);
	      Bottom.setRotationPoint(-8F, 23.9F, -8F);
	      Bottom.setTextureSize(32, 32);
	      setRotation(Bottom, 0F, 0F, 0F);
	  }
	  
	  public void render()
	  {
		 float f5 = 0.0625F;
	    other1.render(f5);
	    other2.render(f5);
	    other3.render(f5);
	    Top.render(f5);
	    face.render(f5);
	    Bottom.render(f5);
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
