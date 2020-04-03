package fr.neocraft.main.Proxy.Serveur.Stage.Recompense;

import fr.neocraft.main.Proxy.Serveur.Stage.Stage;
import net.minecraft.entity.player.EntityPlayer;

public class RargentStage implements IRecompenseStage {

	private int arg = 0;
	
	public RargentStage(int argent)
	{
		arg = argent;
	}
	

	@Override
	public void PushStage(Stage s) {
		s.addtoBank(arg);
	}

}
