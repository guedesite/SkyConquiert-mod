package fr.neocraft.main.Proxy.Client.event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Client.Render.AdvancedGui.GuiStageHome;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

public class OnChat {
	
	
    @SubscribeEvent
    public void onChatMessage(ClientChatReceivedEvent event)
    {
 
    	String txt = event.message.getUnformattedText();

    	
    	
    	if(txt.contains("neoexe:hide1"))
    	{
    		event.setCanceled(true);
    		TextDraw.setString("", 1);
    	}
    	else if(txt.contains("neoexe:hide2"))
    	{
    		event.setCanceled(true);
    		TextDraw.setString("", 2);
    	}
    	

    	else if(txt.contains("neoexe:hide3"))
    	{
    		event.setCanceled(true);
    		TextDraw.setString("", 3);
    	}
    	else if(txt.contains("neoexe:action2voter*"))
    	{
    		event.setCanceled(true);
    		TextDraw.setString(EnumChatFormatting.BOLD + "" + EnumChatFormatting.GRAY  + GetScript(true, txt) + EnumChatFormatting.RESET  + "" + EnumChatFormatting.WHITE  +" vient de Voter, Merci !", 2);
    	}
    	else if(txt.contains("neoexe:action2boutique*"))
    	{
    		event.setCanceled(true);
    		TextDraw.setString(EnumChatFormatting.BOLD + "" + EnumChatFormatting.RED +  "" + EnumChatFormatting.BOLD + GetScript(true, txt) + EnumChatFormatting.RESET + "" + EnumChatFormatting.RED + " Vient d'acheter " + EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.OBFUSCATED + "!!" + EnumChatFormatting.RESET + "" + EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + GetScript(false, txt) + EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.OBFUSCATED + "!!" + EnumChatFormatting.RESET + "" + EnumChatFormatting.RED + " !", 2);
    	}
    	else if(txt.contains("neoexe:action3*"))
    	{
    		event.setCanceled(true);
    		TextDraw.setString(GetScript(true, txt), 3);
    	}
    	else if(txt.contains("neoexe:OpenGui*"))
    	{
    		Minecraft.getMinecraft().thePlayer.openGui(main.instance, Integer.parseInt(GetScript(true, txt)), Minecraft.getMinecraft().thePlayer.worldObj,(int) Minecraft.getMinecraft().thePlayer.posX, (int)Minecraft.getMinecraft().thePlayer.posY, (int)Minecraft.getMinecraft().thePlayer.posZ);
    	}
    }
    
    public String GetScript(boolean nb, String txt)
   	{
   			int taille = txt.length();
   			String messagetext = "";
   			boolean stat = false;
   			char messageUnCaractere;
   			
   			for(int i = 0; i < taille; i++) 
   			{
   				if(nb == true)
   				{
	   				messageUnCaractere = txt.charAt(i);
	   				if(messageUnCaractere == '*')
	   				{
	   					if(stat == true)
	   					{
	   						stat = false;
	   					}
	   					else
	   					{
	   						stat = true;
	   					}
	   				}
	   				else if(stat == true)
	   				{
	   					messagetext += messageUnCaractere;
	   				}
   				}
   				else if(nb == false)
   				{
   					messageUnCaractere = txt.charAt(i);
	   				if(messageUnCaractere == '&')
	   				{
	   					if(stat == true)
	   					{
	   						stat = false;
	   					}
	   					else
	   					{
	   						stat = true;
	   					}
	   				}
	   				else if(stat == true)
	   				{
	   					messagetext += messageUnCaractere;
	   				}
   				}
   			}
   			return messagetext;
   	}
   			
}
