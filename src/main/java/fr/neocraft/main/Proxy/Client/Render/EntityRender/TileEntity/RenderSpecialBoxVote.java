package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.neocraft.main.Init.BlockMod;
import fr.neocraft.main.Proxy.Client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class RenderSpecialBoxVote implements ISimpleBlockRenderingHandler
{
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {
        if(block == BlockMod.BoxVote)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0F, 0F, 1F);
            GL11.glTranslatef(-0.4F, -0.4F, -0.4F);
             Minecraft.getMinecraft().getTextureManager().bindTexture(BoxVoteTileEntitySpecialRenderer.texture);
             BoxVoteTileEntitySpecialRenderer.model.render();
            GL11.glPopMatrix();

        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return ClientProxy.renderBoxVote;
    }
  
}