package fr.neocraft.main.Proxy.Client.Render.AdvancedGui;

import java.util.HashMap;
import java.util.Vector;

import fr.neocraft.main.main;
import fr.neocraft.main.Proxy.Client.Render.ScrollGui.GuiCustomScroll;
import fr.neocraft.main.Proxy.Client.Render.ScrollGui.GuiInterface;
import fr.neocraft.main.Proxy.Client.Render.ScrollGui.IScrollData;
import fr.neocraft.main.Proxy.Client.Render.SimpleGui.GuiShopNavigator;
import fr.neocraft.main.Proxy.Serveur.network.NetWorkServer;
import fr.neocraft.main.Proxy.Serveur.network.util.object.ServerActionGuiCmd;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.StatCollector;

public class GuiStageBank extends GuiInterface implements IScrollData{

	private GuiCustomScroll scroll;
	 private HashMap<String, Integer> map;
	 private GuiTextField unite;
	 
	 
	public GuiStageBank(HashMap<String, Integer> m) {
		
		this.map = m;
		this.title = "";
		 
		 this.xSize = 256;
		 setBackground("stage/background.png");
		
	}
	 
		  public void initGui() {
		    super.initGui();
		    
		    this.unite = new GuiTextField(this.fontRendererObj,this.guiLeft + 170, this.guiTop + 8, 82, 16);
		     this.unite.setMaxStringLength(12);
		     this.unite.setFocused(true);
		     this.unite.setText("1");
		     this.unite.setEnableBackgroundDrawing(true);
		    
		     this.TextField.put(0, this.unite);
		     
		    if (this.scroll == null) {
		      this.scroll = new GuiCustomScroll(this, 0);
		      this.scroll.setSize(165, 208);
		    } 
		    
		    if(this.title.equals(""))
		    {
		    	if(map.containsKey("money"))
		    	{
		    		this.title = "Bank | argent: "+ map.get("money")+"$";
		    		map.remove("money");
		    	}
		    }
		    
		    Vector<String> v = new Vector<String>();
		    v.addAll(map.keySet());
		   this.scroll.setList(v);
		    
		    this.scroll.guiLeft = this.guiLeft + 4;
		    this.scroll.guiTop = this.guiTop + 4;
		    addScroll(this.scroll);
		    
		    String title = StatCollector.translateToLocal("remote.title");
		    int x = (this.xSize - this.fontRendererObj.getStringWidth(title)) / 2;
		 
		    addButton(new GuiButton(0, this.guiLeft + 170, this.guiTop + 28, 82, 20, I18n.format("neo.gui.cmd.addbank")));
		    addButton(new GuiButton(1, this.guiLeft + 170, this.guiTop + 50, 82, 20, I18n.format("neo.gui.cmd.removebank")));
		    addButton(new GuiButton(2, this.guiLeft + 170, this.guiTop + 80, 82, 20,sell));
		    addButton(new GuiButton(3, this.guiLeft + 170, this.guiTop + 102, 82, 20, "Fermer"));
	
		  }
		  
		  private String sell = I18n.format("neo.gui.cmd.sell");
		  private String sellconfirm = I18n.format("neo.gui.cmd.sellconfirm");
		  private boolean IsValid()
		    {
		    	try {
		    		
		    		if(Integer.parseInt(this.unite.getText()) < 1)
		    		{
		    			return false;
		    		}
		    		
		    		return true;
		    	}
		    	catch(Exception e)
		    	{
		    		return false;
		    	}
		    }
		  
		  @Override
		    public void updateScreen()
		    {
		        super.updateScreen();
		        this.unite.updateCursorCounter();

		        if (!this.mc.thePlayer.isEntityAlive() || this.mc.thePlayer.isDead)
		        {
		            this.mc.thePlayer.closeScreen();
		        }
		        if(!IsValid() && this.buttonList != null)
		        {
		        	 for (int i = 0; i < this.buttonList.size(); i++) {
		 	            if (((GuiButton)this.buttonList.get(i)).id !=2 && ((GuiButton)this.buttonList.get(i)).id !=3) {
		 	            	((GuiButton)this.buttonList.get(i)).enabled = false;
		 	            }
		 	        }
		        }
		        else
		        {
		        	for (int i = 0; i < this.buttonList.size(); i++) {
		 	            if (((GuiButton)this.buttonList.get(i)).id !=2 && ((GuiButton)this.buttonList.get(i)).id !=3) {
		 	            	((GuiButton)this.buttonList.get(i)).enabled = true;
		 	            }
		 	        }
		        }
		    }
		  
		  public void keyTyped(char p_73869_1_, int p_73869_2_)
		    {
		        this.unite.textboxKeyTyped(p_73869_1_, p_73869_2_);
		 

		        if (p_73869_2_ != 28 && p_73869_2_ != 156)
		        {
		            if (p_73869_2_ == 1)
		            {
		            	Minecraft.getMinecraft().thePlayer.closeScreen();
		    			Minecraft.getMinecraft().displayGuiScreen(new GuiShopNavigator());
		            }
		        }
		        if(this.buttonList != null)
		        {
		        	if(!IsValid())
			        {
			        	 for (int i = 0; i < this.buttonList.size(); i++) {
			 	            if (((GuiButton)this.buttonList.get(i)).id !=2 && ((GuiButton)this.buttonList.get(i)).id !=3) {
			 	            	((GuiButton)this.buttonList.get(i)).enabled = false;
			 	            }
			 	        }
			        }
			        else 
			        {
			        	for (int i = 0; i < this.buttonList.size(); i++) {
			 	            if (((GuiButton)this.buttonList.get(i)).id !=2 && ((GuiButton)this.buttonList.get(i)).id !=3) {
			 	            	((GuiButton)this.buttonList.get(i)).enabled = true;
			 	            }
			 	        }
			        }
		        }

		    }
		  
		  
		  public void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
		    {
		        super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		        this.unite.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		        this.scroll.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		    }
		 
		  
		  protected void actionPerformed(GuiButton b) {
			  switch(b.id)
			  {
			  	case 0:
			  		if(this.scroll.hasSelected()&& IsValid())
			  		{
			  			main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerActionGuiCmd(map.get(this.scroll.getSelected()),2, Integer.parseInt(this.unite.getText()))));
			  		}
			  		break;
			  	case 1:
			  		if(this.scroll.hasSelected() && IsValid())
			  		{
			  			main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerActionGuiCmd(map.get(this.scroll.getSelected()),3, Integer.parseInt(this.unite.getText()))));
			  		}
			  		break;
			  	case 2:	
			  		if(this.scroll.hasSelected())
			  		{
			  			if(b.displayString.equals(sell))
			  			{
			  				b.displayString = sellconfirm;
			  			} else {
			  				main.getNetWorkServer().sendToServer(new NetWorkServer(new ServerActionGuiCmd(map.get(this.scroll.getSelected()),4)));
			  				Minecraft.getMinecraft().displayGuiScreen(null);
			  			}
			  		
			  		}
			  		break;
			  	case 3:	
			  		Minecraft.getMinecraft().displayGuiScreen(null);
			  		break;
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

}
