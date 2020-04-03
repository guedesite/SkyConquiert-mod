package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeTileNull;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class BoxUltimeTileEntitySpecialRenderer extends TileEntitySpecialRenderer{

    public static RenderBoxUltime model = new RenderBoxUltime();
    public static ResourceLocation texture = new ResourceLocation(neoreference.MOD_ID, "textures/models/blocks/BoxUltime.png");
    
    public void renderTileEntity()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialRenderTick) {
		this.renderTileEntityBoxUltimeAt((BoxUltimeTileNull)tile, x, y, z, partialRenderTick);
	}
	
    private void renderTileEntityBoxUltimeAt(BoxUltimeTileNull tile, double x, double y, double z, float partialRenderTick) // ma propre fonction
    {
    	GL11.glPushMatrix();
    	GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D);
    	GL11.glRotatef(180F, 0F, 0F, 1.0F);
    	this.bindTexture(texture); // affiche la texture
    	model.render();
    	GL11.glPopMatrix();      
	}

}
