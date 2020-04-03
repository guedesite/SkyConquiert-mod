package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Client.event.TextDraw;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.MinecraftForge;

public class GuiTextTabList extends GuiIngame {
	private static NeoChat C = new NeoChat();
	public GuiTextTabList(Minecraft p_i1036_1_) {
		super(p_i1036_1_);
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
		       renderTabList(width,height, mc);
	}
	
	private RenderGameOverlayEvent eventParent;
	private boolean pre(ElementType type)
    {
        return MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Pre(eventParent, type));
    }
    private void post(ElementType type)
    {
        MinecraftForge.EVENT_BUS.post(new RenderGameOverlayEvent.Post(eventParent, type));
    }
    private void bind(ResourceLocation res)
    {
        mc.getTextureManager().bindTexture(res);
    }
    
	 protected void renderTabList(int width, int height, Minecraft mc){

	            this.mc.mcProfiler.startSection("playerList");
	            ArrayList<String> players = TextDraw.Player_List;
	            if(players != null && !players.isEmpty())
	            {
		            int largeur = 300;
		            int nb2, nb;
		            if (players.size() %2 == 0)
		            {
		            	nb2 = players.size();
		            }
		            else
		            {
			        	  nb2 = players.size() +1;
		            }
		            nb = players.size();
		            int hauteur = (nb2 / 2) *9;
					int middle = width / 2;
					int x = middle - largeur / 2;
					int y = 25;
					int y1 = 3;
					int y3 = 15;
					if(main.BossActive)
					{
						y += 25;
						y1 += 25;
						y3 += 25;
					}
					
					DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
							DateFormat.SHORT,
							DateFormat.SHORT);
					Date aujourdhui = new Date();
					
					String date = shortDateFormat.format(aujourdhui);
					String str = C.d_purple + C.b +"N" + C.l_purple + C.b + "eo"+ C.d_purple + C.b +"C"+C.l_purple+ C.b + "raft Sky Conquiert V8";
					drawCenteredString(mc.fontRenderer, str, width / 2, y1, Integer.parseInt("FFAA00", 16));
					drawCenteredString(mc.fontRenderer, C.l_purple +date, width / 2,y3, Integer.parseInt("FFAA00", 16));
					
		            drawRect(x - 1, y - 1, x + largeur + 1, y + hauteur, Integer.MIN_VALUE);
		            boolean stat = false;
		            int y2 = y;
		            int o = 0;
		            if(players == null || players.size() == 0)
		            {
		            	return;
		            }
		            for (int i = 0; i < nb; i++)
		            {
		            	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		                GL11.glEnable(GL11.GL_ALPHA_TEST);
		                if(o < nb)
		                {
			            		drawRect(x, (y2 + i * 8), x + 149, (y2 + i * 8)+ 8, 553648127);
			                    String displayName = players.get(o);
			                    mc.fontRenderer.drawStringWithShadow(displayName, x+13, (y2 + i * 8), 16777215);
			                   
			                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
			                    mc.getTextureManager().bindTexture(Gui.icons);
			                    int   pingIndex = 0;
	
			                    zLevel += 100.0F;
			                    drawTexturedModalRect(x + 1, (y2 + i * 8), 0, 176 + pingIndex * 8, 10, 8);
			                    zLevel -= 100.0F;
			                    o++;
		                }
		                if(o < nb)
		                {
		                	drawRect(x + 150, (y2 + i * 8), x + 300, (y2 + i * 8)+ 8, 553648127);
		            	
	
		                    String displayName2 = players.get(o);
		                    mc.fontRenderer.drawStringWithShadow(displayName2, x + 150 + 13, (y2 + i * 8), 16777215);
		                   
		                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	
		                    mc.getTextureManager().bindTexture(Gui.icons);
	
		                   int pingIndex2 = 0;
		                 
	
		                    zLevel += 100.0F;
		                    drawTexturedModalRect(x + 151, (y2 + i * 8), 0, 176 + pingIndex2 * 8, 10, 8);
		                    zLevel -= 100.0F;
		                    o++;
		                }
		            	y2++;
	
		            }
	            }
	 }
}