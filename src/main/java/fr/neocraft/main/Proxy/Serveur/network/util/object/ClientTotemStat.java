package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiBvn;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiPet;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiShopNavigator;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiTuto;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiTutoDos;
import fr.neocraft.main.Proxy.Serveur.TileEntity.DivinTotemTileEntity;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import fr.neocraft.main.ServeurManager.DailyManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntity;

public class ClientTotemStat extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	
	int action;
	
	int X , Y,Z;


	public ClientTotemStat(int ac,int x, int y, int z) 
	{
		action = ac;
		X=x;
		Y=y;
		Z=z;
	}
	
	

	@SideOnly(Side.CLIENT)
	@Override
	public void A() {
		TileEntity tile = Minecraft.getMinecraft().theWorld.getTileEntity(X, Y, Z);
		if(tile != null)
		{
			if(action == 0)
			{
				((DivinTotemTileEntity) tile).startClient();
			} else if(action == 1) {
				((DivinTotemTileEntity) tile).stopClient();
			}
		} else {
			System.err.println("ERROR TILE NULL CLIENTOTEMSTAT "+X+" "+Y+" "+Z);
		}
	}

}
