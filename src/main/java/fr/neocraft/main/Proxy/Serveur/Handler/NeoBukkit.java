package fr.neocraft.main.Proxy.Serveur.Handler;

public class NeoBukkit {

	public static void executeCmd(String cmd) {
		try {
			org.bukkit.Bukkit.dispatchCommand(org.bukkit.Bukkit.getServer().getConsoleSender(), cmd);
		}
			catch(Exception e) {
				e.printStackTrace();
			}
	}
	
}
