package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.btn.NeoButtonClose;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.btn.NeoButtonCloseExtended;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.btn.NeoButtonDiscord;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.btn.NeoButtonWeb;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.btn.NeoGuiButton;
import fr.neocraft.main.ServeurManager.DailyManager;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;

public class GuiBvn extends GuiScreen
{
    private static ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/daily/Welcome"+DailyManager.getIdDayByName()+".png");
  private static boolean IsList = false;
    private int DayId = 0;
    private int xSize = 256, ySize = 256;

    public GuiBvn(int day)
    {
      this.DayId = day;
    }

    @Override
    public void initGui()
    {
    	SoundManager.PlaySound(EnumSound.NeoMOpen.getSound());
     super.initGui();
     this.buttonList.add(new NeoButtonClose( 0, 706, 38, 24, 24, ""));
     this.buttonList.add(new NeoButtonCloseExtended( 1, 938, 48, 38, 39, ""));
     this.buttonList.add(new NeoButtonWeb( 2, 84, 652, 173, 69, ""));
     this.buttonList.add(new NeoButtonDiscord( 3, 510, 652, 173, 69, ""));
     this.buttonList.add(new NeoGuiButton( 4, 184, 102, 400, 400, ""));
     this.buttonList.add(new NeoGuiButton( 4, 184, 539, 400, 80, ""));
     
    }
    
    @Override
    public void onGuiClosed() {
    	SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
    }
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	if(!IsList)
    	{
	    	if(B.id == 0)
	    	{
	    		this.mc.thePlayer.closeScreen();
	    		SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
	    	} else if(B.id == 2){
	    		String url = "https://neocraft.fr";
	
	            if(Desktop.isDesktopSupported()){
	                Desktop desktop = Desktop.getDesktop();
	                try {
	                    desktop.browse(new URI(url));
	                    SoundManager.PlaySound(EnumSound.NeoMClick.getSound());
	                } catch ( URISyntaxException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }else{
	                Runtime runtime = Runtime.getRuntime();
	                try {
	                    runtime.exec("xdg-open " + url);
	                    SoundManager.PlaySound(EnumSound.NeoMClick.getSound());
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	    	}else if(B.id == 3){
	    		String url = "https://discord.gg/qAFsS67";
	
	                if(Desktop.isDesktopSupported()){
	                    Desktop desktop = Desktop.getDesktop();
	                    try {
	                        desktop.browse(new URI(url));
	                        SoundManager.PlaySound(EnumSound.NeoMClick.getSound());
	                    } catch ( URISyntaxException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }catch (IOException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                }else{
	                    Runtime runtime = Runtime.getRuntime();
	                    try {
	                        runtime.exec("xdg-open " + url);
	                        SoundManager.PlaySound(EnumSound.NeoMClick.getSound());
	                    } catch (IOException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                }
	    	} else if(B.id == 4){
	    		IsList = true;
	    		textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/daily/WelcomeBonus.png");
	    	}
    	}else {
    		
    		if(B.id == 1)
    		{
    			IsList = false;
    			textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/daily/Welcome"+DailyManager.getIdDayByName()+".png");
    		}
    	}
    }

    private int debutx =0, debuty = 0;

	private GuiButton selectedButton;

    @Override
    public void updateScreen()
    {
        super.updateScreen();

    }

   private static float scale = 1F;
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {

    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.mc.getTextureManager().bindTexture(textures);
  

            GL11.glPushMatrix();
           // float scale = 1.625F;
            scale=((float)this.height) / ((float)256);
            if(scale < 1)
            {
            	scale = 1f;
            }
         
           GL11.glScalef(scale, scale, 1F);
      	 this.debuty = Math.round((this.height - this.ySize*scale) / 2);
      	this.debutx  = Math.round((this.width - this.xSize* scale) / (2*scale));

           
            this.drawTexturedModalRect(this.debutx,this.debuty, 0, 0, this.xSize, this.ySize);
            GL11.glTranslatef(this.debutx, this.debuty, 0);
            for (int k = 0; k < this.buttonList.size(); ++k)
            {
            	if(((NeoGuiButton)this.buttonList.get(k)).id == 1  && this.IsList)
            	{
            		((NeoGuiButton)this.buttonList.get(k)).drawButton(this.mc, p_73863_1_, p_73863_2_, scale, this.debutx, this.debuty);
            	}
            	else if(((NeoGuiButton)this.buttonList.get(k)).id != 1  && !this.IsList)
            	{
            		((NeoGuiButton)this.buttonList.get(k)).drawButton(this.mc, p_73863_1_, p_73863_2_, scale, this.debutx, this.debuty);
            	}
            }
            GL11.glPopMatrix();
    }
    
    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        if (p_73864_3_ == 0)
        {
            for (int l = 0; l < this.buttonList.size(); ++l)
            {
                NeoGuiButton guibutton = (NeoGuiButton)this.buttonList.get(l);

                if (guibutton.mousePressed(this.mc, p_73864_1_, p_73864_2_, scale, this.debutx, this.debuty))
                {
                    ActionPerformedEvent.Pre event = new ActionPerformedEvent.Pre(this, guibutton, this.buttonList);
                    if (MinecraftForge.EVENT_BUS.post(event))
                        break;
                    this.selectedButton = event.button;
                    event.button.func_146113_a(this.mc.getSoundHandler());
                    this.actionPerformed(event.button);
                    if (this.equals(this.mc.currentScreen))
                        MinecraftForge.EVENT_BUS.post(new ActionPerformedEvent.Post(this, event.button, this.buttonList));
                }
            }
        }
    }

  

}
