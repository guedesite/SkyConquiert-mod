package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiShopNavigator;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvContainerPublic;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvTileEntityPublic;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerContainer;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class hdvGuiPublic  extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/shop/shop.png");
    private hdvTileEntityPublic NeoTile;
    private IInventory playerInv;
    private boolean stat = false, perform = false;
    private final SimpleNetworkWrapper nw = main.getNetWorkServer();
    private int wait= 0, page = 1;;
    public hdvGuiPublic(hdvTileEntityPublic tile, InventoryPlayer inventory, World w, EntityPlayer p)
    {
        super(new hdvContainerPublic(tile, inventory, w, p));
        
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 250;
        this.xSize = 246;
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
	     this.buttonList.add(new GuiButton( 1, k + 190, l +155, 52, 20, "Retour"));
	     this.buttonList.add(new NextPageButton(2, k+141, l+169, true));
	     this.buttonList.add(new NextPageButton(3, k+20, l+169, false));
    }
    
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	if(wait == 0)
    	{
    		wait = 2;
    		if(B.id == 1)
    		{
	    		Minecraft.getMinecraft().thePlayer.closeScreen();
				Minecraft.getMinecraft().displayGuiScreen(new GuiShopNavigator());
    		} else if(B.id == 2)
    		{
    			if(this.NeoTile.getStackInSlot(71) != null)
    	        {
    				
    				nw.sendToServer(new NetWorkServer( new ServerContainer("HDVNEXT")));
    				wait = 20;
    	    		
    				this.page ++;
    				
    	        }
    		} else if(B.id == 3)
    		{
    			if(this.page > 1)
    	        {
    				nw.sendToServer(new NetWorkServer( new ServerContainer("HDVPREVIOUS")));
    				wait = 20;
    				this.page--;
    				
    	        }
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
        
        if(this.NeoTile.getStackInSlot(71) == null)
        {
        	 for (int i = 0; i < this.buttonList.size(); i++) {
  	            if (((GuiButton)this.buttonList.get(i)).id ==2) {
  	            	((GuiButton)this.buttonList.get(i)).visible = false;
  	            }
  	        }
        }
        else
        {
        	 for (int i = 0; i < this.buttonList.size(); i++) {
   	            if (((GuiButton)this.buttonList.get(i)).id ==2) {
   	            	((GuiButton)this.buttonList.get(i)).visible = true;
   	            }
   	        }
        }
        if(this.page > 1)
        {
        	
        	 for (int i = 0; i < this.buttonList.size(); i++) {
   	            if (((GuiButton)this.buttonList.get(i)).id ==3) {
   	            	((GuiButton)this.buttonList.get(i)).visible = true;
   	            }
   	        }
         }
         else
         {
         	 for (int i = 0; i < this.buttonList.size(); i++) {
    	            if (((GuiButton)this.buttonList.get(i)).id ==3) {
    	            	((GuiButton)this.buttonList.get(i)).visible = false;
    	            }
    	        }
         }

    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {

    			String name = this.NeoTile.getInventoryName()+" | Page "+this.page;
    
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         this.fontRendererObj.drawString(name, (this.xSize - this.fontRendererObj.getStringWidth(name)) / 2 -30 , 10, getIntFromColor(255,255,255));
         
         
    }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
        this.mc.getTextureManager().bindTexture(textures); // affiche la texture
        int k = (this.width - this.xSize) / 2; // on calcul la coordonn�e x du point en haut � gauche
        int l = (this.height - this.ySize) / 2; // on calcul la coordonn�e y du point en haut � gauche
        this.drawTexturedModalRect(k , l, 0, 0, this.xSize, this.ySize); // on desine la texture, la fonction � pour argument point x de d�part, point y de d�part, vecteur u, vecteur v, largeur, hauteur)
    }
    public int getIntFromColor(int Red, int Green, int Blue){
	    Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
	    Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
	    Blue = Blue & 0x000000FF; //Mask out anything not blue.

	    return 0x25000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}
    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton
        {
            public final boolean field_146151_o;
 
            private static final ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
            
            public NextPageButton(int p_i1079_1_, int p_i1079_2_, int p_i1079_3_, boolean p_i1079_4_)
            {
                super(p_i1079_1_, p_i1079_2_, p_i1079_3_, 23, 13, "");
                this.field_146151_o = p_i1079_4_;
            }

			/**
             * Draws this button to the screen.
             */
            public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
            {
                if (this.visible)
                {
                    boolean flag = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    p_146112_1_.getTextureManager().bindTexture(bookGuiTextures);
                    int k = 0;
                    int l = 192;

                    if (flag)
                    {
                        k += 23;
                    }

                    if (!this.field_146151_o)
                    {
                        l += 13;
                    }

                    this.drawTexturedModalRect(this.xPosition, this.yPosition, k, l, 23, 13);
                }
            }
        }
}