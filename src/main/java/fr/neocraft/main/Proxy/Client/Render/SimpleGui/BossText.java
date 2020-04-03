package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;

public class BossText  extends Gui {

	
	public BossText(Minecraft mc, int width, int height)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);
		mc.fontRenderer.drawStringWithShadow(I18n.format("boss.final"), Math.round(width/2)- Math.round((mc.fontRenderer.getStringWidth(I18n.format("boss.final"))/2)), 5, 0);
		}

}