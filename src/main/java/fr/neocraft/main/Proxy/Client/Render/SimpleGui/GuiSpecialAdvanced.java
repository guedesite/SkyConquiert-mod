package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GuiSpecialAdvanced extends Gui {
	private Float factor = 3f;
public GuiSpecialAdvanced(Minecraft mc, String str, int width, int height)
	{

		 GL11.glPushMatrix();
		 GL11.glScalef(factor, factor, 1);
		 drawCenteredString(mc.fontRenderer, str, width / 2, (height / 2) - 30, Integer.parseInt("FFAA00", 16));
		 GL11.glScalef(1f/factor, 1f/factor, 1);
		 GL11.glPopMatrix();
	}

}
