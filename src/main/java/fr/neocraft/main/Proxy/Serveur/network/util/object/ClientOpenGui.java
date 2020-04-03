package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiBvn;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiPet;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiShopNavigator;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiTuto;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiTutoDos;
import fr.neocraft.main.Proxy.Client.Render.TileEntityGui.FuturistInputGui;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import fr.neocraft.main.ServeurManager.DailyManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;


public class ClientOpenGui extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	
	int id;
	boolean r = false;


	public ClientOpenGui(int i, boolean d) //true: totu / false: tuto
	{
		id = i;
		r = d;
	}
	
	
	
	public ClientOpenGui(int i) {
		id = i;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void A() {
		if(id == -3)
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiPet());
		} else
		if(id == -2)
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiShopNavigator());
		}
		else if(id == -1)
		{
			Minecraft.getMinecraft().displayGuiScreen(new GuiBvn(DailyManager.getIdDayByName()));
		}else {
			if(r) {
				Minecraft.getMinecraft().displayGuiScreen(new GuiTutoDos(I18n.format("neo.info." + id, new Object[0]), I18n.format("neo.info.titre." + id, new Object[0])));
				
			}else {
				Minecraft.getMinecraft().displayGuiScreen(new GuiTuto(I18n.format("neo.info." + id, new Object[0]), I18n.format("neo.info.titre." + id, new Object[0])));
			}
		}
	}

}
