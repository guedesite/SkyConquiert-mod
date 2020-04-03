package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiShopNavigator;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvContainerPerso;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvTileEntityPerso;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerContainer;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class hdvGuiPerso  extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/shop/personnel.png");
    private hdvTileEntityPerso NeoTile;
    private IInventory playerInv;
    private boolean stat = false, perform = false;
    private final SimpleNetworkWrapper nw = main.getNetWorkServer();
    private int wait= 0;
    private GuiTextField unite, stack;
    public hdvGuiPerso(hdvTileEntityPerso tile, InventoryPlayer inventory, World w, EntityPlayer p)
    {
        super(new hdvContainerPerso(tile, inventory, w, p));
        
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 256;
        this.xSize = 245;
    }

    @Override
    public void onGuiClosed()
    {
    	 SoundManager.PlaySound(EnumSound.NeoMClose.getSound());
        if (this.mc.thePlayer != null)
        {
            this.inventorySlots.onContainerClosed(this.mc.thePlayer);
        }
    }
    @Override
    public void initGui()
    {
     super.initGui();
     SoundManager.PlaySound(EnumSound.NeoMOpen.getSound());
   
     int k = (this.width - this.xSize) / 2; // on calcul la coordonn�e x du point en haut � gauche
     int l = (this.height - this.ySize) / 2;
     
     this.unite = new GuiTextField(this.fontRendererObj,k+103 , l+24, 57, 16);
     this.unite.setMaxStringLength(9);
     this.unite.setFocused(true);
     this.unite.setText("1");
     this.unite.setEnableBackgroundDrawing(true);
     
     this.stack = new GuiTextField(this.fontRendererObj,k+103 ,l+62, 57, 16);
     this.stack.setMaxStringLength(15);
     this.stack.setFocused(false);
     this.stack.setText("64");
     this.stack.setEnableBackgroundDrawing(true);
     
     this.buttonList.add(new GuiButton( 1,k+ 100 , l+86, 64, 20, "Vendre !"));
     this.buttonList.add(new GuiButton( 2, k+181 , l+153, 56, 20, "Retour"));
     
	   
    }
    
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        this.unite.textboxKeyTyped(p_73869_1_, p_73869_2_);
 

        if (p_73869_2_ != 28 && p_73869_2_ != 156)
        {
            if (p_73869_2_ == 1)
            {
            	Minecraft.getMinecraft().thePlayer.closeScreen();
    			Minecraft.getMinecraft().displayGuiScreen(new GuiShopNavigator());
            }
        }
        if(!IsValid() && this.buttonList != null)
        {
        	 for (int i = 0; i < this.buttonList.size(); i++) {
 	            if (((GuiButton)this.buttonList.get(i)).id ==1) {
 	            	((GuiButton)this.buttonList.get(i)).enabled = false;
 	            }
 	        }
        }
        else if(IsValid())
        {
        	if(this.NeoTile.getStackInSlot(0) != null)
        	{
        		this.stack.setText(""+(Integer.parseInt(this.unite.getText()) * this.NeoTile.getStackInSlot(0).stackSize));
        	}
        	else {
        		this.stack.setText(""+(Integer.parseInt(this.unite.getText()) * 64));
        	}
        }
    }

    private boolean IsValid()
    {
    	try {
    		int u = Integer.parseInt(this.stack.getText());
    		u = Integer.parseInt(this.unite.getText());
    		if(this.NeoTile.getStackInSlot(0)== null)
    		{
    			return false;
    		}
    		if(u < 1)
    		{
    			return false;
    		}
    		for(int i = 1; i < 28; i++)
    		{
    			if(this.NeoTile.getStackInSlot(i) == null)
    			{
    				return true;
    			}
    		}
    		return false;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }
    private boolean IsValid2()
    {
    	try {
    		int u = Integer.parseInt(this.stack.getText());
    		u = Integer.parseInt(this.unite.getText());
    		
    		if(u < 1)
    		{
    			return false;
    		}
    		
    		return true;
    	}
    	catch(Exception e)
    	{
    		return false;
    	}
    }
    /**
     * Called when the mouse is clicked.
     */
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
    		if(B.id == 1 && IsValid())
    		{
    			nw.sendToServer(new NetWorkServer( new ServerContainer("addhdv-"+Integer.parseInt(this.unite.getText()))));
    			wait = 20;
    		}
    		else if(B.id == 2)
        	{
    			Minecraft.getMinecraft().thePlayer.closeScreen();
    			Minecraft.getMinecraft().displayGuiScreen(new GuiShopNavigator());
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
        if(!IsValid() && this.buttonList != null)
        {
        	 for (int i = 0; i < this.buttonList.size(); i++) {
 	            if (((GuiButton)this.buttonList.get(i)).id ==1) {
 	            	((GuiButton)this.buttonList.get(i)).enabled = false;
 	            }
 	        }
        }
        else
        {
        	for (int i = 0; i < this.buttonList.size(); i++) {
 	            if (((GuiButton)this.buttonList.get(i)).id ==1) {
 	            	((GuiButton)this.buttonList.get(i)).enabled = true;
 	            }
 	        }
        }
        
        if(IsValid2())
        {
        	if(this.NeoTile.getStackInSlot(0) != null)
        	{
        		this.stack.setText(""+(Integer.parseInt(this.unite.getText()) * this.NeoTile.getStackInSlot(0).stackSize));
        	}
        	else
        	{
        		this.stack.setText(""+(Integer.parseInt(this.unite.getText()) * 64));
        	}
        }

    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
    	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(this.NeoTile.getInventoryName(),( this.xSize - this.fontRendererObj.getStringWidth(this.NeoTile.getInventoryName())) /2-35 ,-20,getIntFromColor(255, 255 ,255));
     	
         this.fontRendererObj.drawString(I18n.format("neo.unit2e"),100 , 9, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString("Totale:",100, 47, getIntFromColor(255,255,255));
         
    }
    public int getIntFromColor(int Red, int Green, int Blue){
	    Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
	    Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
	    Blue = Blue & 0x000000FF; //Mask out anything not blue.

	    return 0x25000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
    

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise

        this.mc.getTextureManager().bindTexture(textures); // affiche la texture
        int k = (this.width - this.xSize) / 2; // on calcul la coordonn�e x du point en haut � gauche
        int l = (this.height - this.ySize) / 2; // on calcul la coordonn�e y du point en haut � gauche
        this.drawTexturedModalRect(k , l, 0, 0, this.xSize, this.ySize); // on desine la texture, la fonction � pour argument point x de d�part, point y de d�part, vecteur u, vecteur v, largeur, hauteur)
        
        this.unite.drawTextBox();
    	this.stack.drawTextBox();
    }
}