package fr.neocraft.main.Init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Serveur.Stage.RegisterStage;
import fr.neocraft.main.Proxy.Serveur.Stage.Stage;

public class SavingData {
	
	private static final fr.neocraft.main.bdd bdd = main.getbdd();

	public void SaveStage(boolean stat)
	{
		Iterator it = RegisterStage.Stage.values().iterator();
					Stage e;
					String end = "";
					int i = 0;
				    while (it.hasNext()) {
				        e = ((Stage)it.next());
				        if(e.IsChange)
				        {
				        	i++;
				        	e.IsChange = false;
				        	bdd.update("UPDATE `Neo-Stage` SET `id2`='"+ e.getId() +"',"
							        				+ "`WorldId`='"+ e.getIdWorld() +"',"
							        				+ "`StageId`='"+ e.getIdStage() +"',"
							        				+ getOwner("owner", e.getOwner())
							        				+ getSubMember("subowner",e.getSubOwner())
							        				+ "`XPos`='"+e.getXpos() +"',"
							        				+ "`ZPos`='"+e.getZpos()+"',"
							        				+ getSubMember("PermAll",e.getPermAll())
							        				+ getSubMember("PermMember",e.getPermMember())
							        				+ "`isUse`='"+e.IsUseCode()+"',"
							        				+ "`x`='"+e.getX()+"',"
							        				+ "`y`='"+e.getY()+"',"
							        				+ "`z`='"+e.getZ()+"',"
							        				+ "`bank`='"+e.getBank()+"',"
							        				+ "`hasHome`='"+e.hasHomeCode()+"', "
							        				+ "`name`='"+e.getName()+"', "
							        				+ "`lvl`="+e.getLvl()+", "
							        				+ getSubMember("Ally",e.getAlly())
							        				+ getSubMember("Ennemy",e.getEnnemy())
							        				+ "`bouclier`='"+e.getStringBouclier()+"'"
							        				+ "WHERE id2='"+e.getId()+"'");
							        		end += e.getId()+" ";
							       
				        }
				     
				    }
				    RegisterStage.OldStage = new HashMap<Integer,Stage>(RegisterStage.Stage);
				    System.out.println("[NEOCRAFT SAVER STAGE] finishing stat:" +i+" | "+ end);


	}
	
	public String getSubMember(String t, ArrayList<String> ar)
	{
		if(!ar.isEmpty())
		{
			return "`"+t+"`='"+SetArrayListString(ar)+"',";
		}
		return "`"+t+"`=NULL,";	
	}
	
	private String SetArrayListString(ArrayList<String> ar) {
		String finale = "";
		for(String d:ar)
		{
			finale += d+"&";
		}
		return finale;
	}

	public String getOwner(String t, String perm)
	{

		if(perm != null)
		{
			if(perm== "null")
			{
				return "`"+t+"`=NULL,";
			}
			else if(perm!= "" & perm != " ")
			{
				return "`"+t+"`='"+perm+"',";
			}
		}
		return "`"+t+"`=NULL,";
		
	}
}
