package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiAviator;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class ClientGuiAviator extends T implements Serializable{
	
	int b;
	private  static  final  long serialVersionUID =  5464867684657468768L;
	
	public ClientGuiAviator(int t)
	{
		b = t;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void A()
	{
		Minecraft.getMinecraft().displayGuiScreen(new GuiAviator(I18n.format("neo.aviator.txt", new Object[0]), I18n.format("neo.aviator.title", new Object[0]), b == 1));
	}
}
