package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkClient;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;


public class ServerPetChoose extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	private int id = -1;
	public ServerPetChoose(int i)
	{
		id = i;
	}
	
	@SideOnly(Side.SERVER)
	@Override
	public void A(EntityPlayer player) {
		PlayerStats stat =PlayerStats.get(player);
		stat.PetId = id;
		if(main.getbdd().update("UPDATE "+main.getbdd().getStringPlayer()+" SET `PetId`="+id+" WHERE pseudo='"+player.getCommandSenderName()+"'")) {
			stat.updatePet(player, true);
		}
		
	}

}
