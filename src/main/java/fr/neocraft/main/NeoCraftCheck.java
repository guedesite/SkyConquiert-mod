package fr.neocraft.main;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;

public class NeoCraftCheck {
	public static boolean CheckIfIsValidServer() {
		File f = new File("NeoCraft.true");
		if(f.exists() && !f.isDirectory())
		{
			return true;
		}
		else
		{
			System.out.println("[NEOCRAFT] UTILISATION SANS PERMISSION DU MOD ! LE MOD NE CHARGERA PAS LES DONNES NECESSAIRE !");
			FMLCommonHandler.instance().exitJava(0, true);
			return false;
		}
	}
	
	public static String ph = "";
	public static boolean CheckIfIsValidClient() {
		if(!System.getProperty("ischild").equalsIgnoreCase("no"))
		{
			System.out.println("[" + neoreference.VERSION + ".NEOCRAFT] LA VERSION DU LAUNCHER NE CORRESPOND PAS, LE MOD NE CHARGERA PAS LES DONNES NECESSAIRE !");
			FMLCommonHandler.instance().exitJava(0, true);
			return false;
		}
		URL url;
		try {
			url = new URL("http://launcher.neocraft.fr/get/version/" + neoreference.VERSION + ".sky");
			InputStream is = url.openConnection().getInputStream();
			BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );

			String line = null;
			while( ( line = reader.readLine() ) != null )  {
				ph = line;
			}
			reader.close();
		} catch (MalformedURLException e) {
			System.out.println("[" + neoreference.VERSION + ".NEOCRAFT] LA VERSION DU MOD NE CORRESPOND PAS, LE MOD NE CHARGERA PAS LES DONNES NECESSAIRE !");
			FMLCommonHandler.instance().exitJava(0, true);
			return false;
		} catch (IOException e) {
			System.out.println("[" + neoreference.VERSION + ".NEOCRAFT] LA VERSION DU MOD NE CORRESPOND PAS, LE MOD NE CHARGERA PAS LES DONNES NECESSAIRE !");
			FMLCommonHandler.instance().exitJava(0, true);
			return false;
		}
		if(ph == "beug")
		{
			System.out.println("[" + neoreference.VERSION + ".NEOCRAFT] VERSION BEUGUE ET DESACTIVE POUR LE MOMENT !");
			FMLCommonHandler.instance().exitJava(0, true);
			return false;
		}
		else
		{
			return true;
		}
	}

}
