package fr.neocraft.main.Proxy.Client.Render.EntityRender.Entity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.Entity.DragFire;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class DragFireRender extends Render
{
   private static final ResourceLocation firen = new ResourceLocation(neoreference.MOD_ID+":textures/entity/DragFire.png");

    public DragFireRender()
    {
        this.shadowSize = 0.15F;
        this.shadowOpaque = 1F;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(final DragFire p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
    	 GL11.glPushMatrix();
         GL11.glTranslatef((float)p_76986_2_, (float)p_76986_4_, (float)p_76986_6_);
         this.bindEntityTexture(p_76986_1_);
         float f2 = (float)(16 + 0) / 16.0F;
         float f3 = (float)(16 + 16) / 16.0F;
         float f4 = (float)(16 + 0) / 16.0F;
         float f5 = (float)(16 + 16) / 16.0F;
         float f6 = 1.0F;
         float f7 = 0.5F;
         float f8 = 0.25F;
         int j = p_76986_1_.getBrightnessForRender(p_76986_9_);
         int k = j % 65536;
         int l = j / 65536;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)k / 1.0F, (float)l / 1.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
         float f9 = 0.7F;
         GL11.glScalef(f9, f9, f9);
         Tessellator tessellator = Tessellator.instance;
         tessellator.startDrawingQuads();
         tessellator.setNormal(0.0F, 1.0F, 0.0F);
         tessellator.addVertexWithUV((double)(0.0F - f7), (double)(0.0F - f8), 0.0D, (double)f2, (double)f5);
         tessellator.addVertexWithUV((double)(f6 - f7), (double)(0.0F - f8), 0.0D, (double)f3, (double)f5);
         tessellator.addVertexWithUV((double)(f6 - f7), (double)(1.0F - f8), 0.0D, (double)f3, (double)f4);
         tessellator.addVertexWithUV((double)(0.0F - f7), (double)(1.0F - f8), 0.0D, (double)f2, (double)f4);
         tessellator.draw();
         GL11.glDisable(GL11.GL_BLEND);
         GL11.glDisable(GL12.GL_RESCALE_NORMAL);
         GL11.glPopMatrix();
     }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
   
    protected ResourceLocation getEntityTexture(DragFire p_110775_1_)
    {
        return firen;
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity p_110775_1_)
    {
        return this.getEntityTexture((DragFire)p_110775_1_);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void func_76986_a(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        this.doRender((DragFire)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}


