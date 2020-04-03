package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.neocraft.main.neoreference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.Direction;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.chunk.Chunk;

public class GuiDebug extends Gui {

	public GuiDebug(Minecraft mc) {
		ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int k = scaledresolution.getScaledWidth();
        int l = scaledresolution.getScaledHeight();
		GL11.glPushMatrix();
		FontRenderer fontrenderer = mc.fontRenderer;
        fontrenderer.drawStringWithShadow("NeoCraft Sky Conquiert (" + getDebugFps(mc.debug) + ") Version du mod: "+neoreference.VERSION, 2, 2, 16777215);
        fontrenderer.drawStringWithShadow(mc.debugInfoRenders(), 2, 12, 16777215);
        fontrenderer.drawStringWithShadow(mc.getEntityDebug(), 2, 22, 16777215);
        fontrenderer.drawStringWithShadow(mc.debugInfoEntities().replace("E:", EnumChatFormatting.LIGHT_PURPLE+"E:").replace(", B:", EnumChatFormatting.RESET+", B:"), 2, 32, 16777215);
        fontrenderer.drawStringWithShadow(mc.getWorldProviderName(), 2, 42, 16777215);
        long i5 = Runtime.getRuntime().maxMemory();
        long j5 = Runtime.getRuntime().totalMemory();
        long k5 = Runtime.getRuntime().freeMemory();
        long l5 = j5 - k5;
        int r = Math.round(l5 * 100L / i5);
        String s;
        if(r <= 75)
        {
        	 s = EnumChatFormatting.GREEN+"Used memory: " + l5 * 100L / i5 + "% (" + l5 / 1024L / 1024L + "MB) of " + i5 / 1024L / 1024L + "MB";
             
        }else  if(r <= 90)
        {
       	 s = EnumChatFormatting.RED+"Used memory: " + l5 * 100L / i5 + "% (" + l5 / 1024L / 1024L + "MB) of " + i5 / 1024L / 1024L + "MB";
            
       }
        else
        {
        	
        	 s = EnumChatFormatting.DARK_RED+"Used memory: " + l5 * 100L / i5 + "% (" + l5 / 1024L / 1024L + "MB) of " + i5 / 1024L / 1024L + "MB";
             
        }
        int i3 = 14737632;
        this.drawString(fontrenderer, s, k - fontrenderer.getStringWidth(s) - 2, 2, 14737632);
        s = "Allocated memory: " + j5 * 100L / i5 + "% (" + j5 / 1024L / 1024L + "MB)";
        this.drawString(fontrenderer, s, k - fontrenderer.getStringWidth(s) - 2, 12, 14737632);
        int offset = 22;
      /*  for (String brd : FMLCommonHandler.instance().getBrandings(false))
        {
            this.drawString(fontrenderer, brd, k - fontrenderer.getStringWidth(brd) - 2, offset+=10, 14737632);
        } */
        int j3 = MathHelper.floor_double(mc.thePlayer.posX);
        int k3 = MathHelper.floor_double(mc.thePlayer.posY);
        int l3 = MathHelper.floor_double(mc.thePlayer.posZ);
        this.drawString(fontrenderer, String.format(EnumChatFormatting.LIGHT_PURPLE+""+EnumChatFormatting.BOLD+"x: %.5f"+ EnumChatFormatting.RESET+" (%d) // c: %d (%d)", new Object[] {Double.valueOf(mc.thePlayer.posX), Integer.valueOf(j3), Integer.valueOf(j3 >> 4), Integer.valueOf(j3 & 15)}), 2, 64, 14737632);
        this.drawString(fontrenderer, String.format(EnumChatFormatting.LIGHT_PURPLE+""+EnumChatFormatting.BOLD+"y: %.3f"+ EnumChatFormatting.RESET+" (feet pos, %.3f eyes pos)", new Object[] {Double.valueOf(mc.thePlayer.boundingBox.minY), Double.valueOf(mc.thePlayer.posY)}), 2, 72, 14737632);
        this.drawString(fontrenderer, String.format(EnumChatFormatting.LIGHT_PURPLE+""+EnumChatFormatting.BOLD+"z: %.5f"+ EnumChatFormatting.RESET+" (%d) // c: %d (%d)", new Object[] {Double.valueOf(mc.thePlayer.posZ), Integer.valueOf(l3), Integer.valueOf(l3 >> 4), Integer.valueOf(l3 & 15)}), 2, 80, 14737632);
        int i4 = MathHelper.floor_double((double)(mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        this.drawString(fontrenderer, "f: " + i4 + " (" + Direction.directions[i4] + ") / " + MathHelper.wrapAngleTo180_float(mc.thePlayer.rotationYaw), 2, 88, 14737632);

        if (mc.theWorld != null && mc.theWorld.blockExists(j3, k3, l3))
        {
            Chunk chunk = mc.theWorld.getChunkFromBlockCoords(j3, l3);
            this.drawString(fontrenderer, "lc: " + (chunk.getTopFilledSegment() + 15) + " b: " + chunk.getBiomeGenForWorldCoords(j3 & 15, l3 & 15, mc.theWorld.getWorldChunkManager()).biomeName + " bl: " + chunk.getSavedLightValue(EnumSkyBlock.Block, j3 & 15, k3, l3 & 15) + " sl: " + chunk.getSavedLightValue(EnumSkyBlock.Sky, j3 & 15, k3, l3 & 15) + " rl: " + chunk.getBlockLightValue(j3 & 15, k3, l3 & 15, 0), 2, 96, 14737632);
        }

        this.drawString(fontrenderer, String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[] {Float.valueOf(mc.thePlayer.capabilities.getWalkSpeed()), Float.valueOf(mc.thePlayer.capabilities.getFlySpeed()), Boolean.valueOf(mc.thePlayer.onGround), Integer.valueOf(mc.theWorld.getHeightValue(j3, l3))}), 2, 104, 14737632);

        if (mc.entityRenderer != null && mc.entityRenderer.isShaderActive())
        {
            this.drawString(fontrenderer, String.format("shader: %s", new Object[] {mc.entityRenderer.getShaderGroup().getShaderGroupName()}), 2, 112, 14737632);
        }

        GL11.glPopMatrix();
	}
	
	
	public String getDebugFps(String s)
	{
		int t = s.length();
		String pre = "";
		char cara, cara2;
		int d = 0;
		for(int i = 0; i < t; i++) 
		{
			
			cara = s.charAt(i);
			if(cara == ' ')
			{
				break;
			}
			else
			{
				pre += cara;
			}
		}
		int nb = Integer.parseInt(pre);
		if(nb < 15)
		{
			return EnumChatFormatting.DARK_RED+""+nb+ "fps"+ EnumChatFormatting.RESET+", "+WorldRenderer.chunksUpdated;
		}
		else if(nb < 30)
		{
			return EnumChatFormatting.RED+""+nb+ "fps"+ EnumChatFormatting.RESET+", "+WorldRenderer.chunksUpdated;
		} else 
		{
			return EnumChatFormatting.GREEN+""+nb+ "fps"+ EnumChatFormatting.RESET+", "+WorldRenderer.chunksUpdated;
		}
	}

}
