package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.neocraft.main.main;
import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeTileEntity;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerContainer;
import fr.neocraft.main.ServeurManager.EnumSound;
import fr.neocraft.main.ServeurManager.SoundManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BoxUltimeGui extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/boxultime/background.png");
    private BoxUltimeTileEntity NeoTile;
    private IInventory playerInv;
    private boolean stat = false, perform = false;
    private int wait= 0;
    public BoxUltimeGui(BoxUltimeTileEntity tile, InventoryPlayer inventory, World w, EntityPlayer p)
    {
        super(new BoxUltimeContainer(tile, inventory, w, p));
        
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 170;
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
   
     int k = (this.width - 100) / 2; // on calcul la coordonn�e x du point en haut � gauche
     int l = (this.height - 20) / 2;
     this.buttonList.add(new GuiButton( 1, k, l -11, 100, 20, "Lancer !"));
     
    }
    private final SimpleNetworkWrapper network = main.getNetWorkServer();
    @Override
    protected void actionPerformed(GuiButton B)
    {
    	if(wait == 0)
    	{
    		wait = 20;
    		SoundManager.PlaySound(EnumSound.NeoMClick.getSound());
    		network.sendToServer(new NetWorkServer(new ServerContainer("ultime")));

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
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
        this.mc.getTextureManager().bindTexture(textures); // affiche la texture
        int k = (this.width - this.xSize) / 2; // on calcul la coordonn�e x du point en haut � gauche
        int l = (this.height - this.ySize) / 2; // on calcul la coordonn�e y du point en haut � gauche
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize); // on desine la texture, la fonction � pour argument point x de d�part, point y de d�part, vecteur u, vecteur v, largeur, hauteur)
    }
}