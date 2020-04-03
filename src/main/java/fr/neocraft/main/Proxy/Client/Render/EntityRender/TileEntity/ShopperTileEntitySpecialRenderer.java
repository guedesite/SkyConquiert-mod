package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.ShopperTileEntity;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class ShopperTileEntitySpecialRenderer extends TileEntitySpecialRenderer{
	EntityItem entItem = null;
    public static RenderShopper model = new RenderShopper();
    public static ResourceLocation texture = new ResourceLocation(neoreference.MOD_ID, "textures/models/blocks/Shopper.png");
    private int nb = 0;
    @Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialRenderTick) {
		this.renderTileEntityShopperAt((ShopperTileEntity)tile, x, y, z, partialRenderTick);
	}
	
    private void renderTileEntityShopperAt(ShopperTileEntity tile, double x, double y, double z, float partialRenderTick) // ma propre fonction
    {
    		ShopperTileEntity tileEntity = (ShopperTileEntity)tile;
    		if(tileEntity.getValue() != this.nb)
    		{
    			this.nb = tileEntity.getValue();
    		}
	    	if(tileEntity.getStackInSlot(0) == null)
	        {
	    		entItem = null;
	        }
	    	else
	    	{
	    		 if(entItem == null )
	        	 {
	    			 entItem = new EntityItem(tileEntity.getWorldObj(), x, y, z, new ItemStack(tileEntity.getStackInSlot(0).getItem(), 1));
	        	 }
	    		 else if(tileEntity.getStackInSlot(0).getItem() != entItem.getEntityItem().getItem())
	    		 {
	    			 entItem = new EntityItem(tileEntity.getWorldObj(), x, y, z, new ItemStack(tileEntity.getStackInSlot(0).getItem(), 1));
	    		 }
	    	}

        		 int direction = tile.getBlockMetadata();
        		 GL11.glPushMatrix();
        		 GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
                 GL11.glRotatef(180F, 0F, 0F, 1.0F);
                 GL11.glRotatef(90F * direction, 0.0F, 1.0F, 0.0F); 
                 if(direction == 0 || direction == 2)
	        	 {
                	 GL11.glRotatef(180F, 0F, 1.0F, 0.0F); 
	        	 } 
                 this.bindTexture(texture); // affiche la texture
                 model.render();
                 GL11.glPopMatrix();
                 
	             GL11.glPushMatrix();
	             
	             if (entItem != null)
	             {
	            	 this.entItem.hoverStart = 0.0F;
	             }
	             RenderItem.renderInFrame = true;
	             if(direction == 0)
	        	 {
	        		 GL11.glTranslated(x + 0.5D, y + 0.38D, z + 1.0D);
	        	 }
	        	 else if(direction == 2)
	        	 {
	        		 GL11.glTranslated(x + 0.5D, y + 0.38D, z + 0.05D);
	        	 }else if(direction == 1)
	        	 {
	        		 GL11.glTranslated(x + 0.95D, y + 0.38D, z + 0.5D);
	        	 }else if(direction == 3)
	        	 {
	        		 GL11.glTranslated(x + 0.05D, y + 0.38D, z + 0.5D);
	        	 }
	             GL11.glRotatef(0F, 0.0F, 0.0F, 1.0F);
	             GL11.glRotatef(90F * direction, 0.0F, 1.0F, 0.0F);
	             if (entItem != null)
	             {
	            	 RenderManager.instance.renderEntityWithPosYaw(this.entItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
         	 	}
	             RenderItem.renderInFrame = false;
	             float f3;
	        	 float f1 = 0.6666667F;
	        	 FontRenderer fontrenderer = this.func_147498_b();
	             f3 = 0.016666668F * f1;
	             GL11.glScalef(f3, -f3, f3);
	             GL11.glNormal3f(0.0F, 0.0F, -1.0F * f3);
	             GL11.glDepthMask(false);
	             String s = I18n.format("neo.shopper.tilenetityspecialrenderer",this.nb+"");
	             fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 13, 0);
	             GL11.glDepthMask(true);
	             GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	             GL11.glPopMatrix();

    	
    }

}
