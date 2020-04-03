package fr.neocraft.main.Proxy.Client.Render.SimpleGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class GuiText extends Gui {
	
public GuiText(Minecraft mc, String str, int width, int height)
	{
		
        
        drawCenteredString(mc.fontRenderer, str, width / 2, (height / 2) - 40, Integer.parseInt("FFAA00", 16));
        
	}

}
