package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;
import java.util.HashMap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Client.Render.AdvancedGui.GuiStageBank;
import fr.neocraft.main.Proxy.Client.Render.AdvancedGui.GuiStageHome;
import fr.neocraft.main.Proxy.Client.Render.AdvancedGui.GuiWarpList;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import net.minecraft.client.Minecraft;

public class ClientStageCmd extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	
	private HashMap<String, Integer> map;
	private int action;
	
	public ClientStageCmd(HashMap<String, Integer> m, int ac)
	{
		map = m;
		action = ac;
	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	public void A() {
		switch(action)
		{
			case 0:
				Minecraft.getMinecraft().displayGuiScreen(new GuiStageHome(map));
				break;
			case 1:
				Minecraft.getMinecraft().displayGuiScreen(new GuiStageBank(map));
				break;
			case 2:
				Minecraft.getMinecraft().displayGuiScreen(new GuiWarpList());
				break;
		}
	}

}
