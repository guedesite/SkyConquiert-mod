package fr.neocraft.main.ServeurManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.neocraft.main.main;
import fr.neocraft.main.Init.SavingData;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Shopper.ShopperRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;

public class TimerManager extends Thread {

	public static final SavingData saver = new SavingData();
	
	private int minid = 0;

	@Override
	public void run()
	{
		try {
			while(true)
			{
					this.sleep(300000);
						
							 saver.SaveStage(false);
					 	
						System.out.println("[TIMERMANAGER] SAVE SUCCESSFULL");
						DonjonManager.tryDonjon();
						ShopperRegister.ShopperAddStock();
						if(minid == 1)
						{
							RegisterStage.reloadMinage();
							minid = 1;
						} else {
							minid++;
						}

						

			}
		} catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("[TIMERMANAGER] CRASH, TRY IN 5 SECONDS");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				System.out.println("[TIMERMANAGER] CRASH BY THREAD SLEEP, MUST REBOOT :(");
				this.interrupt();
			}
			System.out.println("[TIMERMANAGER] TRY ...");
			run();
			System.out.println("[TIMERMANAGER] TRY SUCCESSFULL");
		}
	}

	
}
