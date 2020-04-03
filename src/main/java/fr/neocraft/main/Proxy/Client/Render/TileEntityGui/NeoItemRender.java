package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;

public class NeoItemRender extends RenderItem{
	
	@Override
	public void renderItemOverlayIntoGUI(FontRenderer p_94148_1_, TextureManager p_94148_2_, ItemStack p_94148_3_, int p_94148_4_, int p_94148_5_, String p_94148_6_)
    {
        if (p_94148_3_ != null)
        {
            if (p_94148_3_.stackSize > 1 || p_94148_6_ != null)
            {
            	int s1 = 1;
            	switch(p_94148_4_)
            	{
            		case 8:
            			s1 = 1;
            			break;
            		case 37:
            			s1 = 16;
            			break;
            		case 66:
            			s1 = 32;
            			break;
            		case 94:
            			s1 = 64;
            			break;
            		case 123:
            			s1 = 128;
            			break;
            		case 152:
            			s1 = 256;
            			break;
            	}
            	
              
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glDisable(GL11.GL_BLEND);
                p_94148_1_.drawStringWithShadow(s1+"", p_94148_4_ + 19 - 2 - p_94148_1_.getStringWidth(s1+""), p_94148_5_ + 6 + 3, 16777215);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
            }

        }
    }
}
