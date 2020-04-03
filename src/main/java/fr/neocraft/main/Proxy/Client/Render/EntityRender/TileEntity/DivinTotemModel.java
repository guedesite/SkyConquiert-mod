package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class DivinTotemModel extends ModelBase
{
	  //fields
	    ModelRenderer base;
	    ModelRenderer[] c1g = new ModelRenderer[50];
	    ModelRenderer[] c2g = new ModelRenderer[50];
	  
	  public DivinTotemModel()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	    Random r = new Random();
	    
	      base = new ModelRenderer(this, 0, 0);
	      base.addBox(0F, 0F, 0F, 16, 14, 16);
	      base.setRotationPoint(-8F, 10F, -8F);
	      base.setTextureSize(64, 32);
	      base.mirror = true;
	      setRotation(base, 0F, 0F, 0F);
	      for(int i = 0; i < c1g.length; i++)
	      {
	    	  c1g[i] = new ModelRenderer(this, 0, 30);
		      c1g[i] .addBox((float) (-0.5F + (0.5-(r.nextFloat()+0.05))*100 ), (float) (-0.25F+ (0.25-(r.nextFloat()/1.25))*100)+1, (float) (-0.5F+ (0.5-(r.nextFloat()+0.05))*100) , 1, 1, 1);
		      c1g[i] .setRotationPoint(0F, 5F, 0F);
		      c1g[i] .setTextureSize(64, 32);
		      c1g[i] .mirror = true;

		      setRotation(c1g[i] , 0F, r.nextFloat() * 360, 0);
	      }
	      for(int i = 0; i < c2g.length; i++)
	      {
	    	  c2g[i] = new ModelRenderer(this, 7, 30);
		      c2g[i] .addBox((float) (-0.5F + (0.5-(r.nextFloat()+0.05))*100 ), (float) (-0.25F+ (0.25-(r.nextFloat()/1.25))*100)+1, (float) (-0.5F+ (0.5-(r.nextFloat()+0.05))*100) , 1, 1, 1);
		      c2g[i] .setRotationPoint(0F, 5F, 0F);
		      c2g[i] .setTextureSize(64, 32);
		      c2g[i] .mirror = true;
		      setRotation(c2g[i] , 0F, r.nextFloat() * 360, 0);
	      }
	  }
	  
	  public void render()
	  {
		 float f5 = 0.0625F;
		 base.render(f5);
		  
	  }
	  
	  public void renderPart(float rot, int ca)
	  {
		 float f5 = 0.0625F;
		
		 
		int count = 0;
		 
		 for(int i = 0; i < c1g.length; i++)
	      {
			 
			 c1g[i].rotateAngleY += rot;
			if(count*5 < ca)
			 {
				 c1g[i].render(f5);
			 }
			 count++;
	      }
	      for(int i = 0; i < c2g.length; i++)
	      {
	    	 
	    	  	c2g[i].rotateAngleY += rot;
				 if(count*5 < ca)
				 {
					 c2g[i].render(f5);
				 }
				 count++;
			
	      }
		
		
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
