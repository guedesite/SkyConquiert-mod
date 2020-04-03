package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.TileEntityNull;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class BoxVoteTileEntitySpecialRenderer extends TileEntitySpecialRenderer{

    public static RenderBoxVote model = new RenderBoxVote();
    public static ResourceLocation texture = new ResourceLocation(neoreference.MOD_ID, "textures/models/blocks/BoxVote.png");
    
    public void renderTileEntity()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialRenderTick) {
		this.renderTileEntityBoxVoteAt((TileEntityNull)tile, x, y, z, partialRenderTick);
	}
	
    private void renderTileEntityBoxVoteAt(TileEntityNull tile, double x, double y, double z, float partialRenderTick) // ma propre fonction
    {
    	GL11.glPushMatrix();
    	GL11.glTranslated(x + 0.875D, y + 0.95D, z + 0.125D);
    	GL11.glRotatef(180F, 0F, 0F, 1.0F);
    	this.bindTexture(texture); // affiche la texture
    	model.render();
    	GL11.glPopMatrix();      
	}

}
