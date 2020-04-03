package fr.neocraft.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Table;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;


public class bdd {
	
	public boolean IsDebug = false;
	public boolean IsOpen = false;
	public boolean IsClass = false;
	private String[] Connection;
	public Connection connexion;
	private  Map<Integer, Statement> s = new HashMap<Integer, Statement>();
	private int Id = 0;
	
	
	
	public bdd()
	{
		IsOpen = false;
		IsClass = false;
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			System.out.println("Openbdd CLASS LOAD");
			if(new File("bdd.data").exists())
			{
				 BufferedReader br;
				try {
					br = new BufferedReader(new FileReader("bdd.data"));
					Connection = new String[] { br.readLine(), br.readLine(), br.readLine()};
					br.close();
					this.IsClass = true;
				} catch (FileNotFoundException e) {
					this.IsClass = false;
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					this.IsClass = false;
				}
			} else
			{
				this.IsClass = false;
			}
		}
		catch ( ClassNotFoundException e ) {
			this.IsClass = false;
			erreur("bdd", e);
	   }
	}

	public void CloseClass() {
		this.IsClass = false;
	}
	
	public void Openbdd()
	{
		try {
			System.out.println("TRY BDD:");
			for(String e:Connection) {
				System.out.println(e);
			}
		    this.connexion = DriverManager.getConnection( Connection[0], Connection[1],Connection[2] );
		    this.IsOpen = true;
		    System.out.println("Openbdd BDD OPEN");
		}catch ( SQLException e ) {
			erreur("Openbdd", e);
			this.IsOpen = false;
		}
	}
	
	public void Closebdd()
	{
		try {
			Iterator it = this.s.values().iterator();
			int i = 0;
			while(it.hasNext())
			{
				((Statement)it.next()).close();
			}
			this.s = new HashMap<Integer, Statement>();
			this.connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 this.erreur("Closebdd", "success");
		 this.IsOpen = false;
	}

	
	public long getId() {
		return this.Id;
	}
	
	public Map getStatement()
	{
		return this.s;
	}
	
	public int getNbStatementActive()
	{
		
		if(this.s.isEmpty()) {
			return 0;
		} else {
			Iterator it = this.s.values().iterator();
			int i = 0;
			while(it.hasNext())
			{
				i++;
				it.next();
			}
			return i;
		}
	}
	
	public String getStringStage()
	{
		return "`Neo-Stage`";
	}
	public String getStringPlayer()
	{
		return "`Neo-Stage-Player`";
	}
	public String getStringChunk()
	{
		return "`Neo-Stage-Chunk`";
	}
	public String getStringXpBlock()
	{
		return "`Neo-Stage-XpBlock`";
	}
	public String getStringDeny()
	{
		return "`Neo-Stage-Deny`";
	}
	public String getStringHdv() {
		return "`Neo-Hdv`";
	}
	public String getStringAlerte() {
		return "`Neo-Stage-Alerte`";
	}
	public String getStringDrag()
	{
		return "`Neo-Stage-Dragon`";
	}
	public String getStringContainerRandom()
	{
		return "`Neo-Container-Random`";
	}
	
	public String getStringShopper()
	{
		return "`Neo-Pnj-Shopper`";
	}
	public String getStringLoyer3()
	{
		return "`Neo-Stage-Loyer-3`";
	}
	public String getStringCode()
	{
		return "`Neo-Stage-Code`";
	}
	public String getStringDenySpecial() {
		return "`Neo-Stage-Deny-special`";
	}

	
	public int GetFreeId() {
		if(this.Id >= Integer.MAX_VALUE-10)
		{
			s.clear();
			this.Id = 0;
		}
		this.Id++;
		if(this.IsDebug)
		{
			System.out.println("[BDD DEBUG] Create id: "+this.Id);
		}
		try {
			this.s.put(this.Id, this.connexion.createStatement());
			return this.Id;
		} catch (SQLException e) {
			this.erreur("GetFreeId("+this.Id+")", e);
			CloseFreeId(this.Id);
			return -1;
		}catch(NullPointerException e)
		{
			this.erreur("GetFreeId("+this.Id+")", e);
			return -1;
		}
	}
	
	public void CloseFreeId(int i) {
		Statement o = this.s.get(i);
		if(this.IsDebug)
		{
			System.out.println("[BDD DEBUG] delete id: "+i);
		}
		if(o != null)
		{
			try {
				o.close();
				if(this.s.remove(i, o)) {
				}
			} catch(SQLException e)
			{
				this.erreur("CloseFreeId("+i+")", e);
			}
			catch(NullPointerException e)
			{
				this.erreur("CloseFreeId("+i+")", e);
			}
		}
		else
		{
			this.erreur("CloseFreeId("+i+")", "NULL");
		}
	}
	
	public boolean Exist(String query)
	{
		int id = GetFreeId();
		if(this.s.containsKey(id))
		{
			ResultSet r = query(query, id);
			if(this.IsDebug)
			{
				System.out.println("[BDD DEBUG] EXIST: "+query);
			}
			if(r != null)
			{
				try {
					while(r.next())
					{
						CloseFreeId(id);
						return true;
					}
				} catch(Exception e)
				{
					this.erreur("Exist", e, query);
				}
			}else {
				this.erreur("Exist", "NULL", query);
			}
		}
		else
		{
			this.erreur("query", "key "+id+" null", query);
		}
		CloseFreeId(id);
		return false;
	}
	
	public ResultSet query(String query, int id)
	{
		ResultSet r = null;
		if(this.s.containsKey(id))
		{
			if(this.IsDebug)
			{
				System.out.println("[BDD DEBUG] QUERY: "+query);
			}
			try {
				r = this.s.get(id).executeQuery(query);
			} catch (SQLException e) {
				this.erreur("query", e, query);
			}catch(NullPointerException e)
			{
				this.erreur("query", e, query);
			}
		}
		else
		{
			this.erreur("query", "key "+id+" null", query);
		}
		return r;
	}

	
	public boolean update(String query)
	{
		int id = GetFreeId();
		if(this.s.containsKey(id))
		{
			if(this.IsDebug)
			{
				System.out.println("[BDD DEBUG] UPDATE: "+query);
			}
			try {
				this.s.get(id).executeUpdate(query);
				CloseFreeId(id);
				return true;
			} catch (SQLException e) {
				this.erreur("update", e, query);
			}
		}
		else
		{
			this.erreur("update", "key "+id+" null", query);
		}
		CloseFreeId(id);
		return false;
	}

	
	public boolean execute(String query)
	{
		int id = GetFreeId();
		if(this.s.containsKey(id))
		{
			if(this.IsDebug)
			{
				System.out.println("[BDD DEBUG] EXECUTE: "+query);
			}
			try {
				this.s.get(id).execute(query);
				CloseFreeId(id);
				return true;
			} catch (SQLException e) {
				this.erreur("query", e, query);
		
			}
		}
		else
		{
			this.erreur("execute", "key "+id+" null", query);

		}
		CloseFreeId(id);
		return false;
	}

	public int compterligne(String Table, String condition)
	{
		int id = GetFreeId();
		ResultSet r = query("SELECT count(*) AS nbLignes FROM "+Table+" WHERE "+condition, id);
		int id2 = 0;
		if(r != null)
		{
			try {
				while(r.next())
				{
					id2 =  r.getInt("nbLignes");
				}
			} catch(Exception e)
			{
				this.erreur("compterligne", e,"SELECT count(*) AS nbLignes FROM "+Table+" WHERE "+condition);
			}
		}
		else
		{
			this.erreur("compterligne", "NULL","SELECT count(*) AS nbLignes FROM "+Table+" WHERE "+condition);
		}
		CloseFreeId(id);
		return id2;
	}
	public int compterligne(String Table)
	{
		return compterligne(Table, "1");
	}
	
	public int getMaxOf(String max, String table)
	{
		int id = GetFreeId();
		ResultSet r = query("SELECT MAX("+max+") FROM "+table, id);
		if(r != null)
		{
			try {
				while(r.next())
				{
					return r.getInt("MAX("+max+")");
				}
			} catch(Exception e)
			{
				this.erreur("getMaxOf", e,"SELECT MAX("+max+") FROM "+table);
			}
		}else
		{
			this.erreur("getMaxOf", "NULL","SELECT MAX("+max+") FROM "+table);
		}
		CloseFreeId(id);
		return 0;
	}
	
	public void erreur(String f, Exception e, String stat)
	{
		System.err.println("ERREUR LORS DE L'EXECUTION '"+f +"'");
		System.err.println(" INFO: ");
		e.printStackTrace();
		System.err.println(" DATA: " + stat);
	}

	public void erreur(String f, Exception e) {
		System.err.println("ERREUR LORS DE L'EXECUTION '"+f +"'");
		System.err.println(" INFO: ");
		e.printStackTrace();
	}
	public void erreur(String f, String e) {
		System.err.println("ERREUR LORS DE L'EXECUTION '"+f +"'");
		System.err.println(" INFO: "+e);
	}

	public void erreur(String f, String e, String stat)
	{
		System.err.println("ERREUR LORS DE L'EXECUTION '"+f +"'");
		System.err.println(" INFO: "+e);
		System.err.println(" DATA: " + stat);
	}


	
	public String SetArrayString(String[] txt)
	{
		String finale = "";
		if(txt != null)
		{
			for(String e:txt)
			{
					finale += e + "&";
			}
			return finale;
		}
		return "";
	}
	
	public String[] GetArrayString(String txt)
	{
		if(txt != null)
		{
			int t = txt.length();
			String[] finale = new String[compterOccurrences(txt, '&')];
			String pre = "";
			char cara, cara2;
			int d = 0;
			for(int i = 0; i < t; i++) 
			{
				
				cara = txt.charAt(i);
				if(cara == '&')
				{
					finale[d] = pre;
					pre = "";
					d++;
				}
				else
				{
					pre += cara;
				}
			}
			return finale;
		}
		else
		{
			return null;
		}
	}
	
	public ArrayList<String> GetArrayListString(String txt)
	{
		ArrayList<String> f = new ArrayList<String>();
		if(txt != null)
		{

			int t = txt.length();
			String pre = "";
			char cara;
			for(int i = 0; i < t; i++) 
			{
				
				cara = txt.charAt(i);
				if(cara == '&')
				{
					f.add(pre);
					pre = "";
				}
				else
				{
					pre += cara;
				}
			}
		}
		return f;
		
	}
	

	public String SetArrayInt(int[] txt)
	{
		String finale = "";
		for(int i = 0; i < txt.length; i++)
		{
				finale += txt[i] + "&";
		}
		return finale;
	}
	public int[] GetArrayInt(String txt)
	{
		if(txt == null) { return null; }
		int t = txt.length();
		int[] finale = new int[compterOccurrences(txt, '&')];
		String pre = "";
		char cara, cara2;
		int d = 0;
		for(int i = 0; i < t; i++) 
		{
			
			cara = txt.charAt(i);
			if(cara == '&')
			{
				finale[d] = Integer.parseInt(pre);
				pre = "";
				d++;
			}
			else
			{
				pre += cara;
			}
		}
		return finale;
	}
	public int compterOccurrences(String maChaine, char recherche)
	{
		 int nb = 0;
		 for (int i=0; i < maChaine.length(); i++)
		 {
			 if (maChaine.charAt(i) == recherche)
			 {
				 nb++;
			 }
		 }
		 return nb;
	}

	
}
