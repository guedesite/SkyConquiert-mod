package fr.neocraft.main.Proxy.Serveur.player;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;

public class AnnimationManager {
	
	
	
	private static Thread t;
	public static void DownUp(final EntityPlayer player, final String effect)
	{
		t = new Thread() {
			@Override
			public void run() 
			{
			 	double d0,d1,d2,d3 = 0.0D,d4 = 0.0D,d5 = 0.0D, fl = 0.00;
			 	Random r = new Random();
			 	double x = player.posX, y = player.posY, z = player.posZ;
			 	int p = 20;
		        for(int i = 0; i <= 20; i++)
		        {
		        	for(int o = 0; o <= p; o++)
			        {
			        	 d0 = (double)((float)x + r.nextFloat());
			             d1 = (double)((float)y + fl + r.nextFloat());
			             d2 = (double)((float)z + r.nextFloat());
			        	player.worldObj.spawnParticle(effect, d0, d1, d2, d3, d4, d5);
			        }
		        	p--;
		        	fl = fl + 0.1;
		        	try {
						t.sleep(100);
					} catch (InterruptedException e) {
						try {
							t.sleep(200);
						} catch (InterruptedException o) {
							o.printStackTrace();
						}
					}
		        }
			}
		};
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
	}
	public static void UpDown(final EntityPlayer player, final String effect)
	{
		t = new Thread() {
			@Override
			public void run() 
			{
			 	double d0,d1,d2,d3 = 0.0D,d4 = 0.0D,d5 = 0.0D, fl = 2.00;
			 	Random r = new Random();
			 	double x = player.posX, y = player.posY, z = player.posZ;
			 	int p = 20;
		        for(int i = 0; i <= 20; i++)
		        {
		        	for(int o = 0; o <= p; o++)
			        {
			        	 d0 = (double)((float)x + r.nextFloat());
			             d1 = (double)((float)y + fl + r.nextFloat());
			             d2 = (double)((float)z + r.nextFloat());
			        	player.worldObj.spawnParticle(effect, d0, d1, d2, d3, d4, d5);
			        }
		        	p--;
		        	fl = fl - 0.1;
		        	try {
						t.sleep(100);
					} catch (InterruptedException e) {
						try {
							t.sleep(200);
						} catch (InterruptedException o) {
							o.printStackTrace();
						}
					}
		        }
			}
		};
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
	}
}
