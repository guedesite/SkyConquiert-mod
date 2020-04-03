package fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelRat extends ModelBase
	 {
	   public ModelRenderer Head;
	   public ModelRenderer EarR;
	   public ModelRenderer EarL;
	   public ModelRenderer WhiskerR;
	   public ModelRenderer WhiskerL;
	   public ModelRenderer Body;
	   public ModelRenderer Tail;
	   public ModelRenderer FrontL;
	   public ModelRenderer FrontR;
	   public ModelRenderer RearL;
	   public ModelRenderer RearR;
	   public ModelRenderer BodyF;
	   
	   public ModelRat()
	   {
	     Head = new ModelRenderer(this, 0, 0);
	     Head.addBox(-1.5F, -1.0F, -6.0F, 3, 4, 6, 0.0F);
	     Head.setRotationPoint(0.0F, 18.0F, -9.0F);
	     EarR = new ModelRenderer(this, 16, 26);
	     EarR.addBox(-3.5F, -3.0F, -2.0F, 3, 3, 1, 0.0F);
	     EarR.setRotationPoint(0.0F, 18.0F, -9.0F);
	     EarL = new ModelRenderer(this, 24, 26);
	     EarL.addBox(0.5F, -3.0F, -2.0F, 3, 3, 1, 0.0F);
	     EarL.setRotationPoint(0.0F, 18.0F, -9.0F);
	     WhiskerR = new ModelRenderer(this, 24, 16);
	     WhiskerR.addBox(-4.5F, -1.0F, -6.0F, 3, 3, 1, 0.0F);
	     WhiskerR.setRotationPoint(0.0F, 18.0F, -9.0F);
	     WhiskerL = new ModelRenderer(this, 24, 20);
	     WhiskerL.addBox(1.5F, -1.0F, -6.0F, 3, 3, 1, 0.0F);
	     WhiskerL.setRotationPoint(0.0F, 18.0F, -9.0F);
	     Body = new ModelRenderer(this, 24, 0);
	     Body.addBox(-4.0F, -3.0F, -3.0F, 8, 8, 8, 0.0F);
	     Body.setRotationPoint(0.0F, 19.0F, 0.0F);
	    Body.rotateAngleX = 1.570796F;
	     Tail = new ModelRenderer(this, 56, 0);
	     Tail.addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2, 0.0F);
	     Tail.setRotationPoint(0.0F, 19.0F, 5.0F);
	     FrontL = new ModelRenderer(this, 0, 18);
	     FrontL.addBox(-2.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
	     FrontL.setRotationPoint(3.0F, 22.0F, -7.0F);
	     FrontR = new ModelRenderer(this, 0, 18);
	     FrontR.addBox(0.0F, 0.0F, -3.0F, 2, 1, 4, 0.0F);
	     FrontR.setRotationPoint(-3.0F, 22.0F, -7.0F);
	     RearL = new ModelRenderer(this, 0, 24);
	     RearL.addBox(-2.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
	     RearL.setRotationPoint(4.0F, 22.0F, 2.0F);
	     RearR = new ModelRenderer(this, 0, 24);
	     RearR.addBox(0.0F, 0.0F, -4.0F, 2, 1, 5, 0.0F);
	     RearR.setRotationPoint(-4.0F, 22.0F, 2.0F);
	     BodyF = new ModelRenderer(this, 32, 16);
	     BodyF.addBox(-3.0F, -3.0F, -7.0F, 6, 6, 6, 0.0F);
	     BodyF.setRotationPoint(0.0F, 19.0F, -2.0F);
	   }
	   

	   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	   {
	     super.render(entity, f, f1, f2, f3, f4, f5);
	     setRotationAngles(f, f1, f2, f3, f4, f5);
	     Head.render(f5);
	     EarR.render(f5);
	     EarL.render(f5);
	     WhiskerR.render(f5);
	     WhiskerL.render(f5);
	     Body.render(f5);
	     Tail.render(f5);
	     FrontL.render(f5);
	     FrontR.render(f5);
	     RearL.render(f5);
	     RearR.render(f5);
	     BodyF.render(f5);
	   }
	   
	   public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	   {
	     Head.rotateAngleY = (-(f4 / 57.29578F));
	     Head.rotateAngleX = (f3 / 57.29578F);
	     EarR.rotateAngleY = Head.rotateAngleY;
	     EarR.rotateAngleX = Head.rotateAngleX;
	     EarL.rotateAngleY = Head.rotateAngleY;
	     EarL.rotateAngleX = Head.rotateAngleX;
	     WhiskerR.rotateAngleY = Head.rotateAngleY;
	     WhiskerR.rotateAngleX = Head.rotateAngleX;
	     WhiskerL.rotateAngleY = Head.rotateAngleY;
	     WhiskerL.rotateAngleX = Head.rotateAngleX;
	     FrontL.rotateAngleX = (MathHelper.sin(f * 0.6662F) * 0.6F * f1);
	     RearL.rotateAngleX = (MathHelper.sin(f * 0.6662F + 3.141593F) * 0.8F * f1);
	     RearR.rotateAngleX = (MathHelper.sin(f * 0.6662F) * 0.6F * f1);
	     FrontR.rotateAngleX = (MathHelper.sin(f * 0.6662F + 3.141593F) * 0.8F * f1);
	     Tail.rotateAngleY = (FrontL.rotateAngleX * 0.625F);
	     Tail.rotateAngleX = 1.32F; 
	   }
}
