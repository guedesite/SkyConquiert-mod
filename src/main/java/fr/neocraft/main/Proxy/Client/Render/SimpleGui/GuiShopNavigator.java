package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerContainer;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class GuiShopNavigator extends GuiScreen
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/shop/navigation.png");
    private boolean stat = false, perform = false;
    private final SimpleNetworkWrapper network = main.getNetWorkServer();
    private int wait= 0;
 
    @Override
    public void onGuiClosed()
    {
    	SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
     
    }
    @Override
    public void initGui()
    {
	     super.initGui();
	     int k = (this.width - 214) / 2; // on calcul la coordonn�e x du point en haut � gauche
	     int l = (this.height - 71) / 2; // on calcul la coordonn�e y du point en haut � gauche
	     SoundManager.PlaySound(EnumSound.NeoMOpen.getSound());
	     this.buttonList.add(new GuiButton( 1, k+7, l+33, 60, 20, "Mes ventes"));
	     this.buttonList.add(new GuiButton( 2, k+71, l+33, 52, 20, I18n.format("neo.marche")));
	     this.buttonList.add(new GuiButton( 3, k+127, l+33, 80, 20, I18n.format("neo.hotel")));	
    }
    
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	if(wait == 0)
    	{
    		wait = 20;

    		if(B.id == 1)
    		{
    			network.sendToServer(new NetWorkServer(new ServerContainer("HDVMy")));
    		}else if(B.id == 2)
    		{
    			network.sendToServer(new NetWorkServer(new ServerContainer("HDVMarche")));
    		} else if(B.id == 3)
    		{
    			network.sendToServer(new NetWorkServer(new ServerContainer("HDVMShop")));
    		}

    	}
    }
    @Override
    public void updateScreen()
    {
        super.updateScreen();

        if(wait != 0)
    	{
        	wait--;
    	}
        if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
        {
            this.mc.thePlayer.closeScreen();
        }

    }
    public int getIntFromColor(int Red, int Green, int Blue){
	    Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
	    Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
	    Blue = Blue & 0x000000FF; //Mask out anything not blue.

	    return 0x25000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTick)
    {
    	
    	   GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
           this.mc.getTextureManager().bindTexture(textures); // affiche la texture
           int k = (this.width - 214) / 2; // on calcul la coordonn�e x du point en haut � gauche
           int l = (this.height - 71) / 2; // on calcul la coordonn�e y du point en haut � gauche
           this.drawTexturedModalRect(k, l, 0, 0, 214, 71); 
           
           String tileName = I18n.format("Navigation");
           int d =  Math.round(this.fontRendererObj.getStringWidth(tileName) * 1.5F);
           GL11.glScalef(1.5F, 1.5F, 1);
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           this.fontRendererObj.drawString(tileName, Math.round((k+(214 - d) / 2)/1.5F), Math.round((l+12)/1.5F), getIntFromColor(255,255,255));
           GL11.glScalef(1/1.5F, 1/1.5F, 1);
         
        super.drawScreen(mouseX, mouseY, partialTick);

    }

}