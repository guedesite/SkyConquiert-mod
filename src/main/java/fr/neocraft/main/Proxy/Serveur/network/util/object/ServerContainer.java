package fr.neocraft.main.Proxy.Serveur.network.util.object;

import java.io.Serializable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxUltimeContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.BoxVoteContainer;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvContainerPerso;
import fr.neocraft.main.Proxy.Serveur.TileEntity.hdvContainerPublic;
import fr.neocraft.main.Proxy.Serveur.network.util.T;
import fr.neocraft.main.Proxy.Serveur.player.PlayerStats;
import net.minecraft.entity.player.EntityPlayer;

public class ServerContainer extends T implements Serializable{

	private  static  final  long serialVersionUID =  5464867684657468768L;
	String e;

	public ServerContainer(String d) {
		e = d;
	}
	@SideOnly(Side.SERVER)
	@Override
	public void A(EntityPlayer player) {
		if(player != null)
		{
			if(e.equals("vote"))
			{
				((BoxVoteContainer)player.openContainer).scroll();
			}else if(e.equals("ultime"))
			{
				((BoxUltimeContainer)player.openContainer).scroll();
			}else if(e.equals("HDVMy"))
			{
				player.openGui(main.instance, 9, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			} else if(e.equals("HDVMarche"))
			{
				player.openGui(main.instance, 10, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			} else if(e.equals("HDVMShop"))
			{
				player.openGui(main.instance, 11, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			} else if(e.equals("DRAG0"))
			{
				player.openGui(main.instance, 1, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			} else if(e.equals("DRAG1"))
			{
				player.openGui(main.instance, 0, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			} else if(e.equals("PERM0"))
			{
				player.openGui(main.instance, 3, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			}else if(e.equals("PERM1"))
			{
				player.openGui(main.instance, 2, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			}else if(e.equals("PERM2"))
			{
				player.openGui(main.instance, 12, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			}else if(e.equals("MAP"))
			{
		        PlayerStats.get(player).QuestEvent("touche_5", player, 1);
				player.openGui(main.instance, 7, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			}else if(e.equals("DRAGUI"))
			{
		        PlayerStats.get(player).QuestEvent("touche_4", player, 1);
				player.openGui(main.instance, 0, player.worldObj,(int) player.posX, (int)player.posY, (int)player.posZ);
			}
			else if(e.contains("addhdv-"))
			{
				int d = Integer.parseInt(e.substring(7));
				((hdvContainerPerso)player.openContainer).PlayerAddHdv(d);
			}else if(e.equals("HDVNEXT"))
			{
				((hdvContainerPublic)player.openContainer).NeoTile.NextPage();
			}else if(e.equals("HDVPREVIOUS"))
			{
				((hdvContainerPublic)player.openContainer).NeoTile.LastPage();;
			}
		}
	}
	
}
