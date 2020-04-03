package fr.neocraft.main.Proxy.Client.Render.AdvancedGui;

import java.util.HashMap;
import java.util.Vector;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Client.Render.ScrollGui.GuiCustomScroll;
import fr.neocraft.main.Proxy.Client.Render.ScrollGui.GuiInterface;
import fr.neocraft.main.Proxy.Client.Render.ScrollGui.GuiWarpScroll;
import fr.neocraft.main.Proxy.Client.Render.ScrollGui.IScrollData;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerActionGuiCmd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraftforge.client.ClientCommandHandler;

public class GuiWarpList extends GuiInterface implements IScrollData{

	private GuiWarpScroll scroll;
	
	 private HashMap<String, String> map = new HashMap<String, String>();
	

	 
	public GuiWarpList() {
		
		
		
		this.map.put("Warp Dragon", "dragon");
		this.map.put("Warp Nether", "nether");
		this.map.put("Warp Portail", "portail");
		this.map.put("Warp Shop", "shop");
		this.map.put(I18n.format("neo.spec.d"), "dieu");
		this.map.put(I18n.format("neo.spec.r"), "roi");
		this.map.put("Warp Staff", "staff");
		this.map.put("Warp Ultime Box", "ultime");
		this.map.put("Warp Minage", "minage");
		

		 this.xSize = 256;
		 setBackground("stage/background.png");
		//	 Client.sendData(EnumPacketServer.RemoteNpcsGet, new Object[0]);
	}
	 
	public boolean doesGuiPauseGame() { return false; }
	
		  public void initGui() {
		    super.initGui();
		    if (this.scroll == null) {
		      this.scroll = new GuiWarpScroll(this, 0);
		      Vector<String> v = new Vector<String>();
			    
			    v.addAll(map.keySet());
		      this.scroll.setList(v);
		      this.scroll.setSize(165, 208);
		    } 
		    
		   
		  
		    
		    this.scroll.guiLeft = this.guiLeft + 4;
		    this.scroll.guiTop = this.guiTop + 4;
		    addScroll(this.scroll);
		    

		    addButton(new GuiButton(0, this.guiLeft + 170, this.guiTop + 6, 82, 20, I18n.format("neo.gui.cmd.tp")));

		    addButton(new GuiButton(1, this.guiLeft + 170, this.guiTop + 40, 82, 20, "Fermer"));

		  }

		  
		/*  public void confirmClicked(boolean flag, int i) {
		    if (flag) {
		      Client.sendData(EnumPacketServer.RemoteDelete, new Object[] { this.data.get(this.scroll.getSelected()) });
		    }
		    NoppesUtil.openGUI(this.player, this);
		  } */
		  
		 
		  
		  protected void actionPerformed(GuiButton guibutton) {
			  switch(guibutton.id)
			  {
			  	case 0:
			  		if(this.scroll.hasSelected())
			  		{
			  				main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerActionGuiCmd(map.get(this.scroll.getSelected()),5)));
			  		}
			  		break;
			  	case 1:	
			  		Minecraft.getMinecraft().thePlayer.closeScreen();
			  		break;
			  }
			  
		/*    int id = guibutton.id;
		    if (id == 3) {
		      Client.sendData(EnumPacketServer.RemoteFreeze, new Object[0]);
		    }
		    if (id == 5) {
		      for (Iterator iterator = this.data.values().iterator(); iterator.hasNext(); ) { int ids = ((Integer)iterator.next()).intValue();
		        Client.sendData(EnumPacketServer.RemoteReset, new Object[] { Integer.valueOf(ids) });
		        Entity entity = this.player.worldObj.getEntityByID(ids);
		        if (entity != null && entity instanceof EntityNPCInterface) {
		          ((EntityNPCInterface)entity).reset();
		        } }
		    
		    }
		    if (!this.data.containsKey(this.scroll.getSelected())) {
		      return;
		    }
		    if (id == 0) {
		      Client.sendData(EnumPacketServer.RemoteMainMenu, new Object[] { this.data.get(this.scroll.getSelected()) });
		    }
		    if (id == 1) {
		      GuiYesNo guiyesno = new GuiYesNo(this, "Confirm", StatCollector.translateToLocal("gui.delete"), 0);
		      displayGuiScreen(guiyesno);
		    } 
		    if (id == 2) {
		      Client.sendData(EnumPacketServer.RemoteReset, new Object[] { this.data.get(this.scroll.getSelected()) });
		      Entity entity = this.player.worldObj.getEntityByID(((Integer)this.data.get(this.scroll.getSelected())).intValue());
		      if (entity != null && entity instanceof EntityNPCInterface)
		        ((EntityNPCInterface)entity).reset(); 
		    } 
		    if (id == 4) {
		      Client.sendData(EnumPacketServer.RemoteTpToNpc, new Object[] { this.data.get(this.scroll.getSelected()) });
		      close();
		    } */
		  }


		  
		  public void mouseClicked(int i, int j, int k) {
		    super.mouseClicked(i, j, k);
		    this.scroll.mouseClicked(i, j, k);
		  }

		  
		  public void keyTyped(char c, int i) {
		    if (i == 1 || isInventoryKey(i))
		    {
		      close();
		    }
		  }


		  
		  public void save() {}

		  
		  public void setData(Vector<String> list) {
		    this.scroll.setList(list);
		  }

		@Override
		public void setSelected(String paramString) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setData(Vector<String> paramVector, HashMap<String, Integer> paramHashMap) {
			// TODO Auto-generated method stub
			
		}

		  
		//  public void setSelected(String selected) { getButton(3).setDisplayText(selected); }
}

