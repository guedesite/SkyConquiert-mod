package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.PlanterTileEntity;
import fr.neocraft.main.Proxy.Serveur.TileEntity.PlanteurContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class PlanteurGui extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/container/cupboard.png");
    private PlanterTileEntity NeoTile;
    private IInventory playerInv;

    public PlanteurGui(PlanterTileEntity tile, InventoryPlayer inventory)
    {
        super(new PlanteurContainer(tile, inventory));
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 170;
    }
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String tileName = this.NeoTile.hasCustomInventoryName() ? this.NeoTile.getInventoryName() : I18n.format(this.NeoTile.getInventoryName());
        this.fontRendererObj.drawString(tileName, (this.xSize - this.fontRendererObj.getStringWidth(tileName)) / 2, 6, 0);
        String invName = this.playerInv.hasCustomInventoryName() ? this.playerInv.getInventoryName() : I18n.format(this.playerInv.getInventoryName());
        this.fontRendererObj.drawString(invName, (this.xSize - this.fontRendererObj.getStringWidth(invName)) / 2, this.ySize - 96, 0);
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