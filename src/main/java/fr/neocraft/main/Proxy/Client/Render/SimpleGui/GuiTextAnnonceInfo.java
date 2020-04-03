package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GuiTextAnnonceInfo extends Gui {

	public GuiTextAnnonceInfo(Minecraft mc, String str)
	{

		mc.fontRenderer.drawStringWithShadow(str, 10, 30, Integer.parseInt("FFAA00", 16));
	}

}