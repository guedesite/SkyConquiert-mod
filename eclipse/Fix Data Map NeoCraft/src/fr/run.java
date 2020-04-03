package fr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class run {

	public static void main(String[] args) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		HashMap<Integer, String> mapChunk = new HashMap<Integer, String>();
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> listChunk = new ArrayList<Integer>();
		try
        {
			 BufferedReader br;
				try {
					br = new BufferedReader(new FileReader("dataMapStage.nc"));
					String line = "";
					String[] d;
					while((line = br.readLine()) != null)
					{
						d = GetArrayString(line);
						System.out.println(d[0]);
						System.out.println(d[1]);
						int id = Integer.parseInt(d[0]);
						String data = d[1];
						if(data.contains("Neo-Stage-Chunk"))
						{
							mapChunk.put(id, data);
						}
						else
						{
							map.put(id, data);
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();

				}	
         
        }
        catch (NumberFormatException ignore)
        {
        	ignore.printStackTrace();
        	System.out.println("nnn");
        }
		
		int fi = 0;
		
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			int id = (int)it.next();
			String d = map.get(id);
			int nb = 0;
			Iterator at = map.values().iterator();
			while(at.hasNext())
			{
				if(((String)at.next()).equals(d))
				{
					nb++;
				}
			}
			if(nb > 1)
			{
				
				list.add(id);
				fi ++;
			}
		}
		
		Iterator ut = mapChunk.keySet().iterator();
		while(ut.hasNext())
		{
			int id = (int)ut.next();
			String d = mapChunk.get(id);
			int nb = 0;
			Iterator at = mapChunk.values().iterator();
			while(at.hasNext())
			{
				if(((String)at.next()).equals(d))
				{
					nb++;
				}
			}
			if(nb > 1)
			{
				listChunk.add(id);
				fi ++;
			}
		}
		
		for(int i =0; i < list.size(); i++)
		{
			map.remove(list.get(i));
		}
		
		for(int i =0; i < listChunk.size(); i++)
		{
			mapChunk.remove(listChunk.get(i));
		}
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File("dataMapStage-verified.nc"), "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		
		}
		Iterator it2 = map.keySet().iterator();
		while(it2.hasNext())
		{
			int nbb = (int)it2.next();
			writer.println(nbb+"$"+map.get(nbb)+"$");
		}
		it2 = mapChunk.keySet().iterator();
		while(it2.hasNext())
		{
			int nbb = (int)it2.next();
			writer.println(nbb+"$"+mapChunk.get(nbb)+"$");
		}
		writer.close();
		System.out.println(fi);
	}
	public static int compterOccurrences(String maChaine, char recherche)
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
	public static String[] GetArrayString(String txt)
	{
		if(txt != null)
		{
			int t = txt.length();
			String[] finale = new String[compterOccurrences(txt, '$')];
			String pre = "";
			char cara, cara2;
			int d = 0;
			for(int i = 0; i < t; i++) 
			{
				
				cara = txt.charAt(i);
				if(cara == '$')
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

}