package fr.neocraft.main.Proxy.Client.Render.ScrollGui;

import java.util.HashMap;
import java.util.Vector;

public interface IScrollData {
	void setData(Vector<String> paramVector, HashMap<String, Integer> paramHashMap);
	void setSelected(String paramString);
}
