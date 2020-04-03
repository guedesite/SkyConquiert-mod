package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.NeoPermissionTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.PermissionQuestContainer;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerContainer;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PermissionQuestGui extends GuiContainer
{
    private static final ResourceLocation textures=  new ResourceLocation(neoreference.MOD_ID, "textures/gui/permission/background.png");;
    private NeoPermissionTileEntity NeoTile;
    private IInventory playerInv;
    private int nb = 0;
    public PermissionQuestGui(NeoPermissionTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p, int i)
    {
        super(new PermissionQuestContainer(tile, inventory, w, p, i));
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 125;
        this.nb = i;
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
    	String tileName;
    	if(nb == 0)
    	{
    		tileName = I18n.format("neo.perm.title1");
		}
		else if(nb == 1)
		{
			tileName = I18n.format("neo.perm.title2");
    	}
		else {
			tileName = I18n.format("neo.perm.title3");
		}
         this.fontRendererObj.drawString(tileName, (this.xSize - this.fontRendererObj.getStringWidth(tileName)) / 2, 6, 0);
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
     int k = (this.width - 100) / 2; // on calcul la coordonnée x du point en haut à gauche
     int l = (this.height - 20) / 2;
     if(this.nb == 0)
     {
    	 this.buttonList.add(new PermissionQuestGui.NextPageButton( 0, ((this.width -this.xSize)/2) + 135 , ((this.height - this.ySize) /2) + 105 , true));
    	 
     }
     else if(this.nb == 1)
     {
    	 this.buttonList.add(new PermissionQuestGui.NextPageButton( 1, ((this.width -this.xSize)/2) + 15 , ((this.height - this.ySize)/2) + 105 , false));
    	 this.buttonList.add(new PermissionQuestGui.NextPageButton( 0, ((this.width -this.xSize)/2) + 135 , ((this.height - this.ySize) /2) + 105 , true));
     } else
     {
    	 this.buttonList.add(new PermissionQuestGui.NextPageButton( 1, ((this.width -this.xSize)/2) + 15 , ((this.height - this.ySize)/2) + 105 , false));
     }
     
    }
    
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	SoundManager.PlaySound(EnumSound.NeoMClick.getSound());
    	if(nb == 0)
    	{
    		main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerContainer("PERM0")));
    	}
    	else if(nb == 1) {
    		if(B.id == 1)
    		{
    			main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerContainer("PERM1")));
    		}else {
    			main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerContainer("PERM2")));
    		}
    	}
    	else {
    		main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerContainer("PERM0")));
    	}
    
    }
    @Override
    public void updateScreen()
    {
        super.updateScreen();

        if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
        {
            this.mc.thePlayer.closeScreen();
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