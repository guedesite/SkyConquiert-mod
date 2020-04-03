package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import org.lwjgl.opengl.GL11;

import fr.neocraft.main.neoreference;
import fr.neocraft.main.Proxy.Serveur.TileEntity.TileEntityFarmLand;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class TileEntityFarmLandSpecial extends TileEntitySpecialRenderer
{
    public static RenderFarmLandSpecial model = new RenderFarmLandSpecial();
    public static ResourceLocation texture = new ResourceLocation(neoreference.MOD_ID + ":textures/models/blocks/BlockFarmLand.png");
    public static ResourceLocation texture2 = new ResourceLocation(neoreference.MOD_ID + ":textures/models/blocks/BlockFarmLand2.png");
    public void renderTileEntity()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }
    
    @Override
    public void renderTileEntityAt(TileEntity entity, double x, double y, double z, float partialRenderTick)
    {
        this.renderTileEntityBlockTesrAt((TileEntityFarmLand)entity, x, y, z, partialRenderTick);
    }
    
    private void renderTileEntityBlockTesrAt(TileEntityFarmLand tile, double x, double y, double z, float partialRenderTick)
    {
        GL11.glPushMatrix(); // ouvre une matrix

        GL11.glTranslated(x + 0.5D, y + 1.5D, z + 0.5D); // déplace le bloc sur les coordonnés et le centre

        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F); // met droit le bloc (par défaut il est à l'envers)


        this.bindTexture(tile.getmeta() > 0 ? texture : texture2);
        model.renderAll(); // rend le modèle

        GL11.glPopMatrix(); // ferme la matrix
    }
    private boolean func_149821_m(World p_149821_1_, int p_149821_2_, int p_149821_3_, int p_149821_4_)
    {
        for (int l = p_149821_2_ - 4; l <= p_149821_2_ + 4; ++l)
        {
            for (int i1 = p_149821_3_; i1 <= p_149821_3_ + 1; ++i1)
            {
                for (int j1 = p_149821_4_ - 4; j1 <= p_149821_4_ + 4; ++j1)
                {
                    if (p_149821_1_.getBlock(l, i1, j1).getMaterial() == Material.water)
                    {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}