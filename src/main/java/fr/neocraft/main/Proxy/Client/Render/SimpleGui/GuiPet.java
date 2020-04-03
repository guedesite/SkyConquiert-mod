package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.btn.NeoGuiButton;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerPetChoose;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent;
import net.minecraftforge.common.MinecraftForge;

public class GuiPet extends GuiScreen
{
    private static ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/PetBackground.png");
  
    private int xSize = 256, ySize = 256;



    
    @Override
    public void initGui()
    {
    	super.initGui();
    	SoundManager.PlaySound(EnumSound.NeoMOpen.getSound());

     this.buttonList.add(new NeoGuiButton( 0, 42, 234, 680, 70, ""));
     this.buttonList.add(new NeoGuiButton( 1, 42, 320, 680, 70, ""));
     this.buttonList.add(new NeoGuiButton( 2, 42, 407, 680, 70, ""));
     this.buttonList.add(new NeoGuiButton( 3, 42, 494, 680, 70, ""));
     
     
    }

    
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	 main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerPetChoose(B.id)));
    	this.mc.thePlayer.closeScreen();
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
            	
            		((NeoGuiButton)this.buttonList.get(k)).drawButton(this.mc, p_73863_1_, p_73863_2_, scale, this.debutx, this.debuty);
            	
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
