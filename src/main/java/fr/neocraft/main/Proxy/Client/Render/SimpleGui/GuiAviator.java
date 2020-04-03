package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class GuiAviator extends GuiScreen
{
	private boolean drag = false;
	private String[] info;
	private Minecraft MC = Minecraft.getMinecraft();
	
    private String titre = "";

    public GuiAviator(String f, String title, boolean i)
    {
    	ScaledResolution scaled = new ScaledResolution(MC, MC.displayWidth, MC.displayHeight);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
    	this.info = getResponsivFromString((width / 5)* 3 - 30, f,MC.fontRenderer);
    	this.titre = title;
    	this.drag = i;
    	
     
    }
    @Override
    public void onGuiClosed() {
    	SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
    }
    
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	if(B.id == 1)
    	{
    		this.mc.thePlayer.closeScreen();
    		SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
    	} else if(B.id == 2){
    	//	main.getNetWorkShopper().sendToServer(new NetWorkManagerShopper("PlayerLaunchDrag"));
    	}
    	
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick)
    {
	    int x = this.width / 5;
	    int y = this.height / 2;
	    drawRect(x, y+((info.length * 15) /2), x *4, y-((info.length * 15) /2)-30, Integer.MIN_VALUE );
   
		 GL11.glPushMatrix();
		 drawCenteredString(MC.fontRenderer, this.titre,(int) ((int)this.width / 2), y-((info.length * 15) /2) - 20 , Integer.parseInt("FF5900", 16));
		 if(info != null)
		 {
			 for(int o =0; o < info.length; o++)
			 {
				 drawCenteredString(MC.fontRenderer, info[o],(int) ((int) this.width / 2), (y-((info.length * 15) /2))+o*15 , Integer.parseInt("FF5900", 16));
			 }
		 }

		 GL11.glPopMatrix();
		 
        super.drawScreen(mouseX, mouseY, partialTick);

    }
    @Override
    public void initGui() 
    {
    	SoundManager.PlaySound(EnumSound.NeoMOpen.getSound());
    	if(this.drag)
    	{
	    	this.buttonList.add(new GuiButton( 1, this.width/2 - 90, this.height/2+((50+info.length * 24) /2) -10, 60, 20, "Plus tard ..."));
	    	this.buttonList.add(new GuiButton( 2, this.width/2 +30, this.height/2+((50+info.length * 24) /2) -10, 60, 20, "J'accepte !"));
    	} else {
    		this.buttonList.add(new GuiButton( 1, this.width/2 - 30, this.height/2+((50+info.length * 24) /2) -10, 60, 20, "Plus tard ..."));
    	}
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

 
}