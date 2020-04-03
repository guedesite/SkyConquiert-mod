package fr.neocraft.main.Proxy.Client.event;

import java.util.ArrayList;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.BossText;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiDebug;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiQuest;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiSpecialAdvanced;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiText;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiTextAnnonce;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiTextAnnonceInfo;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiTextTabList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class TextDraw
{
	public static ArrayList<String> Player_List = null;
	public static String text_drag = "";
	public static String text_bvn = "";
	public static String text_annonce = "";
	public static String text_info = "";
	public static boolean isInAnnim = false;
	public static boolean isQuest = true;
	public static int nbtoreach = 300, nbforreach = 0;
	public static boolean Questbool = true;
	private boolean isbvn = true;
	private Minecraft mc = Minecraft.getMinecraft();
	
	@SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Post event)
    {

		if (event.type != ElementType.EXPERIENCE) {
			return;
		}
		
		ScaledResolution scaled = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = scaled.getScaledWidth();
        int height = scaled.getScaledHeight();
		
		if(!text_info.equals(""))
		{
			new GuiTextAnnonceInfo(Minecraft.getMinecraft(), text_info);
		}
		
		if(Questbool) { new GuiQuest(Minecraft.getMinecraft(), width, height); }
		
		if(text_drag != "") { new GuiSpecialAdvanced(Minecraft.getMinecraft(),text_drag, width, height); }
		
		if(main.BossActive && Questbool) { new BossText(Minecraft.getMinecraft(), width, height); }
		
		if(nbtoreach != nbforreach)
		{
			if(isbvn)
			{
				new GuiText(Minecraft.getMinecraft(), text_bvn, width, height);
			}
			new GuiTextAnnonce(Minecraft.getMinecraft(), text_annonce);
			nbforreach++;
		}
		else
		{
			isbvn = false;
		}
    }
	
	@SubscribeEvent
    public void onRenderGui(RenderGameOverlayEvent.Pre event)
    {
		if (event.type == RenderGameOverlayEvent.ElementType.PLAYER_LIST) {
			event.setCanceled(true);
			
			new GuiTextTabList(Minecraft.getMinecraft());
		} else if(event.type == RenderGameOverlayEvent.ElementType.DEBUG)
		{
			event.setCanceled(true);
			new GuiDebug(Minecraft.getMinecraft());
		}
    }
	
	public static void setString(String bvn, int i)
	{
		if(i == 1)
		{
			text_bvn = bvn;
		} else if(i == 2)
		{
			text_annonce = bvn;
			nbforreach = 0;
		} else if(i == 3)
		{
			text_info = bvn.replace("_", " ");
		}
	}
}
