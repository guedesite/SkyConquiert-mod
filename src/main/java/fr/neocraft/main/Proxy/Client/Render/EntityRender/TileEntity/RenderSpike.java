package fr.neocraft.main.Proxy.Client.Render.EntityRender.TileEntity;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.neocraft.main.Blocks.Special.BlockSpike;
import fr.neocraft.main.Proxy.Client.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderSpike implements ISimpleBlockRenderingHandler
{

 
  @Override
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
  {

  }

  @Override
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
  {
	  Tessellator tessellator = Tessellator.instance;
      tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
      int l = block.colorMultiplier(renderer.blockAccess, x, y, z);
      float f = (float)(l >> 16 & 255) / 255.0F;
      float f1 = (float)(l >> 8 & 255) / 255.0F;
      float f2 = (float)(l & 255) / 255.0F;

      if (EntityRenderer.anaglyphEnable)
      {
          float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
          float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
          float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
          f = f3;
          f1 = f4;
          f2 = f5;
      }

      tessellator.setColorOpaque_F(f, f1, f2);
      double d1 = (double)x;
      double d2 = (double)y;
      double d0 = (double)z;
      long i1;

         // i1 = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
         // i1 = i1 * i1 * 42317861L + i1 * 11L;
         // d1 += ((double)((float)(i1 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
         // d2 += ((double)((float)(i1 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
         // d0 += ((double)((float)(i1 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;


      IIcon iicon = block.getIcon(0, 0);
      int side = world.getBlockMetadata(x, y, z);
      this.drawCrossedSquares(iicon, d1, d2, d0, 1.0F, renderer, side, block);
      return true;
  }
  
  public void drawCrossedSquares(IIcon p_147765_1_, double d, double d1, double d2, float p_147765_8_, RenderBlocks renderer, int side, Block block)
  {
      Tessellator tessellator = Tessellator.instance;

      if (renderer.hasOverrideBlockTexture())
      {
          p_147765_1_ = renderer.overrideBlockTexture;
      }

      double d4 = p_147765_1_.getMinU();
      double d5 = p_147765_1_.getMaxV();
      double d6 = p_147765_1_.getMinV();
      double d3 = p_147765_1_.getMaxU();
      if (!(side == 5) || !(side == 0)) {
        double d7 = 0.0D;
        d7 = d3;
        d3 = d4;
        d4 = d7;
        d7 = d5;
        d5 = d6;
        d6 = d7;
      }
      double d8 = d + 0.5D - 0.44999998807907104D;
      double d9 = d + 0.5D + 0.44999998807907104D;
      double d10 = d2 + 0.5D - 0.44999998807907104D;
      double d11 = d2 + 0.5D + 0.44999998807907104D;
      double d12 = d1 + 0.5D - 0.44999998807907104D;
      double d13 = d1 + 0.5D + 0.44999998807907104D;
      if (side == 0) {
        tessellator.addVertexWithUV(d8, d1 + 0.0D, d10, d3, d5);
        tessellator.addVertexWithUV(d8, d1 + 1.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d9, d1 + 1.0D, d11, d4, d6);
        tessellator.addVertexWithUV(d9, d1 + 0.0D, d11, d4, d5);
        tessellator.addVertexWithUV(d9, d1 + 0.0D, d11, d3, d5);
        tessellator.addVertexWithUV(d9, d1 + 1.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d8, d1 + 1.0D, d10, d4, d6);
        tessellator.addVertexWithUV(d8, d1 + 0.0D, d10, d4, d5);
        tessellator.addVertexWithUV(d8, d1 + 0.0D, d11, d3, d5);
        tessellator.addVertexWithUV(d8, d1 + 1.0D, d11, d3, d6);
        tessellator.addVertexWithUV(d9, d1 + 1.0D, d10, d4, d6);
        tessellator.addVertexWithUV(d9, d1 + 0.0D, d10, d4, d5);
        tessellator.addVertexWithUV(d9, d1 + 0.0D, d10, d3, d5);
        tessellator.addVertexWithUV(d9, d1 + 1.0D, d10, d3, d6);
        tessellator.addVertexWithUV(d8, d1 + 1.0D, d11, d4, d6);
        tessellator.addVertexWithUV(d8, d1 + 0.0D, d11, d4, d5);
      } 
      else  if (side == 5) {
          tessellator.addVertexWithUV(d8, d1 + 1.0D, d10, d3, d5);
          tessellator.addVertexWithUV(d8, d1 + 0.0D, d10, d3, d6);
          tessellator.addVertexWithUV(d9, d1 + 0.0D, d11, d4, d6);
          tessellator.addVertexWithUV(d9, d1 + 1.0D, d11, d4, d5);
          tessellator.addVertexWithUV(d9, d1 + 1.0D, d11, d3, d5);
          tessellator.addVertexWithUV(d9, d1 + 0.0D, d11, d3, d6);
          tessellator.addVertexWithUV(d8, d1 + 0.0D, d10, d4, d6);
          tessellator.addVertexWithUV(d8, d1 + 1.0D, d10, d4, d5);
          tessellator.addVertexWithUV(d8, d1 + 1.0D, d11, d3, d5);
          tessellator.addVertexWithUV(d8, d1 + 0.0D, d11, d3, d6);
          tessellator.addVertexWithUV(d9, d1 + 0.0D, d10, d4, d6);
          tessellator.addVertexWithUV(d9, d1 + 1.0D, d10, d4, d5);
          tessellator.addVertexWithUV(d9, d1 + 1.0D, d10, d3, d5);
          tessellator.addVertexWithUV(d9, d1 + 0.0D, d10, d3, d6);
          tessellator.addVertexWithUV(d8, d1 + 0.0D, d11, d4, d6);
          tessellator.addVertexWithUV(d8, d1 + 1.0D, d11, d4, d5);
        }
      else if (side == 1) {
        tessellator.addVertexWithUV(d + 1.0D, d12, d10, d3, d5);
        tessellator.addVertexWithUV(d + 0.0D, d12, d10, d3, d6);
        tessellator.addVertexWithUV(d + 0.0D, d13, d11, d4, d6);
        tessellator.addVertexWithUV(d + 1.0D, d13, d11, d4, d5);
        tessellator.addVertexWithUV(d + 1.0D, d13, d11, d3, d5);
        tessellator.addVertexWithUV(d + 0.0D, d13, d11, d3, d6);
        tessellator.addVertexWithUV(d + 0.0D, d12, d10, d4, d6);
        tessellator.addVertexWithUV(d + 1.0D, d12, d10, d4, d5);
        tessellator.addVertexWithUV(d + 1.0D, d12, d11, d3, d5);
        tessellator.addVertexWithUV(d + 0.0D, d12, d11, d3, d6);
        tessellator.addVertexWithUV(d + 0.0D, d13, d10, d4, d6);
        tessellator.addVertexWithUV(d + 1.0D, d13, d10, d4, d5);
        tessellator.addVertexWithUV(d + 1.0D, d13, d10, d3, d5);
        tessellator.addVertexWithUV(d + 0.0D, d13, d10, d3, d6);
        tessellator.addVertexWithUV(d + 0.0D, d12, d11, d4, d6);
        tessellator.addVertexWithUV(d + 1.0D, d12, d11, d4, d5);
      }
      else if (side == 2) {
          tessellator.addVertexWithUV(d + 0.0D, d12, d10, d3, d5);
          tessellator.addVertexWithUV(d + 1.0D, d12, d10, d3, d6);
          tessellator.addVertexWithUV(d + 1.0D, d13, d11, d4, d6);
          tessellator.addVertexWithUV(d + 0.0D, d13, d11, d4, d5);
          tessellator.addVertexWithUV(d + 0.0D, d13, d11, d3, d5);
          tessellator.addVertexWithUV(d + 1.0D, d13, d11, d3, d6);
          tessellator.addVertexWithUV(d + 1.0D, d12, d10, d4, d6);
          tessellator.addVertexWithUV(d + 0.0D, d12, d10, d4, d5);
          tessellator.addVertexWithUV(d + 0.0D, d12, d11, d3, d5);
          tessellator.addVertexWithUV(d + 1.0D, d12, d11, d3, d6);
          tessellator.addVertexWithUV(d + 1.0D, d13, d10, d4, d6);
          tessellator.addVertexWithUV(d + 0.0D, d13, d10, d4, d5);
          tessellator.addVertexWithUV(d + 0.0D, d13, d10, d3, d5);
          tessellator.addVertexWithUV(d + 1.0D, d13, d10, d3, d6);
          tessellator.addVertexWithUV(d + 1.0D, d12, d11, d4, d6);
          tessellator.addVertexWithUV(d + 0.0D, d12, d11, d4, d5);
        }
      else if (side == 3) {
        tessellator.addVertexWithUV(d8, d12, d2 + 1.0D, d3, d5);
        tessellator.addVertexWithUV(d8, d12, d2 + 0.0D, d3, d6);
        tessellator.addVertexWithUV(d9, d13, d2 + 0.0D, d4, d6);
        tessellator.addVertexWithUV(d9, d13, d2 + 1.0D, d4, d5);
        tessellator.addVertexWithUV(d9, d13, d2 + 1.0D, d3, d5);
        tessellator.addVertexWithUV(d9, d13, d2 + 0.0D, d3, d6);
        tessellator.addVertexWithUV(d8, d12, d2 + 0.0D, d4, d6);
        tessellator.addVertexWithUV(d8, d12, d2 + 1.0D, d4, d5);
        tessellator.addVertexWithUV(d8, d13, d2 + 1.0D, d3, d5);
        tessellator.addVertexWithUV(d8, d13, d2 + 0.0D, d3, d6);
        tessellator.addVertexWithUV(d9, d12, d2 + 0.0D, d4, d6);
        tessellator.addVertexWithUV(d9, d12, d2 + 1.0D, d4, d5);
        tessellator.addVertexWithUV(d9, d12, d2 + 1.0D, d3, d5);
        tessellator.addVertexWithUV(d9, d12, d2 + 0.0D, d3, d6);
        tessellator.addVertexWithUV(d8, d13, d2 + 0.0D, d4, d6);
        tessellator.addVertexWithUV(d8, d13, d2 + 1.0D, d4, d5);
      }
      else if (side == 4) {
          tessellator.addVertexWithUV(d8, d12, d2 + 0.0D, d3, d5);
          tessellator.addVertexWithUV(d8, d12, d2 + 1.0D, d3, d6);
          tessellator.addVertexWithUV(d9, d13, d2 + 1.0D, d4, d6);
          tessellator.addVertexWithUV(d9, d13, d2 + 0.0D, d4, d5);
          tessellator.addVertexWithUV(d9, d13, d2 + 0.0D, d3, d5);
          tessellator.addVertexWithUV(d9, d13, d2 + 1.0D, d3, d6);
          tessellator.addVertexWithUV(d8, d12, d2 + 1.0D, d4, d6);
          tessellator.addVertexWithUV(d8, d12, d2 + 0.0D, d4, d5);
          tessellator.addVertexWithUV(d8, d13, d2 + 0.0D, d3, d5);
          tessellator.addVertexWithUV(d8, d13, d2 + 1.0D, d3, d6);
          tessellator.addVertexWithUV(d9, d12, d2 + 1.0D, d4, d6);
          tessellator.addVertexWithUV(d9, d12, d2 + 0.0D, d4, d5);
          tessellator.addVertexWithUV(d9, d12, d2 + 0.0D, d3, d5);
          tessellator.addVertexWithUV(d9, d12, d2 + 1.0D, d3, d6);
          tessellator.addVertexWithUV(d8, d13, d2 + 1.0D, d4, d6);
          tessellator.addVertexWithUV(d8, d13, d2 + 0.0D, d4, d5);
        }
        
      
  }

  
      public static void renderSpike(BlockSpike block, int i, double d, double d1, double d2, IIcon overrideBlockTexture) {
    	    Tessellator tessellator = Tessellator.instance;
    	    IIcon icon = block.getBlockTextureFromSide(0);
    	    
    	    if (overrideBlockTexture != null) {
    	      icon = overrideBlockTexture;
    	    }
    	    
    	    double d3 = icon.getMaxU();
    	    double d4 = icon.getMaxV();
    	    double d5 = icon.getMinU();
    	    double d6 = icon.getMinV();
    	    if ((i == 6) || (i == 12) || (i == 4) || (i == 10) || (i == 2) || (i == 8)) {
    	      double d7 = 0.0D;
    	      d7 = d3;
    	      d3 = d4;
    	      d4 = d7;
    	      d7 = d5;
    	      d5 = d6;
    	      d6 = d7;
    	    }
    	    double d8 = d + 0.5D - 0.44999998807907104D;
    	    double d9 = d + 0.5D + 0.44999998807907104D;
    	    double d10 = d2 + 0.5D - 0.44999998807907104D;
    	    double d11 = d2 + 0.5D + 0.44999998807907104D;
    	    double d12 = d1 + 0.5D - 0.44999998807907104D;
    	    double d13 = d1 + 0.5D + 0.44999998807907104D;
    	    if ((i == 1) || (i == 6) || (i == 7) || (i == 12)) {
    	      tessellator.addVertexWithUV(d8, d1 + 1.0D, d10, d3, d5);
    	      tessellator.addVertexWithUV(d8, d1 + 0.0D, d10, d3, d6);
    	      tessellator.addVertexWithUV(d9, d1 + 0.0D, d11, d4, d6);
    	      tessellator.addVertexWithUV(d9, d1 + 1.0D, d11, d4, d5);
    	      tessellator.addVertexWithUV(d9, d1 + 1.0D, d11, d3, d5);
    	      tessellator.addVertexWithUV(d9, d1 + 0.0D, d11, d3, d6);
    	      tessellator.addVertexWithUV(d8, d1 + 0.0D, d10, d4, d6);
    	      tessellator.addVertexWithUV(d8, d1 + 1.0D, d10, d4, d5);
    	      tessellator.addVertexWithUV(d8, d1 + 1.0D, d11, d3, d5);
    	      tessellator.addVertexWithUV(d8, d1 + 0.0D, d11, d3, d6);
    	      tessellator.addVertexWithUV(d9, d1 + 0.0D, d10, d4, d6);
    	      tessellator.addVertexWithUV(d9, d1 + 1.0D, d10, d4, d5);
    	      tessellator.addVertexWithUV(d9, d1 + 1.0D, d10, d3, d5);
    	      tessellator.addVertexWithUV(d9, d1 + 0.0D, d10, d3, d6);
    	      tessellator.addVertexWithUV(d8, d1 + 0.0D, d11, d4, d6);
    	      tessellator.addVertexWithUV(d8, d1 + 1.0D, d11, d4, d5);
    	    } else if ((i == 4) || (i == 10) || (i == 5) || (i == 11)) {
    	      tessellator.addVertexWithUV(d + 1.0D, d12, d10, d3, d5);
    	      tessellator.addVertexWithUV(d + 0.0D, d12, d10, d3, d6);
    	      tessellator.addVertexWithUV(d + 0.0D, d13, d11, d4, d6);
    	      tessellator.addVertexWithUV(d + 1.0D, d13, d11, d4, d5);
    	      tessellator.addVertexWithUV(d + 1.0D, d13, d11, d3, d5);
    	      tessellator.addVertexWithUV(d + 0.0D, d13, d11, d3, d6);
    	      tessellator.addVertexWithUV(d + 0.0D, d12, d10, d4, d6);
    	      tessellator.addVertexWithUV(d + 1.0D, d12, d10, d4, d5);
    	      tessellator.addVertexWithUV(d + 1.0D, d12, d11, d3, d5);
    	      tessellator.addVertexWithUV(d + 0.0D, d12, d11, d3, d6);
    	      tessellator.addVertexWithUV(d + 0.0D, d13, d10, d4, d6);
    	      tessellator.addVertexWithUV(d + 1.0D, d13, d10, d4, d5);
    	      tessellator.addVertexWithUV(d + 1.0D, d13, d10, d3, d5);
    	      tessellator.addVertexWithUV(d + 0.0D, d13, d10, d3, d6);
    	      tessellator.addVertexWithUV(d + 0.0D, d12, d11, d4, d6);
    	      tessellator.addVertexWithUV(d + 1.0D, d12, d11, d4, d5);
    	    } else if ((i == 2) || (i == 8) || (i == 3) || (i == 9)) {
    	      tessellator.addVertexWithUV(d8, d12, d2 + 1.0D, d3, d5);
    	      tessellator.addVertexWithUV(d8, d12, d2 + 0.0D, d3, d6);
    	      tessellator.addVertexWithUV(d9, d13, d2 + 0.0D, d4, d6);
    	      tessellator.addVertexWithUV(d9, d13, d2 + 1.0D, d4, d5);
    	      tessellator.addVertexWithUV(d9, d13, d2 + 1.0D, d3, d5);
    	      tessellator.addVertexWithUV(d9, d13, d2 + 0.0D, d3, d6);
    	      tessellator.addVertexWithUV(d8, d12, d2 + 0.0D, d4, d6);
    	      tessellator.addVertexWithUV(d8, d12, d2 + 1.0D, d4, d5);
    	      tessellator.addVertexWithUV(d8, d13, d2 + 1.0D, d3, d5);
    	      tessellator.addVertexWithUV(d8, d13, d2 + 0.0D, d3, d6);
    	      tessellator.addVertexWithUV(d9, d12, d2 + 0.0D, d4, d6);
    	      tessellator.addVertexWithUV(d9, d12, d2 + 1.0D, d4, d5);
    	      tessellator.addVertexWithUV(d9, d12, d2 + 1.0D, d3, d5);
    	      tessellator.addVertexWithUV(d9, d12, d2 + 0.0D, d3, d6);
    	      tessellator.addVertexWithUV(d8, d13, d2 + 0.0D, d4, d6);
    	      tessellator.addVertexWithUV(d8, d13, d2 + 1.0D, d4, d5);
    	    }
    	  }




  @Override
  public int getRenderId()
  {
      return ClientProxy.spikeRenderID;
  }

  @Override
	public boolean shouldRender3DInInventory(int modelId) {
	  return false;
	}

}
