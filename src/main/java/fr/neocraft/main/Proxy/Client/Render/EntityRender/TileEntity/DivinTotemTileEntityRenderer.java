package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.DivinTotemTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class DivinTotemTileEntityRenderer extends TileEntitySpecialRenderer
{
    public static DivinTotemModel model = new DivinTotemModel();
    public static ResourceLocation texture = new ResourceLocation(neoreference.MOD_ID + ":textures/models/blocks/DivinTotem.png");
    public void renderTileEntity()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }
    
    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float partialRenderTick)
    {
        this.renderTileEntityBlockTesrAt((DivinTotemTileEntity)entity, x, y, z, partialRenderTick);
    }
    
    private void renderTileEntityBlockTesrAt(DivinTotemTileEntity tile, double x, double y, double z, float partialRenderTick)
    {
        GL11.glPushMatrix(); // ouvre une matrix

        GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D); // déplace le bloc sur les coordonnés et le centre

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F); // met droit le bloc (par défaut il est à l'envers)

        this.bindTexture( texture);
        model.render(); // rend le modèle
        model.renderPart(tile.rot, tile.cube);
        GL11.glPopMatrix(); // ferme la matrix
    }
}
 