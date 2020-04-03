package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.ServeurManager.DailyManager;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;

public class GuiTutoDos extends GuiScreen
{
	private String[] info;
	private Minecraft MC = Minecraft.getMinecraft();
	
    private String titre = "";

    public GuiTutoDos(String f, String title)
    {
    	ScaledResolution scaled = new ScaledResolution(MC, MC.displayWidth, MC.displayHeight);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
    	this.info = getResponsivFromString((width / 5)* 3 - 30, f,MC.fontRenderer);
    	this.titre = title;
    	startcounter();
     
    }
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	this.mc.thePlayer.closeScreen();
    	this.mc.displayGuiScreen(new GuiBvn(DailyManager.getIdDayByName()));
    	SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick)
    {
	    int x = this.width / 5;
	    int y = this.height / 2;
	    drawRect(x, y+((info.length * 15) /2), x *4, y-((info.length * 15) /2)-30, Integer.MIN_VALUE );
	    drawRectFont(x, y-((info.length * 15) /2)-32, x *4, y-((info.length * 15) /2)-30, getIntFromColor(249, 198, 12));
   
		 GL11.glPushMatrix();
		 drawCenteredString(MC.fontRenderer, this.titre,(int) ((int)this.width / 2), y-((info.length * 15) /2) - 20 , getIntFromColor(249, 198, 12));
		 if(info != null)
		 {
			 for(int o =0; o < info.length; o++)
			 {
				 drawCenteredString(MC.fontRenderer, info[o],(int) ((int) this.width / 2), (y-((info.length * 15) /2))+o*15 , getIntFromColor(249, 254, 255));
			 }
		 }

		 GL11.glPopMatrix();
		 
        super.drawScreen(mouseX, mouseY, partialTick);

    }
    
    @Override
    public void onGuiClosed() {
    	SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
    }
    
    @Override
    public void initGui() 
    {
    	this.buttonList.add(new GuiButton( 1, this.width/2 - 30, this.height/2+((50+info.length * 24) /2) -10, 60, 20, "Fermer"));
    	for(int i = 0; i < this.buttonList.size(); i++)
    	{
    		((GuiButton)this.buttonList.get(i)).enabled = false;
    	}
    }
    private void startcounter() {
		 Thread t = new Thread() {
			@Override
			public void run()
			{
				for(int o = 1; o < 15; o++)
				{
					for(int i = 0; i < buttonList.size(); i++)
			    	{
			    		((GuiButton)buttonList.get(i)).displayString = ""+(10-o);
			    	}
					try {
						this.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				for(int i = 0; i < buttonList.size(); i++)
		    	{
		    		((GuiButton)buttonList.get(i)).enabled = true;
		    		((GuiButton)buttonList.get(i)).displayString = "Fermer";
		    	}
			}
		};
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
		
	}
    private String[] getResponsivFromString(int max, String txt, FontRenderer font)
	 {
		 	int taille = txt.length();
		 	String[] Final = new String[font.getStringWidth(txt) / max +1];
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
 
}
