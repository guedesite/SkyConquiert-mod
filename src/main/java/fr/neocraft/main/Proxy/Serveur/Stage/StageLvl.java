package fr.neocraft.main.Proxy.Serveur.Stage;

import fr.neocraft.main.Proxy.Serveur.Stage.Recompense.IRecompenseStage;

public class StageLvl {

	private int xp, lvl;
	
	private IRecompenseStage recompense;
	
	public StageLvl(int lvll, int xpto, IRecompenseStage recomp)
	{
		xp = xpto;
		lvl = lvll;
		recompense = recomp;
	}

	public IRecompenseStage getRecompense() {
		return recompense;
	}

	public int getXp() {
		return xp;
	}



	public int getLvl() {
		return lvl;
	}

	
	
	
	
}
