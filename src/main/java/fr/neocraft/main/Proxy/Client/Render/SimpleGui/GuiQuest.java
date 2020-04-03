package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import java.util.Calendar;
import java.util.List;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.NeoChat;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Quest.Quest;
import fr.neocraft.main.Proxy.Serveur.Quest.QuestManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;

public class GuiQuest extends GuiIngame {
	private static NeoChat C = new NeoChat();
	public GuiQuest(Minecraft p_i1036_1_, int width, int height) {
		super(p_i1036_1_);
		 renderQuest(width,height, mc);
	}
	
	
    
	 protected void renderQuest(int width, int height, Minecraft mc){
		 int middle = width / 2;
		  List<Quest> Q = QuestManager.ArrayQuete;
		 int largeur = 150;
		 int hauteur =0, lasthauteur = 0, x, y, z = 0, temp;
		 float factor;
		 String[] info = null;
		 int sec = 1;
		 String secs = "";
		 Calendar c = Calendar.getInstance();
		 if(Q != null && !Q.isEmpty())
		 {
			 for(int i =0; i < Q.size(); i++)
			 {
				 info = null;
				 if(Q.get(i).GetElement().info != null)
				 {factor = 0.70f;
				 	info = getResponsivFromString((int) ((largeur - 10)), Q.get(i).GetElement().info, mc.fontRenderer,(1f/factor));
				 }
					 x = width - 2;
					 if(lasthauteur == 0)
					 {
						 y = 4;
					 }
					 else
					 {
						 y = 4 + lasthauteur+10;
					 }
					 hauteur = 35;
					 if(info != null)
					 {
						 factor = 0.70f;
						hauteur = (int) ((65+ info.length * 8)*(1f*factor));
					 }
					 if(2 <Q.get(i).GetElement().val)
					 {
						 hauteur = hauteur + 30;
						 z = y + hauteur - 20;
					 }else if(Q.get(i).GetElement().val == -1)
					 {
						 hauteur = hauteur + 30;
						 z = y + hauteur - 20;
					 }else if(Q.get(i).GetElement().val == -3)
					 {
						 hauteur = hauteur + 50;
						 z = y + hauteur - 40;
					 }else if(Q.get(i).GetElement().val == -2 && main.dataHorraire != -1 )
					 {
						 hauteur = hauteur + 30;
						 if(main.dataHorraire2 != -1 && main.dataHorraire3 != -1)
						 {
							 hauteur +=20;
							 z += 7;
						 }
						 z = y + hauteur - 20;
						 

					 }
					 drawRect(x, y, width - largeur, y+hauteur, Integer.MIN_VALUE);
					 drawRectFont(x, y-2, width - largeur, y, getIntFromColor(249, 198, 12));
					 factor = 0.85f;
					 
					 GL11.glPushMatrix();
					 GL11.glScalef(factor, factor, 1);
					 drawString(mc.fontRenderer, Q.get(i).GetName(), (int) ((x - largeur + 7) *(1f/factor)), (int) ((y + 5)*(1f/factor)), getIntFromColor(249, 198, 12));
					 GL11.glScalef(1f/factor, 1f/factor, 1);
					 
					 factor = 0.80f;
					 GL11.glScalef(factor, factor, 1);
					 drawString(mc.fontRenderer, Q.get(i).GetState() + 1 + "/"+ Q.get(i).GetNumberElement()+" "+Q.get(i).GetElement().stat, (int) ((x - largeur + 7) *(1f/factor)),  (int) ((y + 20)*(1f/factor)), getIntFromColor(242, 207, 82));
					 GL11.glScalef(1f/factor, 1f/factor, 1);
					 
					 factor = 0.75f;
					 GL11.glScalef(factor, factor, 1);
					 if(info != null)
					 {
						 for(int o =0; o < info.length; o++)
						 {
							 drawString(mc.fontRenderer, info[o], (int) ((x - largeur + 7) *(1f/factor)),  (int) ((y + 35+ o * 8)*(1f/factor)), getIntFromColor(249, 254, 255));
						 }
					 }
					 GL11.glScalef(1f/factor, 1f/factor, 1);
					if(2 <Q.get(i).GetElement().val)
					 {
						 factor = 1F;
						 GL11.glScalef(factor, factor, 1);
						 drawCenteredString(mc.fontRenderer, Q.get(i).GetElement().nbEvent + "/"+ Q.get(i).GetElement().maxEvent, (int) ((int) (x - largeur / 2)*(1f/factor)) ,  (int) (z*(1f/factor)),getIntFromColor(249, 198, 12));
						 GL11.glScalef(1f/factor, 1f/factor, 1);
					 }else if(Q.get(i).GetElement().val == -3)
					 {
						 factor = 1F;
						 GL11.glScalef(factor, factor, 1);
						 drawCenteredString(mc.fontRenderer, Q.get(i).GetElement().nbEvent + "/"+ Q.get(i).GetElement().maxEvent, (int) ((int) (x - largeur / 2)*(1f/factor)) ,  (int) (z*(1f/factor)),getIntFromColor(249, 198, 12));
						 GL11.glScalef(1f/factor, 1f/factor, 1);
						 
						 sec = 60-c.get(c.SECOND);
							if(sec <10)
							{
								secs = "0"+sec;
							}
							else {
								secs = sec+"";
							}
							int min = (20-c.get(c.MINUTE)) - 1;
							if(0 < min )
							{
								 factor = 1F;
								 GL11.glScalef(factor, factor, 1);
								 drawCenteredString(mc.fontRenderer, "Temps restant: "+(20-c.get(c.MINUTE))+":"+secs, (int) ((int) (x - largeur / 2)*(1f/factor)) ,  (int) ((z+20)*(1f/factor)), getIntFromColor(249, 198, 12));
								 GL11.glScalef(1f/factor, 1f/factor, 1);
							}else
							{
								factor = 1F;
								 GL11.glScalef(factor, factor, 1);
								 drawCenteredString(mc.fontRenderer, "Temps restant: 0:00", (int) ((int) (x - largeur / 2)*(1f/factor)) ,  (int) ((z+20)*(1f/factor)), getIntFromColor(249, 198, 12));
								 GL11.glScalef(1f/factor, 1f/factor, 1);
							} 
					 }
					else if(Q.get(i).GetElement().val == -1)
					 {
						sec = 60-c.get(c.SECOND);
						if(sec <10)
						{
							secs = "0"+sec;
						}
						else {
							secs = sec+"";
						}
						int min = (20-c.get(c.MINUTE)) - 1;
						if(0 < min )
						{
							 factor = 1F;
							 GL11.glScalef(factor, factor, 1);
							 drawCenteredString(mc.fontRenderer, "Temps restant: "+(20-c.get(c.MINUTE))+":"+secs, (int) ((int) (x - largeur / 2)*(1f/factor)) ,  (int) (z*(1f/factor)), getIntFromColor(249, 198, 12));
							 GL11.glScalef(1f/factor, 1f/factor, 1);
						}else
						{
							factor = 1F;
							 GL11.glScalef(factor, factor, 1);
							 drawCenteredString(mc.fontRenderer, "Temps restant: 0:00", (int) ((int) (x - largeur / 2)*(1f/factor)) ,  (int) (z*(1f/factor)), getIntFromColor(249, 198, 12));
							 GL11.glScalef(1f/factor, 1f/factor, 1);
						} 
					 }else if(Q.get(i).GetElement().val == -2 && main.dataHorraire != -1 || main.dataHorraire2 != -1)
					 {
						 factor = 1F;
						 GL11.glScalef(factor, factor, 1);
						 if(main.dataHorraire2 != -1 && main.dataHorraire3 != -1)
						 {
							 drawCenteredString(mc.fontRenderer, main.dataHorraire2 + " / "+main.dataHorraire3 , (int) ((int) (x - largeur / 2)*(1f/factor)) ,  (int) ((z-15)*(1f/factor)), getIntFromColor(249, 198, 12));
						 }
						
							 drawCenteredString(mc.fontRenderer, main.dataHorraire + " / 15", (int) ((int) (x - largeur / 2)*(1f/factor)) ,  (int) (z*(1f/factor)), getIntFromColor(249, 198, 12))
							 ;
						 
							 GL11.glScalef(1f/factor, 1f/factor, 1);
					 }
					 GL11.glPopMatrix();
					 lasthauteur = lasthauteur + hauteur;
					 
			 }
		 }
	 }
	 public static void drawRectFont(int p_73734_0_, int p_73734_1_, int p_73734_2_, int p_73734_3_, int p_73734_4_)
	    {
	        int j1;

	        if (p_73734_0_ < p_73734_2_)
	        {
	            j1 = p_73734_0_;
	            p_73734_0_ = p_73734_2_;
	            p_73734_2_ = j1;
	        }

	        if (p_73734_1_ < p_73734_3_)
	        {
	            j1 = p_73734_1_;
	            p_73734_1_ = p_73734_3_;
	            p_73734_3_ = j1;
	        }

	        float f3 = 200.0F;
	        float f = (float)(p_73734_4_ >> 16 & 255) / 255.0F;
	        float f1 = (float)(p_73734_4_ >> 8 & 255) / 255.0F;
	        float f2 = (float)(p_73734_4_ & 255) / 255.0F;
	        Tessellator tessellator = Tessellator.instance;
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
	        GL11.glColor4f(f, f1, f2, f3);
	        tessellator.startDrawingQuads();
	        tessellator.addVertex((double)p_73734_0_, (double)p_73734_3_, 0.0D);
	        tessellator.addVertex((double)p_73734_2_, (double)p_73734_3_, 0.0D);
	        tessellator.addVertex((double)p_73734_2_, (double)p_73734_1_, 0.0D);
	        tessellator.addVertex((double)p_73734_0_, (double)p_73734_1_, 0.0D);
	        tessellator.draw();
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_BLEND);
	    }
	 public int getIntFromColor(int Red, int Green, int Blue){
		    Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
		    Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
		    Blue = Blue & 0x000000FF; //Mask out anything not blue.

		    return 0x25000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
		}
	 public int getIntFromColorOpac(int Red, int Green, int Blue){
		    Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
		    Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
		    Blue = Blue & 0x000000FF; //Mask out anything not blue.

		    return 0x17500000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
		}
	 
	 private String[] getResponsivFromString(int max, String txt, FontRenderer font, float inf)
	 {
		 	int taille = txt.length();
		 	String[] Final = new String[(int) ((font.getStringWidth(txt)*inf) / max +1)];
			String string = "";
			boolean stat = false;
			char messageUnCaractere;
			int o = 0;
			for(int i = 0; i < taille; i++) 
			{
				messageUnCaractere = txt.charAt(i);
				if(Character.isWhitespace(messageUnCaractere))
				{
					if(font.getStringWidth(string) < max)
					{
						string += messageUnCaractere;
					}
					else
					{
						while(txt.charAt(i) != ' ')
						{
							i--;
						}
						string.substring(0, string.lastIndexOf(" "));
						Final[o] = string;
						string = "";
						o++;
					}
					
				}
				else
				{
					string += messageUnCaractere;
				}
			
			}
			Final[o] = string;
		 return Final;
	 }
}