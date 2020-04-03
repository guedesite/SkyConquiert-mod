package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiPet;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientPetChoose extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	
	private boolean vdy;
	
	public ClientPetChoose(boolean d) { vdy = d; }
	
	@SideOnly(Side.CLIENT)
	@Override
	public void A(EntityPlayer player) {
		System.out.println("yeeeeeeeeeeeea "+vdy);
		if(vdy)
		{
			if(Minecraft.getMinecraft().currentScreen instanceof GuiPet) {
				Minecraft.getMinecraft().displayGuiScreen(null);
			}
		} else {
			Minecraft.getMinecraft().displayGuiScreen(new GuiPet());
		}
	}

}
