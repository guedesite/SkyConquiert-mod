package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.DragonQuestContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.NeoDragonTileEntity;
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

public class DragonQuestGui extends GuiContainer
{
    private static ResourceLocation textures;
    private NeoDragonTileEntity NeoTile;
    private IInventory playerInv;
    private int nb = 0;
    public DragonQuestGui(NeoDragonTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p, int i)
    {
        super(new DragonQuestContainer(tile, inventory, w, p, i));
        this.nb = i;
        textures =  new ResourceLocation(neoreference.MOD_ID, "textures/gui/dragon/background-"+i+".png");
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 256;
        this.xSize = 256;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
       }
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	SoundManager.PlaySound(EnumSound.NeoMClick.getSound());
		
    	if(nb == 0)
    	{
    		main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerContainer("DRAG0")));
    	}
    	else {
    		main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerContainer("DRAG1")));
        	
    	}
    
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
    	   GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
           this.mc.getTextureManager().bindTexture(textures); // affiche la texture
           int k = (this.width - this.xSize) / 2; // on calcul la coordonn�e x du point en haut � gauche
           int l = (this.height - this.ySize) / 2; // on calcul la coordonn�e y du point en haut � gauche
           this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize); // on desine la texture, la fonction � pour argument point x de d�part, point y de d�part, vecteur u, vecteur v, largeur, hauteur)
     
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
	     if(this.nb == 0)
	     {
	    	 this.buttonList.add(new DragonQuestGui.NextPageButton( 0, ((this.width -this.xSize)/2)+155 , ((this.height -this.ySize)/2)+173 , true));
	    	 
	     }
	     else
	     {
	    	 this.buttonList.add(new DragonQuestGui.NextPageButton( 0,((this.width -this.xSize)/2)+ 78,((this.height -this.ySize)/2)+ 173 , false));
	     }
    }
   
    @SideOnly(Side.CLIENT)
    static class NextPageButton extends GuiButton
        {
            private final boolean field_146151_o;
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