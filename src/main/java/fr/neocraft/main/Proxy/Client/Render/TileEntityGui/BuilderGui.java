package fr.neocraft.main.Proxy.Client.Render.TileEntityGui;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BuilderContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BuilderTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class BuilderGui extends GuiContainer
{
    private static final ResourceLocation textures = new ResourceLocation(neoreference.MOD_ID, "textures/gui/container/Builder.png");
    private BuilderTileEntity NeoTile;
    private IInventory playerInv;

    public BuilderGui(BuilderTileEntity tile, InventoryPlayer inventory)
    {
        super(new BuilderContainer(tile, inventory));
        this.NeoTile = tile;
        this.playerInv = inventory;
        this.allowUserInput = false;
        this.ySize = 235;
    }
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
     }
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialRenderTick, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F); // on colorise
        this.mc.getTextureManager().bindTexture(textures); // affiche la texture
        int k = (this.width - this.xSize) / 2; // on calcul la coordonnée x du point en haut à gauche
        int l = (this.height - this.ySize) / 2; // on calcul la coordonnée y du point en haut à gauche
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize); // on desine la texture, la fonction à pour argument point x de départ, point y de départ, vecteur u, vecteur v, largeur, hauteur)
    }
}