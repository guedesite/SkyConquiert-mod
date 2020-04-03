package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.FuturistInputTileEntity;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerCodeFuturistInput;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public class FuturistInputGui extends GuiScreen
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/futur/Input.png");
  
    private int xSize = 97, ySize = 99;
    
    private final SimpleNetworkWrapper nw = main.getNetWorkServer();
    private int wait= 0;
    private GuiTextField unite;
    private FuturistInputTileEntity NeoTile;
    
    private int x,y,z,world;
    
    public FuturistInputGui(FuturistInputTileEntity tile, int x2, int y2, int z2, int world2)
    {
    	NeoTile=tile;
      x = x2;
     y = y2; 
     z = z2;
     world = world2;
       
    }


    @Override
    public void initGui()
    {
     super.initGui();
     SoundManager.PlaySound(EnumSound.NeoMOpen.getSound());
   
     int k = (this.width - this.xSize) / 2; 
     int l = (this.height - this.ySize) / 2;
     
     this.unite = new GuiTextField(this.fontRendererObj,k+13 , l+38, 71, 21);
     this.unite.setMaxStringLength(9);
     this.unite.setFocused(true);
     
     
     this.unite.setEnableBackgroundDrawing(true);
     
     
  
     this.buttonList.add(new GuiButton( 0, k+11 , l+67, 77, 20, "Valider"));
     
	   
    }
    
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
    	if(p_73869_1_ != ' ')
    	{
    		this.unite.textboxKeyTyped(p_73869_1_, p_73869_2_);
    	}
 

        if (p_73869_2_ != 28 && p_73869_2_ != 156)
        {
            if (p_73869_2_ == 1)
            {
            	Minecraft.getMinecraft().thePlayer.closeScreen();
            }
        }
      
    }

    protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
    {
        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
        this.unite.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
    }
    
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	if(wait == 0)
    	{
    		if(B.id == 0 && !unite.getText().replace(" ", "").equals(""))
    		{
    			if(!NeoTile.code.equals(""))
    			{
    				nw.sendToServer(new NetWorkServer( new ServerCodeFuturistInput(x,y,z,world, unite.getText(), 1)));
    				
    			} else {
    				nw.sendToServer(new NetWorkServer( new ServerCodeFuturistInput(x,y,z,world, unite.getText(), 0)));
    				NeoTile.code = unite.getText();
    			}
    			//Minecraft.getMinecraft().thePlayer.closeScreen();

    		}
    	}
    }
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        this.unite.updateCursorCounter();
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
	public void drawScreen(int x, int y, float partialRenderTick)
    {
    	
    

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
        GL11.glPushMatrix();
        this.mc.getTextureManager().bindTexture(textures); // affiche la texture
        int k = (this.width - this.xSize) / 2; // on calcul la coordonn�e x du point en haut � gauche
        int l = (this.height - this.ySize) / 2; // on calcul la coordonn�e y du point en haut � gauche
        this.drawTexturedModalRect(k , l, 0, 0, this.xSize, this.ySize); // on desine la texture, la fonction � pour argument point x de d�part, point y de d�part, vecteur u, vecteur v, largeur, hauteur)
        
        GL11.glColor4f(0F, 0F, 0F, 0F);
        if(NeoTile.code.equals(""))
        {
        	for(int i = 0; i < 10 ; i++)
        	{
        		this.fontRendererObj.drawString(I18n.format("neo.futur.input.define"),k+( this.xSize - this.fontRendererObj.getStringWidth(I18n.format("neo.futur.input.define"))) /2,l+7,getIntFromColor(0, 0 ,0));
        	}
        } else
	    {
        	for(int i = 0; i < 10 ; i++)
	    	{
	        	this.fontRendererObj.drawString(I18n.format("neo.futur.input.try"),k+( this.xSize - this.fontRendererObj.getStringWidth(I18n.format("neo.futur.input.try"))) /2,l+7,getIntFromColor(0,0 ,0));
	    	}
	   }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.unite.drawTextBox();
        GL11.glPopMatrix();
    	super.drawScreen(x, y, partialRenderTick);
    }
}