package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GuiTextAnnonce extends Gui {

	public GuiTextAnnonce(Minecraft mc, String str)
	{
		mc.fontRenderer.drawStringWithShadow(str, 10, 10, Integer.parseInt("FFAA00", 16));
		}

}